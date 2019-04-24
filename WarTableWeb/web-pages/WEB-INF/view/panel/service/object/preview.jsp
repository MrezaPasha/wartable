<%--
    Document   : edit
    Created on : Jun 2, 2016, 10:54:25 AM
    Author     : dev1
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>

<div class="container">
    <h3 class="cred-header">
        <span class="cred-title"><spring:message code="T.p.service.object.preview"/></span>
        <span class="cred-name">[${object.name}]</span>
    </h3>
    <div class="tabbable">
        <div class="well well-act">
            <ul class="disabled-li margin-bottom xs-center-ul-4">
                <li class="tooltips " data-original-title="<spring:message code="all.list"/>" data-placement="bottom">
                    <a href="${_url}/list" class="btn btn-default btn-block btn-act">
                        <i class="clip-list-2 light"></i>
                        <span class="title"></span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="tab-content three-container"
             i-three-mode="2"
             i-three-name="${object.fileName}"
             i-three-object="${objectPath}"

        >
        </div>
    </div>
</div>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/three.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/OBJLoader.js"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/3d/TrackballControls.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/MTLLoader.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/dat.gui.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/LoaderSupport.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/OBJLoader2.js"></script>

<script type="text/javascript" src="${cp}/resources-p/plugins/3d/inflate.min.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/FBXLoader.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/OrbitControls.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/WebGL.js"></script>
<script type="text/javascript" src="${cp}/resources-p/plugins/3d/stats.min.js"></script>

<script>

    jQuery(document).ready(function () {
        CustomPanelJs.init3DxLoader();
    });

</script>
