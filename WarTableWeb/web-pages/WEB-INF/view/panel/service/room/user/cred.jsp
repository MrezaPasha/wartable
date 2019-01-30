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
<link rel="stylesheet" href="${cp}/resources/plugins/persian-datetimepicker/persian-datepicker-0.4.5.css">

<div class="container">
    <c:choose>
        <c:when test="${room_ServiceUser.id!=0}">
            <h3 class="cred-header">
                <span class="cred-title"><spring:message code="T.p.service.room.user.edit"/></span>
                <span class="cred-name"><spring:message code="room_ServiceUser.roomTitle"/>: [${room.name}]</span> &nbsp;&nbsp;&nbsp;
                <span class="cred-name"><spring:message code="model.id"/>: [${room_ServiceUser.id}]</span>
            </h3>
        </c:when>
        <c:otherwise>
            <h3 class="cred-header">
                <span class="cred-title"><spring:message code="T.p.service.room.user.create"/></span>
                <span class="cred-name"><spring:message code="room_ServiceUser.roomTitle"/>: [${room.name}]</span>
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
                    <a href="${_url}/user/list/${room.id}" class="btn btn-default btn-block btn-act">
                        <i class="clip-list-2 light"></i>
                        <span class="title"></span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="tab-content">
            <form id="form" accept-charset="UTF-8" action="${action}" method="POST">
                <form:hidden id="room_ServiceUser_id" path="room_ServiceUser.id"/>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="room_ServiceUser.room"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}.id" cssClass="form-control search-select dropdown-required">
                                <form:options items="${rlist}" itemLabel="name" itemValue="id"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="room_ServiceUser.serviceUser"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}.id" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options items="${ulist}" itemLabel="fullName" itemValue="id"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="room_ServiceUser.joinDateTime"/>
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
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="room_ServiceUser.userStatus"/>
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
                        <div class="form-group">
                            <c:set var="varName" value="room_ServiceUser.acceptPrivateChat"/>
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
                        <div class="form-group">
                            <c:set var="varName" value="room_ServiceUser.acceptPrivateTalk"/>
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
                            <c:set var="varName" value="room_ServiceUser.presence"/>
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
                        <div class="form-group">
                            <c:set var="varName" value="room_ServiceUser.permanentUserRoleFlag"/>
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
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="${cp}/resources/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cp}/resources/js/form-validation.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/jquery-inputlimiter/jquery.inputlimiter.1.3.1.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/autosize/jquery.autosize.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${cp}/resources/plugins/persian-datetimepicker/persian-date-0.1.8.js"></script>
<script type="text/javascript" src="${cp}/resources/plugins/persian-datetimepicker/persian-datepicker-0.4.5.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initSelect2();
        FormValidator.init();
        CustomJs.initPersianDateTime();
    });
</script>
