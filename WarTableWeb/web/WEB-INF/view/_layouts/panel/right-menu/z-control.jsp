<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<li>
    <a href="javascript:void(0)">
        <i class="fa fa-cogs"></i>
        <span class="title"><spring:message code="rightmenu.control"/> </span><i class="icon-arrow"></i>
        <span class="selected"></span>
    </a>
    <ul class="sub-menu">
        <li data-menu-id="|p_develop_index|p_develop_create_package|">
            <a href="${cp}/panel/develop">
                <i class="clip-wrench-2 light"></i>
                <span class="title"><spring:message code="rightmenu.control.develop"/></span>
            </a>
        </li>
        <li data-menu-id="p_setting_init">
            <a href="${cp}/panel/setting/init">
                <i class="clip-spinner-6 light"></i>
                <span class="title"><spring:message code="rightmenu.control.init"/></span>
            </a>
        </li>
        <li data-menu-id="p_propertor_boot">
            <a href="${cp}/panel/propertor/boot">
                <i class="clip-target light"></i>
                <span class="title"><spring:message code="rightmenu.control.propertor.boot"/></span>
            </a>
        </li>
        <li data-menu-id="p_propertor_control">
            <a href="${cp}/panel/propertor/control">
                <i class="clip-target light"></i>
                <span class="title"><spring:message code="rightmenu.control.propertor.control"/></span>
            </a>
        </li>
        <li data-menu-id="|p_sys_model_list|p_sys_model_field_details|p_sys_model_field_list|">
            <a href="${cp}/panel/model/list">
                <i class="clip-database light"></i>
                <span class="title"><spring:message code="rightmenu.control.database"/></span>
            </a>
        </li>
        <li data-menu-id="|p_sys_module_list|p_sys_task_active|">
            <a href="${cp}/panel/module/list">
                <i class="clip-windows8 light"></i>
                <span class="title"><spring:message code="rightmenu.control.module"/></span>
            </a>
        </li>
    </ul>
</li>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
