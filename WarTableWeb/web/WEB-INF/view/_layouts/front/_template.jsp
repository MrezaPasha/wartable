<%-- 
    Document   : SiteTemplate
    Created on : Apr 15, 2015, 11:57:56 AM
    Author     : masoud
--%>
<!DOCTYPE html>
<html lang="en" class="no-js">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
    <c:set var="rl1" value="${rl1}" scope="request"/>
    <c:set var="rl2" value="${rl2}" scope="request"/>
    <c:set var="rl3" value="${rl3}" scope="request"/>
    <tiles:importAttribute name="title"/>
    <spring:message  code="${title}" var="title"/>
    <head>
        <meta charset="utf-8">
        <title>${title}</title>
        <meta name="keywords" content="" />
        <meta name="description" content="">
        <meta name="author" content="<spring:message  code="sadr.title"/>">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%--<link rel="shortcut icon" href="${cp}/resources-f/images/eam.png" alt="">--%>
        <link href="${cp}/resources-f/bootstrap/css/bootstrap-rtl.css" rel="stylesheet">

        <c:set var="fontVar" value='${sessionScope.font}'/>
        <c:if test="${fontVar==null}">
            <c:set var="fontVar" value='iransans'/>
        </c:if>
        <link rel="stylesheet" href="${cp}/resources/css/font-${fontVar}.css?r=${rl1}">
        <link rel="stylesheet" href="${cp}/resources/css/custom.css?r=${rl1}">
        <link rel="stylesheet" href="${cp}/resources-f/css/custom-f.css?r=${rl1}">

        <script type="text/javascript" src="${cp}/resources/plugins/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="${cp}/resources-f/bootstrap/js/bootstrap-rtl.min.js"></script>
        <script type="text/javascript" src="${cp}/resources/js/custom.js?r=${rl1}"></script>
        <script type="text/javascript" src="${cp}/resources/js/utils.js?r=${rl2}"></script>
        <script type="text/javascript" src="${cp}/resources-f/js/custom-f.js?r=${rl1}"></script>

    </head>
    <body class="no-trans">
        <input id="cpId" value="${cp}" type="hidden"/>
        <div class="scrollToTop circle"><i class="icon-up-open-big"></i></div>
        <div class="page-wrapper">
            <tiles:insertAttribute name="header" />
            <tiles:insertAttribute name="body" />
            <tiles:insertAttribute name="footer" />
        </div>
        <script>
            jQuery(document).ready(function () {
                CustomFrontJs.init();
                CustomJs.init();
            });
        </script>
    </body>
</html>
