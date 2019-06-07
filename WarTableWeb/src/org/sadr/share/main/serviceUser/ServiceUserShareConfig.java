package org.sadr.share.main.serviceUser;

import org.sadr.share.main.grade.GradeShareConfig;
import org.sadr.share.main.orgPosition.OrgPositionShareConfig;
import org.sadr.share.main.personModel.PersonModelConfig;
import org.sadr.share.main.personModel.PersonModelShareConfig;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserShareConfig;
import org.sadr.share.main.sessions.SessionsShareConfig;
import org.sadr.share.main.textChat.TextChatShareConfig;
import org.sadr.web.config.WebConfig;
import org.sadr.web.main.system.irror.irror.IrrorConfig;
import org.springframework.context.annotation.Bean;

;

public class ServiceUserShareConfig extends WebConfig {

    @Bean
    public ServiceUserDaoImp serviceUserDaoImp() {
        ServiceUserDaoImp udi = new ServiceUserDaoImp();
        return udi;
    }

    @Bean
    public ServiceUserShareServiceImp serviceUserShareServiceImp() {
        ServiceUserShareServiceImp usi = new ServiceUserShareServiceImp();
        usi.setDao(serviceUserDaoImp());
        return usi;
    }

    @Bean
    public ServiceUserShareController serviceUserShareController() {
        ServiceUserShareController uc = new ServiceUserShareController();
        uc.setService(serviceUserShareServiceImp());
        uc.setGradeShareService(new GradeShareConfig().gradeShareServiceImp());
        uc.setOrgPositionShareService(new OrgPositionShareConfig().orgPositionShareServiceImp());
        uc.setSessionsShareService(new SessionsShareConfig().sessionsShareServiceImp());
        uc.setRoom_serviceUserShareService(new Room_ServiceUserShareConfig().room_ServiceUserShareServiceImp());
        uc.setPersonModelShareService(new PersonModelShareConfig().personModelShareServiceImp());
        uc.setTextChatShareService(new TextChatShareConfig().textChatShareServiceImp());
        return uc;
    }


}
