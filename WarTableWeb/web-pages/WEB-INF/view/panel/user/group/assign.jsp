<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : assign
    Created on : Aug 17, 2016, 3:39:17 PM
    Author     : slt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<div class="container">
    <c:choose>
        <c:when test="${userGroup==null}">
            <div class="row">
                <div class="page-header-2">
                    <h3 class="cred-header">
                        <span class="cred-title"><spring:message code="users.userGroup.assign"/></span>
                    </h3>
                </div>
                <div class="col-sm-2">
                    <h3 class="cred-select-lable"><spring:message code="user.selectuserGroups"/>:</h3>
                </div>
                <div class="form-group col-sm-6">
                    <form:select path="selectedUserGroup" id="selectedItemId" cssClass="form-control search-select">
                        <option value=""></option>
                        <form:options items="${uglist}" itemValue="id" itemLabel="title"/>
                    </form:select>
                </div>
                <div class="margin-rl-15">
                    <button id="selectButtonId" class="btn btn-p-act btn-p-choice btn-animated btn-animated-right">
                        <spring:message code="all.choice"/>
                        <i class=" clip-download-3"></i>
                    </button>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="page-header">
                <h3 class="cred-header">
                    <span class="cred-title"><spring:message code="users.userGroup.assign"/></span>
                    <span class="cred-name"><i class="fa  fa-pagelines"></i>&nbsp;<spring:message code="title.category"/>:[${userGroup.title}] </span>
                </h3>
            </div>
            <div class="well well-act">
                <button type="submit" form="form" class="btn btn-p-create btn-p-act btn-animated btn-animated-right">
                    <spring:message code="all.register"/>
                    <i class="clip-plus-circle"></i>
                </button>
                <ul class="disabled-li margin-bottom xs-center-ul">
                    <li class="tooltips" data-original-title="<spring:message  code="users.userGroup"/>" data-placement="bottom">
                        <a href="${cp}/panel/user/group/desk" class="btn btn-default btn-block btn-act">
                            <i class="clip-tree"></i>
                            <span class="title"></span>
                        </a>
                    </li>
                    <li class="tooltips" data-original-title=" <spring:message code="all.reload"/>" data-placement="bottom">
                        <a href="${cp}/panel/user/group/assign/${userGroup.id}" class="btn btn-default btn-block btn-act">
                            <i class=" clip-refresh light"></i>
                            <span class="title"></span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="tab-content">
                <form id="form" name='addForm' action="${action}" method='POST'>
                    <form:hidden path="userGroup.id" cssClass="form-control"/>
                    <c:set var="varName" value="userGroup.users"/>
                    <form:label path="${varName}" cssClass="control-label">
                        <spring:message code="${varName}"/>
                    </form:label>
                    <form:select multiple="multiple" path="${varName}" cssClass="form-control height-auto search-select">
                        <form:options items="${userGroup.users}" itemValue="id" itemLabel="fullNameId"/>
                        <form:options items="${ulist}" itemValue="id" itemLabel="fullNameId"/>
                    </form:select>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<script src="${cp}/resources/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="${cp}/resources/js/form-validation.js"></script>

<script src="${cp}/resources-p/plugins/jquery-inputlimiter/jquery.inputlimiter.1.3.1.min.js"></script>
<script src="${cp}/resources-p/plugins/autosize/jquery.autosize.min.js"></script>
<script src="${cp}/resources-p/plugins/select2/select2.min.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initSelect2();
        ;
        FormValidator.init();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
