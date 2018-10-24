<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message  code="note.details"/></span>
    </h3>
    <div class="well well-act" >
        <a href="${cp}/panel/note/list" class="btn btn-p-back btn-sm btn-default btn-animated btn-animated-right float-left">
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
                            <c:when test="${not empty note}">
                                <tr>
                                    <th class="border-top-0"><spring:message code="model.id" /></th>
                                    <td class="border-top-0">${note.id}</td>
                                <tr>
                                <tr>
                                    <th><spring:message code="model.createDateTime" /></th>
                                    <td>${note.createDateTime}</td>
                                </tr>
                                <tr>
                                    <th><spring:message code="note.title" /></th>
                                    <td>${note.title}</td>
                                </tr>
                                <tr>
                                    <th><spring:message code="note.dateTime" /></th>
                                    <td>${note.dateTime}</td>
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
            <div class="note-sync-details">${note.message}</div>
        </div>
    </section>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
