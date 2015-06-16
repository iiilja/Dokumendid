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

    public List<DocStatusType> findDocStatusTypes();

    public List<DocCatalog> findDocCatalogs();

    public Person findPersonWithLastName(String subjectName);

    public Enterprise findEnterpriseByName(String subjectName);

    public List<DocSubjectRelationType> findDocSubjectRelationTypes();


    
}
