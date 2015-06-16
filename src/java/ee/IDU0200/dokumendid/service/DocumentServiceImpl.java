/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.service;

import ee.IDU0200.dokumendid.entity.DocCatalog;
import ee.IDU0200.dokumendid.entity.unchangeable.AtrTypeSelectionValue;
import ee.IDU0200.dokumendid.entity.unchangeable.DocAttributeType;
import ee.IDU0200.dokumendid.entity.unchangeable.DocStatusType;
import ee.IDU0200.dokumendid.entity.unchangeable.DocSubjectRelationType;
import ee.IDU0200.dokumendid.entity.unchangeable.DocType;
import ee.IDU0200.dokumendid.entity.unchangeable.Enterprise;
import ee.IDU0200.dokumendid.entity.unchangeable.Person;
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
    public List<DocAttributeType> findDocAttributeTypesByDocTypeId(long docTypeId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
                "SELECT * FROM doc_attribute_type WHERE doc_attribute_type IN"+
                "(SELECT doc_attribute_type_fk FROM doc_type_attribute "
                        + "WHERE doc_type_fk = :doc_type_fk)").addEntity(DocAttributeType.class);
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

    
}
