<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<div class=" container">
    <div class="page-header">
        <h3 class="cred-header">
            <span class="cred-title">پیکربندی وب</span>
        </h3>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tabbable propertor-panel">
                <ul id="myTab4" class="nav nav-tabs tab-padding tab-space-3 tab-blue">
                    <li class="active li-right">
                        <a href="#panel_tab3_general" data-toggle="tab">
                            عمومی
                        </a>
                    </li>
                    <li class="li-right">
                        <a href="#panel_tab3_user" data-toggle="tab">
                            کاربری
                        </a>
                    </li>
                    <li class="li-right">
                        <a href="#panel_tab3_load" data-toggle="tab">
                            آستانه
                        </a>
                    </li>
                    <li class="li-right">
                        <a href="#panel_tab3_irror" data-toggle="tab">
                            مدیریت خطا
                        </a>
                    </li>
                    <li class="li-right">
                        <a href="#panel_tab3_service" data-toggle="tab">
                            سرویس
                        </a>
                    </li>

                    <li style="float: left">
                        <a href="${cp}/panel/propertor/web/reset" class="btn btn-default btn-block">
                            بازنشانی به تنظیمات اولیه
                        </a>
                    </li>
                </ul>
                <div class="tab-content min-height">
                    <div class="tab-pane in active" id="panel_tab3_general">
                        <c:set var="olist" value="${glist}" scope="request"/>
                        <jsp:include page="propertor-tabs.jsp"/>
                    </div>
                    <div class="tab-pane" id="panel_tab3_user">
                        <c:set var="olist" value="${ulist}" scope="request"/>
                        <jsp:include page="propertor-tabs.jsp"/>
                    </div>
                    <div class="tab-pane" id="panel_tab3_load">
                        <c:set var="olist" value="${llist}" scope="request"/>
                        <jsp:include page="propertor-tabs.jsp"/>
                    </div>
                    <div class="tab-pane" id="panel_tab3_irror">
                        <c:set var="olist" value="${ilist}" scope="request"/>
                        <jsp:include page="propertor-tabs.jsp"/>
                    </div>
                    <div class="tab-pane" id="panel_tab3_service">
                        <c:set var="olist" value="${slist}" scope="request"/>
                        <jsp:include page="propertor-tabs.jsp"/>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    jQuery(document).ready(function () {
        CustomPanelJs.initPropertor("web");
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
