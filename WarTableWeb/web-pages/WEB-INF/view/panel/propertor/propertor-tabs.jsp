<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page session="false" %>
<div class="propertor-container">
    <c:forEach items="${olist}" var="myvar">
        <c:if test="${myvar.seprator}">
            <div class="section-title">
                    ${myvar.propertor.section.message}
            </div>
        </c:if>
        <section class="section-item row">
            <label class="control-label col-xs-12 col-sm-6">
                    ${myvar.propertor.message}
                <c:if test="${myvar.propertor.isNeedRedeploy}">
                    <i title="<spring:message code="propertor.item.need.redeploy"/>" class="clip-spinner-5 need-redeploy"></i>
                </c:if>
            </label>
            <div class="col-xs-12 col-sm-6">
                <c:choose>
                    <c:when test="${myvar.propertor.type=='OnOff'}">
                        <input type="checkbox" name="${myvar.propertor.key}" id="prop_${myvar.propertor.id}" data-id="${myvar.propertor.id}" data-type="${myvar.propertor.type}"
                               <c:if test="${myvar.isOn}">checked</c:if> data-toggle="toggle"/>
                    </c:when>
                    <c:when test="${myvar.propertor.type=='Integer'}">
                        <input type="number" name="${myvar.propertor.key}" value="${myvar.value}" data-id="${myvar.propertor.id}" data-type="${myvar.propertor.type}"/>
                    </c:when>
                    <c:when test="${myvar.propertor.type=='StringBig'}">
                        <textarea name="${myvar.propertor.key}" data-id="${myvar.propertor.id}" data-type="${myvar.propertor.type}">${myvar.value}</textarea>
                    </c:when>
                    <c:when test="${myvar.propertor.type=='Password'}">
                        <input type="password" name="${myvar.propertor.key}" value="${myvar.value}" data-id="${myvar.propertor.id}" data-type="${myvar.propertor.type}"/>
                    </c:when>
                    <c:when test="${myvar.propertor.type=='TtVariable'}">
                        <select id="select2_${myvar.propertor.id}" name="${myvar.propertor.key}" data-id="${myvar.propertor.id}" data-type="${myvar.propertor.type}">
                            <c:forEach var="op" items="${myvar.propertor.ttValues}">
                                <option value="${op.key}"
                                        <c:if test="${op.key == myvar.value}">selected</c:if> >${op.value}</option>
                            </c:forEach>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="${myvar.propertor.key}" value="${myvar.value}" data-id="${myvar.propertor.id}" data-type="${myvar.propertor.type}"/>
                    </c:otherwise>
                </c:choose>
                <i i-propertor-default="${myvar.propertor.defaultValueInForm}" i-propertor-name="${myvar.propertor.key}" title="بازگشت به پیش فرض: ${myvar.propertor.defaultValue}" class="clip-rotate restore-default"></i>
            </div>
        </section>
    </c:forEach>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
