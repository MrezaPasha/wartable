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
<link rel="stylesheet" href="${cp}/resources-p/plugins/bootstrap-fileupload/bootstrap-fileupload.min.css">

<div class="container">
    <c:choose>
        <c:when test="${serviceUser.id!=0}">
            <h3 class="cred-header">
                <span class="cred-title"><spring:message code="serviceUser.edit"/></span>
                <span class="cred-name">[${serviceUser.name}]</span>
            </h3>
        </c:when>
        <c:otherwise>
            <h3 class="cred-header">
                <span class="cred-title"><spring:message code="serviceUser.create"/></span>
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
            <form id="form" accept-charset="UTF-8" action="${action}" method="POST" enctype="multipart/form-data">
                <form:hidden id="serviceUser_id" path="serviceUser.id"/>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="">
                            <input id="uploadModel" name="uploadModel" type="checkbox" class="checkbox checkbox-table" data-visible-src="dv1">
                            <label for="uploadModel" class="control-label">
                                <spring:message code="serviceUser.userModel.type"/>
                            </label>
                        </div>

                        <div class="form-group" data-visible-dst="dv1" data-visible-align="true">
                            <label class="control-label">
                                <spring:message code="serviceUser.userModel"/>
                            </label>
                            <div class="fileupload-new" data-provides="fileupload">
                                <div class="input-group">
                                    <div class="form-control uneditable-input">
                                        <i class="fa fa-file fileupload-exists"></i>
                                        <span class="fileupload-preview"><c:if test="${not empty serviceUser.userModel.fileName}">${serviceUser.userModel.fileName}</c:if></span>
                                    </div>
                                    <div class="input-group-btn">
                                        <div class="btn btn-light-grey btn-file">
                                            <span class="fileupload-new"><i class="fa fa-folder-open-o"></i><spring:message code="all.file.choose"/></span>
                                            <span class="fileupload-exists"><i class="fa fa-folder-open-o"></i><spring:message code="all.file.change"/></span>
                                            <input name="attachment" type="file" class="file-input" value="${serviceUser.userModel.fileName}">
                                        </div>
                                        <a href="#" class="btn btn-light-grey fileupload-exists" data-dismiss="fileupload">
                                            <i class="fa fa-times"></i><spring:message code="all.file.delete"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" data-visible-dst="dv1" data-visible-align="false">
                            <c:set var="varName" value="serviceUser.userModel"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}.id" cssClass="form-control search-select">
                                <option value=""></option>
                                <form:options items="${umlist}" itemValue="id" itemLabel="name"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="serviceUser.userModel.scale"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="personModel.scale"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control number-required number-format"/>
                        </div>
                        <div class="three-container three-service-user"
                             i-three-mode="2"
                             i-three-name="${serviceUser.userModel.fileName}"
                             i-three-object="${objectPath}"
                             i-three-material="${userMaterialPath}">
                        </div>

                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="serviceUser.userName"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-30"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="serviceUser.name"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-50"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="serviceUser.family"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:input path="${varName}" cssClass="form-control string-required string-max-50"/>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="serviceUser.deleted"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options itemLabel="name"/>
                            </form:select>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="serviceUser.banned"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options itemLabel="name"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="serviceUser.grade"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}.id" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options items="${glist}" itemValue="id" itemLabel="value"/>

                            </form:select>
                        </div>
                        <div class="form-group">
                            <c:set var="varName" value="serviceUser.orgPosition"/>
                            <form:label path="${varName}" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                                <form:errors cssClass="form-validation-error" path="${varName}"/>
                            </form:label>
                            <form:select path="${varName}.id" cssClass="form-control search-select dropdown-required">
                                <option value=""></option>
                                <form:options items="${oplist}" itemValue="id" itemLabel="value"/>
                            </form:select>
                        </div>
                        <div class="form-group-password">
                            <a href="${_url}/user-pass/${serviceUser.id}" class="btn btn-warning btn-block"
                               <c:if test="${serviceUser.id==0}">disabled="disabled" </c:if> >
                                <spring:message code="serviceUser.change.password"/>
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
<script type="text/javascript" src="${cp}/resources/plugins/persian-datetimepicker/persian-date-0.1.8.js"></script>
<script type="text/javascript" src="${cp}/resources/plugins/persian-datetimepicker/persian-datepicker-0.4.5.js"></script>
<script src="${cp}/resources-p/plugins/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/3d/three.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/OBJLoader.js"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/3d/TrackballControls.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/MTLLoader.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/dat.gui.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/LoaderSupport.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/OBJLoader2.js"></script>


<script type="text/javascript" src="${cp}/resources-p/plugins/3d/inflate.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/FBXLoader.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/OrbitControls.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/WebGL.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/stats.min.js"></script>

<script>
    jQuery(document).ready(function () {
        CustomJs.initSelect2();
        FormValidator.init();
        CustomJs.initVisibility();
        CustomJs.initPersianDateTime();
        CustomPanelJs.init3DxLoader();
    });
</script>
