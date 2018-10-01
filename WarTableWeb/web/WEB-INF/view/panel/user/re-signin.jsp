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
        <span class="cred-title"><spring:message code="user.resign"/></span>
    </h3>

    <div class="tabbable">
        <div class="tab-content">
            <form id="form" accept-charset="UTF-8" action="${action}" method="POST">
                <input type="hidden" id="reSignUrl" name="reSignUrl" value="${reSignUrl}"/>
                <input type="hidden" id="taskSignature" name="taskSignature" value="${taskSignature}"/>
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-4">
                        <div class="form-group">
                            <label for="${password}" class="control-label">
                                <spring:message code="user.password"/>
                                <span class="symbol required"></span>
                            </label>
                            <input id="userPass" autofocus="autofocus" type="password" name="userPass" class="form-control password-format text-center"/>
                        </div>
                        <button type="submit" form="form" class="btn btn-p-create btn-block btn-animated btn-animated-right">
                            <spring:message code="all.register"/>
                            <i class="clip-plus-circle"></i>
                        </button>
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
    });
</script>
