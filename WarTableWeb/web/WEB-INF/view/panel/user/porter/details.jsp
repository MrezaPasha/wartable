<%--/*********************#- 1397/01/02 12:57:37 (Emsisoft)  -#*/--%>



<%--/*********************#- 1397/04/20 12:03:52 (Emsisoft_V4)  -#*/--%>

<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>

<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title">
            جزئیات حامل تاییدیه ${userPorter.user.fullName} : ${userPorter.id}
        </span>
    </h3>
    <div class="well well-act">
        <ul class="disabled-li margin-bottom xs-center-ul-3">
            <li class="tooltips " data-original-title="<spring:message code="all.list"/>" data-placement="bottom">
                <a href="${cp}/panel/user/porter/list/${userPorter.user.id}" class="btn btn-default btn-block btn-act">
                    <i class="fa fa-list light"></i>
                    <span class="title"></span>
                </a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="panel-body">
            <div class="panel-group accordion-custom accordion-teal">
                <div class="panel panel-default">
                    <div class="panel-collapse collapse in content-details">
                        <div class="col-sm-8 no-border">
                            <div class="col-sm-4">
                                <label><spring:message code="model.id"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.id}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="model.createDateTime"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.createDateTime}</div>
                        </div>
                         <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="model.modifyDateTime"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.modifyDateTime}</div>
                        </div>
                         <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.computerSignature"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.computerSignature}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.uuid"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.uuid}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.singinDateTimeG"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.singinDateTimeG}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.singoutDateTimeG"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.singoutDateTimeG}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.expireDateTimeG"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.expireDateTimeG}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.confirmCode"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.confirmCode}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.confirmCodeDateTimeG"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.confirmCodeDateTimeG}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.signoutStatus"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.signoutStatus}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.isConfirmedY"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.isConfirmedY.title}</div>
                        </div>
                         <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.isActiveTwoStepConfirmY"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.isActiveTwoStepConfirmY.title}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.count"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.count}</div>
                        </div>
                        <div class="col-sm-8">
                            <div class="col-sm-4">
                                <label><spring:message code="userPorter.user"/></label>
                            </div>
                            <div class="col-sm-7 content-item">${userPorter.user.fullName}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
