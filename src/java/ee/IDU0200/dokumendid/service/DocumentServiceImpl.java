/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.service;

import ee.IDU0200.dokumendid.entity.DocCatalog;
import ee.IDU0200.dokumendid.entity.Document;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ilja
 */
@Transactional
@Service
public class DocumentServiceImpl implements DocumentService{

    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<DocType> findAllDocTypes() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("DocType.findAll").list();
    }

    @Override
    public DocType findDocTypeFirst() {
        Session session = sessionFactory.getCurrentSession();
        return (DocType) session.createSQLQuery(
                "SELECT * FROM doc_type LIMIT 1").addEntity(DocType.class).uniqueResult();
    }

    @Override
    public DocType findDocTypeById(long docTypeId) {
        Session session = sessionFactory.getCurrentSession();
        return (DocType) session.getNamedQuery("DocType.findByDocType").setParameter("docType", docTypeId).uniqueResult();
    }

    @Override
    public List<AtrTypeSelectionValue> findAtrTypeSelectionValuesByDocAttributeTypeId(long docAttributeTypeId) {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("AtrTypeSelectionValue.findByDocAttributeTypeFk")
                .setParameter("docAttributeTypeFk", docAttributeTypeId).list();
    }
    
    @Override
    public AtrTypeSelectionValue findAtrTypeSelectionValueById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AtrTypeSelectionValue) session.getNamedQuery("AtrTypeSelectionValue.findByAtrTypeSelectionValue").
                setParameter("atrTypeSelectionValue", id).uniqueResult();
    }

    @Override
    public List<DocAttributeType> findDocAttributeTypesByDocTypeId(long docTypeId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
                "SELECT dat.* FROM doc_attribute_type as dat INNER JOIN  doc_type_attribute as dta " +
                "ON dat.doc_attribute_type = dta.doc_attribute_type_fk " +
                "WHERE dta.doc_type_fk = :doc_type_fk " +
                "ORDER BY dta.orderby ASC").addEntity(DocAttributeType.class);
        query.setParameter("doc_type_fk", docTypeId);
        return query.list();
    }

    @Override
    public List<DocStatusType> findDocStatusTypes() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("DocStatusType.findAll").list();
    }

    @Override
    public List<DocCatalog> findDocCatalogs() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("DocCatalog.findAll").list();
    }
    
    @Override
    public DocCatalog findDocCatalogById(long docCatalogFk) {
        Session session = sessionFactory.getCurrentSession();
        return (DocCatalog) session.getNamedQuery("DocCatalog.findByDocCatalog").
                setParameter("docCatalog", docCatalogFk).uniqueResult();
    }

    @Override
    public Person findPersonWithLastName(String subjectName) {
        Session session = sessionFactory.getCurrentSession();
        return (Person) session.getNamedQuery("Person.findByLastName").
                setParameter("lastName", subjectName).uniqueResult();
    }

    @Override
    public Enterprise findEnterpriseByName(String subjectName) {
        Session session = sessionFactory.getCurrentSession();
        return (Enterprise) session.getNamedQuery("Enterprise.findByName").
                setParameter("name", subjectName).uniqueResult();
    }

    @Override
    public List<DocSubjectRelationType> findDocSubjectRelationTypes() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("DocSubjectRelationType.findAll").list();
    }

    @Override
    public DocTypeAttribute findDocTypeAttributeTypeBy(long docTypeId, long docAttributeType) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT d FROM DocTypeAttribute d WHERE d.docTypeFk = :docTypeFk AND d.docAttributeTypeFk = :docAttributeTypeFk");
        query.setParameter("docTypeFk", docTypeId);
        query.setParameter("docAttributeTypeFk", docAttributeType);
        return (DocTypeAttribute) query.uniqueResult();
    }

    @Override
    public Document findDocumentById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Document) session.getNamedQuery("Document.findByDocument").
                setParameter("document", id).uniqueResult();
    }

    @Override
    public List<Document> findDocumentsByDocStatusType(long docStatusType) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
            "SELECT document.* \n" +
            "FROM document INNER JOIN doc_status " +
            "ON document.document =  doc_status.document_fk " +
            "WHERE doc_status.doc_status_type_fk = :doc_status_type_fk AND status_end IS NULL").addEntity(Document.class);
        query.setParameter("doc_status_type_fk", docStatusType);
        return query.list();
    }

    @Override
    public List<Document> findDocumentsByDocCatalog(long docCatalog) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
            "SELECT document.* " +
            "FROM document INNER JOIN document_doc_catalog " +
            "ON document.document =  document_doc_catalog.document_fk " +
            "WHERE document_doc_catalog.doc_catalog_fk = :doc_catalog_fk").addEntity(Document.class);
        query.setParameter("doc_catalog_fk", docCatalog);
        return query.list();
    }

    @Override
    public List<Document> findDocumentsByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createSQLQuery(
            "SELECT * FROM document " +
            "WHERE (to_tsvector(name) @@ to_tsquery(:name))").addEntity(Document.class)
                .setParameter("name", name).list();
    }

    @Override
    public List<Document> findDocumentsByDesctiption(String description) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
            "SELECT * FROM document " +
            "WHERE (to_tsvector(description) @@ to_tsquery(:description))").addEntity(Document.class);
        query.setParameter("description", description);
        return query.list();
    }

    @Override
    public List<Document> findDocumentsByDocSubject(long subjectId, long subjectType) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
            "SELECT document.* \n" +
            "FROM document INNER JOIN doc_subject \n" +
            "ON document.document =  doc_subject.document_fk\n" +
            "WHERE doc_subject.subject_fk = :subjectId AND doc_subject.doc_subject_type_fk = :subjectType").addEntity(Document.class);
        query.setParameter("subjectId", subjectId);
        query.setParameter("subjectType", subjectType);
        return query.list();
    }

    @Override
    public List<Document> findDocumentsByChanger(long changedEmployeeId) {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("Document.findByUpdatedBy").
                setParameter("updatedBy", changedEmployeeId).list();
    }

    @Override
    public List<Document> findDocumentsBySomeAttributeText(String string) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
            "SELECT document.* \n" +
            "FROM document INNER JOIN doc_attribute \n" +
            "ON document.document = doc_attribute.document_fk\n" +
            "WHERE (to_tsvector(doc_attribute.value_text) @@ to_tsquery(:someText))").addEntity(Document.class);
        query.setParameter("someText", string);
        return query.list();
    }

    @Override
    public Person findPersonById(Long personFk) {
        Session session = sessionFactory.getCurrentSession();
        return (Person) session.getNamedQuery("Person.findByPerson").
                setParameter("person", personFk).uniqueResult();
    }

    @Override
    public Employee findEmployeeWithLastName(String employeeLastName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
            "SELECT employee.* \n" +
            "FROM employee INNER JOIN person \n" +
            "ON employee.person_fk = person.person \n" +
            "WHERE UPPER(person.last_name) = UPPER(:lastName)").addEntity(Employee.class);
        query.setParameter("lastName", employeeLastName);
        return (Employee) query.uniqueResult();
    }

    @Override
    public Object saveEntity(Object object) {
        return sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void updateEntity(Object object) {
        sessionFactory.getCurrentSession().update(object);
    }
    
    


    
}
