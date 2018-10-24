<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page session="false" %>
<section class="panel-dashboard">
    <div class="container">
        <div class="row dashboard-info-box">
            <c:if test="${signinLog!=null}">
                <div class="col-sm-4">
                    <div class="signin-log">
                        <ul>
                            <li class="log-date">
                                <i><spring:message code="signinLog.lastDateTime"/>:</i>
                                <span>${signinLog.lastDateTimeSM}</span>
                            </li>
                            <li class="log-status">
                                <i><spring:message code="signinLog.status"/>:</i>
                                <span>${signinLog.status.title}
                                     <c:if test="${signinFailedCount>0}">
                                         <span class="log-failed-count">${signinFailedCount} <spring:message code="signin.failed.count"/> </span>
                                     </c:if>
                               </span>
                            </li>
                            <li class="log-ip">
                                <i><spring:message code="signinLog.ipAddress"/>:</i>
                                <span>${signinLog.ipAddress}</span>
                            </li>
                            <li class="log-domain">
                                <i><spring:message code="signinLog.domainName"/>:</i>
                                <span>${signinLog.domainName}</span>
                            </li>
                            <c:if test="${isSigninIpChanged}">
                                <li class="log-danger">
                                    <i><spring:message code="signinLog.danger"/>:</i>

                                    <span><spring:message code="signinLog.ip.changed"/>[${signinMainIp}]</span>
                                </li>
                            </c:if>
                        </ul>

                    </div>
                </div>
            </c:if>
            <c:if test="${not empty nlist}">
                <div class="col-sm-6 col-sm-offset-1 note-sync-container">
                    <h1>یاداشت های نخوانده</h1>
                    <table>
                        <thead>
                        <th><spring:message code="note.title"/></th>
                        <th><spring:message code="note.dateTime"/></th>
                        <th><spring:message code="note.importance"/></th>
                        <th></th>
                        </thead>
                        <tbody>
                        <c:forEach items="${nlist}" var="myvar">
                                <tr>
                                    <td>${myvar.title}</td>
                                    <td>${myvar.dateTime}</td>
                                    <td>${myvar.importance.title}</td>
                                    <td><a href="${cp}/panel/note/details/${myvar.id}"><spring:message code="all.details" /></a></td>
                                </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>

    </div>
</section>
