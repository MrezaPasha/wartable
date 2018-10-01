<%--
    Document   : edit
    Created on : Jun 2, 2016, 10:54:25 AM
    Author     : dev1
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<div class="container">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="task.config"/></span>
        <span class="cred-name">${task.module.title}</span> |
        <span class="cred-name">${task.title}</span>

    </h3>
    <div class="well well-act">
        <button type="submit" form="form" class="btn btn-p-create btn-width-150 btn-animated btn-animated-right">
            <spring:message code="all.register"/>
            <i class="clip-plus-circle"></i>
        </button>
        <ul class="disabled-li margin-bottom xs-center-ul">
            <li class="tooltips " data-original-title="<spring:message code="task.list"/>" data-placement="bottom">
                <a href="${cp}/panel/log/task/list/${task.module.id}" class="btn btn-default btn-block btn-act">
                    <i class="clip-list-2 light"></i>
                    <span class="title"></span>
                </a>
            </li>
        </ul>
    </div>
    <div class="tabbable">
        <div class="tab-content">
            <form id="form" accept-charset="UTF-8" action="${action}" method="POST">
                <form:hidden path="task.id"/>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="task.importanceLevel"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options itemLabel="title"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="task.sensitivity"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options itemLabel="title"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="task.isOnlineLoggingY"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required" data-visible-src="dv1" data-visible-value="Yes">
                                <option value=""></option>
                                <form:options itemLabel="title"/>
                            </form:select>
                        </div>
                        <div class="form-group" data-visible-dst="dv1" data-visible-align="true">
                            <c:set var="varName" value="task.actionType"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options itemLabel="title"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-sm-4" data-visible-dst="dv1" data-visible-align="true">
                        <div class="form-group">
                            <c:set var="varName" value="task.onlineLoggingStrategy"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required" data-visible-src="dv2" data-visible-value="WithDelay,Scheduling">
                                <option value=""></option>
                                <form:options itemLabel="title"/>
                            </form:select>
                        </div>
                        <div class="form-group" data-visible-dst="dv2" data-visible-align-1="true" data-visible-align-2="false">
                            <c:set var="varName" value="task.onlineLoggingDelay"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input type="number" path="${varName}" cssClass="form-control number-required"/>
                        </div>
                        <div class="form-group" data-visible-dst="dv2" data-visible-align-1="false" data-visible-align-2="true">
                            <c:set var="varName" value="task.onlineSchedulingTime"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-15"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="${cp}/resources/plugins/jquery-validation/dist/jquery.validate.min.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources/js/form-validation.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/jquery-inputlimiter/jquery.inputlimiter.1.3.1.min.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/autosize/jquery.autosize.min.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js?r=${rl2}"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initSelect2();
        FormValidator.init();
        CustomJs.initVisibility();
    });
</script>
