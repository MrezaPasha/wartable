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
<link rel="stylesheet" href="${cp}/resources/plugins/persian-datetimepicker/persian-datepicker-0.4.5.css?r=${rl2}">

<div class="container">
    <c:choose>
        <c:when test="${user.id!=0}">
            <h3 class="cred-header">
                <span class="cred-title"><spring:message code="user.edit"/></span>
                <span class="cred-name">[${user.fullName}]</span>
            </h3>
        </c:when>
        <c:otherwise>
            <h3 class="cred-header">
                <span class="cred-title"><spring:message code="user.create"/></span>
            </h3>
        </c:otherwise>
    </c:choose>
    <div class="tabbable">
        <div class="well well-act">
            <button type="submit" form="form" class="btn btn-p-create btn-p-act btn-animated btn-animated-right">
                <spring:message code="all.register"/>
                <i class="clip-plus-circle"></i>
            </button>
            <ul class="disabled-li margin-bottom xs-center-ul-4">
                <li class="tooltips " data-original-title="<spring:message code="all.list"/>" data-placement="bottom">
                    <a href="${cp}/panel/user/list" class="btn btn-default btn-block btn-act">
                        <i class="clip-list-2 light"></i>
                        <span class="title"></span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="tab-content">
            <form id="form" accept-charset="UTF-8" action="${action}" method="POST">
                <form:hidden id="user_id" path="user.id"/>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="user.username"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <c:choose>
                                <c:when test="${user.isLogManager or user.isSuperAdmin}">
                                    <c:set var="dis" value="true"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="dis" value="false"/>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${user.isLogManager}">
                                <c:set var="dis" value="true"/>
                            </c:if>
                            <form:input disabled="${dis}" path="${varName}" cssClass="form-control string-required string-max-30"/>
                            <button type="button" <c:if test="${user.isLogManager or user.isSuperAdmin}"> disabled </c:if>
                                    id="randomButton" class="btn-username-refresh">
                                <i class="clip-refresh"></i>
                            </button>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.gender"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options itemLabel="key"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.firstName"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-50"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.lastName"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-50"/>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="user.userCode"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-max-30"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.status"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                            </form:label>
                            <form:errors cssClass="form-validation-error" path="${varName}"/>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options itemLabel="title"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.level"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                            </form:label>
                            <form:errors cssClass="form-validation-error" path="${varName}"/>
                            <form:select disabled="${dis}" path="${varName}" data-visible-src="dv0" data-visible-value="Master" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options itemLabel="title"/>
                            </form:select>
                        </div>
                        <c:if test="${!dis}">
                            <div data-visible-dst="dv0" data-visible-align="true" class="form-group">
                                <c:set var="varName" value="user.userGroups"/>
                                <form:label path="${varName}" cssClass="control-label">
                                    <spring:message code="${varName}"/>
                                </form:label>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                                <form:select multiple="multiple" path="${varName}" cssClass="form-control height-auto search-select">
                                    <form:options items="${user.userGroups}" itemValue="id" itemLabel="title"/>
                                    <form:options items="${uglist}" itemValue="id" itemLabel="title"/>
                                </form:select>
                            </div>
                        </c:if>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group-password">
                            <a href="${cp}/panel/user/user-pass/${user.id}" class="btn btn-warning btn-block"
                               <c:if test="${user.id==0}">disabled="disabled" </c:if> >
                                <spring:message code="user.change.password"/>
                            </a>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.ipRangeType"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                            </form:label>
                            <form:errors cssClass="form-validation-error" path="${varName}"/>
                            <form:select path="${varName}" data-visible-src="dv1" data-visible-value="One,Range,FirstSignin" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options itemLabel="title"/>
                            </form:select>
                        </div>
                        <div class="form-group" data-visible-dst="dv1" data-visible-align-1="true" data-visible-align-2="false" data-visible-align-3="false">
                            <c:set var="varName" value="user.ipAddress"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-50"/>
                        </div>
                        <div class="form-group" data-visible-dst="dv1" data-visible-align-1="false" data-visible-align-2="true" data-visible-align-3="false">
                            <c:set var="varName" value="user.ipAddressStart"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-50"/>
                        </div>
                        <div class="form-group" data-visible-dst="dv1" data-visible-align-1="false" data-visible-align-2="true" data-visible-align-3="false">
                            <c:set var="varName" value="user.ipAddressEnd"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-50"/>
                        </div>
                        <div class="form-group" data-visible-dst="dv1" data-visible-align-1="false" data-visible-align-2="false" data-visible-align-3="true">
                            <c:set var="varName" value="user.ipAddressFirstSignin"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <span class="form-control">
                                ${user.ipAddressFirstSignin}
                            </span>
                        </div>
                    </div>
                </div>
                <hr class="row"/>
                <div class="row">
                    <div class="col-sm-3">
                        <div class="form-group">
                            <c:set var="varName" value="user.accessLimitYearlyStart"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}"
                                        autocomplete="off"
                                        data-initial-value="false"
                                        cssClass="form-control persian-datetime-format"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.accessLimitYearlyEnd"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}"
                                        autocomplete="off"
                                        data-initial-value="false"
                                        cssClass="form-control persian-datetime-format"/>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <c:set var="varName" value="user.accessLimitMonthlyStart"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input type="number" path="${varName}" min="0" max="12" cssClass="form-control number-format"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.accessLimitMonthlyEnd"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input type="number" path="${varName}" min="0" max="12" cssClass="form-control number-format"/>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <c:set var="varName" value="user.accessLimitDailyStart"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input type="number" path="${varName}" min="0" max="31" cssClass="form-control number-format"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.accessLimitDailyEnd"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input type="number" path="${varName}" min="0" max="31" cssClass="form-control number-format"/>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <c:set var="varName" value="user.accessLimitTimelyStart"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}"
                                        autocomplete="off"
                                        data-initial-value="false"
                                        cssClass="form-control persian-time-format"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="user.accessLimitTimelyEnd"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}"
                                        autocomplete="off"
                                        data-initial-value="false"
                                        cssClass="form-control persian-time-format"/>
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
<script type="text/javascript" src="${cp}/resources/plugins/persian-datetimepicker/persian-date-0.1.8.js?r=${rl2}"></script>
<script type="text/javascript" src="${cp}/resources/plugins/persian-datetimepicker/persian-datepicker-0.4.5.js?r=${rl2}"></script>

<script>
    jQuery(document).ready(function () {
        CustomJs.initSelect2();
        FormValidator.init();
        CustomJs.initVisibility();
        CustomPanelJs.initRandomUsername();
        CustomJs.initPersianDateTime();

    });
</script>
