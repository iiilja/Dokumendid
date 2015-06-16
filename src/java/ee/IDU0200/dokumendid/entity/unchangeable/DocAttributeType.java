/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.entity.unchangeable;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "doc_attribute_type")
@NamedQueries({
    @NamedQuery(name = "DocAttributeType.findAll", query = "SELECT d FROM DocAttributeType d"),
    @NamedQuery(name = "DocAttributeType.findByDocAttributeType", query = "SELECT d FROM DocAttributeType d WHERE d.docAttributeType = :docAttributeType"),
    @NamedQuery(name = "DocAttributeType.findByDefaultSelectionIdFk", query = "SELECT d FROM DocAttributeType d WHERE d.defaultSelectionIdFk = :defaultSelectionIdFk"),
    @NamedQuery(name = "DocAttributeType.findByTypeName", query = "SELECT d FROM DocAttributeType d WHERE d.typeName = :typeName"),
    @NamedQuery(name = "DocAttributeType.findByDefaultValueText", query = "SELECT d FROM DocAttributeType d WHERE d.defaultValueText = :defaultValueText"),
    @NamedQuery(name = "DocAttributeType.findByDataTypeFk", query = "SELECT d FROM DocAttributeType d WHERE d.dataTypeFk = :dataTypeFk"),
    @NamedQuery(name = "DocAttributeType.findByMultipleAttributes", query = "SELECT d FROM DocAttributeType d WHERE d.multipleAttributes = :multipleAttributes")})
public class DocAttributeType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doc_attribute_type")
    private Long docAttributeType;
    @Column(name = "default_selection_id_fk")
    private Long defaultSelectionIdFk;
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "default_value_text")
    private String defaultValueText;
    @Column(name = "data_type_fk")
    private Short dataTypeFk;
    @Column(name = "multiple_attributes")
    private String multipleAttributes;
    
    @Transient
    private List<AtrTypeSelectionValue> selectionValues;

    public DocAttributeType() {
    }

    public DocAttributeType(Long docAttributeType) {
        this.docAttributeType = docAttributeType;
    }

    public Long getDocAttributeType() {
        return docAttributeType;
    }

    public void setDocAttributeType(Long docAttributeType) {
        this.docAttributeType = docAttributeType;
    }

    public Long getDefaultSelectionIdFk() {
        return defaultSelectionIdFk;
    }

    public void setDefaultSelectionIdFk(Long defaultSelectionIdFk) {
        this.defaultSelectionIdFk = defaultSelectionIdFk;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDefaultValueText() {
        return defaultValueText;
    }

    public void setDefaultValueText(String defaultValueText) {
        this.defaultValueText = defaultValueText;
    }

    public Short getDataTypeFk() {
        return dataTypeFk;
    }

    public void setDataTypeFk(Short dataTypeFk) {
        this.dataTypeFk = dataTypeFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docAttributeType != null ? docAttributeType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocAttributeType)) {
            return false;
        }
        DocAttributeType other = (DocAttributeType) object;
        if ((this.docAttributeType == null && other.docAttributeType != null) || (this.docAttributeType != null && !this.docAttributeType.equals(other.docAttributeType))) {
            return false;
        }
        return true;
    }

    public List<AtrTypeSelectionValue> getSelectionValues() {
        return selectionValues;
    }

    public void setSelectionValues(List<AtrTypeSelectionValue> selectionValues) {
        this.selectionValues = selectionValues;
    }
    
    

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.DocAttributeType[ docAttributeType=" + docAttributeType + " ]";
    }
    
}
