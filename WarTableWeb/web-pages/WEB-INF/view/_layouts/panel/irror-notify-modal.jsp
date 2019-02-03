<%-- 
    Document   : modal-select-pricing
    Created on : Sep 4, 2017, 12:31:56 PM
    Author     : dev3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal-dialog modal-irror-notify">
    <div class="modal-content">
        <div class="modal-header">
            <spring:message code="irror.notify.modal.title"/>
        </div>
        <div class="modal-body">
            <spring:message code="irror.notify.modal.text"/>
            <ul>
                <c:forEach items="${inlist}" var="myvar">
                    <li>
                            ${myvar.irror.createDateTimeSM} | <spring:message code="irror.id" /> : ${myvar.irror.id} | ${myvar.irror.level.title} | ${myvar.user.fullName}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
