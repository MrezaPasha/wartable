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
    </ul>
</li>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
