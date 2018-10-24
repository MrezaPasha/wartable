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
        <span class="title"><spring:message code="rightmenu.securityControl"/></span><i class="icon-arrow"></i>
        <span class="selected"></span>
    </a>
    <ul class="sub-menu">
        <li data-menu-id="|p_sys_log_remote_list|p_sys_log_remote_details|">
            <a href="${cp}/panel/log/remote/list">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.logging.list"/></span>
            </a>
        </li>
        <li data-menu-id="|p_sys_log_remote_moduleList|p_sys_log_remote_taskList|p_sys_log_remote_taskConfig|p_sys_log_remote_onlineLogging|">
            <a href="${cp}/panel/log/remote/module/list">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.logging.setting"/></span>
            </a>
        </li>
        <li data-menu-id="|p_propertor_log|">
            <a href="${cp}/panel/propertor/log">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.logging.config"/></span>
            </a>
        </li>
    </ul>
</li>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
