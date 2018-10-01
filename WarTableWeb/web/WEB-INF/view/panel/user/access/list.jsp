<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : assign
    Created on : Aug 17, 2016, 3:39:17 PM
    Author     : slt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container print">
    <c:choose>
        <c:when test="${user==null}">
            <div class="row">
                <div class="page-header-2 ">
                    <h3 class="cred-header">
                        <span class="cred-title" ><spring:message  code="user.level.list"/></span>
                    </h3>
                </div>
                <div class="col-sm-2">
                    <h3 class="cred-select-lable"><spring:message code="user.select"/>:</h3>
                </div>
                <div class="form-group col-sm-6">
                    <form:select path="selectedUser" id="selectedItemId" cssClass="form-control search-select" >
                        <option value="" ></option>
                        <form:options items="${ulist}" itemValue="id" itemLabel="fullNameIdMobile" />
                    </form:select>
                </div>
                <div class="margin-rl-15">
                    <button id="selectButtonId"  class="btn btn-p-act btn-p-choice btn-animated btn-animated-right">
                        <i class="clip-download-3"></i>
                        <spring:message  code="all.choice"/>
                    </button>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="page-header">
                <h3 class="cred-header">
                    <span class="cred-title" ><spring:message  code="user.level.list"/></span>
                    <span class="cred-name"><i class="clip-user"></i>&nbsp;<spring:message code="user.name"/>: ${user.fullName}</span>
                </h3>
            </div>
            <div class="well well-act" >
                <ul class="disabled-li xs-center-ul-2 margin-bottom">
                    <li class="tooltips" data-original-title="<spring:message  code="all.choice"/>" data-placement="bottom">
                        <a  href="${cp}/panel/user/access/list/0" class="btn btn-default btn-block btn-act">
                            <i class="clip-upload-3 light"></i>
                            <span class="title"></span>
                        </a>
                    </li>
                    <li class="tooltips" data-original-title=" <spring:message code="all.reload"/>" data-placement="bottom">
                        <a href="${cp}/panel/user/access/list/${user.id}" class="btn btn-default btn-block btn-act">
                            <i class=" clip-refresh light"></i>
                            <span class="title"></span>
                        </a>
                    </li>
                    <li class="tooltips" data-original-title="<spring:message  code="users.list"/>" data-placement="bottom">
                        <a  href="${cp}/panel/user/list" class="btn btn-default btn-block btn-act">
                            <i class="clip-list light"></i>
                            <span class="title"></span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading"  id="titleHandlerPlace">
                    <i class="fa fa-external-link-square"></i>
                    <div class="panel-tools">
                        <button data-toggle="dropdown" class="btn btn-xs btn-link dropdown-toggle export panel-excel" title="صدور">
                            <i class="fa fa-external-link"></i>
                        </button>
                        <ul class="dropdown-menu dropdown-light pull-right export">
                            <li>
                                <a href="${cp}/Excel">
                                    <i class="clip-file-excel"></i> اکسل
                                </a>
                            </li>

                        </ul>
                        <a class="btn btn-xs btn-link panel-print"  href="javascript:window.print()" class="tooltips" title="پرینت">
                            <i class="fa fa-print light"></i>
                        </a>
                        <a class="btn btn-xs btn-link panel-collapse collapses" href="javascript:void(0)"></a>
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
                                <th class="th-hidden"></th>
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
                                    <td class="btn-tools">
                                        <a href="${cp}/panel/user/access/${user.id}/${myvar.id}" class="btn btn-sm btn-l-edit tooltips " data-placement="top" data-original-title="<spring:message code="all.edit"/> ">
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

<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/jquery.dataTables.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/DT_bootstrap.js?r=${rl2}"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/jquery-inputlimiter/jquery.inputlimiter.1.3.1.min.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/autosize/jquery.autosize.min.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js?r=${rl2}"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initTable();
        CustomJs.initSelect2();;
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
