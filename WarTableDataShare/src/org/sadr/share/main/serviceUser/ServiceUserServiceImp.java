package org.sadr.share.main.serviceUser;

import org.hibernate.criterion.Restrictions;
import org.sadr._core._type.TtCompareResult;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.RePa;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.nonRpc.publish._core.State;
import org.sadr.service.main.nonRpc.publish._types.TtState;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.service.main.rpc._types.TtConfig;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc._types.TtRpcResponseResult;
import org.sadr.service.main.rpc.brokerUtils.BrokerUtils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main._types.*;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.criticalLog.CriticalLog;
import org.sadr.share.main.criticalLog.CriticalLogServiceImp;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtFlag;
import org.sadr.share.main.log._types.TtSensitivity;
import org.sadr.share.main.log._types.TtSubType;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.log.models.serviceLog.ServiceLogServiceImp;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.room._types.TtBusyType;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.roomServiceUser._types.TtRoomServiceUserPresence;
import org.sadr.share.main.serviceUser._types.TtServiceUserSuccessValue;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ServiceUserServiceImp extends GenericServiceImpl<ServiceUser, ServiceUserDao> implements ServiceUserService {
    ServiceUserServiceImp serviceUserServiceImp;
    RpcResponse rpcResponse = new RpcResponse();
    CriticalLog criticalLog = new CriticalLog();
    ErrorResponse errorResponse = new ErrorResponse();
    SessionsServiceImp sessionsServiceImp;
    CriticalLogServiceImp criticalLogServiceImp;
    Room_ServiceUserServiceImp room_serviceUserServiceImp;
    RoomServiceImp roomServiceImp;
    ServiceLogServiceImp serviceLogServiceImp;
    //Room_ServiceUser room_serviceUser;
    //Room_MemberServiceImp room_memberServiceImp;


    public boolean isUserHaveLastLogin(ServiceUser serviceUser) {
        try {
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions._SERVICE_USER, serviceUser));
            if (sessions != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return false;
        }
    }


    private boolean insertServiceUserSession(RpcRequest rpcRequest, ServiceUser user, String sessionId) {
        try {
            String userName = (String) rpcRequest.getParams().get("Username");
            Sessions sessions = new Sessions();
            sessions.setServiceUser(user);
            sessions.setStatus(TtSessionsState.Active);
            sessions.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
            sessions.setUpdateDateTime(ParsCalendar.getInstance().getShortDateTime());
            sessions.setSessionId(sessionId);
            sessions.setPrivilageFlag(null);
            sessionsServiceImp.save(sessions);
            LoggerBL.log(TtRpcFunction.InsertNewSession.toString(), userName, null, TtActionType.Login, TtSensitivity.Debug, TtImportance.MediumImportance, TtFlag.Success, TtSubType.AddNewUser, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
            return true;

        } catch (Exception e) {
            LoggerBL.log(rpcRequest.getMethod().toString(), rpcRequest.getParams().get("Username").toString(), rpcRequest.getParams().get("SessionId").toString(), Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
            return false;

        }
    }


    //  @Autowired
    // public void setRoom_memberServiceImp(Room_MemberServiceImp room_memberServiceImp) {
    //     this.room_memberServiceImp = room_memberServiceImp;
    //}

    //@Autowired
    // public void setRoom_serviceUser(Room_ServiceUser room_serviceUser) {
    //    this.room_serviceUser = room_serviceUser;
    // }

    @Autowired
    public void setServiceLogServiceImp(ServiceLogServiceImp serviceLogServiceImp) {
        this.serviceLogServiceImp = serviceLogServiceImp;
    }

    @Autowired
    public void setRoomServiceImp(RoomServiceImp roomServiceImp) {
        this.roomServiceImp = roomServiceImp;
    }

    @Autowired
    public void setRoom_serviceUserServiceImp(Room_ServiceUserServiceImp room_serviceUserServiceImp) {
        this.room_serviceUserServiceImp = room_serviceUserServiceImp;
    }


    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    @Autowired
    public void setCriticalLogServiceImp(CriticalLogServiceImp criticalLogServiceImp) {
        this.criticalLogServiceImp = criticalLogServiceImp;
    }


    @Autowired
    public void setServiceUserServiceImp(ServiceUserServiceImp serviceUserServiceImp) {
        this.serviceUserServiceImp = serviceUserServiceImp;
    }

    public static RpcResponse generateLockedUserLoginResponse(RpcRequest rpcRequest) {
        ErrorResponse errorResponse = new ErrorResponse();
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult(null);
        errorResponse.setCode(TtErrors.UserIsLocked.ordinal());
        errorResponse.setDescription(TtErrors.UserIsLocked.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateSuccessLoginResponse(RpcRequest rpcRequest, ServiceUser user, Map config, String queueName, String brokerIP, String brokerUsername, String brokerPassword, int brokerPort) {
        try {
            RpcResponse rpcResponse = new RpcResponse();
            ErrorResponse errorResponse = new ErrorResponse();
            rpcResponse.setId(rpcRequest.getId());
            Map result = new HashMap();
            result.put(TtRpcResponseResult.SessionId.getName(), queueName);
            //result.put(TtRpcResponseResult.PrivilegeFlags.getName(),user.getPrivilageClass().getCode());
            result.put(TtRpcResponseResult.LoginCounter.getName(), user.getLoginCount());
            result.put(TtRpcResponseResult.CurrentDateTime.getName(), BrokerUtils.nowTime().toString());
            result.put(TtRpcResponseResult.LastVersion.getName(), config.get(TtConfig.LastSoftwareVersion).toString());
            result.put(TtRpcResponseResult.LastVersionSetupSize.getName(), config.get(TtConfig.LastSoftwareSize).toString());
            result.put("BrokerIP", brokerIP);
            result.put("BrokerUsername", brokerUsername);
            result.put("BrokerPassword", brokerPassword);
            result.put("BrokerPort", brokerPort);
            if (user.getLastRoom().getCurrentMeeting() != null) {
                result.put("ConferencesNumber", user.getLastRoom().getId());
                result.put("RoomName", user.getLastRoom().getName());
            }
            if (user.getLastRoom() == null) {
                result.put(TtRpcResponseResult.LastRoomID.getName(), -1);
            } else if (user.getLastRoom() != null) {
                result.put(TtRpcResponseResult.LastRoomID.getName(), user.getLastRoom().getId());
            }
            rpcResponse.setResult(result);
            errorResponse.setCode(TtErrors.NoError.ordinal());
            errorResponse.setDescription(TtErrors.NoError.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;

        } catch (Exception e) {
            Utils.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return null;
        }
    }

    public static RpcResponse generateDeletedUserdLoinResponse(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult(null);
        errorResponse.setCode(TtErrors.UserIsDeleted.ordinal());
        errorResponse.setDescription(TtErrors.UserIsDeleted.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateTresholdRetryLoginResponse(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult(null);
        errorResponse.setCode(TtErrors.UserPasswordWrong.ordinal());
        errorResponse.setDescription(TtErrors.UserPasswordWrong.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public static RpcResponse generateLockUserLoginResponse(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult(null);
        errorResponse.setCode(TtErrors.UserIsLocked.ordinal());
        errorResponse.setDescription(TtErrors.UserIsLocked.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;

    }

    public static RpcResponse generateUnvalidUsernameLoginRequest(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        rpcResponse.setResult(null);
        errorResponse.setDescription(TtErrors.UserDoesntexist.getErrorValueService());
        errorResponse.setCode(TtErrors.UserDoesntexist.ordinal());
        rpcResponse.setError(errorResponse);
        return rpcResponse;

    }

    public static RpcResponse generateListAllUserResponse(RpcRequest rpcRequest, List<ServiceUser> serviceUsers, List<ServiceUser> sessionUsers, TtErrors ttErrors) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(rpcRequest.getId());
        Map result = new HashMap();
        if (!serviceUsers.isEmpty() || !sessionUsers.isEmpty()) {

            List<String> users = new ArrayList<String>();
            List<String> privilageClasses = new ArrayList<String>();
            List<String> banned = new ArrayList<String>();
            List<String> lastUserId = new ArrayList<String>();
            List<String> names = new ArrayList<String>();
            List<String> families = new ArrayList<String>();
            List<String> grades = new ArrayList<String>();
            List<String> orgPositions = new ArrayList<String>();
            List<String> onlines = new ArrayList<String>();
            List<Map> results;
            for (ServiceUser serviceUser : serviceUsers) {
                users.add(serviceUser.getUserName());
                //privilageClasses.add(serviceUser.getPrivilageClass().getValue());
                banned.add(serviceUser.getBanned().getName());
                lastUserId.add(null);
                names.add(serviceUser.getName());
                families.add(serviceUser.getFamily());
                grades.add(serviceUser.getGrade().getValue());
                orgPositions.add(serviceUser.getOrgPosition().getValue());
                if (sessionUsers.contains(serviceUser)) {
                    onlines.add("1");
                } else {
                    onlines.add("0");
                }
            }
            result.put("Users", users);
            result.put("PrivilegeClasses", privilageClasses);
            result.put("Banned", banned);
            result.put("Online", onlines);
            result.put("LastUserId", lastUserId);
            result.put("Names", names);
            result.put("Families", families);
            result.put("Grades", grades);
            result.put("OrgPositions", orgPositions);
            rpcResponse.setResult(result);
            errorResponse.setCode(TtErrors.NoError.ordinal());
            errorResponse.setDescription(TtErrors.NoError.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        } else {
            rpcResponse.setResult(result);
            errorResponse.setCode(ttErrors.ordinal());
            errorResponse.setDescription(ttErrors.getErrorValueService());
            rpcResponse.setError(errorResponse);
            return rpcResponse;
        }
    }

    public static RpcResponse generateLogoutResponse(TtErrors ttErrors, int resultValue) {
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        rpcResponse.setId(TtGlobalId.LeaveRoom.ordinal());
        Map result = new HashMap();
        result.put(TtRpcResponseResult.Success.getName(), resultValue);
        rpcResponse.setResult(result);
        errorResponse.setCode(ttErrors.ordinal());
        errorResponse.setDescription(ttErrors.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;
    }

    public RpcResponse loginServiceUser(RpcRequest rpcRequest) {
        try {


            Map<TtConfig, String> configMap = new HashMap<TtConfig, String>();
            BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp) IOCContainer.GetBeans(BaseConfigServiceImp.class);
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig : baseConfigs) {
                configMap.put(baseConfig.getConfigId(), baseConfig.getConfigValue());
            }
            String brokerIP = configMap.get(TtConfig.ServerIP).toString();
            String brokerUsername = configMap.get(TtConfig.ServerLoginUsername).toString();
            String brokerPassword = configMap.get(TtConfig.ServerLoginPassword).toString();
            int brokerPort = Integer.parseInt(configMap.get(TtConfig.ServerPort).toString());
            int duration = Integer.parseInt(configMap.get(TtConfig.BannedTimePeriod).toString());
            String userName = (String) rpcRequest.getParams().get("Username");
            String password = (String) rpcRequest.getParams().get("Password");
            ServiceUser user = serviceUserServiceImp.findBy(Restrictions.and(
                Restrictions.eq(ServiceUser.USERNAME, userName),
                Restrictions.eq(ServiceUser.PASSWORD, password)), RePa.p__(ServiceUser._LAST_ROOM, Room._CURRENT_MEETING));
            if (user != null) {
                // check double login
               /* if (isUserHaveLastLogin(user))
                {
                    List<Integer> broadcastFlags = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS,TtState.SESS_UPDATE_SESSIONLIST);
                    String broadcastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update,TtErrors.NoError,broadcastFlags);
                    BrokerUtils.unicastMessage(broadcastMessage,(String) rpcRequest.getParams().get("SessionId"));
                    logout(rpcRequest);
                }*/

                // end check double login

                if (user.getDeleted() == TtServiceUserState.UnDeletedUser) {
                    if (user.getBanned() == TtServiceUserState.BannedUser) {
                        if (/*serviceConfigServiceImp.count() != 0 */ true) {
                            String lastFailedtime = user.getFailedDateTime();
                            String nowTime = ParsCalendar.getInstance().getShortDateTime();
                            if (Utils.isInTreshold(lastFailedtime, duration, userName, password) == true) {
                                rpcResponse = generateLockedUserLoginResponse(rpcRequest);
                                LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.MediumImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                return rpcResponse;


                            } else if (Utils.isInTreshold(lastFailedtime, duration, userName, password) == false) {
                                user.setBanned(TtServiceUserState.UnBannedUser);
                                user.setFailedDateTime(null);
                                user.setFailedPasswordCount(0);
                                Room room = user.getLastRoom();
                                BrokerUtils brokerUtils = new BrokerUtils();
                                String queueName = brokerUtils.defineQueue();
                                if (brokerUtils.declareQueue(queueName)) {
                                    if (insertServiceUserSession(rpcRequest, user, queueName)) {
                                        rpcResponse = generateSuccessLoginResponse(rpcRequest, user, configMap, queueName, brokerIP, brokerUsername, brokerPassword, brokerPort);
                                        user.setLoginCount(user.getLoginCount() + 1);
                                        serviceUserServiceImp.update(user);
                                        LoggerBL.log(TtRpcFunction.Login.toString(), userName, queueName, TtActionType.Login, TtSensitivity.Alarm, TtImportance.MediumImportance, TtFlag.Success, TtSubType.SuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                        return rpcResponse;
                                    }
                                }
                            }
                        }

                    } else if (user.getBanned() == TtServiceUserState.UnBannedUser) {
                        //user.setLoginCount(user.getLoginCount() + 1);
                        BrokerUtils brokerUtils = new BrokerUtils();
                        //int roomId = user.getLastRoomId();
                        Room room = user.getLastRoom();
                        String queueName = brokerUtils.defineQueue();
                        if (brokerUtils.declareQueue(queueName)) {
                            if (insertServiceUserSession(rpcRequest, user, queueName)) {
                                rpcResponse = generateSuccessLoginResponse(rpcRequest, user, configMap, queueName, brokerIP, brokerUsername, brokerPassword, brokerPort);
                                user.setLoginCount(user.getLoginCount() + 1);
                                serviceUserServiceImp.update(user);
                                LoggerBL.log(TtRpcFunction.Login.toString(), userName, queueName, TtActionType.Login, TtSensitivity.Alarm, TtImportance.MediumImportance, TtFlag.Success, TtSubType.SuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                return rpcResponse;
                            }
                        }

                    }

                } else if (user.getDeleted() == TtServiceUserState.DeletedUser) {
                    RpcResponse rpcResponse = generateDeletedUserdLoinResponse(rpcRequest);
                    LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.HighImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                    return rpcResponse;

                }
            } else if (user == null) {
                ServiceUser unAuthenticateUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.USERNAME, userName));
                if (unAuthenticateUser != null) {
                    if (unAuthenticateUser.getDeleted() == TtServiceUserState.UnDeletedUser) {
                        if (unAuthenticateUser.getBanned() == TtServiceUserState.UnBannedUser) {
                            if (unAuthenticateUser.getFailedPasswordCount() == 0) {
                                unAuthenticateUser.setFailedPasswordCount(1);
                                unAuthenticateUser.setFailedDateTime(ParsCalendar.getInstance().getShortDateTime());
                                serviceUserServiceImp.update(unAuthenticateUser);
                                RpcResponse rpcResponse = generateTresholdRetryLoginResponse(rpcRequest);
                                LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.MediumImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                return rpcResponse;


                            } else if (unAuthenticateUser.getFailedPasswordCount() != 0) {
                                if (unAuthenticateUser.getFailedPasswordCount() == Integer.parseInt(configMap.get(TtConfig.BannedRetryCount).toString()) - 1) {
                                    String lastFailedtime = unAuthenticateUser.getFailedDateTime();
                                    String nowTime = ParsCalendar.getInstance().getShortDateTime();
                                    if (Utils.isInTreshold(lastFailedtime, Integer.parseInt(configMap.get(TtConfig.BannedTimePeriod).toString()), userName, password) == true) {
                                        unAuthenticateUser.setBanned(TtServiceUserState.BannedUser);
                                        unAuthenticateUser.setFailedDateTime(ParsCalendar.getInstance().getShortDateTime());
                                        serviceUserServiceImp.update(unAuthenticateUser);
                                        RpcResponse rpcResponse = generateLockUserLoginResponse(rpcRequest);
                                        LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.HighImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                        return rpcResponse;
                                    } else if (Utils.isInTreshold(lastFailedtime, Integer.parseInt(configMap.get(TtConfig.BannedTimePeriod).toString()), userName, password) == false) {
                                        unAuthenticateUser.setFailedPasswordCount(1);
                                        unAuthenticateUser.setFailedDateTime(ParsCalendar.getInstance().getShortDateTime());
                                        serviceUserServiceImp.update(unAuthenticateUser);
                                        RpcResponse rpcResponse = generateTresholdRetryLoginResponse(rpcRequest);
                                        LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.HighImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                        return rpcResponse;
                                    }


                                } else if (unAuthenticateUser.getFailedPasswordCount() != Integer.parseInt(configMap.get(TtConfig.BannedRetryCount).toString()) - 1) {
                                    String lastFailedtime = unAuthenticateUser.getFailedDateTime();
                                    String nowTime = ParsCalendar.getInstance().getShortDateTime();
                                    if (Utils.isInTreshold(lastFailedtime, -(Integer.parseInt(configMap.get(TtConfig.BannedTimePeriod).toString())), userName, password) == true) {
                                        unAuthenticateUser.setFailedPasswordCount(unAuthenticateUser.getFailedPasswordCount() + 1);
                                        serviceUserServiceImp.update(unAuthenticateUser);
                                        RpcResponse rpcResponse = generateTresholdRetryLoginResponse(rpcRequest);
                                        LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.MediumImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                        return rpcResponse;

                                    } else if (Utils.isInTreshold(lastFailedtime, -(Integer.parseInt(configMap.get(TtConfig.BannedTimePeriod).toString())), userName, password) == false) {
                                        //unAuthenticateUser.setFailedTime(new Timestamp(new Date().getTime()));
                                        unAuthenticateUser.setFailedDateTime(ParsCalendar.getInstance().getShortDateTime());
                                        unAuthenticateUser.setFailedPasswordCount(1);
                                        serviceUserServiceImp.update(unAuthenticateUser);
                                        RpcResponse rpcResponse = generateTresholdRetryLoginResponse(rpcRequest);
                                        LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.MediumImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                        return rpcResponse;
                                    }
                                }
                            }
                        } else if (unAuthenticateUser.getBanned() == TtServiceUserState.BannedUser) {
                            unAuthenticateUser.setFailedDateTime(ParsCalendar.getInstance().getShortDateTime());
                            serviceUserServiceImp.update(unAuthenticateUser);
                            RpcResponse rpcResponse = generateLockUserLoginResponse(rpcRequest);
                            LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.HighImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                            return rpcResponse;
                        }

                    } else if (unAuthenticateUser.getDeleted() == TtServiceUserState.DeletedUser) {
                        RpcResponse rpcResponse = generateDeletedUserdLoinResponse(rpcRequest);
                        LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.HighImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                        return rpcResponse;


                    }
                } else if (unAuthenticateUser == null) {
                    RpcResponse rpcResponse = generateUnvalidUsernameLoginRequest(rpcRequest);
                    LoggerBL.log(TtRpcFunction.Login.toString(), userName, null, TtActionType.Login, TtSensitivity.Alarm, TtImportance.HighImportance, TtFlag.Failure, TtSubType.UnsuccessLogin, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                    return rpcResponse;
                }
            }
        } catch (Exception e) {
           /* e.printStackTrace();
           LoggerBL.log(TtRpcFunction.Login.toString(),rpcRequest.getParams().get("Username").toString(),rpcRequest.getParams().get("SessionId").toString(),Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
            return null;*/
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());

        }
        return null;
    }

    public RpcResponse changePasswordUser(RpcRequest rpcRequest) {
        try {
            String password = (String) rpcRequest.getParams().get("Password");
            String oldPassword = (String) rpcRequest.getParams().get("OldPassword");
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                sessionsServiceImp.updateSession(rpcRequest);
                ServiceUser serviceUser = serviceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(ServiceUser.USERNAME, sessions.getServiceUser().getUserName()),
                    Restrictions.eq(ServiceUser.PASSWORD, oldPassword)));
                if (serviceUser != null) {
                    serviceUser.setPassword(password);
                    serviceUserServiceImp.update(serviceUser);
                    rpcResponse = Utils.successPasswordChange(rpcRequest);
                    LoggerBL.log(TtRpcFunction.ChangePassword.name(), sessions.getServiceUser().getUserName(), sessionId, TtActionType.Edit, TtSensitivity.Alarm, TtImportance.MediumImportance, TtFlag.Success, TtSubType.ChangePassword, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                    return rpcResponse;

                } else if (serviceUser == null) {
                    rpcResponse = Utils.failedPasswordChangeDueUsernameError(rpcRequest);
                    LoggerBL.log(TtRpcFunction.ChangePassword.name(), sessions.getServiceUser().getUserName(), sessionId, TtActionType.Edit, TtSensitivity.Alarm, TtImportance.HighImportance, TtFlag.Failure, TtSubType.ChangePassword, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                    return rpcResponse;

                }

            } else if (sessions == null) {
                rpcResponse = Utils.failedPasswordChangeDueSessionError(rpcRequest);
                LoggerBL.log(TtRpcFunction.ChangePassword.name(), null, sessionId, TtActionType.Edit, TtSensitivity.Alarm, TtImportance.HighImportance, TtFlag.Failure, TtSubType.ChangePassword, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                return rpcResponse;
            }
        } catch (Exception e) {
           /*LoggerBL.log(TtRpcFunction.ChangePassword.name(),null,rpcRequest.getParams().get("SessionId").toString(),Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
            return null;*/
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());

        }
        return null;
    }

    /*public RpcResponse setPrivilegeClass(RpcRequest rpcRequest)
    {
        try
        {
            String username = rpcRequest.getParams().get("Username").toString();
            String sessionId = rpcRequest.getParams().get("SessionId").toString();
            String privilageFlag = rpcRequest.getParams().get("ClassName").toString();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
            if (sessions != null)
            {
                if(privilageClassServiceImp.userIsRoot(sessions.getPrivilageFlag()) == true)
                {
                    ServiceUser user = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.USERNAME,username)*//*,ServiceUser._PRIVILAGE_CLASS*//*);
                    if (user != null)
                    {
                        PrivilageClass privilageClass = privilageClassServiceImp.findBy(Restrictions.eq(PrivilageClass.PRIVILAGE_FLAG,privilageFlag));
                        if (privilageClass != null)
                        {
                            //user.setPrivilageClass(privilageClass);
                            serviceUserServiceImp.update(user);
                            rpcResponse = Utils.failedPrivilageClassChange(rpcRequest,TtErrors.NoError,1);
                           LoggerBL.log(rpcRequest.getMethod().toString(),username,sessionId,TtActionType.Edit,TtSensitivity.Alarm,TtImportance.MediumImportance,TtFlag.Success,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                            return rpcResponse;

                        }
                        else if (privilageClass == null)
                        {
                            rpcResponse = Utils.failedPrivilageClassChange(rpcRequest,TtErrors.PrivilageClassNotFound,0);
                           LoggerBL.log(rpcRequest.getMethod().toString(),username,sessionId,TtActionType.Edit,TtSensitivity.Alarm,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                            return rpcResponse;

                        }

                    }
                    else if (user == null)
                    {
                        rpcResponse = Utils.failedPrivilageClassChange(rpcRequest,TtErrors.UserDoesntexist,0);
                       LoggerBL.log(rpcRequest.getMethod().toString(),username,sessionId,TtActionType.Edit,TtSensitivity.Alarm,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                        return rpcResponse;

                    }

                }
                else if (privilageClassServiceImp.userIsRoot(sessions.getPrivilageFlag()) == false)
                {
                    rpcResponse = Utils.failedPrivilageClassChange(rpcRequest,TtErrors.UserIsNotRoot,0);
                   LoggerBL.log(rpcRequest.getMethod().toString(),username,sessionId,TtActionType.Edit,TtSensitivity.Alarm,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                    return rpcResponse;

                }

            }
            else if (sessions == null)
            {
                rpcResponse = Utils.failedPrivilageClassChange(rpcRequest,TtErrors.SessionIsNull,0);
               LoggerBL.log(rpcRequest.getMethod().toString(),username,sessionId,TtActionType.Edit,TtSensitivity.Alarm,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                return rpcResponse;
            }
           return rpcResponse;
        }
        catch (Exception e)
        {
            rpcResponse = Utils.failedPrivilageClassChange(rpcRequest,TtErrors.OperationalErrorOccured,0);
           LoggerBL.log(rpcRequest.getMethod().toString(),rpcRequest.getParams().get("Username").toString(),rpcRequest.getParams().get("SessionId").toString(),Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
            return rpcResponse;
        }
    }*/

    /*public RpcResponse banUser(RpcRequest rpcRequest)
    {
        String id = rpcRequest.getParams().get("Id").toString();
        String sessionId = rpcRequest.getParams().get("SessionId").toString();
        String unban = rpcRequest.getParams().get("Unban").toString();
        try
        {

            TtServiceUserState serviceUserState ;
            TtSessionsState sessionsState;
            if (unban == "1")
            {
                serviceUserState = TtServiceUserState.BannedUser;
                sessionsState = TtSessionsState.Active;
            }
            else
            {
                serviceUserState = TtServiceUserState.UnBannedUser;
                sessionsState = TtSessionsState.Deactive;
            }
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),Sessions._SERVICE_USER);
            if (sessions != null && sessions.getServiceUser() != null)
            {
                if(privilageClassServiceImp.userIsRoot(sessions.getPrivilageFlag())== true)
                {
                    ServiceUser serviceUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.ID,Integer.parseInt(id)));
                    if (serviceUser != null)
                    {
                        serviceUser.setBanned(serviceUserState);
                        serviceUserServiceImp.update(serviceUser);
                        Sessions userSession = sessionsServiceImp.findBy(Restrictions.eq(Sessions._SERVICE_USER,serviceUser));
                        if (userSession != null)
                        {
                            userSession.setStatus(sessionsState);
                            sessionsServiceImp.update(userSession);
                        }
                        RpcResponse rpcResponse = Utils.generateDoBanndingUserResponse(rpcRequest,TtErrors.NoError,1);
                        LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.UserManagement,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Success,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                        return rpcResponse;
                    }
                    else if (serviceUser == null)
                    {
                        RpcResponse rpcResponse = Utils.generateDoBanndingUserResponse(rpcRequest,TtErrors.UserDoesntexist,0);
                        LoggerBL.log(rpcRequest.getMethod().toString(),serviceUser.getUserName(),sessionId,TtActionType.UserManagement,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                        return rpcResponse;
                    }

                }
                else if (privilageClassServiceImp.userIsRoot(sessions.getPrivilageFlag())== false)
                {
                    RpcResponse rpcResponse = Utils.generateDoBanndingUserResponse(rpcRequest,TtErrors.UserIsNotRoot,0);
                    LoggerBL.log(rpcRequest.getMethod().toString(),sessions.getServiceUser().getUserName(),sessionId,TtActionType.UserManagement,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                    return rpcResponse;
                }



            }
            else if (sessions == null || sessions.getServiceUser() == null)
            {
                RpcResponse rpcResponse = Utils.generateDoBanndingUserResponse(rpcRequest,TtErrors.SessionIsNull,0);
                LoggerBL.log(rpcRequest.getMethod().toString(),sessions.getServiceUser().getUserName(),sessionId,TtActionType.UserManagement,TtSensitivity.Notification,TtImportance.MediumImportance,TtFlag.Failure,TtSubType.ChangeUserAccess,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                return rpcResponse;
            }


        }
        catch (Exception e)
        {
            RpcResponse rpcResponse = Utils.generateDoBanndingUserResponse(rpcRequest,TtErrors.OperationalErrorOccured,0);
            LoggerBL.log(rpcRequest.getMethod().toString(), serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.ID,id)).getUserName(),sessionId,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
            return rpcResponse;
        }
        return rpcResponse;
    }*/
    public RpcResponse listAllUsers(RpcRequest rpcRequest) {
        try {
            String sessionId = rpcRequest.getParams().get("SessionId").toString();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            if (sessions != null) {
                ServiceUser serviceUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.USERNAME, sessions.getServiceUser().getUserName()), ServiceUser._LAST_ROOM);
                List<ServiceUser> serviceUsers = serviceUserServiceImp.findAllBy(Restrictions.eq(ServiceUser._LAST_ROOM, serviceUser.getLastRoom()), ServiceUser._GRADE, ServiceUser._ORG_POSITION/*,ServiceUser._PRIVILAGE_CLASS*/);
                List<Sessions> sessionsList = sessionsServiceImp.findAll(Sessions._SERVICE_USER);
                List<ServiceUser> sessionUsers = new ArrayList<ServiceUser>();
                for (Sessions session : sessionsList) {
                    sessionUsers.add(session.getServiceUser());

                }
                RpcResponse rpcResponse = generateListAllUserResponse(rpcRequest, serviceUsers, sessionUsers, TtErrors.NoError);
                LoggerBL.log(TtRpcFunction.ListRoomUsers.name(), sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER).getServiceUser().getUserName(), sessionId, TtActionType.Report, TtSensitivity.Notification, TtImportance.MediumImportance, TtFlag.Success, TtSubType.Report, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));


            } else if (sessions == null) {
                RpcResponse rpcResponse = generateListAllUserResponse(rpcRequest, null, null, TtErrors.SessionIsNull);
                LoggerBL.log(TtRpcFunction.ListRoomUsers.name(), sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER).getServiceUser().getUserName(), sessionId, TtActionType.Report, TtSensitivity.Notification, TtImportance.HighImportance, TtFlag.Failure, TtSubType.Report, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));

            }

        } catch (Exception e) {
            String sessionId = rpcRequest.getParams().get("SessionId").toString();
            RpcResponse rpcResponse = Utils.generateListAllUserResponse(rpcRequest, null, null, TtErrors.OperationalErrorOccured);
            LoggerBL.log(TtRpcFunction.ListRoomUsers.name(), sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER).getServiceUser().getUserName(), sessionId, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
        }
        return rpcResponse;
    }

    public RpcResponse logout(RpcRequest rpcRequest) {
        try {
            String sessionId = rpcRequest.getParams().get("SessionId").toString();
            rpcResponse = leaveRoom(rpcRequest);

            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId));
            if (sessions != null) {
                sessionsServiceImp.delete(sessions);
            }

        } catch (Exception e) {
            rpcResponse = generateLogoutResponse(TtErrors.OperationalErrorOccured, TtServiceUserSuccessValue.UnSuccess.ordinal());
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return rpcResponse;
        }
        return rpcResponse;
    }

    public RpcResponse leaveRoom(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();

        Room_ServiceUser roomServiceUser = new Room_ServiceUser();
        Room room = new Room();
        try {
            String sessionId = rpcRequest.getParams().get("SessionId").toString();
            Integer roomId;


            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            ServiceUser serviceUser = new ServiceUser();

            if (sessions != null) {
                try {
                    Integer userId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("UserId").toString())).intValue();
                    roomId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString())).intValue();
                    serviceUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.ID, userId), ServiceUser._LAST_ROOM);
                    room = roomServiceImp.findBy(Restrictions.eq(Room.ID, roomId));
                } catch (Exception e) {
                    serviceUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.USERNAME, sessions.getServiceUser().getUserName()), ServiceUser._LAST_ROOM);
                    roomId = new Double(Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString())).intValue();
                    if (roomId == 0) {
                        rpcResponse = generateLogoutResponse(TtErrors.NoError, TtServiceUserSuccessValue.Success.ordinal());
                        return rpcResponse;
                    }
                    room = room_serviceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._SERVICE_USER, serviceUser), Restrictions.eq(Room_ServiceUser.PRESENCE, TtRoomServiceUserPresence.Online)), Room_ServiceUser._ROOM).getRoom();
                    //room = serviceUser.getLastRoom();

                }
                // بررسی ایجاد VOICE CALL
                if (room.getBusyType() == TtBusyType.Busy) {
                    if (room_serviceUserServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, room), Restrictions.eq(Room_ServiceUser.PRESENCE, TtRoomServiceUserPresence.Online))).size() == 1) {
                        room.setBusyType(TtBusyType.UnBusy);
                        roomServiceImp.update(room);
                        Utils.deleteVoipConferenceBridge(room);

                    }
                }
                roomServiceUser = room_serviceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._SERVICE_USER, serviceUser), Restrictions.eq(Room_ServiceUser._ROOM, room)));
                //Room userRoom = room_serviceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._SERVICE_USER, serviceUser), Restrictions.eq(Room_ServiceUser._ROOM, room)), Room_ServiceUser._ROOM).getRoom();
                if (serviceUser != null && room != null) {
                    List<Room_ServiceUser> roomServiceUsers = room_serviceUserServiceImp.findAllBy(Restrictions.eq(Room_ServiceUser._ROOM, room));
                    {
                        if (roomServiceUsers.size() == 1) {
                            if (BrokerUtils.deleteQueue(sessionId) && BrokerUtils.removeQueueFromExchange(sessionId, serviceUser.getLastRoom().getName())) {
                                roomServiceUser = room_serviceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._SERVICE_USER, serviceUser), Restrictions.eq(Room_ServiceUser._ROOM, room)));
                                if (roomServiceUser != null) {
                                    //room_serviceUserServiceImp.delete(roomServiceUser);
                                    roomServiceUser.setPresence(TtRoomServiceUserPresence.Offline);
                                    room_serviceUserServiceImp.update(roomServiceUser);
                                    List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_ROOMUSERS);
                                    rpcResponse = generateLogoutResponse(TtErrors.NoError, TtServiceUserSuccessValue.Success.ordinal());
                                    return rpcResponse;

                                    //BrokerUtils.broadcastMessage(broadcastMessage,room.getName());

                                } else if (roomServiceUser == null) {
                                    rpcResponse = generateLogoutResponse(TtErrors.RoomServiceUser_RoomServiceUserDoesntExist, TtServiceUserSuccessValue.UnSuccess.ordinal());
                                    return rpcResponse;
                                }
                            } else {
                                rpcResponse = generateLogoutResponse(TtErrors.OperationalErrorOccured, TtServiceUserSuccessValue.UnSuccess.ordinal());
                                return rpcResponse;
                            }

                        } else {
                            roomServiceUser.setPresence(TtRoomServiceUserPresence.Offline);
                            room_serviceUserServiceImp.update(roomServiceUser);
                            switch (roomServiceUser.getPermanentUserRoleFlag()) {
                                case Admin:
                                case AdminViewSource:
                                    //if (room_serviceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser.PERMANENT_USER_ROLE_FLAG, TtUserRoleFlags.TempAdmin), Restrictions.eq(Room_ServiceUser.PERMANENT_USER_ROLE_FLAG, TtUserRoleFlags.TempAdminViewSource))) != null) {
                                    roomServiceUsers = room_serviceUserServiceImp.findAllBy(Restrictions.eq(Room_ServiceUser._ROOM, roomServiceUser.getRoom()));
                                    String compareTime = ParsCalendar.getInstance().getShortDateTime();
                                    Room_ServiceUser compareRoomUser = new Room_ServiceUser();
                                    for (Room_ServiceUser room_serviceUser : roomServiceUsers) {
                                        TtCompareResult result = ParsCalendar.getInstance().compareDateTime(compareTime, room_serviceUser.getJoinDateTime());
                                        if (result == TtCompareResult.FirstIsBigger) {
                                            compareTime = room_serviceUser.getJoinDateTime();
                                            compareRoomUser = room_serviceUser;
                                        }
                                    }
                                    compareRoomUser.setTemporaryUserRoleFlag(TtUserRoleFlags.TempAdmin);
                                    room_serviceUserServiceImp.update(compareRoomUser);
                                    rpcResponse = generateLogoutResponse(TtErrors.NoError, TtServiceUserSuccessValue.Success.ordinal());
                                    List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS_UPDATE_ROOMUSERS);
                                    //LoggerBL.log(TtRpcFunction.Logout.name(), serviceUser.getUserName(), sessionId, TtActionType.Report, TtSensitivity.Notification, TtImportance.LowImportance, TtFlag.Success, TtSubType.Report, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                    //sessionsServiceImp.delete(sessions);
                                    //}
                                    break;
                                case TempAdmin:
                                case TempAdminViewSource:
                                    if (room_serviceUserServiceImp.findBy(Restrictions.eq(Room_ServiceUser.PERMANENT_USER_ROLE_FLAG, TtUserRoleFlags.Admin)) == null) {
                                        roomServiceUsers = room_serviceUserServiceImp.findAllBy(Restrictions.eq(Room_ServiceUser._ROOM, roomServiceUser.getRoom()));
                                        compareTime = ParsCalendar.getInstance().getShortDateTime();
                                        compareRoomUser = new Room_ServiceUser();
                                        for (Room_ServiceUser room_serviceUser : roomServiceUsers) {
                                            TtCompareResult result = ParsCalendar.getInstance().compareTime(compareTime, room_serviceUser.getJoinDateTime());
                                            if (result == TtCompareResult.FirstIsBigger) {
                                                compareTime = room_serviceUser.getJoinDateTime();
                                                compareRoomUser = room_serviceUser;
                                            }
                                        }
                                        compareRoomUser.setTemporaryUserRoleFlag(TtUserRoleFlags.TempAdmin);
                                        room_serviceUserServiceImp.update(compareRoomUser);
                                        rpcResponse = generateLogoutResponse(TtErrors.NoError, TtServiceUserSuccessValue.Success.ordinal());
                                        LoggerBL.log(TtRpcFunction.Logout.name(), serviceUser.getUserName(), sessionId, TtActionType.Report, TtSensitivity.Notification, TtImportance.LowImportance, TtFlag.Success, TtSubType.Report, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                        //sessionsServiceImp.delete(sessions);
                                        break;

                                    }
                                case ViewSource:
                                case User: {
                                    rpcResponse = generateLogoutResponse(TtErrors.NoError, TtServiceUserSuccessValue.Success.ordinal());
                                    LoggerBL.log(TtRpcFunction.Logout.name(), serviceUser.getUserName(), sessionId, TtActionType.Report, TtSensitivity.Notification, TtImportance.LowImportance, TtFlag.Success, TtSubType.Report, Utils.ObjToJson(rpcRequest), Utils.ObjToJson(rpcResponse));
                                    //sessionsServiceImp.delete(sessions);
                                    break;
                                }
                            }
                        }
                    }
                } else if (serviceUser == null || room == null) {
                    rpcResponse = generateLogoutResponse(TtErrors.NoError, TtServiceUserSuccessValue.Success.ordinal());
                    return rpcResponse;
                }
                //sessionsServiceImp.delete(sessions);
            } else if (sessions == null) {
                //ServiceUser serviceUser = serviceUserServiceImp.findBy(Restrictions.eq(ServiceUser.USERNAME, sessions.getServiceUser().getUserName()), ServiceUser._LAST_ROOM);
                rpcResponse = generateLogoutResponse(TtErrors.SessionIsNull, TtServiceUserSuccessValue.UnSuccess.ordinal());
                //LoggerBL.log(TtRpcFunction.Logout.name(),null,sessionId,TtActionType.Report,TtSensitivity.Notification,TtImportance.LowImportance,TtFlag.Failure,TtSubType.Report,Utils.ObjToJson(rpcRequest),Utils.ObjToJson(rpcResponse));
                //return rpcResponse;
            }
            //String broadcastMessage = new State().setStateBytes(true,TtState.SESS_UPDATE_ROOMUSERS);
            //System.out.println(roomServiceUser.getRoom().getName());
            //BrokerUtils.broadcastMessage(broadcastMessage,room.getName());
        } catch (Exception e) {
            rpcResponse = generateLogoutResponse(TtErrors.OperationalErrorOccured, TtServiceUserSuccessValue.UnSuccess.ordinal());
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(), e.getMessage());
            return rpcResponse;
        }
        return rpcResponse;
    }
}
