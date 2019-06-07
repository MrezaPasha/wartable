<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : home
    Created on : Jul 18, 2015, 11:41:03 PM
    Author     : masoud
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<li>
    <a href="javascript:void(0)">
        <i class="clip-users"></i>
        <span class="title"><spring:message code="rightmenu.service"/></span><i class="icon-arrow"></i>
        <span class="selected"></span>
    </a>
    <ul class="sub-menu">
        <li>
            <a href="javascript:void(0)">
                <i class="clip-users"></i>
                <span class="title"><spring:message code="rightmenu.users"/></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li data-menu-id="p_service_serviceUser_create">
                    <a href="${cp}/panel/service/user/create">
                        <i class="clip-add light"></i>
                        <span class="title"><spring:message code="rightmenu.create"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_service_serviceUser_list|p_service_serviceUser_edit|p_service_serviceUser_changeUserPass|">
                    <a href="${cp}/panel/service/user/list">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.list"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_service_serviceUser_listOnline|p_service_serviceUser_room|">
                    <a href="${cp}/panel/service/user/list/online">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.user.list.online"/></span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="clip-users"></i>
                        <span class="title"><spring:message code="rightmenu.grade"/></span><i class="icon-arrow"></i>
                        <span class="selected"></span>
                    </a>
                    <ul class="sub-menu">
                        <li data-menu-id="p_service_grade_create">
                            <a href="${cp}/panel/service/grade/create">
                                <i class="clip-add light"></i>
                                <span class="title"><spring:message code="rightmenu.create"/></span>
                            </a>
                        </li>
                        <li data-menu-id="|p_service_grade_list|p_service_grade_edit|">
                            <a href="${cp}/panel/service/grade/list">
                                <i class="clip-list light"></i>
                                <span class="title"><spring:message code="rightmenu.list"/></span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="clip-users"></i>
                        <span class="title"><spring:message code="rightmenu.organPosition"/></span><i class="icon-arrow"></i>
                        <span class="selected"></span>
                    </a>
                    <ul class="sub-menu">
                        <li data-menu-id="p_service_orgPosition_create">
                            <a href="${cp}/panel/service/orgPosition/create">
                                <i class="clip-add light"></i>
                                <span class="title"><spring:message code="rightmenu.create"/></span>
                            </a>
                        </li>
                        <li data-menu-id="|p_service_orgPosition_list|p_service_orgPosition_edit|">
                            <a href="${cp}/panel/service/orgPosition/list">
                                <i class="clip-list light"></i>
                                <span class="title"><spring:message code="rightmenu.list"/></span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="clip-cube-2"></i>
                        <span class="title"><spring:message code="rightmenu.personModel"/></span><i class="icon-arrow"></i>
                        <span class="selected"></span>
                    </a>
                    <ul class="sub-menu">
                        <li data-menu-id="p_service_personModel_create">
                            <a href="${cp}/panel/service/personModel/create">
                                <i class="clip-add light"></i>
                                <span class="title"><spring:message code="rightmenu.create"/></span>
                            </a>
                        </li>
                        <li data-menu-id="|p_service_personModel_list|p_service_personModel_edit|">
                            <a href="${cp}/panel/service/personModel/list">
                                <i class="clip-list light"></i>
                                <span class="title"><spring:message code="rightmenu.list"/></span>
                            </a>
                        </li>
                    </ul>
                </li>

            </ul>
        </li>
        <li>
            <a href="javascript:void(0)">
                <i class="clip-stack-2"></i>
                <span class="title"><spring:message code="rightmenu.web.room"/></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li data-menu-id="p_service_room_create">
                    <a href="${cp}/panel/service/room/create">
                        <i class="clip-add light"></i>
                        <span class="title"><spring:message code="rightmenu.create"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_service_room_list|p_service_room_edit|p_service_room_user_list|p_service_room_user_details|p_service_room_user_create|p_service_room_user_edit|p_service_room_map_list|p_service_room_map_create|p_service_room_map_edit|p_service_room_chat_conversation|p_service_room_chat_details|p_service_serviceUser_chat_conversation|p_service_serviceUser_chat_details|">
                    <a href="${cp}/panel/service/room/list">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.list"/></span>
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="javascript:void(0)">
                <i class="clip-users"></i>
                <span class="title"><spring:message code="rightmenu.web.map"/></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li data-menu-id="p_service_map_create">
                    <a href="${cp}/panel/service/map/create">
                        <i class="clip-add light"></i>
                        <span class="title"><spring:message code="rightmenu.create"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_service_map_list|p_service_map_edit|p_service_map_room_create|p_service_map_room_edit|p_service_map_room_list|p_service_map_layer_list|">
                    <a href="${cp}/panel/service/map/list">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.list"/></span>
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="javascript:void(0)">
                <i class="clip-users"></i>
                <span class="title"><spring:message code="rightmenu.web.objects"/></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li data-menu-id="p_service_object_create">
                    <a href="${cp}/panel/service/object/create">
                        <i class="clip-add light"></i>
                        <span class="title"><spring:message code="rightmenu.create"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_service_object_list|p_service_object_edit|p_service_object_preview|">
                    <a href="${cp}/panel/service/object/list">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.list"/></span>
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="javascript:void(0)">
                <i class="clip-users"></i>
                <span class="title"><spring:message code="rightmenu.web.meeting"/></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li data-menu-id="|p_service_meeting_list|p_service_meeting_edit|p_service_meeting_details|p_service_meeting_talk_details|p_service_meeting_talk_list|p_service_meeting_setting_details|p_service_meeting_setting_list|p_service_meeting_vector_list|p_service_meeting_vector_details|p_service_meeting_vector_add|">
                    <a href="${cp}/panel/service/meeting/list">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.list"/></span>
                    </a>
                </li>
            </ul>
        </li>
        <li data-menu-id="">
            <a href="javascript:void(0)">
                <i class="clip-list light"></i>
                <span class="title"><spring:message code="rightmenu.service.error"/></span></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li data-menu-id="|p_service_baseErrors_list|p_service_baseErrors_edit|">
                    <a href="${cp}/panel/service/baseErrors/list">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.service.baseErrors"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_service_criticalLog_list|p_service_criticalLog_details|">
                    <a href="${cp}/panel/service/criticalLog/list">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.service.criticalLog"/></span>
                    </a>
                </li>
            </ul>
        </li>
        <li data-menu-id="">
            <a href="javascript:void(0)">
                <i class="clip-list light"></i>
                <span class="title"><spring:message code="rightmenu.service.startupNotice"/></span></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li data-menu-id="|p_service_startupNotice_create|">
                    <a href="${cp}/panel/service/notice/create">
                        <i class="clip-add light"></i>
                        <span class="title"><spring:message code="rightmenu.create"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_service_startupNotice_edit|p_service_startupNotice_list|p_service_startupNotice_details|p_service_startupNotice_item_list|p_service_startupNotice_item_details|">
                    <a href="${cp}/panel/service/notice/list">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.list"/></span>
                    </a>
                </li>
            </ul>
        </li>
        <li data-menu-id="|p_service_baseConfig_list|p_service_baseConfig_edit|">
            <a href="${cp}/panel/service/baseConfig/list">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.service.baseConfig"/></span>
            </a>
        </li>
        <li data-menu-id="|p_service_serviceLog_list|p_service_serviceLog_details|">
            <a href="${cp}/panel/service/serviceLog/list">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.service.logging"/></span>
            </a>
        </li>
    </ul>
</li>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
