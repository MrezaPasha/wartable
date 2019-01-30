package org.sadr.service.main.rpc.rpcHandler;

import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.log.models.importance.ImportanceServiceImp;
import org.sadr.share.main.log.models.serviceLog.ServiceLogServiceImp;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.serviceUser.ServiceUserServiceImp;

public class RpcHandler {
    private ServiceUserServiceImp serviceUserServiceImp = (ServiceUserServiceImp) IOCContainer.GetBeans(ServiceUserServiceImp.class);
    //private ServiceConfigServiceImp serviceConfigServiceImp = (ServiceConfigServiceImp)IOCContainer.GetBeans(ServiceConfigServiceImp.class);
    private ServiceLogServiceImp serviceLogServiceImp = (ServiceLogServiceImp) IOCContainer.GetBeans(ServiceLogServiceImp.class);
    private ImportanceServiceImp importanceServiceImp = (ImportanceServiceImp) IOCContainer.GetBeans(ImportanceServiceImp.class);
    private RoomServiceImp roomServiceImp = (RoomServiceImp) IOCContainer.GetBeans(RoomServiceImp.class);

    private RpcResponse rpcResponse = new RpcResponse();
    private ErrorResponse errorResponse = new ErrorResponse();
   /* public  RpcResponse login(RpcRequest rpcRequest)
    {
        try
        {
            List<ServiceConfig> serviceConfig = serviceConfigServiceImp.findAll();
            ServiceConfig config = serviceConfig.get(0);
            String userName = (String) rpcRequest.getParams().get("Username");
            String password = (String) rpcRequest.getParams().get("Password");
            ServiceUser user = serviceUserServiceImp.findBy(Restrictions.and(
                Restrictions.eq(ServiceUser.USERNAME,userName),
                Restrictions.eq(ServiceUser.PASSWORD,password)),
                ServiceUser._PRIVILAGE_CLASS,ServiceUser.LOGIN_COUNT,ServiceUser.FAILED_TIME,ServiceUser._LAST_ROOM);
            if(user != null)
            {
                if(user.getDeleted() == TtServiceUserState.UnDeletedUser)
                {
                    if (user.getBanned() == TtServiceUserState.BannedUser)
                    {
                        if(serviceConfigServiceImp.count() != 0)
                        {

                            //Timestamp lastFailedtime = user.getFailedTime();
                            String lastFailedtime = user.getFailedTime();
                            //Timestamp nowTime = new Timestamp(new Date().getTime());
                            String nowTime = ParsCalendar.getInstance().getShortDateTime();

                           // Duration duration = Duration.between(lastFailedtime.toLocalDateTime().toLocalDate(),
                            //    nowTime.toLocalDateTime().toLocalDate());
                            //if(duration.getSeconds() < config.getBannedTimePeriod().getSeconds())
                            if(Utils.isInTreshold(lastFailedtime,config.getBannedTimePeriod(),userName,password) == true)
                            {
                                rpcResponse.setResult(null);
                                errorResponse.setCode(TtErrors.UserIsLocked.ordinal());
                                errorResponse.setDescription(TtErrors.UserIsLocked.geterrorValue());
                                rpcResponse.setError(errorResponse);
                                new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                                return rpcResponse;


                            }
                            else if (Utils.isInTreshold(lastFailedtime,config.getBannedTimePeriod(),userName,password) == false)
                            {
                                user.setLoginCount(user.getLoginCount() + 1);
                                user.setBanned(TtServiceUserState.UnBannedUser);
                                user.setFailedTime(null);
                                user.setFailedPasswordCount(0);
                                Room room = user.getLastRoom();
                                BrokerUtils brokerUtils = new BrokerUtils();
                                String queueName = brokerUtils.defineQueue(room.getName());
                                if(brokerUtils.declareQueue(queueName) && brokerUtils.declareExchange(room.getName()))
                                {
                                    if (brokerUtils.addQueueToExchange(queueName,room.getName()))
                                    {
                                        // update user
                                        serviceUserServiceImp.update(user);
                                        // broadcast to all members of room
                                        String broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
                                        brokerUtils.broadcastMessage(broadcastMessage,room.getName());
                                        // construct response
                                        rpcResponse = Utils.generateSuccessLoginResponse(rpcRequest,user,config,queueName);
                                        new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Success,TtSubType.SuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                                        // return final response
                                        return rpcResponse;
                                    }
                                }
                            }
                        }

                    }
                    else if (user.getBanned() == TtServiceUserState.UnBannedUser)
                    {
                        user.setLoginCount(user.getLoginCount() + 1);
                        BrokerUtils brokerUtils = new BrokerUtils();
                        //int roomId = user.getLastRoomId();
                        Room room = user.getLastRoom();
                        String queueName = brokerUtils.defineQueue(room.getName());
                        if(brokerUtils.declareQueue(queueName) && brokerUtils.declareExchange(room.getName())) {
                            if (brokerUtils.addQueueToExchange(queueName, room.getName())) {
                                serviceUserServiceImp.update(user);
                                String broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
                                rpcResponse = Utils.generateSuccessLoginResponse(rpcRequest,user,config,queueName);
                                brokerUtils.broadcastMessage(broadcastMessage,room.getName());
                                new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Success,TtSubType.SuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                                return rpcResponse;
                            }
                        }

                    }

                }
                else if (user.getDeleted() == TtServiceUserState.DeletedUser)
                {
                    RpcResponse rpcResponse = Utils.generateDeletedUserdLoinResponse(rpcRequest);
                    new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                    return rpcResponse;

                }
            }
            else if (user == null)
            {
                ServiceUser unAuthenticateUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.USERNAME,userName));
                if(unAuthenticateUser != null)
                {
                    if (unAuthenticateUser.getDeleted() == TtServiceUserState.UnDeletedUser)
                    {
                        if (unAuthenticateUser.getBanned() == TtServiceUserState.UnBannedUser)
                        {
                            if (unAuthenticateUser.getFailedPasswordCount() == 0)
                            {
                                unAuthenticateUser.setFailedPasswordCount(1);
                                //unAuthenticateUser.setFailedTime(new Timestamp(new Date().getTime()));
                                unAuthenticateUser.setFailedTime(ParsCalendar.getInstance().getShortDateTime());
                                serviceUserServiceImp.update(unAuthenticateUser);
                                RpcResponse rpcResponse = Utils.generateTresholdRetryLoginResponse(rpcRequest);
                                new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                                return rpcResponse;



                            }
                            else if (unAuthenticateUser.getFailedPasswordCount() != 0)
                            {
                                if (unAuthenticateUser.getFailedPasswordCount() == config.getBannedRetryCount()-1)
                                {
                                    //Timestamp lastFailedtime = unAuthenticateUser.getFailedTime();
                                    String lastFailedtime = unAuthenticateUser.getFailedTime();
                                    //Timestamp nowTime = new Timestamp(new Date().getTime());
                                    String nowTime = ParsCalendar.getInstance().getShortDateTime();
                                    //Duration duration = Duration.between(lastFailedtime.toLocalDateTime().toLocalDate(),
                                     //   nowTime.toLocalDateTime().toLocalDate());
                                    //if (duration.getSeconds() < config.getBannedTimePeriod().getSeconds())
                                    if(Utils.isInTreshold(lastFailedtime,config.getBannedTimePeriod(),userName,password) == true)
                                    {
                                        unAuthenticateUser.setBanned(TtServiceUserState.BannedUser);
                                        //unAuthenticateUser.setFailedTime(new Timestamp(new Date().getTime()));
                                        unAuthenticateUser.setFailedTime(ParsCalendar.getInstance().getShortDateTime());
                                        serviceUserServiceImp.update(unAuthenticateUser);
                                        RpcResponse rpcResponse = Utils.generateLockUserLoginResponse(rpcRequest);
                                        new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                                        return rpcResponse;
                                    }
                                    //else if (duration.getSeconds() >= config.getBannedTimePeriod().getSeconds())
                                    else if (Utils.isInTreshold(lastFailedtime,config.getBannedTimePeriod(),userName,password) == false)
                                    {
                                        //unAuthenticateUser.setFailedTime(new Timestamp(new Date().getTime()));
                                        unAuthenticateUser.setFailedTime(ParsCalendar.getInstance().getShortDateTime());
                                        unAuthenticateUser.setFailedPasswordCount(1);
                                        serviceUserServiceImp.update(unAuthenticateUser);
                                        RpcResponse rpcResponse = Utils.generateTresholdRetryLoginResponse(rpcRequest);
                                        new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                                        return rpcResponse;
                                    }


                                }
                                else if(unAuthenticateUser.getFailedPasswordCount() != config.getBannedRetryCount()-1)
                                {
                                    //Timestamp lastFailedtime = unAuthenticateUser.getFailedTime();
                                    String lastFailedtime = unAuthenticateUser.getFailedTime();
                                    //Timestamp nowTime = new Timestamp(new Date().getTime());
                                    String nowTime = ParsCalendar.getInstance().getShortDateTime();
                                    //Duration duration = Duration.between(lastFailedtime.toLocalDateTime().toLocalDate(),
                                    //    nowTime.toLocalDateTime().toLocalDate());
                                    //if (duration.getSeconds() < config.getBannedTimePeriod().getSeconds())
                                    if (Utils.isInTreshold(lastFailedtime,-config.getBannedTimePeriod(),userName,password) == true)
                                    {
                                        unAuthenticateUser.setFailedPasswordCount(unAuthenticateUser.getFailedPasswordCount()+1);
                                        serviceUserServiceImp.update(unAuthenticateUser);
                                        RpcResponse rpcResponse = Utils.generateTresholdRetryLoginResponse(rpcRequest);
                                        new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                                        return rpcResponse;

                                    }
                                    //else if (duration.getSeconds() >= config.getBannedTimePeriod().getSeconds())
                                    else if (Utils.isInTreshold(lastFailedtime,-config.getBannedTimePeriod(),userName,password) == false)
                                    {
                                        //unAuthenticateUser.setFailedTime(new Timestamp(new Date().getTime()));
                                        unAuthenticateUser.setFailedTime(ParsCalendar.getInstance().getShortDateTime());
                                        unAuthenticateUser.setFailedPasswordCount(1);
                                        serviceUserServiceImp.update(unAuthenticateUser);
                                        RpcResponse rpcResponse = Utils.generateTresholdRetryLoginResponse(rpcRequest);
                                        new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                                        return rpcResponse;
                                    }
                                }
                            }
                        }
                        else if (unAuthenticateUser.getBanned() == TtServiceUserState.BannedUser)
                        {
                            //unAuthenticateUser.setFailedTime(new Timestamp(new Date().getTime()));
                            unAuthenticateUser.setFailedTime(ParsCalendar.getInstance().getShortDateTime());
                            serviceUserServiceImp.update(unAuthenticateUser);
                            RpcResponse rpcResponse = Utils.generateLockUserLoginResponse(rpcRequest);
                            new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                            return rpcResponse;
                        }

                    }
                    else if (unAuthenticateUser.getDeleted() == TtServiceUserState.DeletedUser)
                    {
                        RpcResponse rpcResponse = Utils.generateDeletedUserdLoinResponse(rpcRequest);
                        new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                        return rpcResponse;


                    }
                }
                //else if (user == null)
                else if (unAuthenticateUser == null)
                {
                    RpcResponse rpcResponse = Utils.generateUnvalidUsernameLoginRequest(rpcRequest);
                    new Utils().insertLog(userName,null,TtActionType.Login,TtSensitivity.Necessary,TtImportance.HighImportance,TtFlag.Failure,TtSubType.UnsuccessLogin,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                    return rpcResponse;
                }
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return null;
    }*/
}
