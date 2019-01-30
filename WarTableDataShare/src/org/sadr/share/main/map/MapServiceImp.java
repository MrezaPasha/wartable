package org.sadr.share.main.map;

import com.google.gson.Gson;
import org.hibernate.criterion.Restrictions;
import org.sadr._core.meta.generic.GenericServiceImpl;
import org.sadr._core.utils.OutLog;
import org.sadr._core.utils.ParsCalendar;
import org.sadr._core.utils.RePa;
import org.sadr.service.main.nonRpc.publish._core.State;
import org.sadr.service.main.nonRpc.publish._types.TtState;
import org.sadr.service.main.rpc._core.TtGlobalId;
import org.sadr.service.main.rpc._types.TtErrors;
import org.sadr.service.main.rpc.broadCastMessage.Bl.BroadcastResponse;
import org.sadr.service.main.rpc.brokerUtils.BrokerUtils;
import org.sadr.service.main.rpc.rpcRequest.RpcRequest;
import org.sadr.service.main.rpc.rpcResponse.ErrorResponse;
import org.sadr.service.main.rpc.rpcResponse.RpcResponse;
import org.sadr.share.main.Room_Map.Room_Map;
import org.sadr.share.main.Room_Map.Room_MapServiceImp;
import org.sadr.share.main.Room_Map._types.TtRoomMapDeleted;
import org.sadr.share.main._types.TtUserRoleFlags;
import org.sadr.share.main.layer.Layer;
import org.sadr.share.main.layer.LayerServiceImp;
import org.sadr.share.main.log.models.logger.BL.LoggerBL;
import org.sadr.share.main.map._types.TtMapResponseFields;
import org.sadr.share.main.map.model.DownloadResponse;
import org.sadr.share.main.map.model.Info;
import org.sadr.share.main.meeting.Meeting;
import org.sadr.share.main.meeting.MeetingServiceImp;
import org.sadr.share.main.mrml.Mrml;
import org.sadr.share.main.mrml.MrmlServiceImp;
import org.sadr.share.main.mrml._types.TtMrmlDeleted;
import org.sadr.share.main.privateTalk.PrivateTalkServiceImp;
import org.sadr.share.main.room.Room;
import org.sadr.share.main.room.RoomServiceImp;
import org.sadr.share.main.roomServiceUser.Room_ServiceUser;
import org.sadr.share.main.roomServiceUser.Room_ServiceUserServiceImp;
import org.sadr.share.main.serviceUser.ServiceUser;
import org.sadr.share.main.sessions.Sessions;
import org.sadr.share.main.sessions.SessionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Component
public class MapServiceImp extends GenericServiceImpl<Map,MapDao> implements MapService {

    private SessionsServiceImp sessionsServiceImp;
    private Room_MapServiceImp roomMapServiceImp;
    private RoomServiceImp roomServiceImp;
    private Room_ServiceUserServiceImp roomServiceUserServiceImp;
    private LayerServiceImp layerServiceImp;
    private MrmlServiceImp mrmlServiceImp;
    private MeetingServiceImp meetingServiceImp;
    private PrivateTalkServiceImp privateTalkServiceImp ;
    private static final String SUB_MAP_FOLDER = "G:/WatTable/FINAL SERVER/NewServer/Java FrameWork/ftp/map/";



    @Autowired
    public void setPrivateTalkServiceImp(PrivateTalkServiceImp privateTalkServiceImp) {
        this.privateTalkServiceImp = privateTalkServiceImp;
    }

    @Autowired
    public void setMeetingServiceImp(MeetingServiceImp meetingServiceImp) {
        this.meetingServiceImp = meetingServiceImp;
    }

    @Autowired
    public void setMrmlServiceImp(MrmlServiceImp mrmlServiceImp) {
        this.mrmlServiceImp = mrmlServiceImp;
    }

    @Autowired
    public void setLayerServiceImp(LayerServiceImp layerServiceImp) {
        this.layerServiceImp = layerServiceImp;
    }

    @Autowired
    public void setRoomServiceUserServiceImp(Room_ServiceUserServiceImp roomServiceUserServiceImp) {
        this.roomServiceUserServiceImp = roomServiceUserServiceImp;
    }

    @Autowired
    public void setRoomServiceImp(RoomServiceImp roomServiceImp) {
        this.roomServiceImp = roomServiceImp;
    }


    @Autowired
    public void setRoomMapServiceImp(Room_MapServiceImp roomMapServiceImp) {
        this.roomMapServiceImp = roomMapServiceImp;
    }





    @Autowired
    public void setSessionsServiceImp(SessionsServiceImp sessionsServiceImp) {
        this.sessionsServiceImp = sessionsServiceImp;
    }

