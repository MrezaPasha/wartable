package org.sadr.service.main.rpc.brokerUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc._types.TtConfig;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;

import java.sql.Timestamp;
import java.util.*;

public class BrokerUtils {
    //private static ServiceConfigServiceImp serviceConfigServiceImp = (ServiceConfigServiceImp)IOCContainer.GetBeans(ServiceConfigServiceImp.class);

    public static boolean unicastMessage(String message , String queueName)
    {
        try
        {
            Map configMap = new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }
            String brokerIP = configMap.get(TtConfig.ServerIP).toString();
            String brokerUsername = configMap.get(TtConfig.ServerLoginUsername).toString();
            String brokerPassword = configMap.get(TtConfig.ServerLoginPassword).toString();
            int brokerPort = Integer.parseInt(configMap.get(TtConfig.ServerIP).toString());
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(brokerIP);
            factory.setUsername(brokerUsername);
            factory.setPassword(brokerPassword);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicPublish("",queueName,null,message.getBytes());
            channel.close();
            connection.close();
            return true;

        }
        catch (Exception e)
        {
            return false;
        }
    }
    public static boolean broadcastMessage(String meesage,String exchange)
    {
        try
        {
            //List<ServiceConfig> serviceConfigs = serviceConfigServiceImp.findAll();
            //ServiceConfig serviceConfig = serviceConfigs.get(0);
            Map configMap = new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }
            String brokerIP = configMap.get(TtConfig.ServerIP).toString();
            String brokerUsername = configMap.get(TtConfig.ServerLoginUsername).toString();
            String brokerPassword = configMap.get(TtConfig.ServerLoginPassword).toString();
            int brokerPort = Integer.parseInt(configMap.get(TtConfig.ServerPort).toString());
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(brokerIP);
            factory.setUsername(brokerUsername);
            factory.setPassword(brokerPassword);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicPublish(exchange,"",null, meesage.getBytes());
            channel.close();
            connection.close();
            return true;

        }
        catch (Exception e)
        {
            //e.printStackTrace();
            return false;

        }
    }
    public static boolean declareQueue(String queueName)
    {
        try {
            //List<ServiceConfig> serviceConfigs = serviceConfigServiceImp.findAll();
            //ServiceConfig serviceConfig = serviceConfigs.get(0);
            Map configMap = new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(configMap.get(TtConfig.ServerIP).toString());
            factory.setUsername(configMap.get(TtConfig.ServerLoginUsername).toString());
            factory.setPassword(configMap.get(TtConfig.ServerLoginPassword).toString());
            Connection connection = null;
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
            channel.basicQos(1);
            connection.close();
            //channel.close();
            return true;
        }
        catch (Exception e )
        {
            e.printStackTrace();
            return false;
        }

    }
    public static boolean declareExchange(String exchangeName)
    {
        try
        {
            //List<ServiceConfig> serviceConfigs = serviceConfigServiceImp.findAll();
            Map configMap = new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }
            //ServiceConfig serviceConfig = serviceConfigs.get(0);
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(configMap.get(TtConfig.ServerIP).toString());
            factory.setUsername(configMap.get(TtConfig.ServerLoginUsername).toString());
            factory.setPassword(configMap.get(TtConfig.ServerLoginPassword).toString());
            Connection connection = null;
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchangeName,"fanout",true,false,null);
            channel.basicQos(1);
            channel.close();
            connection.close();
            return true;

        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean addQueueToExchange(String queueName , String exchange)
    {
        try
        {
            //List<ServiceConfig> serviceConfigs = serviceConfigServiceImp.findAll();
            //ServiceConfig serviceConfig = serviceConfigs.get(0);
            Map configMap = new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(configMap.get(TtConfig.ServerIP).toString());
            factory.setUsername(configMap.get(TtConfig.ServerLoginUsername).toString());
            factory.setPassword(configMap.get(TtConfig.ServerLoginPassword).toString());
            Connection connection = null;
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueBind(queueName,exchange,queueName);
            channel.basicQos(1);
            connection.close();
            //channel.close();
            return true;


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }
    public static String defineQueue()
    {
        try
        {
            String secondstSyllabus = UUID.randomUUID().toString();
            String finalQueueName =  secondstSyllabus;
            return finalQueueName;

        }
        catch (Exception e )
        {
            return null;

        }
    }
    public static Timestamp nowTime()
    {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static boolean deleteQueue(String queueName)
    {
        try {
            //List<ServiceConfig> serviceConfigs = serviceConfigServiceImp.findAll();
            //ServiceConfig serviceConfig = serviceConfigs.get(0);
            Map configMap = new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(configMap.get(TtConfig.ServerIP).toString());
            factory.setUsername(configMap.get(TtConfig.ServerLoginUsername).toString());
            factory.setPassword(configMap.get(TtConfig.ServerLoginPassword).toString());
            Connection connection = null;
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueDelete(queueName);
            channel.basicQos(1);
            connection.close();
            //channel.close();
            return true;
        }
        catch (Exception e )
        {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean removeQueueFromExchange(String queueName , String exchange)
    {
        try
        {
            Map configMap = new HashMap();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }
            //List<ServiceConfig> serviceConfigs = serviceConfigServiceImp.findAll();
            //ServiceConfig serviceConfig = serviceConfigs.get(0);
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(configMap.get(TtConfig.ServerIP).toString());
            factory.setUsername(configMap.get(TtConfig.ServerLoginUsername).toString());
            factory.setPassword(configMap.get(TtConfig.ServerLoginPassword).toString());
            Connection connection = null;
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueUnbind(queueName,exchange,"");
            channel.basicQos(1);
            connection.close();
            //channel.close();
            return true;


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }
}
