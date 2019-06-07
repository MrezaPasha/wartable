<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="T.p.service.meeting.vector.details"/></span>
    </h3>
    <div class="well well-act">
        <a href="${_url}/vector/list/${meetingItem.meeting.id}" class="btn btn-p-back btn-sm btn-default btn-animated btn-animated-right float-left">
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
                            <td class="border-top-0">${meetingItem.id}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="model.createDateTime"/></th>
                            <td class="border-top-0">${meetingItem.createDateTime}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meetingItem.meeting"/></th>
                            <td class="border-top-0">${meetingItem.meeting.name}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meeting.room"/></th>
                            <td class="border-top-0">${meetingItem.meeting.room.name}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="vector.name"/></th>
                            <td class="border-top-0">${meetingItem.vector.name}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="vector.fileName"/></th>
                            <td class="border-top-0">${meetingItem.vector.fileName}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="vector.size"/></th>
                            <td class="border-top-0">${meetingItem.vector.size}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="vector.vectorType"/></th>
                            <td class="border-top-0">${meetingItem.vector.vectorType}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="vector.color"/></th>
                            <td class="border-top-0">${meetingItem.vector.color}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="vector.description"/></th>
                            <td class="border-top-0">${meetingItem.vector.description}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="vector.opacity"/></th>
                            <td class="border-top-0">${meetingItem.vector.opacity}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="vector.bounds"/></th>
                            <td class="border-top-0">${meetingItem.vector.bounds}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="vector.uploaderUser"/></th>
                            <td class="border-top-0">${meetingItem.vector.uploaderUser.fullName}</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
