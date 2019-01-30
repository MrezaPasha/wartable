<%-- 
    Document   : modal-select-pricing
    Created on : Sep 4, 2017, 12:31:56 PM
    Author     : dev3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            خوش آمدید
        </div>
        <div class="modal-body">
            ${signinNotice}
        </div>
        <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-success"> موافق هستم</button>
            <a href="${cp}/signout" class="btn btn-warning"> مخالف هستم</a>
        </div>
    </div>
</div>
