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
        <input type="text" class="form-control" placeholder="<spring:message code="backup.title"/>"
               ir-search-field-name="${f_title[0]}"
               ir-search-field-tp="${f_title[1]}"
               ir-search-field-opr="${f_title[2]}"
               ir-search-field-noSp="${f_title[3]}"
               ir-search-submit-realTime
        >
    </div>
    <div class="col-xxs-12 col-xs-3 col-md-6">
        <button ir-search-submit class="btn btn-animated btn-animated-right">
            جستجو
            <i class="clip-search"></i>
        </button>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
