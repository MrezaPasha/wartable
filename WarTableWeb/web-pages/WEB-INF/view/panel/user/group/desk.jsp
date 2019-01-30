<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : edit
    Created on : Mar 10, 2016, 9:34:20 AM
    Author     : dev1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<link rel="stylesheet" href="${cp}/resources-p/plugins/dataTables/media/css/DT_bootstrap.css"/>

<div class="container print">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title"><spring:message code="userGroup"/></span>
        </h3>
    </div>
    <div class="input-group well col-xs-12  padding-scope">
        <button form="form" class="btn btn-p-create btn-p-act btn-animated btn-animated-right" type="submit">
            <spring:message code="all.register"/>
            <i class="clip-plus-circle"></i>
        </button>
        <c:if test="${userGroup.id!=0}">
            <a class="btn btn-p-cancel btn-p-act btn-animated btn-animated-right" href="${_url}/desk">
                <spring:message code="all.cancel"/>
                <i class="clip-close-4"></i>
            </a>
        </c:if>
    </div>
    <div class="tabbable">
        <div class=" col-xs-12 padding-scope data-form">
            <form id="form" accept-charset="UTF-8" action="${action}" method="POST">
                <div class="row">
                    <form:hidden path="userGroup.id"/>
                    <div class="col-sm-9">
                        <div class="form-group col-sm-6 margin-top-3">
                            <c:set var="varName" value="userGroup.title"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                            </form:label><form:errors cssClass="form-validation-error" path="${varName}"/>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-100"/>
                        </div>
                        <c:set var="varName" value="userGroup.parent"/>
                        <div class="form-group col-sm-6">
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                            </form:label>
                            <form:select path="${varName}.id" id="form-field-select-3" cssClass="form-control search-select">
                                <option value=""></option>
                                <form:options items="${uglist}" itemValue="id" itemLabel="title"/>
                            </form:select>
                        </div>
                        <form:errors cssClass="form-validation-error" path="${varName}"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-external-link-square"></i>
            <spring:message code="users.list"/>
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
                <a class="btn btn-xs btn-link panel-print" href="javascript:window.print()" class="tooltips" title="پرینت">
                    <i class="fa fa-print light"></i>
                </a>
                <a class="btn btn-xs btn-link panel-collapse collapses" href="javascript:void(0)"></a>
            </div>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table dataTable table-striped table-hover" id="table_list_x">
                    <thead>
                    <tr>
                        <th><spring:message code="model.id"/></th>
                        <th><spring:message code="userGroup.title"/></th>
                        <th><spring:message code="userGroup.parent"/></th>
                        <th class="th-hidden"></th>
                    </tr>
                    </thead>
                    <tbody class="text-small">
                    <c:if test="${!empty uglist}">
                        <c:forEach items="${uglist}" var="myvar">
                            <tr>
                                <td>${myvar.id}</td>
                                <td>${myvar.title}</td>
                                <td>${myvar.parent.title}</td>
                                <td class="right btn-tools" style="width: 168px;">
                                    <div class="visible-md visible-lg hidden-sm hidden-xs operation-icon">
                                        <a href="${_url}/assign/${myvar.id}" class="btn btn-sm btn-l-assign tooltips" data-placement="top" data-original-title="<spring:message code="users.userGroup.assign"/>">
                                            <i class="clip-stack-empty"></i>
                                        </a>
                                        <a href="${_url}/desk?ix=${myvar.id}" class="btn btn-sm btn-l-edit tooltips" data-placement="top" data-original-title="<spring:message code="all.edit"/> ">
                                            <i class="fa fa-edit  pad-2"></i>
                                        </a>

                                        <a href="${_url}/access/list/${myvar.id}" class="btn btn-sm btn-l-level tooltips" data-placement="top" data-original-title="<spring:message code="users.userGroup.level"/> ">
                                            <i class="fa fa-shield  pad-2"></i>
                                        </a>

                                        <a class="btn btn-sm btn-l-trash tooltips delete-row" data-placement="top" data-original-title="<spring:message code="all.delete"/>">
                                            <i class="fa fa-trash-o  pad-2"></i>
                                        </a>
                                    </div>
                                    <div class="visible-xs visible-sm hidden-md hidden-lg r-button">
                                        <div class="btn-group">
                                            <a class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown" href="javascript.void(0)">
                                                <i class="fa fa-cog"></i> <span class="caret"></span>
                                            </a>

                                            <ul role="menu" class="dropdown-menu pull-right " style="z-index: 1000;">
                                                <li role="presentation">
                                                    <a role="menuitem" tabindex="-1" href="${cp}/panel/user/assign/${myvar.id}">
                                                        <i class="clip-stack-empty"></i>
                                                        <spring:message code="users.userGroup.assign"/>
                                                    </a>
                                                </li>
                                                <li role="presentation">
                                                    <a role="menuitem" tabindex="-1" href="${_url}/desk?ix=${myvar.id}">
                                                        <i class="fa fa-edit  pad-2"></i>
                                                        <spring:message code="all.edit"/>
                                                    </a>
                                                </li>
                                                <li role="presentation">
                                                    <a role="menuitem" tabindex="-1" href="${_url}/access/list/${myvar.id}">
                                                        <i class="fa fa-shield  pad-2"></i>
                                                        <spring:message code="users.userGroup.level"/>
                                                    </a>
                                                </li>
                                                <li role="presentation">
                                                    <a role="menuitem" tabindex="-1" href="javascript.void(0)" class="delete-row">
                                                        <i class="fa fa-trash-o  pad-2"></i>
                                                        <spring:message code="all.delete"/>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${cp}/resources-p/plugins/jquery-inputlimiter/jquery.inputlimiter.1.3.1.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/autosize/jquery.autosize.min.js"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/DT_bootstrap.js"></script>

<script type="text/javascript" src="${cp}/resources/js/form-validation.js"></script>
<script type="text/javascript" src="${cp}/resources/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/bootbox/bootbox.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initTable();
        CustomJs.initSelect2();
        ;
        FormValidator.init();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
