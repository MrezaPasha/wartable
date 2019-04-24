<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : list
    Created on : May 20, 2015, 8:08:15 PM
    Author     : masoud
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>

<link rel="stylesheet" href="${cp}/resources-p/plugins/dataTables/media/css/DT_bootstrap.css"/>

<div class="container c-list-container">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title">لیست حامل های ${user.fullName}</span>
        </h3>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-external-link-square"></i>
                    لیست حامل ها
                    <div class="panel-tools">
                        <a class="btn btn-xs btn-link panel-collapse collapses" href="javascript:void(0)"> </a>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table dataTable table-striped table-hover" id="table_list_x">
                            <thead>
                            <tr>
                                <th><spring:message code="model.id"/></th>
                                <th><spring:message code="userPorter.uuid"/></th>
                                <th><spring:message code="model.createDateTime"/></th>
                                <th><spring:message code="userPorter.confirmCode"/></th>
                                <th><spring:message code="userPorter.isConfirmed"/></th>
                                <th><spring:message code="userPorter.isActiveTwoStepConfirm"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${!empty uplist}">
                                    <c:forEach items="${uplist}" var="myvar">
                                        <tr>
                                            <td>${myvar.id}</td>
                                            <td>${myvar.uuid}</td>
                                            <td>${myvar.createDateTime}</td>
                                            <td>${myvar.confirmCode}</td>
                                            <td>${myvar.isConfirmedY.title}</td>
                                            <td>${myvar.isActiveTwoStepConfirmY.title}</td>
                                            <td class="center">
                                                <div class="visible-md visible-lg hidden-sm hidden-xs  r-button">
                                                    <a href="${_url}/details/${user.id}/${myvar.id}"
                                                       class="btn btn-sm btn-l-details" data-placement="top"
                                                       data-original-title="جزئیات">
                                                        <i class="clip-flickr"></i>
                                                    </a>
                                                    <a href="${_url}/unconfirm/${user.id}/${myvar.id}"
                                                       class="btn btn-sm btn-l-Ptrash" data-placement="top"
                                                       data-original-title="لغو تاییدیه">
                                                        <i class="clip-cancel-circle"></i>
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
        src="${cp}/resources-p/plugins/dataTables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/DT_bootstrap.js"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/bootbox/bootbox.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initTable();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
