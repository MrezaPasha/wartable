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


<tiles:importAttribute name="rightMenuId"/>
<tiles:importAttribute name="title"/>
<spring:message code="${title}" var="title"/>

<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="description" content="صدر"/>
    <meta name="keywords" content="پنل مدیریت  صدر"/>
    <meta name="author" content="<spring:message  code="sadr.title"/>">
    <title>
        صدر-${title}
    </title>

    <c:set var="fontVar" value='${sessionScope.font}'/>
    <c:if test="${fontVar==null}">
        <c:set var="fontVar" value='iransans'/>
    </c:if>
    <link rel="stylesheet" href="${cp}/resources/css/font-${fontVar}.css">
    <c:set var="styleVar" value='${sessionScope.style}'/>
    <c:if test="${styleVar==null}">
        <c:set var="styleVar" value='light'/>
    </c:if>
    <link rel="stylesheet" href="${cp}/resources-p/css/theme_${styleVar}.css" type="text/css" id="skin_color"/>
    <link rel="stylesheet" href="${cp}/resources/plugins/datetime-picker/content/css-date/date.css"/>
    <link rel="stylesheet" href="${cp}/resources-p/plugins/bootstrap/css/bootstrap-rtl.css"/>
    <link rel="stylesheet" href="${cp}/resources-p/css/print.css" type="text/css" media="print"/>
    <link rel="stylesheet" href="${cp}/resources-p/css/style-p.css"/>
    <link rel="stylesheet" href="${cp}/resources-p/plugins/select2/select2.css">
    <link rel="stylesheet" href="${cp}/resources-p/plugins/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${cp}/resources-p/fonts/style.css"/>
    <link rel="stylesheet" href="${cp}/resources-p/css/rtl-version.css"/>
    <link rel="stylesheet" href="${cp}/resources/css/custom.css"/>
    <link rel="stylesheet" href="${cp}/resources-p/css/custom-p.css"/>

    <script type="text/javascript" src="${cp}/resources-p/plugins/jQuery-lib/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/bootstrap/js/bootstrap.min-rtl.js"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js?r=${3}"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/iCheck/jquery.icheck.min.js"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/perfect-scrollbar/src/jquery.mousewheel.js"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/perfect-scrollbar/src/perfect-scrollbar-rtl.js"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/less/less-1.5.0.min.js"></script>
    <script type="text/javascript" src="${cp}/resources-p/plugins/jquery-cookie/jquery.cookie.js"></script>
    <script type="text/javascript" src="${cp}/resources-p/js/main.js"></script>
    <script type="text/javascript" src="${cp}/resources/js/custom.js"></script>
    <script type="text/javascript" src="${cp}/resources/js/utils.js"></script>
    <script type="text/javascript" src="${cp}/resources-p/js/custom-p.js"></script>
    <script>
        jQuery(document).ready(function () {
            // CustomJs.initCloseBrowser();
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