    public String generateTileAddress(int tileX , int tileY , Map map , Layer layer)
    {
        String tileDirectory = SUB_MAP_FOLDER + map.getName() + "/" + layer.getName() + "/" + "tile_" + tileX + "_" + tileY ;
        return tileDirectory;


    }
    public RpcResponse generateGetMeetingInfoResponse(List<Layer> layers, Map map, Meeting meeting,  TtErrors error)
    {

        /////////////////////////
        List<Long> layerIds = new ArrayList<>();
        /////////////////////////
        RpcResponse rpcResponse = new RpcResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        List<Double> opacities = new ArrayList<>();
        List<Long> orders = new ArrayList<>();
        rpcResponse.setId(TtGlobalId.GetMapSessionInfo.ordinal());

        try
        {
            if (error == TtErrors.NoError)
            {
                errorResponse.setCode(error.ordinal());
                errorResponse.setDescription(error.getErrorValueService());
                rpcResponse.setError(errorResponse);
                java.util.Map result = new HashMap();

                Info Info = new Info();
                Info.setName(map.getName());
                Info.setDescription(map.getDescriptions());
                List<org.sadr.share.main.map.model.Layer> Layers = new ArrayList<>();
                for (Layer layer:layers)
                {
                    List<Double> Bounds = new ArrayList<>();
                    org.sadr.share.main.map.model.Layer tmpLayer = new org.sadr.share.main.map.model.Layer();
                    tmpLayer.setId(layer.getId());
                    tmpLayer.setName(layer.getName());
                    tmpLayer.setType(layer.getType());
                    tmpLayer.setSamplesWidth(layer.getSamplesWidth());
                    tmpLayer.setSamplesHeight(layer.getSamplesHeight());
                    tmpLayer.setTileCountHorz(layer.getTileCountHorz());
                    tmpLayer.setTileCountVert(layer.getTileCountVert());
                    tmpLayer.setMinHeight(layer.getMinHeight());
                    tmpLayer.setMaxHeight(layer.getMaxHeight());
                    tmpLayer.setSpacingVert(layer.getSpacingVert());
                    tmpLayer.setSpacingHeight(layer.getSpacingHeight());
                    tmpLayer.setSpacingHorz(layer.getSpacingHorz());
                    // for opacity and  order
                    Mrml mrml = mrmlServiceImp.findBy(Restrictions.and(Restrictions.eq(Mrml._MEETING,meeting),Restrictions.eq(Mrml._LAYER,layer),Restrictions.eq(Mrml.DELETED,TtMrmlDeleted.Undeleted)));
                    opacities.add(mrml.getOpacity());
                    orders.add(mrml.getOrder());
                    layerIds.add(layer.getId());

                    // for Bounds
                    String boundTemp = layer.getBounds();
                    boundTemp = boundTemp.replace("[","");
                    boundTemp = boundTemp.replace("]","");
                    String[] listBoundTmps = boundTemp.split(",");
                    for (String listBoundTmp:listBoundTmps)
                    {
                        Bounds.add(Double.parseDouble(listBoundTmp));
                    }
                    tmpLayer.setBounds(Bounds);
                    Layers.add(tmpLayer);
                }

                /////
                /*java.util.Map Info = new HashMap();
                Info.put("Name",map.getName());
                Info.put("Description",map.getDescriptions());
                Info.put("Layers",layers);*/


                /////
                while(opacities.size() == 8)
                {
                    opacities.add(1.0);
                    orders.add(1l);
                }
                Info.setLayers(Layers);
                String pasha = new Gson().toJson(Info);
                result.put("Info",pasha);
                result.put("MapId" ,map.getId());
                result.put("MapSessionId",meeting.getId());
                result.put("Opacities",opacities);
                result.put("Orders",orders);
                result.put("LayerIds",layerIds);
                rpcResponse.setResult(result);
                return rpcResponse;
            }
            else
            {
                errorResponse.setCode(error.ordinal());
                errorResponse.setDescription(error.getErrorValueService());
                rpcResponse.setError(errorResponse);
                java.util.Map result = null;
                rpcResponse.setResult(result);
                return rpcResponse;

            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
            errorResponse.setCode(TtErrors.OperationalErrorOccured.ordinal());
            errorResponse.setDescription(TtErrors.OperationalErrorOccured.getErrorValueService());
            rpcResponse.setError(errorResponse);
            java.util.Map result = null;
            rpcResponse.setResult(result);
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            return rpcResponse;
        }
    }

    public  boolean isUserHavePermanentAdminPrivilages(Room_ServiceUser roomServiceUser)
    {
        try
        {
            if (roomServiceUser.getPermanentUserRoleFlag() == TtUserRoleFlags.Admin || roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.TempAdmin || roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.TempAdminViewSource || roomServiceUser.getTemporaryUserRoleFlag() == TtUserRoleFlags.AdminViewSource)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            return false;
        }
    }

    public RpcResponse generateGetCurrentRoomMapsResponse(List<Room_Map> roomMaps , TtErrors errors)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.GetRoomMaps.ordinal());
        ErrorResponse errorResponse = new ErrorResponse();
        List<Long> mapIds = new ArrayList<>();
        List<String> mapNames =  new ArrayList<>();
        List<String> mapDescriptions = new ArrayList<>();
        java.util.Map result = new HashMap();
        try
        {
            if (errors == TtErrors.NoError)
            {
                errorResponse.setCode(errors.ordinal());
                errorResponse.setDescription(errors.getErrorValueService());
                for (Room_Map roomMap:roomMaps)
                {
                    mapIds.add(roomMap.getMap().getId());
                    mapNames.add(roomMap.getMap().getName());
                    mapDescriptions.add(roomMap.getMap().getDescriptions());

                }
                result.put(TtMapResponseFields.MapIds,mapIds);
                result.put(TtMapResponseFields.MapNames,mapNames);
                result.put(TtMapResponseFields.MapDescriptions, mapDescriptions);
                rpcResponse.setResult(result);
                rpcResponse.setError(errorResponse);

            }
            else if (errors != TtErrors.NoError)
            {
                errorResponse.setCode(errors.ordinal());
                errorResponse.setDescription(errors.getErrorValueService());
                rpcResponse.setResult(result);
                rpcResponse.setError(errorResponse);
            }




        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            rpcResponse = null;
        }
        return rpcResponse;

    }


    public RpcResponse getCurrentRoomMaps(RpcRequest rpcRequest) {


        RpcResponse rpcResponse = new RpcResponse();
        try {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            double doubleRoomId = Double.parseDouble((String) rpcRequest.getParams().get("RoomId").toString());
            Double d = new Double(doubleRoomId);
            Integer roomId = d.intValue();
            Sessions sessions = sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID, sessionId), Sessions._SERVICE_USER);
            ServiceUser serviceUser = sessions.getServiceUser();
            Room room = roomServiceImp.findBy(Restrictions.eq(Room.ID, roomId));
            if (sessions != null) {
                if (room != null && serviceUser != null) {
                    Room_ServiceUser roomServiceUser = roomServiceUserServiceImp.findBy(Restrictions.and(Restrictions.eq(Room_ServiceUser._ROOM, room), Restrictions.eq(Room_ServiceUser._SERVICE_USER, serviceUser)));
                    if (isUserHavePermanentAdminPrivilages(roomServiceUser))
                    {

                        List<Room_Map> roomMaps = roomMapServiceImp.findAllBy(Restrictions.and(Restrictions.eq(Room_Map._ROOM,room),Restrictions.eq(Room_Map.DELETED,TtRoomMapDeleted.Undeleted)),Room_Map._MAP);
                        if (!roomMaps.isEmpty())
                        {
                            rpcResponse = generateGetCurrentRoomMapsResponse(roomMaps,TtErrors.NoError);
                            return rpcResponse;


                        }
                        else if (roomMaps.isEmpty())
                        {
                            rpcResponse = generateGetCurrentRoomMapsResponse(null,TtErrors.Map_RoomHasNoAnyMap);
                            return rpcResponse;
                        }
                    }
                    else
                    {
                        rpcResponse = generateGetCurrentRoomMapsResponse(null,TtErrors.UserDoesntHaveEnoughPermission);
                        return rpcResponse;

                    }


                }
                else if (room == null || serviceUser == null)
                {
                    rpcResponse = generateGetCurrentRoomMapsResponse(null,TtErrors.RoomOrUserDoesntExist);
                    return rpcResponse;

                }


            }
            else if (sessions == null)
            {
                rpcResponse = generateGetCurrentRoomMapsResponse(null,TtErrors.SessionIsNull);
                return rpcResponse;


            }


        }
        catch (Exception e)
        {
            LoggerBL.insertCriticalLog(ParsCalendar.getInstance().getShortDateTime(), OutLog.getErrorPosition(),e.getMessage());
            rpcResponse = generateGetCurrentRoomMapsResponse(null,TtErrors.OperationalErrorOccured);
            return rpcResponse;



        }
        return rpcResponse;
    }
    public RpcResponse generateDownloadFileResponse(DownloadResponse downloadResponse , TtErrors errors)
    {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setId(TtGlobalId.GetMapSessionInfo.ordinal());
        java.util.Map result = new HashMap();
        result.put("size",downloadResponse.getSize());
        result.put("content",downloadResponse.getContent());
        rpcResponse.setResult(result);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(errors.ordinal());
        errorResponse.setDescription(errors.getErrorValueService());
        rpcResponse.setError(errorResponse);
        return rpcResponse;

    }

    public RpcResponse getMapSessionInfo(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();
        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            Sessions sessions =  sessionsServiceImp.findBy(Restrictions.eq(Sessions.SESSION_ID,sessionId),RePa.p__(Sessions._SERVICE_USER,ServiceUser._LAST_ROOM,Room._CURRENT_MEETING,Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));
            if (sessions != null)
            {
                ServiceUser serviceUser = sessions.getServiceUser();
                Meeting meeting = sessions.getServiceUser().getLastRoom().getCurrentMeeting();
                if (meeting != null)
                {
                    Map map = meeting.getCurrentRoomMap().getMap();
                    if (map != null)
                    {
                        List<Layer> layers = layerServiceImp.findAllBy(Restrictions.eq(Layer._MAP,map),Layer._MRMLS);
                        if (!layers.isEmpty())
                        {
                            rpcResponse = generateGetMeetingInfoResponse(layers,map,meeting,TtErrors.NoError);
                            if (privateTalkServiceImp.isMeetingIsCurrentlyRunning(meeting))
                            {
                                List<Integer> broadcastFlags = new State().setStateBytes(true, TtState.SESS__UPDATE_START_MAPSESSIONTIME);
                                java.lang.String unicastMessage = BroadcastResponse.generateBroadcastResponse(TtGlobalId.Update, TtErrors.NoError, broadcastFlags, new RpcResponse());
                                BrokerUtils.unicastMessage(unicastMessage,sessions.getSessionId());
                            }
                            return rpcResponse;


                        }
                        else if (layers.isEmpty())
                        {
                            rpcResponse = generateGetMeetingInfoResponse(null,null,null,TtErrors.MapDoesntHaveAnyLayer);
                            return rpcResponse;

                        }


                    }
                    else if (map == null)
                    {
                        rpcResponse = generateGetMeetingInfoResponse(null,null,null,TtErrors.MapDoesntExist);
                        return rpcResponse;

                    }

                }
                else if (meeting == null)
                {
                    rpcResponse = generateGetMeetingInfoResponse(null,null,null,TtErrors.Meeting_MeetingIsNull);
                    return rpcResponse;

                }
            }
            else if (sessions == null)
            {
                rpcResponse = generateGetMeetingInfoResponse(null,null,null,TtErrors.SessionIsNull);
                return rpcResponse;

            }

        }
        catch (Exception e)
        {
            rpcResponse = generateGetMeetingInfoResponse(null,null,null,TtErrors.OperationalErrorOccured);
            return rpcResponse;
        }
        return rpcResponse;
    }

    public File getMapTile(RpcRequest rpcRequest)
    {
        RpcResponse rpcResponse = new RpcResponse();

        try
        {
            String sessionId = (String) rpcRequest.getParams().get("SessionId");
            double doubleMapId = Double.parseDouble((String) rpcRequest.getParams().get("MapSessionId").toString());
            Double d = new Double(doubleMapId);
            Integer meetingId = d.intValue();
            /////
            double doubleLayerId = Double.parseDouble((String) rpcRequest.getParams().get("LayerId").toString());
            Double e = new Double(doubleLayerId);
            Integer layerId = e.intValue();
            ////
            double doubleTileX = Double.parseDouble((String) rpcRequest.getParams().get("TileX").toString());
            Double f = new Double(doubleTileX);
            Integer tileX = f.intValue();
            ////
            double doubleTileY = Double.parseDouble((String) rpcRequest.getParams().get("TileY").toString());
            Double g = new Double(doubleTileY);
            Integer tileY = g.intValue();

            //
            Meeting meeting =  meetingServiceImp.findBy(Restrictions.eq(Meeting.ID,meetingId),RePa.p__(Meeting._CURRENT_ROOM_MAP,Room_Map._MAP));

            //

            Map map = findBy(Restrictions.eq(Map.ID,meeting.getCurrentRoomMap().getMap().getId()),Map._LAYERS);
            if (map != null)
            {
                Layer layer = layerServiceImp.findBy(Restrictions.and(Restrictions.eq(Layer._MAP,map),Restrictions.eq(Layer.ID,layerId)));
                if (layer != null)
                {
                    String tileDirectory = generateTileAddress(tileX,tileY,map,layer);
                    /*String text = new String(Files.readAllBytes(Paths.get(tileDirectory)));
                    DownloadResponse downloadResponse = new DownloadResponse();
                    downloadResponse.setSize(layerServiceImp.getFileSize(tileDirectory));
                    downloadResponse.setContent(text);
                    rpcResponse = generateDownloadFileResponse(downloadResponse,TtErrors.NoError);*/
                    File file =  new File(tileDirectory);

                    return file;




                }
                else if (layer == null)
                {

                }
            }
            else if (map == null)
            {

            }



        }
        catch (Exception e)
        {
            return null;


        }
        return null;
    }

    }
