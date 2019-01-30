package org.sadr.share.main.serviceUser;

import org.sadr.share.config.ShareConfig;
import org.sadr.share.main.criticalLog.CriticalLogServiceImp;
import org.sadr.share.main.log.models.serviceLog.ServiceLogServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.context.annotation.Bean;



public class ServiceUserConfig extends ShareConfig {

    @Bean
    public ServiceUserDaoImp serviceUserDaoImp()
    {
        ServiceUserDaoImp udi = new ServiceUserDaoImp();
        return udi;
    }

    @Bean
    public ServiceUserServiceImp serviceUserServiceImp()
    {
        ServiceUserServiceImp usi = new ServiceUserServiceImp();
        usi.setDao(serviceUserDaoImp());
        //usi.setServiceConfigServiceImp(new ServiceConfigServiceImp());

        //usi.setServiceUserServiceImp(new ServiceUserServiceImp());
        usi.setSessionsServiceImp(new SessionsServiceImp());
        usi.setCriticalLogServiceImp(new CriticalLogServiceImp());
        usi.setRoom_serviceUserServiceImp(new Room_ServiceUserServiceImp());
        usi.setServiceLogServiceImp(new ServiceLogServiceImp());
        usi.setRoom_serviceUserServiceImp(new Room_ServiceUserServiceImp());

       // usi.setRoom_memberServiceImp(new Room_MemberServiceImp());
        return usi;
    }



}
