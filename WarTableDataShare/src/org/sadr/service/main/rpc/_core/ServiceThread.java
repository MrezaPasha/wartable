package org.sadr.service.main.rpc._core;

import com.rabbitmq.client.*;
import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtCompareResult;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils._type.TtCalendarItem;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc._types.TtConfig;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsServiceImp;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceThread {

    private static final String RPC_QUEUE_NAME = "serverRPC";

    private static int fib(int n) {
        if (n ==0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }

    public static void main(/*String[] argv*/) {
        //new Utils().test();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.0.21.4");

        Connection connection = null;
        try {
            connection      = factory.newConnection();
            final Channel channel = connection.createChannel();

            //channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

            channel.basicQos(1);

            System.out.println(" [x] Awaiting RPC requests");


            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                            .Builder()
                            .correlationId(properties.getCorrelationId())
                            .build();

                    String response = "";
                    System.out.println("################################################################");
                    //Test.insertTestConf();
                    //Test.inserTestRoom();
                    //Test.insertTestMap();
                    //new Test().inserTestUser();
                    //Test.insertTestMapSession();

                    //new Utils().test();
                    //LayerServiceImp layerServiceImp = (LayerServiceImp) IOCContainer.GetBeans(LayerServiceImp.class);
                    //layerServiceImp.uploadMap("taiz.zip");

                    System.out.println("################################################################");

                    String message = new String(body,"UTF-8");
                    System.out.println("##########################REQUEST###############################");
                    System.out.println(message);
                    System.out.println("################################################################");

                    RpcRequest rpcRequest = Utils.JsonToRpcProtocolObj(message);
                    try {
                        if (rpcRequest != null && rpcRequest.getParams().isEmpty() == false && rpcRequest.getMethod() != null
                                && rpcRequest.getId() != 0 && rpcRequest.getJsonrpc()!= null)
                        {
                            response = Dispatch.dispatchFunction(rpcRequest);
                        }
                        else
                        {
                            response = Utils.invalidJsonRequest(rpcRequest);
                        }
                        //System.out.println(response);

                        //System.out.println(" [.] fib(" + message + ")");
                        //response += fib(n);
                    }
                    catch (RuntimeException e){
                        response = Utils.invalidJsonRequest(rpcRequest);
                        System.out.println(" [.] " + e.toString());
                    }
                    finally {
                        System.out.println("##########################RESPONSE##############################");
                        System.out.println(response);
                        System.out.println("################################################################");
                        if (!response.isEmpty())
                        {
                            channel.basicPublish( "", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
                        }
                        channel.basicAck(envelope.getDeliveryTag(), false);
                        // RabbitMq consumer worker thread notifies the RPC server owner thread
                        synchronized(this) {
                            this.notify();
                        }
                    }
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
            // Wait and be prepared to consume the message from RPC client.
            while (true) {
                synchronized(consumer) {
                    try {
                        consumer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (IOException _ignore) {}
        }
    }
    public static void sessionTimeoutDetector()
    {
        try
        {
            SessionsServiceImp sessionsServiceImp = (SessionsServiceImp) IOCContainer.GetBeans(SessionsServiceImp.class);
            //ServiceConfigServiceImp serviceConfigServiceImp = (ServiceConfigServiceImp)IOCContainer.GetBeans(ServiceConfigServiceImp.class);
            Room_ServiceUserServiceImp room_serviceUserServiceImp = (Room_ServiceUserServiceImp) IOCContainer.GetBeans(Room_ServiceUserServiceImp.class);

            //ServiceConfig serviceConfig = serviceConfigServiceImp.findAll().get(0);
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);
            BaseConfig baseConfig = baseConfigServiceImp.findBy(Restrictions.eq(BaseConfig.CONFIG_ID,TtConfig.SessionTimeoutMinute));
            int afterNow = Integer.parseInt(baseConfig.getConfigValue());

            List<Sessions> sessions = sessionsServiceImp.findAll();
            if (!sessions.isEmpty())
            {
                for (Sessions session: sessions)
                {
                 String creationTime = session.getCreationDateTime();
                 String updateTime = session.getUpdateDateTime();
                 String nowTime = ParsCalendar.getInstance().getShortDateTime();
                 String tresholdTime = ParsCalendar.getInstance().getShortDate(TtCalendarItem.Minute,afterNow,updateTime);
                 if (ParsCalendar.getInstance().compareTime(nowTime,tresholdTime) == TtCompareResult.SecondIsBigger)
                 {
                     // session expire unicat
                     //String unicastMessage = new State().setStateBytes(true,TtState.SESS_ERROR_SESSION_EXPIRED);
                     //BrokerUtils.unicastMessage(unicastMessage,session.getSessionId());
                     // wait 2 second
                     // call logout
                     //sessionsServiceImp.delete(session);

                     // here we must delete user from room_serviceUser table
                     List<Sessions> sessionss =  sessionsServiceImp.findAllBy(Restrictions.eq(Sessions.SESSION_ID,session.getSessionId()),Sessions._SERVICE_USER);
                     if (sessionss.isEmpty())
                     {
                         for (Sessions sess:sessionss)
                         {
                             ServiceUser serviceUser = sess.getServiceUser();
                             Room_ServiceUser roomServiceUser = room_serviceUserServiceImp.findBy(Restrictions.eq(Room_ServiceUser._SERVICE_USER,serviceUser));
                             if (roomServiceUser != null)
                             {
                                 room_serviceUserServiceImp.delete(roomServiceUser);
                             }
                         }
                     }
                 }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public static void startOriginalService()
    {
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(new Runnable() {
            public void run() {
               ServiceThread.main();
           }
         });
    }
}
