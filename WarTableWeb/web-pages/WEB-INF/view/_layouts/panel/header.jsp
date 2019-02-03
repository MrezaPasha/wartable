<%-- 
    Document   : header
    Created on : Apr 15, 2015, 12:00:49 PM
    Author     : masoud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<span id="n-count-id" class="hidden">${fn:length(notice)}</span>

<c:forEach items="${notice}" var="note" varStatus="loop">
    <div id="notice-id-${loop.index}" class="alert-notice alert  ${note.cssClass}" style="min-width: 300px; left:  10px; top: -100px; font-size: 12px; position: absolute;  z-index: 2000; opacity: 0.5;">
        <a href="javascript:void(0)" class="close close-2" data-dismiss="alert" aria-label="close" style="margin-right: 10px;">&times;</a>
            ${note.message}
    </div>
</c:forEach>


<c:if test="${signinNotice!=null}">
    <div class="modal fade" id="signinModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <jsp:include page="signin-notice-modal.jsp"/>
    </div>
    <script type="text/javascript">
        $(window).on('load', function () {
            $('#signinModal').modal({
                backdrop: 'static',
                keyboard: false
            });

        });
    </script>
</c:if>
<c:if test="${inlist!=null}">
    <div class="modal fade" id="irrorNotify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <jsp:include page="irror-notify-modal.jsp"/>
    </div>
    <script type="text/javascript">
        $(window).on('load', function () {
            $('#irrorNotify').modal();

        });
    </script>
</c:if>
<div class="modal fade" id="noteSyncModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <jsp:include page="note-sync-modal.jsp"/>
</div>
<div class="navbar navbar-inverse navbar-fixed-top" style="position: absolute">
    <div class="container">
        <div class="navbar-header">
            <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle hidden-navbar-toggle" type="button">
                <span class="clip-list-2"></span>
            </button>
            <p style="font-size: 18px; margin-top: 8px">

            </p>
        </div>
        <div class="navbar-tools">
            <ul class="nav navbar-right">
                <li class="dropdown current-user">
                    <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" data-close-others="true" href="javascript:void(0)">
                        <img src="" class="circle-img" alt="">

                        <c:choose>
                            <c:when test="${not empty sUser}">
                                <span i-user-id="${sUser.id}" class="username">${sUser.fullName}</span>
                            </c:when>
                            <c:otherwise>
                                <span><spring:message code="user.username"/></span>
                            </c:otherwise>
                        </c:choose>
                        <i class="clip-chevron-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <c:choose>
                            <c:when test="${not empty sUser}">
                                <c:if test="${suAdLoginCode!=null
                                          and suAdLoginCode>0
                                          and sUser.superAdminLogingIn
                                          and suAdLoginCode==sUser.superAdminLogingId}">
                                    <li>
                                        <a href="${cp}/panel/user/signout/as">
                                            <i class="clip-exit"></i>
                                            <spring:message code="all.return"/>
                                        </a>
                                    </li>
                                </c:if>
                                <li>
                                    <a href="${cp}/panel/user/your-edit">
                                        <i class="clip-stack"></i>
                                        <spring:message code="user.profile"/>
                                    </a>
                                </li>
                                <li>
                                    <a href="${cp}/signout">
                                        <i class="clip-exit"></i>
                                        <spring:message code="all.exit"/>
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${cp}/system/user/signin">
                                        <i class="clip-enter"></i>
                                        <spring:message code="all.signin"/>
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </li>
                <li>
                    <a class="sb-toggle hidden-sm hidden-md hidden-lg" href="#"><i class="fa fa-indent"></i></a>
                </li>
            </ul>
        </div>
    </div>
</div>
