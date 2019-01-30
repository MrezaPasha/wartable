package org.sadr.service.main.rpc._core;

import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr.service.config.IOCContainer;
import org.sadr.share.main._types.TtMapCategory;
import org.sadr.share.main._types.TtRoomType;
import org.sadr.share.main._types.TtServiceUserState;
import org.sadr.share.main.grade.Grade;
import org.sadr.share.main.grade.GradeServiceImp;
import org.sadr.share.main.map.Map;
import org.sadr.share.main.map.MapServiceImp;
import org.sadr.share.main.orgPosition.OrgPosition;
import org.sadr.share.main.orgPosition.OrgPositionServiceImp;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.serviceUser.ServiceUserServiceImp;

import java.util.List;

public class Test {



    public static void inserTestRoom()
    {
        try
        {
            Room room = new Room();
            room.setCurrentMeeting(null);
            room.setRoomType(TtRoomType.FirstModel);
            room.setName("otaghe jalase");
            room.setDescriptions("jvdvfvc");
            room.setTableLenght(100);
            room.setTableWidth(100);
            room.setRoom_maps(null);
            RoomServiceImp roomServiceImp = (RoomServiceImp) IOCContainer.GetBeans(RoomServiceImp.class);
            roomServiceImp.save(room);

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }
   /* public static void insertTestConf()
    {
        try
        {
            ServiceConfigServiceImp serviceConfigServiceImp = (ServiceConfigServiceImp)IOCContainer.GetBeans(ServiceConfigServiceImp.class);
            ServiceConfig serviceConfig = new ServiceConfig();
            //serviceConfig.setBannedTimePeriod(Duration.ofSeconds(300));
            serviceConfig.setBannedTimePeriod(300);
            serviceConfig.setBannedRetryCount(5);
            //serviceConfig.setLockBannedTimePeriod(Duration.ofSeconds(300));
            serviceConfig.setLockBannedTimePeriod(300);
            serviceConfig.setSoftwareID("1");
            serviceConfig.setSoftwareName("warTable");
            serviceConfig.setSoftwareVersion("1.0.1");
            serviceConfig.setSoftwareSize(255);
            serviceConfig.setLastSoftwareVersion("1.0.1");
            serviceConfig.setLastSoftwareSize(254);
            serviceConfig.setServerHostname("sadr.local");
            serviceConfig.setServerIP("192.168.1.23");
            serviceConfig.setServerPort(5672);
            serviceConfig.setServerLoginUsername("khabir");
            serviceConfig.setServerLoginPassword("12345678");
            serviceConfig.setServerQueue("serverRPC");
            serviceConfig.setLoggerIPAddress("127.0.0.1");
            serviceConfig.setLoggerDbName("logger");
            serviceConfig.setLoggerDbUsername("root");
            serviceConfig.setLoggerDbPassword("P@ssw0rds");
            serviceConfig.setLoggerPort("5432");
            serviceConfig.setLoggerTableName("log");
            serviceConfigServiceImp.save(serviceConfig);

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }*/
    public static void insertTestMap()
    {
        try
        {
            Map map = new Map();
            map.setName("iranMap");
            map.setCategory(TtMapCategory.WarMap);
            //int myYear = 2014;
            ///int myMonth = 3;
            //int myDay = 24;
            //map.setCreationTime(Timestamp.valueOf(String.format("%04d-%02d-%02d 00:00:00",
             //   myYear, myMonth, myDay)));
            map.setDescriptions("testtttttttttttt");
            map.setFileName("cdcscsdcdsc");
            //map.setThumnailNam("d::::");
            map.setUpdateDateTime(ParsCalendar.getInstance().getShortDateTime());
            map.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
            map.setAssignRooms(null);
            MapServiceImp mapServiceImp = (MapServiceImp) IOCContainer.GetBeans(MapServiceImp.class);
            mapServiceImp.save(map);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static   ServiceUser inserTestUser()
    {
        try
        {
             ServiceUserServiceImp serviceUserServiceImp = (ServiceUserServiceImp) IOCContainer.GetBeans(ServiceUserServiceImp.class);
             GradeServiceImp gradeServiceImp = (GradeServiceImp) IOCContainer.GetBeans(GradeServiceImp.class);
             OrgPositionServiceImp orgPositionServiceImp = (OrgPositionServiceImp) IOCContainer.GetBeans(OrgPositionServiceImp.class);
             /*PrivilageClassServiceImp privilageClassServiceImp = (PrivilageClassServiceImp)IOCContainer.GetBeans(PrivilageClassServiceImp.class);*/
            // insert grade
            Grade grade = new Grade();
            grade.setCode(1);
            grade.setValue("سرهنگ");
            gradeServiceImp.save(grade);
            // insert organization position
            OrgPosition orgPosition = new OrgPosition();
            orgPosition.setId(1l);
            orgPosition.setCode(1);
            orgPosition.setValue("فرمانده");
            orgPositionServiceImp.save(orgPosition);
            // insert privilage class
            /*PrivilageClass privilageClass = new PrivilageClass();
            privilageClass.setId(1);
            privilageClass.setCode(1);
            privilageClass.setValue("user");
            privilageClass.setPrivilageFlag("10101001010101010");
            privilageClassServiceImp.save(privilageClass);*/
            // here we insert user
            ServiceUser serviceUser  =  new ServiceUser();
            serviceUser.setName("mreza");
            serviceUser.setFamily("pasha");
            serviceUser.setBanned(TtServiceUserState.UnBannedUser);
            //Timestamp timestamp = new Timestamp(new Date().getTime());
            String timestamp = ParsCalendar.getInstance().getShortDateTime();
            //serviceUser.setCreationTime(timestamp);
            serviceUser.setCreationDateTime(ParsCalendar.getInstance().getShortDateTime());
            RoomServiceImp roomServiceImp = (RoomServiceImp) IOCContainer.GetBeans(RoomServiceImp.class);
            List<Room> rooms = roomServiceImp.findAll();
            Room room = rooms.get(0);
            serviceUser.setLastRoom(room);
            serviceUser.setFailedDateTime(null);
            serviceUser.setDeleted(TtServiceUserState.UnDeletedUser);
            serviceUser.setUserName("user2");
            serviceUser.setPassword("sadr@123");
            serviceUser.setLastPassword(null);
            //serviceUser.setEnableAccpetPrivateCha(1);
            serviceUser.setFailedPasswordCount(0);
            serviceUser.setGrade(grade);
            serviceUser.setOrgPosition(orgPosition);
            serviceUser.setUserModel(null);
            //serviceUser.setPrivilageClass(privilageClass);
            serviceUserServiceImp.save(serviceUser);

            return serviceUser;

        }
        catch (Exception e)
        {
            Utils.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            return null;
        }
    }
/*    public static void testInsertConf()
    {
        try
        {
            ServiceConfigServiceImp serviceConfigServiceImp = (ServiceConfigServiceImp)IOCContainer.GetBeans(ServiceConfigServiceImp.class);
            ServiceConfig serviceConfig = new ServiceConfig();
            serviceConfig.setBannedTimePeriod(300);
            serviceConfig.setBannedRetryCount(5);
            serviceConfig.setLockBannedTimePeriod(300);
            serviceConfig.setSoftwareID("1");
            serviceConfig.setSoftwareName("warTable");
            serviceConfig.setSoftwareVersion("1.0.1");
            serviceConfig.setSoftwareSize(255);
            serviceConfig.setLastSoftwareVersion("1.0.1");
            serviceConfig.setLastSoftwareSize(254);
            serviceConfig.setServerHostname("sadr.local");
            serviceConfig.setServerIP("192.168.1.23");
            serviceConfig.setServerPort(5672);
            serviceConfig.setServerLoginUsername("khabir");
            serviceConfig.setServerLoginPassword("12345678");
            serviceConfig.setServerQueue("serverRPC");
            serviceConfigServiceImp.save(serviceConfig);
        }
        catch (Exception e )
        {
            Utils.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
        }
    }*/
}
