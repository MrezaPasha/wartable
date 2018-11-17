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
        <span class="cred-title"><spring:message code="user.change.password"/></span>
        <span class="cred-name">[${user.fullName}]</span>
    </h3>
    <div class="tabbable">
        <div class="well well-act">
            <button type="submit" form="form" class="btn btn-p-create btn-p-act btn-animated btn-animated-right">
                <spring:message code="all.register"/>
                <i class="clip-plus-circle"></i>
            </button>
            <ul class="disabled-li margin-bottom xs-center-ul-4">
                <li class="tooltips" data-original-title="<spring:message code="all.edit"/>" data-placement="bottom">
                    <a href="${cp}/panel/user/your-edit" class="btn btn-default btn-block btn-act">
                        <i class="fa fa-edit light"></i>
                        <span class="title"></span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="tab-content">
            <form id="form" accept-charset="UTF-8" action="${action}" method="POST">
                <form:hidden id="user_id" path="user.id"/>
                <div id="pwd-container" class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="user.password.new"/>
                            <label for="newPassword" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                            </label>
                            <input id="newPassword" type="password" name="newPassword" data-repassword-target
                                   autocomplete="off"
                                   class="form-control disable-paste password-required password-required-minlen password-format"/>
                        </div>
                        <div class="pwstrength_viewport_progress"></div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <c:set var="varName" value="user.repassword"/>
                            <label for="repassword" cssClass="control-label">
                                <spring:message code="${varName}"/>
                                <span class="symbol required"></span>
                            </label>
                            <input id="repassword" name="repassword" type="password"
                                   autocomplete="off"
                                   class="form-control disable-paste password-required password-required-minlen repassword-format"/>
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
<script type="text/javascript" src="${cp}/resources-p/plugins/pw-strength/dist/pwstrength-bootstrap.min.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initSelect2();
        FormValidator.init();
        CustomPanelJs.intPwLength();
    });
</script>
