package org.sadr.web.config;

import org.sadr._core.utils.Cryptor;
import org.sadr._core.utils.OutLog;
import org.sadr.web.main.CoreConfig;
import org.sadr.web.main._core.develop.DevelopConfig;
import org.sadr.web.main._core.propertor.*;
import org.sadr.web.main._core.propertor._type.TtPropertorInBootList;
import org.sadr.web.main._core.propertor._type.TtPropertorInBoot_DeployMode;
import org.sadr.web.main._core.setting.SettingConfig;
import org.sadr.web.main._core.setting.SettingController;
import org.sadr.web.main._core.tools.authorizer.AuthorizerAspectConfig;
import org.sadr.web.main._core.tools.schedule.SchedulerConfig;
import org.sadr.web.main._core.ui.UiConfig;
import org.sadr.web.main._core.ui.UiController;
import org.sadr.web.main.admin.user.confirm.UserConfirm;
import org.sadr.web.main.admin.user.confirm.UserConfirmConfig;
import org.sadr.web.main.admin.user.group.UserGroup;
import org.sadr.web.main.admin.user.group.UserGroupConfig;
import org.sadr.web.main.admin.user.porter.UserPorter;
import org.sadr.web.main.admin.user.porter.UserPorterConfig;
import org.sadr.web.main.admin.user.user.User;
import org.sadr.web.main.admin.user.user.UserConfig;
import org.sadr.web.main.admin.user.uuid.UserUuid;
import org.sadr.web.main.admin.user.uuid.UserUuidConfig;
import org.sadr.web.main.archive.directory.Directory;
import org.sadr.web.main.archive.directory.DirectoryConfig;
import org.sadr.web.main.archive.file.download.FileDownload;
import org.sadr.web.main.archive.file.download.FileDownloadConfig;
import org.sadr.web.main.archive.file.file.File;
import org.sadr.web.main.archive.file.file.FileConfig;
import org.sadr.web.main.note.note.Note;
import org.sadr.web.main.note.note.NoteConfig;
import org.sadr.web.main.system.backup.Backup;
import org.sadr.web.main.system.backup.BackupConfig;
import org.sadr.web.main.system.field.Field;
import org.sadr.web.main.system.field.FieldConfig;
import org.sadr.web.main.system.irror.Irror;
import org.sadr.web.main.system.irror.IrrorConfig;
import org.sadr.web.main.system.log.attempt.UserAttempt;
import org.sadr.web.main.system.log.attempt.UserAttemptConfig;
import org.sadr.web.main.system.log.daily.DailyLog;
import org.sadr.web.main.system.log.daily.DailyLogConfig;
import org.sadr.web.main.system.log.general.Log;
import org.sadr.web.main.system.log.general.LogConfig;
import org.sadr.web.main.system.log.remote.RemoteLog;
import org.sadr.web.main.system.log.remote.RemoteLogConfig;
import org.sadr.web.main.system.log.signin.SigninLog;
import org.sadr.web.main.system.log.signin.SigninLogConfig;
import org.sadr.web.main.system.model.Model;
import org.sadr.web.main.system.model.ModelConfig;
import org.sadr.web.main.system.module.Module;
import org.sadr.web.main.system.module.ModuleConfig;
import org.sadr.web.main.system.registery.Registery;
import org.sadr.web.main.system.registery.RegisteryConfig;
import org.sadr.web.main.system.task.Task;
import org.sadr.web.main.system.task.TaskConfig;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author MSD
 */
public class WebConfigHandler {

    //=========================################===========================// messageSource
    private static ReloadableResourceBundleMessageSource messageSource;

    public static ReloadableResourceBundleMessageSource getMessageSource() {
        return messageSource;
    }

