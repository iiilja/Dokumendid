<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Documents</title>
    </head>
    <body>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        Create document
        <br>

        
        <form id="createDocumentForm">
            <input type="text" name="docTypeId" value="${docType.docType}" hidden>
            Document type is <input type="text" name="docTypeId" value="${docType.typeName}" disabled>
            
            <table border=1 cellpadding=2 cellspacing=1>
                <tr><td>Document id</td><td><input type="text" name="docId" value="${document.document}"  readonly="readonly"/></td></tr>
                <tr><td>Document name<b class="errorsClass" id="docName"></td><td><input type="text" name="docName" value="${document.name}" /></td></tr>
                <tr><td>Document description<b class="errorsClass" id="docDescription"></td><td><input type="text" name="docDescription" value="${document.description}"/></td></tr>
                <tr>
                    <td>Document status<b class="errorsClass" id="docStatusType"></td>
                    <td>
                        <select name="docStatusType">
                            <c:forEach var="docStatusType" items="${docStatusTypesList}" >
                                <option ${docStatusType.selected? "selected": ""} value="${docStatusType.docStatusType}" >${docStatusType.typeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Document catalog<b class="errorsClass" id="docCatalog"></td>
                    <td>
                        <select name="docCatalog">
                            <c:forEach var="docCatalog" items="${docCatalogList}" >
                                <option value="${docCatalog.docCatalog}" ${docStatusType.selected? "selected": ""}>${docCatalog.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Doc Subject<b class="errorsClass" id="subject"></td>
                    <td>
                        <input type="text" name="subjectId" id="subjectId" value="${docSubject.docSubject}" hidden/>
                        <input type="text" name="subjectType" id="subjectType" value="${docSubject.docSubjectTypeFk}" hidden/>
                        <input type="text" id="subjectName" disabled value="${docSubject.fullName}"/>
                        <input type="text" id="subjectInput"/>
                        <button type="button" onclick="searchPerson()">Search</button>
                    </td>
                </tr>
                <tr>
                    <td>Doc Subject Relation</td>
                    <td><select name="docSubjectRelationType">
                            <c:forEach var="relationType" items="${docSubjectRelationTypes}" >
                                <option value="${relationType.docSubjectRelationType}" ${relationType.selected? "selected": ""}>${relationType.typeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    
                </tr>
            </table><br/>

            
            
            <table border=1 cellpadding=2 cellspacing=1>
                <TR >
                    <TD  nowrap>&nbsp Attribute name &nbsp;</TD>
                    <c:forEach var="attributeType" items="${attributes}" >
                    <TD>${attributeType.typeName} <b class="errorsClass" id="${attributeType.docAttributeType}"/></TD>
                    </c:forEach>
                </TR>
                <TR>
                    <TD  nowrap>&nbsp Attribute value &nbsp;</TD>
                    <c:forEach var="attributeType" items="${attributes}" >
                        <TD>
                            <c:choose>
                                <c:when test="${attributeType.selectionValues == null}">
                                    <input type="text" name="${attributeType.docAttributeType}" value="${attributeType.value}">
                                </c:when>
                                <c:otherwise>
                                    <select name = "${attributeType.docAttributeType}">
                                        <c:forEach var="selectionValue" items="${attributeType.selectionValues}" >
                                            <option value="${selectionValue.atrTypeSelectionValue}">${selectionValue.valueText}</option>
                                        </c:forEach>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </TD>
                    </c:forEach>
                </TR>
            </table>
            <br/>
            <button type="button" onclick="createString()">Change</button>
        </form>

    </body>
    <script type="text/javascript">
        var myHost = "http://localhost:8080/documents/service/${userId}"
        function createString(){
            var formData = parseForm($('#createDocumentForm'));
            console.log("Form data"  + formData);
            $(".errorsClass").text("");
            var url = myHost + "/updateDocument";
            $.getJSON(url,{documentData : formData} ,function (data) {
                if(data.OK){
                    console.log("OK");
                    //document.location.href = myHost + "/document?id=" + data.docId;
                } else {
                    jQuery.each(data, function(name, val) {
                        $("#" + name).append(val);
                    });
                }
            });
            return false;
        }
        
        function searchPerson(){
            var name = $("#subjectInput").val();
            var url = myHost + "/searchSubject";
            $.getJSON(url,{subjectName : name} ,function (data) {
                if(data.OK){
                    console.log("OK");
                    $("#subjectName").val(data.subjectName);
                    $("#subjectId").val(data.subjectId);
                    $("#subjectType").val(data.subjectType);
                } else {
                    alert("not found");
                }
            });
        }
        
        var parseForm = function($form){
            var serialized = $form.serializeArray();
            var s = '';
            var data = {};
            for(s in serialized){
                if(serialized[s]['value'] !== ""){
                    data[serialized[s]['name']] = serialized[s]['value'];
                }
            }
            return JSON.stringify(data);
        }
    </script>
</html>