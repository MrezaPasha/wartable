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
    <c:choose>
        <c:when test="${layer.id!=0}">
            <h3 class="cred-header">
                <span class="cred-title"><spring:message code="layer.edit"/></span>
                <span class="cred-name">[${layer.name}]</span>
            </h3>
        </c:when>
        <c:otherwise>
            <h3 class="cred-header">
                <span class="cred-title"><spring:message code="layer.create"/></span>
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
                 <li class="tooltips " data-original-title="<spring:message code="all.register.new"/>" data-placement="bottom">
                    <a href="${_url}/create" class="btn btn-default btn-block btn-act">
                        <i class="clip-add light"></i>
                        <span class="title"></span>
                    </a>
                </li>
                <li class="tooltips " data-original-title="<spring:message code="all.list"/>" data-placement="bottom">
                    <a href="${_url}/list" class="btn btn-default btn-block btn-act">
                        <i class="clip-list-2 light"></i>
                        <span class="title"></span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="tab-content">
            <form id="form" accept-charset="UTF-8" action="${action}" method="POST">
                <form:hidden id="layer_id" path="layer.id"/>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="layer.name"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-50"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="layer.order"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control number-required number-format"/>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="layer.layerStatus"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options/>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="layer.type"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-50"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="layer.layerType"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options/>
                            </form:select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <c:set var="varName" value="layer.description"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required"/>
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
