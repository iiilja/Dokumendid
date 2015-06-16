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
@Table(name = "doc_catalog")
@NamedQueries({
    @NamedQuery(name = "DocCatalog.findAll", query = "SELECT d FROM DocCatalog d"),
    @NamedQuery(name = "DocCatalog.findByDocCatalog", query = "SELECT d FROM DocCatalog d WHERE d.docCatalog = :docCatalog"),
    @NamedQuery(name = "DocCatalog.findByCatalogOwnerFk", query = "SELECT d FROM DocCatalog d WHERE d.catalogOwnerFk = :catalogOwnerFk"),
    @NamedQuery(name = "DocCatalog.findByDocCatalogTypeFk", query = "SELECT d FROM DocCatalog d WHERE d.docCatalogTypeFk = :docCatalogTypeFk"),
    @NamedQuery(name = "DocCatalog.findByName", query = "SELECT d FROM DocCatalog d WHERE d.name = :name"),
    @NamedQuery(name = "DocCatalog.findByDescription", query = "SELECT d FROM DocCatalog d WHERE d.description = :description"),
    @NamedQuery(name = "DocCatalog.findByLevel", query = "SELECT d FROM DocCatalog d WHERE d.level = :level"),
    @NamedQuery(name = "DocCatalog.findByContentUpdated", query = "SELECT d FROM DocCatalog d WHERE d.contentUpdated = :contentUpdated"),
    @NamedQuery(name = "DocCatalog.findByContentUpdatedBy", query = "SELECT d FROM DocCatalog d WHERE d.contentUpdatedBy = :contentUpdatedBy"),
    @NamedQuery(name = "DocCatalog.findByUpperCatalogFk", query = "SELECT d FROM DocCatalog d WHERE d.upperCatalogFk = :upperCatalogFk"),
    @NamedQuery(name = "DocCatalog.findByFolder", query = "SELECT d FROM DocCatalog d WHERE d.folder = :folder")})
public class DocCatalog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doc_catalog")
    private Long docCatalog;
    @Column(name = "catalog_owner_fk")
    private Long catalogOwnerFk;
    @Column(name = "doc_catalog_type_fk")
    private Long docCatalogTypeFk;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "level")
    private Long level;
    @Column(name = "content_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contentUpdated;
    @Column(name = "content_updated_by")
    private Long contentUpdatedBy;
    @Column(name = "upper_catalog_fk")
    private Long upperCatalogFk;
    @Column(name = "folder")
    private String folder;

    public DocCatalog() {
    }

    public DocCatalog(Long docCatalog) {
        this.docCatalog = docCatalog;
    }

    public Long getDocCatalog() {
        return docCatalog;
    }

    public void setDocCatalog(Long docCatalog) {
        this.docCatalog = docCatalog;
    }

    public Long getCatalogOwnerFk() {
        return catalogOwnerFk;
    }

    public void setCatalogOwnerFk(Long catalogOwnerFk) {
        this.catalogOwnerFk = catalogOwnerFk;
    }

    public Long getDocCatalogTypeFk() {
        return docCatalogTypeFk;
    }

    public void setDocCatalogTypeFk(Long docCatalogTypeFk) {
        this.docCatalogTypeFk = docCatalogTypeFk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Date getContentUpdated() {
        return contentUpdated;
    }

    public void setContentUpdated(Date contentUpdated) {
        this.contentUpdated = contentUpdated;
    }

    public Long getContentUpdatedBy() {
        return contentUpdatedBy;
    }

    public void setContentUpdatedBy(Long contentUpdatedBy) {
        this.contentUpdatedBy = contentUpdatedBy;
    }

    public Long getUpperCatalogFk() {
        return upperCatalogFk;
    }

    public void setUpperCatalogFk(Long upperCatalogFk) {
        this.upperCatalogFk = upperCatalogFk;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docCatalog != null ? docCatalog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocCatalog)) {
            return false;
        }
        DocCatalog other = (DocCatalog) object;
        if ((this.docCatalog == null && other.docCatalog != null) || (this.docCatalog != null && !this.docCatalog.equals(other.docCatalog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.DocCatalog[ docCatalog=" + docCatalog + " ]";
    }
    
}
