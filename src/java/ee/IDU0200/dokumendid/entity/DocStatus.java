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
@Table(name = "doc_status")
@NamedQueries({
    @NamedQuery(name = "DocStatus.findAll", query = "SELECT d FROM DocStatus d"),
    @NamedQuery(name = "DocStatus.findByDocStatus", query = "SELECT d FROM DocStatus d WHERE d.docStatus = :docStatus"),
    @NamedQuery(name = "DocStatus.findByDocumentFk", query = "SELECT d FROM DocStatus d WHERE d.documentFk = :documentFk"),
    @NamedQuery(name = "DocStatus.findByDocStatusTypeFk", query = "SELECT d FROM DocStatus d WHERE d.docStatusTypeFk = :docStatusTypeFk"),
    @NamedQuery(name = "DocStatus.findByStatusBegin", query = "SELECT d FROM DocStatus d WHERE d.statusBegin = :statusBegin"),
    @NamedQuery(name = "DocStatus.findByStatusEnd", query = "SELECT d FROM DocStatus d WHERE d.statusEnd = :statusEnd"),
    @NamedQuery(name = "DocStatus.findByCreatedBy", query = "SELECT d FROM DocStatus d WHERE d.createdBy = :createdBy")})
public class DocStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "doc_status_id")
    @Column(name = "doc_status")
    private Long docStatus;
    @Column(name = "document_fk")
    private Long documentFk;
    @Column(name = "doc_status_type_fk")
    private Long docStatusTypeFk;
    @Column(name = "status_begin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusBegin;
    @Column(name = "status_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusEnd;
    @Column(name = "created_by")
    private Long createdBy;

    public DocStatus() {
    }

    public DocStatus(Long docStatus) {
        this.docStatus = docStatus;
    }

    public Long getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(Long docStatus) {
        this.docStatus = docStatus;
    }

    public Long getDocumentFk() {
        return documentFk;
    }

    public void setDocumentFk(Long documentFk) {
        this.documentFk = documentFk;
    }

    public Long getDocStatusTypeFk() {
        return docStatusTypeFk;
    }

    public void setDocStatusTypeFk(Long docStatusTypeFk) {
        this.docStatusTypeFk = docStatusTypeFk;
    }

    public Date getStatusBegin() {
        return statusBegin;
    }

    public void setStatusBegin(Date statusBegin) {
        this.statusBegin = statusBegin;
    }

    public Date getStatusEnd() {
        return statusEnd;
    }

    public void setStatusEnd(Date statusEnd) {
        this.statusEnd = statusEnd;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docStatus != null ? docStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocStatus)) {
            return false;
        }
        DocStatus other = (DocStatus) object;
        if ((this.docStatus == null && other.docStatus != null) || (this.docStatus != null && !this.docStatus.equals(other.docStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.DocStatus[ docStatus=" + docStatus + " ]";
    }
    
}
