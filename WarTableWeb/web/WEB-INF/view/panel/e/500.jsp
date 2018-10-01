<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>

<section class="container container-error">
    <div class="row">
        <div class="col-md-8 col-md-offset-2 err-p-500-header">
            <h1>500</h1>
            <h2><spring:message code="error.500"/></h2>
            <div class="err-id"> <spring:message code="error.500.id"/>: ${errorId}</div>
        </div>
    </div>
</section>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
