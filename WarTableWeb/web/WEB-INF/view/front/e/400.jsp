<%-- 
    Document   : index
    Created on : Apr 17, 2015, 12:59:54 AM
    Author     : masoud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<div class="error-container">
    <section class="jumbotron" >
        <h1 class="page-title"><span class="text-danger">400</span></h1>
        <h3><spring:message code="error.400"/></h3>
    </section>
</div>
<div class="footer-error"></div>

