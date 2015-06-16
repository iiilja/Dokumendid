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
@Table(name = "document")
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findByDocument", query = "SELECT d FROM Document d WHERE d.document = :document"),
    @NamedQuery(name = "Document.findByDocNr", query = "SELECT d FROM Document d WHERE d.docNr = :docNr"),
    @NamedQuery(name = "Document.findByName", query = "SELECT d FROM Document d WHERE d.name = :name"),
    @NamedQuery(name = "Document.findByDescription", query = "SELECT d FROM Document d WHERE d.description = :description"),
    @NamedQuery(name = "Document.findByCreated", query = "SELECT d FROM Document d WHERE d.created = :created"),
    @NamedQuery(name = "Document.findByCreatedBy", query = "SELECT d FROM Document d WHERE d.createdBy = :createdBy"),
    @NamedQuery(name = "Document.findByUpdated", query = "SELECT d FROM Document d WHERE d.updated = :updated"),
    @NamedQuery(name = "Document.findByUpdatedBy", query = "SELECT d FROM Document d WHERE d.updatedBy = :updatedBy"),
    @NamedQuery(name = "Document.findByDocStatusTypeFk", query = "SELECT d FROM Document d WHERE d.docStatusTypeFk = :docStatusTypeFk"),
    @NamedQuery(name = "Document.findByFilename", query = "SELECT d FROM Document d WHERE d.filename = :filename")})
public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "document")
    private Long document;
    @Column(name = "doc_nr")
    private String docNr;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Column(name = "updated_by")
    private Long updatedBy;
    @Column(name = "doc_status_type_fk")
    private Long docStatusTypeFk;
    @Column(name = "filename")
    private String filename;

    public Document() {
    }

    public Document(Long document) {
        this.document = document;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getDocNr() {
        return docNr;
    }

    public void setDocNr(String docNr) {
        this.docNr = docNr;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getDocStatusTypeFk() {
        return docStatusTypeFk;
    }

    public void setDocStatusTypeFk(Long docStatusTypeFk) {
        this.docStatusTypeFk = docStatusTypeFk;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (document != null ? document.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.document == null && other.document != null) || (this.document != null && !this.document.equals(other.document))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.Document[ document=" + document + " ]";
    }   
}
