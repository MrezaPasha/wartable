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
        <span class="cred-title"><spring:message code="user.your.edit"/></span>
        <span class="cred-name">[${user.fullName}]</span>
    </h3>

    <div class="tabbable">
        <div class="well well-act">
            <button type="submit" form="form" class="btn btn-p-create btn-p-act btn-animated btn-animated-right">
                <spring:message code="all.register"/>
                <i class="clip-plus-circle"></i>
            </button>
        </div>
        <div class="tab-content">
            <form id="form" accept-charset="UTF-8" action="${action}" method="POST">
                <form:hidden id="user_id" path="user.id"/>

                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="user.username"/>
                            <c:if test="${user.isLogManager}">
                                <c:set var="dis" value="true"/>
                                <form:hidden path="${varName}"/>
                            </c:if>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input disabled="${dis}" path="${varName}" cssClass="form-control string-required string-max-30"/>
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
                        <div class="form-group-password">
                            <a href="${cp}/panel/user/your-pass" class="btn btn-warning btn-block"
                               <c:if test="${user.id==0}">disabled="disabled" </c:if> >
                                <spring:message code="user.change.password"/>
                            </a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="${cp}/resources/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cp}/resources/js/form-validation.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/jquery-inputlimiter/jquery.inputlimiter.1.3.1.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/autosize/jquery.autosize.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initSelect2();
        FormValidator.init();
    });
</script>
