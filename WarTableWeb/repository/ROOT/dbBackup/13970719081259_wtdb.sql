PGDMP     ;                	    v            wtdb    10.4    10.4 �    �	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �	           1262    16384    wtdb    DATABASE     �   CREATE DATABASE wtdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Persian_Iran.1256' LC_CTYPE = 'Persian_Iran.1256';
    DROP DATABASE wtdb;
             sa_wt    false                        2615    66866    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                        3079    12278    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �	           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    66867    Service.Admin.Account    TABLE        CREATE TABLE public."Service.Admin.Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "accountGroup_id" bigint,
    "accountModel_id" bigint
);
 +   DROP TABLE public."Service.Admin.Account";
       public         postgres    false    5            �            1259    66870    Service.Admin.Account.Group    TABLE     �   CREATE TABLE public."Service.Admin.Account.Group" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 1   DROP TABLE public."Service.Admin.Account.Group";
       public         postgres    false    5            �            1259    66873    Service.Admin.Account.Model    TABLE     �   CREATE TABLE public."Service.Admin.Account.Model" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 1   DROP TABLE public."Service.Admin.Account.Model";
       public         postgres    false    5            �            1259    66876    Service.Admin.Notification    TABLE     �   CREATE TABLE public."Service.Admin.Notification" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 0   DROP TABLE public."Service.Admin.Notification";
       public         postgres    false    5            �            1259    66879 "   Service.Admin.Notification_Account    TABLE       CREATE TABLE public."Service.Admin.Notification_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    notification_id bigint
);
 8   DROP TABLE public."Service.Admin.Notification_Account";
       public         postgres    false    5            �            1259    66882    Service.Layer    TABLE     �   CREATE TABLE public."Service.Layer" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 #   DROP TABLE public."Service.Layer";
       public         postgres    false    5            �            1259    66885    Service.Layer_Map    TABLE     �   CREATE TABLE public."Service.Layer_Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    layer_id bigint,
    map_id bigint
);
 '   DROP TABLE public."Service.Layer_Map";
       public         postgres    false    5            �            1259    66888    Service.Map    TABLE     �   CREATE TABLE public."Service.Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "mapRegion_id" bigint
);
 !   DROP TABLE public."Service.Map";
       public         postgres    false    5            �            1259    66891    Service.Map.Region    TABLE     �   CREATE TABLE public."Service.Map.Region" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 (   DROP TABLE public."Service.Map.Region";
       public         postgres    false    5            �            1259    66894    Service.Map_Room    TABLE     �   CREATE TABLE public."Service.Map_Room" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    room_id bigint
);
 &   DROP TABLE public."Service.Map_Room";
       public         postgres    false    5            �            1259    66897    Service.Material    TABLE     �   CREATE TABLE public."Service.Material" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 &   DROP TABLE public."Service.Material";
       public         postgres    false    5            �            1259    66900    Service.Material.Area    TABLE     �   CREATE TABLE public."Service.Material.Area" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    material_id bigint
);
 +   DROP TABLE public."Service.Material.Area";
       public         postgres    false    5            �            1259    66903    Service.Material.Group    TABLE     �   CREATE TABLE public."Service.Material.Group" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 ,   DROP TABLE public."Service.Material.Group";
       public         postgres    false    5            �            1259    66906    Service.Material.Material_Group    TABLE     y   CREATE TABLE public."Service.Material.Material_Group" (
    group_id bigint NOT NULL,
    material_id bigint NOT NULL
);
 5   DROP TABLE public."Service.Material.Material_Group";
       public         postgres    false    5            �            1259    66909    Service.Material_Map    TABLE     �   CREATE TABLE public."Service.Material_Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    material_id bigint
);
 *   DROP TABLE public."Service.Material_Map";
       public         postgres    false    5            �            1259    66912    Service.Meeting    TABLE     �   CREATE TABLE public."Service.Meeting" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    room_id bigint
);
 %   DROP TABLE public."Service.Meeting";
       public         postgres    false    5            �            1259    66915    Service.Meeting_Account    TABLE     �   CREATE TABLE public."Service.Meeting_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    meeting_id bigint
);
 -   DROP TABLE public."Service.Meeting_Account";
       public         postgres    false    5            �            1259    66918    Service.Room    TABLE     �   CREATE TABLE public."Service.Room" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "activeMeeting_id" bigint,
    "adminAccount_id" bigint
);
 "   DROP TABLE public."Service.Room";
       public         postgres    false    5            �            1259    66921    Service.Room_Account    TABLE     �   CREATE TABLE public."Service.Room_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    room_id bigint
);
 *   DROP TABLE public."Service.Room_Account";
       public         postgres    false    5            �            1259    66924    Web.Admin.User    TABLE       CREATE TABLE public."Web.Admin.User" (
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
       public         postgres    false    5            �            1259    66934    Web.Admin.User.Group    TABLE     j  CREATE TABLE public."Web.Admin.User.Group" (
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
       public         postgres    false    5            �            1259    66937    Web.Admin.User.Porter    TABLE     �  CREATE TABLE public."Web.Admin.User.Porter" (
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
       public         postgres    false    5            �            1259    66944    Web.Admin.User.User_UserGroup    TABLE     y   CREATE TABLE public."Web.Admin.User.User_UserGroup" (
    "userGroup_id" bigint NOT NULL,
    user_id bigint NOT NULL
);
 3   DROP TABLE public."Web.Admin.User.User_UserGroup";
       public         postgres    false    5            �            1259    66947    Web.Admin.User.Uuid    TABLE     �  CREATE TABLE public."Web.Admin.User.Uuid" (
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
       public         postgres    false    5            �            1259    66953    Web.Admin.UserConfirm    TABLE     �  CREATE TABLE public."Web.Admin.UserConfirm" (
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
       public         postgres    false    5            �            1259    66956    Web.Archive.Directory    TABLE     �  CREATE TABLE public."Web.Archive.Directory" (
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
       public         postgres    false    5            �            1259    66962    Web.Archive.File    TABLE     �  CREATE TABLE public."Web.Archive.File" (
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
       public         postgres    false    5            �            1259    66968    Web.Archive.File.Download    TABLE       CREATE TABLE public."Web.Archive.File.Download" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    status integer,
    file_id bigint,
    user_id bigint
);
 /   DROP TABLE public."Web.Archive.File.Download";
       public         postgres    false    5            �            1259    66971    Web.Note    TABLE     �  CREATE TABLE public."Web.Note" (
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
       public         postgres    false    5            �            1259    66977    Web.System.Backup    TABLE       CREATE TABLE public."Web.System.Backup" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "backupDateTime" character varying(30),
    file_id bigint,
    "backupType" integer
);
 '   DROP TABLE public."Web.System.Backup";
       public         postgres    false    5            �            1259    66980    Web.System.Field    TABLE       CREATE TABLE public."Web.System.Field" (
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
       public         postgres    false    5            �            1259    66986    Web.System.Irror    TABLE     �  CREATE TABLE public."Web.System.Irror" (
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
       public         postgres    false    5            �            1259    66993    Web.System.Log    TABLE     �  CREATE TABLE public."Web.System.Log" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
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
       public         postgres    false    5            �            1259    66999    Web.System.Log.DailyLog    TABLE     Z  CREATE TABLE public."Web.System.Log.DailyLog" (
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
       public         postgres    false    5            �            1259    67002    Web.System.Log.SigninLog    TABLE       CREATE TABLE public."Web.System.Log.SigninLog" (
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
       public         postgres    false    5            �            1259    67008    Web.System.Log.User    TABLE     1  CREATE TABLE public."Web.System.Log.User" (
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
       public         postgres    false    5            �            1259    67015    Web.System.Model    TABLE     �  CREATE TABLE public."Web.System.Model" (
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
       public         postgres    false    5            �            1259    67021    Web.System.Module    TABLE     �  CREATE TABLE public."Web.System.Module" (
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
       public         postgres    false    5            �            1259    67027    Web.System.Registery    TABLE     w  CREATE TABLE public."Web.System.Registery" (
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
       public         postgres    false    5            �            1259    67033    Web.System.Task    TABLE     �  CREATE TABLE public."Web.System.Task" (
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
       public         postgres    false    5            �            1259    67039    Web.System.Task_User    TABLE     i   CREATE TABLE public."Web.System.Task_User" (
    user_id bigint NOT NULL,
    task_id bigint NOT NULL
);
 *   DROP TABLE public."Web.System.Task_User";
       public         postgres    false    5            �            1259    67042    Web.System.Task_UserGroup    TABLE     u   CREATE TABLE public."Web.System.Task_UserGroup" (
    "userGroup_id" bigint NOT NULL,
    task_id bigint NOT NULL
);
 /   DROP TABLE public."Web.System.Task_UserGroup";
       public         postgres    false    5            �            1259    67045    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    5            �	          0    66867    Service.Admin.Account 
   TABLE DATA               �   COPY public."Service.Admin.Account" (id, "createDateTime", "entityState", "modifyDateTime", "accountGroup_id", "accountModel_id") FROM stdin;
    public       postgres    false    196   (5      �	          0    66870    Service.Admin.Account.Group 
   TABLE DATA               n   COPY public."Service.Admin.Account.Group" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    197   E5      �	          0    66873    Service.Admin.Account.Model 
   TABLE DATA               n   COPY public."Service.Admin.Account.Model" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    198   b5      �	          0    66876    Service.Admin.Notification 
   TABLE DATA               m   COPY public."Service.Admin.Notification" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    199   5      �	          0    66879 "   Service.Admin.Notification_Account 
   TABLE DATA               �   COPY public."Service.Admin.Notification_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, notification_id) FROM stdin;
    public       postgres    false    200   �5      �	          0    66882    Service.Layer 
   TABLE DATA               `   COPY public."Service.Layer" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    201   �5      �	          0    66885    Service.Layer_Map 
   TABLE DATA               v   COPY public."Service.Layer_Map" (id, "createDateTime", "entityState", "modifyDateTime", layer_id, map_id) FROM stdin;
    public       postgres    false    202   �5      �	          0    66888    Service.Map 
   TABLE DATA               n   COPY public."Service.Map" (id, "createDateTime", "entityState", "modifyDateTime", "mapRegion_id") FROM stdin;
    public       postgres    false    203   �5      �	          0    66891    Service.Map.Region 
   TABLE DATA               e   COPY public."Service.Map.Region" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    204   6      �	          0    66894    Service.Map_Room 
   TABLE DATA               t   COPY public."Service.Map_Room" (id, "createDateTime", "entityState", "modifyDateTime", map_id, room_id) FROM stdin;
    public       postgres    false    205   -6      �	          0    66897    Service.Material 
   TABLE DATA               c   COPY public."Service.Material" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    206   J6      �	          0    66900    Service.Material.Area 
   TABLE DATA               u   COPY public."Service.Material.Area" (id, "createDateTime", "entityState", "modifyDateTime", material_id) FROM stdin;
    public       postgres    false    207   g6      �	          0    66903    Service.Material.Group 
   TABLE DATA               i   COPY public."Service.Material.Group" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    208   �6      �	          0    66906    Service.Material.Material_Group 
   TABLE DATA               R   COPY public."Service.Material.Material_Group" (group_id, material_id) FROM stdin;
    public       postgres    false    209   �6      �	          0    66909    Service.Material_Map 
   TABLE DATA               |   COPY public."Service.Material_Map" (id, "createDateTime", "entityState", "modifyDateTime", map_id, material_id) FROM stdin;
    public       postgres    false    210   �6      �	          0    66912    Service.Meeting 
   TABLE DATA               s   COPY public."Service.Meeting" (id, "createDateTime", "entityState", "modifyDateTime", map_id, room_id) FROM stdin;
    public       postgres    false    211   �6      �	          0    66915    Service.Meeting_Account 
   TABLE DATA               �   COPY public."Service.Meeting_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, meeting_id) FROM stdin;
    public       postgres    false    212   �6      �	          0    66918    Service.Room 
   TABLE DATA               �   COPY public."Service.Room" (id, "createDateTime", "entityState", "modifyDateTime", "activeMeeting_id", "adminAccount_id") FROM stdin;
    public       postgres    false    213   7      �	          0    66921    Service.Room_Account 
   TABLE DATA               |   COPY public."Service.Room_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, room_id) FROM stdin;
    public       postgres    false    214   27      �	          0    66924    Web.Admin.User 
   TABLE DATA               �  COPY public."Web.Admin.User" (id, "createDateTime", "entityState", "modifyDateTime", "accessLimitDailyEnd", "accessLimitDailyStart", "accessLimitMonthlyEnd", "accessLimitMonthlyStart", "accessLimitTimelyEnd", "accessLimitTimelyStart", "accessLimitYearlyEnd", "accessLimitYearlyStart", comment, "firstName", gender, "ipAddress", "ipAddressEnd", "ipAddressFirstSignin", "ipAddressStart", "ipRangeType", "isBlocked", "isLogManager", "isNeedToChangePassword", "isSuperAdmin", "lastName", "lastSigninDateTime", level, password, "passwordDateTime", "passwordHistory", "porterUuid", status, "superAdminCode", "superAdminLogingId", "superAdminLogingIn", "userCode", username, logo_id) FROM stdin;
    public       postgres    false    215   O7      �	          0    66934    Web.Admin.User.Group 
   TABLE DATA               �   COPY public."Web.Admin.User.Group" (id, "createDateTime", "entityState", "modifyDateTime", code, "memberCount", "subCount", title, parent_id) FROM stdin;
    public       postgres    false    216   �8      �	          0    66937    Web.Admin.User.Porter 
   TABLE DATA               @  COPY public."Web.Admin.User.Porter" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "confirmCode", "confirmCodeDateTimeG", count, "expireDateTimeG", "isActiveTwoStepConfirm", "isConfirmed", "signoutStatus", "singinDateTimeG", "singoutDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    217   �8      �	          0    66944    Web.Admin.User.User_UserGroup 
   TABLE DATA               R   COPY public."Web.Admin.User.User_UserGroup" ("userGroup_id", user_id) FROM stdin;
    public       postgres    false    218   9      �	          0    66947    Web.Admin.User.Uuid 
   TABLE DATA               �   COPY public."Web.Admin.User.Uuid" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "expireDateTimeG", "singinDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    219   ,9      �	          0    66953    Web.Admin.UserConfirm 
   TABLE DATA               �   COPY public."Web.Admin.UserConfirm" (id, "createDateTime", "entityState", "modifyDateTime", "callCount", "confirmDateTime", "confirmDateTimeG", "taskSignature", task_id, user_id) FROM stdin;
    public       postgres    false    220   I9      �	          0    66956    Web.Archive.Directory 
   TABLE DATA                 COPY public."Web.Archive.Directory" (id, "createDateTime", "entityState", "modifyDateTime", "className", comment, "containedDirectoryCount", "containedFileCount", "directoryOrigin", icon, "isPermanent", level, name, path, "repoDirectory", url, owner_id, parent_id) FROM stdin;
    public       postgres    false    221   f9      �	          0    66962    Web.Archive.File 
   TABLE DATA               �  COPY public."Web.Archive.File" (id, "createDateTime", "entityState", "modifyDateTime", "accessLevel", "accessRule", "contentType", "directoryAbsolutePath", "directoryRelativePath", "downloadCount", "downloadCountGuest", "fetchManner", "isContainOrginal", "isTemporary", "lastUploadDateTime", "orginalName", size, thumbnails, title, "tryToDownloadCount", "tryToDownloadCountGuest", "uploadLink", "uploadLinkComment", "uploadStatus", directory_id, owner_id) FROM stdin;
    public       postgres    false    222   �9      �	          0    66968    Web.Archive.File.Download 
   TABLE DATA               �   COPY public."Web.Archive.File.Download" (id, "createDateTime", "entityState", "modifyDateTime", status, file_id, user_id) FROM stdin;
    public       postgres    false    223   �:      �	          0    66971    Web.Note 
   TABLE DATA               �   COPY public."Web.Note" (id, "createDateTime", "entityState", "modifyDateTime", "dateTime", "dateTimeG", importance, "isNotified", "isVisited", message, title, user_id) FROM stdin;
    public       postgres    false    224   �:      �	          0    66977    Web.System.Backup 
   TABLE DATA               �   COPY public."Web.System.Backup" (id, "createDateTime", "entityState", "modifyDateTime", "backupDateTime", file_id, "backupType") FROM stdin;
    public       postgres    false    225   D<      �	          0    66980    Web.System.Field 
   TABLE DATA                 COPY public."Web.System.Field" (id, "createDateTime", "entityState", "modifyDateTime", "dbConstraint", "dbDefaultValue", "dbExtra", "dbIndex", "dbKey", "dbRefColumn", "dbRefTable", "dbSize", "dbTitle", "dbType", "isBidirectional", "isDbFK", "isDbNullable", "isDbPrimary", "isDbRefreshed", "isEncrypted", "isMoNullable", "isMoRefreshed", "moAnnotations", "moDataRelation", "moDataRelationDes", "moDefaultValue", "moExtra", "moKey", "moMaxSize", "moMinSize", "moModifier", "moPreviousTitle", "moTitle", "moType", status, model_id) FROM stdin;
    public       postgres    false    226   �<      �	          0    66986    Web.System.Irror 
   TABLE DATA                 COPY public."Web.System.Irror" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", cause, "computerSignature", "httpErrorCode", "isVisited", level, message, place, "porterUuid", "sessionId", status, "taskName", "visitCount", user_id) FROM stdin;
    public       postgres    false    227   �<      �	          0    66993    Web.System.Log 
   TABLE DATA               �  COPY public."Web.System.Log" (id, "createDateTime", "entityState", "modifyDateTime", "actionType", "agentSignature", "computerSignature", "dateTimeG", "httpCode", "importanceLevel", "isTaskTwoLevelConfirm", message, "onlineLoggingStrategy", "portNumber", "porterUuid", "requestMethod", "sendDateTimeG", "sendStatus", sensitivity, "serverId", "sessionId", "taskName", "taskTitle", url, "userGroupId", "userId", "userLevel") FROM stdin;
    public       postgres    false    228   M      �	          0    66999    Web.System.Log.DailyLog 
   TABLE DATA               �   COPY public."Web.System.Log.DailyLog" (id, "createDateTime", "entityState", "modifyDateTime", chrome, "dayDate", desktop, firefox, "guestVisitCount", "internetExplorer", mobile, netscape, opera, "pageCount", "userVisitCount", "visitCount") FROM stdin;
    public       postgres    false    229   �x      �	          0    67002    Web.System.Log.SigninLog 
   TABLE DATA               �   COPY public."Web.System.Log.SigninLog" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "domainName", "ipAddress", "lastDateTime", "lastDateTimeG", status, uuid, user_id) FROM stdin;
    public       postgres    false    230   �x      �	          0    67008    Web.System.Log.User 
   TABLE DATA               �   COPY public."Web.System.Log.User" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "attemptType", "computerSignature", count, "dateTimeG", "isSuccess", "lastDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    231   {      �	          0    67015    Web.System.Model 
   TABLE DATA               �   COPY public."Web.System.Model" (id, "createDateTime", "entityState", "modifyDateTime", "className", "isRefreshed", "messageCode", "packageName", status, "tableName") FROM stdin;
    public       postgres    false    232   7{      �	          0    67021    Web.System.Module 
   TABLE DATA               �   COPY public."Web.System.Module" (id, "createDateTime", "entityState", "modifyDateTime", "className", "isProtected", "isRefreshed", "menuMessageCode", "messageCode", "packageName", url) FROM stdin;
    public       postgres    false    233   T{      �	          0    67027    Web.System.Registery 
   TABLE DATA               �   COPY public."Web.System.Registery" (id, "createDateTime", "entityState", "modifyDateTime", key, title, type, value, "valueJson", "valueType") FROM stdin;
    public       postgres    false    234   J      �	          0    67033    Web.System.Task 
   TABLE DATA               �  COPY public."Web.System.Task" (id, "createDateTime", "entityState", "modifyDateTime", "accessLevel", "actionType", "importanceLevel", "isActive", "isActiveLogging", "isAjax", "isLogManager", "isOnlineLogging", "isPanelTask", "isRefreshed", "isSuperAdmin", "isTwoLevelConfirm", "menuIdentity", "menuMessageCode", "messageCode", method, "onlineLoggingDelay", "onlineLoggingStrategy", "onlineSchedulingTime", sensitivity, signature, url, module_id) FROM stdin;
    public       postgres    false    235   �      �	          0    67039    Web.System.Task_User 
   TABLE DATA               B   COPY public."Web.System.Task_User" (user_id, task_id) FROM stdin;
    public       postgres    false    236   *�      �	          0    67042    Web.System.Task_UserGroup 
   TABLE DATA               N   COPY public."Web.System.Task_UserGroup" ("userGroup_id", task_id) FROM stdin;
    public       postgres    false    237   G�      �	           0    0    hibernate_sequence    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hibernate_sequence', 616, true);
            public       postgres    false    238            �           2606    67054 <   Service.Admin.Account.Group Service.Admin.Account.Group_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public."Service.Admin.Account.Group"
    ADD CONSTRAINT "Service.Admin.Account.Group_pkey" PRIMARY KEY (id);
 j   ALTER TABLE ONLY public."Service.Admin.Account.Group" DROP CONSTRAINT "Service.Admin.Account.Group_pkey";
       public         postgres    false    197            �           2606    67056 <   Service.Admin.Account.Model Service.Admin.Account.Model_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public."Service.Admin.Account.Model"
    ADD CONSTRAINT "Service.Admin.Account.Model_pkey" PRIMARY KEY (id);
 j   ALTER TABLE ONLY public."Service.Admin.Account.Model" DROP CONSTRAINT "Service.Admin.Account.Model_pkey";
       public         postgres    false    198            �           2606    67058 0   Service.Admin.Account Service.Admin.Account_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Service.Admin.Account"
    ADD CONSTRAINT "Service.Admin.Account_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "Service.Admin.Account_pkey";
       public         postgres    false    196            �           2606    67060 J   Service.Admin.Notification_Account Service.Admin.Notification_Account_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Notification_Account"
    ADD CONSTRAINT "Service.Admin.Notification_Account_pkey" PRIMARY KEY (id);
 x   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "Service.Admin.Notification_Account_pkey";
       public         postgres    false    200            �           2606    67062 :   Service.Admin.Notification Service.Admin.Notification_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY public."Service.Admin.Notification"
    ADD CONSTRAINT "Service.Admin.Notification_pkey" PRIMARY KEY (id);
 h   ALTER TABLE ONLY public."Service.Admin.Notification" DROP CONSTRAINT "Service.Admin.Notification_pkey";
       public         postgres    false    199            �           2606    67064 (   Service.Layer_Map Service.Layer_Map_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Service.Layer_Map"
    ADD CONSTRAINT "Service.Layer_Map_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "Service.Layer_Map_pkey";
       public         postgres    false    202            �           2606    67066     Service.Layer Service.Layer_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public."Service.Layer"
    ADD CONSTRAINT "Service.Layer_pkey" PRIMARY KEY (id);
 N   ALTER TABLE ONLY public."Service.Layer" DROP CONSTRAINT "Service.Layer_pkey";
       public         postgres    false    201            �           2606    67068 *   Service.Map.Region Service.Map.Region_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public."Service.Map.Region"
    ADD CONSTRAINT "Service.Map.Region_pkey" PRIMARY KEY (id);
 X   ALTER TABLE ONLY public."Service.Map.Region" DROP CONSTRAINT "Service.Map.Region_pkey";
       public         postgres    false    204            �           2606    67070 &   Service.Map_Room Service.Map_Room_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Service.Map_Room"
    ADD CONSTRAINT "Service.Map_Room_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "Service.Map_Room_pkey";
       public         postgres    false    205            �           2606    67072    Service.Map Service.Map_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public."Service.Map"
    ADD CONSTRAINT "Service.Map_pkey" PRIMARY KEY (id);
 J   ALTER TABLE ONLY public."Service.Map" DROP CONSTRAINT "Service.Map_pkey";
       public         postgres    false    203            �           2606    67074 0   Service.Material.Area Service.Material.Area_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Service.Material.Area"
    ADD CONSTRAINT "Service.Material.Area_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Service.Material.Area" DROP CONSTRAINT "Service.Material.Area_pkey";
       public         postgres    false    207            �           2606    67076 2   Service.Material.Group Service.Material.Group_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public."Service.Material.Group"
    ADD CONSTRAINT "Service.Material.Group_pkey" PRIMARY KEY (id);
 `   ALTER TABLE ONLY public."Service.Material.Group" DROP CONSTRAINT "Service.Material.Group_pkey";
       public         postgres    false    208            �           2606    67078 D   Service.Material.Material_Group Service.Material.Material_Group_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Material_Group"
    ADD CONSTRAINT "Service.Material.Material_Group_pkey" PRIMARY KEY (material_id, group_id);
 r   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "Service.Material.Material_Group_pkey";
       public         postgres    false    209    209            �           2606    67080 .   Service.Material_Map Service.Material_Map_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Service.Material_Map"
    ADD CONSTRAINT "Service.Material_Map_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "Service.Material_Map_pkey";
       public         postgres    false    210            �           2606    67082 &   Service.Material Service.Material_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Service.Material"
    ADD CONSTRAINT "Service.Material_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Service.Material" DROP CONSTRAINT "Service.Material_pkey";
       public         postgres    false    206            �           2606    67084 4   Service.Meeting_Account Service.Meeting_Account_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public."Service.Meeting_Account"
    ADD CONSTRAINT "Service.Meeting_Account_pkey" PRIMARY KEY (id);
 b   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "Service.Meeting_Account_pkey";
       public         postgres    false    212            �           2606    67086 $   Service.Meeting Service.Meeting_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."Service.Meeting"
    ADD CONSTRAINT "Service.Meeting_pkey" PRIMARY KEY (id);
 R   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "Service.Meeting_pkey";
       public         postgres    false    211            �           2606    67088 .   Service.Room_Account Service.Room_Account_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Service.Room_Account"
    ADD CONSTRAINT "Service.Room_Account_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "Service.Room_Account_pkey";
       public         postgres    false    214            �           2606    67090    Service.Room Service.Room_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."Service.Room"
    ADD CONSTRAINT "Service.Room_pkey" PRIMARY KEY (id);
 L   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "Service.Room_pkey";
       public         postgres    false    213            �           2606    67092 .   Web.Admin.User.Group Web.Admin.User.Group_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Web.Admin.User.Group"
    ADD CONSTRAINT "Web.Admin.User.Group_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Web.Admin.User.Group" DROP CONSTRAINT "Web.Admin.User.Group_pkey";
       public         postgres    false    216            �           2606    67094 0   Web.Admin.User.Porter Web.Admin.User.Porter_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Web.Admin.User.Porter"
    ADD CONSTRAINT "Web.Admin.User.Porter_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Admin.User.Porter" DROP CONSTRAINT "Web.Admin.User.Porter_pkey";
       public         postgres    false    217            �           2606    67096 @   Web.Admin.User.User_UserGroup Web.Admin.User.User_UserGroup_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "Web.Admin.User.User_UserGroup_pkey" PRIMARY KEY (user_id, "userGroup_id");
 n   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "Web.Admin.User.User_UserGroup_pkey";
       public         postgres    false    218    218            �           2606    67098 ,   Web.Admin.User.Uuid Web.Admin.User.Uuid_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public."Web.Admin.User.Uuid"
    ADD CONSTRAINT "Web.Admin.User.Uuid_pkey" PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public."Web.Admin.User.Uuid" DROP CONSTRAINT "Web.Admin.User.Uuid_pkey";
       public         postgres    false    219            �           2606    67100 0   Web.Admin.UserConfirm Web.Admin.UserConfirm_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Web.Admin.UserConfirm"
    ADD CONSTRAINT "Web.Admin.UserConfirm_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "Web.Admin.UserConfirm_pkey";
       public         postgres    false    220            �           2606    67102 "   Web.Admin.User Web.Admin.User_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public."Web.Admin.User"
    ADD CONSTRAINT "Web.Admin.User_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public."Web.Admin.User" DROP CONSTRAINT "Web.Admin.User_pkey";
       public         postgres    false    215            �           2606    67104 0   Web.Archive.Directory Web.Archive.Directory_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Web.Archive.Directory"
    ADD CONSTRAINT "Web.Archive.Directory_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "Web.Archive.Directory_pkey";
       public         postgres    false    221            �           2606    67106 8   Web.Archive.File.Download Web.Archive.File.Download_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public."Web.Archive.File.Download"
    ADD CONSTRAINT "Web.Archive.File.Download_pkey" PRIMARY KEY (id);
 f   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "Web.Archive.File.Download_pkey";
       public         postgres    false    223            �           2606    67108 &   Web.Archive.File Web.Archive.File_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.Archive.File"
    ADD CONSTRAINT "Web.Archive.File_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "Web.Archive.File_pkey";
       public         postgres    false    222            �           2606    67110    Web.Note Web.Note_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Web.Note"
    ADD CONSTRAINT "Web.Note_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Web.Note" DROP CONSTRAINT "Web.Note_pkey";
       public         postgres    false    224            �           2606    67112 (   Web.System.Backup Web.System.Backup_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Web.System.Backup"
    ADD CONSTRAINT "Web.System.Backup_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Web.System.Backup" DROP CONSTRAINT "Web.System.Backup_pkey";
       public         postgres    false    225            �           2606    67114 &   Web.System.Field Web.System.Field_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.System.Field"
    ADD CONSTRAINT "Web.System.Field_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Field" DROP CONSTRAINT "Web.System.Field_pkey";
       public         postgres    false    226            �           2606    67116 &   Web.System.Irror Web.System.Irror_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.System.Irror"
    ADD CONSTRAINT "Web.System.Irror_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Irror" DROP CONSTRAINT "Web.System.Irror_pkey";
       public         postgres    false    227            �           2606    67118 4   Web.System.Log.DailyLog Web.System.Log.DailyLog_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public."Web.System.Log.DailyLog"
    ADD CONSTRAINT "Web.System.Log.DailyLog_pkey" PRIMARY KEY (id);
 b   ALTER TABLE ONLY public."Web.System.Log.DailyLog" DROP CONSTRAINT "Web.System.Log.DailyLog_pkey";
       public         postgres    false    229            �           2606    67120 6   Web.System.Log.SigninLog Web.System.Log.SigninLog_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public."Web.System.Log.SigninLog"
    ADD CONSTRAINT "Web.System.Log.SigninLog_pkey" PRIMARY KEY (id);
 d   ALTER TABLE ONLY public."Web.System.Log.SigninLog" DROP CONSTRAINT "Web.System.Log.SigninLog_pkey";
       public         postgres    false    230            �           2606    67122 ,   Web.System.Log.User Web.System.Log.User_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public."Web.System.Log.User"
    ADD CONSTRAINT "Web.System.Log.User_pkey" PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public."Web.System.Log.User" DROP CONSTRAINT "Web.System.Log.User_pkey";
       public         postgres    false    231            �           2606    67124 "   Web.System.Log Web.System.Log_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public."Web.System.Log"
    ADD CONSTRAINT "Web.System.Log_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public."Web.System.Log" DROP CONSTRAINT "Web.System.Log_pkey";
       public         postgres    false    228            �           2606    67126 &   Web.System.Model Web.System.Model_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.System.Model"
    ADD CONSTRAINT "Web.System.Model_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Model" DROP CONSTRAINT "Web.System.Model_pkey";
       public         postgres    false    232            �           2606    67128 (   Web.System.Module Web.System.Module_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Web.System.Module"
    ADD CONSTRAINT "Web.System.Module_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Web.System.Module" DROP CONSTRAINT "Web.System.Module_pkey";
       public         postgres    false    233            �           2606    67130 .   Web.System.Registery Web.System.Registery_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Web.System.Registery"
    ADD CONSTRAINT "Web.System.Registery_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Web.System.Registery" DROP CONSTRAINT "Web.System.Registery_pkey";
       public         postgres    false    234            �           2606    67132 8   Web.System.Task_UserGroup Web.System.Task_UserGroup_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_UserGroup"
    ADD CONSTRAINT "Web.System.Task_UserGroup_pkey" PRIMARY KEY (task_id, "userGroup_id");
 f   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "Web.System.Task_UserGroup_pkey";
       public         postgres    false    237    237            �           2606    67134 .   Web.System.Task_User Web.System.Task_User_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public."Web.System.Task_User"
    ADD CONSTRAINT "Web.System.Task_User_pkey" PRIMARY KEY (task_id, user_id);
 \   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "Web.System.Task_User_pkey";
       public         postgres    false    236    236            �           2606    67136 $   Web.System.Task Web.System.Task_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."Web.System.Task"
    ADD CONSTRAINT "Web.System.Task_pkey" PRIMARY KEY (id);
 R   ALTER TABLE ONLY public."Web.System.Task" DROP CONSTRAINT "Web.System.Task_pkey";
       public         postgres    false    235            �           2606    67137 -   Service.Layer_Map FK13nj6p1jqt0xubl2bm7evx7ob    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Layer_Map"
    ADD CONSTRAINT "FK13nj6p1jqt0xubl2bm7evx7ob" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 [   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "FK13nj6p1jqt0xubl2bm7evx7ob";
       public       postgres    false    202    203    2225            	           2606    67142 +   Web.System.Irror FK179yr80p4adleu0bn1y54bkm    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Irror"
    ADD CONSTRAINT "FK179yr80p4adleu0bn1y54bkm" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 Y   ALTER TABLE ONLY public."Web.System.Irror" DROP CONSTRAINT "FK179yr80p4adleu0bn1y54bkm";
       public       postgres    false    227    215    2249            	           2606    67147 ,   Web.System.Field FK1es95ggvfg9vombcaiqtlv5j5    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Field"
    ADD CONSTRAINT "FK1es95ggvfg9vombcaiqtlv5j5" FOREIGN KEY (model_id) REFERENCES public."Web.System.Model"(id);
 Z   ALTER TABLE ONLY public."Web.System.Field" DROP CONSTRAINT "FK1es95ggvfg9vombcaiqtlv5j5";
       public       postgres    false    226    232    2283            	           2606    67152 *   Web.Admin.User FK1uwd6vtspmpndkcaw6f7c02jh    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User"
    ADD CONSTRAINT "FK1uwd6vtspmpndkcaw6f7c02jh" FOREIGN KEY (logo_id) REFERENCES public."Web.Archive.File"(id);
 X   ALTER TABLE ONLY public."Web.Admin.User" DROP CONSTRAINT "FK1uwd6vtspmpndkcaw6f7c02jh";
       public       postgres    false    215    222    2263            	           2606    67157 3   Service.Meeting_Account FK1xfplj8yodpqrd6lymh91clcv    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting_Account"
    ADD CONSTRAINT "FK1xfplj8yodpqrd6lymh91clcv" FOREIGN KEY (account_id) REFERENCES public."Service.Admin.Account"(id);
 a   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "FK1xfplj8yodpqrd6lymh91clcv";
       public       postgres    false    212    196    2211            	           2606    67162 9   Web.Admin.User.User_UserGroup FK3u8c5qf627qwin8qwvm2jocfy    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "FK3u8c5qf627qwin8qwvm2jocfy" FOREIGN KEY ("userGroup_id") REFERENCES public."Web.Admin.User.Group"(id);
 g   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "FK3u8c5qf627qwin8qwvm2jocfy";
       public       postgres    false    218    216    2251            	           2606    67167 -   Web.System.Backup FK4ch0kue8bh5lpvsyjqnl3qo7r    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Backup"
    ADD CONSTRAINT "FK4ch0kue8bh5lpvsyjqnl3qo7r" FOREIGN KEY (file_id) REFERENCES public."Web.Archive.File"(id);
 [   ALTER TABLE ONLY public."Web.System.Backup" DROP CONSTRAINT "FK4ch0kue8bh5lpvsyjqnl3qo7r";
       public       postgres    false    225    222    2263            	           2606    67172 5   Web.Archive.File.Download FK4kwt8ccqa8s3pm6wyhhhxhbor    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File.Download"
    ADD CONSTRAINT "FK4kwt8ccqa8s3pm6wyhhhxhbor" FOREIGN KEY (file_id) REFERENCES public."Web.Archive.File"(id);
 c   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "FK4kwt8ccqa8s3pm6wyhhhxhbor";
       public       postgres    false    223    222    2263            	           2606    67177 +   Service.Meeting FK5vxbkwhno5m5f6io9ewpfvfrt    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting"
    ADD CONSTRAINT "FK5vxbkwhno5m5f6io9ewpfvfrt" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 Y   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "FK5vxbkwhno5m5f6io9ewpfvfrt";
       public       postgres    false    211    203    2225             	           2606    67182 ;   Service.Material.Material_Group FK6lqodoftkhranmm8ll9qddtig    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Material_Group"
    ADD CONSTRAINT "FK6lqodoftkhranmm8ll9qddtig" FOREIGN KEY (material_id) REFERENCES public."Service.Material"(id);
 i   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "FK6lqodoftkhranmm8ll9qddtig";
       public       postgres    false    209    206    2231            	           2606    67187 (   Service.Room FK7y95kfit57u0f9xku0criwo18    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room"
    ADD CONSTRAINT "FK7y95kfit57u0f9xku0criwo18" FOREIGN KEY ("adminAccount_id") REFERENCES public."Service.Admin.Account"(id);
 V   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "FK7y95kfit57u0f9xku0criwo18";
       public       postgres    false    213    196    2211            	           2606    67192 1   Web.Archive.Directory FK86fcckds9fmo9vn6gnirngn7u    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.Directory"
    ADD CONSTRAINT "FK86fcckds9fmo9vn6gnirngn7u" FOREIGN KEY (parent_id) REFERENCES public."Web.Archive.Directory"(id);
 _   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "FK86fcckds9fmo9vn6gnirngn7u";
       public       postgres    false    221    221    2261            	           2606    67197 /   Web.System.Log.User FK8ld9m40vmrs5efepdj72eojlv    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Log.User"
    ADD CONSTRAINT "FK8ld9m40vmrs5efepdj72eojlv" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 ]   ALTER TABLE ONLY public."Web.System.Log.User" DROP CONSTRAINT "FK8ld9m40vmrs5efepdj72eojlv";
       public       postgres    false    231    215    2249            	           2606    67202 3   Service.Meeting_Account FK8nm941j0t4gv3bumcwsyg9s3n    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting_Account"
    ADD CONSTRAINT "FK8nm941j0t4gv3bumcwsyg9s3n" FOREIGN KEY (meeting_id) REFERENCES public."Service.Meeting"(id);
 a   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "FK8nm941j0t4gv3bumcwsyg9s3n";
       public       postgres    false    212    211    2241            	           2606    67207 ;   Service.Material.Material_Group FK9bdp8ucdge8uyvc90q92do2f8    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Material_Group"
    ADD CONSTRAINT "FK9bdp8ucdge8uyvc90q92do2f8" FOREIGN KEY (group_id) REFERENCES public."Service.Material.Group"(id);
 i   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "FK9bdp8ucdge8uyvc90q92do2f8";
       public       postgres    false    209    208    2235            	           2606    67212 ,   Web.Archive.File FKagip4f17ss7vj9s2wwrkcvm5r    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File"
    ADD CONSTRAINT "FKagip4f17ss7vj9s2wwrkcvm5r" FOREIGN KEY (owner_id) REFERENCES public."Web.Admin.User"(id);
 Z   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "FKagip4f17ss7vj9s2wwrkcvm5r";
       public       postgres    false    222    215    2249            �           2606    67217 ,   Service.Map_Room FKb3laq886tx4mhxma1549o6xby    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Map_Room"
    ADD CONSTRAINT "FKb3laq886tx4mhxma1549o6xby" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 Z   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "FKb3laq886tx4mhxma1549o6xby";
       public       postgres    false    205    203    2225            �           2606    67222 >   Service.Admin.Notification_Account FKbopt2hxcvrh141iy9cjjya4s2    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Notification_Account"
    ADD CONSTRAINT "FKbopt2hxcvrh141iy9cjjya4s2" FOREIGN KEY (notification_id) REFERENCES public."Service.Admin.Notification"(id);
 l   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "FKbopt2hxcvrh141iy9cjjya4s2";
       public       postgres    false    200    199    2217            �           2606    67227 1   Service.Admin.Account FKbs6slesa63l45yy54d0uvdglm    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Account"
    ADD CONSTRAINT "FKbs6slesa63l45yy54d0uvdglm" FOREIGN KEY ("accountGroup_id") REFERENCES public."Service.Admin.Account.Group"(id);
 _   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "FKbs6slesa63l45yy54d0uvdglm";
       public       postgres    false    196    197    2213            
	           2606    67232 /   Service.Room_Account FKclhapld1q4n01ygcv0oif1i1    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room_Account"
    ADD CONSTRAINT "FKclhapld1q4n01ygcv0oif1i1" FOREIGN KEY (room_id) REFERENCES public."Service.Room"(id);
 ]   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "FKclhapld1q4n01ygcv0oif1i1";
       public       postgres    false    214    213    2245            �           2606    67237 ,   Service.Map_Room FKddf8seuxcgwn0ux9xh8nafo5r    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Map_Room"
    ADD CONSTRAINT "FKddf8seuxcgwn0ux9xh8nafo5r" FOREIGN KEY (room_id) REFERENCES public."Service.Room"(id);
 Z   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "FKddf8seuxcgwn0ux9xh8nafo5r";
       public       postgres    false    205    213    2245            	           2606    67242 0   Web.Admin.User.Group FKdhnm45lgors90in2fw2h2sdtq    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.Group"
    ADD CONSTRAINT "FKdhnm45lgors90in2fw2h2sdtq" FOREIGN KEY (parent_id) REFERENCES public."Web.Admin.User.Group"(id);
 ^   ALTER TABLE ONLY public."Web.Admin.User.Group" DROP CONSTRAINT "FKdhnm45lgors90in2fw2h2sdtq";
       public       postgres    false    216    216    2251            		           2606    67247 (   Service.Room FKe5eb6d3mlm3t7xekphhyndvt9    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room"
    ADD CONSTRAINT "FKe5eb6d3mlm3t7xekphhyndvt9" FOREIGN KEY ("activeMeeting_id") REFERENCES public."Service.Meeting"(id);
 V   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "FKe5eb6d3mlm3t7xekphhyndvt9";
       public       postgres    false    213    211    2241            	           2606    67252 +   Web.Archive.File FKev7boejuglytck8yuglcq1rd    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File"
    ADD CONSTRAINT "FKev7boejuglytck8yuglcq1rd" FOREIGN KEY (directory_id) REFERENCES public."Web.Archive.Directory"(id);
 Y   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "FKev7boejuglytck8yuglcq1rd";
       public       postgres    false    222    221    2261            	           2606    67257 0   Service.Room_Account FKevw25cji9v1xvgt74eb675iyy    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room_Account"
    ADD CONSTRAINT "FKevw25cji9v1xvgt74eb675iyy" FOREIGN KEY (account_id) REFERENCES public."Service.Admin.Account"(id);
 ^   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "FKevw25cji9v1xvgt74eb675iyy";
       public       postgres    false    214    196    2211            #	           2606    67262 5   Web.System.Task_UserGroup FKflr8ben4uy9x748n06ounuldq    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_UserGroup"
    ADD CONSTRAINT "FKflr8ben4uy9x748n06ounuldq" FOREIGN KEY (task_id) REFERENCES public."Web.System.Task"(id);
 c   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "FKflr8ben4uy9x748n06ounuldq";
       public       postgres    false    237    235    2289            $	           2606    67267 5   Web.System.Task_UserGroup FKfmar5n0tdatsry2ttfu0ombrp    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_UserGroup"
    ADD CONSTRAINT "FKfmar5n0tdatsry2ttfu0ombrp" FOREIGN KEY ("userGroup_id") REFERENCES public."Web.Admin.User.Group"(id);
 c   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "FKfmar5n0tdatsry2ttfu0ombrp";
       public       postgres    false    237    216    2251            	           2606    67272 9   Web.Admin.User.User_UserGroup FKg72228uwjmtumk7o0khpnpaqb    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "FKg72228uwjmtumk7o0khpnpaqb" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 g   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "FKg72228uwjmtumk7o0khpnpaqb";
       public       postgres    false    218    215    2249            	           2606    67277 0   Service.Material_Map FKhc2x83k2jlmn29kq68o5a45um    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material_Map"
    ADD CONSTRAINT "FKhc2x83k2jlmn29kq68o5a45um" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 ^   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "FKhc2x83k2jlmn29kq68o5a45um";
       public       postgres    false    210    203    2225            	           2606    67282 1   Web.Admin.UserConfirm FKi4t2i0kg245d8fb6yj5xign2q    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.UserConfirm"
    ADD CONSTRAINT "FKi4t2i0kg245d8fb6yj5xign2q" FOREIGN KEY (task_id) REFERENCES public."Web.System.Task"(id);
 _   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "FKi4t2i0kg245d8fb6yj5xign2q";
       public       postgres    false    220    235    2289            	           2606    67287 0   Service.Material_Map FKj200gs4cgur5xq51a5kf82pel    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material_Map"
    ADD CONSTRAINT "FKj200gs4cgur5xq51a5kf82pel" FOREIGN KEY (material_id) REFERENCES public."Service.Material"(id);
 ^   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "FKj200gs4cgur5xq51a5kf82pel";
       public       postgres    false    210    206    2231            	           2606    67292 1   Web.Admin.UserConfirm FKk5lovfvt0apu4ni7wkpqg5147    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.UserConfirm"
    ADD CONSTRAINT "FKk5lovfvt0apu4ni7wkpqg5147" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 _   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "FKk5lovfvt0apu4ni7wkpqg5147";
       public       postgres    false    215    220    2249            	           2606    67297 1   Web.Admin.User.Porter FKkaat5quhoarvfx70f5m98q7j4    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.Porter"
    ADD CONSTRAINT "FKkaat5quhoarvfx70f5m98q7j4" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 _   ALTER TABLE ONLY public."Web.Admin.User.Porter" DROP CONSTRAINT "FKkaat5quhoarvfx70f5m98q7j4";
       public       postgres    false    217    215    2249            !	           2606    67302 0   Web.System.Task_User FKkx74jgadegi5o45ijchxvuw2m    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_User"
    ADD CONSTRAINT "FKkx74jgadegi5o45ijchxvuw2m" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 ^   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "FKkx74jgadegi5o45ijchxvuw2m";
       public       postgres    false    236    215    2249            	           2606    67307 5   Web.Archive.File.Download FKlmvo3wehmatw4p69t2498jrdr    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File.Download"
    ADD CONSTRAINT "FKlmvo3wehmatw4p69t2498jrdr" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 c   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "FKlmvo3wehmatw4p69t2498jrdr";
       public       postgres    false    223    215    2249            	           2606    67312 4   Web.System.Log.SigninLog FKly85hqgbrmb5scmawg6kyrtn6    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Log.SigninLog"
    ADD CONSTRAINT "FKly85hqgbrmb5scmawg6kyrtn6" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 b   ALTER TABLE ONLY public."Web.System.Log.SigninLog" DROP CONSTRAINT "FKly85hqgbrmb5scmawg6kyrtn6";
       public       postgres    false    230    215    2249            	           2606    67317 0   Web.Archive.Directory FKmwjxdube2yqdoh7qkoeag216    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.Directory"
    ADD CONSTRAINT "FKmwjxdube2yqdoh7qkoeag216" FOREIGN KEY (owner_id) REFERENCES public."Web.Admin.User"(id);
 ^   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "FKmwjxdube2yqdoh7qkoeag216";
       public       postgres    false    221    215    2249             	           2606    67322 +   Web.System.Task FKn1h41i6mfajsxx8vh8jdtu2u5    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task"
    ADD CONSTRAINT "FKn1h41i6mfajsxx8vh8jdtu2u5" FOREIGN KEY (module_id) REFERENCES public."Web.System.Module"(id);
 Y   ALTER TABLE ONLY public."Web.System.Task" DROP CONSTRAINT "FKn1h41i6mfajsxx8vh8jdtu2u5";
       public       postgres    false    235    233    2285            	           2606    67327 /   Web.Admin.User.Uuid FKo27exmbsc9qlt8si712r12qci    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.Uuid"
    ADD CONSTRAINT "FKo27exmbsc9qlt8si712r12qci" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 ]   ALTER TABLE ONLY public."Web.Admin.User.Uuid" DROP CONSTRAINT "FKo27exmbsc9qlt8si712r12qci";
       public       postgres    false    219    215    2249            	           2606    67332 $   Web.Note FKpxrohtf9r2xso6tqgw5gr1ji0    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Note"
    ADD CONSTRAINT "FKpxrohtf9r2xso6tqgw5gr1ji0" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 R   ALTER TABLE ONLY public."Web.Note" DROP CONSTRAINT "FKpxrohtf9r2xso6tqgw5gr1ji0";
       public       postgres    false    224    215    2249            �           2606    67337 1   Service.Material.Area FKqk9o4fe4k64pg1030jec07ybj    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Area"
    ADD CONSTRAINT "FKqk9o4fe4k64pg1030jec07ybj" FOREIGN KEY (material_id) REFERENCES public."Service.Material"(id);
 _   ALTER TABLE ONLY public."Service.Material.Area" DROP CONSTRAINT "FKqk9o4fe4k64pg1030jec07ybj";
       public       postgres    false    207    206    2231            "	           2606    67342 0   Web.System.Task_User FKrbwfcbgu9tplt9huxukqoxwts    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_User"
    ADD CONSTRAINT "FKrbwfcbgu9tplt9huxukqoxwts" FOREIGN KEY (task_id) REFERENCES public."Web.System.Task"(id);
 ^   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "FKrbwfcbgu9tplt9huxukqoxwts";
       public       postgres    false    236    235    2289            �           2606    67347 '   Service.Map FKsaplo8iwt8crpoej8foemvr3g    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Map"
    ADD CONSTRAINT "FKsaplo8iwt8crpoej8foemvr3g" FOREIGN KEY ("mapRegion_id") REFERENCES public."Service.Map.Region"(id);
 U   ALTER TABLE ONLY public."Service.Map" DROP CONSTRAINT "FKsaplo8iwt8crpoej8foemvr3g";
       public       postgres    false    203    204    2227            �           2606    67352 1   Service.Admin.Account FKtag9wsxdn3huwoyu9xnct3i4o    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Account"
    ADD CONSTRAINT "FKtag9wsxdn3huwoyu9xnct3i4o" FOREIGN KEY ("accountModel_id") REFERENCES public."Service.Admin.Account.Model"(id);
 _   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "FKtag9wsxdn3huwoyu9xnct3i4o";
       public       postgres    false    196    2215    198            �           2606    67357 -   Service.Layer_Map FKtaw5vu9h5m0aw28ik1x5xxjmv    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Layer_Map"
    ADD CONSTRAINT "FKtaw5vu9h5m0aw28ik1x5xxjmv" FOREIGN KEY (layer_id) REFERENCES public."Service.Layer"(id);
 [   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "FKtaw5vu9h5m0aw28ik1x5xxjmv";
       public       postgres    false    201    202    2221            	           2606    67362 +   Service.Meeting FKtfx79xn6n2cjyfut6qjsom86f    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting"
    ADD CONSTRAINT "FKtfx79xn6n2cjyfut6qjsom86f" FOREIGN KEY (room_id) REFERENCES public."Service.Room"(id);
 Y   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "FKtfx79xn6n2cjyfut6qjsom86f";
       public       postgres    false    211    213    2245            �           2606    67367 >   Service.Admin.Notification_Account FKthcd9eh23nwo3yreqgldl4bnl    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Notification_Account"
    ADD CONSTRAINT "FKthcd9eh23nwo3yreqgldl4bnl" FOREIGN KEY (account_id) REFERENCES public."Service.Admin.Account"(id);
 l   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "FKthcd9eh23nwo3yreqgldl4bnl";
       public       postgres    false    200    196    2211            �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	   v  x���Ko�@����ޤ��x@1�)���H�(�E�DS�ڃ�ã��3P�LW�mcM���lv��fX� D.�b�$b hhTErX��������kCХ��C��m��o";�!��6�I1+�j�t�T��:ved���2��y�h:��J�� ���Nm5�*��*QGC��$���1IR���<<u���:w�M����#��_h�.��p�~���ޅ�8Z�:x�L�3*�e�F��%8��t<�T�j͖�H;dA�	�&o����Z�了��+���#"�J8�9%otJ�I��bP�cs:S�<'�ּ��+c`U�����0˿2�W�dV��]��]�+d��$-E-i��b��$Ļ      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	   �   x�345�44�4�70�74P02�26�20�4����;o��츱�fP&X�i�Y��_©&a�1~\��f�_R�X������4'�Ȕ$�����N}8��X�M�(�/*q�HN���G�#����� �CbA      �	   �   x�}��
�@���>EO���]���B�"�nT���ۧ��3"�n-�E�Y'���j�R�a��!��K=�&b�c�Cזm���x����c�؄�?�|'S�yp��"�'Yf�����S���A�ӿ�3+l�,�J�RoE:�      �	   ;   x�323�44�4�70�74T00�21�24�4���!###NCSK.#3Lu�&�b���� ��g      �	   F  x�}�Mn�0���)r���ObϺ7趫
%���),r n��E4��������X����7��e m=��x)�
A2��U%T�-������_��ʟ�|q���-�2	@i�<������Be�}R?tL���J�t����Z�+���i�d�����6���nK�ؤ��
�J����T�K��|z������Yd2|�<׿\1et�#}x�/�Ӿ<����v�%�}v=tn]��Нvn�
T(0�i<�G*@�|(���A�8��8(�݊����_;(BD�r����9���ʛ��i�RJ��Ze6)g�F!n�g����*D@?�E����
      �	   3   x�322�44�4�70�74T00�22�25�4����"a�idd�i����� TQ
�      �	      x������ � �      �	      x��]�n�H}v��h�7QK�����Y�icz2JdIbL�J���_g�e�����4�g�6J�Dɢ$'vr�nE�*��z��9ݦc�V�R�+%�P�Jݪ֭ʎ��������T�tE=�C/���Wg��k�#�8�#�c?P�� ���/~R*[�r���g/_<T�+ϰ{=P=�qɩi�fٵ�f��BD|y�N	�RY�w�z���S���w�*n������O�d/�C|��4BZ�®����A�Ga]�HW������2"�%nk���W8N���d�$s�)�8�UʀD.�cz��A~��GJ�/Qp�X�c��H��6�q8B�����q�.
�����TA�B{R̺R:���Sc��1�H��3��Y"x�~������g%�}�܋�ĆL��^1����2��Z�1xG�X=}�&��S���=�{���������ɻ���P�����5���h���	�(|�uӮb��Q��Bu��z�~p�@vw�P�4���Z|t�^��m���
��p]`�q�� :dO���0��pff����FG�ӕ�'��;�ߥ�l�S���3���6��` �����	�t�E.b�L��9���7Q���Cc��u�;v�����=uI��(W��	�j�6k�D���YϜ��C[�-�X-o:�߻�ZEA��!5K��t�쏿�s���~��se��Z0Xc�u�ы�Z<5"&��
M&þ�6و�����*(��YQ֋�3׵{��f��9����6ܾm�?9�R�����s����ߎ��D̍�����biW�������vx�O������ڷ�����.�H4�)@j^�ظ����o;[���b�\%z�7?QOg�d-k�a�
�ܯ�S��L�E���U���˦K���>���|���i��Ok�=��̸iU�普c�{�k:mp�|G��_��p0�H��%E�"9O�E/:�Kg�Z�m{�D�EDƑK�V�aX�,�H��%
�([�\eK�(V����k7��Q��{�z�D����$�)h���onhl����:D�
�+�VS��ݙ��i���6+�EE����ĥW�YH�wZ��W�����ʃ��QGl�jm���(-M��DT���Ջn��A\
��E��N������R/I�s���2���-G��qNe�#��<�]h�[�!+v� :'5�p����#|����z�t�6z�a���s�RuA;qnZ7��[��>k��0Y��+�R��Qx]�?���ఋ���8�PD�o�Zx���Y�Q8���J�yFm=���W�h�0N��:_!��
�r+ڍi�a�ȮY�N���>��yw�.��hZ.�v���]=bw�5T�b�O�+��ƫK�g�x��"�N_P+�����g�a�qn�g����`�M�!�#�5��(����tӥr��������j�\5���Fd�l͖���v/���������N���Z�̷f:��(�m�N�[����`��0[{C��#j��4���+?j�ʹۊ�<y`z��C5�"�u+�>����(��6��eN�p�������3��F�I�O���NM�S7�z09�2R.6�W��+��g^d�1�]���N��c�����9�*�!Ÿ>��^zI�c�`�W��Xp�0��^�����������7B���!� �.�E޻w��5a&=��@��Ť+����0j��m�!'���0�3O	�f�F��!썡�����~�JPbz����f �����G�Ǟ)�N��;�|���|ώƸD�7��?� $C��W��kC|I|�%���:;s5��/Nă)�e�� &�i��:��M+1����Y�����65�s4$c�	���6�4�\��=�T�򚤚=��~�h���ҧ��U+FTw5�a�	;�$���>A�n�Z�>}��X���W,�Yjl��Zh��᱾�J!/!�μ��
�����;n	ZQ'��|�L��OO^v�YP�7^m��#��|����Pg���2"䵢!q��ձ��4.�!������'`?�	�O�õ����c��2=��sk5o�6���*�^�5̲c"��{�q읪]�M�қU�֝Z�*����84����}g:z�\�g:֌
�v�	*���=�0A�	*LPa�
�DPa�
T���&�/��|�<$�!!	yH�C�
��=�N�&�0A�	*LPa�
T���&�0A�	*LB!&(�@�	3e]'T�����T���&�0A��5U��6J`?�	�O�~�����n���
�R�UaV��w���BϿriD��S��G4jQF>�T.����t�����#p� �$�ӀB���6��3�>!A	BHB�n!�tYN�)F������EHFED�4�$$� II"H!)I9H�AR�r���+I9`��`��$$� II"HA��D�$�$$�)����
�,�����mAj��r�����%�8��0H�ѭ���k��[�!7 ��ܩ� (Eo�p�Rf
(E�� �R��(E��-��W�}Y��:��G<z���x���=��G<z���x���=��G���]9�+��/�G<z���x���������x���=� 7 ��[� �����L�� ��߲=��1�����[��=��G<z���x���=��G<z���x���=�+�w���.�` =��G<z���x���W�G<z���x���� �n}n x��j8�G3x�wr �G�x�����<��mNx��bT�Y��oȣ���Ґ�	%��IQB}���H;���܍.�Ů~�����L&�g0��`�%�q�N�GJ���a	��m��ݝ��2�,}@�C�.`�^+o��1�e=�����u\�5Ʒ(dC/("�	��֢A'��K�^�M|�?ӧ�Gq�<m%��ctj�5"�(׬���b����8�WK���Х38�?�Hw�oD����K[�=rc��4a�x�xLa:���X����� ���kn�8�M�=E��@y�BD��"^�2��8�&j��)��bo��X�[��q�)4KΘ��|
1[�IXl�IH�.�P�[�ڕg����0�����t����Ԯ��{�W�h̛oc���]|�,$a[n�f���=��Ю�A��(Mꚱ2u�B�6�M�mf����`��
��@�:�����AǷ����@�Nd�˴����5�;F;�Ӂ|���|�{ ���-��D�W+�I�kq��|���p��=�8z0S��w' 8z�l@����q�@�zd�#��ȠG.�G�֔�<۩��޼h��68���P�K��z�%M��*�.�$Ojw��v�9���Vb�m��M���Ҩ��Z����8d��W�Ȳ��U��ɸ���#��-�:%+%'�[�&�k!������Y&w�U���5�yg���ՉI�A�����b�jr5>	���ɸZ��U��(�]^�kr�l�,���í���x�7��e��i"L�I�/���Us�d���aW���qjSQ�(S%����;VQ���,�%6G�1ws:�GLrA�]�U�`�2��� �����l��q��[�6E�~�Y�B��`C�oB����)m�d�4���&�1���ЩUjY�ڊЀ��]�㏁p�N��v]i���>�+�0�1�O��xa���R��Oʈ;J8f~�o<�Ve�*�!N�ԕ#�I�E!�%kK�XQ�r��2ְ�i�-��W*z�|�m����?�C�Ͽ������?�J�Ҫ�C%I?������?��ǟ��5��&��/#��?w�+�Ct�?��7~3_^6���7����ˮ�=�f��F�������^�_�ƫ�.�<�>�-o���bf�y*`dit�!Wid�g:�.�GX�A�H��7�J�����@�_�2�%^hި>�V2�B�Zz�X��_:�Ұ!�6~;��gA�+؛�c9���y;'�ޜ=�T=
P7V�I��&��: �^l�8�e:$ O   ������ʮ�����"|�n2��?k��r[/��6j�^���QiV�\>hZ�a�R.[ͦ���^Ui#��{���C�?K      �	      x��}[o$Gr���W|��'����sA]�c��g�ŁVX��=3�(�n6u���5dI�����V�cA�:0��R�3'���To�Y�hv�4M�/���""#��$��Kg3�-!��\?b�~������eo�X���_�f�`����w��و��>꯶>��[�����7�g�7��I������_��/�����`����������~8}n�FR93�m=�<����?�������ii53�h���u=��������t�=�NN�/g{�2�%�Gӝ������/o��dz<g+�;>��w^Nw��[�����x:�8�;�������������a�c�v������8���`ޙH���;\h�)L�� @=�E�k\���K�4�ҊV��)�d��'˒I���g/FǓ�����ч����d�C|\\����!;<����6Gϟ.����ק?�����ϏƏ��L�_��������J�� �54�5 ������9~|49��?~6�������{8�>�F��W��$�j�P�\FB�h��;�;^L�����W���
�KY��y�)���������t��ӹ�7���������d���{8�ۙ���{�m����.�������h~x�������p����������������_t|�=>8����}���({c��}<:���/Ͼo�~w��?��7�?l���韮�����K��=(�:H!I
�E+K /�P��4�bd3����6�,J��D$�Ȅ̙JIJ��t�X�q�r����5�]X������ԛ���yz�c�}�����m�����?�h���g�{Ӄ���1�S|	l�	��R4�J9˺q}:�s�@� ���S�������g���n}G��� 7C���c��_J~q�������N<�փ|%ċ۸�!��$Ďi+�9-C�����<p����� ~���|���5��/U�%^�k�Ͼ@�ϧ�]�Tn�~��Pj�I��	)��^����VД�\^�-�m��P0^�~��7���-V$D���@���ɋ<��rAG�'{��ׁk��YG�+aKS�ɼ�A0�0x��6�:]��XZ��i��E����h�s��	��W�%Di
�s����xo�/0z�L����>��>������=芗������ ���|����7[O�"A��#�	����Fra�¯���[����K��YP%0�˔YW�$e�,������S���J�Th%��e��~a�M|3�̠�#��i3���{~qa��F��A��V*�ש`8�|�t�c���̦�����k�@�Wu���ݸ�~�zq?�?܌��.%��iD� N2�CN��\����sst\t�X=KЫH�"e�P<�Q�B�, ��H->;��@����%��IG��+�x�`x����>�}�
�Y�.V�"SI5�m�t"�Lm�ga����3�<���@ڀ�����N:��`�ğ3�ğVh
԰���P���I�,!��(.5�^GW_~�԰H,3朤�I���]̀�rcF�qJZ1���j{���'�.�wJ	eb%�)p�.d�dF��.R��2M��LTj�ҩΝ�����`w�ɣ��w����{���髳�Ͼ�b]_g}\�\e��M�O�e�h`\$y�+J���23�}��\�K[)O	�~�S���Kl��2�25�@'Zr(,ϳ�ĳN&���t,�GIC,��v(���<�hgL�rWHmKU2]
W��ȝ�%�
�:m@L4}۲������>�WS��,7���&���2�C,�c�����8&���A��4�]+0�v�6�U- 﫺�͠J���}z#c��
�`,F��F}ej0j��p��U���,1��.�Wro����HBt����䮖�]C��rr7�ƱL�U��3c.Ɯ`���\*��� �_U��f�Gә�KZ.L }��p�r�Q�&�@6+8$���J��Ln+Er���P�_UJBIф �����WC�B(5�2R����@��PJ�%��U
�5���=����g�A�4u��Iw� _�!��/����7�j��R�{���˛�դ��R�inƎI���$ZHB_N�m �`ń�k������աt�(B)��b��z�Y�9[���a=���(	fX<��ω��ʀ���&��*�c&�����I.�Ƃdt�85��k��Nr!�����&�(�$�����R��1��P��+�������hǛ�>�~��Z�"Q�!�@\\��u�CI�_�C	b�]����Ay(M(�|=Bj�2�R�%�Ɗ$����P�%%I��ց�7�ap���N|=f$�{�1��?e��0,����:���;�o}�w�������t�!�)ˇ&1-�u9�r~�-�993BiuU5g��՜��R�뱢)v�a�>�I1��w��f=�B��i|��2�J��g��|˸z\�%#yR��M�������1m�-(�2���eQ��PN���)d=p��RLa"�F�~c�x��d஗+P�ֲ\��=�rY/W@�)�iE�QPY<�����pw��-d�
���,�"��{~�����4�`$�Gjy6���OG��*�Og��{{o6����@���t���t^���zyϗ
�z�ο����+`yo��4�N���	�D�c��_�_�Y<�4����*G����HzɌO3GB��f�P1r�ZLDT���~�i���Z
��8��+2JRj�р:��E���=��p*�"�%O���`�%W&O
%��6vB-��Iz���Nk\Y�;�'+E�3��D��q�671V�%wE�
֙x�*�m�]{��a���KLl*J��,s�B�-Z�[�g@~H��=p~�]���v�,8�E���8~cp[�%3Z)�`]M4*�9�)x����D<�Q� �U4��>���5���)�d "W�����vKF�n�c��*S�qnNi%���}2S- RA���S�HF�xD� �[BA�k�U#
c��<g�0��ǂb+�ӂ�w�1��=V$�|\,@ϝ~B�=ؽQ�: W`�H�<0�P�c��0�����J�6g�Xe���V*.���i���<���+D������ ��vT|ߵ�d���b�>�L��I�w��/�-�j옑��:� ��<���;8�W����y����cA2�b�����Tރ_=HmqX0t�~�T���i���>���2�(mV��J�e���	�u,����B�"iK^�Z t�
Ģ#E|n�wz�hc#�_��F���Er#����
+ 9�w+$��(��de���[��1E5��p��8f���:�� ~=pB�5��}<���m���������F�h�i����m>���_�u~��v��6��y(	�_Jk�a.ڃ��z�ZY����U<�`�#������������Ӄ�l�?���
�����yn���ߟ}ue���V$!��-ɶy/	�A�J�� ���	��wl�ί��6ƒl
�PZ���q?�۶���5cI?P~��x[x�-$-`�<�%9�@1��*������m�`Ѹ�+�S��W��9�m�7B,H�J0f���Q�ji�=�����A�ciNr��b�!�kӭ/DƴP\+���*	.��`	�<�p��z'[��5�qT_�L,?��n}2�˃)C�m<���ѱw�K�,(��I�/e�h-�m9���"Z�*�����G�[p%K�jA�L�q�a��*��V�Ѿ��LD�2&Y���)�Ze�)cS}Y �ֶ[X�"��x.+M�d��IY�,� M��}q��Y�T�8�)Vѵ[E����|�L�ܰ�W�E���D"K��\�2o#؍x�k���NFk��v�7h�UQ��g�.�y��ɤ,䴳!|N��*����xv��:ė ����������֮~:��N-&k�v'e��gB',˕* ��؂'��ufy�2\vc;3��a۸a�����yM�B�4��Lѡ�0!)��Mc�ʔyMc���Ɠ����	(�i��֕���H�N2���m�lJC<%�/\Ez�H(q.cQ��    �a*5E��I�T�T�6� ��)k<�.�A�GL� ��ݰxva�$�Q=f�������L��Ry�J��CitY��&���AP-�$�<qE�U����ȥ��5��)�B	�ei*���EQ���ɂ(PW+�ﴰ
\��V5�P��V�@J�ʲTYiӢLM
Ew��Z+���/���27,+�6%+ /��Ұ�f"�WJ��s�������=ϡ�֨1ȋ�΁LE�l�c�rn�Dt�%AXELg�&E��n�5H�i�-�_-��h�Z')r*� �$�2�1�~u{1E�+W�(��!6:��Wk1	���^�D������7l���1���%�5�璙�3�Y��\��5�Zi��C����oe_0e����]�xv�?DP�E���j�k��mm�IYx �$I�s�U4r�/0"���Ac�W���w�{;S�=~��|���F���&�����>w8�ox6�z{k{���d���<��l�?������6��n_۟~�^��x�x>��g۟���A�:P��4�"��2&�Fc��h������Ó�sk2:<���OG���!yr��p�"R4�<����1~	IwT-��E����$��C��0iY��R����
\d[$E����s�!� �#;=�A�'���|7J��i�a��ܺȾ��FS���#"���=U7?�P���5�R��.Mq�{�����I���E�w�6�:�.���R�E���Iiz���P���ˠ��0�����Y�@Ji�̶�������d n���B,�z̵ln�%g2`�̘�d�� �;�	e�w� ����~挷 ��9�R�� �i�A��8.=�tQ��ܙ$1FCY�K�ϋwQ(s��"Yg�<�$�fܩe_�� ߍ/Vg�<���.𕌯U�!���i�U���a ����C���z��1Q��0��5��!0�d$�Ny	#��2B���t� ���NQ�ذ���Z��!=������d���<���q!D�~��غ�4��@�@I����:���s�wf��|���5j�g�����?�~{���O@U����f���U��*�f���Lu��]�>7,��.�;m}��n�Ɋ��6��(x!h��h���M_�y�A�������"|z��������W�w�`x���?����W����u�R�j h��3i��,n�g��U?p����Z�GI�hA7�:4Kq�D�Ƥ�%mҨ���{����fͮ�
��ξ�V�_�_E
���OP�0&Z.j�-{��X��ʭ�h�
n�=�0D����;��+k.�W��+�BE∲�XsW��h��������{ۧ�yÌ����ƊA�/(�)[��ϱ�E�A��y)� ��;��ls��a����Hn��U�����%Zt�/w<�.$  �,��c�[M�	@lx�	戰H����M1��L `� �"S9�2E����Vc#�S��Y��%:��	gY�d�&0Ov�i.���k�$J@@�1'�=:�x�p�K�L@(	{ۿѕ�d	"���+�4�����J��Y�zɒ�4c!0z��^�(�u�n0�u�	W+��� _�:Dp͆����Ap��&��ӳ��F��4�@��	y�)�$
�'b��ƪy�D�N�� �F�ٮ$�[���ƌ"�#�RL�R�kH@�6��u�HĞ_y	h\$�q�4�"S'�2�iʔ8��=pB8E���\�������ٿ&��"���1B�hbH�9� BK�m���#��G��QguVA=`�u��]�3���iJ��h�4���ö��PA���R\�g�Tй�*��3!�t���d��T�k��H�'�H��y��MT��
��
�*�T�9�$��P#�B�4�-D�Ei$D;
s ~@��b_E�7��)B�If ����G? ��X�_��v�.[�Mp}dh�"�N��{���Tѱ$BY�t,S߂Hx���\����M#`�=�{p��f�ȷ������f�C�ɨ@�IB3�����do��Y��_�����K���;�����W�ߟ}u�Tn�"Aiǒd�Js���z��c�X�Z�������&�E�21�c�+����0|_l¢��bb��v,}�Ɂ�KjSp����#.�~�����g_{жN��g��7g�_����>�j�����>���t�#Zߪ�h���n�f����9SD�\+ѡ=Bkea8mr�(�W��[���1巊ԩ�yH0�)y���.n(��+�s�L'
�\���ke��F��<;ڷVX9i������Ja��x�����y�["�$K^�JF�z�]��ށo��U�#��KG5{a�qÀ���{M(IvEk\��L����l<m��C_Y��6E3$�VŞ�3tJ���Θ1�E'����V��C+���`�$=w��x���_��;xS��2E����}��J3�s�B����!�Wؖ����O�9� #��'�F������P!�4���`�mY1�&�n �$�B��-�3w�H8�\B�j[�b4��Ħ,a䊦7!���h��/v��@��g����}i`]iVƀp6���Vd�*IB�ӯ!�W0���WK%d�I�PA�ND�&��&�F�����$+2�ᒷ0+yr���P��|Ӿ4p�4�:�=���X՛h�>M�C)."� �6P�/Mrjh�OU�<�*���m�w��f��Ȍ�blN}�l�%�j�'�{͞K�Ae���yě��Tũi%�9�����p�|�(�����螂
�(N�S�;Z�5Σb�<����,=f��сg���,��T@Kq���X���������Tm� �谡�����<N��4L1o�� ](GNtR��"�j����x���"ŉ�P�F�� ]��BJӜç%W<�!7q���Yw>r��!�J�����!Z��Y0�9���잇�bL����g�n�ufŧz)V�⋜+6��(��N�(��kڰ�-�N�(�g
��rP�(�]gVuAu�l6��-�Ιx�iF[V	�Ŏ���4���o��F)v@��؉�F����@�h��\a�~wņ�,�F�A��(vC=�������B��3M�,	!���1; ��G�$�z�h�P�<C�i�n����jq��!�$ӗJ�
��SFb�
<��0e�(?��2rS�v�A�����RZˁ��%7o�d�ls 甉�ֺl3;�{q�wP�9�"u�9e3��¦�R �(�E7C�������G�v����g_\����`�5SG�|>:9��]u$�O�����qq]��*�O��ȫ*��JY�wp���%y�HƘ�k�v���7��t� :�:2��h���X�����KF��a�E�]�����[����Ó�d6]�8�}r0���/��f�BxQ�m������/�6_��|�/�B[����#���z=���&���v�`2���H�\��4&Ѐ1�T�X0FĘͦ���[����|4�}:����O/ɓ�燃QƮ����oEA��6"n� 6|=�]W�bR?���!9 !V��*�A7B�"��k!�@���؀&cM�@ܚ�1�&Pn�b���9!v\���Q;���n�dz01�Is�'H�v�8�P6���&0R_�D;1�65�9K�N���G<Wf� ��b�Ӝ� �p冀��uZ�CL��F����т��i1�X�܋���{q�봘��$�i�C��:�Q�Ӂvђ�q��SlB@���A�[@\'�b�m���܋�0�ظ����qƦr���b�jJ�+�� ����h=^ٿ�[�h� wB
�-����WE�hvx4��g�g��N��_�����Og�ܥI�&��搖��\������m�aX�*%�����MN��v��h5��O�&BL�qV��g���؁G�Pܧ�rĈ(P���������ZD]@�J���s����f! q  *i��@��������qW��w��� ���9���p�P�Y@�"�䂰%��d�11���1ɒ2��x	�;x���2l�U!�Z�XP��c�a:���u= Qb��)1�# �����a�:�XE9A1hCA֮�>b���ȑ�Յ+q��t]����5k`�H��BA�Bk-t���#�b�t`��{mj\�FN�d���El�[,�tw�
��+�z��u	�������Ǜ����
��h�p��O�XmX8E��A��?'mp�q�x��i����s� t'W����\{��RF��E��u��M��3w��<7�2pֺ�U��xR�����C�6�:_'���\2�@�|1��p��=~�|�fط~���+����ٗ�J����HA���R@��K\M��/Vxg6�̧�r�|��$d�k��^�D�J�͒���KYu�eASL�Z��K�+�,B�"�:�'�&�ξ�z�n�|�� H9�S�w�$x��{Pe�d.�"u^P1����2,^9� WID� �A�]%���q�DԙB/$w`F�u8���89-� �N�)�����c��c�s�^
h�\�\�v}OE�}��R�-�d�������B�]�څ:è��Qpf��t#u��K�2��X���!V�"V�u��K�X�B7vA�yF/$�O�C`=��5ʀg�cN�3Ђ��B�]��.�"���(�D��4d$����%N��Ђ�����{���.�ؕp�e�X�|��Hg�hr���';;���SG�^��x�(���������`N���خ���[��숲�Y�S+W��4\�f k����Hj���"y����إ�]XJ�j ���0����P�%ƒd��ю���� �z�I&͝VN�"��mq]�����q\�$\k�LjD�i'��弴���. ���4���K�\�r#�/W�x����AE�OD��E���ŵ=�cT��U�T�wp~%�o@]h��T_'],�ʿޝ�|K�� �&�h����NG�f)�a	+�˸aZ� s�:OY����Ñ�?9؝~�������g_\�%�A�OL�bmݝ[�xv��:߀hu��U�v�ke��'����c2��1��@��0�g�'���%�Bd7Y�+-\��]�c�SF�u�,��n�5�MN�1m���o�Y��=�q��'�d��.g��n�c��A�kƊbq�c�\Č�A�1�z�X:�\��y�+���$������b�R ��$�_��I�2ա����9Z
%�
��� Ց�:Q��ڰ<u`e�eE��Ii�]@.��Ō5M��0m�L��[|h��C��C�_n�����Ab��iE|�����4��*��LkƙȔ(i�S&aEg�4p/t��v$g�ֹ��(kCT�)��_-T�!�=�:� ��k���٬�0ǅPw�JCY+�,wy	J�6/Tw�!�&��kc�[�ى�� �4g/:��X���� q�z���7I�K|u{�7A�kǂ��̙d�J{7
� ȵ4O�D���\���Z���|�8Iϋ3б�u�ۊ�;w��2A��?��p���8��I�����NS"@Ek�����mn���T�#�^(G�Q6�0*�h�������)��>�?g��!��}����q~����Ͼ���a�������bA^6kƫB'�-����ܓ��t�o���E��#��� ���B~�[�_�xw�_k���,��<:Or�4 WF�H9�VQ�[E!u,-�5i,����ԐZ^(���P�,2�y�J���J,kXX�na5:D�+�YR���\* I�4RgifL�0�@޸�Q���hb�b{�4I�r�;�	(�M�ڣ�3��<-�M<ŵKdw?Η�Cf�L �RV2�
.�4��&3�*���F`B�Ȯ��J<�QVՀ/9W|�/�1B��'imo瓜�u�협�-\�n��s�G:��^�5�8�iu]�ZV߄c�Zl=J�o�w�ށi���5DW�A��{�Z嬔2MT˄.4X�$�Q�T^up�Q�*�h�����Ҵ���KΥd&-��pyfX�3.�R��n,,��G�~aU��U��^�SKK��,��
Q&(;��9/�4jeSU<]=J]eA���h/�R�$�%�B�.�N��\�,��v4�A<�Q�Kg���Y�c96o�A�������ei�SY�u��en��>��ԣT�`�h	�[ka�xv���(��K��@|�A��S��/��F�<�R]=J���k�\���_G��Q�Z�JF��Q*e�9�8��^7��Q*�TW�R�c%�#o��r���2Z'S�\*�H]�9Hy�T��axeU�6ӍB�;q-�\�����"w��Y!E
9gi������d0V_X5fwOR��E����W<+�R��{MT�0���sW����ٸ�x�$+�R	�Y#Ҕk�j&K&m�e}�&�\��8��*��'�'2e��5�On��I�I)��J�*�
L��6���R[�ٍ��|�|���Kmm��T`�N�����H�W�I.��^�5���X��ns��o�s���(U�[[@��}�l����Qs#��hgT�6��ט.����V��
����m1;��-'�p�ū4���T��*Q���A%|,D�q}i�L��4)��ZcR��Dg���A�B"�qy����[�B�xu�����F4*��%����HB��7��桱a����+zSۊ_`?jL4p�w�w����y�w�?��+7�1F�J����:�E� ���ȅ,Jm�I�[a�e��HZ��T����|U_P�<�B��%+�$�O��'A0r�X�W�����n���*�A�~�ajB��&�D���oGo����Lr`�      �	      x������ � �      �	   Z  x�՘Kk1F����2�z|�t��*]��&��	����I����򌝨co4;�Y��Ǚ��C�b'��A����$�N`rw=���]o6�0g��_���s}c�0���G��||zڬnW?.ׯsv�81g�_n��~0����|^-��������j.���Q�̷��}^�o�@�?8�l��a��z��)ld�U����ڥ]�!,gdEg�S��-�mW��w�tg���q��*eXz��)p(�x�������{գ-�a����j+����E�P^&�x�J�*�{/��XG��#���dt�{"P�ex�2F��Lz�NA��X,ÁO%��fT��{'�;D�n�4q���k�d��{�r�D*�U&�^F�&�\�A*qmKW�����ĖOR�/�GH����q��e�+#�	�{F��w��2=v/ç]|Le
�T� ��2�{�b솩Z�49`�2��0�L�Dj���'#ad�s�u#d���Z�:�a��`k������k;�f�A��q��?C�hӘ��dd�IF !I�g�R�PF�`�XWͰ{����V�L�߽2"�XW�Ȱw2vf����]3�N���n#      �	      x������ � �      �	      x������ � �      �	   �  x����N�0ǯO�bO�A)㎁6M���)2�)I\��o?��uCj;��q���W'�#3�̾.�Ƌ����tz5���aO� �:۲����sV���n��7�i��*.���Z�v�j��B����W+���ݶۖי�`����d�[=�5�dzP˶o�o5�`�P A��X�v.���«ȩ�
H>%S,��	,��Keȏ��Z!&'��� �^��f��J)��~�1ą2 q��d�e�sc�Z��-�4�QC�F� �},o��g����b�A�ڊA���C�oqq"�F����1BR�ތ1����J�,D�R��>Vm�0�<;jvA��R��Vb��y�%��;�*)hy <oHԗ;R�V��>�[=F�VFֈ����5n[�u�^"x[+g� I���'J^���ܫ/BiE�!	�~"̱�H��U)��Z!>'���>��s,�����x����˳��2��2�ah��7J�����O|+��T�����Lb|V�$�k�"�A���Y���Q�@�V0���C�{="�unG8ܫ!D�E��>�f�L@=��M��ox	���>�F�D)9n��T�x����5��^�5g�`���	4%��D�����,�Ӂ?I�v݋��f��s�kg��I�k�'�)�����/e��Rt&��$FNB�G��ߒ���>s{B������G}7�_���yJ*��U�C5�4��1��i���sG8�Sp�m�&_�`HVV�
�i����-�D�/£��G�Hiq;k���z����	��Y*�L`)W����\��2"X�k��+1,G5Sa��2q��a6Ҁ[u���V"�E�a��ZMR��I�U�B����@�҂F�e��`cݗ�����\��4�e��Ӂ?���t2XRj�۲Y�뒮AH��]r��1��	և+r�.��ՇR��#��l>�v`�!��0�����y"���W���,)'=���^O�h4�_�      �	   �  x��T�n�@>�SX�5�xM�%��K��rA���`VY�Z����W�O�h.}��Lg8l j�"x����ڣ���(�0��xp�a/썯zJİ\�HIɸ$3��TZU����z��R�U�j%|��}�8	�{����J�L�,hp\��B���<�q��lF�yp���M���G\����?%�� �X��-&����8| ,/��)��r��*.�[ (��Qk�	教֭��R���75���'�*�X��_ 9	��u�]w���E�\s_��	�G�h4o!��c��SŌyP:'5���T�XER d�$z�k,��U�Y!�ML�$��O�f��ُ������g��K2K��)�1�2�n�Ozc-��]�c�G�Mn�E2�$�U��	��z*Tvw�KH���$v� ]��>�1�,����ZK4�'�}x��׺�lg�� Y�ir��ɹ��[|��#����S����	1��K�Xf��ײQ~G�x�U�oV�$�	�b�����������B}�l��6��\!}�]4�1�bz��k�4���u��ew���}�\��g����٠9��g̡W�g9UN{GD�t�2��i͚��s2:��0��r���hU�(�vع���y.m�~����M      �	   1  x���mo�8�?ǿ��@�8n^�ߺ}��!�I��,`(6��*K�$����#gHj(QE*t�Ր�C�CJ�����ur~�,��qq�������?���>����nW��f�~��i�u~`�i�c�k.��l]�l�n�ϵ��(w�*ݔ���a~H���������2V6�%������b�ְ��,#�?
����P��FY��3�}w��.gWc:U�]��CQW��GM,�ށ�J��d���Ƅ���]�ٻm�K�|�o�m!^��oJ�=7�NY�V����m�DY���	iq9[�{���Ta�qu(6����}E��x+��Lx*\�Z��jv�˛��ꯠ�4�J	��b�X�2j�j]��}yh3�-�Oߋ[��e�d��RaN��.y�l�=��AkCo7E�����?(f�������~����b�x������7F�^�Z_F�H�[%aKA�-�t��̱bL68���;��@�c٪d�}���ߡ�]�-Π���B���o}/>)U�D�)-I�ݮg�1\�&=����L�(��Qc����"|u�y�e %�gR��X@�f��]��%����d�$�vöݳlSQ��(�H�:
E�0����<�~���}fp��"�Q$J��"[oXPd���b\rgCh�v�Zv{�-�U���'Ɏka����ks�Ȓ7�@���B����ns�P�Sߓ�p�..g�݌?o{����v����K����j�F�x*���ۤ�[V��|������4;����Zc\��DY�<jh=Vd��N��~�S��e�f���/�w���,��^���V#�:����(����8����l鱀��3Z�y`2��m.:��vX��X��G"��b%=z�g巌��ο�|�[������㚯8(�%l�:.(ka�Y��h2���9^��Ef-�!�*4�<�:n
�E��)�-�9۩b��8�K��ޠD���x90���?����?�Q��]TI�eǮg��%(���U��3�����R�����ǐ(������y��A�;%Wd�0���E)�O�$ WN \�,��W �h."��"�(��B�.� �8���َH;��"C;��.J՘#��Z12X�:<S�t8p�qR��cK8:�'3�T3J��;~���k-`:���<𰵉E���N���R'�h�(!5ub�4E`�k� Դ��k-5��%��ki���*�0�����d<y��'��:E�IT���d�.��q�5x����v;��LG�0������\B;�_N��\K"��_����,:���̝ifǬ����15S�W_�|��#/�^��f���.�]��Rج1��,`1�gk� ;�X�{���I���� ���`1���c���*�^�0��݁䳬�a[�}�hK[{T�0��	T[�`A��VD�n+ ����U�V"��+qiK|Gh�V"�jp+qiK
|a��L~[�K[��濕��ź� [���Kv�N �w�L!Al�Sz(����\�r��tIP���$�ʖM��b�$h�`eM��9�ߑņ+�$P9�ߑ�L���\y.�V�]Y�ă�m�̽�����޳_c��OQcC��?�6ԙ����bÍ�4�PW�gW1+.�;3#�<���jI���r+cb�8tnԂ�'�&�9H�1K4";6��s�.߁���#��.Ǻ�ɵa�W�$�_T �
4u��Ns�X�}���(1�e��"��!$�;9Vמ뼃��*���'�ǷZڝy��I�[:�䍙�Q�c������&�";�*��Y)��҆��a�U�?A_E�+��`T���h(���:���4��7�屾o���:eO:�=B���Uœ��n����V�
��UL����MI�u�b&^_ͮ=���8����>���hjL�#�jK�h�;��6�J;�kl����#C�g7t��LO�&poefŮ��\�3����;��2��Ŏ�d����bv��(�-s^�Cӏ#����H_��o.ϥ�}����~/�{�^P��m_��z���A������^��n���J������7���Bm������RW��!��ېy����Ǡ`U967��['>��-�������F1�V�ܪ����'4_�E-�����p�N_�0:bd�p��c�D5C	���N:�
�� ��ib�4�Da�~1V{� Nb�i�ߴ�҂t:VP��ǸLk���e��W������D>3Q�C�ٮqk慄&����\F�g拾��V�n�M�'A��3އ��Y�x�4����A;������QGV�E�.�֜�~F�[>0�W�S����}���DY��f�8:�ܮ���q�zDOZ�{���}�M��A�	בH3�6�s�z(���p�����ߊ�>V]abT�7&&`"��qXKV��T��1�Xa�1E�0�h"��.�1��9�x���)q4d3��#
�穴d`����&0�9+�6�m�Y���.����M�'��ҥa��J?���:ǚ����Ԙ��Q�-۠�i3�7���|zN��p�gp�9n3�].,q{�Q�i���e5{�;=啓���b
84���W�4L`���߱1����8}��y��FWc����X�s���|V��[W�~:K��M���Ex~՟q���'�4 �@��$J��cj:'�SLG���x
�>��.G|�C����e0u�A����p��4�>�㩞k���@6����9ޮ71{[����<S����M�s/7HN�LLMU�~G�z��V[�2HK�LLKU�Z�h�����.�yġ^2�#~�	���o����N��Lㄿ+x�9_�����׋���ˈ`?y�|�&�[�;��t��-�/���M|�&۾��M��ͩ~��������hx���`x߷����2�|y?�m��ޱ)�Q�N���d��(�&[M^zJ[{�����g��Z��&���dhM|=v�W>��֟j�0�!]=�~�e#�O��+���o�$=|)�\z��|WǗh��廊�P�H��D��(	L�+�d�?	m!DR	$��<�u�\��sy�o�=�?�����F��      �	      x������ � �      �	      x������ � �     