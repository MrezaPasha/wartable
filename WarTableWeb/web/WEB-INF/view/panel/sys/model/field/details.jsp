<%--/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>

<div class="container" style="min-height: 300px;">
    <h3 class="cred-header">
        <span class="cred-title">
            جزئیات فیلد
        </span>
    </h3>
    <div class="well well-act">
        <ul class="disabled-li margin-bottom xs-center-ul-3">
            <li class="tooltips " data-original-title="<spring:message code="all.list"/>" data-placement="bottom">
                <a  href="${cp}/panel/model/fields/${field.model.id}" class="btn btn-default btn-block btn-act">
                    <i class="clip-forrst light"></i>
                    <span class="title"></span>
                </a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="panel-body">
            <div class="panel-group accordion-custom accordion-teal">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle">
                                <i class="icon-arrow"></i>
                                جزئیات فیلد
                            </a></h4>
                    </div>
                    <div class="panel-collapse collapse in content-details">
                        <div class="panel-body">
                            <div class="col-sm-8 no-border">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moTitle"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moTitle}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moType"/></label>
                                </div>
                                <div class="col-sm-4 content-item iransans-standard">${field.moType}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moPreviousTitle"/></label>
                                </div>
                                <div class="col-sm-4 content-item iransans-standard">${field.moPreviousTitle}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moMinSize"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moMinSize}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moMaxSize"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moMaxSize}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.isMoNullable"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.isMoNullableY.title}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moModifier"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moModifier}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.isMoRefreshed"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.isMoRefreshedY.title}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moAnnotations"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moAnnotations}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moKey"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moKey}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moExtra"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moExtra}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moDefaultValue"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moDefaultValue}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moDataRelation"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moDataRelation}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.moDataRelationDes"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.moDataRelationDes}</div>
                            </div> 
                        </div>
                    </div>
                    <div class="panel-collapse collapse in content-details">
                        <div class="panel-body">
                            <div class="col-sm-8 no-border">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.status"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.status.title}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.isBidirectional"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.isBidirectionalY.title}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.isEncrypted"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.isEncryptedY.title}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.model"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.model.tableName}</div>
                            </div> 
                        </div>
                    </div>
                    <div class="panel-collapse collapse in content-details">
                        <div class="panel-body">
                            <div class="col-sm-8 no-border">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbTitle"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.dbTitle}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbType"/></label>
                                </div>
                                <div class="col-sm-4 content-item iransans-standard">${field.dbType}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbSize"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.dbSize}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.isDbNullable"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.isDbNullableY.title}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbDefaultValue"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.dbDefaultValue}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbExtra"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.dbExtra}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbKey"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.dbKey}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.isDbRefreshed"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.isDbRefreshedY.title}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.isDbFK"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.isDbFKY.title}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbConstraint"/></label>
                                </div>
                                <div class="col-sm-4 content-item iransans-standard">${field.dbConstraint}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbRefTable"/></label>
                                </div>
                                <div class="col-sm-4 content-item iransans-standarda">${field.dbRefTable}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbRefColumn"/></label>
                                </div>
                                <div class="col-sm-4 content-item iransans-standard">${field.dbRefColumn}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.isDbPrimary"/></label>
                                </div>
                                <div class="col-sm-4 content-item">${field.isDbPrimaryY.title}</div>
                            </div> 
                            <div class="col-sm-8">                                       
                                <div class="col-sm-4">                                       
                                    <label><spring:message code="field.dbIndex"/></label>
                                </div>
                                <div class="col-sm-4 content-item iransans-standard">${field.dbIndex}</div>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/--%>
