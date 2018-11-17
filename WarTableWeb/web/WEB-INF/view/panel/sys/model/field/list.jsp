<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<link rel="stylesheet" href="${cp}/resources-p/plugins/dataTables/media/css/DT_bootstrap.css"/>
<div class="container">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title">
                مدیریت بانک اطلاعاتی
            </span> 
        </h3>
    </div>
    <div class="input-group well col-xs-12  padding-scope">
        <a href="${cp}/panel/model/adapt/${model.id}?sx=sx" class="btn btn-l-edit btn-p-act btn-animated btn-animated-right">
            تطبیق ساختار
            <i class="clip-vynil"></i>
        </a>
        <a href="${cp}/panel/model/rebuild/${model.id}?sx=sx" class="btn btn-l-complete btn-p-act btn-animated btn-animated-right">
            بازسازی ساختار
            <i class="clip-cogs"></i>
        </a>
        <ul class="disabled-li margin-bottom xs-center-ul-4">
            <li class="tooltips " data-original-title="بازگشت به مدل" data-placement="top">
                <a  href="${cp}/panel/model/list" class="btn btn-default btn-block btn-act">
                    <i class="clip-database light"></i>
                    <span class="title"></span>
                </a>
            </li>
        </ul>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <i class="fa fa-external-link-square"></i>
            لیست فیلدها
            <div class="panel-tools">
                <a class="btn btn-xs btn-link panel-collapse collapses" href="javascript.void(0)"></a>
            </div>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table dataTable table-striped table-hover" id="table_list_x" data-sort-index="0" data-sort-type="asc" data-display-len="-1">
                    <thead>
                        <tr>
                            <th><spring:message code="model.id"/></th>
                            <th><spring:message code="field.moTitle"/></th>
                            <th><spring:message code="field.isMoNullable"/></th>
                            <th><spring:message code="field.moType"/></th>
                            <th><spring:message code="field.moMaxSize"/></th>
                            <th><spring:message code="field.moDataRelation"/></th>
                            <th><spring:message code="field.dbTitle"/></th>
                            <th><spring:message code="field.isDbNullable"/></th>
                            <th><spring:message code="field.dbType"/></th>
                            <th><spring:message code="field.dbSize"/></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody class="text-small">
                        <c:if test="${!empty flist}">
                            <c:forEach items="${flist}" var="myvar">
                                <c:choose>
                                    <c:when test="${!myvar.isBidirectional}"><c:set var="mycl" value="bidirectional"/></c:when>
                                    <c:when test="${myvar.isNew}"><c:set var="mycl" value="added"/></c:when>
                                    <c:when test="${myvar.isRemoveFromModel}"><c:set var="mycl" value="removed"/></c:when>
                                    <c:when test="${myvar.isRemoveFromModelAndDB}"><c:set var="mycl" value="removed"/></c:when>
                                    <c:when test="${myvar.isChanged}"><c:set var="mycl" value="changed"/></c:when>
                                    <c:otherwise><c:set var="mycl" value=""/></c:otherwise>
                                </c:choose>
                                <tr class="${mycl}">
                                    <td>${myvar.id}</td>
                                    <td class="iransans-standard dir-ltr">${myvar.moTitle}</td>
                                    <td>${myvar.isMoNullableY.title}</td>
                                    <td class="iransans-standard dir-ltr">${myvar.moType}</td>
                                    <td>${myvar.moMaxSize}</td>
                                    <td>${myvar.moDataRelation.title}</td>
                                    <td class="iransans-standard dir-ltr">${myvar.dbTitle}</td>
                                    <td>${myvar.isDbNullableY.title}</td>
                                    <td class="iransans-standard dir-ltr">${myvar.dbType}</td>
                                    <td>${myvar.dbSize}</td>
                                    <td class="center">
                                        <div class="visible-md visible-lg hidden-sm hidden-xs r-button">
                                            <a href="${cp}/panel/model/details/${myvar.id}" class="btn btn-sm btn-l-details tooltips " data-placement="top" data-original-title="جزئیات">
                                                <i class="clip-flickr"></i>
                                            </a>
                                        </div>
                                        <div class="visible-xs visible-sm hidden-md hidden-lg r-button">
                                            <div class="btn-group">
                                                <a class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown" href="javascript:void(0)">
                                                    <i class="fa fa-cog"></i>
                                                    <span class="caret"></span>
                                                </a>
                                                <ul role="menu" class="dropdown-menu pull-right" style="z-index: 1000;">
                                                    <li role="presentation">
                                                        <a role="menuitem" tabindex="-1" href="${cp}/panel/model/details/${myvar.id}" >
                                                            <i class="clip-flickr"></i> 
                                                            جزئیات
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
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
