<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="error.system"/></span>
    </h3>
    <div class="well well-act">
        <a href="${cp}/panel/log/remote/list" class="btn btn-p-back btn-sm btn-default btn-animated btn-animated-right float-left">
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
                            <td class="border-top-0">${log.id}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.serverId"/></th>
                            <td>${log.serverId}</td>
                        </tr>

                        <tr class="details-separator">
                            <th class="border-top-0"><spring:message code="model.createDateTime"/></th>
                            <td class="border-top-0">${log.createDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.dateTimeG"/></th>
                            <td>${log.dateTimeG}</td>
                        </tr>

                        <tr class="details-separator">
                            <th><spring:message code="log.taskTitle"/></th>
                            <td>${log.taskTitle}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.taskName"/></th>
                            <td>${log.taskName}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.isTaskTwoLevelConfirm"/></th>
                            <td>${log.isTaskTwoLevelConfirmY.title}</td>
                        </tr>

                        <tr class="details-separator">
                            <th><spring:message code="log.userId"/></th>
                            <td>${log.userId}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.userLevel"/></th>
                            <td>${log.userLevel.title}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.userGroupId"/></th>
                            <td>${log.userGroupId}</td>
                        </tr>

                        <tr class="details-separator">
                            <th><spring:message code="log.sessionId"/></th>
                            <td>${log.sessionId}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.computerSignature"/></th>
                            <td>${log.computerSignature}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.agentSignature"/></th>
                            <td>${log.agentSignature}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.porterUuid"/></th>
                            <td>${log.porterUuid}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.url"/></th>
                            <td>${log.url}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.portNumber"/></th>
                            <td>${log.portNumber}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.requestMethod"/></th>
                            <td>${log.requestMethod}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.httpCode"/></th>
                            <td>${log.httpCode}</td>
                        </tr>

                        <tr class="details-separator">
                            <th><spring:message code="log.actionType"/></th>
                            <td>${log.actionType.title}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.importanceLevel"/></th>
                            <td>${log.importanceLevel.title}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.sensitivity"/></th>
                            <td>${log.sensitivity.title}</td>
                        </tr>

                        <tr class="details-separator">
                            <th><spring:message code="log.onlineLoggingStrategy"/></th>
                            <td>${log.onlineLoggingStrategy.title}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.sendDateTime"/></th>
                            <td>${log.sendDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="log.sendStatus"/></th>
                            <td>${log.sendStatus.title}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <section class="jumbotron text-center margin-clear" style=" background-color: white;">
        <div style="height: 320px; margin-top: 20px; font-size: 13px;">
            <div class="err-p-500">${log.message}</div>
        </div>
    </section>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
