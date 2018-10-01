<%-- 
    Document   : SiteTemplate
    Created on : Apr 15, 2015, 11:57:56 AM
    Author     : masoud
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request"/>
<c:set var="rl1" value="${rl1}" scope="request"/>
<c:set var="rl2" value="${rl2}" scope="request"/>
<c:set var="rl3" value="${rl3}" scope="request"/>

<tiles:importAttribute name="rightMenuId"/>
<tiles:importAttribute name="title"/>
<spring:message code="${title}" var="title"/>

<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="description" content="شرکت ایده پردازان رایانش ابری سپاهان (ایراس)"/>
    <meta name="keywords" content="پنل مدیریت  میراث"/>
    <meta name="author" content="<spring:message  code="sadr.title"/>">
    <title>
        ایراس-${title}
    </title>
    <link rel="stylesheet" href="${cp}/resources/plugins/datetime-picker/content/css-date/date.css?r=${rl3}"/>
    <link rel="stylesheet" href="${cp}/resources-p/plugins/bootstrap/css/bootstrap-rtl.css?r=${rl3}"/>
    <link rel="stylesheet" href="${cp}/resources-p/css/print.css?r=${rl2}" type="text/css" media="print"/>
    <link rel="stylesheet" href="${cp}/resources-p/css/style-p.css?r=${rl2}"/>
    <link rel="stylesheet" href="${cp}/resources-p/plugins/select2/select2.css?r=${rl3}">
    <link rel="stylesheet" href="${cp}/resources-p/plugins/font-awesome/css/font-awesome.min.css?r=${rl3}"/>
    <link rel="stylesheet" href="${cp}/resources-p/fonts/style.css?r=${rl2}"/>
    <link rel="stylesheet" href="${cp}/resources-p/css/rtl-version.css?r=${rl2}"/>
    <link rel="stylesheet" href="${cp}/resources-p/css/theme_light.css?r=${rl2}" type="text/css" id="skin_color"/>
    <link rel="stylesheet" href="${cp}/resources/css/custom.css?r=${rl1}"/>
    <link rel="stylesheet" href="${cp}/resources-p/css/custom-p.css?r=${rl1}"/>

    <script type="text/javascript" src="${cp}/resources-p/plugins/jQuery-lib/2.0.3/jquery.min.js?r=${rl3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js?r=${rl3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/bootstrap/js/bootstrap.min-rtl.js?r=${rl3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js?r=${3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/iCheck/jquery.icheck.min.js?r=${rl3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/perfect-scrollbar/src/jquery.mousewheel.js?r=${rl3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/perfect-scrollbar/src/perfect-scrollbar-rtl.js?r=${rl3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/less/less-1.5.0.min.js?r=${rl3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/jquery-cookie/jquery.cookie.js?r=${rl3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/js/main.js?r=${rl2}"></script>
    <script type="text/javascript" src="${cp}/resources/js/custom.js?r=${rl1}"></script>
    <script type="text/javascript" src="${cp}/resources/js/utils.js?r=${rl2}"></script>
    <script type="text/javascript" src="${cp}/resources-p/js/custom-p.js?r=${rl1}"></script>
    <script>
        jQuery(document).ready(function () {
            CustomJs.initCloseBrowser();
            Main.init();
            CustomPanelJs.init();
            CustomJs.init();
        });
    </script>
</head>
<body class='rtl <c:if test="${cookie.navToggle.value==1}">navigation-small</c:if>'>
<input id="cpId" value="${cp}" type="hidden"/>
<tiles:insertAttribute name="header"/>

<div class="main-container">
    <input id="rightMenuIdHandler" value="${rightMenuId}" type="hidden">
    <tiles:insertAttribute name="rightMenu"/>
    <div class="main-content">
        <div id="titleHandler" title="${title}"></div>
        <tiles:insertAttribute name="body"/>
    </div>
</div>
<tiles:insertAttribute name="footer"/>
</body>
</html>

