<%-- 
    Document   : front_header
    Created on : Jun 27, 2015, 2:33:39 PM
    Author     : masoud
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<input id="n-count-id" value="${fn:length(notice)}" type="hidden"/>
<header class="Header">
</header>
<c:if test="${not empty notice}">
    <div class="notice">
        <c:forEach items="${notice}" var="note" varStatus="loop">
            <div id="notice-id-${loop.index}" class="alert-notice alert ${note.cssClass}">
                <a href="javascript:void(0)" class="close close-2">&times;</a>
                    ${note.message}
            </div>
        </c:forEach>
    </div>
</c:if>
<div class="front-header">
    <c:choose>
        <c:when test="${sUser!=null}">
            <a href="${cp}/signout" class="btn btn-signout">خروج</a>
        </c:when>
        <c:otherwise>
            <a href="${cp}/signin" class="btn btn-signin">ورود به سامانه</a>
        </c:otherwise>
    </c:choose>
</div>
