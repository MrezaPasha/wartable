PGDMP                      	    v            wtdb    9.5.2    9.5.2 �    �	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �	           1262    69236    wtdb    DATABASE     �   CREATE DATABASE wtdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE wtdb;
             sa_wt    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �	           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6            �	           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6                        3079    12355    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �	           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    69237    Service.Admin.Account    TABLE     �   CREATE TABLE "Service.Admin.Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "accountGroup_id" bigint,
    "accountModel_id" bigint
);
 +   DROP TABLE public."Service.Admin.Account";
       public         postgres    false    6            �            1259    69242    Service.Admin.Account.Group    TABLE     �   CREATE TABLE "Service.Admin.Account.Group" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 1   DROP TABLE public."Service.Admin.Account.Group";
       public         postgres    false    6            �            1259    69247    Service.Admin.Account.Model    TABLE     �   CREATE TABLE "Service.Admin.Account.Model" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 1   DROP TABLE public."Service.Admin.Account.Model";
       public         postgres    false    6            �            1259    69252    Service.Admin.Notification    TABLE     �   CREATE TABLE "Service.Admin.Notification" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 0   DROP TABLE public."Service.Admin.Notification";
       public         postgres    false    6            �            1259    69257 "   Service.Admin.Notification_Account    TABLE     �   CREATE TABLE "Service.Admin.Notification_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    notification_id bigint
);
 8   DROP TABLE public."Service.Admin.Notification_Account";
       public         postgres    false    6            �            1259    69262    Service.Layer    TABLE     �   CREATE TABLE "Service.Layer" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 #   DROP TABLE public."Service.Layer";
       public         postgres    false    6            �            1259    69267    Service.Layer_Map    TABLE     �   CREATE TABLE "Service.Layer_Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    layer_id bigint,
    map_id bigint
);
 '   DROP TABLE public."Service.Layer_Map";
       public         postgres    false    6            �            1259    69272    Service.Map    TABLE     �   CREATE TABLE "Service.Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "mapRegion_id" bigint
);
 !   DROP TABLE public."Service.Map";
       public         postgres    false    6            �            1259    69277    Service.Map.Region    TABLE     �   CREATE TABLE "Service.Map.Region" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 (   DROP TABLE public."Service.Map.Region";
       public         postgres    false    6            �            1259    69282    Service.Map_Room    TABLE     �   CREATE TABLE "Service.Map_Room" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    room_id bigint
);
 &   DROP TABLE public."Service.Map_Room";
       public         postgres    false    6            �            1259    69287    Service.Material    TABLE     �   CREATE TABLE "Service.Material" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 &   DROP TABLE public."Service.Material";
       public         postgres    false    6            �            1259    69292    Service.Material.Area    TABLE     �   CREATE TABLE "Service.Material.Area" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    material_id bigint
);
 +   DROP TABLE public."Service.Material.Area";
       public         postgres    false    6            �            1259    69297    Service.Material.Group    TABLE     �   CREATE TABLE "Service.Material.Group" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 ,   DROP TABLE public."Service.Material.Group";
       public         postgres    false    6            �            1259    69302    Service.Material.Material_Group    TABLE     r   CREATE TABLE "Service.Material.Material_Group" (
    group_id bigint NOT NULL,
    material_id bigint NOT NULL
);
 5   DROP TABLE public."Service.Material.Material_Group";
       public         postgres    false    6            �            1259    69307    Service.Material_Map    TABLE     �   CREATE TABLE "Service.Material_Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    material_id bigint
);
 *   DROP TABLE public."Service.Material_Map";
       public         postgres    false    6            �            1259    69312    Service.Meeting    TABLE     �   CREATE TABLE "Service.Meeting" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    room_id bigint
);
 %   DROP TABLE public."Service.Meeting";
       public         postgres    false    6            �            1259    69317    Service.Meeting_Account    TABLE     �   CREATE TABLE "Service.Meeting_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    meeting_id bigint
);
 -   DROP TABLE public."Service.Meeting_Account";
       public         postgres    false    6            �            1259    69322    Service.Room    TABLE     �   CREATE TABLE "Service.Room" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "activeMeeting_id" bigint,
    "adminAccount_id" bigint
);
 "   DROP TABLE public."Service.Room";
       public         postgres    false    6            �            1259    69327    Service.Room_Account    TABLE     �   CREATE TABLE "Service.Room_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    room_id bigint
);
 *   DROP TABLE public."Service.Room_Account";
       public         postgres    false    6            �            1259    69332    Web.Admin.User    TABLE       CREATE TABLE "Web.Admin.User" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "accessLimitDailyEnd" integer NOT NULL,
    "accessLimitDailyStart" integer NOT NULL,
    "accessLimitMonthlyEnd" integer NOT NULL,
    "accessLimitMonthlyStart" integer NOT NULL,
    "accessLimitTimelyEnd" character varying(15),
    "accessLimitTimelyStart" character varying(15),
    "accessLimitYearlyEnd" character varying(30),
    "accessLimitYearlyStart" character varying(30),
    comment character varying(255),
    "firstName" character varying(50),
    gender integer,
    "ipAddress" character varying(50),
    "ipAddressEnd" character varying(50),
    "ipAddressFirstSignin" character varying(50),
    "ipAddressStart" character varying(50),
    "ipRangeType" integer,
    "isBlocked" boolean,
    "isLogManager" boolean,
    "isNeedToChangePassword" boolean,
    "isSuperAdmin" boolean,
    "lastName" character varying(50),
    "lastSigninDateTime" character varying(30),
    level integer,
    password character varying(255) NOT NULL,
    "passwordDateTime" character varying(30),
    "passwordHistory" text,
    "porterUuid" character varying(1000),
    status integer NOT NULL,
    "superAdminCode" character varying(100),
    "superAdminLogingId" integer NOT NULL,
    "superAdminLogingIn" boolean,
    "userCode" character varying(255),
    username character varying(255) NOT NULL,
    logo_id bigint,
    CONSTRAINT "Web.Admin.User_accessLimitDailyEnd_check" CHECK ((("accessLimitDailyEnd" >= 0) AND ("accessLimitDailyEnd" <= 31))),
    CONSTRAINT "Web.Admin.User_accessLimitDailyStart_check" CHECK ((("accessLimitDailyStart" >= 0) AND ("accessLimitDailyStart" <= 31))),
    CONSTRAINT "Web.Admin.User_accessLimitMonthlyEnd_check" CHECK ((("accessLimitMonthlyEnd" >= 0) AND ("accessLimitMonthlyEnd" <= 12))),
    CONSTRAINT "Web.Admin.User_accessLimitMonthlyStart_check" CHECK ((("accessLimitMonthlyStart" >= 0) AND ("accessLimitMonthlyStart" <= 12)))
);
 $   DROP TABLE public."Web.Admin.User";
       public         postgres    false    6            �            1259    69344    Web.Admin.User.Group    TABLE     c  CREATE TABLE "Web.Admin.User.Group" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    code character varying(255),
    "memberCount" integer NOT NULL,
    "subCount" integer NOT NULL,
    title character varying(150) NOT NULL,
    parent_id bigint
);
 *   DROP TABLE public."Web.Admin.User.Group";
       public         postgres    false    6            �            1259    69349    Web.Admin.User.Porter    TABLE     �  CREATE TABLE "Web.Admin.User.Porter" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "agentSignature" character varying(255),
    "computerSignature" character varying(255),
    "confirmCode" integer NOT NULL,
    "confirmCodeDateTimeG" bigint NOT NULL,
    count integer NOT NULL,
    "expireDateTimeG" bigint NOT NULL,
    "isActiveTwoStepConfirm" boolean,
    "isConfirmed" boolean,
    "signoutStatus" integer,
    "singinDateTimeG" bigint NOT NULL,
    "singoutDateTimeG" bigint NOT NULL,
    uuid character varying(255),
    user_id bigint,
    CONSTRAINT "Web.Admin.User.Porter_count_check" CHECK ((count >= 0))
);
 +   DROP TABLE public."Web.Admin.User.Porter";
       public         postgres    false    6            �            1259    69358    Web.Admin.User.User_UserGroup    TABLE     r   CREATE TABLE "Web.Admin.User.User_UserGroup" (
    "userGroup_id" bigint NOT NULL,
    user_id bigint NOT NULL
);
 3   DROP TABLE public."Web.Admin.User.User_UserGroup";
       public         postgres    false    6            �            1259    69363    Web.Admin.User.Uuid    TABLE     �  CREATE TABLE "Web.Admin.User.Uuid" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "agentSignature" character varying(250),
    "computerSignature" character varying(250),
    "expireDateTimeG" bigint NOT NULL,
    "singinDateTimeG" bigint NOT NULL,
    uuid character varying(100),
    user_id bigint
);
 )   DROP TABLE public."Web.Admin.User.Uuid";
       public         postgres    false    6            �            1259    69371    Web.Admin.UserConfirm    TABLE     �  CREATE TABLE "Web.Admin.UserConfirm" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "callCount" integer NOT NULL,
    "confirmDateTime" character varying(30),
    "confirmDateTimeG" bigint NOT NULL,
    "taskSignature" character varying(250),
    task_id bigint,
    user_id bigint
);
 +   DROP TABLE public."Web.Admin.UserConfirm";
       public         postgres    false    6            �            1259    69376    Web.Archive.Directory    TABLE     �  CREATE TABLE "Web.Archive.Directory" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "className" character varying(255),
    comment character varying(255),
    "containedDirectoryCount" integer NOT NULL,
    "containedFileCount" integer NOT NULL,
    "directoryOrigin" integer,
    icon character varying(255),
    "isPermanent" boolean,
    level integer NOT NULL,
    name character varying(255) NOT NULL,
    path character varying(255),
    "repoDirectory" integer,
    url character varying(255),
    owner_id bigint,
    parent_id bigint
);
 +   DROP TABLE public."Web.Archive.Directory";
       public         postgres    false    6            �            1259    69384    Web.Archive.File    TABLE     �  CREATE TABLE "Web.Archive.File" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "accessLevel" integer,
    "accessRule" character varying(250),
    "contentType" character varying(250),
    "directoryAbsolutePath" character varying(255),
    "directoryRelativePath" character varying(255),
    "downloadCount" integer NOT NULL,
    "downloadCountGuest" integer NOT NULL,
    "fetchManner" integer,
    "isContainOrginal" boolean,
    "isTemporary" boolean,
    "lastUploadDateTime" character varying(30),
    "orginalName" character varying(100),
    size bigint NOT NULL,
    thumbnails character varying(512),
    title character varying(100),
    "tryToDownloadCount" integer NOT NULL,
    "tryToDownloadCountGuest" integer NOT NULL,
    "uploadLink" character varying(255),
    "uploadLinkComment" character varying(255),
    "uploadStatus" integer,
    directory_id bigint,
    owner_id bigint
);
 &   DROP TABLE public."Web.Archive.File";
       public         postgres    false    6            �            1259    69392    Web.Archive.File.Download    TABLE     �   CREATE TABLE "Web.Archive.File.Download" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    status integer,
    file_id bigint,
    user_id bigint
);
 /   DROP TABLE public."Web.Archive.File.Download";
       public         postgres    false    6            �            1259    69397    Web.Note    TABLE     �  CREATE TABLE "Web.Note" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "dateTime" character varying(30),
    "dateTimeG" bigint NOT NULL,
    importance integer,
    "isNotified" boolean,
    "isVisited" boolean,
    message text,
    title character varying(250),
    user_id bigint
);
    DROP TABLE public."Web.Note";
       public         postgres    false    6            �            1259    69405    Web.System.Backup    TABLE       CREATE TABLE "Web.System.Backup" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "backupDateTime" character varying(30),
    "backupType" integer,
    file_id bigint
);
 '   DROP TABLE public."Web.System.Backup";
       public         postgres    false    6            �            1259    69410    Web.System.Field    TABLE       CREATE TABLE "Web.System.Field" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "dbConstraint" character varying(255),
    "dbDefaultValue" character varying(255),
    "dbExtra" character varying(255),
    "dbIndex" character varying(255),
    "dbKey" character varying(255),
    "dbRefColumn" character varying(255),
    "dbRefTable" character varying(255),
    "dbSize" integer NOT NULL,
    "dbTitle" character varying(255),
    "dbType" character varying(255),
    "isBidirectional" boolean,
    "isDbFK" boolean,
    "isDbNullable" boolean,
    "isDbPrimary" boolean,
    "isDbRefreshed" boolean,
    "isEncrypted" boolean,
    "isMoNullable" boolean,
    "isMoRefreshed" boolean,
    "moAnnotations" character varying(255),
    "moDataRelation" integer,
    "moDataRelationDes" character varying(255),
    "moDefaultValue" character varying(255),
    "moExtra" character varying(255),
    "moKey" character varying(255),
    "moMaxSize" integer NOT NULL,
    "moMinSize" integer NOT NULL,
    "moModifier" integer NOT NULL,
    "moPreviousTitle" character varying(255),
    "moTitle" character varying(255),
    "moType" character varying(255),
    status integer,
    model_id bigint
);
 &   DROP TABLE public."Web.System.Field";
       public         postgres    false    6            �            1259    69418    Web.System.Irror    TABLE     �  CREATE TABLE "Web.System.Irror" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "agentSignature" character varying(255),
    cause character varying(255),
    "computerSignature" character varying(255),
    "httpErrorCode" integer,
    "isVisited" boolean,
    level integer,
    message text,
    place integer,
    "porterUuid" character varying(255),
    "sessionId" character varying(255),
    status integer,
    "taskName" character varying(255),
    "visitCount" integer NOT NULL,
    user_id bigint,
    CONSTRAINT "Web.System.Irror_visitCount_check" CHECK (("visitCount" >= 0))
);
 &   DROP TABLE public."Web.System.Irror";
       public         postgres    false    6            �            1259    69427    Web.System.Log    TABLE     $  CREATE TABLE "Web.System.Log" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "actionStatus" integer,
    "actionSubType" integer,
    "actionType" integer,
    "agentSignature" character varying(255),
    "computerSignature" character varying(255),
    "dateTimeG" bigint NOT NULL,
    "httpCode" character varying(100),
    "importanceLevel" integer,
    "isTaskTwoLevelConfirm" boolean,
    message character varying(2048),
    "onlineLoggingStrategy" integer,
    "portNumber" integer NOT NULL,
    "porterUuid" character varying(255),
    "requestMethod" character varying(255),
    "sendDateTimeG" bigint NOT NULL,
    "sendStatus" integer,
    sensitivity integer,
    "serverId" character varying(250),
    "sessionId" character varying(255),
    "taskName" character varying(255),
    "taskTitle" character varying(255),
    url character varying(512),
    "userGroupId" bigint NOT NULL,
    "userId" bigint NOT NULL,
    "userLevel" integer
);
 $   DROP TABLE public."Web.System.Log";
       public         postgres    false    6            �            1259    69435    Web.System.Log.DailyLog    TABLE     S  CREATE TABLE "Web.System.Log.DailyLog" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    chrome integer NOT NULL,
    "dayDate" character varying(255),
    desktop integer NOT NULL,
    firefox integer NOT NULL,
    "guestVisitCount" integer NOT NULL,
    "internetExplorer" integer NOT NULL,
    mobile integer NOT NULL,
    netscape integer NOT NULL,
    opera integer NOT NULL,
    "pageCount" integer NOT NULL,
    "userVisitCount" integer NOT NULL,
    "visitCount" integer NOT NULL
);
 -   DROP TABLE public."Web.System.Log.DailyLog";
       public         postgres    false    6            �            1259    69440    Web.System.Log.SigninLog    TABLE       CREATE TABLE "Web.System.Log.SigninLog" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "agentSignature" character varying(250),
    "computerSignature" character varying(250),
    "domainName" character varying(150),
    "ipAddress" character varying(50),
    "lastDateTime" character varying(30),
    "lastDateTimeG" bigint NOT NULL,
    status integer,
    uuid character varying(150),
    user_id bigint
);
 .   DROP TABLE public."Web.System.Log.SigninLog";
       public         postgres    false    6            �            1259    69448    Web.System.Log.User    TABLE     *  CREATE TABLE "Web.System.Log.User" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "agentSignature" character varying(250),
    "attemptType" integer,
    "computerSignature" character varying(250),
    count integer NOT NULL,
    "dateTimeG" bigint NOT NULL,
    "isSuccess" boolean,
    "lastDateTimeG" bigint NOT NULL,
    uuid character varying(150),
    user_id bigint,
    CONSTRAINT "Web.System.Log.User_count_check" CHECK ((count >= 0))
);
 )   DROP TABLE public."Web.System.Log.User";
       public         postgres    false    6            �            1259    69457    Web.System.Model    TABLE     �  CREATE TABLE "Web.System.Model" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "className" character varying(255),
    "isRefreshed" boolean,
    "messageCode" character varying(255),
    "packageName" character varying(255),
    status integer,
    "tableName" character varying(255)
);
 &   DROP TABLE public."Web.System.Model";
       public         postgres    false    6            �            1259    69465    Web.System.Module    TABLE     �  CREATE TABLE "Web.System.Module" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "className" character varying(255),
    "isProtected" boolean,
    "isRefreshed" boolean,
    "menuMessageCode" character varying(255),
    "messageCode" character varying(255),
    "packageName" character varying(255),
    url character varying(255)
);
 '   DROP TABLE public."Web.System.Module";
       public         postgres    false    6            �            1259    69473    Web.System.Registery    TABLE     p  CREATE TABLE "Web.System.Registery" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    key character varying(255),
    title character varying(255),
    type character varying(255),
    value character varying(255),
    "valueJson" text,
    "valueType" integer
);
 *   DROP TABLE public."Web.System.Registery";
       public         postgres    false    6            �            1259    69481    Web.System.Task    TABLE     �  CREATE TABLE "Web.System.Task" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "accessLevel" integer,
    "actionType" integer,
    "importanceLevel" integer,
    "isActive" boolean,
    "isActiveLogging" boolean,
    "isAjax" boolean,
    "isLogManager" boolean,
    "isOnlineLogging" boolean,
    "isPanelTask" boolean,
    "isRefreshed" boolean,
    "isSuperAdmin" boolean,
    "isTwoLevelConfirm" boolean,
    "menuIdentity" character varying(255),
    "menuMessageCode" character varying(255),
    "messageCode" character varying(255),
    method integer,
    "onlineLoggingDelay" integer NOT NULL,
    "onlineLoggingStrategy" integer,
    "onlineSchedulingTime" character varying(15),
    sensitivity integer,
    signature character varying(255),
    url character varying(255),
    module_id bigint
);
 %   DROP TABLE public."Web.System.Task";
       public         postgres    false    6            �            1259    69489    Web.System.Task_User    TABLE     b   CREATE TABLE "Web.System.Task_User" (
    user_id bigint NOT NULL,
    task_id bigint NOT NULL
);
 *   DROP TABLE public."Web.System.Task_User";
       public         postgres    false    6            �            1259    69494    Web.System.Task_UserGroup    TABLE     n   CREATE TABLE "Web.System.Task_UserGroup" (
    "userGroup_id" bigint NOT NULL,
    task_id bigint NOT NULL
);
 /   DROP TABLE public."Web.System.Task_UserGroup";
       public         postgres    false    6            �            1259    69499    hibernate_sequence    SEQUENCE     t   CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    6            l	          0    69237    Service.Admin.Account 
   TABLE DATA               �   COPY "Service.Admin.Account" (id, "createDateTime", "entityState", "modifyDateTime", "accountGroup_id", "accountModel_id") FROM stdin;
    public       postgres    false    181   �*      m	          0    69242    Service.Admin.Account.Group 
   TABLE DATA               g   COPY "Service.Admin.Account.Group" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    182   �*      n	          0    69247    Service.Admin.Account.Model 
   TABLE DATA               g   COPY "Service.Admin.Account.Model" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    183   �*      o	          0    69252    Service.Admin.Notification 
   TABLE DATA               f   COPY "Service.Admin.Notification" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    184   �*      p	          0    69257 "   Service.Admin.Notification_Account 
   TABLE DATA               �   COPY "Service.Admin.Notification_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, notification_id) FROM stdin;
    public       postgres    false    185   +      q	          0    69262    Service.Layer 
   TABLE DATA               Y   COPY "Service.Layer" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    186   8+      r	          0    69267    Service.Layer_Map 
   TABLE DATA               o   COPY "Service.Layer_Map" (id, "createDateTime", "entityState", "modifyDateTime", layer_id, map_id) FROM stdin;
    public       postgres    false    187   U+      s	          0    69272    Service.Map 
   TABLE DATA               g   COPY "Service.Map" (id, "createDateTime", "entityState", "modifyDateTime", "mapRegion_id") FROM stdin;
    public       postgres    false    188   r+      t	          0    69277    Service.Map.Region 
   TABLE DATA               ^   COPY "Service.Map.Region" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    189   �+      u	          0    69282    Service.Map_Room 
   TABLE DATA               m   COPY "Service.Map_Room" (id, "createDateTime", "entityState", "modifyDateTime", map_id, room_id) FROM stdin;
    public       postgres    false    190   �+      v	          0    69287    Service.Material 
   TABLE DATA               \   COPY "Service.Material" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    191   �+      w	          0    69292    Service.Material.Area 
   TABLE DATA               n   COPY "Service.Material.Area" (id, "createDateTime", "entityState", "modifyDateTime", material_id) FROM stdin;
    public       postgres    false    192   �+      x	          0    69297    Service.Material.Group 
   TABLE DATA               b   COPY "Service.Material.Group" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    193   ,      y	          0    69302    Service.Material.Material_Group 
   TABLE DATA               K   COPY "Service.Material.Material_Group" (group_id, material_id) FROM stdin;
    public       postgres    false    194    ,      z	          0    69307    Service.Material_Map 
   TABLE DATA               u   COPY "Service.Material_Map" (id, "createDateTime", "entityState", "modifyDateTime", map_id, material_id) FROM stdin;
    public       postgres    false    195   =,      {	          0    69312    Service.Meeting 
   TABLE DATA               l   COPY "Service.Meeting" (id, "createDateTime", "entityState", "modifyDateTime", map_id, room_id) FROM stdin;
    public       postgres    false    196   Z,      |	          0    69317    Service.Meeting_Account 
   TABLE DATA               {   COPY "Service.Meeting_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, meeting_id) FROM stdin;
    public       postgres    false    197   w,      }	          0    69322    Service.Room 
   TABLE DATA                  COPY "Service.Room" (id, "createDateTime", "entityState", "modifyDateTime", "activeMeeting_id", "adminAccount_id") FROM stdin;
    public       postgres    false    198   �,      ~	          0    69327    Service.Room_Account 
   TABLE DATA               u   COPY "Service.Room_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, room_id) FROM stdin;
    public       postgres    false    199   �,      	          0    69332    Web.Admin.User 
   TABLE DATA               �  COPY "Web.Admin.User" (id, "createDateTime", "entityState", "modifyDateTime", "accessLimitDailyEnd", "accessLimitDailyStart", "accessLimitMonthlyEnd", "accessLimitMonthlyStart", "accessLimitTimelyEnd", "accessLimitTimelyStart", "accessLimitYearlyEnd", "accessLimitYearlyStart", comment, "firstName", gender, "ipAddress", "ipAddressEnd", "ipAddressFirstSignin", "ipAddressStart", "ipRangeType", "isBlocked", "isLogManager", "isNeedToChangePassword", "isSuperAdmin", "lastName", "lastSigninDateTime", level, password, "passwordDateTime", "passwordHistory", "porterUuid", status, "superAdminCode", "superAdminLogingId", "superAdminLogingIn", "userCode", username, logo_id) FROM stdin;
    public       postgres    false    200   �,      �	          0    69344    Web.Admin.User.Group 
   TABLE DATA               �   COPY "Web.Admin.User.Group" (id, "createDateTime", "entityState", "modifyDateTime", code, "memberCount", "subCount", title, parent_id) FROM stdin;
    public       postgres    false    201   G.      �	          0    69349    Web.Admin.User.Porter 
   TABLE DATA               9  COPY "Web.Admin.User.Porter" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "confirmCode", "confirmCodeDateTimeG", count, "expireDateTimeG", "isActiveTwoStepConfirm", "isConfirmed", "signoutStatus", "singinDateTimeG", "singoutDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    202   d.      �	          0    69358    Web.Admin.User.User_UserGroup 
   TABLE DATA               K   COPY "Web.Admin.User.User_UserGroup" ("userGroup_id", user_id) FROM stdin;
    public       postgres    false    203   �.      �	          0    69363    Web.Admin.User.Uuid 
   TABLE DATA               �   COPY "Web.Admin.User.Uuid" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "expireDateTimeG", "singinDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    204   �.      �	          0    69371    Web.Admin.UserConfirm 
   TABLE DATA               �   COPY "Web.Admin.UserConfirm" (id, "createDateTime", "entityState", "modifyDateTime", "callCount", "confirmDateTime", "confirmDateTimeG", "taskSignature", task_id, user_id) FROM stdin;
    public       postgres    false    205   �.      �	          0    69376    Web.Archive.Directory 
   TABLE DATA                 COPY "Web.Archive.Directory" (id, "createDateTime", "entityState", "modifyDateTime", "className", comment, "containedDirectoryCount", "containedFileCount", "directoryOrigin", icon, "isPermanent", level, name, path, "repoDirectory", url, owner_id, parent_id) FROM stdin;
    public       postgres    false    206   �.      �	          0    69384    Web.Archive.File 
   TABLE DATA               �  COPY "Web.Archive.File" (id, "createDateTime", "entityState", "modifyDateTime", "accessLevel", "accessRule", "contentType", "directoryAbsolutePath", "directoryRelativePath", "downloadCount", "downloadCountGuest", "fetchManner", "isContainOrginal", "isTemporary", "lastUploadDateTime", "orginalName", size, thumbnails, title, "tryToDownloadCount", "tryToDownloadCountGuest", "uploadLink", "uploadLinkComment", "uploadStatus", directory_id, owner_id) FROM stdin;
    public       postgres    false    207   l/      �	          0    69392    Web.Archive.File.Download 
   TABLE DATA                  COPY "Web.Archive.File.Download" (id, "createDateTime", "entityState", "modifyDateTime", status, file_id, user_id) FROM stdin;
    public       postgres    false    208   �/      �	          0    69397    Web.Note 
   TABLE DATA               �   COPY "Web.Note" (id, "createDateTime", "entityState", "modifyDateTime", "dateTime", "dateTimeG", importance, "isNotified", "isVisited", message, title, user_id) FROM stdin;
    public       postgres    false    209   �/      �	          0    69405    Web.System.Backup 
   TABLE DATA               �   COPY "Web.System.Backup" (id, "createDateTime", "entityState", "modifyDateTime", "backupDateTime", "backupType", file_id) FROM stdin;
    public       postgres    false    210   �/      �	          0    69410    Web.System.Field 
   TABLE DATA                 COPY "Web.System.Field" (id, "createDateTime", "entityState", "modifyDateTime", "dbConstraint", "dbDefaultValue", "dbExtra", "dbIndex", "dbKey", "dbRefColumn", "dbRefTable", "dbSize", "dbTitle", "dbType", "isBidirectional", "isDbFK", "isDbNullable", "isDbPrimary", "isDbRefreshed", "isEncrypted", "isMoNullable", "isMoRefreshed", "moAnnotations", "moDataRelation", "moDataRelationDes", "moDefaultValue", "moExtra", "moKey", "moMaxSize", "moMinSize", "moModifier", "moPreviousTitle", "moTitle", "moType", status, model_id) FROM stdin;
    public       postgres    false    211   0      �	          0    69418    Web.System.Irror 
   TABLE DATA                 COPY "Web.System.Irror" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", cause, "computerSignature", "httpErrorCode", "isVisited", level, message, place, "porterUuid", "sessionId", status, "taskName", "visitCount", user_id) FROM stdin;
    public       postgres    false    212   60      �	          0    69427    Web.System.Log 
   TABLE DATA               �  COPY "Web.System.Log" (id, "createDateTime", "entityState", "modifyDateTime", "actionStatus", "actionSubType", "actionType", "agentSignature", "computerSignature", "dateTimeG", "httpCode", "importanceLevel", "isTaskTwoLevelConfirm", message, "onlineLoggingStrategy", "portNumber", "porterUuid", "requestMethod", "sendDateTimeG", "sendStatus", sensitivity, "serverId", "sessionId", "taskName", "taskTitle", url, "userGroupId", "userId", "userLevel") FROM stdin;
    public       postgres    false    213   �=      �	          0    69435    Web.System.Log.DailyLog 
   TABLE DATA               �   COPY "Web.System.Log.DailyLog" (id, "createDateTime", "entityState", "modifyDateTime", chrome, "dayDate", desktop, firefox, "guestVisitCount", "internetExplorer", mobile, netscape, opera, "pageCount", "userVisitCount", "visitCount") FROM stdin;
    public       postgres    false    214   Mf      �	          0    69440    Web.System.Log.SigninLog 
   TABLE DATA               �   COPY "Web.System.Log.SigninLog" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "domainName", "ipAddress", "lastDateTime", "lastDateTimeG", status, uuid, user_id) FROM stdin;
    public       postgres    false    215   jf      �	          0    69448    Web.System.Log.User 
   TABLE DATA               �   COPY "Web.System.Log.User" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "attemptType", "computerSignature", count, "dateTimeG", "isSuccess", "lastDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    216   �h      �	          0    69457    Web.System.Model 
   TABLE DATA               �   COPY "Web.System.Model" (id, "createDateTime", "entityState", "modifyDateTime", "className", "isRefreshed", "messageCode", "packageName", status, "tableName") FROM stdin;
    public       postgres    false    217   �h      �	          0    69465    Web.System.Module 
   TABLE DATA               �   COPY "Web.System.Module" (id, "createDateTime", "entityState", "modifyDateTime", "className", "isProtected", "isRefreshed", "menuMessageCode", "messageCode", "packageName", url) FROM stdin;
    public       postgres    false    218   i      �	          0    69473    Web.System.Registery 
   TABLE DATA               �   COPY "Web.System.Registery" (id, "createDateTime", "entityState", "modifyDateTime", key, title, type, value, "valueJson", "valueType") FROM stdin;
    public       postgres    false    219   )m      �	          0    69481    Web.System.Task 
   TABLE DATA               �  COPY "Web.System.Task" (id, "createDateTime", "entityState", "modifyDateTime", "accessLevel", "actionType", "importanceLevel", "isActive", "isActiveLogging", "isAjax", "isLogManager", "isOnlineLogging", "isPanelTask", "isRefreshed", "isSuperAdmin", "isTwoLevelConfirm", "menuIdentity", "menuMessageCode", "messageCode", method, "onlineLoggingDelay", "onlineLoggingStrategy", "onlineSchedulingTime", sensitivity, signature, url, module_id) FROM stdin;
    public       postgres    false    220   yp      �	          0    69489    Web.System.Task_User 
   TABLE DATA               ;   COPY "Web.System.Task_User" (user_id, task_id) FROM stdin;
    public       postgres    false    221   �{      �	          0    69494    Web.System.Task_UserGroup 
   TABLE DATA               G   COPY "Web.System.Task_UserGroup" ("userGroup_id", task_id) FROM stdin;
    public       postgres    false    222   �{      �	           0    0    hibernate_sequence    SEQUENCE SET     ;   SELECT pg_catalog.setval('hibernate_sequence', 491, true);
            public       postgres    false    223            z           2606    69246     Service.Admin.Account.Group_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY "Service.Admin.Account.Group"
    ADD CONSTRAINT "Service.Admin.Account.Group_pkey" PRIMARY KEY (id);
 j   ALTER TABLE ONLY public."Service.Admin.Account.Group" DROP CONSTRAINT "Service.Admin.Account.Group_pkey";
       public         postgres    false    182    182            |           2606    69251     Service.Admin.Account.Model_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY "Service.Admin.Account.Model"
    ADD CONSTRAINT "Service.Admin.Account.Model_pkey" PRIMARY KEY (id);
 j   ALTER TABLE ONLY public."Service.Admin.Account.Model" DROP CONSTRAINT "Service.Admin.Account.Model_pkey";
       public         postgres    false    183    183            x           2606    69241    Service.Admin.Account_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY "Service.Admin.Account"
    ADD CONSTRAINT "Service.Admin.Account_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "Service.Admin.Account_pkey";
       public         postgres    false    181    181            �           2606    69261 '   Service.Admin.Notification_Account_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY "Service.Admin.Notification_Account"
    ADD CONSTRAINT "Service.Admin.Notification_Account_pkey" PRIMARY KEY (id);
 x   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "Service.Admin.Notification_Account_pkey";
       public         postgres    false    185    185            ~           2606    69256    Service.Admin.Notification_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY "Service.Admin.Notification"
    ADD CONSTRAINT "Service.Admin.Notification_pkey" PRIMARY KEY (id);
 h   ALTER TABLE ONLY public."Service.Admin.Notification" DROP CONSTRAINT "Service.Admin.Notification_pkey";
       public         postgres    false    184    184            �           2606    69271    Service.Layer_Map_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY "Service.Layer_Map"
    ADD CONSTRAINT "Service.Layer_Map_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "Service.Layer_Map_pkey";
       public         postgres    false    187    187            �           2606    69266    Service.Layer_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY "Service.Layer"
    ADD CONSTRAINT "Service.Layer_pkey" PRIMARY KEY (id);
 N   ALTER TABLE ONLY public."Service.Layer" DROP CONSTRAINT "Service.Layer_pkey";
       public         postgres    false    186    186            �           2606    69281    Service.Map.Region_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY "Service.Map.Region"
    ADD CONSTRAINT "Service.Map.Region_pkey" PRIMARY KEY (id);
 X   ALTER TABLE ONLY public."Service.Map.Region" DROP CONSTRAINT "Service.Map.Region_pkey";
       public         postgres    false    189    189            �           2606    69286    Service.Map_Room_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY "Service.Map_Room"
    ADD CONSTRAINT "Service.Map_Room_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "Service.Map_Room_pkey";
       public         postgres    false    190    190            �           2606    69276    Service.Map_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY "Service.Map"
    ADD CONSTRAINT "Service.Map_pkey" PRIMARY KEY (id);
 J   ALTER TABLE ONLY public."Service.Map" DROP CONSTRAINT "Service.Map_pkey";
       public         postgres    false    188    188            �           2606    69296    Service.Material.Area_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY "Service.Material.Area"
    ADD CONSTRAINT "Service.Material.Area_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Service.Material.Area" DROP CONSTRAINT "Service.Material.Area_pkey";
       public         postgres    false    192    192            �           2606    69301    Service.Material.Group_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY "Service.Material.Group"
    ADD CONSTRAINT "Service.Material.Group_pkey" PRIMARY KEY (id);
 `   ALTER TABLE ONLY public."Service.Material.Group" DROP CONSTRAINT "Service.Material.Group_pkey";
       public         postgres    false    193    193            �           2606    69306 $   Service.Material.Material_Group_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY "Service.Material.Material_Group"
    ADD CONSTRAINT "Service.Material.Material_Group_pkey" PRIMARY KEY (material_id, group_id);
 r   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "Service.Material.Material_Group_pkey";
       public         postgres    false    194    194    194            �           2606    69311    Service.Material_Map_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY "Service.Material_Map"
    ADD CONSTRAINT "Service.Material_Map_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "Service.Material_Map_pkey";
       public         postgres    false    195    195            �           2606    69291    Service.Material_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY "Service.Material"
    ADD CONSTRAINT "Service.Material_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Service.Material" DROP CONSTRAINT "Service.Material_pkey";
       public         postgres    false    191    191            �           2606    69321    Service.Meeting_Account_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY "Service.Meeting_Account"
    ADD CONSTRAINT "Service.Meeting_Account_pkey" PRIMARY KEY (id);
 b   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "Service.Meeting_Account_pkey";
       public         postgres    false    197    197            �           2606    69316    Service.Meeting_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY "Service.Meeting"
    ADD CONSTRAINT "Service.Meeting_pkey" PRIMARY KEY (id);
 R   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "Service.Meeting_pkey";
       public         postgres    false    196    196            �           2606    69331    Service.Room_Account_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY "Service.Room_Account"
    ADD CONSTRAINT "Service.Room_Account_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "Service.Room_Account_pkey";
       public         postgres    false    199    199            �           2606    69326    Service.Room_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY "Service.Room"
    ADD CONSTRAINT "Service.Room_pkey" PRIMARY KEY (id);
 L   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "Service.Room_pkey";
       public         postgres    false    198    198            �           2606    69348    Web.Admin.User.Group_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY "Web.Admin.User.Group"
    ADD CONSTRAINT "Web.Admin.User.Group_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Web.Admin.User.Group" DROP CONSTRAINT "Web.Admin.User.Group_pkey";
       public         postgres    false    201    201            �           2606    69357    Web.Admin.User.Porter_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY "Web.Admin.User.Porter"
    ADD CONSTRAINT "Web.Admin.User.Porter_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Admin.User.Porter" DROP CONSTRAINT "Web.Admin.User.Porter_pkey";
       public         postgres    false    202    202            �           2606    69362 "   Web.Admin.User.User_UserGroup_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY "Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "Web.Admin.User.User_UserGroup_pkey" PRIMARY KEY (user_id, "userGroup_id");
 n   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "Web.Admin.User.User_UserGroup_pkey";
       public         postgres    false    203    203    203            �           2606    69370    Web.Admin.User.Uuid_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY "Web.Admin.User.Uuid"
    ADD CONSTRAINT "Web.Admin.User.Uuid_pkey" PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public."Web.Admin.User.Uuid" DROP CONSTRAINT "Web.Admin.User.Uuid_pkey";
       public         postgres    false    204    204            �           2606    69375    Web.Admin.UserConfirm_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY "Web.Admin.UserConfirm"
    ADD CONSTRAINT "Web.Admin.UserConfirm_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "Web.Admin.UserConfirm_pkey";
       public         postgres    false    205    205            �           2606    69343    Web.Admin.User_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY "Web.Admin.User"
    ADD CONSTRAINT "Web.Admin.User_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public."Web.Admin.User" DROP CONSTRAINT "Web.Admin.User_pkey";
       public         postgres    false    200    200            �           2606    69383    Web.Archive.Directory_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY "Web.Archive.Directory"
    ADD CONSTRAINT "Web.Archive.Directory_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "Web.Archive.Directory_pkey";
       public         postgres    false    206    206            �           2606    69396    Web.Archive.File.Download_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY "Web.Archive.File.Download"
    ADD CONSTRAINT "Web.Archive.File.Download_pkey" PRIMARY KEY (id);
 f   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "Web.Archive.File.Download_pkey";
       public         postgres    false    208    208            �           2606    69391    Web.Archive.File_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY "Web.Archive.File"
    ADD CONSTRAINT "Web.Archive.File_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "Web.Archive.File_pkey";
       public         postgres    false    207    207            �           2606    69404    Web.Note_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY "Web.Note"
    ADD CONSTRAINT "Web.Note_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Web.Note" DROP CONSTRAINT "Web.Note_pkey";
       public         postgres    false    209    209            �           2606    69409    Web.System.Backup_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY "Web.System.Backup"
    ADD CONSTRAINT "Web.System.Backup_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Web.System.Backup" DROP CONSTRAINT "Web.System.Backup_pkey";
       public         postgres    false    210    210            �           2606    69417    Web.System.Field_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY "Web.System.Field"
    ADD CONSTRAINT "Web.System.Field_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Field" DROP CONSTRAINT "Web.System.Field_pkey";
       public         postgres    false    211    211            �           2606    69426    Web.System.Irror_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY "Web.System.Irror"
    ADD CONSTRAINT "Web.System.Irror_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Irror" DROP CONSTRAINT "Web.System.Irror_pkey";
       public         postgres    false    212    212            �           2606    69439    Web.System.Log.DailyLog_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY "Web.System.Log.DailyLog"
    ADD CONSTRAINT "Web.System.Log.DailyLog_pkey" PRIMARY KEY (id);
 b   ALTER TABLE ONLY public."Web.System.Log.DailyLog" DROP CONSTRAINT "Web.System.Log.DailyLog_pkey";
       public         postgres    false    214    214            �           2606    69447    Web.System.Log.SigninLog_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY "Web.System.Log.SigninLog"
    ADD CONSTRAINT "Web.System.Log.SigninLog_pkey" PRIMARY KEY (id);
 d   ALTER TABLE ONLY public."Web.System.Log.SigninLog" DROP CONSTRAINT "Web.System.Log.SigninLog_pkey";
       public         postgres    false    215    215            �           2606    69456    Web.System.Log.User_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY "Web.System.Log.User"
    ADD CONSTRAINT "Web.System.Log.User_pkey" PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public."Web.System.Log.User" DROP CONSTRAINT "Web.System.Log.User_pkey";
       public         postgres    false    216    216            �           2606    69434    Web.System.Log_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY "Web.System.Log"
    ADD CONSTRAINT "Web.System.Log_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public."Web.System.Log" DROP CONSTRAINT "Web.System.Log_pkey";
       public         postgres    false    213    213            �           2606    69464    Web.System.Model_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY "Web.System.Model"
    ADD CONSTRAINT "Web.System.Model_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Model" DROP CONSTRAINT "Web.System.Model_pkey";
       public         postgres    false    217    217            �           2606    69472    Web.System.Module_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY "Web.System.Module"
    ADD CONSTRAINT "Web.System.Module_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Web.System.Module" DROP CONSTRAINT "Web.System.Module_pkey";
       public         postgres    false    218    218            �           2606    69480    Web.System.Registery_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY "Web.System.Registery"
    ADD CONSTRAINT "Web.System.Registery_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Web.System.Registery" DROP CONSTRAINT "Web.System.Registery_pkey";
       public         postgres    false    219    219            �           2606    69498    Web.System.Task_UserGroup_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Task_UserGroup"
    ADD CONSTRAINT "Web.System.Task_UserGroup_pkey" PRIMARY KEY (task_id, "userGroup_id");
 f   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "Web.System.Task_UserGroup_pkey";
       public         postgres    false    222    222    222            �           2606    69493    Web.System.Task_User_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY "Web.System.Task_User"
    ADD CONSTRAINT "Web.System.Task_User_pkey" PRIMARY KEY (task_id, user_id);
 \   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "Web.System.Task_User_pkey";
       public         postgres    false    221    221    221            �           2606    69488    Web.System.Task_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY "Web.System.Task"
    ADD CONSTRAINT "Web.System.Task_pkey" PRIMARY KEY (id);
 R   ALTER TABLE ONLY public."Web.System.Task" DROP CONSTRAINT "Web.System.Task_pkey";
       public         postgres    false    220    220            �           2606    69526    FK13nj6p1jqt0xubl2bm7evx7ob    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Layer_Map"
    ADD CONSTRAINT "FK13nj6p1jqt0xubl2bm7evx7ob" FOREIGN KEY (map_id) REFERENCES "Service.Map"(id);
 [   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "FK13nj6p1jqt0xubl2bm7evx7ob";
       public       postgres    false    187    2182    188            �           2606    69696    FK179yr80p4adleu0bn1y54bkm    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Irror"
    ADD CONSTRAINT "FK179yr80p4adleu0bn1y54bkm" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 Y   ALTER TABLE ONLY public."Web.System.Irror" DROP CONSTRAINT "FK179yr80p4adleu0bn1y54bkm";
       public       postgres    false    200    212    2206            �           2606    69691    FK1es95ggvfg9vombcaiqtlv5j5    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Field"
    ADD CONSTRAINT "FK1es95ggvfg9vombcaiqtlv5j5" FOREIGN KEY (model_id) REFERENCES "Web.System.Model"(id);
 Z   ALTER TABLE ONLY public."Web.System.Field" DROP CONSTRAINT "FK1es95ggvfg9vombcaiqtlv5j5";
       public       postgres    false    211    217    2240            �           2606    69611    FK1uwd6vtspmpndkcaw6f7c02jh    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Admin.User"
    ADD CONSTRAINT "FK1uwd6vtspmpndkcaw6f7c02jh" FOREIGN KEY (logo_id) REFERENCES "Web.Archive.File"(id);
 X   ALTER TABLE ONLY public."Web.Admin.User" DROP CONSTRAINT "FK1uwd6vtspmpndkcaw6f7c02jh";
       public       postgres    false    2220    200    207            �           2606    69581    FK1xfplj8yodpqrd6lymh91clcv    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Meeting_Account"
    ADD CONSTRAINT "FK1xfplj8yodpqrd6lymh91clcv" FOREIGN KEY (account_id) REFERENCES "Service.Admin.Account"(id);
 a   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "FK1xfplj8yodpqrd6lymh91clcv";
       public       postgres    false    197    2168    181            �           2606    69631    FK3u8c5qf627qwin8qwvm2jocfy    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "FK3u8c5qf627qwin8qwvm2jocfy" FOREIGN KEY ("userGroup_id") REFERENCES "Web.Admin.User.Group"(id);
 g   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "FK3u8c5qf627qwin8qwvm2jocfy";
       public       postgres    false    2208    203    201            �           2606    69686    FK4ch0kue8bh5lpvsyjqnl3qo7r    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Backup"
    ADD CONSTRAINT "FK4ch0kue8bh5lpvsyjqnl3qo7r" FOREIGN KEY (file_id) REFERENCES "Web.Archive.File"(id);
 [   ALTER TABLE ONLY public."Web.System.Backup" DROP CONSTRAINT "FK4ch0kue8bh5lpvsyjqnl3qo7r";
       public       postgres    false    210    207    2220            �           2606    69671    FK4kwt8ccqa8s3pm6wyhhhxhbor    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Archive.File.Download"
    ADD CONSTRAINT "FK4kwt8ccqa8s3pm6wyhhhxhbor" FOREIGN KEY (file_id) REFERENCES "Web.Archive.File"(id);
 c   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "FK4kwt8ccqa8s3pm6wyhhhxhbor";
       public       postgres    false    208    2220    207            �           2606    69571    FK5vxbkwhno5m5f6io9ewpfvfrt    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Meeting"
    ADD CONSTRAINT "FK5vxbkwhno5m5f6io9ewpfvfrt" FOREIGN KEY (map_id) REFERENCES "Service.Map"(id);
 Y   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "FK5vxbkwhno5m5f6io9ewpfvfrt";
       public       postgres    false    196    2182    188            �           2606    69551    FK6lqodoftkhranmm8ll9qddtig    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Material.Material_Group"
    ADD CONSTRAINT "FK6lqodoftkhranmm8ll9qddtig" FOREIGN KEY (material_id) REFERENCES "Service.Material"(id);
 i   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "FK6lqodoftkhranmm8ll9qddtig";
       public       postgres    false    191    2188    194            �           2606    69596    FK7y95kfit57u0f9xku0criwo18    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Room"
    ADD CONSTRAINT "FK7y95kfit57u0f9xku0criwo18" FOREIGN KEY ("adminAccount_id") REFERENCES "Service.Admin.Account"(id);
 V   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "FK7y95kfit57u0f9xku0criwo18";
       public       postgres    false    198    181    2168            �           2606    69656    FK86fcckds9fmo9vn6gnirngn7u    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Archive.Directory"
    ADD CONSTRAINT "FK86fcckds9fmo9vn6gnirngn7u" FOREIGN KEY (parent_id) REFERENCES "Web.Archive.Directory"(id);
 _   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "FK86fcckds9fmo9vn6gnirngn7u";
       public       postgres    false    2218    206    206            �           2606    69706    FK8ld9m40vmrs5efepdj72eojlv    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Log.User"
    ADD CONSTRAINT "FK8ld9m40vmrs5efepdj72eojlv" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 ]   ALTER TABLE ONLY public."Web.System.Log.User" DROP CONSTRAINT "FK8ld9m40vmrs5efepdj72eojlv";
       public       postgres    false    216    2206    200            �           2606    69586    FK8nm941j0t4gv3bumcwsyg9s3n    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Meeting_Account"
    ADD CONSTRAINT "FK8nm941j0t4gv3bumcwsyg9s3n" FOREIGN KEY (meeting_id) REFERENCES "Service.Meeting"(id);
 a   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "FK8nm941j0t4gv3bumcwsyg9s3n";
       public       postgres    false    2198    196    197            �           2606    69556    FK9bdp8ucdge8uyvc90q92do2f8    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Material.Material_Group"
    ADD CONSTRAINT "FK9bdp8ucdge8uyvc90q92do2f8" FOREIGN KEY (group_id) REFERENCES "Service.Material.Group"(id);
 i   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "FK9bdp8ucdge8uyvc90q92do2f8";
       public       postgres    false    193    2192    194            �           2606    69666    FKagip4f17ss7vj9s2wwrkcvm5r    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Archive.File"
    ADD CONSTRAINT "FKagip4f17ss7vj9s2wwrkcvm5r" FOREIGN KEY (owner_id) REFERENCES "Web.Admin.User"(id);
 Z   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "FKagip4f17ss7vj9s2wwrkcvm5r";
       public       postgres    false    2206    200    207            �           2606    69536    FKb3laq886tx4mhxma1549o6xby    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Map_Room"
    ADD CONSTRAINT "FKb3laq886tx4mhxma1549o6xby" FOREIGN KEY (map_id) REFERENCES "Service.Map"(id);
 Z   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "FKb3laq886tx4mhxma1549o6xby";
       public       postgres    false    2182    188    190            �           2606    69516    FKbopt2hxcvrh141iy9cjjya4s2    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Admin.Notification_Account"
    ADD CONSTRAINT "FKbopt2hxcvrh141iy9cjjya4s2" FOREIGN KEY (notification_id) REFERENCES "Service.Admin.Notification"(id);
 l   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "FKbopt2hxcvrh141iy9cjjya4s2";
       public       postgres    false    185    184    2174            �           2606    69501    FKbs6slesa63l45yy54d0uvdglm    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Admin.Account"
    ADD CONSTRAINT "FKbs6slesa63l45yy54d0uvdglm" FOREIGN KEY ("accountGroup_id") REFERENCES "Service.Admin.Account.Group"(id);
 _   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "FKbs6slesa63l45yy54d0uvdglm";
       public       postgres    false    181    182    2170            �           2606    69606    FKclhapld1q4n01ygcv0oif1i1    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Room_Account"
    ADD CONSTRAINT "FKclhapld1q4n01ygcv0oif1i1" FOREIGN KEY (room_id) REFERENCES "Service.Room"(id);
 ]   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "FKclhapld1q4n01ygcv0oif1i1";
       public       postgres    false    198    199    2202            �           2606    69541    FKddf8seuxcgwn0ux9xh8nafo5r    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Map_Room"
    ADD CONSTRAINT "FKddf8seuxcgwn0ux9xh8nafo5r" FOREIGN KEY (room_id) REFERENCES "Service.Room"(id);
 Z   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "FKddf8seuxcgwn0ux9xh8nafo5r";
       public       postgres    false    2202    198    190            �           2606    69616    FKdhnm45lgors90in2fw2h2sdtq    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Admin.User.Group"
    ADD CONSTRAINT "FKdhnm45lgors90in2fw2h2sdtq" FOREIGN KEY (parent_id) REFERENCES "Web.Admin.User.Group"(id);
 ^   ALTER TABLE ONLY public."Web.Admin.User.Group" DROP CONSTRAINT "FKdhnm45lgors90in2fw2h2sdtq";
       public       postgres    false    2208    201    201            �           2606    69591    FKe5eb6d3mlm3t7xekphhyndvt9    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Room"
    ADD CONSTRAINT "FKe5eb6d3mlm3t7xekphhyndvt9" FOREIGN KEY ("activeMeeting_id") REFERENCES "Service.Meeting"(id);
 V   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "FKe5eb6d3mlm3t7xekphhyndvt9";
       public       postgres    false    2198    198    196            �           2606    69661    FKev7boejuglytck8yuglcq1rd    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Archive.File"
    ADD CONSTRAINT "FKev7boejuglytck8yuglcq1rd" FOREIGN KEY (directory_id) REFERENCES "Web.Archive.Directory"(id);
 Y   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "FKev7boejuglytck8yuglcq1rd";
       public       postgres    false    207    206    2218            �           2606    69601    FKevw25cji9v1xvgt74eb675iyy    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Room_Account"
    ADD CONSTRAINT "FKevw25cji9v1xvgt74eb675iyy" FOREIGN KEY (account_id) REFERENCES "Service.Admin.Account"(id);
 ^   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "FKevw25cji9v1xvgt74eb675iyy";
       public       postgres    false    2168    181    199            �           2606    69726    FKflr8ben4uy9x748n06ounuldq    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Task_UserGroup"
    ADD CONSTRAINT "FKflr8ben4uy9x748n06ounuldq" FOREIGN KEY (task_id) REFERENCES "Web.System.Task"(id);
 c   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "FKflr8ben4uy9x748n06ounuldq";
       public       postgres    false    222    220    2246            �           2606    69731    FKfmar5n0tdatsry2ttfu0ombrp    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Task_UserGroup"
    ADD CONSTRAINT "FKfmar5n0tdatsry2ttfu0ombrp" FOREIGN KEY ("userGroup_id") REFERENCES "Web.Admin.User.Group"(id);
 c   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "FKfmar5n0tdatsry2ttfu0ombrp";
       public       postgres    false    2208    201    222            �           2606    69626    FKg72228uwjmtumk7o0khpnpaqb    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "FKg72228uwjmtumk7o0khpnpaqb" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 g   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "FKg72228uwjmtumk7o0khpnpaqb";
       public       postgres    false    203    200    2206            �           2606    69561    FKhc2x83k2jlmn29kq68o5a45um    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Material_Map"
    ADD CONSTRAINT "FKhc2x83k2jlmn29kq68o5a45um" FOREIGN KEY (map_id) REFERENCES "Service.Map"(id);
 ^   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "FKhc2x83k2jlmn29kq68o5a45um";
       public       postgres    false    2182    195    188            �           2606    69641    FKi4t2i0kg245d8fb6yj5xign2q    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Admin.UserConfirm"
    ADD CONSTRAINT "FKi4t2i0kg245d8fb6yj5xign2q" FOREIGN KEY (task_id) REFERENCES "Web.System.Task"(id);
 _   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "FKi4t2i0kg245d8fb6yj5xign2q";
       public       postgres    false    2246    220    205            �           2606    69566    FKj200gs4cgur5xq51a5kf82pel    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Material_Map"
    ADD CONSTRAINT "FKj200gs4cgur5xq51a5kf82pel" FOREIGN KEY (material_id) REFERENCES "Service.Material"(id);
 ^   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "FKj200gs4cgur5xq51a5kf82pel";
       public       postgres    false    195    2188    191            �           2606    69646    FKk5lovfvt0apu4ni7wkpqg5147    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Admin.UserConfirm"
    ADD CONSTRAINT "FKk5lovfvt0apu4ni7wkpqg5147" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 _   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "FKk5lovfvt0apu4ni7wkpqg5147";
       public       postgres    false    200    2206    205            �           2606    69621    FKkaat5quhoarvfx70f5m98q7j4    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Admin.User.Porter"
    ADD CONSTRAINT "FKkaat5quhoarvfx70f5m98q7j4" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 _   ALTER TABLE ONLY public."Web.Admin.User.Porter" DROP CONSTRAINT "FKkaat5quhoarvfx70f5m98q7j4";
       public       postgres    false    2206    200    202            �           2606    69721    FKkx74jgadegi5o45ijchxvuw2m    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Task_User"
    ADD CONSTRAINT "FKkx74jgadegi5o45ijchxvuw2m" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 ^   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "FKkx74jgadegi5o45ijchxvuw2m";
       public       postgres    false    2206    200    221            �           2606    69676    FKlmvo3wehmatw4p69t2498jrdr    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Archive.File.Download"
    ADD CONSTRAINT "FKlmvo3wehmatw4p69t2498jrdr" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 c   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "FKlmvo3wehmatw4p69t2498jrdr";
       public       postgres    false    2206    208    200            �           2606    69701    FKly85hqgbrmb5scmawg6kyrtn6    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Log.SigninLog"
    ADD CONSTRAINT "FKly85hqgbrmb5scmawg6kyrtn6" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 b   ALTER TABLE ONLY public."Web.System.Log.SigninLog" DROP CONSTRAINT "FKly85hqgbrmb5scmawg6kyrtn6";
       public       postgres    false    200    215    2206            �           2606    69651    FKmwjxdube2yqdoh7qkoeag216    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Archive.Directory"
    ADD CONSTRAINT "FKmwjxdube2yqdoh7qkoeag216" FOREIGN KEY (owner_id) REFERENCES "Web.Admin.User"(id);
 ^   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "FKmwjxdube2yqdoh7qkoeag216";
       public       postgres    false    2206    206    200            �           2606    69711    FKn1h41i6mfajsxx8vh8jdtu2u5    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Task"
    ADD CONSTRAINT "FKn1h41i6mfajsxx8vh8jdtu2u5" FOREIGN KEY (module_id) REFERENCES "Web.System.Module"(id);
 Y   ALTER TABLE ONLY public."Web.System.Task" DROP CONSTRAINT "FKn1h41i6mfajsxx8vh8jdtu2u5";
       public       postgres    false    2242    218    220            �           2606    69636    FKo27exmbsc9qlt8si712r12qci    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Admin.User.Uuid"
    ADD CONSTRAINT "FKo27exmbsc9qlt8si712r12qci" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 ]   ALTER TABLE ONLY public."Web.Admin.User.Uuid" DROP CONSTRAINT "FKo27exmbsc9qlt8si712r12qci";
       public       postgres    false    2206    200    204            �           2606    69681    FKpxrohtf9r2xso6tqgw5gr1ji0    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.Note"
    ADD CONSTRAINT "FKpxrohtf9r2xso6tqgw5gr1ji0" FOREIGN KEY (user_id) REFERENCES "Web.Admin.User"(id);
 R   ALTER TABLE ONLY public."Web.Note" DROP CONSTRAINT "FKpxrohtf9r2xso6tqgw5gr1ji0";
       public       postgres    false    2206    209    200            �           2606    69546    FKqk9o4fe4k64pg1030jec07ybj    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Material.Area"
    ADD CONSTRAINT "FKqk9o4fe4k64pg1030jec07ybj" FOREIGN KEY (material_id) REFERENCES "Service.Material"(id);
 _   ALTER TABLE ONLY public."Service.Material.Area" DROP CONSTRAINT "FKqk9o4fe4k64pg1030jec07ybj";
       public       postgres    false    192    2188    191            �           2606    69716    FKrbwfcbgu9tplt9huxukqoxwts    FK CONSTRAINT     �   ALTER TABLE ONLY "Web.System.Task_User"
    ADD CONSTRAINT "FKrbwfcbgu9tplt9huxukqoxwts" FOREIGN KEY (task_id) REFERENCES "Web.System.Task"(id);
 ^   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "FKrbwfcbgu9tplt9huxukqoxwts";
       public       postgres    false    2246    220    221            �           2606    69531    FKsaplo8iwt8crpoej8foemvr3g    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Map"
    ADD CONSTRAINT "FKsaplo8iwt8crpoej8foemvr3g" FOREIGN KEY ("mapRegion_id") REFERENCES "Service.Map.Region"(id);
 U   ALTER TABLE ONLY public."Service.Map" DROP CONSTRAINT "FKsaplo8iwt8crpoej8foemvr3g";
       public       postgres    false    188    2184    189            �           2606    69506    FKtag9wsxdn3huwoyu9xnct3i4o    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Admin.Account"
    ADD CONSTRAINT "FKtag9wsxdn3huwoyu9xnct3i4o" FOREIGN KEY ("accountModel_id") REFERENCES "Service.Admin.Account.Model"(id);
 _   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "FKtag9wsxdn3huwoyu9xnct3i4o";
       public       postgres    false    181    183    2172            �           2606    69521    FKtaw5vu9h5m0aw28ik1x5xxjmv    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Layer_Map"
    ADD CONSTRAINT "FKtaw5vu9h5m0aw28ik1x5xxjmv" FOREIGN KEY (layer_id) REFERENCES "Service.Layer"(id);
 [   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "FKtaw5vu9h5m0aw28ik1x5xxjmv";
       public       postgres    false    187    186    2178            �           2606    69576    FKtfx79xn6n2cjyfut6qjsom86f    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Meeting"
    ADD CONSTRAINT "FKtfx79xn6n2cjyfut6qjsom86f" FOREIGN KEY (room_id) REFERENCES "Service.Room"(id);
 Y   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "FKtfx79xn6n2cjyfut6qjsom86f";
       public       postgres    false    2202    198    196            �           2606    69511    FKthcd9eh23nwo3yreqgldl4bnl    FK CONSTRAINT     �   ALTER TABLE ONLY "Service.Admin.Notification_Account"
    ADD CONSTRAINT "FKthcd9eh23nwo3yreqgldl4bnl" FOREIGN KEY (account_id) REFERENCES "Service.Admin.Account"(id);
 l   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "FKthcd9eh23nwo3yreqgldl4bnl";
       public       postgres    false    185    181    2168            l	      x������ � �      m	      x������ � �      n	      x������ � �      o	      x������ � �      p	      x������ � �      q	      x������ � �      r	      x������ � �      s	      x������ � �      t	      x������ � �      u	      x������ � �      v	      x������ � �      w	      x������ � �      x	      x������ � �      y	      x������ � �      z	      x������ � �      {	      x������ � �      |	      x������ � �      }	      x������ � �      ~	      x������ � �      	   i  x��Q�N�@>o��0��/�I
�%B�X	.��I	l�
����{p$ėi�e܂,�w~6;�3��N 䑜a��. �y�`SQA�ѭ$�a�h�E���F�k�,�ĵ��} І���Z�N3S�A#�R���_jKDI��@x�"��'C}�rU��$��E]��~V�)��΍s*1�[�-�M�G���0	�e�a0P�==G	�"��DĐ2%�99d�j<���y�:��`@��*��?��"ZƳ��^%y]p�Xi{�'�<z�]Vw�Э)�4�U�"C��7�%��Y,",d��?���Jm���́ڈ#/�N
2�;#Ӛԝ\�+�󁪤!�X�Ax�"<����u�d���0�,$�      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	   �   x�343�44�4�70�7�T04�26�25�4����;o��츱�fP&X�i�Y��_©&a�1~\�ff�_R�X������4'�Ȕ$�����N}8��X�M�(�/*q�HN���G�#����� �]be      �	      x������ � �      �	      x������ � �      �	   F   x�]���0�P$\� �b�?a�Wf�>;�0��w�>B0�f������ܛ'�'= �&?eU��3/=W      �	      x������ � �      �	      x������ � �      �	   Q  x��]ms�����
>d����f����[�t�4�����޹#@��������W/�wlc�6M5��A:GG��R7͚a6��ݩ7�ܭ[�u��O��֮a�.�SE`��tE�Q�b��V1tM���z�<:�o�~��;��&;��j�����^��K��.TN��ſ)��{p�ih�fZ����rZ���U��>@0���M��OM��g���f��Ǵ��G �����8َB��}�@� jk�i]�%�?����}%�wؕ{�=��7�	H�qҁ$~��5��;
��!���>Da���cV�[w�ߖtF��1H�=�ة� =�B�Ń8�=Mܦv�n �}x��g��L9o5�W���'�����M���}xr~v��eB�ey^S�=�B�}"H@��e�Ƶ��~[��&]�oG���	~h0����g�Ƌ��VU� ����!���\�?B�(0��|�v�g��N\#p�uc���a+�~��8T[>����	�d�K�u�� ?��UlNa�ݎ��F�[F��.L:#8���es�Yo �~ܧ��I�}L�l<��5R/�ר_u�`����gvn�]4E��B?�cL�+;�7A{�����c�ȇd�{�z��j6�ܻ��� :�(!8� ����d�B�S�Y�HLq@�=��e@��
��o��M�0h{�����Җcl�U��>&PK0�b�T,	����ؠ��"u�����������,n9�S�a�I>-���}�1&�����3�"����R׻��#�6WԞD�j�KZ�F$�f��EC�B�;�)��{�p�1�}/�����Q��B���Bq"����F�Oڱ:�*ܖ�������Y�eNQ0H�W�����F�2�;?�qG��$����,�rf+��M�LY�i��e�än͜i��^����\]�♤����k�A��¹Q�.�l�*��S�jP�� �	�Y.�4y�"���\�R'�f�a(B��Zi�t��(B8~�F�.Ļ�Q�.n-P�W��Qg� �>�2C��>uc�	h��D]���W����-#� �572��@�s�	%N�T�YqYR�QH�N|zEf�t~�e�le�S��)4B�R���ܨY}�Ks!<���ܟ��5T�
��	,��pJ���5�CI�$�k�����8��x��f�-����.�I���[�&]����j��2A"��6{����C��z�aYZ���#,JՂv"n�ϊ%X��T�L�wZ�M�����d񚋭g�Y�����Xef�@;f��c�c��%��eQ�L/��Ot�r��m
�:�V��K���2C�{�O6&1�����j���GcX"6'����HR����l���D>�0��g]���q���� 䀬��&�
����u$��~�i8�.�@鍧����|L�J��[�y�p�� ��)��ó�vMg*�o�Ko.��SϿ�Q�кU9�g���1!lO�m=L�)�j��-;�
�;H�=!��6�S�L��k��C�*}�J��b1B�g�v�8���7O�'K�P�Z��xg|�`|�`WbՇ��Za�<3쵵,��C8��<lw��N��Z��ړzFt�}m
�S���s`�Yrn�a�#M�HC4Ÿ�1
�S��u�����c-�;�b�3�>%��-Be�+_�37�{˅Q��SB���n;��������2K�"�h6L�؜�l��Y$o	����D;�M�u�{��[w��-*�j�2b�Ű>��vy�����U���^M�9u��A���t�ݶ����l��������?�1��I�x�G_�������/���p��a��y��O����?�� ���}���C��Kx�ӑ}D��K���k��|c���֦�]۠���t�&������h綵�s;ou�IO��6hk���~�&�t��U)���!EwW9`��J�hs��$%J��#{�K	~P|��Di�{�[�G��;���D��
�^~�b�S]��>`.O	cn�C�,xF���|/P�S���~��ҀE��g:�c�I�j0���=���λ&#:V�)^^+J�N1ê�Y1�Wgoȧ��O�i�[4�:Ľ^��[�4��v�84R6F���*���݆�`�k��0׈@B�Ơ\ (ҹ�W�L�67ʷ-�:K�J�u����k��K5+?)M�ǫ[	�d>����zmW'i~/�xu*�$��zO)M�/��
$q �����Ɠ�Mr��S�,ᣑ����g4<�Rg+3Z�RQD1S�z�s-O2�%S��e��P��*�%Dm�.��I&�+b�Iz���Kz���gJ	�ժ;�	Z�������")�".)�".)�".)�kk$E\R�_�QRĥ�H��O1 �"��DR�%E\R�%E\R�%E\R�_3E|�?�1���۽C~�x�ݟ��;B�-�|�H�v��x�r菕ht��.u�i���t��[�Oi���#�����<4	��aު]b�o����@�b~��ԉi_�ጀ�l0c�Xz������^��M�E�"����ֆ�!�%�t�t��\�1˯6FP�Y��L���\��3RS��ʲ����3�CM��g
S��E4 �U�IR�KÕeZ�]/�yς�P���~`:�Y�9U��H;Y�$fa��*��)xG��HH�EW�����L����E�z�<�.������ONo�M�i��5�4Mq�J�.*h/˨��io7<n[����cl��az-�0aC�����N/�܆�_��7�Ǆ\]�����3<�FW���S������^�s�!O���oEgs��Z�����7�9�nV}״��Yn�أY�ND�n�Y�3�1����Sng�l���<?�e�6P�f븥�ފ�j�MhE��?t�<>�l���j���omG1��ky�$2K"�OKd�cD�={�h!��Q�K 9g��s&�̒�,�̼̒j�T@ivZ�Y�}'K�$�YF�f�lf�f�lf�f~Al�+\(�ᖊfkә��GIg�tfIg�tfIg�t�_s $�����3K:��3K:��3K:��3�f:s�~̪���PeS�����[?�P%�ԝ����u%:��ȇ�m0@�=����ʏņ��)���w�G_���ՙ��F5�ż(�-������}�v�ȱ1�����V~���>\��+?�
��"A��V�a��}��^��\�3s�Y�*D[��=�P�_-��"�-`.`����'����/d���,|��t�O�I"����|���? ����JV���J�����,r��� �4-в�zc��I��*�j+���j+���j��������i�"�I�i���C2m��H��O9 �i��D2m%�V2m%�V2m%�V2m_5�V��L��H7�����͛�(�      �	      x���n+G��O=m�4�����G��$Q����.U��"�J.Z�梁��~������]e�.
�~�~��$�P�)����H"�c�b��(#~���o	��;��,
E��1�����z�����j��I�ז���%L��; ْ!��?�` H�����»��q)S�WM����˝v�a�oLk��4s����Z�h_hE6:f����[�L;W2�v#�?,3_��wƢ�r�^�*�J�*˿���� 7D�7�X;��r��~&o�V״�l�L4Z�?�w����^B;ɝ�R�ln�����n{�v".6����en���i"�^06@�c��A�yT��V;�ֻ�U\jɟ�tif�j�r}i�a���z�jT��.���y���o����ۿFz��~u��w�v��F��\�Z��Y̾��ݯ�;���d��LAR4��NKޙk&o�i>�^��<(� �&̷�7g�7�2��u�f"�^����]��d���Pت����v�{"V<�#��ɏ�߸��P~����o����ޏ�������=������@���i=,ݽ���:1����Nl&7jx}m7av��uZOծX!���ǥ�tu��Y�7�+��i[�덩=,X�d@C�����E�+(a�d�|���]E�''����Y�V��R�ut��:ŝ���n񠵗Y��l7�e��3���4n��j&J��ȿ����e+�:� y���7(�C���`ucD��#f�҈B���K����}���aj�a�jjG{ΰ�!Ρ�"����3��b��"�y�)%�5>��q��r{���Bb�Z-���x�R�M���]I�n��*+���s%�����fk;_:X��L�&-ꉒ���Hu�n���Or�f|:<�  ̈ @�X��QC�oZQ�F@'�u�j����e5����qh��/T5�k�����������ۿ�"�&���?{�<��`p��n,�8L���#��f�<6�[�v� ��h�_�;����+怖�GVKV�fF)���06���g
�|���E	N��k�&#y�[��-��]̘�."��0CQR�9� ��*l� �p�
7W��m޾�,l�&�N��r�.�ie��q��^Y)-�4�d�,e�mn/\�K����"pH0�=� Q1���nJ���ȏ�q�	 >�9�%�Ht&uE�@G��\�����ͪ��ݟ5Bӱ1.EGwP��:.(��й@�4��?�h�6:V:�jE>��q��N���a�?{D��T@m��ui��{�gZ�l#c���oz?���Az��8ȻT��#��=F(�ȓ�O�D��^˫�MyY^��1�����o�WMi�򟴭��/�4�h�s�'��������xϹ�z/�߂��H������{�:xP4_�m��'�k?���~���߾���ޏi�������i�C�#L_�	�P��4Ov�X?7:W+-#~�?�95�ډy�8�V+b�7nίS���us��r��L�i�������qג1�PsƠ����Y-����#�\a�(ᜎ�i�IoPU�d5���~�s�
�-��s.\:�<.�( 8����vP\�P��\(�MP7N0G�*���h�K�r��r�+Q�J@���Y���U>ag[��1���?z߽��>��5.��ƀ�253(Q���k�23m�����Cg�[���zߎ��`4��q	G4��{��5CG����ڮ{hx�{�(H9J8��gɥ��$�c�`�fȦǛ�{������h/��g+�!���ߣe�15���Y�W�{'�3[-�:\]���ߺ��z�4x�~�~�^���t2?xG�R�Y\��;�&��qq��V�z�ȏ?�N��t��Ҏ$�P[�L@���f�f��4�P[׌�p�7���Kt��8/t�0:�4�b��Ӿ��� ۗ�b�V&��J�~�N�\�n�|=��0:.]R��5uø����/����j��3cNi�=�=O�g��׉����#��ǈ��I�goI�F�ƙ�l�٧כ�*�z��:��I��ح�<�=:A��ZbO��z�Js�� ��vfO������O�hx��D_��K�u�p�R�F�������[;�
��~#o�3�;O������W�	��������B:��������__�_��M.\�DuݜsD���ps>����[6�G�6�(̙7�nΡኑHP���E4��fo^\�� ����H�BE'tqT�8������j�d *��6h�?������	�ф[v_���F�i��� ǌ�/�,�3D!Y�$�������0Ͳ�z
��VY}�p��6;ڮi�f�	:���L���,�A���!pȎ젧k�A� 2��a�"�.;�r�Az�����\��c�9޼*_�'jɋ���^��f<
�V-ϻ�;�JZ�����u��S\�W.�h�1Cۤ/1�4\��02�������Hv��i��ΰ�͒��2������9����܌ e���[��	�f�R��h�VQb#&�N�^��^��~C;�鳄N��;d��\�����������l������軏G�{��Iph���@�� C���Y d@�(�hB]�
3���"���ii��3|c��{3B� h
�;��j����0`�F������W����v�ਬ��rȍ�ց"A0A'��|�b��K�.��%�����K�z&�.w_2`�o�c��G�u#��v���������Sa��!��|�F�$|�5��!SeȘ�%���kWB�!���奖��Q9*�(D���i��ay��}���#�n���1���#��ҵt��Ɔ��]�C��:�MWv���0������-�?GU��z�Zn_�)0�7��6�"�0����*���#5�dW�.A��&W��ަ���B����Gx�T��ҴM�j7��qvq��w�P����n����d�޿��p{���Ͻ�$b��7���`o�ھ`7��}�U����t�+�eO~Ӌ8:�v�I���5.H!�0�.44k:�5��EtVJ�\Y��G���}�������_��������_����� a�ҡ��D��D��R���F��s�^�ϊ�?|5tLd@�Qq�5Dԃ}�l����=�l6�d�n�ȥ1i_Φ�=�9(�y�S�F���%����^��j_|g#��%�����S����ఓ�L����dEh.fY�,b���"�3�&79/d�B!��1��fAlܬ�Z���=�l�,�8��+k��d����M���>�Vλ������n#_`,���٬�.a�n��0B�ׁ�A�jh�-@@]���l5S��ۆ+����!�pנn�����Σ��]J��G�����K�R�҃ ��(D��<�	E��)������㽿�~���|��T� u?VCP�����o �bH՟W5�}2Edu��[Lm��wO�$��)t�����uʂ�yc�x����t6w��uu��W8.o��OƮ�3�1� o60�d��(�'c���Z*jI	Cx���!%o�ĥ��N�����!%#S���$%��8������6�dl��47��	*�3	���k�5��������.�k��Ʀ�¨�5F\:�E�cI%'Npp��u�ꉙr])qis<F�
D��Z�Ǽ"9�/�Pɫ�g��)9wCG��U�)%�!��24n�H劆�*�N�"������g��p|亡���͋(�,3�~�ppmNqZ��ܓSN{�%9�n��MC4�S�@!}�B����*nZ��B�����mZ�9�'��u*�0�/�r*��m�J�'Zf�l�z�*��3��N���_j���Um����V�f�����_5>�?	�ϓ�sK�g��<h���'B���5\��b�Ф3-�1i���?,6���2���>��ư&�]�oC��x�E���e����{2�C:�X� �xKB ���	F^�FqnW~�@�Щ�A��c TmGp���Msy�Na���5mp�J�u�T�~�zβ�ǉr��Jz����I�lǬ�{���F�4�����n���uT:*
eЮ\>fJQ��=&b
IW�:��(Ie�.�V{$&�=��0#    �1v��:��$%��c�CTY���ť�j
���N�{��?mt�;�n�Ո��3>�A��� %�}�Vr��O��h���g8���{�t-�6�O�T�)mV����1�e�����'��������xϹ�z/�߂��H������{�:xP6G����˛��wd�0D�K�1�mU58lɞ!�g�^?�Е�4ZlWҧ�}����_�+;�݃��֒�h�N|s��z"h�R�W�����n.�����%i��}��x��5�S�<�!���- B�ǻ5����>�9(T�>�u�4y
�L�?g��6�T�B��������$)�v���P�)!)��!2"mk/�^�T���H��i�4pY"�VE���ʍH�Z��V��X^XN���5|�MX�Bku��FO�;%����z�[_7JV� ���hl�L����=7����0e՘3�jM�%Q-�!j��E�fd;���x�':'%
���Ef���Aŕ��=(
��f��N}�QY��=�6}ۇP`0`�+Uj]�����w᛼�[����k�f�^Ѭ�V����(�^�f���f�W��ݏ�P.E�m[P �l�(}�r	w�%&h�P.}�\��}����R�K��.�B\�+��R�K���
#�3P.uQ��.2.殷����Tz�pW� �C�z@���Z����MӬ�.e>�OkH��{)+t��Ωܭ�!�\��怩<�\u���V�?q��&��qCI���l�Ŀp���Bݼ���O�ep���mQ�=���/���דD�P֒"�$�z��#T ��&���rj>i����@}�7��+�*�P�,�G� 
m-��
��Bf�)M�-��!�<�H�&1��n���Rl�\�>��$ 7�k3�M�D��?ns�R-�����?z������_I���J�t�aB�L��i�a��s���v�"ur��(�5s���o�l0NC���(�]#�a`8UE��8��ܼ
��`��uО�	�M�lM�+�F?��-�C�����}S;�~KlC���^8?��Ec3��mt���i�U�p��ƹuO����Fv�Ϻ�(o�W6.��
���g�?����/����}�p����B� ��X�z�I~�]�j���4�#�ƚ� M���T^��c-2\����Q;G�m�	� �.|��z�]����*c>Σ?�.�UN���M85���|�8��8{�~ͮ�>ξ?�B���M���@W|sq��S>���9��m|�s6��7H;��P=H���
՝�>�l	�Iw�(x��7M:f�*��g΄v����k)[V�ZJ����7�	M}���\ڇJZ<1�>}Gpi,x�v�o�� :A9�4�J�K췣���/�����������z?��C�������!@\2�9Q����cXC�W.�ܡ2FY؇@g����1�o�*v�Qe@5�FӴڍ�<��z��W���9��g������d]��P�a�[��{=�}/�����������+v�e�W"�OFp:���=tv�	6�
�
�ے?j�6ӊ�7
؋.V����f��i%���{P՞xW��3ĎS[�D�#n�N��^a�����rfJ���z޲��	�3�l�f�>!6n�h�v�鞋J�Co���SY�{]b�sp�m�f+�������Z��/0_��lV}I��ߺ9%]?�|�����)L��p��>�.���댞���z�n�	�#aF@��C5U���!��L:t�~N:��8lp&ܩ���G��D(˒
'|�	w�W$�����c��A�s�8�ǈp�~%�m�Z-oF���(�p	�,I���S�#r@U��#?�����I��|�cvHO~s!!��=����㕳����z_g㢼����-?-���pͧ��z(4#���3)aU�Y� F>�G��9tI't�6�o��b��tGɧ�?�#���H��w���"3H�A��d�p�����u6����<����%=�ɵ1ۼ����+��b~��ӭxe�r����J���.ME��G^��T0UY��wϦ0�Sr�\�n�O��`h��[�g��s��t�7�)L��i^dP��*�\o9�XvF��>s�o
�χ�YJ6~d��B�;���a\R�m�0����\z��99P��ǽE��ȕ�CG/��*(<�$DeT\b�DEۄW�
��Z���d3�J��B���JиI?+��}tX~�\}'��o�?��`wI��kU�@��b�%i���'td�y�S�|LAC.�LBw�44d�*�(/�L�Ί�|;Д�;;,s�o��}$���_~�rç��1�42�c�{J%��a�E�z{;�.�k��i����h%G���R��IVӫ|�Jג�܆�����jn��ݬ���u����"݈�Rۇ+T���z"hl���N�]����~�� lx�4F��� �3V��G$@��`������d�`�A���$۔@���� �f��c`���#f�n���Gρ�{����(]�G(ƹ��F��,Z�Ns)o�*ޝ��ף����5d��j����0^qy��x}!����،כ2?�YOUD3���6�Gڹ�}f ����4`A�9Y"��_��(<�=dI)K������3`		DG���%4�(e���#Ȣ�;�p�����d�����a�K���a��;�JJC<�y;��������\4>�;�=��5i�f
3!C;����������r��d��Mf��X��"(H�(���C�ƺ6?�A�h�ڱ�N�}����H���}�!�a���N�^�<E����f���=*vl67�2)����]+}~f�a��p���n)���4Z���Co�e�f�6J�d��Xs$��Ϳ�D�Tr}����qp4AIě[RL/),0�x)xz�B|�L�-���J=�92j���"?�X��*S�X�K��:��0je�-�˕����%�Q�k'���	6L����J��<�s�<�su,iV�>��,e�.aPB�q�"���ci��Nu�jLND�l�\�����Fs��	(�w��{��rڵ��up��6�f�n@ܖ�>����m�G��㷯#���Ovu�t�o���o�g��@�1�Ch��H���g�&P]R�6�Cӣ���!�2�hy;���ʀt���ipm����AO�W�z���1yD��a%B���O!BZ!��%Bw
�n̓l�����7��_��ys��T߮ffp���}��¾���N+�����=�P��Ja�y��:W�J�g�3��l���P�X桇C�.n0C]k�0�;rx�y��P���#1A;�p}�e}�а5�(�[r!Wɇ�ݩ�w���qm�[���J�h�0{�>��tz�c�I��s�FK~����d�Ӄ�J|'�mŷ�w6?TNq��b4Ef��������]?/�4���Ʌ�����a���c�<�L+��y����%nj�y(��[Z�B�w� Xi�!�E#�y`�%�s�s|8��(<: t(n{�+H�;K����a�\��ٙ���/v�Н%�C_Z;�hώ퐦v�R�:�2s�c�K.�Ww���֚�i�pT�	퐖vȥS��a}��,	�n�;K4�Kz��1�La���4r۞�㙛e�]l��i��I�W��G�����V�j�{Z�˯�uj��̮Z��|�N���.�v�j�"���?�����*��
�v^�3&�h�N �gК%�碹�<�!/=O�<N�Va!;�#'$h��v'\�4\�0�bI��!9gDa�SȒR��b�Z��P�;�ǚ�b�Z�#X�kj��|k}��p�!}kM�W|S�Μ���y`�#�B��	��Ǡ��}C�i��'�"����[�z�Z�,2B7d䲙� �<��E��E(X<�Q����Q���3ǉ��������^����l[)P��c���R���
��-�ڙ녕l�[J��s'�\�&5��֏y`�+G�����G'hy��� 1���C����H(+�9
$*�,�ڔk�7Vn���ڤ���pN ��5e�k��5K��,iʒWB��, �  ��)K^I�Z��T�[
YR$D^Iɚ����DE��	�˴a�+1Q_e�s*_�k��k��~�5KHeK��%�,y�Gz��i��i^IZ�ChX������f�)<�(dIi�(�(�4q��#r�~?�&
`�? �^��B�*j_��Į3e�����;69�9TX$q&�[̀<X8�1�$[���#1.f����ކg�N�q�m7v��2O�G�NP����G����Ҝ%/@b����x-�:���<����%5km:	�a=M��%5k��­��,/�Yk����C����%=k͒]n�uئ/�Ykv
���i��Gm*ҷ�G�;�(�~�	�#&�b�Z�M��|���9L^]���o��:���@G:A�����lLzw�M��xDA5'����K�)�ob}�	?F��xD35'�TRhO�H�G�A�:D�����=yZڕ��)�N��,	�}��e{*0a��Xe�w�+`�wא�=�C���!���~6o B����G4^���8f\G�. ��'tn�$� ��FOA��w;�REj�{�buv��6Bf�-Ћ5��<���y�2��ǥJ����(V������j����]�� (\�tYΜ2# Z�� 5B�Hcc�������ݧA�WT�j4M�-��l&W�4]P=|Ct����\�2��6}�j���7�����>��K���w��o��[�����ھ _��������K�Փ��/{�^d�9�CL:u'�Imu'�$�X(cr0�6npm���M8{�Ƚ��И!䔟 ���9�(�H]k��]��6M^(F�49w�T}��V�钿�tN�JB�� b����õT˵ԩC�f�AL��p-dRK&���dRߌ*�)�X]ϣ�I-�t�rƌ�4�C�^�U��,/�L�Xe9*�4~�������w�ws�������#�^$��mC�%S���M���
��kCǴ5L��0�"4L#&g*����9E��(���o3�m��D�]'BQU��J��*D7��3�0c=�i�����wPh=��i�^��7���#�?K�+��<W���!P�B��+:1a�vS��0��F��w3+����j5KS�������V�J�ӕ�������I���E������J�~߻�iG;���v��z���_��B���!L�ڤ�� 4���]3),�ͮYm4�?���ȧ�?����߳� ��6~ĩڔh�]_RB�'Y�Z9#s2v5qi3h�ޞ~P�RU�먿�M�e`@q�)ڃ2A9��N��W�N�KT�:�v8f�"Ui����N��t�Kl�W,IvUx�˳FfN�ط[��*�dG����-')U��ፆ[������g�@���G����V��>^?)����#�.ik\&9��P��jB�K7���ͩ�OY7���z����Ή����Z�9�`.�ǰ��'Ta(^�����3���+/?����u�\]^b��~���}%�4���!%?�-V�0CԸ�pI�����Q��ݩ)t��.�ǈ��0얾��)-r]5p�|�u�N�q����Y��$�f�4�9%x�sx��H6]V�@K`NM��b ���Pw2Ҭ���CqV�f�n�-g���.��2;��ݳ�����Msa�ts�_�&�A���`ow�ju��^�m�טS4���У�Μ%J�՘S��	>�qպ&���v���Ĝ*�M���6ԏ��U�Z`@q����$Ym�+��d5�T�m��9'�m�Z-oF���(�p	�,٧�=�����+;Ef4_A~s����+�".x%Y�0���}��lԒ{�[+�Zg4�i�p?)L��:BG�y��.��R�!ooJN G�1i��#�?x<�� ��"�rP���}�����C<A�Ή�0Ħ�s��H (��1�[�Ϥ|q�cJI�@��1Fb��6kU��6<�uҍ�n��[-�yj=zt����Ğ8������,y�̞(�k��9����aȱ�kB�D�3l��vV�v�7�������)}8# 6W[�T�f�^(ķ:(��_ߨ���!�4N��M�Yř�\�rgj���	���]�S�9%T:�spl� �{����Qg��Wl�w���1�]3lP��%��t�9}�D�NSnP�!�ع���oU����%��� �3:�����3�4Z���~��%��      �	      x������ � �      �	   Z  x�՗Ak�0���_�c�=�I#�T
m!M.��ZK�l�	m误v�&Yo`q c�������(�!yW�+�W����Avs�]����UU�����w��}T�W
��3�,��g˧����*^��|�TrYur�����'�Z�G�-���S��n�����
b����ZT������U;�Vw�����[��0 j�H�S��3���5��٢ͫ(6'�X]Y���[k�3�~��U�L�Erݳ0"8���}�	h'Ƣwݱ0-97��p���Xl]�X� ;����*a@���u���b�b�'�P��Y$�=�I�xnX#U?I���'Уk�R]T)�#5b
<S�?!ه�.g�!���.�q�>���lC��P��K�{�o<{�Hy�$2����׮29�Թo�ʣD�E�E��R�����U6G��K�={�ȓ�bߑH���h��D�U7�y|"�_#�L����,4�n�+��6���ԺQ�z�±'��,X����ڎݻޱH���͎M0��ƛ\js���=Z�Q�z1G�򊅱UM��Xt�;ڥ�Af<�U�����uǂĦ���??	��ڟ_�z˂��f|�����?���R      �	      x������ � �      �	      x������ � �      �	     x���]o� ��O�~�W��w۪M��j궻Jsh�f��8����BR"���������k��o�o'˷��7���|q�X������tr7���k��+���_��a�U�jUUB��#�P�ծ�͎���`V�~�ȯ�Ӭ���m+j�5�����Vӱ#�� l���a��*#d(�\7#�9Q�'��{�8�֌���c�i��&�{��������An�o� �Z+;�2�2 q�dn��S�Um��Vh*~�"�gE��>�7�w�7EǵV�~�Q�E�/�[���X����~�0�L*8�)hj���h�Y��E�ڍ��;q��kŢ���1�*�fk��+�U�c�F
V�^�"��dY��՞��2A��4��/�v���Zn�1w��%��u"�Q�����Y�Z��l
��@�N>J!��"��!���$�)��K���A9��'ys��6Zm�n����E14/�Ã�Aj�;�ܵt�[��EV]�IƓ;+T�.L�~�n<ȕ��T���������J��-�P�FFc'���[���� �b�%�/����^bh$�N��M��on�z���T�����t��vRp������a{֚Ү�;�y�@a�n�$l��Ϝ5��I׉䉆:+u�t�׃��+c3m�,y+�I�1�$����C�Cgۥ��2;W�I��j�h�f@�~����<^5�^���C�r"�Q�:��2��/h۫�Bu&D4�tbf��f��'�h$E0əH%'~�V���œ	��D(ѕ s����c�4'<tA�4��:{�Nйۈ�5w�~o*�@[���m��s�oI[�#O�}u(��Z
������޳�%�j�ūtVR�~���6�����_��&	�Gΰm���L��w��^},��z�E������҇Ej���L�_��2?Hu��Ԣ4��=�wQ�������y�Ǖ#w��M~|���%����C���s�㑓�u����gG�%�ҁ�JlT�������?�wx�      �	   @  x��UMO�@='��W�����C)�"U�^"��=qVػ�z���r��RE�z�Oq�?�Y�		�&�"	��̼�y�y�m9�����C�q�N��Zvkt�2"%��1I(��2H*E
R	I�W'���t����Rp%El��	�rI�:��&�@f���ѾuLxVC#9�{�9���r��u����rm�PG���"!
H 8�@�#�OI�T��v����LAB8v��� �A�J��F��_\��R�e@l|;��6�P�d9��� =�z�����}G��cPԶ��׼��\��+��N����	��5�8��}.~Xş���K�K�����l�m��u�nϳ�O3�;?�!��5CV�fCj1�F!1E���B%�H1����tPT�<%�8��), ��9�+~[ŷ٧�����~sI�H��E$�R�Oh��̡�^(�=U�z��M��zo�E�s�K!`�5�6��c\]0��sh�8ϓ1�2 dڹD!�����m�V(7A�+���K�y�J#�C����HM����V�5��8v)��%yKu��5%%4��u���mt��\�ej<d"��V���-		�_1Z[�JIZz�����7ؠf�n��z���z�3����EvD��<}�]Vկ���ܼ�h�7V�	���;5^� 	�ңLE.�h�=�<Cp���ŧu��/�Q�F��\éBY�/�	�5	���w�4	+ŞIò��s����ʿ�rA�S��b\��r�!4�z�b\ �
!\C,R���S�ĭ��Y��8�Q����fjĝ�ʅ����!� �g�WS��7�0��0���r�L��i��� !�.�      �	   	  x���{o�8���>�}��b���u�v/�t䱷,`(��*K�,'���Gr��(�5^��VCj4�!9C��Ώ�'���x�.�1�}:9�t:?:��g��ǟN�����ٟ�h��[�E����I��<*�ݏɆd���K�p�d�4%Z����.���y�l�$�\���,�4%�ych<�,��05 ����m��԰|{�����9��X�_�c���Sςˁ���4����,&?��ᪧ�ᦐ8=�MJea�䗟��kR^Sٟ�Ru��Vu[(�@m>�a�g,���e���bcv�Ƿ������,Z��-$��]I6~�#�1;LU�����E��&��G���| ��.9�&�I���hk��g&���7�*?�u�%�
�~c�&��I��!�2=�}F{;bL�(Iw&�)>)�&�XT���o?
�'���y���	w"�M�(HS�*,Q��>�dh��V���+Mb�ӿaT:�$�xl��-g�xi���nٵ�W����?��������Vd�
#�G�<Y7V��С���"����Ii�8Q��z0�̢n�Z���{�J���	�q���i0�}s��� �}�f/ۯ�\��т���(ݓݟ6�����Cie���	��@j��KQ�S	��բn�p��5�ѺF��28Q�<��������6���j{���O���,���@�ה����������\��]
�ǒF'@�i�;z@��+��p�S�y�Y�oK>Jg�J�&�wW���H�����k����w�'�Ӕ�Y�)'��ɐ�F�eA�[�Ir�%��c�FR�L6a��f�HWM�,[dJ0�K�1� ��Yؤd�X����E�{�^�@r��H(Pb�B"`D}�o1h���$�B$H���#���q�`�L�y�&���� 5�!�	��,Ba
���[9����� �x�r$��bb��aL��$F���7!�t�����8�Cj�C	��e"ks)jo�R��Jh��Qk�M4,�*��&��D�2y�$�k%�Z�"�\�frV1b��u��}Y�����R�5BLejB�H�q�筧�x�Fv��z��5a掖��E�'+���������R�s�H�.��vQ�UA�l��2*�I�{c�a�Z�����\���QUk���
ca���X�:�C<�傾3 v}��l����z3�B�n=������ˠQ�	ݖ�*�=sS�j���S��yJ�`�⩅d8ؠˁ���E}C\2����Z�Q���rSa_��Z{����ͳ������	F��nW@��N�1����h�ڢ�f��N�M`xCb�k���m��3��V.�펗��׽�0�zywzx�D�0��Mg��z�b�����,�cm���g����'U�� ]�EZ��I�𚛰�n�&Gm��.���R�%�%��X�����ڽKzn��;�$#E�Nn�u�,�K������_:Q
��K"֎��$[�s��H�[�p�-�*t�U�{�ў�5.�[���Ĭ��$^̃��3����rK�n��C�/p���r�����H|k����׆i���� �;|0{q正Y�i�z�r)^�ru}b��їC��t��������9��n�9n�����s�6y�O��#|�%�� �x�A(��l���/Y���@/�	����?�J�?%�F�N�I�p9��8�x�x��?�=�#�P�]�oIQ��M�+�q�*l��OvV���(%��e��L����c���`+v��=��´FzY��.Ն�:`�9:���m\YR���uZ j�B�tF(���,٪�P��8�u�{^�?��A����O`��*��O��hq�a| �8��-�y^z�!-��BY�Z���f�y���C/l�:��s�X n����N&��h���j����:�9�qvWj�?/��<��]�_+�+��r�C� �P��J	��"X��y�Y�y}�h��o�`P�]���	S�cA0|�7��U�a ��(0ѱ&Cݓ
��4 ��!�3l��>���?�i��a�� ��!�¿5��O�����t�y�^��vhz����~�v����Ԁ���5��e�3z����y�ײe�q~1�H�կfA6��Y�54�x�HV�����QN�N[��Տ]�.�����)����R��c*�"%?�=�N�ց�s�ꪻ��G,��>����s*s=^Nޜ������6)�a�$�$(\�=
Z�#�\��D��1�<��	�ډ�)7�,+!©e��vnY�����`?g	�v��,�U�r���h�+�wX�M��7m)��6��N��H�˻�o��sL��� sK>��yҷ���*��(6W�W��[�f0��X�_�o�#V��u���b�Bۄk�?_+��t��e��8_��΃)���r�?b%��}��~�?���U0�֙�����c+I�wV�F\���D�@���Kְ+-�I�����*�2k�h&��Y���e��cNX2ut ���9C��hI��`t3�u�sǠ9��K���J�9'��L5A�-q��`̞^����H�Tv�S�d}�D�Sh�n�!m�r���{!�+R�e��Z�H4��$Zl2�t �
r��,4U���F&3h�l6(�V�`���dg�Z:����bq��I)�>?�O���M*3��`��)����h���oV��_��*fkJ��ʽ)s]ssʳ4�]�<�r$,D�93�!:��L;U�r�o!� Kp|��5	�����*      �	      x�325�46�2Q�\1z\\\ ��      �	      x������ � �     