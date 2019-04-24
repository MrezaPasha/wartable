<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page session="false" %>
<section class="panel-dashboard">
    <div class="container">
        <div class="row dashboard-info-box">
            <div class="col-sm-4">
                <c:if test="${slList!=null}">
                    <div class="successful-login">
                        <div class="signin-log">
                            <ul>
                                <li class="log-date">
                                    <i><spring:message code="signinLog.lastDateTime"/>:</i>
                                    <span>${slList[0].lastDateTimeSM}</span>
                                </li>
                                <li class="log-ip">
                                    <i><spring:message code="signinLog.ipAddress"/>:</i>
                                    <span>${slList[0].ipAddress}</span>
                                </li>
                                <li class="log-domain">
                                    <i><spring:message code="signinLog.domainName"/>:</i>
                                    <span>${slList[0].domainName}</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <c:if test="${fn:length(slList)>1}">
                        <div class="unsuccessful-login">
                            <div class="signin-count">
                                    ${fn:length(slList)-1} <spring:message code="user.signin.unsuccessfull.login"/>
                            </div>
                            <div class="signin-box">
                                <c:forEach var="signinLog" items="${slList}" varStatus="loop">
                                    <c:if test="${loop.index>0}">
                                        <div class="signin-log">
                                            <ul>
                                                <li class="log-date">
                                                    <i><spring:message code="signinLog.attempt.time"/>:</i>
                                                    <span>${signinLog.lastDateTimeSM}</span>
                                                </li>
                                                <li class="log-ip">
                                                    <i><spring:message code="signinLog.connection.info"/>:</i>
                                                    <span dir="ltr">${signinLog.domainName} [${signinLog.ipAddress}]</span>
                                                </li>
                                            </ul>

                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                </c:if>
            </div>

            <c:if test="${not empty nlist}">
                <div class="col-sm-6 col-sm-offset-1 note-sync-container">
                    <h1><spring:message code="note.not.read" /></h1>
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
                                <td><a href="${cp}/panel/note/details/${myvar.id}"><spring:message code="all.details"/></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>

    </div>
</section>
