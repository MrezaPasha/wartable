<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : list
    Created on : Jun 24, 2017, 8:25:21 AM
    Author     : dev3
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container c-list-container">
    <div class="page-header">
        <div class="cred-header">

            <h3 class="cred-title"><spring:message code="room.list"/></h3>
            <a href="${_url}/create" class="btn btn-p-create btn-animated btn-animated-right">
                <i class="clip-plus-circle light"></i>
                <span class="title"><spring:message code="room.create"/></span>
            </a>
        </div>
    </div>
    <div class="row">
        <div class="c-search-container"
             i-search-opt-id="${searchId}"
             i-search-opt-url="${_url}/list"
             i-search-opt-pageSize="10"
             i-search-opt-toggleColumns="true"
        >
            <jsp:include page="/WEB-INF/view/_layouts/panel/list-top-search.jsp"/>
            <div class="vertical margin-b-20">
                <div class="tab-content c-search-table">
                    <section i-search-template class="">
                        <div class="row c-search-paging">
                            <div i-search-message-text class="col-sm-6 c-message-body">
                                <span></span>
                            </div>
                            <div i-search-template-pageSize class="drop-down-page-size col-sm-6">
                                <div class="float-left">
                                    <span>تعداد نمایش در صفحه:</span>
                                    <select class="form-control drop-down-num">
                                        <option value="2">۲</option>
                                        <option value="5">۵</option>
                                        <option value="10">۱۰</option>
                                        <option value="15">۱۵</option>
                                        <option value="20">۲۰</option>
                                        <option value="50">۵۰</option>
                                        <option value="-1">همه</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div i-search-template-body class="margin-b-10 c-search-list table-responsive">
                            <table>
                                <thead>
                                <tr style="display: none">
                                    <c:forEach items="${cols}" var="col">
                                        <th i-search-body-col='${col.value[0]}'><a href="javascript:void(0)">${col.value[1]}</a></th>
                                    </c:forEach>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody i-search-template-body-list>
                                <tr style="display: none">
                                    <c:forEach items="${cols}" var="col">
                                        <td> @{${col.key}}</td>
                                    </c:forEach>
                                    <td class="center">
                                        <div class="r-button">
                                            <a
                                                    href="${_url}/user/list/@{id}"
                                                    class="btn btn-sm btn-l-assign" data-placement="top"
                                                    title="<spring:message code="T.p.service.room.user.list"/> ">
                                                <i class="fa fa-user"></i>
                                            </a>
                                            <a
                                                    href="${_url}/map/list/@{id}"
                                                    class="btn btn-sm btn-l-complete" data-placement="top"
                                                    title="<spring:message code="T.p.service.room.map.list"/> ">
                                                <i class="clip-map"></i>
                                            </a>
                                            <a
                                                    href="${_url}/chat/conversation/@{id}"
                                                    class="btn btn-sm btn-l-design" data-placement="top"
                                                    title="<spring:message code="T.p.service.room.chat.list"/> ">
                                                <i class="clip-bubbles-2"></i>
                                            </a>
                                            <a
                                                    href="${_url}/edit/@{id}"
                                                    class="btn btn-sm btn-l-edit" data-placement="top"
                                                    title="<spring:message code="all.edit"/> ">
                                                <i class="fa fa-edit"></i>
                                            </a>
                                            <a href="javascript:void(0)" class="btn btn-sm btn-bricky"
                                               i-search-body-item-trash-id="@{id}"
                                               i-search-body-item-trash-title="@{name}"
                                               title="<spring:message code="all.delete"/> ">
                                                <i class="fa fa-trash-o"></i>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div i-search-template-pagination class="row">
                            <div class="col-sm-6 paging-info">
                                <span>نمایش </span>
                                <span i-search-pagination-info-from>-1</span>
                                <span>تا</span>
                                <span i-search-pagination-info-to>-1</span>
                                <span> (کل: <span i-search-pagination-info-count>-1</span>)</span>
                            </div>
                            <div class="nav-pagination col-sm-6">
                                <ul class="pagination">
                                    <li i-search-pagination-prev i-search-pagination-value=""><a href="javascript:void(0)" class="number">&laquo;</a></li>
                                    <li i-search-pagination-item i-search-pagination-value="-1"><a href="javascript:void(0)" class="number">-1</a></li>
                                    <li i-search-pagination-next i-search-pagination-value=""><a href="javascript:void(0)" class="number">&raquo;</a></li>
                                </ul>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    jQuery(document).ready(function () {
        pluginFillBody = function (i, value, newItem) {
            return newItem;
        };
        CustomJs.initAjaxSearch();
    });
</script>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
