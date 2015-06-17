/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.controller;

import ee.IDU0200.dokumendid.entity.DocAttribute;
import ee.IDU0200.dokumendid.entity.DocCatalog;
import ee.IDU0200.dokumendid.entity.DocStatus;
import ee.IDU0200.dokumendid.entity.DocSubject;
import ee.IDU0200.dokumendid.entity.Document;
import ee.IDU0200.dokumendid.entity.DocumentDocCatalog;
import ee.IDU0200.dokumendid.entity.DocumentDocType;
import ee.IDU0200.dokumendid.entity.unchangeable.AtrTypeSelectionValue;
import ee.IDU0200.dokumendid.entity.unchangeable.DocAttributeType;
import ee.IDU0200.dokumendid.entity.unchangeable.DocStatusType;
import ee.IDU0200.dokumendid.entity.unchangeable.DocType;
import ee.IDU0200.dokumendid.entity.unchangeable.DocTypeAttribute;
import ee.IDU0200.dokumendid.entity.unchangeable.Employee;
import ee.IDU0200.dokumendid.entity.unchangeable.Enterprise;
import ee.IDU0200.dokumendid.entity.unchangeable.Person;
import ee.IDU0200.dokumendid.service.DocumentService;
import ee.IDU0200.dokumendid.util.ListCleaner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ilja
 */
@Controller
public class DocumentsController {

    
    private static final String DATE_FORMAT = "yyyy MM d HH:mm:ss";
    private static final int DATA_TYPE_STRING = 1;
    private static final int DATA_TYPE_NUMBER = 2;
    private static final int DATA_TYPE_DATE = 3;
    private static final int DATA_TYPE_CHOICE = 4;
    private static final String REQUIRED_TRUE = "Y";
    private static final String REQUIRED_FALSE = "N";
    
    private static final int SUBJECT_TYPE_PERSON = 1;
    private static final int SUBJECT_TYPE_ENTERPRISE = 2;
    
    @Autowired(required=true)
    private DocumentService documentService;
    
    
    @RequestMapping(value = "/{userId}/createForm")
    public ModelAndView createDocumentAddingForm(
            @PathVariable("userId") String userId,
            @RequestParam(required = false) Long docTypeId,
            HttpServletRequest request,
            HttpServletResponse response){
        List<DocType> docTypes = documentService.findAllDocTypes();
        
        System.out.println("DocTypes list contains " + docTypes.size());
        
        DocType docType = findDoctypeFromListById(docTypes, docTypeId);
        if (docTypeId == null) {
            docType = documentService.findDocTypeFirst();
        }
        docType.setSelected(true);
        Map<String, Object> model = new HashMap<>();
        model.put("docType", docType);
        model.put("docTypesList", docTypes);
        List<DocAttributeType> attributes = documentService.findDocAttributeTypesByDocTypeId(docType.getDocType());
        System.out.println("attributes list contains " + attributes.size());
        for (DocAttributeType attribute : attributes) {
            if (attribute.getDataTypeFk() == DATA_TYPE_CHOICE) {
                List<AtrTypeSelectionValue> selectionValues 
                        = documentService.findAtrTypeSelectionValuesByDocAttributeTypeId(attribute.getDocAttributeType());
                attribute.setSelectionValues(selectionValues);
            }
        }
        
        model.put("attributes", attributes);
        model.put("docStatusTypesList", documentService.findDocStatusTypes());
        model.put("docCatalogList", documentService.findDocCatalogs());
        model.put("docSubjectRelationTypes", documentService.findDocSubjectRelationTypes());
        model.put("userId", userId);
        return new ModelAndView("createDocumentForm",model);
    }
    
