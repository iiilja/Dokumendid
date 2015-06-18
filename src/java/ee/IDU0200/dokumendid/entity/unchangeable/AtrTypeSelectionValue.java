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
import javax.persistence.Transient;

/**
 *
 * @author ilja
 */
@Entity
@Table(name = "atr_type_selection_value")
@NamedQueries({
    @NamedQuery(name = "AtrTypeSelectionValue.findAll", query = "SELECT a FROM AtrTypeSelectionValue a"),
    @NamedQuery(name = "AtrTypeSelectionValue.findByAtrTypeSelectionValue", query = "SELECT a FROM AtrTypeSelectionValue a WHERE a.atrTypeSelectionValue = :atrTypeSelectionValue"),
    @NamedQuery(name = "AtrTypeSelectionValue.findByDocAttributeTypeFk", query = "SELECT a FROM AtrTypeSelectionValue a WHERE a.docAttributeTypeFk = :docAttributeTypeFk"),
    @NamedQuery(name = "AtrTypeSelectionValue.findByValueText", query = "SELECT a FROM AtrTypeSelectionValue a WHERE a.valueText = :valueText"),
    @NamedQuery(name = "AtrTypeSelectionValue.findByOrderby", query = "SELECT a FROM AtrTypeSelectionValue a WHERE a.orderby = :orderby")})
public class AtrTypeSelectionValue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "atr_type_selection_value")
    private Long atrTypeSelectionValue;
    @Column(name = "doc_attribute_type_fk")
    private Long docAttributeTypeFk;
    @Column(name = "value_text")
    private String valueText;
    @Column(name = "orderby")
    private Long orderby;
    
    @Transient
    private boolean selected;

    public AtrTypeSelectionValue() {
    }

    public AtrTypeSelectionValue(Long atrTypeSelectionValue) {
        this.atrTypeSelectionValue = atrTypeSelectionValue;
    }

    public Long getAtrTypeSelectionValue() {
        return atrTypeSelectionValue;
    }

    public void setAtrTypeSelectionValue(Long atrTypeSelectionValue) {
        this.atrTypeSelectionValue = atrTypeSelectionValue;
    }

    public Long getDocAttributeTypeFk() {
        return docAttributeTypeFk;
    }

    public void setDocAttributeTypeFk(Long docAttributeTypeFk) {
        this.docAttributeTypeFk = docAttributeTypeFk;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public Long getOrderby() {
        return orderby;
    }

    public void setOrderby(Long orderby) {
        this.orderby = orderby;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean b) {
        this.selected = b;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atrTypeSelectionValue != null ? atrTypeSelectionValue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtrTypeSelectionValue)) {
            return false;
        }
        AtrTypeSelectionValue other = (AtrTypeSelectionValue) object;
        if ((this.atrTypeSelectionValue == null && other.atrTypeSelectionValue != null) || (this.atrTypeSelectionValue != null && !this.atrTypeSelectionValue.equals(other.atrTypeSelectionValue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.AtrTypeSelectionValue[ atrTypeSelectionValue=" + atrTypeSelectionValue + " ]";
    }
    
}
