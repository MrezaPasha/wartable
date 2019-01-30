<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="T.p.service.room.user.details"/></span>
        <span class="cred-name">
            <spring:message code="room_ServiceUser.serviceUser"/>: [${room_ServiceUser.serviceUserFullName}]&nbsp;&nbsp;&nbsp;
            <spring:message code="room_ServiceUser.room"/>: [${room_ServiceUser.roomTitle}]
        </span>

    </h3>
    <div class="well well-act">
        <a href="${_url}/user/list/${room_ServiceUser.room.id}" class="btn btn-p-back btn-sm btn-default btn-animated btn-animated-right float-left">
            <spring:message code="all.back"/>
            <i class="fa fa-reply"></i>
        </a>
    </div>
    <div class="row margin-top-50">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-hover iransans-standard" id="sample-table-1" style="font-size: 0.8em">
                        <tbody>
                        <tr class="details-separator">
                            <th class="border-top-0"><spring:message code="model.id"/></th>
                            <td class="border-top-0">${room_ServiceUser.id}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="room_ServiceUser.roomTitle"/></th>
                            <td>${room_ServiceUser.roomTitle}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="room_ServiceUser.joinDateTime"/></th>
                            <td>${room_ServiceUser.joinDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="room_ServiceUser.acceptPrivateChat"/></th>
                            <td>${room_ServiceUser.acceptPrivateChat}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="room_ServiceUser.acceptPrivateTalk"/></th>
                            <td>${room_ServiceUser.acceptPrivateTalk}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="room_ServiceUser.temporaryUserRoleFlag"/></th>
                            <td>${room_ServiceUser.temporaryUserRoleFlag}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="room_ServiceUser.permanentUserRoleFlag"/></th>
                            <td>${room_ServiceUser.permanentUserRoleFlag}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="room_ServiceUser.presence"/></th>
                            <td>${room_ServiceUser.presence}</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
