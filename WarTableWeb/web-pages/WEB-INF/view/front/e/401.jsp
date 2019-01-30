<%-- 
    Document   : index
    Created on : Apr 17, 2015, 12:59:54 AM
    Author     : masoud
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="background-image-blur"></div>
<div class="container scroll-container">
    <div class="col-xs-12 ">
        <section class="enter-block white-bg-transparent">
            <h1 class="page-title"><span class="text-danger">401</span></h1>
            <h3><spring:message code="error.401"/></h3>
            <% if (session.getAttribute("sUser") == null) { %>
            <a href="${cp}/signin" class="btn btn-group btn-default-transparent btn-animated btn-animated-right  btn-lg" style="float:center">
                <spring:message code="user.loginToYourAccount"/>
                <i class="fa  fa-lock"></i>
            </a>
            <% }%>
        </section>
    </div>
</div>
