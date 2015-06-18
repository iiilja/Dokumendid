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
@Table(name = "doc_subject_relation_type")
@NamedQueries({
    @NamedQuery(name = "DocSubjectRelationType.findAll", query = "SELECT d FROM DocSubjectRelationType d"),
    @NamedQuery(name = "DocSubjectRelationType.findByDocSubjectRelationType", query = "SELECT d FROM DocSubjectRelationType d WHERE d.docSubjectRelationType = :docSubjectRelationType"),
    @NamedQuery(name = "DocSubjectRelationType.findByTypeName", query = "SELECT d FROM DocSubjectRelationType d WHERE d.typeName = :typeName")})
public class DocSubjectRelationType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "doc_subject_relation_type")
    private Long docSubjectRelationType;
    @Column(name = "type_name")
    private String typeName;
    
    @Transient
    private boolean selected;

    public DocSubjectRelationType() {
    }

    public DocSubjectRelationType(Long docSubjectRelationType) {
        this.docSubjectRelationType = docSubjectRelationType;
    }

    public Long getDocSubjectRelationType() {
        return docSubjectRelationType;
    }

    public void setDocSubjectRelationType(Long docSubjectRelationType) {
        this.docSubjectRelationType = docSubjectRelationType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isSelected() {
        return selected;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docSubjectRelationType != null ? docSubjectRelationType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocSubjectRelationType)) {
            return false;
        }
        DocSubjectRelationType other = (DocSubjectRelationType) object;
        if ((this.docSubjectRelationType == null && other.docSubjectRelationType != null) || (this.docSubjectRelationType != null && !this.docSubjectRelationType.equals(other.docSubjectRelationType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.DocSubjectRelationType[ docSubjectRelationType=" + docSubjectRelationType + " ]";
    }

    public void setSelected(boolean b) {
        this.selected = b;
    }
    
}
