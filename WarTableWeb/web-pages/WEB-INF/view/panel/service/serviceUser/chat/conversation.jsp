<%--
    Document   : list
    Created on : Jun 24, 2017, 8:25:21 AM
    Author     : dev3
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container c-list-container">
    <div class="page-header">
        <div class="cred-header">
            <h3 class="cred-title"><spring:message code="T.p.service.serviceUser.chat.conversation"/></h3>
            <span class="cred-name">[${serviceUser.name}]</span>
            <a href="${_url}/list" class="btn btn-p-back btn-animated btn-animated-right">
                <i class="fa fa-reply"></i>
                <span class="title"><spring:message code="T.p.service.serviceUser.list"/></span>
            </a>
        </div>

    </div>
    <div class="row">
        <div class="timeline">
            <div class="spine"></div>
            <c:choose>
                <c:when test="${not empty clist}">
                    <ul class="columns">
                        <c:forEach items="${clist}" var="myvar">
                            <li>
                                <a href="${_url}/chat/details/${serviceUser.id}/${myvar.id}">
                                    <div class="timeline_element <c:if test="${myvar.sender.id== serviceUser.id}">your-sender</c:if> ">
                                        <div class="timeline_title">
                                            <span class="timeline_label"><spring:message code="textChat.senderFullName"/>: ${myvar.senderFullName}</span>
                                            <span class="timeline_date deliver"><spring:message code="textChat.deliverDateTime"/>: ${myvar.deliverDateTime}</span>
                                            <span class="timeline_date send"><spring:message code="textChat.sendDateTime"/>: ${myvar.sendDateTime}</span>
                                        </div>
                                        <div class="content">
                                                ${myvar.messageBody}
                                        </div>
                                        <div class="readmore">
                                            <span class="timeline-receiver-title"><spring:message code="textChat.privateChatReceiver"/></span>
                                            <span class="timeline-receiver">${myvar.privateChatReceiver.fullName}</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <div class="date_separator">
                        <span><spring:message code="chat.list.empty"/></span>
                    </div>
                </c:otherwise>
            </c:choose>


        </div>

    </div>
</div>
