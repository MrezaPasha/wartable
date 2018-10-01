<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : assign
    Created on : Aug 17, 2016, 3:39:17 PM
    Author     : slt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<div class="container module-list">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title"><spring:message code="module.list"/></span>
        </h3>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading" id="titleHandlerPlace">
            <i class="fa fa-external-link-square"></i>
            <div class="panel-tools">
                <a class="btn btn-xs btn-link panel-collapse collapses" href="javascript:void(0)"> </a>
            </div>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table dataTable table-striped table-hover" id="table_list_x" data-sort-index="0" data-sort-type="asc" data-display-len="-1">
                    <thead>
                    <tr>
                        <th><spring:message code="model.id"/></th>
                        <th><spring:message code="all.module"/></th>
                        <th><spring:message code="module.tasks"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${mlist}" var="myvar">
                        <tr>
                            <td>${myvar.id}</td>
                            <td>${myvar.title}</td>
                            <td>
                                <c:forEach items="${myvar.tasks}" var="tsk">
                                    <c:if test="${tsk.isSuperAdmin==false}">
                                        <a href="${cp}/panel/log/remote/task/config/${tsk.id}" class="task-item-log <c:if test="${tsk.isOnlineLogging}">online-logging</c:if>">
                                            <span title="آدرس: ${tsk.url}" class="task-title">${tsk.fullTitle}</span>
                                            <span title="<spring:message code="task.actionType" />" class="task-action">${tsk.actionType}</span>
                                            <div>
                                                <ul>
                                                    <li title="<spring:message code="task.importanceLevel" />" class="task-importance">${tsk.importanceLevel.title}</li>
                                                    <li title="<spring:message code="task.sensitivity" />" class="task-sensitivity">${tsk.sensitivity.title}</li>
                                                </ul>
                                                <c:if test="${tsk.isOnlineLogging}">
                                                    <ul>
                                                        <li title="<spring:message code="task.onlineLoggingStrategy" />" class="task-strategy">
                                                                ${tsk.onlineLoggingStrategy.title}
                                                        </li>
                                                        <c:if test="${tsk.onlineLoggingStrategy=='WithDelay'}">
                                                            <li title="<spring:message code="task.onlineLoggingDelay" />" class="task-delay">
                                                                    ${tsk.onlineLoggingDelayTitle}
                                                            </li>
                                                        </c:if>
                                                        <c:if test="${tsk.onlineLoggingStrategy=='Scheduling'}">
                                                            <li title="<spring:message code="task.onlineSchedulingTime" />" class="task-scheduling">
                                                                    ${tsk.onlineSchedulingTimeTitle}
                                                            </li>
                                                        </c:if>
                                                    </ul>
                                                </c:if>
                                            </div>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                    <%--<a href="${cp}/panel/log/task/list/${myvar.id}" class="btn btn-sm btn-l-level tooltips " data-placement="top" data-original-title="<spring:message code="task.list" />">--%>
                                    <%--<i class="fa fa-tasks"></i>--%>
                                    <%--</a>--%>
                                    <%--<a href="${cp}/panel/log/task/online/logging/${myvar.id}" class="btn btn-sm btn-l-assign tooltips " data-placement="top" data-original-title="<spring:message code="log.task.online.logging" />">--%>
                                    <%--<i class="fa fa-bullseye"></i>--%>
                                    <%--</a>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/jquery.dataTables.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/DT_bootstrap.js?r=${rl2}"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/jquery-inputlimiter/jquery.inputlimiter.1.3.1.min.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/autosize/jquery.autosize.min.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js?r=${rl2}"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initTable();
        CustomJs.initSelect2();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
