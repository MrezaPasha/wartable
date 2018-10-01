<%-- 
    Document   : index
    Created on : Apr 17, 2015, 12:59:54 AM
    Author     : masoud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<div class="background-image-blur"></div>
<div class="container scroll-container">
    <div class="col-xs-12">
        <div class="center-block white-bg-transparent err-f-500-header">
            <h1>500</h1>
            <h3><spring:message code="error.500"/></h3>
            <div class="err-send-id"> <spring:message code="error.500.send.id"/></div>
            <div class="err-id"> <spring:message code="error.500.id"/>: ${errorId}</div>
        </div>
    </div>
</div>
<div class="footer-error"></div>
