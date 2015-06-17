/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ilja
 */
@Entity
@Table(name = "doc_attribute")
@NamedQueries({
    @NamedQuery(name = "DocAttribute.findAll", query = "SELECT d FROM DocAttribute d"),
    @NamedQuery(name = "DocAttribute.findByDocAttribute", query = "SELECT d FROM DocAttribute d WHERE d.docAttribute = :docAttribute"),
    @NamedQuery(name = "DocAttribute.findByAtrTypeSelectionValueFk", query = "SELECT d FROM DocAttribute d WHERE d.atrTypeSelectionValueFk = :atrTypeSelectionValueFk"),
    @NamedQuery(name = "DocAttribute.findByDocAttributeTypeFk", query = "SELECT d FROM DocAttribute d WHERE d.docAttributeTypeFk = :docAttributeTypeFk"),
    @NamedQuery(name = "DocAttribute.findByDocumentFk", query = "SELECT d FROM DocAttribute d WHERE d.documentFk = :documentFk"),
    @NamedQuery(name = "DocAttribute.findByTypeName", query = "SELECT d FROM DocAttribute d WHERE d.typeName = :typeName"),
    @NamedQuery(name = "DocAttribute.findByValueText", query = "SELECT d FROM DocAttribute d WHERE d.valueText = :valueText"),
    @NamedQuery(name = "DocAttribute.findByValueNumber", query = "SELECT d FROM DocAttribute d WHERE d.valueNumber = :valueNumber"),
    @NamedQuery(name = "DocAttribute.findByValueDate", query = "SELECT d FROM DocAttribute d WHERE d.valueDate = :valueDate"),
    @NamedQuery(name = "DocAttribute.findByDataType", query = "SELECT d FROM DocAttribute d WHERE d.dataType = :dataType"),
    @NamedQuery(name = "DocAttribute.findByOrderby", query = "SELECT d FROM DocAttribute d WHERE d.orderby = :orderby"),
    @NamedQuery(name = "DocAttribute.findByRequired", query = "SELECT d FROM DocAttribute d WHERE d.required = :required")})
public class DocAttribute implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "doc_attribute_id")
    @Column(name = "doc_attribute")
    private Long docAttribute;
    
    @Column(name = "atr_type_selection_value_fk")
    private Long atrTypeSelectionValueFk;
    
    @Column(name = "doc_attribute_type_fk")
    private Long docAttributeTypeFk;
    
    @Column(name = "document_fk")
    private Long documentFk;
    
    @Column(name = "type_name")
    private String typeName;
    
    @Column(name = "value_text")
    private String valueText;
    
    @Column(name = "value_number")
    private Integer valueNumber;
    
    @Column(name = "value_date")
    @Temporal(TemporalType.DATE)
    private Date valueDate;
    
    @Column(name = "data_type")
    private Short dataType;
    
    @Column(name = "orderby")
    private Long orderby;
    
    @Column(name = "required")
    private String required;

    public DocAttribute() {
    }

    public DocAttribute(Long docAttribute) {
        this.docAttribute = docAttribute;
    }

    public Long getDocAttribute() {
        return docAttribute;
    }

    public void setDocAttribute(Long docAttribute) {
        this.docAttribute = docAttribute;
    }

    public Long getAtrTypeSelectionValueFk() {
        return atrTypeSelectionValueFk;
    }

    public void setAtrTypeSelectionValueFk(Long atrTypeSelectionValueFk) {
        this.atrTypeSelectionValueFk = atrTypeSelectionValueFk;
    }

    public Long getDocAttributeTypeFk() {
        return docAttributeTypeFk;
    }

    public void setDocAttributeTypeFk(Long docAttributeTypeFk) {
        this.docAttributeTypeFk = docAttributeTypeFk;
    }

    public Long getDocumentFk() {
        return documentFk;
    }

    public void setDocumentFk(Long documentFk) {
        this.documentFk = documentFk;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public Integer getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(Integer valueNumber) {
        this.valueNumber = valueNumber;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Short getDataType() {
        return dataType;
    }

    public void setDataType(Short dataType) {
        this.dataType = dataType;
    }

    public Long getOrderby() {
        return orderby;
    }

    public void setOrderby(Long orderby) {
        this.orderby = orderby;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docAttribute != null ? docAttribute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocAttribute)) {
            return false;
        }
        DocAttribute other = (DocAttribute) object;
        if ((this.docAttribute == null && other.docAttribute != null) || (this.docAttribute != null && !this.docAttribute.equals(other.docAttribute))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.DocAttribute[ docAttribute=" + docAttribute + " ]";
    }
    
}
