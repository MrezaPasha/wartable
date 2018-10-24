PGDMP         2        
    	    v            wtdb    10.4    10.4 �    �	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �	           1262    16384    wtdb    DATABASE     �   CREATE DATABASE wtdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Persian_Iran.1256' LC_CTYPE = 'Persian_Iran.1256';
    DROP DATABASE wtdb;
             sa_wt    false                        2615    65345    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                        3079    12278    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �	           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    65346    Service.Admin.Account    TABLE        CREATE TABLE public."Service.Admin.Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "accountGroup_id" bigint,
    "accountModel_id" bigint
);
 +   DROP TABLE public."Service.Admin.Account";
       public         postgres    false    6            �            1259    65349    Service.Admin.Account.Group    TABLE     �   CREATE TABLE public."Service.Admin.Account.Group" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 1   DROP TABLE public."Service.Admin.Account.Group";
       public         postgres    false    6            �            1259    65352    Service.Admin.Account.Model    TABLE     �   CREATE TABLE public."Service.Admin.Account.Model" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 1   DROP TABLE public."Service.Admin.Account.Model";
       public         postgres    false    6            �            1259    65355    Service.Admin.Notification    TABLE     �   CREATE TABLE public."Service.Admin.Notification" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 0   DROP TABLE public."Service.Admin.Notification";
       public         postgres    false    6            �            1259    65358 "   Service.Admin.Notification_Account    TABLE       CREATE TABLE public."Service.Admin.Notification_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    notification_id bigint
);
 8   DROP TABLE public."Service.Admin.Notification_Account";
       public         postgres    false    6            �            1259    65361    Service.Layer    TABLE     �   CREATE TABLE public."Service.Layer" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 #   DROP TABLE public."Service.Layer";
       public         postgres    false    6            �            1259    65364    Service.Layer_Map    TABLE     �   CREATE TABLE public."Service.Layer_Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    layer_id bigint,
    map_id bigint
);
 '   DROP TABLE public."Service.Layer_Map";
       public         postgres    false    6            �            1259    65367    Service.Map    TABLE     �   CREATE TABLE public."Service.Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "mapRegion_id" bigint
);
 !   DROP TABLE public."Service.Map";
       public         postgres    false    6            �            1259    65370    Service.Map.Region    TABLE     �   CREATE TABLE public."Service.Map.Region" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 (   DROP TABLE public."Service.Map.Region";
       public         postgres    false    6            �            1259    65373    Service.Map_Room    TABLE     �   CREATE TABLE public."Service.Map_Room" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    room_id bigint
);
 &   DROP TABLE public."Service.Map_Room";
       public         postgres    false    6            �            1259    65376    Service.Material    TABLE     �   CREATE TABLE public."Service.Material" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 &   DROP TABLE public."Service.Material";
       public         postgres    false    6            �            1259    65379    Service.Material.Area    TABLE     �   CREATE TABLE public."Service.Material.Area" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    material_id bigint
);
 +   DROP TABLE public."Service.Material.Area";
       public         postgres    false    6            �            1259    65382    Service.Material.Group    TABLE     �   CREATE TABLE public."Service.Material.Group" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30)
);
 ,   DROP TABLE public."Service.Material.Group";
       public         postgres    false    6            �            1259    65385    Service.Material.Material_Group    TABLE     y   CREATE TABLE public."Service.Material.Material_Group" (
    group_id bigint NOT NULL,
    material_id bigint NOT NULL
);
 5   DROP TABLE public."Service.Material.Material_Group";
       public         postgres    false    6            �            1259    65388    Service.Material_Map    TABLE     �   CREATE TABLE public."Service.Material_Map" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    material_id bigint
);
 *   DROP TABLE public."Service.Material_Map";
       public         postgres    false    6            �            1259    65391    Service.Meeting    TABLE     �   CREATE TABLE public."Service.Meeting" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    map_id bigint,
    room_id bigint
);
 %   DROP TABLE public."Service.Meeting";
       public         postgres    false    6            �            1259    65394    Service.Meeting_Account    TABLE     �   CREATE TABLE public."Service.Meeting_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    meeting_id bigint
);
 -   DROP TABLE public."Service.Meeting_Account";
       public         postgres    false    6            �            1259    65397    Service.Room    TABLE     �   CREATE TABLE public."Service.Room" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "activeMeeting_id" bigint,
    "adminAccount_id" bigint
);
 "   DROP TABLE public."Service.Room";
       public         postgres    false    6            �            1259    65400    Service.Room_Account    TABLE     �   CREATE TABLE public."Service.Room_Account" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    account_id bigint,
    room_id bigint
);
 *   DROP TABLE public."Service.Room_Account";
       public         postgres    false    6            �            1259    65403    Web.Admin.User    TABLE       CREATE TABLE public."Web.Admin.User" (
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
       public         postgres    false    6            �            1259    65413    Web.Admin.User.Group    TABLE     j  CREATE TABLE public."Web.Admin.User.Group" (
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
       public         postgres    false    6            �            1259    65416    Web.Admin.User.Porter    TABLE     �  CREATE TABLE public."Web.Admin.User.Porter" (
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
       public         postgres    false    6            �            1259    65423    Web.Admin.User.User_UserGroup    TABLE     y   CREATE TABLE public."Web.Admin.User.User_UserGroup" (
    "userGroup_id" bigint NOT NULL,
    user_id bigint NOT NULL
);
 3   DROP TABLE public."Web.Admin.User.User_UserGroup";
       public         postgres    false    6            �            1259    65426    Web.Admin.User.Uuid    TABLE     �  CREATE TABLE public."Web.Admin.User.Uuid" (
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
       public         postgres    false    6            �            1259    65432    Web.Admin.UserConfirm    TABLE     �  CREATE TABLE public."Web.Admin.UserConfirm" (
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
       public         postgres    false    6            �            1259    65435    Web.Archive.Directory    TABLE     �  CREATE TABLE public."Web.Archive.Directory" (
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
       public         postgres    false    6            �            1259    65441    Web.Archive.File    TABLE     �  CREATE TABLE public."Web.Archive.File" (
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
       public         postgres    false    6            �            1259    65447    Web.Archive.File.Download    TABLE       CREATE TABLE public."Web.Archive.File.Download" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    status integer,
    file_id bigint,
    user_id bigint
);
 /   DROP TABLE public."Web.Archive.File.Download";
       public         postgres    false    6            �            1259    65450    Web.Note    TABLE     �  CREATE TABLE public."Web.Note" (
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
       public         postgres    false    6            �            1259    65456    Web.System.Backup    TABLE       CREATE TABLE public."Web.System.Backup" (
    id bigint NOT NULL,
    "createDateTime" character varying(30),
    "entityState" integer NOT NULL,
    "modifyDateTime" character varying(30),
    "backupDateTime" character varying(30),
    file_id bigint,
    "backupType" integer
);
 '   DROP TABLE public."Web.System.Backup";
       public         postgres    false    6            �            1259    65459    Web.System.Field    TABLE       CREATE TABLE public."Web.System.Field" (
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
       public         postgres    false    6            �            1259    65465    Web.System.Irror    TABLE     �  CREATE TABLE public."Web.System.Irror" (
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
       public         postgres    false    6            �            1259    65472    Web.System.Log    TABLE     �  CREATE TABLE public."Web.System.Log" (
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
       public         postgres    false    6            �            1259    65478    Web.System.Log.DailyLog    TABLE     Z  CREATE TABLE public."Web.System.Log.DailyLog" (
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
       public         postgres    false    6            �            1259    65481    Web.System.Log.SigninLog    TABLE       CREATE TABLE public."Web.System.Log.SigninLog" (
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
       public         postgres    false    6            �            1259    65487    Web.System.Log.User    TABLE     1  CREATE TABLE public."Web.System.Log.User" (
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
       public         postgres    false    6            �            1259    65494    Web.System.Model    TABLE     �  CREATE TABLE public."Web.System.Model" (
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
       public         postgres    false    6            �            1259    65500    Web.System.Module    TABLE     �  CREATE TABLE public."Web.System.Module" (
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
       public         postgres    false    6            �            1259    65506    Web.System.Registery    TABLE     w  CREATE TABLE public."Web.System.Registery" (
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
       public         postgres    false    6            �            1259    65512    Web.System.Task    TABLE     �  CREATE TABLE public."Web.System.Task" (
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
       public         postgres    false    6            �            1259    65518    Web.System.Task_User    TABLE     i   CREATE TABLE public."Web.System.Task_User" (
    user_id bigint NOT NULL,
    task_id bigint NOT NULL
);
 *   DROP TABLE public."Web.System.Task_User";
       public         postgres    false    6            �            1259    65521    Web.System.Task_UserGroup    TABLE     u   CREATE TABLE public."Web.System.Task_UserGroup" (
    "userGroup_id" bigint NOT NULL,
    task_id bigint NOT NULL
);
 /   DROP TABLE public."Web.System.Task_UserGroup";
       public         postgres    false    6            �            1259    65524    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    6            �	          0    65346    Service.Admin.Account 
   TABLE DATA               �   COPY public."Service.Admin.Account" (id, "createDateTime", "entityState", "modifyDateTime", "accountGroup_id", "accountModel_id") FROM stdin;
    public       postgres    false    196   (5      �	          0    65349    Service.Admin.Account.Group 
   TABLE DATA               n   COPY public."Service.Admin.Account.Group" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    197   E5      �	          0    65352    Service.Admin.Account.Model 
   TABLE DATA               n   COPY public."Service.Admin.Account.Model" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    198   b5      �	          0    65355    Service.Admin.Notification 
   TABLE DATA               m   COPY public."Service.Admin.Notification" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    199   5      �	          0    65358 "   Service.Admin.Notification_Account 
   TABLE DATA               �   COPY public."Service.Admin.Notification_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, notification_id) FROM stdin;
    public       postgres    false    200   �5      �	          0    65361    Service.Layer 
   TABLE DATA               `   COPY public."Service.Layer" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    201   �5      �	          0    65364    Service.Layer_Map 
   TABLE DATA               v   COPY public."Service.Layer_Map" (id, "createDateTime", "entityState", "modifyDateTime", layer_id, map_id) FROM stdin;
    public       postgres    false    202   �5      �	          0    65367    Service.Map 
   TABLE DATA               n   COPY public."Service.Map" (id, "createDateTime", "entityState", "modifyDateTime", "mapRegion_id") FROM stdin;
    public       postgres    false    203   �5      �	          0    65370    Service.Map.Region 
   TABLE DATA               e   COPY public."Service.Map.Region" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    204   6      �	          0    65373    Service.Map_Room 
   TABLE DATA               t   COPY public."Service.Map_Room" (id, "createDateTime", "entityState", "modifyDateTime", map_id, room_id) FROM stdin;
    public       postgres    false    205   -6      �	          0    65376    Service.Material 
   TABLE DATA               c   COPY public."Service.Material" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    206   J6      �	          0    65379    Service.Material.Area 
   TABLE DATA               u   COPY public."Service.Material.Area" (id, "createDateTime", "entityState", "modifyDateTime", material_id) FROM stdin;
    public       postgres    false    207   g6      �	          0    65382    Service.Material.Group 
   TABLE DATA               i   COPY public."Service.Material.Group" (id, "createDateTime", "entityState", "modifyDateTime") FROM stdin;
    public       postgres    false    208   �6      �	          0    65385    Service.Material.Material_Group 
   TABLE DATA               R   COPY public."Service.Material.Material_Group" (group_id, material_id) FROM stdin;
    public       postgres    false    209   �6      �	          0    65388    Service.Material_Map 
   TABLE DATA               |   COPY public."Service.Material_Map" (id, "createDateTime", "entityState", "modifyDateTime", map_id, material_id) FROM stdin;
    public       postgres    false    210   �6      �	          0    65391    Service.Meeting 
   TABLE DATA               s   COPY public."Service.Meeting" (id, "createDateTime", "entityState", "modifyDateTime", map_id, room_id) FROM stdin;
    public       postgres    false    211   �6      �	          0    65394    Service.Meeting_Account 
   TABLE DATA               �   COPY public."Service.Meeting_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, meeting_id) FROM stdin;
    public       postgres    false    212   �6      �	          0    65397    Service.Room 
   TABLE DATA               �   COPY public."Service.Room" (id, "createDateTime", "entityState", "modifyDateTime", "activeMeeting_id", "adminAccount_id") FROM stdin;
    public       postgres    false    213   7      �	          0    65400    Service.Room_Account 
   TABLE DATA               |   COPY public."Service.Room_Account" (id, "createDateTime", "entityState", "modifyDateTime", account_id, room_id) FROM stdin;
    public       postgres    false    214   27      �	          0    65403    Web.Admin.User 
   TABLE DATA               �  COPY public."Web.Admin.User" (id, "createDateTime", "entityState", "modifyDateTime", "accessLimitDailyEnd", "accessLimitDailyStart", "accessLimitMonthlyEnd", "accessLimitMonthlyStart", "accessLimitTimelyEnd", "accessLimitTimelyStart", "accessLimitYearlyEnd", "accessLimitYearlyStart", comment, "firstName", gender, "ipAddress", "ipAddressEnd", "ipAddressFirstSignin", "ipAddressStart", "ipRangeType", "isBlocked", "isLogManager", "isNeedToChangePassword", "isSuperAdmin", "lastName", "lastSigninDateTime", level, password, "passwordDateTime", "passwordHistory", "porterUuid", status, "superAdminCode", "superAdminLogingId", "superAdminLogingIn", "userCode", username, logo_id) FROM stdin;
    public       postgres    false    215   O7      �	          0    65413    Web.Admin.User.Group 
   TABLE DATA               �   COPY public."Web.Admin.User.Group" (id, "createDateTime", "entityState", "modifyDateTime", code, "memberCount", "subCount", title, parent_id) FROM stdin;
    public       postgres    false    216   �8      �	          0    65416    Web.Admin.User.Porter 
   TABLE DATA               @  COPY public."Web.Admin.User.Porter" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "confirmCode", "confirmCodeDateTimeG", count, "expireDateTimeG", "isActiveTwoStepConfirm", "isConfirmed", "signoutStatus", "singinDateTimeG", "singoutDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    217   �8      �	          0    65423    Web.Admin.User.User_UserGroup 
   TABLE DATA               R   COPY public."Web.Admin.User.User_UserGroup" ("userGroup_id", user_id) FROM stdin;
    public       postgres    false    218   9      �	          0    65426    Web.Admin.User.Uuid 
   TABLE DATA               �   COPY public."Web.Admin.User.Uuid" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "expireDateTimeG", "singinDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    219   ,9      �	          0    65432    Web.Admin.UserConfirm 
   TABLE DATA               �   COPY public."Web.Admin.UserConfirm" (id, "createDateTime", "entityState", "modifyDateTime", "callCount", "confirmDateTime", "confirmDateTimeG", "taskSignature", task_id, user_id) FROM stdin;
    public       postgres    false    220   I9      �	          0    65435    Web.Archive.Directory 
   TABLE DATA                 COPY public."Web.Archive.Directory" (id, "createDateTime", "entityState", "modifyDateTime", "className", comment, "containedDirectoryCount", "containedFileCount", "directoryOrigin", icon, "isPermanent", level, name, path, "repoDirectory", url, owner_id, parent_id) FROM stdin;
    public       postgres    false    221   f9      �	          0    65441    Web.Archive.File 
   TABLE DATA               �  COPY public."Web.Archive.File" (id, "createDateTime", "entityState", "modifyDateTime", "accessLevel", "accessRule", "contentType", "directoryAbsolutePath", "directoryRelativePath", "downloadCount", "downloadCountGuest", "fetchManner", "isContainOrginal", "isTemporary", "lastUploadDateTime", "orginalName", size, thumbnails, title, "tryToDownloadCount", "tryToDownloadCountGuest", "uploadLink", "uploadLinkComment", "uploadStatus", directory_id, owner_id) FROM stdin;
    public       postgres    false    222   �9      �	          0    65447    Web.Archive.File.Download 
   TABLE DATA               �   COPY public."Web.Archive.File.Download" (id, "createDateTime", "entityState", "modifyDateTime", status, file_id, user_id) FROM stdin;
    public       postgres    false    223   �:      �	          0    65450    Web.Note 
   TABLE DATA               �   COPY public."Web.Note" (id, "createDateTime", "entityState", "modifyDateTime", "dateTime", "dateTimeG", importance, "isNotified", "isVisited", message, title, user_id) FROM stdin;
    public       postgres    false    224   ';      �	          0    65456    Web.System.Backup 
   TABLE DATA               �   COPY public."Web.System.Backup" (id, "createDateTime", "entityState", "modifyDateTime", "backupDateTime", file_id, "backupType") FROM stdin;
    public       postgres    false    225   }<      �	          0    65459    Web.System.Field 
   TABLE DATA                 COPY public."Web.System.Field" (id, "createDateTime", "entityState", "modifyDateTime", "dbConstraint", "dbDefaultValue", "dbExtra", "dbIndex", "dbKey", "dbRefColumn", "dbRefTable", "dbSize", "dbTitle", "dbType", "isBidirectional", "isDbFK", "isDbNullable", "isDbPrimary", "isDbRefreshed", "isEncrypted", "isMoNullable", "isMoRefreshed", "moAnnotations", "moDataRelation", "moDataRelationDes", "moDefaultValue", "moExtra", "moKey", "moMaxSize", "moMinSize", "moModifier", "moPreviousTitle", "moTitle", "moType", status, model_id) FROM stdin;
    public       postgres    false    226   �<      �	          0    65465    Web.System.Irror 
   TABLE DATA                 COPY public."Web.System.Irror" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", cause, "computerSignature", "httpErrorCode", "isVisited", level, message, place, "porterUuid", "sessionId", status, "taskName", "visitCount", user_id) FROM stdin;
    public       postgres    false    227   �<      �	          0    65472    Web.System.Log 
   TABLE DATA               �  COPY public."Web.System.Log" (id, "createDateTime", "entityState", "modifyDateTime", "actionType", "agentSignature", "computerSignature", "dateTimeG", "httpCode", "importanceLevel", "isTaskTwoLevelConfirm", message, "onlineLoggingStrategy", "portNumber", "porterUuid", "requestMethod", "sendDateTimeG", "sendStatus", sensitivity, "serverId", "sessionId", "taskName", "taskTitle", url, "userGroupId", "userId", "userLevel") FROM stdin;
    public       postgres    false    228   WM      �	          0    65478    Web.System.Log.DailyLog 
   TABLE DATA               �   COPY public."Web.System.Log.DailyLog" (id, "createDateTime", "entityState", "modifyDateTime", chrome, "dayDate", desktop, firefox, "guestVisitCount", "internetExplorer", mobile, netscape, opera, "pageCount", "userVisitCount", "visitCount") FROM stdin;
    public       postgres    false    229   ]x      �	          0    65481    Web.System.Log.SigninLog 
   TABLE DATA               �   COPY public."Web.System.Log.SigninLog" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "computerSignature", "domainName", "ipAddress", "lastDateTime", "lastDateTimeG", status, uuid, user_id) FROM stdin;
    public       postgres    false    230   zx      �	          0    65487    Web.System.Log.User 
   TABLE DATA               �   COPY public."Web.System.Log.User" (id, "createDateTime", "entityState", "modifyDateTime", "agentSignature", "attemptType", "computerSignature", count, "dateTimeG", "isSuccess", "lastDateTimeG", uuid, user_id) FROM stdin;
    public       postgres    false    231   �z      �	          0    65494    Web.System.Model 
   TABLE DATA               �   COPY public."Web.System.Model" (id, "createDateTime", "entityState", "modifyDateTime", "className", "isRefreshed", "messageCode", "packageName", status, "tableName") FROM stdin;
    public       postgres    false    232   {      �	          0    65500    Web.System.Module 
   TABLE DATA               �   COPY public."Web.System.Module" (id, "createDateTime", "entityState", "modifyDateTime", "className", "isProtected", "isRefreshed", "menuMessageCode", "messageCode", "packageName", url) FROM stdin;
    public       postgres    false    233   {      �	          0    65506    Web.System.Registery 
   TABLE DATA               �   COPY public."Web.System.Registery" (id, "createDateTime", "entityState", "modifyDateTime", key, title, type, value, "valueJson", "valueType") FROM stdin;
    public       postgres    false    234         �	          0    65512    Web.System.Task 
   TABLE DATA               �  COPY public."Web.System.Task" (id, "createDateTime", "entityState", "modifyDateTime", "accessLevel", "actionType", "importanceLevel", "isActive", "isActiveLogging", "isAjax", "isLogManager", "isOnlineLogging", "isPanelTask", "isRefreshed", "isSuperAdmin", "isTwoLevelConfirm", "menuIdentity", "menuMessageCode", "messageCode", method, "onlineLoggingDelay", "onlineLoggingStrategy", "onlineSchedulingTime", sensitivity, signature, url, module_id) FROM stdin;
    public       postgres    false    235   ��      �	          0    65518    Web.System.Task_User 
   TABLE DATA               B   COPY public."Web.System.Task_User" (user_id, task_id) FROM stdin;
    public       postgres    false    236   ��      �	          0    65521    Web.System.Task_UserGroup 
   TABLE DATA               N   COPY public."Web.System.Task_UserGroup" ("userGroup_id", task_id) FROM stdin;
    public       postgres    false    237   �      �	           0    0    hibernate_sequence    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hibernate_sequence', 618, true);
            public       postgres    false    238            �           2606    65527 <   Service.Admin.Account.Group Service.Admin.Account.Group_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public."Service.Admin.Account.Group"
    ADD CONSTRAINT "Service.Admin.Account.Group_pkey" PRIMARY KEY (id);
 j   ALTER TABLE ONLY public."Service.Admin.Account.Group" DROP CONSTRAINT "Service.Admin.Account.Group_pkey";
       public         postgres    false    197            �           2606    65529 <   Service.Admin.Account.Model Service.Admin.Account.Model_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public."Service.Admin.Account.Model"
    ADD CONSTRAINT "Service.Admin.Account.Model_pkey" PRIMARY KEY (id);
 j   ALTER TABLE ONLY public."Service.Admin.Account.Model" DROP CONSTRAINT "Service.Admin.Account.Model_pkey";
       public         postgres    false    198            �           2606    65531 0   Service.Admin.Account Service.Admin.Account_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Service.Admin.Account"
    ADD CONSTRAINT "Service.Admin.Account_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "Service.Admin.Account_pkey";
       public         postgres    false    196            �           2606    65533 J   Service.Admin.Notification_Account Service.Admin.Notification_Account_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Notification_Account"
    ADD CONSTRAINT "Service.Admin.Notification_Account_pkey" PRIMARY KEY (id);
 x   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "Service.Admin.Notification_Account_pkey";
       public         postgres    false    200            �           2606    65535 :   Service.Admin.Notification Service.Admin.Notification_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY public."Service.Admin.Notification"
    ADD CONSTRAINT "Service.Admin.Notification_pkey" PRIMARY KEY (id);
 h   ALTER TABLE ONLY public."Service.Admin.Notification" DROP CONSTRAINT "Service.Admin.Notification_pkey";
       public         postgres    false    199            �           2606    65537 (   Service.Layer_Map Service.Layer_Map_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Service.Layer_Map"
    ADD CONSTRAINT "Service.Layer_Map_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "Service.Layer_Map_pkey";
       public         postgres    false    202            �           2606    65539     Service.Layer Service.Layer_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public."Service.Layer"
    ADD CONSTRAINT "Service.Layer_pkey" PRIMARY KEY (id);
 N   ALTER TABLE ONLY public."Service.Layer" DROP CONSTRAINT "Service.Layer_pkey";
       public         postgres    false    201            �           2606    65541 *   Service.Map.Region Service.Map.Region_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public."Service.Map.Region"
    ADD CONSTRAINT "Service.Map.Region_pkey" PRIMARY KEY (id);
 X   ALTER TABLE ONLY public."Service.Map.Region" DROP CONSTRAINT "Service.Map.Region_pkey";
       public         postgres    false    204            �           2606    65543 &   Service.Map_Room Service.Map_Room_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Service.Map_Room"
    ADD CONSTRAINT "Service.Map_Room_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "Service.Map_Room_pkey";
       public         postgres    false    205            �           2606    65545    Service.Map Service.Map_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public."Service.Map"
    ADD CONSTRAINT "Service.Map_pkey" PRIMARY KEY (id);
 J   ALTER TABLE ONLY public."Service.Map" DROP CONSTRAINT "Service.Map_pkey";
       public         postgres    false    203            �           2606    65547 0   Service.Material.Area Service.Material.Area_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Service.Material.Area"
    ADD CONSTRAINT "Service.Material.Area_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Service.Material.Area" DROP CONSTRAINT "Service.Material.Area_pkey";
       public         postgres    false    207            �           2606    65549 2   Service.Material.Group Service.Material.Group_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY public."Service.Material.Group"
    ADD CONSTRAINT "Service.Material.Group_pkey" PRIMARY KEY (id);
 `   ALTER TABLE ONLY public."Service.Material.Group" DROP CONSTRAINT "Service.Material.Group_pkey";
       public         postgres    false    208            �           2606    65551 D   Service.Material.Material_Group Service.Material.Material_Group_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Material_Group"
    ADD CONSTRAINT "Service.Material.Material_Group_pkey" PRIMARY KEY (material_id, group_id);
 r   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "Service.Material.Material_Group_pkey";
       public         postgres    false    209    209            �           2606    65553 .   Service.Material_Map Service.Material_Map_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Service.Material_Map"
    ADD CONSTRAINT "Service.Material_Map_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "Service.Material_Map_pkey";
       public         postgres    false    210            �           2606    65555 &   Service.Material Service.Material_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Service.Material"
    ADD CONSTRAINT "Service.Material_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Service.Material" DROP CONSTRAINT "Service.Material_pkey";
       public         postgres    false    206            �           2606    65557 4   Service.Meeting_Account Service.Meeting_Account_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public."Service.Meeting_Account"
    ADD CONSTRAINT "Service.Meeting_Account_pkey" PRIMARY KEY (id);
 b   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "Service.Meeting_Account_pkey";
       public         postgres    false    212            �           2606    65559 $   Service.Meeting Service.Meeting_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."Service.Meeting"
    ADD CONSTRAINT "Service.Meeting_pkey" PRIMARY KEY (id);
 R   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "Service.Meeting_pkey";
       public         postgres    false    211            �           2606    65561 .   Service.Room_Account Service.Room_Account_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Service.Room_Account"
    ADD CONSTRAINT "Service.Room_Account_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "Service.Room_Account_pkey";
       public         postgres    false    214            �           2606    65563    Service.Room Service.Room_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public."Service.Room"
    ADD CONSTRAINT "Service.Room_pkey" PRIMARY KEY (id);
 L   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "Service.Room_pkey";
       public         postgres    false    213            �           2606    65565 .   Web.Admin.User.Group Web.Admin.User.Group_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Web.Admin.User.Group"
    ADD CONSTRAINT "Web.Admin.User.Group_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Web.Admin.User.Group" DROP CONSTRAINT "Web.Admin.User.Group_pkey";
       public         postgres    false    216            �           2606    65567 0   Web.Admin.User.Porter Web.Admin.User.Porter_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Web.Admin.User.Porter"
    ADD CONSTRAINT "Web.Admin.User.Porter_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Admin.User.Porter" DROP CONSTRAINT "Web.Admin.User.Porter_pkey";
       public         postgres    false    217            �           2606    65569 @   Web.Admin.User.User_UserGroup Web.Admin.User.User_UserGroup_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "Web.Admin.User.User_UserGroup_pkey" PRIMARY KEY (user_id, "userGroup_id");
 n   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "Web.Admin.User.User_UserGroup_pkey";
       public         postgres    false    218    218            �           2606    65571 ,   Web.Admin.User.Uuid Web.Admin.User.Uuid_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public."Web.Admin.User.Uuid"
    ADD CONSTRAINT "Web.Admin.User.Uuid_pkey" PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public."Web.Admin.User.Uuid" DROP CONSTRAINT "Web.Admin.User.Uuid_pkey";
       public         postgres    false    219            �           2606    65573 0   Web.Admin.UserConfirm Web.Admin.UserConfirm_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Web.Admin.UserConfirm"
    ADD CONSTRAINT "Web.Admin.UserConfirm_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "Web.Admin.UserConfirm_pkey";
       public         postgres    false    220            �           2606    65575 "   Web.Admin.User Web.Admin.User_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public."Web.Admin.User"
    ADD CONSTRAINT "Web.Admin.User_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public."Web.Admin.User" DROP CONSTRAINT "Web.Admin.User_pkey";
       public         postgres    false    215            �           2606    65577 0   Web.Archive.Directory Web.Archive.Directory_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public."Web.Archive.Directory"
    ADD CONSTRAINT "Web.Archive.Directory_pkey" PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "Web.Archive.Directory_pkey";
       public         postgres    false    221            �           2606    65579 8   Web.Archive.File.Download Web.Archive.File.Download_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public."Web.Archive.File.Download"
    ADD CONSTRAINT "Web.Archive.File.Download_pkey" PRIMARY KEY (id);
 f   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "Web.Archive.File.Download_pkey";
       public         postgres    false    223            �           2606    65581 &   Web.Archive.File Web.Archive.File_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.Archive.File"
    ADD CONSTRAINT "Web.Archive.File_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "Web.Archive.File_pkey";
       public         postgres    false    222            �           2606    65583    Web.Note Web.Note_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Web.Note"
    ADD CONSTRAINT "Web.Note_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Web.Note" DROP CONSTRAINT "Web.Note_pkey";
       public         postgres    false    224            �           2606    65585 (   Web.System.Backup Web.System.Backup_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Web.System.Backup"
    ADD CONSTRAINT "Web.System.Backup_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Web.System.Backup" DROP CONSTRAINT "Web.System.Backup_pkey";
       public         postgres    false    225            �           2606    65587 &   Web.System.Field Web.System.Field_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.System.Field"
    ADD CONSTRAINT "Web.System.Field_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Field" DROP CONSTRAINT "Web.System.Field_pkey";
       public         postgres    false    226            �           2606    65589 &   Web.System.Irror Web.System.Irror_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.System.Irror"
    ADD CONSTRAINT "Web.System.Irror_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Irror" DROP CONSTRAINT "Web.System.Irror_pkey";
       public         postgres    false    227            �           2606    65591 4   Web.System.Log.DailyLog Web.System.Log.DailyLog_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public."Web.System.Log.DailyLog"
    ADD CONSTRAINT "Web.System.Log.DailyLog_pkey" PRIMARY KEY (id);
 b   ALTER TABLE ONLY public."Web.System.Log.DailyLog" DROP CONSTRAINT "Web.System.Log.DailyLog_pkey";
       public         postgres    false    229            �           2606    65593 6   Web.System.Log.SigninLog Web.System.Log.SigninLog_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public."Web.System.Log.SigninLog"
    ADD CONSTRAINT "Web.System.Log.SigninLog_pkey" PRIMARY KEY (id);
 d   ALTER TABLE ONLY public."Web.System.Log.SigninLog" DROP CONSTRAINT "Web.System.Log.SigninLog_pkey";
       public         postgres    false    230            �           2606    65595 ,   Web.System.Log.User Web.System.Log.User_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public."Web.System.Log.User"
    ADD CONSTRAINT "Web.System.Log.User_pkey" PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public."Web.System.Log.User" DROP CONSTRAINT "Web.System.Log.User_pkey";
       public         postgres    false    231            �           2606    65597 "   Web.System.Log Web.System.Log_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public."Web.System.Log"
    ADD CONSTRAINT "Web.System.Log_pkey" PRIMARY KEY (id);
 P   ALTER TABLE ONLY public."Web.System.Log" DROP CONSTRAINT "Web.System.Log_pkey";
       public         postgres    false    228            �           2606    65599 &   Web.System.Model Web.System.Model_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public."Web.System.Model"
    ADD CONSTRAINT "Web.System.Model_pkey" PRIMARY KEY (id);
 T   ALTER TABLE ONLY public."Web.System.Model" DROP CONSTRAINT "Web.System.Model_pkey";
       public         postgres    false    232            �           2606    65601 (   Web.System.Module Web.System.Module_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public."Web.System.Module"
    ADD CONSTRAINT "Web.System.Module_pkey" PRIMARY KEY (id);
 V   ALTER TABLE ONLY public."Web.System.Module" DROP CONSTRAINT "Web.System.Module_pkey";
       public         postgres    false    233            �           2606    65603 .   Web.System.Registery Web.System.Registery_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."Web.System.Registery"
    ADD CONSTRAINT "Web.System.Registery_pkey" PRIMARY KEY (id);
 \   ALTER TABLE ONLY public."Web.System.Registery" DROP CONSTRAINT "Web.System.Registery_pkey";
       public         postgres    false    234            �           2606    65605 8   Web.System.Task_UserGroup Web.System.Task_UserGroup_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_UserGroup"
    ADD CONSTRAINT "Web.System.Task_UserGroup_pkey" PRIMARY KEY (task_id, "userGroup_id");
 f   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "Web.System.Task_UserGroup_pkey";
       public         postgres    false    237    237            �           2606    65607 .   Web.System.Task_User Web.System.Task_User_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public."Web.System.Task_User"
    ADD CONSTRAINT "Web.System.Task_User_pkey" PRIMARY KEY (task_id, user_id);
 \   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "Web.System.Task_User_pkey";
       public         postgres    false    236    236            �           2606    65609 $   Web.System.Task Web.System.Task_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."Web.System.Task"
    ADD CONSTRAINT "Web.System.Task_pkey" PRIMARY KEY (id);
 R   ALTER TABLE ONLY public."Web.System.Task" DROP CONSTRAINT "Web.System.Task_pkey";
       public         postgres    false    235            �           2606    65610 -   Service.Layer_Map FK13nj6p1jqt0xubl2bm7evx7ob    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Layer_Map"
    ADD CONSTRAINT "FK13nj6p1jqt0xubl2bm7evx7ob" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 [   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "FK13nj6p1jqt0xubl2bm7evx7ob";
       public       postgres    false    202    203    2225            	           2606    65615 +   Web.System.Irror FK179yr80p4adleu0bn1y54bkm    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Irror"
    ADD CONSTRAINT "FK179yr80p4adleu0bn1y54bkm" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 Y   ALTER TABLE ONLY public."Web.System.Irror" DROP CONSTRAINT "FK179yr80p4adleu0bn1y54bkm";
       public       postgres    false    227    215    2249            	           2606    65620 ,   Web.System.Field FK1es95ggvfg9vombcaiqtlv5j5    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Field"
    ADD CONSTRAINT "FK1es95ggvfg9vombcaiqtlv5j5" FOREIGN KEY (model_id) REFERENCES public."Web.System.Model"(id);
 Z   ALTER TABLE ONLY public."Web.System.Field" DROP CONSTRAINT "FK1es95ggvfg9vombcaiqtlv5j5";
       public       postgres    false    226    232    2283            	           2606    65625 *   Web.Admin.User FK1uwd6vtspmpndkcaw6f7c02jh    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User"
    ADD CONSTRAINT "FK1uwd6vtspmpndkcaw6f7c02jh" FOREIGN KEY (logo_id) REFERENCES public."Web.Archive.File"(id);
 X   ALTER TABLE ONLY public."Web.Admin.User" DROP CONSTRAINT "FK1uwd6vtspmpndkcaw6f7c02jh";
       public       postgres    false    215    222    2263            	           2606    65630 3   Service.Meeting_Account FK1xfplj8yodpqrd6lymh91clcv    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting_Account"
    ADD CONSTRAINT "FK1xfplj8yodpqrd6lymh91clcv" FOREIGN KEY (account_id) REFERENCES public."Service.Admin.Account"(id);
 a   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "FK1xfplj8yodpqrd6lymh91clcv";
       public       postgres    false    212    196    2211            	           2606    65635 9   Web.Admin.User.User_UserGroup FK3u8c5qf627qwin8qwvm2jocfy    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "FK3u8c5qf627qwin8qwvm2jocfy" FOREIGN KEY ("userGroup_id") REFERENCES public."Web.Admin.User.Group"(id);
 g   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "FK3u8c5qf627qwin8qwvm2jocfy";
       public       postgres    false    218    216    2251            	           2606    65640 -   Web.System.Backup FK4ch0kue8bh5lpvsyjqnl3qo7r    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Backup"
    ADD CONSTRAINT "FK4ch0kue8bh5lpvsyjqnl3qo7r" FOREIGN KEY (file_id) REFERENCES public."Web.Archive.File"(id);
 [   ALTER TABLE ONLY public."Web.System.Backup" DROP CONSTRAINT "FK4ch0kue8bh5lpvsyjqnl3qo7r";
       public       postgres    false    225    222    2263            	           2606    65645 5   Web.Archive.File.Download FK4kwt8ccqa8s3pm6wyhhhxhbor    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File.Download"
    ADD CONSTRAINT "FK4kwt8ccqa8s3pm6wyhhhxhbor" FOREIGN KEY (file_id) REFERENCES public."Web.Archive.File"(id);
 c   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "FK4kwt8ccqa8s3pm6wyhhhxhbor";
       public       postgres    false    223    222    2263            	           2606    65650 +   Service.Meeting FK5vxbkwhno5m5f6io9ewpfvfrt    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting"
    ADD CONSTRAINT "FK5vxbkwhno5m5f6io9ewpfvfrt" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 Y   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "FK5vxbkwhno5m5f6io9ewpfvfrt";
       public       postgres    false    211    203    2225             	           2606    65655 ;   Service.Material.Material_Group FK6lqodoftkhranmm8ll9qddtig    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Material_Group"
    ADD CONSTRAINT "FK6lqodoftkhranmm8ll9qddtig" FOREIGN KEY (material_id) REFERENCES public."Service.Material"(id);
 i   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "FK6lqodoftkhranmm8ll9qddtig";
       public       postgres    false    209    206    2231            	           2606    65660 (   Service.Room FK7y95kfit57u0f9xku0criwo18    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room"
    ADD CONSTRAINT "FK7y95kfit57u0f9xku0criwo18" FOREIGN KEY ("adminAccount_id") REFERENCES public."Service.Admin.Account"(id);
 V   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "FK7y95kfit57u0f9xku0criwo18";
       public       postgres    false    213    196    2211            	           2606    65665 1   Web.Archive.Directory FK86fcckds9fmo9vn6gnirngn7u    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.Directory"
    ADD CONSTRAINT "FK86fcckds9fmo9vn6gnirngn7u" FOREIGN KEY (parent_id) REFERENCES public."Web.Archive.Directory"(id);
 _   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "FK86fcckds9fmo9vn6gnirngn7u";
       public       postgres    false    221    221    2261            	           2606    65670 /   Web.System.Log.User FK8ld9m40vmrs5efepdj72eojlv    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Log.User"
    ADD CONSTRAINT "FK8ld9m40vmrs5efepdj72eojlv" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 ]   ALTER TABLE ONLY public."Web.System.Log.User" DROP CONSTRAINT "FK8ld9m40vmrs5efepdj72eojlv";
       public       postgres    false    231    215    2249            	           2606    65675 3   Service.Meeting_Account FK8nm941j0t4gv3bumcwsyg9s3n    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting_Account"
    ADD CONSTRAINT "FK8nm941j0t4gv3bumcwsyg9s3n" FOREIGN KEY (meeting_id) REFERENCES public."Service.Meeting"(id);
 a   ALTER TABLE ONLY public."Service.Meeting_Account" DROP CONSTRAINT "FK8nm941j0t4gv3bumcwsyg9s3n";
       public       postgres    false    212    211    2241            	           2606    65680 ;   Service.Material.Material_Group FK9bdp8ucdge8uyvc90q92do2f8    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Material_Group"
    ADD CONSTRAINT "FK9bdp8ucdge8uyvc90q92do2f8" FOREIGN KEY (group_id) REFERENCES public."Service.Material.Group"(id);
 i   ALTER TABLE ONLY public."Service.Material.Material_Group" DROP CONSTRAINT "FK9bdp8ucdge8uyvc90q92do2f8";
       public       postgres    false    209    208    2235            	           2606    65685 ,   Web.Archive.File FKagip4f17ss7vj9s2wwrkcvm5r    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File"
    ADD CONSTRAINT "FKagip4f17ss7vj9s2wwrkcvm5r" FOREIGN KEY (owner_id) REFERENCES public."Web.Admin.User"(id);
 Z   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "FKagip4f17ss7vj9s2wwrkcvm5r";
       public       postgres    false    222    215    2249            �           2606    65690 ,   Service.Map_Room FKb3laq886tx4mhxma1549o6xby    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Map_Room"
    ADD CONSTRAINT "FKb3laq886tx4mhxma1549o6xby" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 Z   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "FKb3laq886tx4mhxma1549o6xby";
       public       postgres    false    205    203    2225            �           2606    65695 >   Service.Admin.Notification_Account FKbopt2hxcvrh141iy9cjjya4s2    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Notification_Account"
    ADD CONSTRAINT "FKbopt2hxcvrh141iy9cjjya4s2" FOREIGN KEY (notification_id) REFERENCES public."Service.Admin.Notification"(id);
 l   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "FKbopt2hxcvrh141iy9cjjya4s2";
       public       postgres    false    200    199    2217            �           2606    65700 1   Service.Admin.Account FKbs6slesa63l45yy54d0uvdglm    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Account"
    ADD CONSTRAINT "FKbs6slesa63l45yy54d0uvdglm" FOREIGN KEY ("accountGroup_id") REFERENCES public."Service.Admin.Account.Group"(id);
 _   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "FKbs6slesa63l45yy54d0uvdglm";
       public       postgres    false    196    197    2213            
	           2606    65705 /   Service.Room_Account FKclhapld1q4n01ygcv0oif1i1    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room_Account"
    ADD CONSTRAINT "FKclhapld1q4n01ygcv0oif1i1" FOREIGN KEY (room_id) REFERENCES public."Service.Room"(id);
 ]   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "FKclhapld1q4n01ygcv0oif1i1";
       public       postgres    false    214    213    2245            �           2606    65710 ,   Service.Map_Room FKddf8seuxcgwn0ux9xh8nafo5r    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Map_Room"
    ADD CONSTRAINT "FKddf8seuxcgwn0ux9xh8nafo5r" FOREIGN KEY (room_id) REFERENCES public."Service.Room"(id);
 Z   ALTER TABLE ONLY public."Service.Map_Room" DROP CONSTRAINT "FKddf8seuxcgwn0ux9xh8nafo5r";
       public       postgres    false    205    213    2245            	           2606    65715 0   Web.Admin.User.Group FKdhnm45lgors90in2fw2h2sdtq    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.Group"
    ADD CONSTRAINT "FKdhnm45lgors90in2fw2h2sdtq" FOREIGN KEY (parent_id) REFERENCES public."Web.Admin.User.Group"(id);
 ^   ALTER TABLE ONLY public."Web.Admin.User.Group" DROP CONSTRAINT "FKdhnm45lgors90in2fw2h2sdtq";
       public       postgres    false    216    216    2251            		           2606    65720 (   Service.Room FKe5eb6d3mlm3t7xekphhyndvt9    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room"
    ADD CONSTRAINT "FKe5eb6d3mlm3t7xekphhyndvt9" FOREIGN KEY ("activeMeeting_id") REFERENCES public."Service.Meeting"(id);
 V   ALTER TABLE ONLY public."Service.Room" DROP CONSTRAINT "FKe5eb6d3mlm3t7xekphhyndvt9";
       public       postgres    false    213    211    2241            	           2606    65725 +   Web.Archive.File FKev7boejuglytck8yuglcq1rd    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File"
    ADD CONSTRAINT "FKev7boejuglytck8yuglcq1rd" FOREIGN KEY (directory_id) REFERENCES public."Web.Archive.Directory"(id);
 Y   ALTER TABLE ONLY public."Web.Archive.File" DROP CONSTRAINT "FKev7boejuglytck8yuglcq1rd";
       public       postgres    false    222    221    2261            	           2606    65730 0   Service.Room_Account FKevw25cji9v1xvgt74eb675iyy    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Room_Account"
    ADD CONSTRAINT "FKevw25cji9v1xvgt74eb675iyy" FOREIGN KEY (account_id) REFERENCES public."Service.Admin.Account"(id);
 ^   ALTER TABLE ONLY public."Service.Room_Account" DROP CONSTRAINT "FKevw25cji9v1xvgt74eb675iyy";
       public       postgres    false    214    196    2211            #	           2606    65735 5   Web.System.Task_UserGroup FKflr8ben4uy9x748n06ounuldq    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_UserGroup"
    ADD CONSTRAINT "FKflr8ben4uy9x748n06ounuldq" FOREIGN KEY (task_id) REFERENCES public."Web.System.Task"(id);
 c   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "FKflr8ben4uy9x748n06ounuldq";
       public       postgres    false    237    235    2289            $	           2606    65740 5   Web.System.Task_UserGroup FKfmar5n0tdatsry2ttfu0ombrp    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_UserGroup"
    ADD CONSTRAINT "FKfmar5n0tdatsry2ttfu0ombrp" FOREIGN KEY ("userGroup_id") REFERENCES public."Web.Admin.User.Group"(id);
 c   ALTER TABLE ONLY public."Web.System.Task_UserGroup" DROP CONSTRAINT "FKfmar5n0tdatsry2ttfu0ombrp";
       public       postgres    false    237    216    2251            	           2606    65745 9   Web.Admin.User.User_UserGroup FKg72228uwjmtumk7o0khpnpaqb    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup"
    ADD CONSTRAINT "FKg72228uwjmtumk7o0khpnpaqb" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 g   ALTER TABLE ONLY public."Web.Admin.User.User_UserGroup" DROP CONSTRAINT "FKg72228uwjmtumk7o0khpnpaqb";
       public       postgres    false    218    215    2249            	           2606    65750 0   Service.Material_Map FKhc2x83k2jlmn29kq68o5a45um    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material_Map"
    ADD CONSTRAINT "FKhc2x83k2jlmn29kq68o5a45um" FOREIGN KEY (map_id) REFERENCES public."Service.Map"(id);
 ^   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "FKhc2x83k2jlmn29kq68o5a45um";
       public       postgres    false    210    203    2225            	           2606    65755 1   Web.Admin.UserConfirm FKi4t2i0kg245d8fb6yj5xign2q    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.UserConfirm"
    ADD CONSTRAINT "FKi4t2i0kg245d8fb6yj5xign2q" FOREIGN KEY (task_id) REFERENCES public."Web.System.Task"(id);
 _   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "FKi4t2i0kg245d8fb6yj5xign2q";
       public       postgres    false    220    235    2289            	           2606    65760 0   Service.Material_Map FKj200gs4cgur5xq51a5kf82pel    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material_Map"
    ADD CONSTRAINT "FKj200gs4cgur5xq51a5kf82pel" FOREIGN KEY (material_id) REFERENCES public."Service.Material"(id);
 ^   ALTER TABLE ONLY public."Service.Material_Map" DROP CONSTRAINT "FKj200gs4cgur5xq51a5kf82pel";
       public       postgres    false    210    206    2231            	           2606    65765 1   Web.Admin.UserConfirm FKk5lovfvt0apu4ni7wkpqg5147    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.UserConfirm"
    ADD CONSTRAINT "FKk5lovfvt0apu4ni7wkpqg5147" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 _   ALTER TABLE ONLY public."Web.Admin.UserConfirm" DROP CONSTRAINT "FKk5lovfvt0apu4ni7wkpqg5147";
       public       postgres    false    215    220    2249            	           2606    65770 1   Web.Admin.User.Porter FKkaat5quhoarvfx70f5m98q7j4    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.Porter"
    ADD CONSTRAINT "FKkaat5quhoarvfx70f5m98q7j4" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 _   ALTER TABLE ONLY public."Web.Admin.User.Porter" DROP CONSTRAINT "FKkaat5quhoarvfx70f5m98q7j4";
       public       postgres    false    217    215    2249            !	           2606    65775 0   Web.System.Task_User FKkx74jgadegi5o45ijchxvuw2m    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_User"
    ADD CONSTRAINT "FKkx74jgadegi5o45ijchxvuw2m" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 ^   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "FKkx74jgadegi5o45ijchxvuw2m";
       public       postgres    false    236    215    2249            	           2606    65780 5   Web.Archive.File.Download FKlmvo3wehmatw4p69t2498jrdr    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.File.Download"
    ADD CONSTRAINT "FKlmvo3wehmatw4p69t2498jrdr" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 c   ALTER TABLE ONLY public."Web.Archive.File.Download" DROP CONSTRAINT "FKlmvo3wehmatw4p69t2498jrdr";
       public       postgres    false    223    215    2249            	           2606    65785 4   Web.System.Log.SigninLog FKly85hqgbrmb5scmawg6kyrtn6    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Log.SigninLog"
    ADD CONSTRAINT "FKly85hqgbrmb5scmawg6kyrtn6" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 b   ALTER TABLE ONLY public."Web.System.Log.SigninLog" DROP CONSTRAINT "FKly85hqgbrmb5scmawg6kyrtn6";
       public       postgres    false    230    215    2249            	           2606    65790 0   Web.Archive.Directory FKmwjxdube2yqdoh7qkoeag216    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Archive.Directory"
    ADD CONSTRAINT "FKmwjxdube2yqdoh7qkoeag216" FOREIGN KEY (owner_id) REFERENCES public."Web.Admin.User"(id);
 ^   ALTER TABLE ONLY public."Web.Archive.Directory" DROP CONSTRAINT "FKmwjxdube2yqdoh7qkoeag216";
       public       postgres    false    221    215    2249             	           2606    65795 +   Web.System.Task FKn1h41i6mfajsxx8vh8jdtu2u5    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task"
    ADD CONSTRAINT "FKn1h41i6mfajsxx8vh8jdtu2u5" FOREIGN KEY (module_id) REFERENCES public."Web.System.Module"(id);
 Y   ALTER TABLE ONLY public."Web.System.Task" DROP CONSTRAINT "FKn1h41i6mfajsxx8vh8jdtu2u5";
       public       postgres    false    235    233    2285            	           2606    65800 /   Web.Admin.User.Uuid FKo27exmbsc9qlt8si712r12qci    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Admin.User.Uuid"
    ADD CONSTRAINT "FKo27exmbsc9qlt8si712r12qci" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 ]   ALTER TABLE ONLY public."Web.Admin.User.Uuid" DROP CONSTRAINT "FKo27exmbsc9qlt8si712r12qci";
       public       postgres    false    219    215    2249            	           2606    65805 $   Web.Note FKpxrohtf9r2xso6tqgw5gr1ji0    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.Note"
    ADD CONSTRAINT "FKpxrohtf9r2xso6tqgw5gr1ji0" FOREIGN KEY (user_id) REFERENCES public."Web.Admin.User"(id);
 R   ALTER TABLE ONLY public."Web.Note" DROP CONSTRAINT "FKpxrohtf9r2xso6tqgw5gr1ji0";
       public       postgres    false    224    215    2249            �           2606    65810 1   Service.Material.Area FKqk9o4fe4k64pg1030jec07ybj    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Material.Area"
    ADD CONSTRAINT "FKqk9o4fe4k64pg1030jec07ybj" FOREIGN KEY (material_id) REFERENCES public."Service.Material"(id);
 _   ALTER TABLE ONLY public."Service.Material.Area" DROP CONSTRAINT "FKqk9o4fe4k64pg1030jec07ybj";
       public       postgres    false    207    206    2231            "	           2606    65815 0   Web.System.Task_User FKrbwfcbgu9tplt9huxukqoxwts    FK CONSTRAINT     �   ALTER TABLE ONLY public."Web.System.Task_User"
    ADD CONSTRAINT "FKrbwfcbgu9tplt9huxukqoxwts" FOREIGN KEY (task_id) REFERENCES public."Web.System.Task"(id);
 ^   ALTER TABLE ONLY public."Web.System.Task_User" DROP CONSTRAINT "FKrbwfcbgu9tplt9huxukqoxwts";
       public       postgres    false    236    235    2289            �           2606    65820 '   Service.Map FKsaplo8iwt8crpoej8foemvr3g    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Map"
    ADD CONSTRAINT "FKsaplo8iwt8crpoej8foemvr3g" FOREIGN KEY ("mapRegion_id") REFERENCES public."Service.Map.Region"(id);
 U   ALTER TABLE ONLY public."Service.Map" DROP CONSTRAINT "FKsaplo8iwt8crpoej8foemvr3g";
       public       postgres    false    203    204    2227            �           2606    65825 1   Service.Admin.Account FKtag9wsxdn3huwoyu9xnct3i4o    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Account"
    ADD CONSTRAINT "FKtag9wsxdn3huwoyu9xnct3i4o" FOREIGN KEY ("accountModel_id") REFERENCES public."Service.Admin.Account.Model"(id);
 _   ALTER TABLE ONLY public."Service.Admin.Account" DROP CONSTRAINT "FKtag9wsxdn3huwoyu9xnct3i4o";
       public       postgres    false    196    2215    198            �           2606    65830 -   Service.Layer_Map FKtaw5vu9h5m0aw28ik1x5xxjmv    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Layer_Map"
    ADD CONSTRAINT "FKtaw5vu9h5m0aw28ik1x5xxjmv" FOREIGN KEY (layer_id) REFERENCES public."Service.Layer"(id);
 [   ALTER TABLE ONLY public."Service.Layer_Map" DROP CONSTRAINT "FKtaw5vu9h5m0aw28ik1x5xxjmv";
       public       postgres    false    201    202    2221            	           2606    65835 +   Service.Meeting FKtfx79xn6n2cjyfut6qjsom86f    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Meeting"
    ADD CONSTRAINT "FKtfx79xn6n2cjyfut6qjsom86f" FOREIGN KEY (room_id) REFERENCES public."Service.Room"(id);
 Y   ALTER TABLE ONLY public."Service.Meeting" DROP CONSTRAINT "FKtfx79xn6n2cjyfut6qjsom86f";
       public       postgres    false    211    213    2245            �           2606    65840 >   Service.Admin.Notification_Account FKthcd9eh23nwo3yreqgldl4bnl    FK CONSTRAINT     �   ALTER TABLE ONLY public."Service.Admin.Notification_Account"
    ADD CONSTRAINT "FKthcd9eh23nwo3yreqgldl4bnl" FOREIGN KEY (account_id) REFERENCES public."Service.Admin.Account"(id);
 l   ALTER TABLE ONLY public."Service.Admin.Notification_Account" DROP CONSTRAINT "FKthcd9eh23nwo3yreqgldl4bnl";
       public       postgres    false    200    196    2211            �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	   v  x���Ko�@����ޤ��x@1�)���H�(�E�DS�ڃ�ã��3P�LW�mcM���lv��fX� D.�b�$b hhTErX��������kCХ��C��m��o";�!��6�I1+�j�t�T��:ved���2��y�h:��J�� ���Nm5�*��*QGC��$���1IR���<<u���:w�M����#��_h�.��p�~���ޅ�8Z�:x�L�3*�e�F��%8��t<�T�j͖�H;dA�	�&o����Z�了��+���#"�J8�9%otJ�I��bP�cs:S�<'�ּ��+c`U�����0˿2�W�dV��]��]�+d��$-E-i��b��$Ļ      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	      x������ � �      �	   �   x�345�44�4�70�74P02�26�20�4����;o��츱�fP&X�i�Y��_©&a�1~\��f�_R�X������4'�Ȕ$�����N}8��X�M�(�/*q�HN���G�#����� �CbA      �	   �   x����
�@��ק�	ڙ�YW�2�N��@�4�m5��O�~�.80p�pf>"�#�H��%�Z�q�Xł�n=J���[��s��[�3W��mUH_�MW����M�f�,�n���{�6i�4��0��&D0Ě�[_��rqsx�<��#� �@�!�e����^.G@�)���Y��s��%��v� �Hu�      �	   M   x�eʱ� ��p�_e'p�9rI�޽��;i�Q%�j�H��NB{= ����q�f�sm�H�4���|߾����      �	   F  x�}�Mn�0���)r���ObϺ7趫
%���),r n��E4��������X����7��e m=��x)�
A2��U%T�-������_��ʟ�|q���-�2	@i�<������Be�}R?tL���J�t����Z�+���i�d�����6���nK�ؤ��
�J����T�K��|z������Yd2|�<׿\1et�#}x�/�Ӿ<����v�%�}v=tn]��Нvn�
T(0�i<�G*@�|(���A�8��8(�݊����_;(BD�r����9���ʛ��i�RJ��Ze6)g�F!n�g����*D@?�E����
      �	   I   x�m��� �wR��DR�����ΒM�ͨ�jVI�������0�z2ݳ��0�wۇ�^�d�      �	      x������ � �      �	      x��]�n�H}v��h�7QK�����Y�icz2JdIbL�J���_g�e�����4�g�6J�Dɢ$'vr�nE�*��z��9ݦc�V�R�+%�P�Jݪ֭ʎ��������T�tE=�C/���Wg��k�#�8�#�c?P�� ���/~R*[�r���g/_<T�+ϰ{=P=�qɩi�fٵ�f��BD|y�N	�RY�w�z���S���w�*n������O�d/�C|��4BZ�®����A�Ga]�HW������2"�%nk���W8N���d�$s�)�8�UʀD.�cz��A~��GJ�/Qp�X�c��H��6�q8B�����q�.
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
P7V�I��&��: �^l�8�e:$ O   ������ʮ�����"|�n2��?k��r[/��6j�^���QiV�\>hZ�a�R.[ͦ���^Ui#��{���C�?K      �	      x��}Io$W���O݀��%z�Z��՚��j*�"#3��Hv0��j��t��s_R�´�.�C�R�3c/�Lz�sn�N���A2�����}�<n���>a�	g�B��s�Ŷ~�n����e��`�D���_�f�p����w��و��6<a��lb�_o'����L?����-�H��������?������O�1����_og/gGN�?b#��qƶ�M�Of��b��p}Z:�,�Z{wq]Ϸ>�y99�;��v�;�����l�_����x�;�y{�Óᅓ�_�NO��l��'Ǔ�������h�?=��Lg��������:ۇ�?Y>��y�7�lk�=��=��ή�{�(a�;i�;z�m�dj&�w�����װl���iΥ�/�d�W<�rO�%�.-��f/F'�������'����އ𸸮��?�Cvt8��m��?[\����g?���^����'O�v'/�N��p�wpq%oq#�4��$|gӽ��s��xr8=x��d����Of�ޣ��	�
�_��g$QWk���	������x1�G��_MNN*@/e!��ɧ��;��2���O��O��ÿ9���f�?�g�Sx�ݣ���T���lg^~w���G/'MG�l~��G�����w��Χ�Ϗ��'�=���Ý�������S4-��+�c��/�_�1�>��������ߜ����g�q�6�-\2�~K�@�X �$)���@^��h�i�E�2��mYSZ�'")D&d�TJR:�:I�ǒ�[��k�ϥ�	v��ڍNNww���-�ϳ�+&賝���퀏�ᵃ�G�x=;؟�w>ǰO��rtR�hJ��u��t�琁�7@L3z����������&������� 7���c�*\J~y�W���g��~<�6�|-ċ۸�,�!��$Ği����!��`�d��Lx�a ?��d>����ŗ� ���5��_�����n�*�T��N(5�$�7�	)��^o���VД�\^��-�l��`�:���o�"�[�H�X'	1x;�]�C�y��傎�O����<x���BW�Γ9"�@�aᚇm�:]��X:��[��"�W�f4M���ftEB��i	Q��6���gn�(��	^4BA�����{�N��`���t�ˏ{[�u�F�j���p>=8�����y���ʑ�J�����ѥ\8��+Vtr��|}s�<gTi��eʜ/X�2Y���J�[���)GPh%H*�Na��a�_Xs[ߌIf����ã���������l#�� }+��Tp �d>�p�x��wg��|���5j�g���r��n�w�?�����n�y_W��4"H '߁'XW.�bm�98]"V��*�HY$�5
A��R<���gg�`^�x��c��"�h�Ce��n�j��:��^�㏷K��JUd*�#�f���^ ��z.�pCp�-�g�9H��f,i괗^b�������cF��s�	M�v��D R��T$$b�(K��?s�K�n���/?nj�E$�sNR�$ǫ��`W3"�����C��N)jTm�W:�D�U��^� �,V������@�JfeY�"ey)�$I�D�N(�������O���l����?�����^�}��5��:���,�*��V4�>��I��"��}Q*ϴq�*3k���t��si+�)�}X���&e���̝L�QF'ZrS8�g��g�Lxs�X���6X���Pjk�y�����羐ڕ�d���e�{K��uڀ2�`0��e=�ٍɫ�}����9n��&���2�C,�C����������JK��D{��MAU����{3��c�ǂb���J,�^���a��\@�W��S^����Җ%�3��J��-�����I��?��J�j)���/����r�c����mf�\�9�
E�T�u����ώ���З�\�H�^��b��Q�&�@.+�I�J'].2e�����nB�~U(e%EP*f���JC�	����
3@y���$YP:�^CO?ٳ[��;{&�i ���0����b��~�ءF�+�g�ο�}�PM
l,��f왔�8M��$��T��	VL�uvؾ�߾}J�E(�\z��ǜ5�O���K��f��X�9o��`�%@���k��(+3�$wW�9f�l0� ~��l,HFWFX��|��I.�R��c#��I��:��$�%)��1��P��+������th�MtvW ��Z-J�(�o��v .n��:�$�/�4b�]o���A(=M(�|=Bj�2�R�%�Ɗ$���P�� %I�t��7�ap��
�N|=f$�{�1����?e��0,������x��ķ!����aD��j:#�Ĕ�C�����s9?�՜�Y�������2F5'a��z�h��b�{&�Q�Ns�l _��=̓o��[F�;@I2�암�o����p_2�'冱~ڢ��？W67a0�܂�,s�
Ye��uO͞B�ǀ/�&�k5�7v���%w�\�2�f-�Z@ݓ-��r ��V ��9�]6v�����n!�T��$�e�q������<O ��q7Vr���g����t����t��xP��g3���
�/�MgL�Ex�o��|��G�����={u� ,﭂z�f�I6wxa�@;FzP��u�%�O��[)٠�8*_gd uE��KfC�	�盍zD��1k1mPqZtp����&NjUd\(�'��ΰ"sF�BJ�;P'T��a�U|�r�B^eR��uF��q�+�'�Mc;�T�$��j|�����,�ē�"�\K"mʸ��X�Ғ�"��L<#�f�UD��^<yn7�;Y�%&.%�E�yn�B�-Z�[�gD~H��pa�]���v�,�)�"�i�q��¶�Jf�R��u5�X��� ��X1O�Y�xv��QL�h�)�}Ji�5���)�d �"W����vKF�n�1�h����87��h�|��T�{b�T��+�|/���K�(�VcM�jDA����?"�XPl��Zp�n ����cErχ�2��OH��7jZG�+�*og ���G,�%�밼ҭ�Y/N9&�d����I]�4ˬ�r����+D����������kC��12 7�&}��0��'-������qcF2\7��`�x;8����A��v��#�ƍ�<�e����=�| �z���f��]]�]S�Nr��G;b�}��	��ʖ�tYa+S]&)HN����&b�@�H�RW��]�2���"	>��;�l���H�W���j���Hn��zb����ʐT��Ҹu����DQ-�|��P}=\�)��0|�[�8��`��>�[��6]�mݿ���|+Zu� ��zv;�O0��Wr���*�]a����jJ��W�����������V�d�)��Gls�wtp��}x��W�bz8�MF�<zQ���p������B��󯮍�޼Ê$D��#�6$�2�W�<� �>3�2�����y��8�MAJg9��s�m��p[10����^���w�B��[Hlĳ8���p���:������&X����K����QlW��bA�@U�1��FQ�z��iN �K�s��z=�Ҝ� i�4�צ[_��i��V��SUS���%F��C=�;���i��
�Zf���>���@\/�1wxTy��c�n�YTʫ���^ʜ�-�m9���":�*���U@"��;�K�4Ղ�L��aM�U4�j�}Qm���gL��-�S.��Δe��eL�e�<oXX�na^EDK�,|V�"�8˓�LY��4�6�y6gMS�p�S4��o���釯���ȹeei|Q	,'�,��s�ʼi�`7�)o\X*�8��<��x1n�X��P	�J%|"�T/�� ��Ά�y�F`*�)�,-��FYu�/A�)��|]���-���]�t�;���ڝ�����,W�0�)�+x��\g��>�e��3�hV������-$/J��Z�B��4�m�l���4�f7��
Z���L��t��'E�u�yeX)��Ȧ���)Y~�*�D�@�s�E5v    놩��f6IR!R͓B��i!LY��t8`F!a0����a-����I�z�x������L��Ry�J�6.7��e�C4���A�Ԓ(�U�VQ0k|G.�K�5Lز,��Y����]��+J�,�u�B�^���w�wê�*�iH�TY�*+]Z��MMѝxF��
�h�Ë����-�J�m�
���Ҳ�e"5���L8L禅%�;W��{�CۭQC���Nxod*R�2�s�s�%�3-��*�`z�6)�xv��QrMSl�j!}G��:I�S��G�'a��]�����c�9�\bC�0� ��x��^��$��{	f}RS��o� }�Bc3����Ί�Kf�B�gY.r�G�F��кHK@���u������Dk���En�g7�SD��(�:m�$e[@�oR6�.IR��s�F��`��#2�q�1�}�q��y�h��;;��>�مWޭ~`����h2�.~��==�sG���f�BpQoo���c�����/'�#��Ý�g;�=����o�kӏ�Ã���g���l����D;H�B7Go.��|��ID���6��x6�h������N�������$|ziH�>?��$���zx�/!鎪%q��(��r��� �~�Q6-���\�29c\��I�4�2�j�2���� :g����{D��n��4�0Cp�|d��T0n���b�uO�͏(�$#h��)�AS��އ���6pRd��h�n�f�@��m,ŰY�.h���7�l!h����cUwƘہw\;+"H)M���r��`��H�&
�*Ă��\��vXr&#ʎM�Q�~���C�P��pG
b���3g������N)Ms\m�a�駋����&���$&+ui\��"��G�>|�H�� :I�vjٗ�� �w��١�/���|%�kUyH�:1Cc��"�9 ASuv���Y�C/��3&�^F��x<����� a$VV(��]��@2��!�[��Pp;��T�
��Ya�;z� n"�H�[�=[��[��1�� I���R�~� |w6�̧͐/_�z���+��ٷg�m��GP���n�y_	h�hV�[�TW�����s�'R9t�X�i�S$tC�OV$4�D����Y .ţ16\|v6}�5����ӓ��8��Տo���C!�_�P���U��^m0� ��^�㏷K��JUd��d�9D*̤A�Y�\�B׫~�4=-�Z��Zтn�uh��r	�Ƥ�%mҨ����[��fͮ�
g�ο�Q�_�_E
"��T���$�Z�\�[��2cAs+w M*��{Ht\?.b���$��J�+k.�W��+�BE"∲X���+ň|498X|���𽝳�a���ycŨ�)�Ӕ-�ю��`�"� ]��B�;ǽ��3�\�M�0��ak� wZcl0���{ƍ��y�K	��"Gt��玡�����g2sDX$86st[aDy&#a�`DE�"r
d�d����!�S��YD��#:���r�z��'7�4�^a��+	�~�Ir�^+��@�x���%
�EB`o����]�,��HC�
m#�I�;���J��Y�zɒ�4c! z��^�(�Ѻ[7�:��+���B���
x���fC��ʀ�� 8�D �����H�UF]�^��KE���}��j^/
�S�. ��&!���|K�:�՘Qd|�P�I,����~�I@W'lH�����H@�; ��$����8!�)MS���	��Y���)�� 8,(r��&qB�f���� v"F���EĐs�����F-KGV��:���1ꬂz��4��6����Ӕ�"*Hќi.�a����wD)��Kp����	�T�!fBX�e=�nө �@�lO� y��mT��
r��
r*�GT�s�I&�"�A�5�-d�Ƌ�J�v� ���"24��|��	n:�S��� H���G?"��X�_���]�*��#���l'��=�$?vm��$�cI�r�k,S߂Hx���|��0�M#�<Þ�ߎ=��p3@�۳?��M�h���!�dT$Y��?��6�ى��|�p�,\/R�d��]I����x������Ͽ�]
*7V���tcI2M�9�rh=ZѱT,bQFZs尻I�СLt�Vb�"�����M��Y-&<j�2T ��؞���1����=����_���k�u m��;x�?s��x?����?�c�j�XDgz���Z�v�`��(b6	ˁ�9�"
�(�Z��Z+g��&W��"~�-�����S~�XD�z��Úː'{߉����\��8�x�t� �̥h��@p�l�y�hz�gG��
'��&�x�hz�����el�n�#� &Y�P���n�*z�!��*G2)��j��*�;+��P��ְh�L����l<�a����N7�m�fH��3tJ�����1�E'��ít��V|�'�+��I$z�j��_��;pS��2E�6ֵ��f�q瘅X#9���C���-w�1��8s�F6IO�p�>�qC�b�iN�1�1?�ea6M� >I��ņ����	�Fr�M�%���e-FӋMl��A�hz��>���P���H����`_�ٗ֕fe��a���hE6p���!DF�o�5$�
&V��j��l ?i*h�h��ބ�H�S6��d�@b.y#��7!�	���7�K�I�3���7x�zܧ�I{(����l$����$���éʝ�S�����Y������Z������g7U���K��X��r8:ce�""����̪f��Y�o#*"+8��?�Z����b�Y����,=O�����P�z�Ud*�,8�B@k����&RK��ҝ���RC�h�UTE�Z�(]e�bo�� ]��(Nt���� �񹇮�Hu݈� D,'�BY���B�A�^'���9�MK�8�	(q���]��=����	�J׋���y�bVSo�b�:� ���pd!�������$�:x�Y�Wj�A�ɳ 1�jRx�sņ�e{��i��i�:�6��n��u�L=S8��0#�FQ�:y����f��)k��uZ,@L3�vJH5(6�bGL��y�<h��7��SFb/�E�MS�I�s���Wl������*>(6�b7�g$ӗ��6nP���@�i��g �	'o����yf���J2���uŎ�3����o\��GL@L2C���j��[@1e�!V~��q��)�D�Q����}g�hh"���͔�Z���-�}�$sd�?��^Y���U'�������
�����df�δ�`)-
s;�ϟ�M?ٺ�ӯο>��H/�e ����-����$��Ց\>�0xN�>���u-�	��>[\�VPU�{��.��
_�K��'#cҭ�����ܮ�e��� t���;�`�W��ZO7�Vk�1X��-���c��ѧG���l��q���p��_�>��
�E]tr�f2����:��/���m�kӏ�Ã��#<�̏f;(=�dM����>ܽGhLL�KPc�cr<�~�tzraMFG���|��h�b>�4$O�VDY�Ff$�Yяۈ�5����(w]���~\�Cr�@��Û�A7B�"�k!�@`�i\D���x 
l��Z�!���)$h���=WfPlŎh"¨�5�&��A#i�v5�1��G�#��ќ�e����G���b�Ĝ�%I��I�ϕ� ���4'>g��C@��:- &I�9+̚��т��i1�X�܋���{q��X��$��C��:�Q���ђ�q�SlB ��3����Nx�$�@��s/���j���'V��YܾN�q�)o�\�3�P�Gi=�uF�S�A�c�Pm��{Ĉ�$ci��> ���`=BѸ���#>��@H�cީ0%n0�+��E�DY4�E�#5�{w�"MҜ� k���qW��w��� D\��9* �
��o�q�,b� p���wfM�3����C��������+-�|E"~N��(�2��� �
  ����[�#� �X� �07�9���ÍU����6��zLn#����O ������=R]���Y#Gr��j(sj���^ˤ7�IhS�r1r�&#g���+l��ӽ�k �P4�\��_�?����o��*�G,������!�����p�&E��p����u����D�s�A�N"�N�߹
{��J"F��E��u�L��3w��<O�1���^��+�)X'�㈓}6�:_'���\2�`�x�xx��e([3��?�����߆P�������U��N�) i�%�����ޝM'�i�,_�)	���³W|�@��p�$,��JD��Y�ӶN��xeW�E�XQ����I���o5bBo���b�)7��&	��}�T��{Y����HD�T�f��3LY�W�1H�uQ'�DP��0V��B�D\'u�0H�]�0+��t�3ȴ�ӂ�:ɦg���cc�+R@�?�J#r�w��u���J�����A"h��`
v�j���cFq*�3F
1HA7RPg�P�!)Ќ�Յ��5����A"h�
���.tcd�gR@2�<�3ϸ�^��xF>�;���]h��؅�[$:��[�)NCF2�Hʈ_�4��qo��W��Z*t���4���#��(b��]�돯�M_���X���NON.��|v�c�i��K��Uf���9�c*�]-+ķ�.N��dO3ģW��4\�f ����Hj���"yb�3 إ�]XJ�j �H���,�J	�Ω�]b,I��Z��G��h�����cM2i����>��u�^F0[�(�p����I��H����B>��T�#���4G׃p8.�܈��U^+_��6_�?[\�V�������+|#�B���z���*�
xw��-��C4	���ȌtA����%�`>�i�����J��}8��{�O���_�}��5�^�����
k���:���e�Q�f�V�{XE��^+�=<=8���A8
���_4Xsq��yb����R���6K`��O�+s����`J�<P������FY�`���T=�NH,|k�r7�1��<?a�%�#L����=Ɯ���׎��.����8
rb��� ���h͒_�ƺ�䗎�c���H��o�P��$��T�����h)�'L�lg��,ՉʝӖ�7N&^8Vd���#)�ȕp��l�cM�/�̢��<z��v����sKs���ڱ ��:� %�p�^��N�������N��dZ3�D�DYH���	+:��L�BL�#9;��-��FY�rM1��j���@��� �pCP�i�c6d���BAܕ+m�Ұ2a�q��F�����7��d��|m�q<;QVC���Eϼ5�>Rd �Rϔ!vx�����W�ǘ<��B^7$�f�$C+��(�� ��<� �s�N�kv���m�$=/Όƞb�ynw�$�e�:�p0
I)�`��q�m��eç��00��D���y�mX��-�R��{�,أl�`.&T����`�ٛ�S��s�o��3@�7�>��0�&�sߟ�S������)�w5ł�l֌W�N��̏�����������m?͋�!G�+1
�]�c���xf�����Ǜ�;�Zk �f�p.�й(x��FÕU�+R���U�VQH���w&͢��&�y!�I/��J](^��y�J���JkXX�na58D+�YR���|*L�8i�����<a,1y㪢��㘫h�V��x�$K9�=τ)�K�ڃ�3��<-�M<ōKdw� .��Cf�LL�IYɼ*�(�T{Л̖��NK��i�"��3+-��FYU��\�%�&��>Ih{;��tSݍ����q���>�id�ڮĘ��u�kY}�̵�z����［k��ʠ�]���k��R�4Q��X&t���h@��5J�U�����m�������e^r.%�i�%��3��qY�B�Duc�xd�|=J��-�
 *�ڰ��y��B��8!�d'�<�e��V6U��ף�UV�h����)5O�\_��'��)�&�V+g|�:u� ��(�ͅ��A������ Y�u�tb�4�,���en��GK�Q*e0=ZB��Z�#��(k=J�Rd! _m�����m�Q*��T_�R���#������!8j]�R��u=J�17=?x�M�}�ʣ(�W�T�-�X��Hǻ:�܇t�D�dj�K�YbR�eޤ<7�T��Cx�T�6�E��G\EG �j!h*L�{^�"59gi������d�a���j��8կ��yV��,�"���ܛ�&^��]M~4�c�"Z�q{�TIVf�I�q�Y��\�T3Y2�,�k �a�aaɕ�/��W���z"z"S��\3m���N9������4]�R�k�C��\j<�QVՀ/=b���zr�-��7�j��A'HM��t���+�$�JF��A�V"��\��0b.հ(JU���{,�O?��������i��U�M��5��/�u�Un�
|��Hr���!�c�����\'
�/4�a�̕8�l�w ߆����?s�X~��GCFW>T��qhlW����t��zV���9cB�u��x�����.�T�y�U�f)
'4Ť&�����K��_U�gG�������D���=Z���nB�~U(uJKp?@����9�oR8_�_q�ڽ��A��HЎ���P!�|�Q�PR�Jm�����v��[o�Ĵ�s      �	      x������ � �      �	   Z  x�՘Kk1F����2�z|�t��*]��&��	����I����򌝨co4;�Y��Ǚ��C�b'��A����$�N`rw=���]o6�0g��_���s}c�0���G��||zڬnW?.ׯsv�81g�_n��~0����|^-��������j.���Q�̷��}^�o�@�?8�l��a��z��)ld�U����ڥ]�!,gdEg�S��-�mW��w�tg���q��*eXz��)p(�x�������{գ-�a����j+����E�P^&�x�J�*�{/��XG��#���dt�{"P�ex�2F��Lz�NA��X,ÁO%��fT��{'�;D�n�4q���k�d��{�r�D*�U&�^F�&�\�A*qmKW�����ĖOR�/�GH����q��e�+#�	�{F��w��2=v/ç]|Le
�T� ��2�{�b솩Z�49`�2��0�L�Dj���'#ad�s�u#d���Z�:�a��`k������k;�f�A��q��?C�hӘ��dd�IF !I�g�R�PF�`�XWͰ{����V�L�߽2"�XW�Ȱw2vf����]3�N���n#      �	      x������ � �      �	      x������ � �      �	   �  x����N�0ǯO�bO�A)㎁6M���)2�)I\��o?��uCj;��q���W'�#3�̾.�Ƌ����tz5���aO� �:۲����sV���n��7�i��*.���Z�v�j��B����W+���ݶۖי�`����d�[=�5�dzP˶o�o5�`�P A��X�v.���«ȩ�
H>%S,��	,��Keȏ��Z!&'��� �^��f��J)��~�1ą2 q��d�e�sc�Z��-�4�QC�F� �},o��g����b�A�ڊA���C�oqq"�F����1BR�ތ1����J�,D�R��>Vm�0�<;jvA��R��Vb��y�%��;�*)hy <oHԗ;R�V��>�[=F�VFֈ����5n[�u�^"x[+g� I���'J^���ܫ/BiE�!	�~"̱�H��U)��Z!>'���>��s,�����x����˳��2��2�ah��7J�����O|+��T�����Lb|V�$�k�"�A���Y���Q�@�V0���C�{="�unG8ܫ!D�E��>�f�L@=��M��ox	���>�F�D)9n��T�x����5��^�5g�`���	4%��D�����,�Ӂ?I�v݋��f��s�kg��I�k�'�)�����/e��Rt&��$FNB�G��ߒ���>s{B������G}7�_���yJ*��U�C5�4��1��i���sG8�Sp�m�&_�`HVV�
�i����-�D�/£��G�Hiq;k���z����	��Y*�L`)W����\��2"X�k��+1,G5Sa��2q��a6Ҁ[u���V"�E�a��ZMR��I�U�B����@�҂F�e��`cݗ�����\��4�e��Ӂ?���t2XRj�۲Y�뒮AH��]r��1��	և+r�.��ՇR��#��l>�v`�!��0�����y"���W���,)'=���^O�h4�_�      �	   �  x��T�n�@]�W��m5�ĒM��&�Z�l�����Q�3�<���J]�+��&Cԟ��Z����sϜ������q�(�ON� ���UG��izZ0.�Ti��V%h�4�^���Z	��x-O~Oy�4�\Ir�4+��6�Krq��\01i@1�cy4r@ާ�Q}N.?|�(�>�j�Py�)�d9��jM1�� m����eY�ekL��m��Ur����ĭUeM2e���Dݘ�!�L�PS. ���eX�+$����������N��������n�y��+KgPN%3�^�
�z�RYj� 5  ��%��\c���D�r�cb
O!��z�>$�O����v�d�
�m'e4c�y�>魵P�vՏ-Q<��6���I�+!�S�&�+�O�JoG��$n)�e�	�f 07K-�W���-�x���Y�m�+^����{'@�v���rA�r�F_���]�W崙����	�賏�Xj���f~��x�U�ov�9$g{�bV��^�)�V*k��l	����v� |����8�Ⰷ�U��o����afv��[W���=�������>W���s޵* 4c_������)��Β`0���uY/W�F�����Q�Ԩ���-�ܨ��Y�68��n��L@      �	   1  x���mo�8�?ǿ��@�8n^�ߺ}��!�I��,`(6��*K�$����#gHj(QE*t�Ր�C�CJ�����ur~�,��qq�������?���>����nW��f�~��i�u~`�i�c�k.��l]�l�n�ϵ��(w�*ݔ���a~H���������2V6�%������b�ְ��,#�?
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