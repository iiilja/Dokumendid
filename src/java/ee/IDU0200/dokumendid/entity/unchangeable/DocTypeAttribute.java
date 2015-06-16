/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.entity.unchangeable;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ilja
 */
@Entity
@Table(name = "doc_type_attribute")
@NamedQueries({
    @NamedQuery(name = "DocTypeAttribute.findAll", query = "SELECT d FROM DocTypeAttribute d"),
    @NamedQuery(name = "DocTypeAttribute.findByDocTypeAttribute", query = "SELECT d FROM DocTypeAttribute d WHERE d.docTypeAttribute = :docTypeAttribute"),
    @NamedQuery(name = "DocTypeAttribute.findByDocAttributeTypeFk", query = "SELECT d FROM DocTypeAttribute d WHERE d.docAttributeTypeFk = :docAttributeTypeFk"),
    @NamedQuery(name = "DocTypeAttribute.findByDocTypeFk", query = "SELECT d FROM DocTypeAttribute d WHERE d.docTypeFk = :docTypeFk"),
    @NamedQuery(name = "DocTypeAttribute.findByOrderby", query = "SELECT d FROM DocTypeAttribute d WHERE d.orderby = :orderby"),
    @NamedQuery(name = "DocTypeAttribute.findByRequired", query = "SELECT d FROM DocTypeAttribute d WHERE d.required = :required"),
    @NamedQuery(name = "DocTypeAttribute.findByCreatedByDefault", query = "SELECT d FROM DocTypeAttribute d WHERE d.createdByDefault = :createdByDefault")})
public class DocTypeAttribute implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doc_type_attribute")
    private Long docTypeAttribute;
    @Column(name = "doc_attribute_type_fk")
    private Long docAttributeTypeFk;
    @Column(name = "doc_type_fk")
    private Long docTypeFk;
    @Column(name = "orderby")
    private Long orderby;
    @Column(name = "required")
    private String required;
    @Column(name = "created_by_default")
    private String createdByDefault;

    public DocTypeAttribute() {
    }

    public DocTypeAttribute(Long docTypeAttribute) {
        this.docTypeAttribute = docTypeAttribute;
    }

    public Long getDocTypeAttribute() {
        return docTypeAttribute;
    }

    public void setDocTypeAttribute(Long docTypeAttribute) {
        this.docTypeAttribute = docTypeAttribute;
    }

    public Long getDocAttributeTypeFk() {
        return docAttributeTypeFk;
    }

    public void setDocAttributeTypeFk(Long docAttributeTypeFk) {
        this.docAttributeTypeFk = docAttributeTypeFk;
    }

    public Long getDocTypeFk() {
        return docTypeFk;
    }

    public void setDocTypeFk(Long docTypeFk) {
        this.docTypeFk = docTypeFk;
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

    public String getCreatedByDefault() {
        return createdByDefault;
    }

    public void setCreatedByDefault(String createdByDefault) {
        this.createdByDefault = createdByDefault;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docTypeAttribute != null ? docTypeAttribute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocTypeAttribute)) {
            return false;
        }
        DocTypeAttribute other = (DocTypeAttribute) object;
        if ((this.docTypeAttribute == null && other.docTypeAttribute != null) || (this.docTypeAttribute != null && !this.docTypeAttribute.equals(other.docTypeAttribute))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.DocTypeAttribute[ docTypeAttribute=" + docTypeAttribute + " ]";
    }
    
}
