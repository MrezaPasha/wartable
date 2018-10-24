PGDMP     2    .            	    v            wtdb    10.4    10.4 �    �	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �	           1262    16384    wtdb    DATABASE     �   CREATE DATABASE wtdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Persian_Iran.1256' LC_CTYPE = 'Persian_Iran.1256';
    DROP DATABASE wtdb;
             sa_wt    false                        2615    59316    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                        3079    12278    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �	           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    59318    Service.Admin.Account    TABLE        CREATE TABLE public."Service.Admin.Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "accountGroup_id" bigint,
    "accountModel_id" bigint
);
 +   DROP TABLE public."Service.Admin.Account";
       public         postgres    false    3            �            1259    59323    Service.Admin.Account.Group    TABLE     �   CREATE TABLE public."Service.Admin.Account.Group" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 1   DROP TABLE public."Service.Admin.Account.Group";
       public         postgres    false    3            �            1259    59328    Service.Admin.Account.Model    TABLE     �   CREATE TABLE public."Service.Admin.Account.Model" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 1   DROP TABLE public."Service.Admin.Account.Model";
       public         postgres    false    3            �            1259    59333    Service.Admin.Notification    TABLE     �   CREATE TABLE public."Service.Admin.Notification" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 0   DROP TABLE public."Service.Admin.Notification";
       public         postgres    false    3            �            1259    59338 "   Service.Admin.Notification_Account    TABLE       CREATE TABLE public."Service.Admin.Notification_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    notification_id bigint
);
 8   DROP TABLE public."Service.Admin.Notification_Account";
       public         postgres    false    3            �            1259    59343    Service.Layer    TABLE     �   CREATE TABLE public."Service.Layer" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 #   DROP TABLE public."Service.Layer";
       public         postgres    false    3            �            1259    59348    Service.Layer_Map    TABLE     �   CREATE TABLE public."Service.Layer_Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    layer_id bigint,
    map_id bigint
);
 '   DROP TABLE public."Service.Layer_Map";
       public         postgres    false    3            �            1259    59353    Service.Map    TABLE     �   CREATE TABLE public."Service.Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "mapRegion_id" bigint
);
 !   DROP TABLE public."Service.Map";
       public         postgres    false    3            �            1259    59358    Service.Map.Region    TABLE     �   CREATE TABLE public."Service.Map.Region" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 (   DROP TABLE public."Service.Map.Region";
       public         postgres    false    3            �            1259    59363    Service.Map_Room    TABLE     �   CREATE TABLE public."Service.Map_Room" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    room_id bigint
);
 &   DROP TABLE public."Service.Map_Room";
       public         postgres    false    3            �            1259    59368    Service.Material    TABLE     �   CREATE TABLE public."Service.Material" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 &   DROP TABLE public."Service.Material";
       public         postgres    false    3            �            1259    59373    Service.Material.Area    TABLE     �   CREATE TABLE public."Service.Material.Area" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    material_id bigint
);
 +   DROP TABLE public."Service.Material.Area";
       public         postgres    false    3            �            1259    59378    Service.Material.Group    TABLE     �   CREATE TABLE public."Service.Material.Group" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 ,   DROP TABLE public."Service.Material.Group";
       public         postgres    false    3            �            1259    59383    Service.Material.Material_Group    TABLE     y   CREATE TABLE public."Service.Material.Material_Group" (
    group_id bigint NOT NULL,
    material_id bigint NOT NULL
);
 5   DROP TABLE public."Service.Material.Material_Group";
       public         postgres    false    3            �            1259    59388    Service.Material_Map    TABLE     �   CREATE TABLE public."Service.Material_Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    material_id bigint
);
 *   DROP TABLE public."Service.Material_Map";
       public         postgres    false    3            �            1259    59393    Service.Meeting    TABLE     �   CREATE TABLE public."Service.Meeting" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    room_id bigint
);
 %   DROP TABLE public."Service.Meeting";
       public         postgres    false    3            �            1259    59398    Service.Meeting_Account    TABLE     �   CREATE TABLE public."Service.Meeting_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    meeting_id bigint
);
 -   DROP TABLE public."Service.Meeting_Account";
       public         postgres    false    3            �            1259    59403    Service.Room    TABLE     �   CREATE TABLE public."Service.Room" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "activeMeeting_id" bigint,
    "adminAccount_id" bigint
);
 "   DROP TABLE public."Service.Room";
       public         postgres    false    3            �            1259    59408    Service.Room_Account    TABLE     �   CREATE TABLE public."Service.Room_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    room_id bigint
);
 *   DROP TABLE public."Service.Room_Account";
       public         postgres    false    3            �            1259    59413    Web.Admin.User    TABLE       CREATE TABLE public."Web.Admin.User" (
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
       public         postgres    false    3            �            1259    59425    Web.Admin.User.Group    TABLE     j  CREATE TABLE public."Web.Admin.User.Group" (
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
       public         postgres    false    3            �            1259    59430    Web.Admin.User.Porter    TABLE     �  CREATE TABLE public."Web.Admin.User.Porter" (
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
       public         postgres    false    3            �            1259    59439    Web.Admin.User.User_UserGroup    TABLE     y   CREATE TABLE public."Web.Admin.User.User_UserGroup" (
    "userGroup_id" bigint NOT NULL,
    user_id bigint NOT NULL
);
 3   DROP TABLE public."Web.Admin.User.User_UserGroup";
       public         postgres    false    3            �            1259    59444    Web.Admin.User.Uuid    TABLE     �  CREATE TABLE public."Web.Admin.User.Uuid" (
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
       public         postgres    false    3            �            1259    59452    Web.Admin.UserConfirm    TABLE     �  CREATE TABLE public."Web.Admin.UserConfirm" (
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
       public         postgres    false    3            �            1259    59457    Web.Archive.Directory    TABLE     �  CREATE TABLE public."Web.Archive.Directory" (
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
       public         postgres    false    3            �            1259    59465    Web.Archive.File    TABLE     �  CREATE TABLE public."Web.Archive.File" (
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
       public         postgres    false    3            �            1259    59473    Web.Archive.File.Download    TABLE       CREATE TABLE public."Web.Archive.File.Download" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    status integer,
    file_id bigint,
    user_id bigint
);
 /   DROP TABLE public."Web.Archive.File.Download";
       public         postgres    false    3            �            1259    59478    Web.Note    TABLE     �  CREATE TABLE public."Web.Note" (
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
       public         postgres    false    3            �            1259    59486    Web.System.Backup    TABLE        CREATE TABLE public."Web.System.Backup" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "backupDateTime" character varying(30),
    file_id bigint
);
 '   DROP TABLE public."Web.System.Backup";
       public         postgres    false    3            �            1259    59491    Web.System.Field    TABLE       CREATE TABLE public."Web.System.Field" (
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
       public         postgres    false    3            �            1259    59499    Web.System.Irror    TABLE     �  CREATE TABLE public."Web.System.Irror" (
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
       public         postgres    false    3            �            1259    59508    Web.System.Log    TABLE     �  CREATE TABLE public."Web.System.Log" (
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
       public         postgres    false    3            �            1259    59516    Web.System.Log.DailyLog    TABLE     Z  CREATE TABLE public."Web.System.Log.DailyLog" (
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
       public         postgres    false    3            �            1259    59521    Web.System.Log.SigninLog    TABLE       CREATE TABLE public."Web.System.Log.SigninLog" (
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
       public         postgres    false    3            �            1259    59529    Web.System.Log.User    TABLE     1  CREATE TABLE public."Web.System.Log.User" (
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
       public         postgres    false    3            �            1259    59538    Web.System.Model    TABLE     �  CREATE TABLE public."Web.System.Model" (
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
       public         postgres    false    3            �            1259    59546    Web.System.Module    TABLE     �  CREATE TABLE public."Web.System.Module" (
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
       public         postgres    false    3            �            1259    59554    Web.System.Registery    TABLE     w  CREATE TABLE public."Web.System.Registery" (
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
       public         postgres    false    3            �            1259    59562    Web.System.Task    TABLE     �  CREATE TABLE public."Web.System.Task" (
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
       public         postgres    false    3            �            1259    59570    Web.System.Task_User    TABLE     i   CREATE TABLE public."Web.System.Task_User" (
    user_id bigint NOT NULL,
    task_id bigint NOT NULL
);
 *   DROP TABLE public."Web.System.Task_User";
       public         postgres    false    3            �            1259    59575    Web.System.Task_UserGroup    TABLE     u   CREATE TABLE public."Web.System.Task_UserGroup" (
    "userGroup_id" bigint NOT NULL,
    task_id bigint NOT NULL
);
 /   DROP TABLE public."Web.System.Task_UserGroup";
       public         postgres    false    3            �            1259    59580    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    3            �	          0    59318    Service.Admin.Account 
   TABLE DATA               �   COPY public."Service.Admin.Account" (id, "createDateTime", "entityState", "modifyDateTime", "accountGroup_id", "accountModel_id") FROM stdin;
    public       postgres    false    196    5      �	          0    59323    Service.Admin.Account.Group 
   TABLE DATA               n   COPY public."Service.Admin.Account.Group" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    197   5      �	          0    59328    Service.Admin.Account.Model 
   TABLE DATA               n   COPY public."Service.Admin.Account.Model" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    198   :5      �	          0    59333    Service.Admin.Notification 
   TABLE DATA               m   COPY public."Service.Admin.Notification" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    199   W5      �	          0    59338 "   Service.Admin.Notification_Account 
   TABLE DATA               �   COPY public."Service.Admin.Notification_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, notification_id) FROM stdin;
    public       postgres    false    200   t5      �	          0    59343    Service.Layer 
   TABLE DATA               `   COPY public."Service.Layer" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    201   �5      �	          0    59348    Service.Layer_Map 
   TABLE DATA               v   COPY public."Service.Layer_Map" (id, "createDateTime", "entityState", "modifyDateTime", layer_id, map_id) FROM stdin;
    public       postgres    false    202   �5      �	          0    59353    Service.Map 
   TABLE DATA               n   COPY public."Service.Map" (id, "createDateTime", "entityState", "modifyDateTime", "mapRegion_id") FROM stdin;
    public       postgres    false    203   �5      �	          0    59358    Service.Map.Region 
   TABLE DATA               e   COPY public."Service.Map.Region" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    204   �5      �	          0    59363    Service.Map_Room 
   TABLE DATA               t   COPY public."Service.Map_Room" (id, "createDateTime", "entityState", "modifyDateTime", map_id, room_id) FROM stdin;
    public       postgres    false    205   6      �	          0    59368    Service.Material 
   TABLE DATA               c   COPY public."Service.Material" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    206   "6      �	          0    59373    Service.Material.Area 
   TABLE DATA               u   COPY public."Service.Material.Area" (id, "createDateTime", "entityState", "modifyDateTime", material_id) FROM stdin;
    public       postgres    false    207   ?6      �	          0    59378    Service.Material.Group 
   TABLE DATA               i   COPY public."Service.Material.Group" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    208   \6      �	          0    59383    Service.Material.Material_Group 
   TABLE DATA               R   COPY public."Service.Material.Material_Group" (group_id, material_id) FROM stdin;
    public       postgres    false    209   y6      �	          0    59388    Service.Material_Map 
   TABLE DATA               |   COPY public."Service.Material_Map" (id, "createDateTime", "entityState", "modifyDateTime", map_id, material_id) FROM stdin;
    public       postgres    false    210   �6      �	          0    59393    Service.Meeting 
   TABLE DATA               s   COPY public."Service.Meeting" (id, "createDateTime", "entityState", "modifyDateTime", map_id, room_id) FROM stdin;
    public       postgres    false    211   �6      �	          0    59398    Service.Meeting_Account 
   TABLE DATA               �   COPY public."Service.Meeting_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, meeting_id) FROM stdin;
    public       postgres    false    212   �6      �	          0    59403    Service.Room 
   TABLE DATA               �   COPY public."Service.Room" (id, "createDateTime", "entityState", "modifyDateTime", "activeMeeting_id", "adminAccount_id") FROM stdin;
    public       postgres    false    213   �6      �	          0    59408    Service.Room_Account 
   TABLE DATA               |   COPY public."Service.Room_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, room_id) FROM stdin;
    public       postgres    false    214   
7      �	          0    59413    Web.Admin.User 
   TABLE DATA               �  COPY public."Web.Admin.User" (id, "createDateTime", "entityState", "modifyDateTime", "accessLimitDailyEnd", "accessLimitDailyStart", "accessLimitMonthlyEnd", "accessLimitMonthlyStart", "accessLimitTimelyEnd", "accessLimitTimelyStart", "accessLimitYearlyEnd", "accessLimitYearlyStart", comment, "firstName", gender, "ipAddress", "ipAddressEnd", "ipAddressFirstSignin", "ipAddressStart", "ipRangeType", "isBlocked", "isLogManager", "isNeedToChangePassword", "isSuperAdmin", "lastName", "lastSigninDateTime", level, password, "passwordDateTime", "passwordHistory", "porterUuid", status, "superAdminCode", "superAdminLogingId", "superAdminLogingIn", "userCode", username, logo_id) FROM stdin;
    public       postgres    false    215   '7      �	          0    59425    Web.Admin.User.Group 
   TABLE DATA               �   COPY public."Web.Admin.User.Group" (id, "createDateTime", "entityState", "modifyDateTime", code, "memberCount", "subCount", title, parent_id) FROM stdin;
    public       postgres    false    216   '8      �	          0    59430    Web.Admin.User.Porter 
   TABLE DATA               @  COPY public."Web.Admin.User.Porter" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "confirmCode", "confirmCodeDateTimeG", count, "expireDateTimeG", "isActiveTwoStepConfirm", "isConfirmed", "signoutStatus", "singinDateTimeG", "singoutDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    217   D8      �	          0    59439    Web.Admin.User.User_UserGroup 
   TABLE DATA               R   COPY public."Web.Admin.User.User_UserGroup" ("userGroup_id", user_id) FROM stdin;
    public       postgres    false    218   a8      �	          0    59444    Web.Admin.User.Uuid 
   TABLE DATA               �   COPY public."Web.Admin.User.Uuid" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "expireDateTimeG", "singinDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    219   ~8      �	          0    59452    Web.Admin.UserConfirm 
   TABLE DATA               �   COPY public."Web.Admin.UserConfirm" (id, "createDateTime", "entityState", "modifyDateTime", "callCount", "confirmDateTime", "confirmDateTimeG", "taskSignature", task_id, user_id) FROM stdin;
    public       postgres    false    220   �8      �	          0    59457    Web.Archive.Directory 
   TABLE DATA                 COPY public."Web.Archive.Directory" (id, "createDateTime", "entityState", "modifyDateTime", "className", comment, "containedDirectoryCount", "containedFileCount", "directoryOrigin", icon, "isPermanent", level, name, path, "repoDirectory", url, owner_id, parent_id) FROM stdin;
    public       postgres    false    221   �8      �	          0    59465    Web.Archive.File 
   TABLE DATA               �  COPY public."Web.Archive.File" (id, "createDateTime", "entityState", "modifyDateTime", "accessLevel", "accessRule", "contentType", "directoryAbsolutePath", "directoryRelativePath", "downloadCount", "downloadCountGuest", "fetchManner", "isContainOrginal", "isTemporary", "lastUploadDateTime", "orginalName", size, thumbnails, title, "tryToDownloadCount", "tryToDownloadCountGuest", "uploadLink", "uploadLinkComment", "uploadStatus", directory_id, owner_id) FROM stdin;
    public       postgres    false    222   L9      �	          0    59473    Web.Archive.File.Download 
   TABLE DATA               �   COPY public."Web.Archive.File.Download" (id, "createDateTime", "entityState", "modifyDateTime", status, file_id, user_id) FROM stdin;
    public       postgres    false    223   i9      �	          0    59478    Web.Note 
   TABLE DATA               �   COPY public."Web.Note" (id, "createDateTime", "entityState", "modifyDateTime", "dateTime", "dateTimeG", importance, "isNotified", "isVisited", message, title, user_id) FROM stdin;
    public       postgres    false    224   �9      �	          0    59486    Web.System.Backup 
   TABLE DATA                  COPY public."Web.System.Backup" (id, "createDateTime", "entityState", "modifyDateTime", "backupDateTime", file_id) FROM stdin;
    public       postgres    false    225   �9      �	          0    59491    Web.System.Field 
   TABLE DATA                 COPY public."Web.System.Field" (id, "createDateTime", "entityState", "modifyDateTime", "dbConstraint", "dbDefaultValue", "dbExtra", "dbIndex", "dbKey", "dbRefColumn", "dbRefTable", "dbSize", "dbTitle", "dbType", "isBidirectional", "isDbFK", "isDbNullable", "isDbPrimary", "isDbRefreshed", "isEncrypted", "isMoNullable", "isMoRefreshed", "moAnnotations", "moDataRelation", "moDataRelationDes", "moDefaultValue", "moExtra", "moKey", "moMaxSize", "moMinSize", "moModifier", "moPreviousTitle", "moTitle", "moType", status, model_id) FROM stdin;
    public       postgres    false    226   �9      �	          0    59499    Web.System.Irror 
   TABLE DATA                 COPY public."Web.System.Irror" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", cause, "computerSignature", "httpErrorCode", "isVisited", level, message, place, "porterUuid", "sessionId", status, "taskName", "visitCount", user_id) FROM stdin;
    public       postgres    false    227   �9      �	          0    59508    Web.System.Log 
   TABLE DATA               �  COPY public."Web.System.Log" (id, "createDateTime", "entityState", "modifyDateTime", "actionType", "agentSignature", "computerSignature", "dateTimeG", "httpCode", "importanceLevel", "isTaskTwoLevelConfirm", message, "onlineLoggingStrategy", "portNumber", "porterUuid", "requestMethod", "sendDateTimeG", "sendStatus", sensitivity, "serverId", "sessionId", "taskName", "taskTitle", url, "userGroupId", "userId", "userLevel") FROM stdin;
    public       postgres    false    228   �9      �	          0    59516    Web.System.Log.DailyLog 
   TABLE DATA               �   COPY public."Web.System.Log.DailyLog" (id, "createDateTime", "entityState", "modifyDateTime", chrome, "dayDate", desktop, firefox, "guestVisitCount", "internetExplorer", mobile, netscape, opera, "pageCount", "userVisitCount", "visitCount") FROM stdin;
    public       postgres    false    229   �=      �	          0    59521    Web.System.Log.SigninLog 
   TABLE DATA               �   COPY public."Web.System.Log.SigninLog" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "domainName", "ipAddress", "lastDateTime", "lastDateTimeG", status, uuid, user_id) FROM stdin;
    public       postgres    false    230   �=      �	          0    59529    Web.System.Log.User 
   TABLE DATA               �   COPY public."Web.System.Log.User" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "attemptType", "computerSignature", count, "dateTimeG", "isSuccess", "lastDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    231   �>      �	          0    59538    Web.System.Model 
   TABLE DATA               �   COPY public."Web.System.Model" (id, "createDateTime", "entityState", "modifyDateTime", "className", "isRefreshed", "messageCode", "packageName", status, "tableName") FROM stdin;
    public       postgres    false    232   �>      �	          0    59546    Web.System.Module 
   TABLE DATA               �   COPY public."Web.System.Module" (id, "createDateTime", "entityState", "modifyDateTime", "className", "isProtected", "isRefreshed", "menuMessageCode", "messageCode", "packageName", url) FROM stdin;
    public       postgres    false    233   ?      �	          0    59554    Web.System.Registery 
   TABLE DATA               �   COPY public."Web.System.Registery" (id, "createDateTime", "entityState", "modifyDateTime", key, title, type, value, "valueJson", "valueType") FROM stdin;
    public       postgres    false    234   C      �	          0    59562    Web.System.Task 
   TABLE DATA               �  COPY public."Web.System.Task" (id, "createDateTime", "entityState", "modifyDateTime", "accessLevel", "actionType", "importanceLevel", "isActive", "isActiveLogging", "isAjax", "isLogManager", "isOnlineLogging", "isPanelTask", "isRefreshed", "isSuperAdmin", "isTwoLevelConfirm", "menuIdentity", "menuMessageCode", "messageCode", method, "onlineLoggingDelay", "onlineLoggingStrategy", "onlineSchedulingTime", sensitivity, signature, url, module_id) FROM stdin;
    public       postgres    false    235   ;E      �	          0    59570    Web.System.Task_User 
   TABLE DATA               B   COPY public."Web.System.Task_User" (user_id, task_id) FROM stdin;
    public       postgres    false    236   RQ      �	          0    59575    Web.System.Task_UserGroup 
   TABLE DATA               N   COPY public."Web.System.Task_UserGroup" ("userGroup_id", task_id) FROM stdin;
    public       postgres    false    237   oQ      �	           0    0    hibernate_sequence    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hibernate_sequence', 172, true);
            public       postgres    false    238            �           2606    59327 <   Service.Admin.Account.Group Service.Admin.Account.Group_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public."Service.Admin.Account.Group"
    ADD CONSTRAINT "Service.Admin.Account.Group_pkey" PRIMARY KEY (id);
 j   ALTER TABLE ONLY public."Service.Admin.Account.Group" DROP CONSTRAINT "Service.Admin.Account.Group_pkey";
       public         postgres    false    197            �           2606    59332 <   Service.Admin.Account.Model Service.Admin.Account.Model_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public."Service.Admin.Account.Model"
    ADD CONSTRAINT "Service.Admin.Account.Model_pkey" PRIMARY KEY (id);
 j   ALTER TABLE ONLY public."Service.Admin.Account.Model" DROP CONSTRAINT "Service.Admin.Account.Model_pkey";
       public         postgres    false    198            �           2606    59322 0   Service.Admin.Account Service.Admin.Account_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Service.Admin.Account"
    ADD CONSTRAINT "Service.Admin.Account_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "Service.Admin.Account_pkey";
       public         postgres    false    196            �           2606    59342 J   Service.Admin.Notification_Account Service.Admin.Notification_Account_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Notification_Account"
    ADD CONSTRAINT "Service.Admin.Notification_Account_pkey" PRIMARY KEY (id);
 x   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "Service.Admin.Notification_Account_pkey";
       public         postgres    false    200            �           2606    59337 :   Service.Admin.Notification Service.Admin.Notification_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY public."Service.Admin.Notification"
    ADD CONSTRAINT "Service.Admin.Notification_pkey" PRIMARY KEY (id);
 h   ALTER TABLE ONLY public."Service.Admin.Notification" DROP CONSTRAINT "Service.Admin.Notification_pkey";
       public         postgres    false    199            �           2606    59352 (   Service.Layer_Map Service.Layer_Map_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Service.Layer_Map"
    ADD CONSTRAINT "Service.Layer_Map_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "Service.Layer_Map_pkey";
       public         postgres    false    202            �           2606    59347     Service.Layer Service.Layer_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public."Service.Layer"
    ADD CONSTRAINT "Service.Layer_pkey" PRIMARY KEY (id);
 N   ALTER TABLE ONLY public."Service.Layer" DROP CONSTRAINT "Service.Layer_pkey";
       public         postgres    false    201            �           2606    59362 *   Service.Map.Region Service.Map.Region_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public."Service.Map.Region"
    ADD CONSTRAINT "Service.Map.Region_pkey" PRIMARY KEY (id);
 X   ALTER TABLE ONLY public."Service.Map.Region" DROP CONSTRAINT "Service.Map.Region_pkey";
       public         postgres    false    204            �           2606    59367 &   Service.Map_Room Service.Map_Room_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Service.Map_Room"
    ADD CONSTRAINT "Service.Map_Room_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "Service.Map_Room_pkey";
       public         postgres    false    205            �           2606    59357    Service.Map Service.Map_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public."Service.Map"
    ADD CONSTRAINT "Service.Map_pkey" PRIMARY KEY (id);
 J   ALTER TABLE ONLY public."Service.Map" DROP CONSTRAINT "Service.Map_pkey";
       public         postgres    false    203            �           2606    59377 0   Service.Material.Area Service.Material.Area_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Service.Material.Area"
    ADD CONSTRAINT "Service.Material.Area_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Service.Material.Area" DROP CONSTRAINT "Service.Material.Area_pkey";
       public         postgres    false    207            �           2606    59382 2   Service.Material.Group Service.Material.Group_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public."Service.Material.Group"
    ADD CONSTRAINT "Service.Material.Group_pkey" PRIMARY KEY (id);
 `   ALTER TABLE ONLY public."Service.Material.Group" DROP CONSTRAINT "Service.Material.Group_pkey";
       public         postgres    false    208            �           2606    59387 D   Service.Material.Material_Group Service.Material.Material_Group_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Material_Group"
    ADD CONSTRAINT "Service.Material.Material_Group_pkey" PRIMARY KEY (material_id, group_id);
 r   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "Service.Material.Material_Group_pkey";
       public         postgres    false    209    209            �           2606    59392 .   Service.Material_Map Service.Material_Map_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Service.Material_Map"
    ADD CONSTRAINT "Service.Material_Map_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "Service.Material_Map_pkey";
       public         postgres    false    210            �           2606    59372 &   Service.Material Service.Material_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Service.Material"
    ADD CONSTRAINT "Service.Material_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Service.Material" DROP CONSTRAINT "Service.Material_pkey";
       public         postgres    false    206            �           2606    59402 4   Service.Meeting_Account Service.Meeting_Account_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public."Service.Meeting_Account"
    ADD CONSTRAINT "Service.Meeting_Account_pkey" PRIMARY KEY (id);
 b   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "Service.Meeting_Account_pkey";
       public         postgres    false    212            �           2606    59397 $   Service.Meeting Service.Meeting_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."Service.Meeting"
    ADD CONSTRAINT "Service.Meeting_pkey" PRIMARY KEY (id);
 R   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "Service.Meeting_pkey";
       public         postgres    false    211            �           2606    59412 .   Service.Room_Account Service.Room_Account_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Service.Room_Account"
    ADD CONSTRAINT "Service.Room_Account_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "Service.Room_Account_pkey";
       public         postgres    false    214            �           2606    59407    Service.Room Service.Room_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."Service.Room"
    ADD CONSTRAINT "Service.Room_pkey" PRIMARY KEY (id);
 L   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "Service.Room_pkey";
       public         postgres    false    213            �           2606    59429 .   Web.Admin.User.Group Web.Admin.User.Group_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Web.Admin.User.Group"
    ADD CONSTRAINT "Web.Admin.User.Group_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Web.Admin.User.Group" DROP CONSTRAINT "Web.Admin.User.Group_pkey";
       public         postgres    false    216            �           2606    59438 0   Web.Admin.User.Porter Web.Admin.User.Porter_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Web.Admin.User.Porter"
    ADD CONSTRAINT "Web.Admin.User.Porter_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Admin.User.Porter" DROP CONSTRAINT "Web.Admin.User.Porter_pkey";
       public         postgres    false    217            �           2606    59443 @   Web.Admin.User.User_UserGroup Web.Admin.User.User_UserGroup_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "Web.Admin.User.User_UserGroup_pkey" PRIMARY KEY (user_id, "userGroup_id");
 n   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "Web.Admin.User.User_UserGroup_pkey";
       public         postgres    false    218    218            �           2606    59451 ,   Web.Admin.User.Uuid Web.Admin.User.Uuid_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public."Web.Admin.User.Uuid"
    ADD CONSTRAINT "Web.Admin.User.Uuid_pkey" PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public."Web.Admin.User.Uuid" DROP CONSTRAINT "Web.Admin.User.Uuid_pkey";
       public         postgres    false    219            �           2606    59456 0   Web.Admin.UserConfirm Web.Admin.UserConfirm_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Web.Admin.UserConfirm"
    ADD CONSTRAINT "Web.Admin.UserConfirm_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "Web.Admin.UserConfirm_pkey";
       public         postgres    false    220            �           2606    59424 "   Web.Admin.User Web.Admin.User_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public."Web.Admin.User"
    ADD CONSTRAINT "Web.Admin.User_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public."Web.Admin.User" DROP CONSTRAINT "Web.Admin.User_pkey";
       public         postgres    false    215            �           2606    59464 0   Web.Archive.Directory Web.Archive.Directory_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Web.Archive.Directory"
    ADD CONSTRAINT "Web.Archive.Directory_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "Web.Archive.Directory_pkey";
       public         postgres    false    221            �           2606    59477 8   Web.Archive.File.Download Web.Archive.File.Download_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public."Web.Archive.File.Download"
    ADD CONSTRAINT "Web.Archive.File.Download_pkey" PRIMARY KEY (id);
 f   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "Web.Archive.File.Download_pkey";
       public         postgres    false    223            �           2606    59472 &   Web.Archive.File Web.Archive.File_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.Archive.File"
    ADD CONSTRAINT "Web.Archive.File_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "Web.Archive.File_pkey";
       public         postgres    false    222            �           2606    59485    Web.Note Web.Note_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Web.Note"
    ADD CONSTRAINT "Web.Note_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Web.Note" DROP CONSTRAINT "Web.Note_pkey";
       public         postgres    false    224            �           2606    59490 (   Web.System.Backup Web.System.Backup_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Web.System.Backup"
    ADD CONSTRAINT "Web.System.Backup_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Web.System.Backup" DROP CONSTRAINT "Web.System.Backup_pkey";
       public         postgres    false    225            �           2606    59498 &   Web.System.Field Web.System.Field_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.System.Field"
    ADD CONSTRAINT "Web.System.Field_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Field" DROP CONSTRAINT "Web.System.Field_pkey";
       public         postgres    false    226            �           2606    59507 &   Web.System.Irror Web.System.Irror_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.System.Irror"
    ADD CONSTRAINT "Web.System.Irror_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Irror" DROP CONSTRAINT "Web.System.Irror_pkey";
       public         postgres    false    227            �           2606    59520 4   Web.System.Log.DailyLog Web.System.Log.DailyLog_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public."Web.System.Log.DailyLog"
    ADD CONSTRAINT "Web.System.Log.DailyLog_pkey" PRIMARY KEY (id);
 b   ALTER TABLE ONLY public."Web.System.Log.DailyLog" DROP CONSTRAINT "Web.System.Log.DailyLog_pkey";
       public         postgres    false    229            �           2606    59528 6   Web.System.Log.SigninLog Web.System.Log.SigninLog_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public."Web.System.Log.SigninLog"
    ADD CONSTRAINT "Web.System.Log.SigninLog_pkey" PRIMARY KEY (id);
 d   ALTER TABLE ONLY public."Web.System.Log.SigninLog" DROP CONSTRAINT "Web.System.Log.SigninLog_pkey";
       public         postgres    false    230            �           2606    59537 ,   Web.System.Log.User Web.System.Log.User_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public."Web.System.Log.User"
    ADD CONSTRAINT "Web.System.Log.User_pkey" PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public."Web.System.Log.User" DROP CONSTRAINT "Web.System.Log.User_pkey";
       public         postgres    false    231            �           2606    59515 "   Web.System.Log Web.System.Log_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public."Web.System.Log"
    ADD CONSTRAINT "Web.System.Log_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public."Web.System.Log" DROP CONSTRAINT "Web.System.Log_pkey";
       public         postgres    false    228            �           2606    59545 &   Web.System.Model Web.System.Model_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.System.Model"
    ADD CONSTRAINT "Web.System.Model_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Model" DROP CONSTRAINT "Web.System.Model_pkey";
       public         postgres    false    232            �           2606    59553 (   Web.System.Module Web.System.Module_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Web.System.Module"
    ADD CONSTRAINT "Web.System.Module_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Web.System.Module" DROP CONSTRAINT "Web.System.Module_pkey";
       public         postgres    false    233            �           2606    59561 .   Web.System.Registery Web.System.Registery_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Web.System.Registery"
    ADD CONSTRAINT "Web.System.Registery_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Web.System.Registery" DROP CONSTRAINT "Web.System.Registery_pkey";
       public         postgres    false    234            �           2606    59579 8   Web.System.Task_UserGroup Web.System.Task_UserGroup_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_UserGroup"
    ADD CONSTRAINT "Web.System.Task_UserGroup_pkey" PRIMARY KEY (task_id, "userGroup_id");
 f   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "Web.System.Task_UserGroup_pkey";
       public         postgres    false    237    237            �           2606    59574 .   Web.System.Task_User Web.System.Task_User_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public."Web.System.Task_User"
    ADD CONSTRAINT "Web.System.Task_User_pkey" PRIMARY KEY (task_id, user_id);
 \   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "Web.System.Task_User_pkey";
       public         postgres    false    236    236            �           2606    59569 $   Web.System.Task Web.System.Task_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."Web.System.Task"
    ADD CONSTRAINT "Web.System.Task_pkey" PRIMARY KEY (id);
 R   ALTER TABLE ONLY public."Web.System.Task" DROP CONSTRAINT "Web.System.Task_pkey";
       public         postgres    false    235            �           2606    59607 -   Service.Layer_Map FK13nj6p1jqt0xubl2bm7evx7ob    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Layer_Map"
    ADD CONSTRAINT "FK13nj6p1jqt0xubl2bm7evx7ob" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 [   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "FK13nj6p1jqt0xubl2bm7evx7ob";
       public       postgres    false    203    2225    202            	           2606    59777 +   Web.System.Irror FK179yr80p4adleu0bn1y54bkm    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Irror"
    ADD CONSTRAINT "FK179yr80p4adleu0bn1y54bkm" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 Y   ALTER TABLE ONLY public."Web.System.Irror" DROP CONSTRAINT "FK179yr80p4adleu0bn1y54bkm";
       public       postgres    false    215    2249    227            	           2606    59772 ,   Web.System.Field FK1es95ggvfg9vombcaiqtlv5j5    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Field"
    ADD CONSTRAINT "FK1es95ggvfg9vombcaiqtlv5j5" FOREIGN KEY (model_id) REFERENCES public."Web.System.Model"(id);
 Z   ALTER TABLE ONLY public."Web.System.Field" DROP CONSTRAINT "FK1es95ggvfg9vombcaiqtlv5j5";
       public       postgres    false    226    2283    232            	           2606    59692 *   Web.Admin.User FK1uwd6vtspmpndkcaw6f7c02jh    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User"
    ADD CONSTRAINT "FK1uwd6vtspmpndkcaw6f7c02jh" FOREIGN KEY (logo_id) REFERENCES public."Web.Archive.File"(id);
 X   ALTER TABLE ONLY public."Web.Admin.User" DROP CONSTRAINT "FK1uwd6vtspmpndkcaw6f7c02jh";
       public       postgres    false    222    2263    215            	           2606    59662 3   Service.Meeting_Account FK1xfplj8yodpqrd6lymh91clcv    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting_Account"
    ADD CONSTRAINT "FK1xfplj8yodpqrd6lymh91clcv" FOREIGN KEY (account_id) REFERENCES public."Service.Admin.Account"(id);
 a   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "FK1xfplj8yodpqrd6lymh91clcv";
       public       postgres    false    2211    196    212            	           2606    59712 9   Web.Admin.User.User_UserGroup FK3u8c5qf627qwin8qwvm2jocfy    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "FK3u8c5qf627qwin8qwvm2jocfy" FOREIGN KEY ("userGroup_id") REFERENCES public."Web.Admin.User.Group"(id);
 g   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "FK3u8c5qf627qwin8qwvm2jocfy";
       public       postgres    false    218    216    2251            	           2606    59767 -   Web.System.Backup FK4ch0kue8bh5lpvsyjqnl3qo7r    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Backup"
    ADD CONSTRAINT "FK4ch0kue8bh5lpvsyjqnl3qo7r" FOREIGN KEY (file_id) REFERENCES public."Web.Archive.File"(id);
 [   ALTER TABLE ONLY public."Web.System.Backup" DROP CONSTRAINT "FK4ch0kue8bh5lpvsyjqnl3qo7r";
       public       postgres    false    2263    222    225            	           2606    59752 5   Web.Archive.File.Download FK4kwt8ccqa8s3pm6wyhhhxhbor    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File.Download"
    ADD CONSTRAINT "FK4kwt8ccqa8s3pm6wyhhhxhbor" FOREIGN KEY (file_id) REFERENCES public."Web.Archive.File"(id);
 c   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "FK4kwt8ccqa8s3pm6wyhhhxhbor";
       public       postgres    false    223    2263    222            	           2606    59652 +   Service.Meeting FK5vxbkwhno5m5f6io9ewpfvfrt    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting"
    ADD CONSTRAINT "FK5vxbkwhno5m5f6io9ewpfvfrt" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 Y   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "FK5vxbkwhno5m5f6io9ewpfvfrt";
       public       postgres    false    203    2225    211             	           2606    59632 ;   Service.Material.Material_Group FK6lqodoftkhranmm8ll9qddtig    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Material_Group"
    ADD CONSTRAINT "FK6lqodoftkhranmm8ll9qddtig" FOREIGN KEY (material_id) REFERENCES public."Service.Material"(id);
 i   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "FK6lqodoftkhranmm8ll9qddtig";
       public       postgres    false    2231    206    209            		           2606    59677 (   Service.Room FK7y95kfit57u0f9xku0criwo18    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room"
    ADD CONSTRAINT "FK7y95kfit57u0f9xku0criwo18" FOREIGN KEY ("adminAccount_id") REFERENCES public."Service.Admin.Account"(id);
 V   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "FK7y95kfit57u0f9xku0criwo18";
       public       postgres    false    2211    213    196            	           2606    59737 1   Web.Archive.Directory FK86fcckds9fmo9vn6gnirngn7u    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.Directory"
    ADD CONSTRAINT "FK86fcckds9fmo9vn6gnirngn7u" FOREIGN KEY (parent_id) REFERENCES public."Web.Archive.Directory"(id);
 _   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "FK86fcckds9fmo9vn6gnirngn7u";
       public       postgres    false    221    221    2261            	           2606    59787 /   Web.System.Log.User FK8ld9m40vmrs5efepdj72eojlv    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Log.User"
    ADD CONSTRAINT "FK8ld9m40vmrs5efepdj72eojlv" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 ]   ALTER TABLE ONLY public."Web.System.Log.User" DROP CONSTRAINT "FK8ld9m40vmrs5efepdj72eojlv";
       public       postgres    false    215    2249    231            	           2606    59667 3   Service.Meeting_Account FK8nm941j0t4gv3bumcwsyg9s3n    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting_Account"
    ADD CONSTRAINT "FK8nm941j0t4gv3bumcwsyg9s3n" FOREIGN KEY (meeting_id) REFERENCES public."Service.Meeting"(id);
 a   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "FK8nm941j0t4gv3bumcwsyg9s3n";
       public       postgres    false    211    2241    212            	           2606    59637 ;   Service.Material.Material_Group FK9bdp8ucdge8uyvc90q92do2f8    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Material_Group"
    ADD CONSTRAINT "FK9bdp8ucdge8uyvc90q92do2f8" FOREIGN KEY (group_id) REFERENCES public."Service.Material.Group"(id);
 i   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "FK9bdp8ucdge8uyvc90q92do2f8";
       public       postgres    false    209    2235    208            	           2606    59747 ,   Web.Archive.File FKagip4f17ss7vj9s2wwrkcvm5r    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File"
    ADD CONSTRAINT "FKagip4f17ss7vj9s2wwrkcvm5r" FOREIGN KEY (owner_id) REFERENCES public."Web.Admin.User"(id);
 Z   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "FKagip4f17ss7vj9s2wwrkcvm5r";
       public       postgres    false    222    2249    215            �           2606    59617 ,   Service.Map_Room FKb3laq886tx4mhxma1549o6xby    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Map_Room"
    ADD CONSTRAINT "FKb3laq886tx4mhxma1549o6xby" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 Z   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "FKb3laq886tx4mhxma1549o6xby";
       public       postgres    false    205    203    2225            �           2606    59597 >   Service.Admin.Notification_Account FKbopt2hxcvrh141iy9cjjya4s2    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Notification_Account"
    ADD CONSTRAINT "FKbopt2hxcvrh141iy9cjjya4s2" FOREIGN KEY (notification_id) REFERENCES public."Service.Admin.Notification"(id);
 l   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "FKbopt2hxcvrh141iy9cjjya4s2";
       public       postgres    false    199    2217    200            �           2606    59582 1   Service.Admin.Account FKbs6slesa63l45yy54d0uvdglm    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Account"
    ADD CONSTRAINT "FKbs6slesa63l45yy54d0uvdglm" FOREIGN KEY ("accountGroup_id") REFERENCES public."Service.Admin.Account.Group"(id);
 _   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "FKbs6slesa63l45yy54d0uvdglm";
       public       postgres    false    197    2213    196            	           2606    59687 /   Service.Room_Account FKclhapld1q4n01ygcv0oif1i1    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room_Account"
    ADD CONSTRAINT "FKclhapld1q4n01ygcv0oif1i1" FOREIGN KEY (room_id) REFERENCES public."Service.Room"(id);
 ]   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "FKclhapld1q4n01ygcv0oif1i1";
       public       postgres    false    2245    214    213            �           2606    59622 ,   Service.Map_Room FKddf8seuxcgwn0ux9xh8nafo5r    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Map_Room"
    ADD CONSTRAINT "FKddf8seuxcgwn0ux9xh8nafo5r" FOREIGN KEY (room_id) REFERENCES public."Service.Room"(id);
 Z   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "FKddf8seuxcgwn0ux9xh8nafo5r";
       public       postgres    false    2245    205    213            	           2606    59697 0   Web.Admin.User.Group FKdhnm45lgors90in2fw2h2sdtq    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.Group"
    ADD CONSTRAINT "FKdhnm45lgors90in2fw2h2sdtq" FOREIGN KEY (parent_id) REFERENCES public."Web.Admin.User.Group"(id);
 ^   ALTER TABLE ONLY public."Web.Admin.User.Group" DROP CONSTRAINT "FKdhnm45lgors90in2fw2h2sdtq";
       public       postgres    false    2251    216    216            	           2606    59672 (   Service.Room FKe5eb6d3mlm3t7xekphhyndvt9    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room"
    ADD CONSTRAINT "FKe5eb6d3mlm3t7xekphhyndvt9" FOREIGN KEY ("activeMeeting_id") REFERENCES public."Service.Meeting"(id);
 V   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "FKe5eb6d3mlm3t7xekphhyndvt9";
       public       postgres    false    2241    211    213            	           2606    59742 +   Web.Archive.File FKev7boejuglytck8yuglcq1rd    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File"
    ADD CONSTRAINT "FKev7boejuglytck8yuglcq1rd" FOREIGN KEY (directory_id) REFERENCES public."Web.Archive.Directory"(id);
 Y   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "FKev7boejuglytck8yuglcq1rd";
       public       postgres    false    221    2261    222            
	           2606    59682 0   Service.Room_Account FKevw25cji9v1xvgt74eb675iyy    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room_Account"
    ADD CONSTRAINT "FKevw25cji9v1xvgt74eb675iyy" FOREIGN KEY (account_id) REFERENCES public."Service.Admin.Account"(id);
 ^   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "FKevw25cji9v1xvgt74eb675iyy";
       public       postgres    false    196    2211    214            #	           2606    59807 5   Web.System.Task_UserGroup FKflr8ben4uy9x748n06ounuldq    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_UserGroup"
    ADD CONSTRAINT "FKflr8ben4uy9x748n06ounuldq" FOREIGN KEY (task_id) REFERENCES public."Web.System.Task"(id);
 c   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "FKflr8ben4uy9x748n06ounuldq";
       public       postgres    false    235    237    2289            $	           2606    59812 5   Web.System.Task_UserGroup FKfmar5n0tdatsry2ttfu0ombrp    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_UserGroup"
    ADD CONSTRAINT "FKfmar5n0tdatsry2ttfu0ombrp" FOREIGN KEY ("userGroup_id") REFERENCES public."Web.Admin.User.Group"(id);
 c   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "FKfmar5n0tdatsry2ttfu0ombrp";
       public       postgres    false    237    216    2251            	           2606    59707 9   Web.Admin.User.User_UserGroup FKg72228uwjmtumk7o0khpnpaqb    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "FKg72228uwjmtumk7o0khpnpaqb" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 g   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "FKg72228uwjmtumk7o0khpnpaqb";
       public       postgres    false    218    2249    215            	           2606    59642 0   Service.Material_Map FKhc2x83k2jlmn29kq68o5a45um    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material_Map"
    ADD CONSTRAINT "FKhc2x83k2jlmn29kq68o5a45um" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 ^   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "FKhc2x83k2jlmn29kq68o5a45um";
       public       postgres    false    210    2225    203            	           2606    59722 1   Web.Admin.UserConfirm FKi4t2i0kg245d8fb6yj5xign2q    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.UserConfirm"
    ADD CONSTRAINT "FKi4t2i0kg245d8fb6yj5xign2q" FOREIGN KEY (task_id) REFERENCES public."Web.System.Task"(id);
 _   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "FKi4t2i0kg245d8fb6yj5xign2q";
       public       postgres    false    2289    220    235            	           2606    59647 0   Service.Material_Map FKj200gs4cgur5xq51a5kf82pel    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material_Map"
    ADD CONSTRAINT "FKj200gs4cgur5xq51a5kf82pel" FOREIGN KEY (material_id) REFERENCES public."Service.Material"(id);
 ^   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "FKj200gs4cgur5xq51a5kf82pel";
       public       postgres    false    206    210    2231            	           2606    59727 1   Web.Admin.UserConfirm FKk5lovfvt0apu4ni7wkpqg5147    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.UserConfirm"
    ADD CONSTRAINT "FKk5lovfvt0apu4ni7wkpqg5147" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 _   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "FKk5lovfvt0apu4ni7wkpqg5147";
       public       postgres    false    2249    220    215            	           2606    59702 1   Web.Admin.User.Porter FKkaat5quhoarvfx70f5m98q7j4    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.Porter"
    ADD CONSTRAINT "FKkaat5quhoarvfx70f5m98q7j4" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 _   ALTER TABLE ONLY public."Web.Admin.User.Porter" DROP CONSTRAINT "FKkaat5quhoarvfx70f5m98q7j4";
       public       postgres    false    2249    215    217            "	           2606    59802 0   Web.System.Task_User FKkx74jgadegi5o45ijchxvuw2m    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_User"
    ADD CONSTRAINT "FKkx74jgadegi5o45ijchxvuw2m" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 ^   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "FKkx74jgadegi5o45ijchxvuw2m";
       public       postgres    false    2249    236    215            	           2606    59757 5   Web.Archive.File.Download FKlmvo3wehmatw4p69t2498jrdr    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File.Download"
    ADD CONSTRAINT "FKlmvo3wehmatw4p69t2498jrdr" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 c   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "FKlmvo3wehmatw4p69t2498jrdr";
       public       postgres    false    223    2249    215            	           2606    59782 4   Web.System.Log.SigninLog FKly85hqgbrmb5scmawg6kyrtn6    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Log.SigninLog"
    ADD CONSTRAINT "FKly85hqgbrmb5scmawg6kyrtn6" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 b   ALTER TABLE ONLY public."Web.System.Log.SigninLog" DROP CONSTRAINT "FKly85hqgbrmb5scmawg6kyrtn6";
       public       postgres    false    215    2249    230            	           2606    59732 0   Web.Archive.Directory FKmwjxdube2yqdoh7qkoeag216    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.Directory"
    ADD CONSTRAINT "FKmwjxdube2yqdoh7qkoeag216" FOREIGN KEY (owner_id) REFERENCES public."Web.Admin.User"(id);
 ^   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "FKmwjxdube2yqdoh7qkoeag216";
       public       postgres    false    2249    221    215             	           2606    59792 +   Web.System.Task FKn1h41i6mfajsxx8vh8jdtu2u5    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task"
    ADD CONSTRAINT "FKn1h41i6mfajsxx8vh8jdtu2u5" FOREIGN KEY (module_id) REFERENCES public."Web.System.Module"(id);
 Y   ALTER TABLE ONLY public."Web.System.Task" DROP CONSTRAINT "FKn1h41i6mfajsxx8vh8jdtu2u5";
       public       postgres    false    233    2285    235            	           2606    59717 /   Web.Admin.User.Uuid FKo27exmbsc9qlt8si712r12qci    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.Uuid"
    ADD CONSTRAINT "FKo27exmbsc9qlt8si712r12qci" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 ]   ALTER TABLE ONLY public."Web.Admin.User.Uuid" DROP CONSTRAINT "FKo27exmbsc9qlt8si712r12qci";
       public       postgres    false    215    2249    219            	           2606    59762 $   Web.Note FKpxrohtf9r2xso6tqgw5gr1ji0    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Note"
    ADD CONSTRAINT "FKpxrohtf9r2xso6tqgw5gr1ji0" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 R   ALTER TABLE ONLY public."Web.Note" DROP CONSTRAINT "FKpxrohtf9r2xso6tqgw5gr1ji0";
       public       postgres    false    224    2249    215            �           2606    59627 1   Service.Material.Area FKqk9o4fe4k64pg1030jec07ybj    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Area"
    ADD CONSTRAINT "FKqk9o4fe4k64pg1030jec07ybj" FOREIGN KEY (material_id) REFERENCES public."Service.Material"(id);
 _   ALTER TABLE ONLY public."Service.Material.Area" DROP CONSTRAINT "FKqk9o4fe4k64pg1030jec07ybj";
       public       postgres    false    2231    207    206            !	           2606    59797 0   Web.System.Task_User FKrbwfcbgu9tplt9huxukqoxwts    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_User"
    ADD CONSTRAINT "FKrbwfcbgu9tplt9huxukqoxwts" FOREIGN KEY (task_id) REFERENCES public."Web.System.Task"(id);
 ^   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "FKrbwfcbgu9tplt9huxukqoxwts";
       public       postgres    false    236    235    2289            �           2606    59612 '   Service.Map FKsaplo8iwt8crpoej8foemvr3g    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Map"
    ADD CONSTRAINT "FKsaplo8iwt8crpoej8foemvr3g" FOREIGN KEY ("mapRegion_id") REFERENCES public."Service.Map.Region"(id);
 U   ALTER TABLE ONLY public."Service.Map" DROP CONSTRAINT "FKsaplo8iwt8crpoej8foemvr3g";
       public       postgres    false    204    2227    203            �           2606    59587 1   Service.Admin.Account FKtag9wsxdn3huwoyu9xnct3i4o    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Account"
    ADD CONSTRAINT "FKtag9wsxdn3huwoyu9xnct3i4o" FOREIGN KEY ("accountModel_id") REFERENCES public."Service.Admin.Account.Model"(id);
 _   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "FKtag9wsxdn3huwoyu9xnct3i4o";
       public       postgres    false    198    2215    196            �           2606    59602 -   Service.Layer_Map FKtaw5vu9h5m0aw28ik1x5xxjmv    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Layer_Map"
    ADD CONSTRAINT "FKtaw5vu9h5m0aw28ik1x5xxjmv" FOREIGN KEY (layer_id) REFERENCES public."Service.Layer"(id);
 [   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "FKtaw5vu9h5m0aw28ik1x5xxjmv";
       public       postgres    false    201    2221    202            	           2606    59657 +   Service.Meeting FKtfx79xn6n2cjyfut6qjsom86f    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting"
    ADD CONSTRAINT "FKtfx79xn6n2cjyfut6qjsom86f" FOREIGN KEY (room_id) REFERENCES public."Service.Room"(id);
 Y   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "FKtfx79xn6n2cjyfut6qjsom86f";
       public       postgres    false    2245    211    213            �           2606    59592 >   Service.Admin.Notification_Account FKthcd9eh23nwo3yreqgldl4bnl    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Notification_Account"
    ADD CONSTRAINT "FKthcd9eh23nwo3yreqgldl4bnl" FOREIGN KEY (account_id) REFERENCES public."Service.Admin.Account"(id);
 l   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "FKthcd9eh23nwo3yreqgldl4bnl";
       public       postgres    false    196    2211    200            �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	   �   x�343�44�4�70�74P02�21�2��4���d"���7��S&���8ol��T�����o���A��&y~�>aYE��&!Ɩ��yy�Ɂ���s�9�gk{Fef噆e�:��Dڂ�s�Z�p>�����y���o,�Z~��f;V��8#ˌ��=�=�-]2�BR��2���|]\�]m�j����#���C�<��K����S|�!����� ����      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	   �   x�345�44�4�70�74P02�21�2��4����;o��츱�fP&X�i�Y��_©&a�1~\��f�_R�X������4'�Ȕ$�����N}8��X�M�(�/*q�HN���G�#����� ��bQ      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	   �  x���n�6ǯ�� xh���(�"�u�-�Y�6��Ű#ѱYD�I�@����ѡ�Pt7��s��ˌ���k�(4��`��yD�O���dK�l��kX&@���g9��=ک��'QsÁ&��0J��H��=`�мT�w�1��A'�b�P�ߋ
ñ]hp��{�o�8:`K��m���t" 	m�\h�&�ϣ�n��-��>Ǧ��-�1]�B�H;��<	c���)�q�GODޑ�
}S�ȃ� �V)d��Hf��"E
>�E��Jϐ�A%��.e�.�~VO@Mfjq��0�1"n��.n;���� ��������ڳ�]�m����mu��a�E]jR��1�Z�@���}8�Q�4~�y�5��v�c��}���y�#�^N�OϵqQd�a�i��q*��i\̼a�F0��9��T{v�������D�3]��kU,�δi����y}��
���%|�j����Y3���»_W��F��!��[�1�7i��r)@���5Ɖ��3eV֯J���zNq�)'	�y)>�o�NZD�����V����u��?�5�Y�3�Z�n���x��޷�z�Fߙ�Ss�1,`�^h����4�M����d��u��j ��^R���w��2�o^�C��9�az��U���o_To�r�g�/78�24J��0)&������gF2���b�+7�z>f�O�.��v�8����#꣎�F^E�p��;�v����~.cM�.�%�e�:������B:���C]�([,�X��8I��5����<C�yu�5J�V�k�b�K�B.��)���O�ns> �{���n-�w���x]��~�~�!߈x�����]�d�f���9��'�+�K�W�Cv#���������`��Zf8���� �'��K���ע�W��a�������?�Kz������</      �	      x������ � �      �	   �   x�ՑMK1���_1���L>&I{
��X� q7�K�)mA�׻��P/=��fx�w�!ւtp��MO�녘��>��X�0Z�/]y=�b	��04�L������V�i֟������r~��%�M����f{(ϩ� Qj�$Dx��x�1���E"�6�m9��&�&��EC�����Dթ�nC�j���Q;Sۖ�eG���f CE�Ϸ~f��2�GF`?|�RkYU�����      �	      x������ � �      �	      x������ � �      �	   �  x����N�0ǯO�bO�Iw�iE�mwH�IM�H��M����ϱ]����]����ur>2�����b8���G����lCx~ !Wن-e��_���Mv+$�M+EUq�5-�b��xV�f��������Z�_���mZ^gf�^B��mouz�<��@-ۼg����"@e Ec~޹�J�"��+ q�L�L��gg�H�*�!wٓ�BLN����\�M�g�R
���c�e@� �4K��Ɩ��.i����*�X��i��i�8g%�v�<?��B8+���5���L����9�G4c����c�4�U���Y�浔�}�ڎaxv�삤�>��ݭ�*[�KVe�b"TR��@$xސ�+w�-YY��;=F��Fֈ�Ẃ5nS�u�^"x+g� I��WgJ^���ܫ/BiE�!	�~�"̱XK��U)��Z!>'���.�#�XFG]���ؕ�[��g�ee��e���"@eg����_>�},�GS	�D�;3��Y��8�݋�{e�gYJ^�G��Z�H�"�/a����չ�Dltx���*�X�d��b0�wU3��%DBB0�@�!���S��Vb۴�zC����]R��'ДDr��+3ڲ0O�$��u/����7�Ѯ�y:�'I̮�MS��'?�/_˂��8�<x�H9x�ܗ~KJV
���	��#Oxߛ=t�L�}�ӔT����N5�4��1��i���sG8�sp򚭉&_�`HVV�
�i����-�D�/£��'�Hiq����W���Z��T��R>����R�Zs�Oʈ`���B��Գ�L}�����c��Hn�)�#��س9/�s|7j�¨u�Ob�z��Y}�*�4Z-k_��T�z��"�`�Y,�Ԟ�Iԥ�YϒRsޖ��]tB��h��	�gM�>\�3t)��>���<B}P�f=k� bI�<�|�'��Y*p�;;͒rp�Ӂ?	1������*�      �	   $  x��T�n�@='_�R�hk����| z�TP�K$��'����;�"�H�
>�^��T��NbB�NR		˖��y3o�<;���N����4a�t:O'� �.�V܉��;�y-��
c�7�4`�X~�~�Я�FkT�-ϣ���\V�
�F�+aE�1����g�\ LY�����ѵ��@���}v���5%�i����L�^�Т����)������e-uoL-�T(��i��L��p[mZ�ͅrOD�XG��R��ś�K����2���!�$�x�e�=��~�v'n�o�%G�Ț�!��������-�"��9�w��@(��c�Ca�7�Y��M���ŏ����l������׷�����P6�d�a����P7��'VK2�6����k�5Pȹ,���2��kYC��n9����i ���r���c�-��#���y����Lv3ߝ]�M�bO�K	WF|�tOt'_7�^c�Z(���O�D��6h�)��kڔ�_U�dg{ �Y+:>`R6$m]�?B}���G�t����p���      �	     x���ms�6�?[���@EK�-��R;���d�כ錆� ��ԐT^&��~�.^I�@x���\���l��Y&��dv����׋�_/gg�g�����l��O��]m҆4ٞ����I�/�=)��i����y�.+2]��s���[V�i�n��7�<ݧY1���ܖES�yN*��D{�br�g��GX�ٺ�/6仰�_�f�)��\����ۭ�r�ՠŖ�X�Z�*�˦�6���A��
E�)ɉm�nL�g-&����;=,Z��$�=|d���~y.�j#�m�O3�}w�O]Lf��6o��Շվ�s�ʳZo����2f3\XZ��������rЁ�&X)���Lf����m_��b�U�6û����| _I~�k�T�
8-Z�䉰���"���*]7�W����$ky��7}�:��X�����g���f���"���{��0a�e���"��i|���f��E`�#F�f���� ���=~��n��{=z�{���y��8[Nf7c�N7��0���RY�D���,H~��XN��ч�T������E&�.Ť+2��%��#2��Ko���QB:����nw�|�6#��֑ޡD���Q\��OV0cC�4��;.� B0�D(1P���G��<��֒ǘB��&Lbl���>�����$Տ��~%�_e=dԋ��y�����������ܱ��G%�̎���Q�(.*��2�p�2^G�u�;Ǌ�﫱�<�#���#-�w]�������Q���ae���ֿ��/�߷s��!�VUY�����1,%��:��}�(J��K���d�����/巎�OLh����i>{8�,��n3�n���椁g�+-6��
�V8
ɦP��\Ӱ�d�P��;S>�j���\h��1hV�	�t��v5*}[�e-eUh��g�nƹ?��¶�VǚT����E;{ֽ���b�(���n������^��8���EҤ�r��F ��H�d� a�H.M4�JӤ3q+(̅���UYx3@�`$�ԣC`Jt�MA�'��(��/6��҂�F���t2e��C�Ye��(�<x9���"X)�Ǫ��"]�I]w���8�h$��I�BS�)H���W����B��cC��7[L�;�:���x]�DE��s��8�&�dV{���Y�m��D��^F0�jI����KV3M��/Ʀ0Ĉ����������0_yVt����!��"x��޹)kLp��Z�NJ�H���ki�	5�(�嘀oݴ׻*F'u��v�w��L��Cj��ۗ��?Ғoe�Q�W�hl���	�` C��G*���(��jh�Q��*u����	\�{���Z�4^�=��3� �[�u�nK[�	nC�L��#G#!�G��j�-�FR.�.mY�<��h$F��ly�/B�(���Җ���&�#Iz�n �e�x)�8�-)�-s�{���<��><�r%듅��ŖMXO�v'A��5	��e�=!	�oOH��l9���EAN� �2}QwUy<xn�����%�'(���+�;�;&W��1�UG�q�-Ҙ����F
cy�ѱΗ��.���%?�i�5&�"׆�_�2�6�@
4u��F�¾#�f1��(1G"��D�e�P����Z:B�'ݶ"���l@Qׯ�Q@u�]Zn��KϨ>@��쟍��`J��ZY4lM��+(xGn�	_��t8�(zj҆Z��[�[������X�?&a)�	BxH.ێ����(D�v!�9����%��.�`�5��u(�Ʋ�������K��*��v����_'miՉ�>������j�����-'B��g��Q���[�G�&��H1�;����$���XXN�+�>���P5���Rmiix�U��;�������K*�Թ�Y6vÎ�J��C���rm��Z�Ҿ�M���0�2����~8� [���3�8�� <CG{�ã���{���5�͝J��?@������^
��n��q%�N�P���ח�k�EcCٗM�PW��Y�C�<�[��B��S�*��ɍ�b���75t�S'��3K8�9Y(����I��w����X�+������j
���\;����,���YJ� Ku=������[w��7Im����.1��c�
����{����I�$���ؙ+$���Ql��*�i��Q��31�����H�j�Sݳ�ួCg/�����+
���\��U7.�Uih�i��-�w��@���Xc�� 
�j̙�?�`�����R �6~g�T���n�'������,���XQF�	XHA;��}~�hI+���T&����%�+�-�8Lwr/�(�L&�&�: �����Ԥo,�>B�X��:Ĩ[`'h2�{�.��a�:��}�g�3��k�?W.��-��.�Z /Ӎ������:���%9��A��7���ww���A9a�A2��W���C�����5�LU��8��-�::�>�ٙ�<>%v&�T��myr�ߙt�;SV�?fygb�':���S�9���%*nTU�r$���i��h�[�ч6�@�Q5����8�k�h3�:ǉꤥsf�VՈ?]-��:mЎ;�N\6�x�*�0�1�f9��.Gv�b�L�/�^�1�Jwl�/Kޤ^D�y�`��D�����I�X��OR�L����̠[W��� �Q)2��{]"{&Tmd澜�� �^'2��<���̪Khr���z�ȴ�j�N���P��
�v��jde��2|�N?z1�M��/��sj|��0��+*�|~l?�_��D�Ѐ�rH�}~ʨ�l��m�h���hx���²Ɏ'��>`���r?M<�[���gJ�ជW�moR)���Z���=i����﹔kb ����x�̮��pl6ܭ����n=�h���9�M�B���6�1;�?�������v�i�3�#a<ߊ�������y��p��p�L]0���ę�����hD\0�k1y��$0Lq�R'��Y/�>�8 �-0?(]?�|��A��5�L&�E�c      �	      x������ � �      �	      x������ � �     