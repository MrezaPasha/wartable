<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="T.p.service.meeting.details"/></span>
    </h3>
    <div class="well well-act">
        <a href="${_url}/vector/list" class="btn btn-p-back btn-sm btn-default btn-animated btn-animated-right float-left">
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
                        <tr class="details-separator">
                            <th class="border-top-0"><spring:message code="model.id"/></th>
                            <td class="border-top-0">${meeting.id}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="model.createDateTime"/></th>
                            <td class="border-top-0">${meeting.createDateTime}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meetingItem.vectorName"/></th>
                            <td class="border-top-0">${meetingItem.vectorName}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meetingItem.vectorFileName"/></th>
                            <td class="border-top-0">${meetingItem.vectorFileName}</td>
                        </tr>
                        <tr>
                            <th class="border-top-0"><spring:message code="meetingItem.vectorType"/></th>
                            <td class="border-top-0">${meetingItem.vectorType}</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
