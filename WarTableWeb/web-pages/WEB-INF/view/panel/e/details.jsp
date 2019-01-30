<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container c-list-container">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="error.system"/></span>
    </h3>
    <div class="well well-act">
        <a href="${_url}/list" class="btn btn-p-back btn-sm btn-default btn-animated btn-animated-right float-left">
            <spring:message code="all.back"/>
            <i class="fa fa-reply"></i>
        </a>
    </div>
    <div class="row margin-top-50">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-hover iransans-standard" id="sample-table-1" style="font-size: 0.8em">
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty irror}">
                                <tr>
                                    <th class="border-top-0">شناسه</th>
                                    <td class="border-top-0">${irror.id}</td>
                                <tr>
                                <tr>
                                    <th>مشخصه دستگاه</th>
                                    <td>${irror.computerSignature}</td>
                                </tr>
                                <tr>
                                    <th>مشخصه مرورگر</th>
                                    <td>${irror.agentSignature}</td>
                                </tr>
                                <tr>
                                    <th>کد حامل</th>
                                    <td>${irror.porterUuid}</td>
                                </tr>
                                <tr>
                                    <th>شناسه نشست</th>
                                    <td>${irror.sessionId}</td>
                                </tr>
                                <tr>
                                    <th>عملیات</th>
                                    <td>${irror.taskName}</td>
                                </tr>
                                <tr>
                                    <th>کدخطا</th>
                                    <td>${irror.httpErrorCode}</td>
                                </tr>
                                <tr>
                                    <th>علت رخداد</th>
                                    <td>${irror.cause}</td>
                                </tr>
                                <tr>
                                    <th>محل ثبت</th>
                                    <td>${irror.place.title}</td>
                                </tr>
                                <tr>
                                    <th>سطح خطا</th>
                                    <td>${irror.level.title}</td>
                                </tr>
                                <tr>
                                    <th>کاربر</th>
                                    <td>${irror.user.fullName}</td>
                                </tr>
                            </c:when>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <section class="jumbotron text-center margin-clear" style=" background-color: white;">
        <div style="height: 320px; margin-top: 20px; font-size: 13px;">
            <div class="err-p-500">${irror.message}</div>
        </div>
    </section>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
