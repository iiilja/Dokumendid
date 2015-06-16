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
@Table(name = "doc_catalog_type")
@NamedQueries({
    @NamedQuery(name = "DocCatalogType.findAll", query = "SELECT d FROM DocCatalogType d"),
    @NamedQuery(name = "DocCatalogType.findByDocCatalogType", query = "SELECT d FROM DocCatalogType d WHERE d.docCatalogType = :docCatalogType"),
    @NamedQuery(name = "DocCatalogType.findByTypeName", query = "SELECT d FROM DocCatalogType d WHERE d.typeName = :typeName")})
public class DocCatalogType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "doc_catalog_type")
    private Long docCatalogType;
    @Column(name = "type_name")
    private String typeName;

    public DocCatalogType() {
    }

    public DocCatalogType(Long docCatalogType) {
        this.docCatalogType = docCatalogType;
    }

    public Long getDocCatalogType() {
        return docCatalogType;
    }

    public void setDocCatalogType(Long docCatalogType) {
        this.docCatalogType = docCatalogType;
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
        hash += (docCatalogType != null ? docCatalogType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocCatalogType)) {
            return false;
        }
        DocCatalogType other = (DocCatalogType) object;
        if ((this.docCatalogType == null && other.docCatalogType != null) || (this.docCatalogType != null && !this.docCatalogType.equals(other.docCatalogType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.DocCatalogType[ docCatalogType=" + docCatalogType + " ]";
    }
    
}
