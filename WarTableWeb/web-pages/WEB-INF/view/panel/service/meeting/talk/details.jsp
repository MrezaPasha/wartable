<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="T.p.service.meeting.talk.details"/></span>
    </h3>
    <div class="well well-act">
        <a href="${_url}/talk/list/${privateTalk.meeting.id}" class="btn btn-p-back btn-sm btn-default btn-animated btn-animated-right float-left">
            <spring:message code="all.back"/>
            <i class="fa fa-reply"></i>
        </a>
    </div>
    <div class="row margin-top-50">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div>
                        <audio controls="controls" class="sound-control">
                            <source src="${_url}/talk/sound/${privateTalk.id}" type="audio/mpeg"/>
                            مرورگر پخش این صوت را پشتیبانی نمی کند!
                        </audio>
                        <a class="talk-sound-download" href="${_url}/talk/sound/${privateTalk.id}"><spring:message code="all.download.sound"/></a>
                    </div>
                    <table class="table table-hover" id="sample-table-1" style="font-size: 0.8em">
                        <tbody>
                        <tr>
                            <th class="border-top-0"><spring:message code="model.id"/></th>
                            <td class="border-top-0">${privateTalk.id}</td>
                        <tr>
                        <tr>
                            <th><spring:message code="model.createDateTime"/></th>
                            <td>${privateTalk.createDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="privateTalk.startDateTime"/></th>
                            <td>${privateTalk.startDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="privateTalk.endDateTime"/></th>
                            <td>${privateTalk.endDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="privateTalk.status"/></th>
                            <td>${privateTalk.status}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="privateTalk.fileName"/></th>
                            <td>${privateTalk.fileName}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="privateTalk.size"/></th>
                            <td>${privateTalk.size}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="privateTalk.requestUser"/></th>
                            <td>${privateTalk.requestUser.fullName}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="privateTalk.serviceUsers"/></th>
                            <td>
                                <c:forEach items="${privateTalk.serviceUsers}" var="myvar">
                                    <span class="chat-user-item">${myvar.fullName}</span>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="privateTalk.joinedServiceUsers"/></th>
                            <td>
                                <c:forEach items="${privateTalk.joinedServiceUsers}" var="myvar">
                                    <span class="chat-user-item">${myvar.fullName}</span>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
