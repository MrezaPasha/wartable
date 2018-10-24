<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%--
    Document   : top-search
    Created on : Jul 2, 2017, 12:35:04 PM
    Author     : dev3
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<div class="row search-lix">
    <div class="col-xxs-12 col-xs-3 col-md-2">
        <input type="text" class="form-control" placeholder="<spring:message code="user.top.search.username"/>"
               ir-search-field-name="${f_username[0]}"
               ir-search-field-tp="${f_username[1]}"
               ir-search-field-opr="${f_username[2]}"
               ir-search-field-noSp="${f_username[3]}"
        >
    </div>
    <div class="col-xxs-12 col-xs-3 col-md-2">
        <input type="text" class="form-control" placeholder="<spring:message code="user.top.search.firstName"/>"
               ir-search-field-name="${f_firstName[0]}"
               ir-search-field-tp="${f_firstName[1]}"
               ir-search-field-opr="${f_firstName[2]}"
               ir-search-field-noSp="${f_firstName[3]}"
        >
    </div>
    <div class="col-xxs-12 col-xs-3 col-md-2">
        <input type="text" class="form-control" placeholder="<spring:message code="user.top.search.lastName"/>"
               ir-search-field-name="${f_lastName[0]}"
               ir-search-field-tp="${f_lastName[1]}"
               ir-search-field-opr="${f_lastName[2]}"
               ir-search-field-noSp="${f_lastName[3]}"
        >
    </div>
    <div class="col-xxs-12 col-xs-12 col-md-6">
        <button ir-search-submit="export"
                class="btn btn-p-export btn-animated btn-animated-right">
            <spring:message code="all.export" />
            <i class="clip-file-excel"></i>
        </button>
        <button ir-search-submit class="btn btn-animated btn-animated-right">
            <spring:message code="all.search" />
            <i class="clip-search"></i>
        </button>

    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
