package org.sadr.service.main.rpc._types;

import org.hibernate.criterion.Restrictions;
import org.sadr.service.config.IOCContainer;
import org.sadr.share.main.baseErrors.BaseErrors;
import org.sadr.share.main.baseErrors.BaseErrorsServiceImp;

public enum TtErrors {
    // enum for users error
    NoError("valid request response"),
    UserDoesntexist("specific user doesnt exist"),
    UserIsUnvalidUsernamePassword("specific username and password doesnt exist"),
    UserIsDeleted("the specific user is deleted"),
    UserIsBanned("the specific user is banned"),
    UserPasswordWrong("the specific password is wrong"),
    UserIsLocked("specific user is locked"),
    InvalidJsonResquest("json request is invalid"),
    InvalidMethod("rpc method is not declared in server"),
    ChangePasswordFailedDueUsernameError("specific user  is not declare"),
    ChangePasswordFailedDueSessionErroe("specific session is not declare"),
    PrivilageClassNotFound("specific privilage flag not found"),
    UserIsNotRoot("specific user is doesnt have privilage to do this service"),
    SessionIsNull("specific session is null"),
    OperationalErrorOccured("an error occured during this service"),
    ListRoomIsNull("list room request return null value"),
    SessionUpdateFailed("sessions update time fail"),
    NewSessionInsertError("create new session occured error"),
    FailureJoinRoomDueUserIsDeactive("fail join room due user login failure"),
    FailureJoinRoomDueUserIsNotMemberOfRoom("fail join room due user is not member of room"),
    RoomDoesntExist("specific room doesnt exist"),
    MapDoesntExist("specific map doesnt exist"),
    UserDoesntHaveEnoughPermission("specific user doesnt have enough permission for doing this task"),
    MapDoesntHaveAnyLayer("specific map doesnt have any layer"),
    MapDoesntForRoom("specific map doesnt for this room"),
    RoomServiceUser_RoomDoesntHaveAnyMeeting("specific room doesnt have any meeting"),
    Map_RoomHasNoAnyMap("specific room doesnt have any map binding"),
    RoomOrUserDoesntExist("specifc room or user doesnt exist"),
    Meeting_DuplicateMeeting("the name and goal and description of the meeting that you send is duplicate"),
    Meeting_MeetingIsNull("the map session id is not valid"),
    RoomServiceUser_RoomServiceUserDoesntExist("specifc user doesnt for this room"),
    NoObjectFound("object table is empty"),
    NoWeatherAssignForThisMeeting("this meeting doesnt have any weather assignment"),
    WeatherNotExist("this type of the weather not exist"),
    NoEntityAssignForThisMeeting("no entity assign for this meeting"),
    NoMediaExist("there is no media exist"),
    FinalRoomRoleFlagNotDefine("final room role flag is not exist"),
    MeetingItemIdNotExist("id is not exist in database"),
    UserIsDeactive("user is not have a permission to join this meeting"),
    DeleteMeetingErrorForAdminReason("specific meeting is not allowed to delete because you are in this meeting yet"),
    DeleteMeetingErrorForUserReason("specific meeting has member or members that are online"),
    MrmlIsNotExist("detail of layer is not exist"),
    LayerIdNotExist("this layer id is not exist"),
    InputValuesAreNull("the input value are null"),
    NoSvgFoundInThisMeeting("there is no svg in this meeting"),
    VectorNotExist("this vector id is not exist"),
    SvgNotExist("this Svg id is not exist"),
    SvgFileNotExist("this svg file not exist"),
    UserIsCurrentlyAddedToPrivateTalk("this user currently added to private talk"),
    NoPrivateTalkExist("there is no private Talk exist for this id"),
    PrivateTalkDoesntHaveSpecificUser("the private talk doesnt have user with this session id"),
    NoMeetingExistForStoping("there is no meeting for stoping"),
    NoRecordedArchiveForThisMeeting("no recorded arcive for this meeting"),
    PrivateTalkDoesntExist("this private talk id doesnt exist");


    private  final String errorValue;

    TtErrors(String name) {
        this.errorValue = name;
    }

    public String getErrorValueService() {
        return errorValue;
    }

    public String geterrorValue() {

        BaseErrorsServiceImp baseErrorsServiceImp = (BaseErrorsServiceImp)IOCContainer.GetBeans(BaseErrorsServiceImp.class);
        String errorValue = baseErrorsServiceImp.findBy(Restrictions.eq(BaseErrors.ERROR_ID,this)).getErrorValue();
        return errorValue;
    }

    public static TtErrors getByOrdinal(int o){
        for (TtErrors f:values()) {
            if(f.ordinal()==o){
                return  f;
            }
        }
        return null;
    }
}
