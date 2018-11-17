<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : list
    Created on : Jun 24, 2017, 8:25:21 AM
    Author     : dev3
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${cp}/resources/plugins/persian-datetimepicker/persian-datepicker-0.4.5.css">

<div class="container" style="min-height: 300px;">
    <div class="page-header">
        <div class="cred-header">
            <h3 class="cred-title"><spring:message code="T.p.sys.log.signin.list"/></h3>
        </div>
    </div>
    <div class="row">
        <div ir-search-id="adv1" ir-search-url="${cp}/panel/log/signin/list" ir-search-auto-submit="true"
             class="lix-container">
            <jsp:include page="list/top-search.jsp"/>
            <div class="vertical margin-b-20">
                <div class="tab-content ir-serach-table">
                    <section ir-search-template class="hidden">
                        <div ir-search-empty-body-template class="not-find">
                            موردی یافت نشد!
                        </div>
                        <div ir-search-error-body-template class="erorr-find">
                            خطا در جستجو!
                        </div>
                        <div ir-search-paging-size-template class="row ir-search-paging">
                            <div class="col-sm-6"></div>
                            <div class="drop-down-page-size col-sm-6">
                                <div class="float-left">
                                    <span>تعداد نمایش در صفحه:</span>
                                    <select class="form-control drop-down-num">
                                        <option value="2">۲</option>
                                        <option value="5">۵</option>
                                        <option value="10" selected="select">۱۰</option>
                                        <option value="15">۱۵</option>
                                        <option value="20">۲۰</option>
                                        <option value="50">۵۰</option>
                                        <option value="-1">همه</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div ir-search-body-template="table" class="margin-b-10 ir-search-list table-responsive">
                            <table>
                                <thead>
                                <tr>
                                    <c:forEach items="${cols}" var="col">
                                        <th ir-search-order-col="${col.value[0]}">${col.value[1]}</th>
                                    </c:forEach>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ir-search-body-item>
                                    <c:forEach items="${cols}" var="col">
                                        <td> @{${col.key}}</td>
                                    </c:forEach>
                                    <td class="center">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div ir-search-paging-template class="row">
                            <div ir-search-paginig-info class="col-sm-6 paging-info">
                                <span>نمایش </span>
                                <span ir-search-paging-info-from>-1</span>
                                <span>تا</span>
                                <span ir-search-paging-info-to>-1</span>
                                <span> (کل: <span ir-search-paging-info-count>-1</span>)</span>
                            </div>
                            <div class="nav-pagination col-sm-6">
                                <ul class="pagination">
                                    <li ir-search-paging-prev ir-search-paging-value=""><span
                                            class="number">&laquo;</span></li>
                                    <li ir-search-paging-item ir-search-paging-value="-1"><span class="number">-1</span>
                                    </li>
                                    <li ir-search-paging-next ir-search-paging-value=""><span
                                            class="number">&raquo;</span></li>
                                </ul>
                            </div>
                        </div>
                    </section>
                    <div ir-search-container>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${cp}/resources/plugins/persian-datetimepicker/persian-date-0.1.8.js"></script>
<script type="text/javascript" src="${cp}/resources/plugins/persian-datetimepicker/persian-datepicker-0.4.5.js"></script>
<script>
    jQuery(document).ready(function () {
        CustomJs.initPersianDateTime();
        pluginFillBody = function (i, value, newItem) {
            return newItem;
        };
        CustomJs.initAjaxSearch();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
