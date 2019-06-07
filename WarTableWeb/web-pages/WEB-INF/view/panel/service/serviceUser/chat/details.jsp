<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="T.p.service.serviceUser.chat.details"/></span>
        <span class="cred-name">[${serviceUser.name}]</span>

    </h3>
    <div class="well well-act">
        <a href="${_url}/chat/conversation/${serviceUser.id}" class="btn btn-p-back btn-sm btn-default btn-animated btn-animated-right float-left">
            <spring:message code="all.back"/>
            <i class="fa fa-reply"></i>
        </a>
    </div>
    <div class="row margin-top-50">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <table class="table table-hover" id="sample-table-1" style="font-size: 0.8em">
                        <tbody>
                        <tr>
                            <th class="border-top-0"><spring:message code="model.id"/></th>
                            <td class="border-top-0">${textChat.id}</td>
                        <tr>
                        <tr>
                            <th><spring:message code="model.createDateTime"/></th>
                            <td>${textChat.createDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="textChat.sendDateTime"/></th>
                            <td>${textChat.sendDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="textChat.deliverDateTime"/></th>
                            <td>${textChat.deliverDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="textChat.room"/></th>
                            <td>${textChat.room.name}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="textChat.sendStatus"/></th>
                            <td>${textChat.sendStatus}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="textChat.chatType"/></th>
                            <td>${textChat.chatType}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="textChat.senderFullName"/></th>
                            <td>${textChat.senderFullName}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="textChat.privateChatReceiver"/></th>
                            <td>${textChat.privateChatReceiver.fullName}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <section class="jumbotron text-center margin-clear" style=" background-color: white;">
        <div style="height: 320px; margin-top: 20px; font-size: 13px;">
            <div class="chat-sync-details">${textChat.messageBody}</div>
        </div>
    </section>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
