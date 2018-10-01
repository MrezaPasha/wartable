<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : list
    Created on : Jun 24, 2017, 8:25:21 AM
    Author     : dev3
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container" style="min-height: 300px;">
    <div class="page-header">
        <div class="cred-header">

            <h3 class="cred-title"><spring:message code="user.list" /> </h3>
            <a href="${cp}/panel/user/create" class="btn btn-p-create btn-animated btn-animated-right">
                <i class="clip-plus-circle light"></i>
                <span class="title"><spring:message code="user.create" /></span>
            </a>
            <a href="${cp}/panel/user/list/inactive" class="btn btn-p-choice btn-animated btn-animated-right">
                <i class="clip-deviantart light"></i>
                <span class="title"><spring:message code="user.list.inactive" /></span>
            </a>
            <a href="${cp}/panel/user/list/online" class="btn btn-p-createandNew btn-animated btn-animated-right">
                <i class="clip-data light"></i>
                <span class="title"><spring:message code="user.list.online" /></span>
            </a>
        </div>
    </div>
    <div class="row">
        <div ir-search-id="adv1" ir-search-url="${cp}/panel/user/list" ir-search-auto-submit="true"
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
                                        <div class="r-button">
                                            <a href="@{plugin_masterLink}@{id}"
                                               class="btn btn-sm tooltips @{plugin_masterClass}" data-placement="top"
                                               data-original-title="<spring:message code="user.level"/>">
                                                <i class="fa fa-shield"></i>
                                            </a>
                                            <a
                                               href="${cp}/panel/user/edit/@{id}"
                                               class="btn btn-sm btn-l-edit tooltips " data-placement="top"
                                               data-original-title="<spring:message code="all.edit"/> ">
                                                <i class="fa fa-edit"></i>
                                            </a>
                                            <a ir-search-action-trash="@{id}" data-title="@{fullName}" data-back=""
                                               href="javascript:void(0)" class="btn btn-sm btn-bricky tooltips"
                                               data-placement="top"
                                               data-original-title="<spring:message code="all.delete"/> ">
                                                <i class="fa fa-trash-o"></i>
                                            </a>
                                        </div>
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
<script>
    jQuery(document).ready(function () {

        pluginFillBody = function (i, value, newItem) {
            if (value.isMaster) {
                newItem = newItem.replace(new RegExp("@{plugin_masterClass}", "g"), " btn-l-level");
                newItem = newItem.replace(new RegExp("@{plugin_masterLink}", "g"), "${cp}/panel/user/access/list/");
            } else {
                newItem = newItem.replace(new RegExp("@{plugin_masterClass}", "g"), "btn-light-grey btn-disable");
                newItem = newItem.replace(new RegExp("@{plugin_masterLink}", "g"), "javascript:void(0)");
            }
            return newItem;
        };
        CustomJs.initAjaxSearch();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
