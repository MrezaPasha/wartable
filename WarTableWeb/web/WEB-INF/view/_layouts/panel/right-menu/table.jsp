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
        <span class="title"><spring:message code="rightmenu.table"/></span><i class="icon-arrow"></i>
        <span class="selected"></span>
    </a>
    <ul class="sub-menu">
        <li data-menu-id="">
            <a href="${cp}/panel/">
                <i class="clip-list light"></i>
                <span class="title"><spring:message code="rightmenu.web.user"/></span>
            </a>
        </li>
        <li data-menu-id="">
            <a href="${cp}/panel/">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.room"/></span>
            </a>
        </li>
        <li data-menu-id="">
            <a href="${cp}/panel/">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.map"/></span>
            </a>
        </li>
        <li data-menu-id="">
            <a href="${cp}/panel/">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.region"/></span>
            </a>
        </li>
        <li data-menu-id="">
            <a href="${cp}/panel/">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.layer"/></span>
            </a>
        </li>
        <li data-menu-id="">
            <a href="${cp}/panel/">
                <i class=" clip-tree light"></i>
                <span class="title"><spring:message code="rightmenu.web.objects"/></span>
            </a>
        </li>
    </ul>
</li>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
