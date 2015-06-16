/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.IDU0200.dokumendid.entity.unchangeable;

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
@Table(name = "enterprise")
@NamedQueries({
    @NamedQuery(name = "Enterprise.findAll", query = "SELECT e FROM Enterprise e"),
    @NamedQuery(name = "Enterprise.findByEnterprise", query = "SELECT e FROM Enterprise e WHERE e.enterprise = :enterprise"),
    @NamedQuery(name = "Enterprise.findByName", query = "SELECT e FROM Enterprise e WHERE UPPER(e.name) = UPPER(:name)"),
    @NamedQuery(name = "Enterprise.findByFullName", query = "SELECT e FROM Enterprise e WHERE e.fullName = :fullName"),
    @NamedQuery(name = "Enterprise.findByCreatedBy", query = "SELECT e FROM Enterprise e WHERE e.createdBy = :createdBy"),
    @NamedQuery(name = "Enterprise.findByUpdatedBy", query = "SELECT e FROM Enterprise e WHERE e.updatedBy = :updatedBy"),
    @NamedQuery(name = "Enterprise.findByCreated", query = "SELECT e FROM Enterprise e WHERE e.created = :created"),
    @NamedQuery(name = "Enterprise.findByUpdated", query = "SELECT e FROM Enterprise e WHERE e.updated = :updated")})
public class Enterprise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "enterprise")
    private Long enterprise;
    @Column(name = "name")
    private String name;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "updated_by")
    private Long updatedBy;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Enterprise() {
    }

    public Enterprise(Long enterprise) {
        this.enterprise = enterprise;
    }

    public Long getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Long enterprise) {
        this.enterprise = enterprise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enterprise != null ? enterprise.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enterprise)) {
            return false;
        }
        Enterprise other = (Enterprise) object;
        if ((this.enterprise == null && other.enterprise != null) || (this.enterprise != null && !this.enterprise.equals(other.enterprise))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.Enterprise[ enterprise=" + enterprise + " ]";
    }
    
}
