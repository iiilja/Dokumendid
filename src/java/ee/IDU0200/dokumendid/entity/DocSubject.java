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
@Table(name = "doc_subject")
@NamedQueries({
    @NamedQuery(name = "DocSubject.findAll", query = "SELECT d FROM DocSubject d"),
    @NamedQuery(name = "DocSubject.findByDocSubject", query = "SELECT d FROM DocSubject d WHERE d.docSubject = :docSubject"),
    @NamedQuery(name = "DocSubject.findByDocSubjectRelationTypeFk", query = "SELECT d FROM DocSubject d WHERE d.docSubjectRelationTypeFk = :docSubjectRelationTypeFk"),
    @NamedQuery(name = "DocSubject.findByDocSubjectTypeFk", query = "SELECT d FROM DocSubject d WHERE d.docSubjectTypeFk = :docSubjectTypeFk"),
    @NamedQuery(name = "DocSubject.findByDocumentFk", query = "SELECT d FROM DocSubject d WHERE d.documentFk = :documentFk"),
    @NamedQuery(name = "DocSubject.findBySubjectFk", query = "SELECT d FROM DocSubject d WHERE d.subjectFk = :subjectFk"),
    @NamedQuery(name = "DocSubject.findByNote", query = "SELECT d FROM DocSubject d WHERE d.note = :note")})
public class DocSubject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "doc_subject_id")
    @Column(name = "doc_subject")
    private Long docSubject;
    @Column(name = "doc_subject_relation_type_fk")
    private Long docSubjectRelationTypeFk;
    @Column(name = "doc_subject_type_fk")
    private Long docSubjectTypeFk;
    @Column(name = "document_fk")
    private Long documentFk;
    @Column(name = "subject_fk")
    private Long subjectFk;
    @Column(name = "note")
    private String note;

    public DocSubject() {
    }

    public DocSubject(Long docSubject) {
        this.docSubject = docSubject;
    }

    public Long getDocSubject() {
        return docSubject;
    }

    public void setDocSubject(Long docSubject) {
        this.docSubject = docSubject;
    }

    public Long getDocSubjectRelationTypeFk() {
        return docSubjectRelationTypeFk;
    }

    public void setDocSubjectRelationTypeFk(Long docSubjectRelationTypeFk) {
        this.docSubjectRelationTypeFk = docSubjectRelationTypeFk;
    }

    public Long getDocSubjectTypeFk() {
        return docSubjectTypeFk;
    }

    public void setDocSubjectTypeFk(Long docSubjectTypeFk) {
        this.docSubjectTypeFk = docSubjectTypeFk;
    }

    public Long getDocumentFk() {
        return documentFk;
    }

    public void setDocumentFk(Long documentFk) {
        this.documentFk = documentFk;
    }

    public Long getSubjectFk() {
        return subjectFk;
    }

    public void setSubjectFk(Long subjectFk) {
        this.subjectFk = subjectFk;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docSubject != null ? docSubject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocSubject)) {
            return false;
        }
        DocSubject other = (DocSubject) object;
        if ((this.docSubject == null && other.docSubject != null) || (this.docSubject != null && !this.docSubject.equals(other.docSubject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.DocSubject[ docSubject=" + docSubject + " ]";
    }
    
}
