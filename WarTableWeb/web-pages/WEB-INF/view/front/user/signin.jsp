<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : login
    Created on : Jun 1, 2016, 10:00:00 AM
    Author     : dev2
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp/simple-api" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="background-image-signin ">
    <div class="container">
        <div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 signin-box">
            <form id="form" action="${cp}/signin" class="form-horizontal padding-b-0" method="POST">
                <div class="row">
                    <div class="form-group">
                        <c:set var="varName" value="user.username"/>
                        <label for="${varName}" class="col-sm-3 control-label">
                            <spring:message code="${varName}"/>
                        </label>
                        <div class="col-sm-8">
                            <%--<input value="${username}" class="form-control string-required" name="username" id="username" autofocus="autofocus" autocomplete="off" placeholder="<spring:message code='user.signin.enter.username'/>"/>--%>
                            <input value="admin" class="form-control string-required" name="username" id="username" autofocus="autofocus" autocomplete="off" placeholder="<spring:message code='user.signin.enter.username'/>"/>
                            <%--<input value="" class="form-control" name="username" id="username" autofocus="autofocus"--%>
                            <%--placeholder="<spring:message code='user.signin.enter.username'/>"--%>
                            <%--autocomplete="off" readonly onfocus="this.removeAttribute('readonly');" onblur="this.attribute('readonly','readonly');"/>--%>
                            <i class="fa fa-user form-control-feedback"></i>
                            <span class="symbol required"></span>
                        </div>
                        <form:errors cssClass="form-validation-error" path="${varName}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group height-100px">
                        <c:set var="varName" value="user.password"/>
                        <label for="${varName}" class="col-sm-3 control-label">
                            <spring:message code="${varName}"/>
                        </label>
                        <div class="col-sm-8">
                            <input value="admin" class="form-control password-format" type="password" name="password" autocomplete="off" placeholder="<spring:message code='user.signin.enter.password'/>"/>
                            <%--<input value="${password}" class="form-control password-format" type="password" name="password" autocomplete="off" placeholder="<spring:message code='user.signin.enter.password'/>"/>--%>
                            <i class="fa fa-lock form-control-feedback"></i>
                            <span class="symbol required"></span>
                        </div>
                        <form:errors cssClass="form-validation-error" path="${varName}"/>
                    </div>
                </div>

                <c:if test="${!noRecaptcha}">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-3">
                            <div class="signin-bot-img">
                                <botDetect:simpleCaptcha id="exampleCaptcha"/>
                            </div>
                            <div class="form-group validationDiv">
                                <input id="captchaCode" type="text" name="captchaCode" class="form-control"
                                       value="${basicExample.captchaCode}"/>
                                <span class="correct">${basicExample.captchaCorrect}</span>
                                <span class="incorrect">${basicExample.captchaIncorrect}</span>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8">
                            <c:choose>
                                <c:when test="${isForceToLogin}">
                                    <button name="force" <c:if test="${blocked}"> disabled="true"</c:if> type="submit" class="btn btn-group btn-signin-force btn-animated btn-animated-right">
                                        <spring:message code="user.signout.others"/>
                                    </button>
                                    <a href="${cp}/signin" class="btn btn-group btn-signin-cancel btn-animated btn-animated-right">
                                        <spring:message code="user.signin.cancel"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <button <c:if test="${blocked}"> disabled="true"</c:if> type="submit" class="btn btn-group btn-signin btn-animated btn-animated-right">
                                        <spring:message code="user.signin"/>
                                        <i class="fa fa-hand-o-left"></i>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="${cp}/resources/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="${cp}/resources/js/form-validation.js"></script>
<script>
    jQuery(document).ready(function () {
        FormValidator.init();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
