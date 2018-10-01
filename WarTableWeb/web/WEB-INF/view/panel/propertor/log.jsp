<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>

<div class=" container">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title">تنظیمات پیکربندی</span>
        </h3>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tabbable propertor-panel">
                <ul id="myTab4" class="nav nav-tabs tab-padding tab-space-3 tab-blue">
                    <li class="active li-right">
                        <a href="#panel_tab3_system" data-toggle="tab">
                            سامانه
                        </a>
                    </li>
                    <li class="li-right">
                        <a href="#panel_tab3_connection" data-toggle="tab">
                            ارتباطات
                        </a>
                    </li>
                    <li style="float: left">
                        <a href="${cp}/panel/propertor/log/reset" class="btn btn-default btn-block">
                            بازنشانی
                        </a>
                    </li>
                    <li style="float: left">
                        <a href="${cp}/panel/propertor/log/update" class="btn btn-default btn-block">
                            بروزرسانی
                        </a>
                    </li>
                    <li style="float: left">
                        <a href="${cp}/panel/propertor/log/load" class="btn btn-default btn-block">
                            بارگذاری مجدد
                        </a>
                    </li>
                </ul>
                <div class="tab-content min-height">
                    <div class="tab-pane in active" id="panel_tab3_system">
                        <c:set var="olist" value="${slist}" scope="request"/>
                        <jsp:include page="propertor-tabs.jsp"/>
                    </div>
                    <div class="tab-pane" id="panel_tab3_connection">
                        <c:set var="olist" value="${clist}" scope="request"/>
                        <jsp:include page="propertor-tabs.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    jQuery(document).ready(function () {
        CustomPanelJs.initPropertor("log");
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
