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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ilja
 */
@Entity
@Table(name = "document_doc_catalog")
@NamedQueries({
    @NamedQuery(name = "DocumentDocCatalog.findAll", query = "SELECT d FROM DocumentDocCatalog d"),
    @NamedQuery(name = "DocumentDocCatalog.findByDocumentDocCatalog", query = "SELECT d FROM DocumentDocCatalog d WHERE d.documentDocCatalog = :documentDocCatalog"),
    @NamedQuery(name = "DocumentDocCatalog.findByDocumentFk", query = "SELECT d FROM DocumentDocCatalog d WHERE d.documentFk = :documentFk"),
    @NamedQuery(name = "DocumentDocCatalog.findByDocCatalogFk", query = "SELECT d FROM DocumentDocCatalog d WHERE d.docCatalogFk = :docCatalogFk"),
    @NamedQuery(name = "DocumentDocCatalog.findByCatalogTime", query = "SELECT d FROM DocumentDocCatalog d WHERE d.catalogTime = :catalogTime")})
public class DocumentDocCatalog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "document_doc_catalog")
    private Long documentDocCatalog;
    @Column(name = "document_fk")
    private Long documentFk;
    @Column(name = "doc_catalog_fk")
    private Long docCatalogFk;
    @Column(name = "catalog_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date catalogTime;

    public DocumentDocCatalog() {
    }

    public DocumentDocCatalog(Long documentDocCatalog) {
        this.documentDocCatalog = documentDocCatalog;
    }

    public Long getDocumentDocCatalog() {
        return documentDocCatalog;
    }

    public void setDocumentDocCatalog(Long documentDocCatalog) {
        this.documentDocCatalog = documentDocCatalog;
    }

    public Long getDocumentFk() {
        return documentFk;
    }

    public void setDocumentFk(Long documentFk) {
        this.documentFk = documentFk;
    }

    public Long getDocCatalogFk() {
        return docCatalogFk;
    }

    public void setDocCatalogFk(Long docCatalogFk) {
        this.docCatalogFk = docCatalogFk;
    }

    public Date getCatalogTime() {
        return catalogTime;
    }

    public void setCatalogTime(Date catalogTime) {
        this.catalogTime = catalogTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentDocCatalog != null ? documentDocCatalog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentDocCatalog)) {
            return false;
        }
        DocumentDocCatalog other = (DocumentDocCatalog) object;
        if ((this.documentDocCatalog == null && other.documentDocCatalog != null) || (this.documentDocCatalog != null && !this.documentDocCatalog.equals(other.documentDocCatalog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.DocumentDocCatalog[ documentDocCatalog=" + documentDocCatalog + " ]";
    }
    
}
