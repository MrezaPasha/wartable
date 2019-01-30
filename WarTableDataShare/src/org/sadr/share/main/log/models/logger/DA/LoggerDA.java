package org.sadr.share.main.log.models.logger.DA;

import org.hibernate.criterion.Restrictions;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils._type.TtCalendarItem;
import org.sadr.service.config.IOCContainer;
import org.sadr.service.main.rpc._core.Utils;
import org.sadr.service.main.rpc._types.TtConfig;
import org.sadr.share.main.SC.SCLogConfig.SCLogConfig;
import org.sadr.share.main.SC.SCLogConfig.SCLogConfigServiceImp;
import org.sadr.share.main.baseConfig.BaseConfig;
import org.sadr.share.main.baseConfig.BaseConfigServiceImp;
import org.sadr.share.main.log._types.TtActionType;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.log.models.logger.TO.Logger;
import org.sadr.share.main.log.models.logger.TO.LoggerTO;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerDA {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static SCLogConfigServiceImp scLogConfigServiceImp;
    private static String ipAddress;
    private static String port;
    private static String dbName;
    private static String dbUsername;
    private static String dbPassword;
    private static String tableName;
    private static String connectionString;
    private BaseConfigServiceImp baseConfigServiceImp = (BaseConfigServiceImp)IOCContainer.GetBeans(BaseConfigServiceImp.class);
    List<BaseConfig> baseConfigs = baseConfigServiceImp.findAll();
    private static Map configMap = new HashMap();

    public LoggerDA() throws Exception{
        for (BaseConfig baseConfig: baseConfigs)
        {
            configMap.put(baseConfig.getConfigId(),baseConfig.getConfigValue());
        }
        Class.forName("org.postgresql.Driver");
        //serviceConfigServiceImp = (ServiceConfigServiceImp)IOCContainer.GetBeans(ServiceConfigServiceImp.class);
        //ServiceConfig serviceConfig = serviceConfigServiceImp.findAll().get(0);
        if (!configMap.isEmpty())
        {
            /*ipAddress = serviceConfig.getLoggerIPAddress();
            port = serviceConfig.getLoggerPort();
            dbName = serviceConfig.getLoggerDbName();
            dbUsername = serviceConfig.getLoggerDbUsername();
            dbPassword = serviceConfig.getLoggerDbPassword();
            tableName = serviceConfig.getLoggerTableName();*/
            ipAddress = configMap.get(TtConfig.ServerIP).toString();
            dbName = configMap.get(TtConfig.LoggerDbName).toString();
            dbUsername = configMap.get(TtConfig.LoggerDbUsername).toString();
            dbPassword = configMap.get(TtConfig.LoggerDbPassword).toString();
            tableName = configMap.get(TtConfig.LoggerTableName).toString();

            connectionString = "jdbc:postgresql://" + ipAddress + ":" + port + "/" + dbName ;
            connection = DriverManager.getConnection(connectionString,dbUsername,dbPassword);
            scLogConfigServiceImp = (SCLogConfigServiceImp)IOCContainer.GetBeans(SCLogConfigServiceImp.class);


        }

    }
    public boolean isTableExist()
    {
        try
        {

            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "employee", null);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public boolean createTable(Logger logger) throws Exception
    {
        try
        {
            String createTableQuery = "CREATE TABLE " + tableName +
                "(id BIGINT not NULL, " +
                " log VARCHAR(4000), " +
                " importance INTEGER not NULL, " +
                " actionType INTEGER not NULL, " +
                " sendstate INTEGER not NULL, " +
                " sendStatus INTEGER not NULL, " +
                " sendTime VARCHAR(40), " +
                " deliverDateTime VARCHAR(40), " +
                " PRIMARY KEY ( id ))";
            preparedStatement = connection.prepareStatement(createTableQuery);
            preparedStatement.execute();
            return true;

        }
        catch (Exception e)
        {
            new LoggerBL().log(logger.getURL(),logger.getUsername(),logger.getUserUniqueID(),logger.getSubTypeDescription(),logger.getDescription());
            return false;

        }
    }
    public void insert(Logger logger) throws Exception
    {
        if (isTableExist())
        {
            try
            {
                String actionType = logger.getActionType();
                SCLogConfig scLogConfig = scLogConfigServiceImp.findBy(Restrictions.eq(SCLogConfig.ACTION_TYPE,TtActionType.valueOf(actionType)));
                if(scLogConfig != null && scLogConfig.getSendState())
                {
                    logger.setImportance(scLogConfig.getImportance().getName());
                    LoggerTO loggerTO = new LoggerTO();
                    loggerTO.setLog(Utils.ObjToJson(logger));
                    loggerTO.setActionType(scLogConfig.getActionType());
                    loggerTO.setImportance(scLogConfig.getImportance());
                    loggerTO.setSendstate(scLogConfig.getSendState());
                    loggerTO.setSendStatus(scLogConfig.getSendStatus());
                    switch (scLogConfig.getSendStatus())
                    {

                        case RealTime:
                        {

                            loggerTO.setSendTime(ParsCalendar.getInstance().getShortDateTime());
                            // here we must send log to remote security center real time
                            loggerTO.setDeliverDateTime(ParsCalendar.getInstance().getShortDateTime());
                            break;

                        }
                        case SpecificTime:
                        {
                            loggerTO.setSendTime(scLogConfig.getSendTime());
                            loggerTO.setDeliverDateTime(null);
                            // here we must not log to remote security center real time

                        }
                        case Delay:
                        {
                            loggerTO.setSendTime(ParsCalendar.getInstance().getShortDateTime(TtCalendarItem.Minute,scLogConfig.getSendDelayMinuteTime()));
                            loggerTO.setDeliverDateTime(null);

                        }
                    }
                    preparedStatement = connection.prepareStatement("INSERT INTO " + tableName +
                        " (log,importance,actionType,sendstate,sendStatus,sendTime,deliverDateTime) " +
                        "VALUES(?,?,?,?,?,?,?)");
                    preparedStatement.setString(1,loggerTO.getLog());
                    preparedStatement.setInt(2,loggerTO.getImportance().ordinal());
                    preparedStatement.setInt(3,loggerTO.getActionType().ordinal());
                    preparedStatement.setInt(4,loggerTO.getSendStatus().ordinal());
                    preparedStatement.setInt(5,loggerTO.getSendStatus().ordinal());
                    preparedStatement.setString(6,loggerTO.getSendTime());
                    preparedStatement.setString(7,loggerTO.getDeliverDateTime());
                    preparedStatement.execute();

                }
            }
            catch (Exception e)
            {
                new LoggerBL().log(logger.getURL(),logger.getUsername(),logger.getUserUniqueID(),logger.getSubTypeDescription(),logger.getDescription());

            }

        }
        else if (!isTableExist())
        {
            if(createTable(logger))
                insert(logger);
        }

    }
}
