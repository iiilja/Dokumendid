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

/**
 *
 * @author ilja
 */
@Entity
@Table(name = "doc_subject_type")
@NamedQueries({
    @NamedQuery(name = "DocSubjectType.findAll", query = "SELECT d FROM DocSubjectType d"),
    @NamedQuery(name = "DocSubjectType.findByDocSubjectType", query = "SELECT d FROM DocSubjectType d WHERE d.docSubjectType = :docSubjectType"),
    @NamedQuery(name = "DocSubjectType.findByTypeName", query = "SELECT d FROM DocSubjectType d WHERE d.typeName = :typeName")})
public class DocSubjectType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "doc_subject_type")
    private Long docSubjectType;
    @Column(name = "type_name")
    private String typeName;

    public DocSubjectType() {
    }

    public DocSubjectType(Long docSubjectType) {
        this.docSubjectType = docSubjectType;
    }

    public Long getDocSubjectType() {
        return docSubjectType;
    }

    public void setDocSubjectType(Long docSubjectType) {
        this.docSubjectType = docSubjectType;
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
        hash += (docSubjectType != null ? docSubjectType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocSubjectType)) {
            return false;
        }
        DocSubjectType other = (DocSubjectType) object;
        if ((this.docSubjectType == null && other.docSubjectType != null) || (this.docSubjectType != null && !this.docSubjectType.equals(other.docSubjectType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.DocSubjectType[ docSubjectType=" + docSubjectType + " ]";
    }
    
}
