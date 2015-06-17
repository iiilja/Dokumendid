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
        Search document
        <br>
        <form action="http://localhost:8080/documents/service/${userId}/searchDocumentsForm">
            <select name = "docTypeId">
                <c:forEach var="docType" items="${docTypesList}" >
                    <option ${docType.selected? "selected": ""} value="${docType.docType}">${docType.typeName}</option>
                </c:forEach>
            </select>
            <button type="submit">Choose</button>
        </form>
        <br/>
        <br/>
        <br/>
        <br/>
        
        <form id="createDocumentForm">
            <input type="text" name="docTypeId" value="${docType.docType}" hidden>
            
            <table border=1 cellpadding=2 cellspacing=1>
                <tr><td>Document id</td><td><input type="text" name="docId"/></td></tr>
                <tr><td>Document name</td><td><input type="text" name="docName" /></td></tr>
                <tr><td>Document description</td><td><input type="text" name="docDescription"/></td></tr>
                <tr>
                    <td>Document status</td>
                    <td>
                        <select name="docStatusType">
                            <c:forEach var="docStatusType" items="${docStatusTypesList}" >
                                <option value="${docStatusType.docStatusType}">${docStatusType.typeName}</option>
                            </c:forEach>
                            <option value="-1" selected>not chosen</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Document catalog</td>
                    <td>
                        <select name="docCatalog">
                            <c:forEach var="docCatalog" items="${docCatalogList}" >
                                <option value="${docCatalog.docCatalog}">${docCatalog.name}</option>
                            </c:forEach>
                            <option value="-1" selected>not chosen</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Doc Subject</td>
                    <td>
                        <input type="text" name="subjectId" id="subjectId" hidden/>
                        <input type="text" name="subjectType" id="subjectType" hidden/>
                        <input type="text" id="subjectName" disabled/>
                        <input type="text" id="subjectInput"/>
                        <button type="button" onclick="searchPerson()">Search</button>
                    </td>
                </tr>
                <tr>
                    <td>Doc last changer</td>
                    <td>
                        <input type="text" name="changedEmployeeId" id="changedEmployeeId" hidden/>
                        <input type="text" id="changedEmployeeName" disabled/>
                        <input type="text" id="changedEmployeeInput"/>
                        <button type="button" onclick="searchEmployee()">Search</button>
                    </td>
                </tr>
                <tr><td>Document attribute</td><td><input type="text" name="someAttributeText"/></td></tr>
            </table><br/>

            
            
            <table border=1 cellpadding=2 cellspacing=1>
                <TR >
                    <TD  nowrap>&nbsp Attribute name &nbsp;</TD>
                    <c:forEach var="attributeType" items="${attributes}" >
                    <TD>${attributeType.typeName}</TD>
                    </c:forEach>
                </TR>
                <TR>
                    <TD  nowrap>&nbsp Attribute value &nbsp;</TD>
                    <c:forEach var="attributeType" items="${attributes}" >
                        <TD>
                            <c:choose>
                                <c:when test="${attributeType.selectionValues == null}">
                                    <input type="text" name="${attributeType.docAttributeType}">
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
            <h4 onclick="createString()">Search</h4>
        </form>
            
            <div id="documents">
                
            </div>

    </body>
    <script type="text/javascript">
        var myHost = "http://localhost:8080/documents/service/${userId}"
        function createString(){
            var formData = parseForm($('#createDocumentForm'));
            console.log("Form data"  + formData);
            $(".errorsClass").text("");
            var url = myHost + "/searchDocuments";
            $.getJSON(url,{documentData : formData} ,function (data) {
                if(data.documents){
                    console.log("OK");
                    console.log(data.documents);
                    var div = $("#documents").text("");
                    var table = document.createElement("table");
                    table.setAttribute("border", 1);
                    var doc;
                    for(var i = 0; i < data.documents.length; i++){
                        doc = data.documents[i];
                        console.log(doc);
                        var tr = document.createElement("tr");
                        
                        var td1 = document.createElement("td");
                        var td2 = document.createElement("td");
                        var td3 = document.createElement("td");
                        var td4 = document.createElement("td");
                        var a = document.createElement("a");
                        td1.innerHTML= doc.docId;
                        td2.innerHTML=doc.docName;
                        td3.innerHTML =doc.docDescription;
                        a.setAttribute("href", myHost + "/document?id="+doc.docId);
                        a.innerHTML = "Look / change";
                        td4.appendChild(a);
                        tr.appendChild(td1);
                        tr.appendChild(td2);
                        tr.appendChild(td3);
                        tr.appendChild(td4);
                        table.appendChild(tr);
                    }
                    div.append(table);
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
        
        function searchEmployee(){
            var name = $("#changedEmployeeInput").val();
            var url = myHost + "/searchEmployee";
            $.getJSON(url,{employeeName : name} ,function (data) {
                if(data.OK){
                    console.log("OK");
                    $("#changedEmployeeName").val(data.changedEmployeeName);
                    $("#changedEmployeeId").val(data.changedEmployeeId);
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