/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.service;

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
import ee.IDU0200.dokumendid.entity.unchangeable.DocSubjectRelationType;
import ee.IDU0200.dokumendid.entity.unchangeable.DocType;
import ee.IDU0200.dokumendid.entity.unchangeable.DocTypeAttribute;
import ee.IDU0200.dokumendid.entity.unchangeable.Employee;
import ee.IDU0200.dokumendid.entity.unchangeable.Enterprise;
import ee.IDU0200.dokumendid.entity.unchangeable.Person;
import java.util.Collection;
import java.util.List;


/**
 *
 * @author ilja
 */
public interface DocumentService {
    
    public List<DocType> findAllDocTypes();
    public DocType findDocTypeFirst();
    public DocType findDocTypeById(long docTypeId);
    
    public List<DocAttributeType> findDocAttributeTypesByDocTypeId(long docTypeId);
    
    public List<AtrTypeSelectionValue> findAtrTypeSelectionValuesByDocAttributeTypeId(long docAttributeTypeId);
    public AtrTypeSelectionValue findAtrTypeSelectionValueById(long id);

    public List<DocStatusType> findDocStatusTypes();

    public List<DocCatalog> findDocCatalogs();
    public DocCatalog findDocCatalogById(long docCatalogFk);

    public Person findPersonById(Long personFk);
    public Person findPersonWithLastName(String subjectName);

    public Enterprise findEnterpriseByName(String subjectName);

    public List<DocSubjectRelationType> findDocSubjectRelationTypes();

    public DocTypeAttribute findDocTypeAttributeTypeBy(long docTypeId, long docAttributeType);


    public Document findDocumentById(long id);
    public List<Document> findDocumentsByDocStatusType(long docStatusType);
    public List<Document> findDocumentsByDocCatalog(long docCatalog);
    public List<Document> findDocumentsByName(String name);
    public List<Document> findDocumentsByDesctiption(String description);
    public List<Document> findDocumentsByDocSubject(long subjectId, long subjectType);
    public List<Document> findDocumentsByChanger(long changedEmployeeId);
    public List<Document> findDocumentsBySomeAttributeText(String string);


    public Employee findEmployeeWithLastName(String employeeLastName);
    
    public Object saveEntity(Object object);

    public void updateEntity(Object object);
    
    public void deleteEntity(Object object);

    public DocStatus findDocstatusByDocId(long id);

    public DocumentDocType findDocumentDocTypeByDocumentId(long id);

    public DocumentDocCatalog findDocumentDocCatalogByDocumentId(long id);

    public DocSubject findDocsubjectByDocumentId(long id);

    public Enterprise findEnterpriseById(long subjectFk);

    public List<DocAttribute> findDocAttributesByDocId(long id);



}
