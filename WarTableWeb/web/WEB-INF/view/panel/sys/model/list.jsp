<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<link rel="stylesheet" href="${cp}/resources-p/plugins/dataTables/media/css/DT_bootstrap.css?r=${rl2}"/>
<div class="container">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title">
                مدیریت بانک اطلاعاتی
            </span> 
        </h3>
    </div>
    <div class="input-group well col-xs-12  padding-scope">
        <a href="${cp}/panel/model/adapt" class="btn btn-l-edit btn-p-act btn-animated btn-animated-right">
            تطبیق کل ساختار
            <i class="clip-vynil"></i>
        </a>
        <a href="${cp}/panel/model/rebuild" class="btn btn-l-complete btn-p-act btn-animated btn-animated-right">
            بازسازی کل ساختار
            <i class="clip-cogs"></i>
        </a>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-external-link-square"></i>
            لیست مدل ها
            <div class="panel-tools">
                <a class="btn btn-xs btn-link panel-collapse collapses" href="javascript.void(0)"></a>
            </div>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table dataTable table-striped table-hover" id="table_list_x" data-sort-index="0" data-sort-type="asc" data-display-len="-1">
                    <thead>
                        <tr>
                            <th> <spring:message code="model.id"/></th>
                            <th><spring:message code="model.tableName"/></th>
                            <th><spring:message code="model.className"/></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody class="text-small">
                        <c:if test="${!empty mlist}">
                            <c:forEach items="${mlist}" var="myvar">
                                <c:choose>
                                    <c:when test="${myvar.isChanged}"><c:set var="mycl" value="changed"/></c:when>
                                    <c:when test="${myvar.isRemoveFromModel}"><c:set var="mycl" value="removed"/></c:when>
                                    <c:otherwise><c:set var="mycl" value=""/></c:otherwise>
                                </c:choose>
                                <tr class="${mycl}">
                                    <td>${myvar.id}</td>
                                    <td class="dir-ltr iransans-standard">${myvar.tableName}</td>
                                    <td  class="dir-ltr iransans-standard">${myvar.className}</td>
                                    <td class="right" style="width: 168px;">
                                        <div class="visible-md visible-lg hidden-sm hidden-xs ">
                                            <a target="_blank" href="${cp}/panel/model/fields/${myvar.id}" class="btn btn-sm btn-l-state tooltips" data-placement="top" data-original-title="فیلدها">
                                                <i class="clip-forrst pad-2"></i>
                                            </a>
                                            <a href="${cp}/panel/model/adapt/${myvar.id}" class="btn btn-sm btn-l-edit tooltips" data-placement="top" data-original-title="تطبیق اطلاعات">
                                                <i class="clip-vynil pad-2"></i>
                                            </a>
                                            <a href="${cp}/panel/model/rebuild/${myvar.id}" class="btn btn-sm btn-l-complete tooltips" data-placement="top" data-original-title="بازسازی اطلاعات">
                                                <i class="clip-cogs pad-2"></i>
                                            </a>
                                        </div>
                                        <div class="visible-xs visible-sm hidden-md hidden-lg r-button">
                                            <div class="btn-group">
                                                <a class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown" href="javascript.void(0)">
                                                    <i class="fa fa-cog"></i> <span class="caret"></span>
                                                </a>
                                                <ul role="menu" class="dropdown-menu pull-right" style="z-index: 1000;">
                                                    <li role="presentation">
                                                        <a target="_blank" role="menuitem" tabindex="-1"  href="${cp}/panel/model/fields/${myvar.id}">
                                                            <i class="clip-forrst pad-2"></i>
                                                            فیلدها
                                                        </a>
                                                    </li>
                                                    <li role="presentation">
                                                        <a role="menuitem" tabindex="-1" href="${cp}/panel/model/adapt/${myvar.id}">
                                                            <i class="clip-vynil pad-2"></i>
                                                            تطبیق اطلاعات
                                                        </a>
                                                    </li>
                                                    <li role="presentation">
                                                        <a role="menuitem" tabindex="-1" href="${cp}/panel/model/rebuild/${myvar.id}">
                                                            <i class="clip-cogs pad-2"></i>
                                                            بازسازی اطلاعات
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
<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/jquery.dataTables.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/dataTables/media/js/DT_bootstrap.js?r=${rl2}"></script>

<script>
    jQuery(document).ready(function () {
        CustomJs.initTable();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
