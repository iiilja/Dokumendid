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
@Table(name = "doc_status_type")
@NamedQueries({
    @NamedQuery(name = "DocStatusType.findAll", query = "SELECT d FROM DocStatusType d"),
    @NamedQuery(name = "DocStatusType.findByDocStatusType", query = "SELECT d FROM DocStatusType d WHERE d.docStatusType = :docStatusType"),
    @NamedQuery(name = "DocStatusType.findByTypeName", query = "SELECT d FROM DocStatusType d WHERE d.typeName = :typeName")})
public class DocStatusType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doc_status_type")
    private Long docStatusType;
    @Column(name = "type_name")
    private String typeName;

    public DocStatusType() {
    }

    public DocStatusType(Long docStatusType) {
        this.docStatusType = docStatusType;
    }

    public Long getDocStatusType() {
        return docStatusType;
    }

    public void setDocStatusType(Long docStatusType) {
        this.docStatusType = docStatusType;
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
        hash += (docStatusType != null ? docStatusType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocStatusType)) {
            return false;
        }
        DocStatusType other = (DocStatusType) object;
        if ((this.docStatusType == null && other.docStatusType != null) || (this.docStatusType != null && !this.docStatusType.equals(other.docStatusType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.DocStatusType[ docStatusType=" + docStatusType + " ]";
    }
    
}
