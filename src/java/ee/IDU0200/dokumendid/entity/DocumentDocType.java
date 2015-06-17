/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.entity;

import java.io.Serializable;
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

/**
 *
 * @author ilja
 */
@Entity
@Table(name = "document_doc_type")
@NamedQueries({
    @NamedQuery(name = "DocumentDocType.findAll", query = "SELECT d FROM DocumentDocType d"),
    @NamedQuery(name = "DocumentDocType.findByDocumentDocType", query = "SELECT d FROM DocumentDocType d WHERE d.documentDocType = :documentDocType"),
    @NamedQuery(name = "DocumentDocType.findByDocTypeFk", query = "SELECT d FROM DocumentDocType d WHERE d.docTypeFk = :docTypeFk"),
    @NamedQuery(name = "DocumentDocType.findByDocumentFk", query = "SELECT d FROM DocumentDocType d WHERE d.documentFk = :documentFk")})
public class DocumentDocType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "document_doc_type_id")
    @Column(name = "document_doc_type")
    private Long documentDocType;
    @Column(name = "doc_type_fk")
    private Long docTypeFk;
    @Column(name = "document_fk")
    private Long documentFk;

    public DocumentDocType() {
    }

    public DocumentDocType(Long documentDocType) {
        this.documentDocType = documentDocType;
    }

    public Long getDocumentDocType() {
        return documentDocType;
    }

    public void setDocumentDocType(Long documentDocType) {
        this.documentDocType = documentDocType;
    }

    public Long getDocTypeFk() {
        return docTypeFk;
    }

    public void setDocTypeFk(Long docTypeFk) {
        this.docTypeFk = docTypeFk;
    }

    public Long getDocumentFk() {
        return documentFk;
    }

    public void setDocumentFk(Long documentFk) {
        this.documentFk = documentFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentDocType != null ? documentDocType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentDocType)) {
            return false;
        }
        DocumentDocType other = (DocumentDocType) object;
        if ((this.documentDocType == null && other.documentDocType != null) || (this.documentDocType != null && !this.documentDocType.equals(other.documentDocType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.DocumentDocType[ documentDocType=" + documentDocType + " ]";
    }
    
}
