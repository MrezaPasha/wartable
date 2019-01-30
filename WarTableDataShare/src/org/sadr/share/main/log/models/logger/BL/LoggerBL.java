package org.sadr.share.main.log.models.logger.BL;

import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.share.main._types.TtImportance;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.criticalLog.CriticalLog;
import org.sadr.share.main.criticalLog.CriticalLogServiceImp;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log._types.TtFlag;
import org.sadr.share.main.log._types.TtSensitivity;
import org.sadr.share.main.log._types.TtSubType;
import org.sadr.share.main.log.models.serviceLog.ServiceLogServiceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerBL {
    private static ServiceLogServiceImp serviceLogServiceImp = (ServiceLogServiceImp)IOCContainer.GetBeans(ServiceLogServiceImp.class);
    private static CriticalLogServiceImp criticalLogServiceImp = (CriticalLogServiceImp)IOCContainer.GetBeans(CriticalLogServiceImp.class);
    //private static ServiceConfig serviceConfig = serviceConfigServiceImp.findAll().get(0);
    private static BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp)IOCContainer.GetBeans(BaseConfigServiceImp.class);
    List<BaseConfig> baseConfigs = null;

    private static Map configMap = new HashMap();

   /* public LoggerBL() {
        List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
        for (BaseConfig baseConfig: baseConfigs)
        {
            configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
        }
    }*/

    public static void log(String url , String username , String userUniqueId , String subtypeDescription , String description)
    {
        try
        {
            List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
            for (BaseConfig baseConfig: baseConfigs)
            {
                configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
            }

            if (!configMap.isEmpty())
            {
                serviceLogServiceImp.log(url,username,userUniqueId,subtypeDescription,description);
                /*Logger logger = new Logger();
                logger.setTime(ParsCalendar.getInstance().getShortDateTime());
                logger.setSoftwareID(configMap.get(TtConfig.SoftwareID).toString());
                logger.setSoftwareName(configMap.get(TtConfig.SoftwareName).toString());
                logger.setSoftwareVersion(configMap.get(TtConfig.SoftwareVersion).toString());
                logger.setServerHostname(configMap.get(TtConfig.ServerHostname).toString());
                logger.setServerIP(configMap.get(TtConfig.ServerIP).toString());
                logger.setPortNumber(configMap.get(TtConfig.ServerPort).toString());
                logger.setClientHostname(null);
                logger.setClientIP(null);
                logger.setPageTitle(null);
                logger.setURL(url);
                logger.setUsername(username);
                logger.setUserUniqueID(userUniqueId);
                logger.setActionType(TtActionType.Error.getActionName());
                logger.setSensitivity(TtSensitivity.Exception.getSensitivityName());
                logger.setImportance(TtImportance.HighImportance.getName());
                logger.setFlag(TtFlag.Failure.getFlagName());
                logger.setSubType(TtSubType.Report.getSubTypeName());
                logger.setSubTypeDescription(subtypeDescription);
                logger.setDescription(description);
                LoggerDA loggerDA = new LoggerDA();
                loggerDA.insert(logger);*/
            }
        }
        catch (Exception e)
        {
            log(url,username,userUniqueId,subtypeDescription,description);
        }
    }
    public static void log(String url , String username , String userUniqueId , TtActionType actionType , TtSensitivity sensitivity , TtImportance importance , TtFlag flag , TtSubType subType , String subtypeDescription , String description)
    {
        List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
        for (BaseConfig baseConfig: baseConfigs)
        {
            configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
        }
        try
        {
            if (!configMap.isEmpty())
            {
                serviceLogServiceImp.log(url,username,userUniqueId,actionType,sensitivity,importance,flag,subType,subtypeDescription,description);
                /*Logger logger = new Logger();
                logger.setTime(ParsCalendar.getInstance().getShortDateTime());
                logger.setSoftwareID(configMap.get(TtConfig.SoftwareID).toString());
                logger.setSoftwareName(configMap.get(TtConfig.SoftwareName).toString());
                logger.setSoftwareVersion(configMap.get(TtConfig.SoftwareVersion).toString());
                logger.setServerHostname(configMap.get(TtConfig.ServerHostname).toString());
                logger.setServerIP(configMap.get(TtConfig.ServerIP).toString());
                logger.setPortNumber(configMap.get(TtConfig.ServerPort).toString());
                logger.setClientHostname(null);
                logger.setClientIP(null);
                logger.setPageTitle(null);
                logger.setURL(url);
                logger.setUsername(username);
                logger.setUserUniqueID(userUniqueId);
                logger.setActionType(actionType.getActionName());
                logger.setSensitivity(sensitivity.getSensitivityName());
                logger.setImportance(importance.getName());
                logger.setFlag(flag.getFlagName());
                logger.setSubType(subType.getSubTypeName());
                logger.setSubTypeDescription(subtypeDescription);
                logger.setDescription(description);
                LoggerDA loggerDA = new LoggerDA();
                loggerDA.insert(logger);*/
            }

        }
        catch (Exception e)
        {
            log(url,username,userUniqueId,subtypeDescription,description);
        }
    }
    public static void insertCriticalLog(String dateTime,String position,String errorMessage)
    {
        try
        {

            CriticalLog criticalLog = new CriticalLog();
            criticalLog.setCreationDateTime(dateTime);
            criticalLog.setLogDetailPosition(position);
            criticalLog.setLogDetailMessage(errorMessage);
            criticalLogServiceImp.save(criticalLog);

        }
        catch (Exception e )
        {
            Utils.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
    }

}
