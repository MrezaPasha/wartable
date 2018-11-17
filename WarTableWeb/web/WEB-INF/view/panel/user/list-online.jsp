<%--/*********************#- 1397/01/02 12:57:36 (Emsisoft)  -#*/--%>
<%--/*********************#- 1397/01/30 13:43:31 (Kheyriyeha)  -#*/--%>
<%--/*********************#- 1397/04/20 12:03:51 (Emsisoft_V4)  -#*/--%>
<%--/*********************#- 1397/04/30 20:55:39 (Sayed)  -#*/--%>
<%--/*********************#- 1397/05/11 13:22:44 (Meiras)  -#*/--%>
<%--/*********************#- 1397/06/01 11:17:00 (Baravard)  -#*/--%>
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

<div class="container" style="min-height: 300px;">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title"><spring:message code="user.list.online"/></span>
        </h3>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-external-link-square"></i>
                    <spring:message code="user.list"/>
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
                                <th><spring:message code="user.username"/></th>
                                <th><spring:message code="user.fullName"/></th>
                                <th><spring:message code="user.lastSigninDateTime"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${ulist}" var="myvar">
                                <tr>
                                    <td>${myvar.id}</td>
                                    <td>${myvar.username}</td>
                                    <td>${myvar.fullName}</td>
                                    <td>${myvar.lastSigninDateTime}</td>
                                    <td class="center">
                                        <div class="visible-md visible-lg hidden-sm hidden-xs  r-button">
                                            <a href="${cp}/panel/user/expire/${myvar.id}" class="btn btn-sm btn-danger tooltips" data-placement="top" data-original-title="<spring:message code="user.expire.session"/>">
                                                <i class="clip-close"></i>
                                            </a>
                                        </div>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/DT_bootstrap.js"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/bootbox/bootbox.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initTable();
        CustomJs.initTooltip();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
