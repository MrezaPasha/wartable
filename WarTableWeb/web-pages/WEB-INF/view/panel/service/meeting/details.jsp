<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="T.p.service.meeting.details"/></span>
    </h3>
    <div class="well well-act">
        <a href="${_url}/list" class="btn btn-p-back btn-sm btn-default btn-animated btn-animated-right float-left">
            <spring:message code="all.back"/>
            <i class="fa fa-reply"></i>
        </a>
    </div>
    <div class="row margin-top-50">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-hover" id="sample-table-1" style="font-size: 0.8em">
                        <tbody>
                        <tr class="details-separator">
                            <th class="border-top-0"><spring:message code="model.id"/></th>
                            <td class="border-top-0">${meeting.id}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="model.createDateTime"/></th>
                            <td class="border-top-0">${meeting.createDateTime}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meeting.name"/></th>
                            <td class="border-top-0">${meeting.name}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meeting.boardText"/></th>
                            <td class="border-top-0">${meeting.boardText}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meeting.goal"/></th>
                            <td class="border-top-0">${meeting.goal}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meeting.roomTitle"/></th>
                            <td class="border-top-0">${meeting.roomTitle}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meeting.result"/></th>
                            <td class="border-top-0">${meeting.result}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="room.room_serviceUsers"/></th>
                            <td class="border-top-0">
                                <c:forEach items="${meeting.roomServiceUsers}" var="myvar">
                                    <span class="service-user-item"> ${myvar.serviceUser.fullName}</span>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <section class="jumbotron text-center margin-clear" style=" background-color: white;">
        <div style="height: 320px; margin-top: 20px; font-size: 13px;">
            <div class="err-p-500">${meeting.description}</div>
        </div>
    </section>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
