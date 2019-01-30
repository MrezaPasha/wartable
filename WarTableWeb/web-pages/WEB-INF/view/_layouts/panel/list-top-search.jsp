<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : top-search
    Created on : Jul 2, 2017, 12:35:04 PM
    Author     : dev3
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div i-search-submit-opt class="modal fade" id="ixportSetting" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog note-sync-modal">
        <div class="modal-content">
            <div class="modal-header">
                <spring:message code="ixport.setting"/>
            </div>
            <div class="modal-body">
                <div class="checkbox">
                    <label for="isEncrypted">
                        <input i-search-submit-opt-encrypt type="checkbox" id="isEncrypted" name="isEncrypted"/>
                        <spring:message code="ixport.isEncrypted"/>
                    </label>
                </div>
                <div class="checkbox">
                    <label for="isSigned">
                        <input i-search-submit-opt-sign type="checkbox" id="isSigned" name="isSigned"/>
                        <spring:message code="ixport.isSigned"/>
                    </label>
                </div>
                <div class="select">
                    <label>
                        <spring:message code="ixport.securityTag" />
                    <select i-search-submit-opt-security>
                        <c:forEach var="tg" items="${securityTags}">
                            <option value="${tg.ordinal}">${tg.title}</option>
                        </c:forEach>
                    </select>

                    </label>
                </div>

            </div>
            <div class="modal-footer">
                <button i-search-submit-btn="excel" class="btn btn-primary">
                    <spring:message code="ixport.do.export"/>
                </button>
            </div>
        </div>
    </div>
</div>

<div class="row search-lix">
    <c:forEach items="${searchInputs}" var="myvar">
        <div class="col-xxs-12 col-xs-3 col-md-2">
            <input type="text" class="form-control" placeholder="${myvar[1]}"
                   i-search-field-id="${myvar[0]}"
                   i-search-field-param=${myvar[2]}
                           i-search-field-submitOnChange
            >
        </div>
    </c:forEach>

    <div i-search-submit class="col-xxs-12 col-xs-12 col-md-6">
        <div class="btn-group">
            <button i-search-submit-btn class="btn btn-animated btn-animated-right">
                <spring:message code="all.search"/>
                <i class="clip-search"></i>
            </button>
            <div class="btn-group">
                <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle">
                    <spring:message code="ixport.export"/> <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" role="menu">
                    <li role="presentation">
                        <a href="#ixportSetting" data-toggle="modal" tabindex="-1" role="menuitem">
                            <spring:message code="ixport.excel"/>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
