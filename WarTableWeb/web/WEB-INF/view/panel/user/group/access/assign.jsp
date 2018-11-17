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
<%@ page session="false" %>

<div class="container">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title" ><spring:message  code="all.access"/></span> 
            <span class="cred-name">
                <i class="clip-tree"></i>
                <span class="item"><spring:message code="title.category"/></span>
                <span class="text"> :[${userGroup.title}]</span>
            </span>
            <div class="cred-name">
                <i class="clip-puzzle-4"></i>
                <span class="item"><spring:message code="all.module"/></span>
                <span class="text"><spring:message code="${moduleName}"/></span>
            </div>
        </h3>
    </div>
    <div class="input-group well col-xs-12  padding-scope" >
        <button type="submit" form="form"  class="btn btn-p-create btn-p-act btn-animated btn-animated-right">
            <spring:message code="all.register"/>
            <i class="clip-plus-circle"></i>
        </button>
        <ul class="disabled-li margin-bottom xs-center-ul" >
            <li class="tooltips" data-original-title="<spring:message  code="all.access"/>" data-placement="bottom">
                <a  href="${cp}/panel/user/group/access/list/${userGroup.id}" class="btn btn-default btn-block">
                    <i class="fa fa-shield"></i>
                    <span class="title"></span>
                </a>
            </li>
            <li class="tooltips" data-original-title="<spring:message  code="users.userGroup"/>" data-placement="bottom">
                <a  href="${cp}/panel/user/group/desk" class="btn btn-default btn-block">
                    <i class="clip-tree"></i>
                </a>
            </li>
            <li class="tooltips" data-original-title=" <spring:message code="all.reload"/>" data-placement="bottom">
                <a href="${cp}/panel/user/group/access/${userGroup.id}/${moduleId}" class="btn btn-default btn-block">
                    <i class=" clip-refresh light"></i>
                    <span class="title"></span>
                </a>
            </li>
        </ul>
    </div>
    <div class="tab-content">
        <div class="row" style="min-height: 400px;">
            <div class="col-sm-12">
                <form id="form" name='addForm' action="${cp}/panel/user/group/access" method='POST' >
                    <form:hidden path="userGroup.id" cssClass="form-control"/>
                    <input type="hidden" name="moduleId" value="${moduleId}"  cssClass="form-control" />
                    <c:set var="varName" value="userGroup.tasks"/>
                    <form:label path="${varName}" cssClass="control-label" >
                        <spring:message  code="${varName}"/>
                    </form:label>
                    <form:select multiple="multiple" path="${varName}" cssClass="form-control search-select" >
                        <c:forEach items="${userGroup.tasks}" var="tsk">
                            <form:option value="${tsk.id}" id="${tsk.id}" selected="selected">
                                <spring:message code="${tsk.messageCode}"/>
                            </form:option>
                        </c:forEach>
                        <c:forEach items="${tasks}" var="tsk">
                            <form:option value="${tsk.id}" id="${tsk.id}">
                                <spring:message code="${tsk.messageCode}"/>
                            </form:option>
                        </c:forEach>
                    </form:select>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="${cp}/resources-p/plugins/jquery-inputlimiter/jquery.inputlimiter.1.3.1.min.js"></script>
<script src="${cp}/resources-p/plugins/autosize/jquery.autosize.min.js"></script>
<script src="${cp}/resources-p/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/bootbox/bootbox.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initSelect2();;
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
