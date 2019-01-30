<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="serviceLog.details"/></span>
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
                        <tr class="details-separator">
                            <th class="border-top-0"><spring:message code="model.id"/></th>
                            <td class="border-top-0">${serviceLog.id}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.actionType"/></th>
                            <td>${serviceLog.actionType}</td>
                        </tr>

                        <tr class="details-separator">
                            <th class="border-top-0"><spring:message code="model.createDateTime"/></th>
                            <td class="border-top-0">${serviceLog.createDateTime}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.clientHostname"/></th>
                            <td>${serviceLog.clientHostname}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.clientIP"/></th>
                            <td>${serviceLog.clientIP}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.flag"/></th>
                            <td>${serviceLog.flag}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.importance"/></th>
                            <td>${serviceLog.importance}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.sensitivity"/></th>
                            <td>${serviceLog.sensitivity}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.pageTitle"/></th>
                            <td>${serviceLog.pageTitle}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.portNumber"/></th>
                            <td>${serviceLog.portNumber}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.serverHostname"/></th>
                            <td>${serviceLog.serverHostname}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.serverIP"/></th>
                            <td>${serviceLog.serverIP}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.url"/></th>
                            <td>${serviceLog.url}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.softwareID"/></th>
                            <td>${serviceLog.softwareID}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.softwareName"/></th>
                            <td>${serviceLog.softwareName}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.softwareVersion"/></th>
                            <td>${serviceLog.softwareVersion}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.username"/></th>
                            <td>${serviceLog.username}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.userUniqueID"/></th>
                            <td>${serviceLog.userUniqueID}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.subType"/></th>
                            <td>${serviceLog.subType}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="serviceLog.subTypeDescription"/></th>
                            <td>${serviceLog.subTypeDescription}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <section class="jumbotron text-center margin-clear" style=" background-color: white;">
        <div style="height: 320px; margin-top: 20px; font-size: 13px;">
            <div class="err-p-500">${serviceLog.description}</div>
        </div>
    </section>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