    public static void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
        WebConfigHandler.messageSource = messageSource;
    }


    //=========================################===========================// webApplicationContext
    private static AnnotationConfigWebApplicationContext webApplicationContext;

    public static AnnotationConfigWebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public static void setWebApplicationContext(AnnotationConfigWebApplicationContext acwac) {
        webApplicationContext = acwac;
    }

    //=========================################===========================// Database
    private static String[] databaseParamRest;
    private static String[] databaseParamLog;

    public static void setDatabaseParamsRest(Environment env) {
        String dm = PropertorInBoot.getInstance().getProperty(TtPropertorInBootList.DeployMode);
        if (TtPropertorInBoot_DeployMode.ServerReal.getTitle().equals(dm)) {
            databaseParamRest = new String[]{
                    Cryptor.decrypt(env.getProperty("db.rest.server.url")) + Cryptor.decrypt(env.getProperty("db.rest.server.name")),
                    Cryptor.decrypt(env.getProperty("db.rest.server.username")),
                    Cryptor.decrypt(env.getProperty("db.rest.server.password")),
                    Cryptor.decrypt(env.getProperty("db.rest.server.name"))};
        } else {
            OutLog.pl(env.getProperty("db.rest.local.name"));
            databaseParamRest = new String[]{
                    Cryptor.decrypt(env.getProperty("db.rest.local.url")) + env.getProperty("db.rest.local.name"),
                    Cryptor.decrypt(env.getProperty("db.rest.local.username")),
                    Cryptor.decrypt(env.getProperty("db.rest.local.password")),
                    env.getProperty("db.rest.local.name")};
            OutLog.pl(Arrays.toString(databaseParamRest));
        }
    }

    public static String[] getDatabaseParamRest() {
        return databaseParamRest;
    }

    public static void setDatabaseParamsLog(Environment env) {
        String dm = PropertorInBoot.getInstance().getProperty(TtPropertorInBootList.DeployMode);
        if (TtPropertorInBoot_DeployMode.ServerReal.getTitle().equals(dm)) {
            databaseParamLog = new String[]{
                    Cryptor.decrypt(env.getProperty("db.log.server.url")) + Cryptor.decrypt(env.getProperty("db.log.server.name")),
                    Cryptor.decrypt(env.getProperty("db.log.server.username")),
                    Cryptor.decrypt(env.getProperty("db.log.server.password")),
                    Cryptor.decrypt(env.getProperty("db.log.server.name"))};
        } else {
            OutLog.pl(env.getProperty("db.log.local.name"));
            databaseParamLog = new String[]{
                    Cryptor.decrypt(env.getProperty("db.log.local.url")) + env.getProperty("db.log.local.name"),
                    Cryptor.decrypt(env.getProperty("db.log.local.username")),
                    Cryptor.decrypt(env.getProperty("db.log.local.password")),
                    env.getProperty("db.log.local.name")};
        }
    }

    public static String[] getDatabaseParamLog() {
        return databaseParamLog;
    }

    public static List<Class<?>> getModelClasses() {
        List<Class<?>> classes = new ArrayList<>();
        if (modelClassesLog != null) {
            classes.addAll(modelClassesLog);
        }
        classes.addAll(modelClassesRest);
        return classes;
    }


    //=========================################===========================// ModelClasses Rest
    private static List<Class<?>> modelClassesRest;

    public static List<Class<?>> getModelClassesRest() {
        return modelClassesRest;
    }

    public static void addModelClassRest(Class c) {
        if (modelClassesRest == null) {
            modelClassesRest = new ArrayList<>();
        }
        modelClassesRest.add(c);
    }


    public static Class<?>[] getModelClassArraysRest() {
        if (modelClassesRest == null) {
            return null;
        }
        return modelClassesRest.toArray(new Class<?>[modelClassesRest.size()]);
    }

    ////------
    public static void loadModelsRest() {
        //============================  Core
        addModelClassRest(Module.class);
        addModelClassRest(Task.class);
        addModelClassRest(Model.class);
        addModelClassRest(Field.class);
        addModelClassRest(Registery.class);
        addModelClassRest(Irror.class);
        //
        addModelClassRest(User.class);
        addModelClassRest(UserUuid.class);
        addModelClassRest(UserGroup.class);
        addModelClassRest(UserAttempt.class);
        addModelClassRest(UserPorter.class);
        addModelClassRest(UserConfirm.class);
        //
        addModelClassRest(Log.class);
        addModelClassRest(DailyLog.class);
        addModelClassRest(SigninLog.class);

        //============================  archive
        addModelClassRest(File.class);
        addModelClassRest(FileDownload.class);
        addModelClassRest(Directory.class);

        //============================  note
        addModelClassRest(Note.class);

        //============================  note
        addModelClassRest(Backup.class);


    }


    //=========================################===========================// ModelClasses Log

    private static List<Class<?>> modelClassesLog;

    public static List<Class<?>> getModelClassesLog() {
        return modelClassesLog;
    }

    public static void addModelClassLog(Class c) {
        if (modelClassesLog == null) {
            modelClassesLog = new ArrayList<>();
        }
        modelClassesLog.add(c);
    }

    public static Class<?>[] getModelClassArraysLog() {
        if (modelClassesLog == null) {
            return null;
        }
        return modelClassesLog.toArray(new Class<?>[modelClassesLog.size()]);
    }

    ////------
    public static void loadModelsLog() {
        addModelClassLog(RemoteLog.class);
    }


    //=========================################===========================// ConfigClasses
    private static List<Class<?>> configClasses;

    public static void addConfigClass(Class c) {
        if (configClasses == null) {
            configClasses = new ArrayList<>();
        }
        configClasses.add(c);
    }

    public static List<Class<?>> getConfigClasses() {
        return configClasses;
    }

    public static Class<?>[] getConfigClassArrays() {
        if (configClasses == null) {
            return null;
        }
        return configClasses.toArray(new Class<?>[configClasses.size()]);
    }

    ////------
    public static void loadConfigs() {
        loadCoreConfigs();
    }

    private static void loadCoreConfigs() {
        OutLog.po("");
        //============================  Core
        addConfigClass(CoreConfig.class);
        //
        addConfigClass(ModuleConfig.class);
        addConfigClass(TaskConfig.class);
        addConfigClass(ModelConfig.class);
        addConfigClass(FieldConfig.class);
        addConfigClass(RegisteryConfig.class);
        addConfigClass(IrrorConfig.class);
        //

        addConfigClass(UserConfig.class);
        addConfigClass(UserUuidConfig.class);
        addConfigClass(UserGroupConfig.class);
        addConfigClass(UserAttemptConfig.class);
        addConfigClass(UserPorterConfig.class);
        addConfigClass(UserConfirmConfig.class);
        addConfigClass(LogConfig.class);
        addConfigClass(DailyLogConfig.class);
        addConfigClass(SigninLogConfig.class);
        addConfigClass(RemoteLogConfig.class);
        //
        addConfigClass(AuthorizerAspectConfig.class);
        addConfigClass(SchedulerConfig.class);
        addConfigClass(SettingConfig.class);
        addConfigClass(UiConfig.class);
        addConfigClass(DevelopConfig.class);
        addConfigClass(PropertorConfig.class);

        //============================  archive
        addConfigClass(FileConfig.class);
        addConfigClass(FileDownloadConfig.class);
        addConfigClass(DirectoryConfig.class);

        //============================  note
        addConfigClass(NoteConfig.class);

        //============================  backup
        addConfigClass(BackupConfig.class);

    }


    //=========================################===========================// Load Propertor
    public static void loadPropertors() {
        PropertorInControl.getInstance().load();
        PropertorInWeb.getInstance().load();
        PropertorInBackup.getInstance().load();
        PropertorInLog.getInstance().load();
    }

    //=========================################===========================// InitPrime
    public static void initPrime() {
        SettingController ic;
        ic = webApplicationContext.getBean(SettingController.class);
        boolean isInitEmergency;
        try {
            isInitEmergency = PropertorInBoot.getInstance().isOnProperty(TtPropertorInBootList.InitEmergency);
        } catch (Exception e) {
            isInitEmergency = false;
        }
        if (ic != null) {
            ic.initCorePrime(isInitEmergency);
        }
    }

    //=========================################===========================// Init Ui
    public static void initUi() {
        UiController ic;
        ic = webApplicationContext.getBean(UiController.class);
        if (ic != null) {
            ic.init();
        }
    }

}
