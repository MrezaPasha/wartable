<%-- 
    Document   : right-menu
    Created on : Apr 15, 2015, 12:02:38 PM
    Author     : masoud
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<input id="amId" type="hidden" value="${userMenu}">

<div class="navbar-content">
    <div class="main-navigation navbar-collapse collapse">
        <div>
            <div class="navigation-toggler">
                <i class="clip-chevron-left"></i>
                <i class="clip-chevron-right"></i>
            </div>
        </div>
        <ul class="main-navigation-menu <c:if test="${!sUser.isAdmin}">right-menu</c:if> ">
            <jsp:include page="right-menu/dashboard.jsp"/>
            <jsp:include page="right-menu/web.jsp"/>
            <jsp:include page="right-menu/service.jsp"/>
            <c:if test="${sUser.isLogManager}">
                <jsp:include page="right-menu/log-manager.jsp"/>
            </c:if>
            <c:if test="${sUser.isSuperAdmin}">
                <jsp:include page="right-menu/z-control.jsp"/>
            </c:if>
        </ul>
    </div>
</div>
