<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<div class="container padding-b-10">
    <div class="well well-act" >
        <button type="submit" form="form" class="btn btn-p-create btn-p-act btn-animated btn-animated-right">
            <spring:message code="all.register"/>
            <i class="clip-plus-circle"></i>
        </button>
    </div>
    <div class="tab-content">
        <form id="form" accept-charset="UTF-8" action="" method="POST">   
            <div class="row">
                <div class="form-group col-sm-6">
                    <label for="path" cssClass="control-label" >
                        مسیر پکیج
                        <span class="symbol required"></span>
                    </label>
                    <input name="path" value="" class="form-control string-required"/>
                </div>
                <div class="form-group col-sm-6">
                    <label for="name" cssClass="control-label" >
                        نام پکیج
                        <span class="symbol required"></span>
                    </label>
                    <input name="name" value="" class="form-control string-required"/>
                </div>
            </div>
        </form>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
