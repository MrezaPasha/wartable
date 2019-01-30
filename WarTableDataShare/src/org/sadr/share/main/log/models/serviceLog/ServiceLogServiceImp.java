package org.sadr.share.main.log.models.serviceLog;

import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.service.main.rpc._types.TtConfig;
import org.sadr.share.main._types.TtImportance;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtFlag;
import org.sadr.share.main.log._types.TtSensitivity;
import org.sadr.share.main.log._types.TtSubType;
import org.sadr.share.main.serviceUser.ServiceUserServiceImp;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ServiceLogServiceImp extends GenericServiceImpl<ServiceLog,ServiceLogDao> implements ServiceLogService {
    private  ServiceLogServiceImp serviceLogServiceImp;
    private  ServiceUserServiceImp serviceUserServiceImp;
    private  SessionsServiceImp sessionsServiceImp;
    private  BaseConfigServiceImp baseConfigServiceImp ;
    private List<BaseConfig> baseConfigs ;
    private static Map configMap = new HashMap();



    @Autowired
    public void setBaseConfigServiceImp(BaseConfigServiceImp baseConfigServiceImp) {
        this.baseConfigServiceImp = baseConfigServiceImp;
    }

    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    @Autowired
    public void setServiceUserServiceImp(ServiceUserServiceImp serviceUserServiceImp) {
        this.serviceUserServiceImp = serviceUserServiceImp;
    }



    @Autowired
    public void setServiceLogServiceImp(ServiceLogServiceImp serviceLogServiceImp) {
        this.serviceLogServiceImp = serviceLogServiceImp;
    }




    public  void log(String url , String username , String userUniqueId , String subtypeDescription , String description)
    {
        try
        {
            baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }

            //ServiceConfig serviceConfig = serviceConfigServiceImp.findAll().get(0);
            ServiceLog serviceLog = new ServiceLog();
            serviceLog.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
            serviceLog.setSoftwareID(configMap.get(TtConfig.SoftwareID).toString());
            serviceLog.setSoftwareName(configMap.get(TtConfig.SoftwareName).toString());
            serviceLog.setSoftwareVersion(configMap.get(TtConfig.SoftwareVersion).toString());
            serviceLog.setServerHostname(configMap.get(TtConfig.ServerHostname).toString());
            serviceLog.setPortNumber(configMap.get(TtConfig.ServerPort).toString());
            serviceLog.setClientHostname(null);
            serviceLog.setClientIP(null);
            serviceLog.setPageTitle(null);
            serviceLog.setUrl(url);
            serviceLog.setUsername(username);
            serviceLog.setUserUniqueID(userUniqueId);
            serviceLog.setActionType(TtActionType.Error);
            serviceLog.setSensitivity(TtSensitivity.Exception);
            serviceLog.setImportance(TtImportance.HighImportance);
            serviceLog.setFlag(TtFlag.Failure);
            serviceLog.setSubType(TtSubType.Report);
            serviceLog.setSubTypeDescription(subtypeDescription);
            serviceLog.setDescription(description);
            serviceLogServiceImp.save(serviceLog);

        }
        catch (Exception e )
        {
            log(url,username,userUniqueId,subtypeDescription,description);
        }

    }
    public  void log(String url , String username , String userUniqueId , TtActionType actionType , TtSensitivity sensitivity , TtImportance importance , TtFlag flag , TtSubType subType , String subtypeDescription , String description)
    {
        try
        {
            baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }

            //ServiceConfigServiceImp serviceConfigServiceImp = (ServiceConfigServiceImp)IOCContainer.GetBeans(ServiceConfigServiceImp.class);
           // ServiceLogServiceImp serviceLogServiceImp = (ServiceLogServiceImp)IOCContainer.GetBeans(ServiceLogServiceImp.class);
            //List<ServiceConfig> serviceConfigs = serviceConfigServiceImp.findAll();
            //ServiceConfig serviceConfig = serviceConfigs.get(0);
            ServiceLog serviceLog = new ServiceLog();
            serviceLog.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
            serviceLog.setSoftwareID(configMap.get(TtConfig.SoftwareID).toString());
            serviceLog.setSoftwareName(configMap.get(TtConfig.SoftwareName).toString());
            serviceLog.setSoftwareVersion(configMap.get(TtConfig.SoftwareVersion).toString());
            serviceLog.setServerHostname(configMap.get(TtConfig.ServerHostname).toString());
            serviceLog.setPortNumber(configMap.get(TtConfig.ServerPort).toString());
            serviceLog.setClientHostname(null);
            serviceLog.setClientIP(null);
            serviceLog.setPageTitle(null);
            serviceLog.setUrl(url);
            serviceLog.setUsername(username);
            serviceLog.setUserUniqueID(userUniqueId);
            serviceLog.setActionType(actionType);
            serviceLog.setSensitivity(sensitivity);
            serviceLog.setImportance(importance);
            serviceLog.setFlag(flag);
            serviceLog.setSubType(subType);
            serviceLog.setSubTypeDescription(subtypeDescription);
            serviceLog.setDescription(description);
            serviceLogServiceImp.save(serviceLog);

        }
        catch (Exception e )
        {
            e.printStackTrace();
            log(url,username,userUniqueId,subtypeDescription,description);
        }
    }
}
