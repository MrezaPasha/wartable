<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<div class="container">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title" ><spring:message code="task.tow.level.confirm" /></span>
            <div class="cred-name">
                <i class="clip-puzzle-4"></i>
                <span class="item"><spring:message code="all.module"/></span>
                <span class="text">${taskViewModel.moduleName}</span>
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
                <a  href="${cp}/panel/module/list/confirm" class="btn btn-default btn-block">
                    <i class="fa fa-list"></i>
                    <span class="title"></span>
                </a>
            </li>
            <li class="tooltips" data-original-title=" <spring:message code="all.reload"/>" data-placement="bottom">
                <a href="${cp}/panel/module/confirm/${taskViewModel.moduleId}" class="btn btn-default btn-block">
                    <i class=" clip-refresh light"></i>
                    <span class="title"></span>
                </a>
            </li>
        </ul>
    </div>
    <div class="tab-content">
        <div class="row" style="min-height: 400px;">
            <div class="col-sm-12">
                <form id="form" name='addForm' action="${action}" method='POST' >
                    <form:hidden path="taskViewModel.moduleId" cssClass="form-control"/>
                    <c:set var="varName" value="taskViewModel.tasks"/>
                    <form:label path="${varName}" cssClass="control-label" >
                        <spring:message  code="module.tasks"/>
                    </form:label>
                    <form:select multiple="multiple" path="${varName}" cssClass="form-control search-select" >
                        <c:forEach items="${taskViewModel.tasks}" var="tsk">
                            <form:option value="${tsk.id}" id="${tsk.id}" selected="selected">
                                ${tsk.fullTitle}
                            </form:option>
                        </c:forEach>
                        <c:forEach items="${tlist}" var="tsk">
                            <form:option value="${tsk.id}" id="${tsk.id}">
                                ${tsk.fullTitle}
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
<script>
    jQuery(document).ready(function () {
        CustomJs.initSelect2();;
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
