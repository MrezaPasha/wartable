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

<div class="container">
    <c:choose>
        <c:when test="${userGroup==null}">
            <div class="row">
                <div class="page-header-2 ">
                    <h3 class="cred-header">
                        <span class="cred-title"><spring:message code="user.levelGroup.list"/></span>
                    </h3>
                </div>
                <div class="col-sm-2">
                    <h3 class="cred-select-lable"><spring:message code="user.selectuserGroups"/>:</h3>
                </div>
                <div class="form-group col-sm-6">
                    <form:select path="selectedUserGroup" id="selectedItemId" cssClass="form-control search-select">
                        <option value=""></option>
                        <form:options items="${uglist}" itemValue="id" itemLabel="title"/>
                    </form:select>
                </div>
                <div class="margin-rl-15">
                    <button id="selectButtonId" class="btn btn-p-act btn-p-choice btn-animated btn-animated-right">
                        <spring:message code="all.choice"/>
                        <i class=" clip-download-3"></i>
                    </button>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="page-header">
                <h3 class="cred-header">
                    <span class="cred-title"><spring:message code="user.levelGroup.list"/></span>
                    <span class="cred-name"><i class="clip-tree"></i>&nbsp;<spring:message code="title.category"/>:[${userGroup.title}] </span>
                </h3>
            </div>
            <div class="well well-act">
                <ul class="disabled-li margin-bottom xs-center-ul">
                    <li class="tooltips" data-original-title="<spring:message code="users.userGroup"/>" data-placement="bottom">
                        <a href="${_url}/desk" class="btn btn-default btn-block btn-act">
                            <i class="clip-tree"></i>
                            <span class="title"></span>
                        </a>
                    </li>
                    <li class="tooltips" data-original-title=" <spring:message code="all.reload"/>" data-placement="bottom">
                        <a href="${_url}/list/${userGroup.id}" class="btn btn-default btn-block btn-act">
                            <i class=" clip-refresh light"></i>
                            <span class="title"></span>
                        </a>
                    </li>
                </ul>
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
                                <th><spring:message code="all.tasks"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${mlist}" var="myvar">
                                <tr>
                                    <td>${myvar.id}</td>
                                    <td><spring:message code="${myvar.messageCode}"/></td>
                                    <td>
                                        <c:forEach items="${myvar.tasks}" var="tsk">
                                                <span class="task-item">
                                                    <spring:message code="${tsk.messageCode}"/>
                                                </span>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <a href="${_url}/access/${userGroup.id}/${myvar.id}" class="btn btn-sm btn-l-edit tooltips " data-placement="top" data-original-title="<spring:message code="user.access.submit"/> ">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/DT_bootstrap.js"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/jquery-inputlimiter/jquery.inputlimiter.1.3.1.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/autosize/jquery.autosize.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initTable();
        CustomJs.initSelect2();
        ;
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
