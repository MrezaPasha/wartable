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
        <span class="title"><spring:message code="rightmenu.web"/></span><i class="icon-arrow"></i>
        <span class="selected"></span>
    </a>
    <ul class="sub-menu">
        <li>
            <a href="javascript:void(0)">
                <i class="clip-note"></i>
                <span class="title"><spring:message code="rightmenu.note"/></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>


            <ul class="sub-menu">
                <li id="noteMenu" data-menu-id="p_note_create">
                    <a href="${cp}/panel/note/create">
                        <i class=" clip-add light"></i>
                        <span class="title"><spring:message code="rightmenu.create"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_note_list|p_note_edit|p_note_details|">
                    <a href="${cp}/panel/note/list">
                        <i class=" clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.list"/></span>
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="javascript:void(0)">
                <i class="clip-users"></i>
                <span class="title"><spring:message code="rightmenu.users"/></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li data-menu-id="p_user_create">
                    <a href="${cp}/panel/user/create">
                        <i class="clip-add light"></i>
                        <span class="title"><spring:message code="rightmenu.create"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_user_list|p_user_edit|p_user_changeUserPass|p_user_access_list|p_user_access_assign|">
                    <a href="${cp}/panel/user/list">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.list"/></span>
                    </a>
                </li>
                <li data-menu-id="p_user_listInactive">
                    <a href="${cp}/panel/user/list/inactive">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.user.list.inactive"/></span>
                    </a>
                </li>
                <li data-menu-id="p_user_listOnline">
                    <a href="${cp}/panel/user/list/online">
                        <i class="clip-list light"></i>
                        <span class="title"><spring:message code="rightmenu.user.list.online"/></span>
                    </a>
                </li>
                <li data-menu-id="|p_user_group_desk|p_user_group_access|p_user_group_access_assign|p_user_group_assign|p_user_group_access_list|p_user_group_access_assign|">
                    <a href="${cp}/panel/user/group/desk">
                        <i class=" clip-tree light"></i>
                        <span class="title"><spring:message code="rightmenu.userGroups"/></span>
                    </a>
                </li>
            </ul>
        </li>
        <li data-menu-id="|p_e_lix|p_e_list|p_e_show|">
            <a href="${cp}/panel/e/list">
                <i class="clip-IcoMoon light"></i>
                <span class="title"><spring:message code="rightmenu.web.error"/></span>
            </a>
        </li>
        <li data-menu-id="p_propertor_web">
            <a href="${cp}/panel/propertor/web">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.config"/></span>
            </a>
        </li>
        <li data-menu-id="|p_sys_module_listConfirm|p_sys_task_confirm|">
            <a href="${cp}/panel/module/list/confirm">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.sys.tow.level.confirm"/></span>
            </a>
        </li>
        <li data-menu-id="|p_sys_log_list|p_sys_log_details|">
            <a href="${cp}/panel/log/list">
                <i class=" clip-loop light"></i>
                <span class="title"><spring:message code="rightmenu.web.logging"/></span>
            </a>
        </li>
        <li data-menu-id="p_sys_log_signin_list">
            <a href="${cp}/panel/log/signin/list">
                <i class=" clip-data light"></i>
                <span class="title"><spring:message code="rightmenu.web.logging.signin"/></span>
            </a>
        </li>
        <li>
            <a href="javascript:void(0)">
                <i class="clip-users"></i>
                <span class="title"><spring:message code="rightmenu.web.backup"/></span><i class="icon-arrow"></i>
                <span class="selected"></span>
            </a>
            <ul class="sub-menu">
                <li data-menu-id="|p_backup_list|p_backup_upload|">
                    <a href="${cp}/panel/backup/list">
                        <i class=" clip-tree light"></i>
                        <span class="title"><spring:message code="rightmenu.web.backup.list"/></span>
                    </a>
                </li>
                <li data-menu-id="p_propertor_backup">
                    <a href="${cp}/panel/propertor/backup">
                        <i class=" clip-tree light"></i>
                        <span class="title"><spring:message code="rightmenu.backup.config"/></span>
                    </a>
                </li>
            </ul>
        </li>
        <li data-menu-id="|p_uiBag_set|">
            <a href="${cp}/panel/ui/set">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.ui.set"/></span>
            </a>
        </li>
    </ul>
</li>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
