<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class=" container" >
    <div class="row" style="margin-top:50px ">
        <c:forEach items="${list}" var="myvar">
            <div class="col-sm-4" style="margin: 10px;">
                <a href="${cp}/panel/setting${myvar[1]}" class="btn btn-default btn-block">${myvar[0]}</a>
            </div>
        </c:forEach>
    </div>
    <div class="row" style="margin-top:50px ">
        <c:forEach items="${plist}" var="myvar">
            <div class="col-sm-4" style="margin: 10px;">
                <a href="${cp}/panel/setting/prj${myvar[1]}" class="btn btn-default btn-block">${myvar[0]}</a>
            </div>
        </c:forEach>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