    @RequestMapping(value = "/{userId}/createDocument")
    public @ResponseBody String createDocument(
            @PathVariable("userId") long userId,
            @RequestParam String documentData,
            HttpServletRequest request,
            HttpServletResponse response) throws JSONException{
        JSONObject json = new JSONObject(documentData);
        JSONObject errors = validateData(json);
        
        if (errors == null) {
            Document document = createDocument(json, userId);
            documentService.saveEntity(document);
            DocStatus docStatus = createDocStatus(json, document.getDocument(), userId);
            documentService.saveEntity(docStatus);
            DocumentDocType documentDocType = createDocumentDocType(json, document.getDocument());
            documentService.saveEntity(documentDocType);
            DocumentDocCatalog documentDocCatalog = createDocumentDocCatalog(json, document.getDocument());
            documentService.saveEntity(documentDocCatalog);
            DocCatalog docCatalog = documentService.findDocCatalogById(documentDocCatalog.getDocCatalogFk());
            docCatalog.setContentUpdated(new Date());
            docCatalog.setContentUpdatedBy(userId);
            documentService.updateEntity(docCatalog);
            DocSubject docSubject = createDocSubjectFromJSON(json,document.getDocument());
            documentService.saveEntity(docSubject);
            List<DocAttribute> docAttributes = createDocAttributesFromJSON(json,document.getDocument());
            for (DocAttribute docAttribute : docAttributes) {
                documentService.saveEntity(docAttribute);
            }
            json = new JSONObject();
            json.put("OK", true);
            json.put("docId", document.getDocument());
        } else {
            json = errors;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return json.toString();
    }
    
    
    @RequestMapping(value = "/{userId}/searchSubject")
    public @ResponseBody String findSubject(
            @PathVariable("userId") long userId,
            @RequestParam String subjectName,
            HttpServletRequest request,
            HttpServletResponse response) throws JSONException{
        JSONObject json = new JSONObject();
        Person person = documentService.findPersonWithLastName(subjectName);
        if (person != null) {
            json.put("OK", true);
            json.put("subjectType", SUBJECT_TYPE_PERSON);
            json.put("subjectId", person.getPerson());
            json.put("subjectName", person.getFirstName() + " " + person.getLastName());
        } else {
            Enterprise enterprise = documentService.findEnterpriseByName(subjectName);
            if (enterprise != null) {
                json.put("OK", true);
                json.put("subjectType", SUBJECT_TYPE_ENTERPRISE);
                json.put("subjectId", enterprise.getEnterprise());
                json.put("subjectName", enterprise.getFullName());
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return json.toString();
    }
    
    @RequestMapping(value = "/{userId}/searchEmployee")
    public @ResponseBody String findEmployee(
            @PathVariable("userId") long userId,
            @RequestParam String employeeName,
            HttpServletRequest request,
            HttpServletResponse response) throws JSONException{
        JSONObject json = new JSONObject();
        Employee employee = documentService.findEmployeeWithLastName(employeeName);
        if (employee != null) {
            Person person = documentService.findPersonById(employee.getPersonFk());
            json.put("OK", true);
            json.put("changedEmployeeId", employee.getEmployee());
            json.put("changedEmployeeName", person.getFirstName() + " " + person.getLastName());
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return json.toString();
    }
    
    @RequestMapping(value = "/{userId}/searchDocumentsForm")
    public ModelAndView findDocumentsForm(
            @PathVariable("userId") String userId,
            @RequestParam(required = false) Long docTypeId,
            HttpServletRequest request,
            HttpServletResponse response){
        List<DocType> docTypes = documentService.findAllDocTypes();
        
        DocType docType = findDoctypeFromListById(docTypes, docTypeId);
        Map<String, Object> model = new HashMap<>();
        if (docType != null) {
            docType.setSelected(true);
            model.put("docType", docType);
            List<DocAttributeType> attributes = documentService.findDocAttributeTypesByDocTypeId(docType.getDocType());
            for (DocAttributeType attribute : attributes) {
                if (attribute.getDataTypeFk() == DATA_TYPE_CHOICE) {
                    List<AtrTypeSelectionValue> selectionValues 
                            = documentService.findAtrTypeSelectionValuesByDocAttributeTypeId(attribute.getDocAttributeType());
                    attribute.setSelectionValues(selectionValues);
                }
            }
            model.put("attributes", attributes);
        }
        DocType noDocType = new DocType(0L);
        noDocType.setTypeName("not selected");
        noDocType.setSelected(docType == null);
        docTypes.add(noDocType);
        model.put("docTypesList", docTypes);
        model.put("docStatusTypesList", documentService.findDocStatusTypes());
        model.put("docCatalogList", documentService.findDocCatalogs());
        model.put("docSubjectRelationTypes", documentService.findDocSubjectRelationTypes());
        model.put("userId", userId);
        return new ModelAndView("searchDocumentForm",model);
    }
    
    @RequestMapping(value = "/{userId}/searchDocuments")
    public @ResponseBody String findDocuments(
            @PathVariable("userId") String userId,
            @RequestParam String documentData,
            HttpServletRequest request,
            HttpServletResponse response) throws JSONException{
        List<Document> docs = searchDocument(new JSONObject(documentData));
        System.out.println(docs.size());
        response.setStatus(HttpServletResponse.SC_OK);
        return createJSONFromDocuments(docs).toString();
    }
    
    @RequestMapping(value = "/{userId}/document")
    public @ResponseBody String findDocuments(
            @PathVariable("userId") String userId,
            @RequestParam long id,
            HttpServletRequest request,
            HttpServletResponse response) throws JSONException{
//        List<Document> docs = searchDocument(new JSONObject(documentData));
//        System.out.println(docs.size());
//        response.setStatus(HttpServletResponse.SC_OK);
        return "";
    }
    
    private DocType findDoctypeFromListById(List<DocType> docTypes, Long docTypeId){
        if (docTypeId == null) {
            return null;
        }
        for (DocType docType : docTypes) {
            if (docTypeId.equals(docType.getDocType())) {
                return docType;
            }
        }
        return null;
    }
    
    private List<Document> searchDocument(JSONObject json) throws JSONException{
        List<Document> documents = new ArrayList<>();
        JSONObject errors = new JSONObject();
//        dokumendi (andmebaasi) id järgi
        if (json.has("docId")){ 
            try {
                long id = Long.parseLong(json.getString("docId"));
                Document document = documentService.findDocumentById(id);
                if (document != null) documents.add(document);
            } catch (NumberFormatException e){
                errors.put("docId", "Specify docId");
            }
        }
//        dokumendi staatuse järgi
        if (json.has("docStatusType")){ 
            try {
                long id = Long.parseLong(json.getString("docStatusType"));
                documents.addAll(documentService.findDocumentsByDocStatusType(id));
            } catch (NumberFormatException e){
                errors.put("docStatusType", "Specify docStatusType");
            }
        }
//        kataloogi nime järgi kus dokument asub
        if (json.has("docCatalog")){ 
            try {
                long id = Long.parseLong(json.getString("docCatalog"));
                documents.addAll(documentService.findDocumentsByDocCatalog(id));
            } catch (NumberFormatException e){
                errors.put("docCatalog", "Specify docCatalog");
            }
        }
//        dokumendi nime järgi 
        if (json.has("docName") ){
            documents.addAll(documentService.findDocumentsByName(json.getString("docName")));
        }
//        dokumendi kirjelduse järgi
        if (json.has("docDescription") ){
            documents.addAll(documentService.findDocumentsByDesctiption(json.getString("docDescription")));
        }
//        dokumentidega seotud subjektide nime järgi ([enterpise].name, [person].last_name)
        if (json.has("subjectId") && json.has("subjectType")){
            try {
                long subjectId = Long.parseLong(json.getString("subjectId"));
                long subjectType = Long.parseLong(json.getString("subjectType"));
                documents.addAll(documentService.findDocumentsByDocSubject(subjectId, subjectType));
            } catch (NumberFormatException e){
                errors.put("docCatalog", "Specify docCatalog");
            }
        }
//        viimase muutja perekonnanime järgi ([employee]->[person].last_name)
        if (json.has("changedEmployeeId")){
            try {
                long changedEmployeeId = Long.parseLong(json.getString("changedEmployeeId"));
                documents.addAll(documentService.findDocumentsByChanger(changedEmployeeId));
            } catch (NumberFormatException e){
                errors.put("docCatalog", "Specify docCatalog");
            }
        }
        if (json.has("someAttributeText")){
            documents.addAll(documentService.findDocumentsBySomeAttributeText(json.getString("someAttributeText")));
        }
        return ListCleaner.cleanListFromEqualObjects(documents);
    }
    
    private JSONObject validateData(JSONObject json) throws JSONException{
        boolean OK = true;
        JSONObject errors = new JSONObject();
        
        if (!json.has("docStatusType")){ 
            errors.put("docStatusType", "Specify docStatusType");
            OK = false;
        }
        if (!json.has("docCatalog")){ 
            errors.put("docCatalog", "Specify docCatalog");
            OK = false;
        }
        if (!json.has("docName") || json.getString("docName").length() == 0){
            errors.put("docName", "Specify docName");
            OK = false;
        }
        if (!json.has("docDescription") || json.getString("docDescription").length() == 0){
            errors.put("docDescription", "Specify docDescription");
            OK = false;
        }
        if (!json.has("subjectId") || json.getString("subjectId").length() == 0){
            errors.put("subject", "Specify subject");
            OK = false;
        }
        if (!json.has("subjectType") || json.getString("subjectType").length() == 0){
            errors.put("subject", "Specify subject");
            OK = false;
        }
        if (json.has("docTypeId")) {
            long docTypeId = Long.parseLong(json.getString("docTypeId"));
            DocType docType = documentService.findDocTypeById(docTypeId);
            List<DocAttributeType> attributes = documentService.findDocAttributeTypesByDocTypeId(docType.getDocType());
            for (DocAttributeType attribute : attributes) {
                DocTypeAttribute docTypeAttribute = documentService.findDocTypeAttributeTypeBy(docTypeId, attribute.getDocAttributeType());
                if (REQUIRED_FALSE.equals(docTypeAttribute.getRequired())){
                    System.out.println(attribute.getTypeName() + " not required");
                    continue;
                }
                String docAttributeTypeId = attribute.getDocAttributeType() + "";
                if (json.has(docAttributeTypeId)) {
                    String data = json.getString(docAttributeTypeId);
                    if (attribute.getDataTypeFk() == DATA_TYPE_STRING) {
                        if (data.length() == 0) {
                            errors.put(docAttributeTypeId, "Please insert field value");
                            OK = false;
                        }
                    } else if (attribute.getDataTypeFk() == DATA_TYPE_NUMBER) {
                        try {
                            Double.parseDouble(data);
                        } catch (NumberFormatException ex){
                            System.err.println("ERROR " + ex.getMessage());
                            errors.put(docAttributeTypeId, "Should be a number");
                            OK = false;
                        }
                    } else if (attribute.getDataTypeFk() == DATA_TYPE_DATE){
                        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                        try {
                            sdf.parse(data);
                        } catch (ParseException ex) {
                            System.err.println("ERROR " + ex.getMessage());
                            errors.put(docAttributeTypeId, "Format:" +DATE_FORMAT);
                            OK = false;
                        }
                    }
                } else {
                    errors.put(docAttributeTypeId, "Please insert field value");
                    OK = false;
                }
            }
            
        } else {
            OK = false;
        }
        if (!OK) {
            return errors;
        }
        return null;
    }

    private List<DocAttribute> createDocAttributesFromJSON(JSONObject json, long documentFk) throws JSONException {
        long docTypeId = Long.parseLong(json.getString("docTypeId"));
        DocType docType = documentService.findDocTypeById(docTypeId);
        List<DocAttributeType> attributeTypes = documentService.findDocAttributeTypesByDocTypeId(docType.getDocType());
        List<DocAttribute> attributes = new ArrayList<>();
        for (DocAttributeType attributeType : attributeTypes) {
            String docAttributeTypeId = attributeType.getDocAttributeType() + "";
            if (!json.has(docAttributeTypeId)) continue;
            
            String data = json.getString(docAttributeTypeId);
            
            DocTypeAttribute docTypeAttribute = documentService.findDocTypeAttributeTypeBy(docTypeId, attributeType.getDocAttributeType());
            
            DocAttribute attribute = new DocAttribute();
            attribute.setDocAttributeTypeFk(attributeType.getDocAttributeType());
            attribute.setTypeName(attributeType.getTypeName());
            attribute.setDataType(attributeType.getDataTypeFk());
            attribute.setDocumentFk(documentFk);
            attribute.setRequired(docTypeAttribute.getRequired());
            attribute.setOrderby(docTypeAttribute.getOrderby());
            if (attributeType.getDataTypeFk() == DATA_TYPE_CHOICE) {
                attribute.setAtrTypeSelectionValueFk(Long.parseLong(data));
                AtrTypeSelectionValue value = documentService.findAtrTypeSelectionValueById(Long.parseLong(data));
                data = value.getValueText();
            } else if (attributeType.getDataTypeFk() == DATA_TYPE_NUMBER) {
                attribute.setValueNumber(Integer.parseInt(data));
            } else if (attributeType.getDataTypeFk() == DATA_TYPE_DATE){
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                Date date = null;
                try {
                    sdf.parse(data);
                    attribute.setValueDate(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
            attribute.setValueText(data);
        }
        return attributes;
    }

    private Document createDocument(JSONObject json, long userId) throws JSONException {
        Document document = new Document();
        document.setName(json.getString("docName"));
        document.setDescription(json.getString("docDescription"));
        document.setDocStatusTypeFk(Long.parseLong(json.getString("docStatusType")));
        document.setCreated(new Date());
        document.setCreatedBy(userId);
        return document;
    }

    private DocumentDocType createDocumentDocType(JSONObject json, long documentFk) throws JSONException {
        long docTypeId = Long.parseLong(json.getString("docTypeId"));
        DocumentDocType documentDocType = new DocumentDocType();
        documentDocType.setDocTypeFk(docTypeId);
        documentDocType.setDocumentFk(documentFk);
        return documentDocType;
    }

    private DocumentDocCatalog createDocumentDocCatalog(JSONObject json, long documentFk) throws JSONException {
        long docCatalogFk = Long.parseLong(json.getString("docCatalog"));
        DocumentDocCatalog catalog = new DocumentDocCatalog();
        catalog.setDocCatalogFk(docCatalogFk);
        catalog.setDocumentFk(documentFk);
        catalog.setCatalogTime(new Date());
        return catalog;
    }

    private DocStatus createDocStatus(JSONObject json, Long documentFk, long userId) throws JSONException {
        DocStatus docStatus = new DocStatus();
        docStatus.setDocumentFk(documentFk);
        docStatus.setCreatedBy(userId);
        docStatus.setDocStatusTypeFk(Long.parseLong(json.getString("docStatusType")));
        docStatus.setStatusBegin(new Date());
        return docStatus;
    }

    private DocSubject createDocSubjectFromJSON(JSONObject json, long document) throws JSONException {
        DocSubject subject = new DocSubject();
        subject.setSubjectFk(Long.parseLong(json.getString("subjectId")));
        subject.setDocSubjectTypeFk(Long.parseLong(json.getString("subjectType")));
        subject.setDocSubjectRelationTypeFk(Long.parseLong(json.getString("docSubjectRelationType")));
        subject.setDocumentFk(document);
        return subject;
    }
    
    private static JSONObject createJSONFromDocuments(List<Document> documents) throws JSONException{
        JSONObject docsJSON = new JSONObject();
        JSONArray docsArray = new JSONArray();
        for (Document document : documents) {
            JSONObject docJSON = new JSONObject();
            docJSON.put("docId", document.getDocument());
            docJSON.put("docName", document.getName());
            docJSON.put("docDescription", document.getDescription());
            docsArray.put(docJSON);
        }
        docsJSON.put("documents", docsArray);
        return docsJSON;
    }
}
