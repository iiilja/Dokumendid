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
import javax.persistence.Transient;

/**
 *
 * @author ilja
 */
@Entity
@Table(name = "doc_type")
@NamedQueries({
    @NamedQuery(name = "DocType.findAll", query = "SELECT d FROM DocType d"),
    @NamedQuery(name = "DocType.findByDocType", query = "SELECT d FROM DocType d WHERE d.docType = :docType"),
    @NamedQuery(name = "DocType.findBySuperTypeFk", query = "SELECT d FROM DocType d WHERE d.superTypeFk = :superTypeFk"),
    @NamedQuery(name = "DocType.findByLevel", query = "SELECT d FROM DocType d WHERE d.level = :level"),
    @NamedQuery(name = "DocType.findByTypeName", query = "SELECT d FROM DocType d WHERE d.typeName = :typeName")})
public class DocType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doc_type")
    private Long docType;
    @Column(name = "super_type_fk")
    private Long superTypeFk;
    @Column(name = "level")
    private Long level;
    @Column(name = "type_name")
    private String typeName;
    
    @Transient
    private boolean selected;

    public DocType() {
    }

    public DocType(Long docType) {
        this.docType = docType;
    }

    public Long getDocType() {
        return docType;
    }

    public void setDocType(Long docType) {
        this.docType = docType;
    }

    public Long getSuperTypeFk() {
        return superTypeFk;
    }

    public void setSuperTypeFk(Long superTypeFk) {
        this.superTypeFk = superTypeFk;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docType != null ? docType.hashCode() : 0);
        return hash;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocType)) {
            return false;
        }
        DocType other = (DocType) object;
        if ((this.docType == null && other.docType != null) || (this.docType != null && !this.docType.equals(other.docType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.DocType[ docType=" + docType + " ]";
    }
    
}
