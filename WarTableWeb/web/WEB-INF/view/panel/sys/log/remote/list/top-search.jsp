<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<div class="row search-lix">
    <div class="col-xxs-12 col-xs-3 col-md-2">
        <input type="text" class="form-control" placeholder="<spring:message code="log.search.userId"/>"
               ir-search-field-name="${f_userId[0]}"
               ir-search-field-tp="${f_userId[1]}"
               ir-search-field-opr="${f_userId[2]}"
               ir-search-field-noSp="${f_userId[3]}"
               ir-search-submit-realTime
        >
    </div>
    <div class="col-xxs-12 col-xs-3 col-md-2">
        <input type="text" class="form-control persian-dateTime-format" data-initial-value="false" autocomplete="off" placeholder="<spring:message code="log.search.from.date"/>"
               ir-search-field-name="${f_fromDate[0]}"
               ir-search-field-tp="${f_fromDate[1]}"
               ir-search-field-opr="${f_fromDate[2]}"
               ir-search-field-noSp="${f_fromDate[3]}"
               ir-search-submit-realTime
        >
    </div>
    <div class="col-xxs-12 col-xs-3 col-md-2">
        <input type="text" class="form-control persian-dateTime-format" data-initial-value="false" autocomplete="off" placeholder="<spring:message code="log.search.to.date"/>"
               ir-search-field-name="${f_toDate[0]}"
               ir-search-field-tp="${f_toDate[1]}"
               ir-search-field-opr="${f_toDate[2]}"
               ir-search-field-noSp="${f_toDate[3]}"
               ir-search-submit-realTime
        >
    </div>

    <div class="col-xxs-12 col-xs-4 col-md-3">
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
