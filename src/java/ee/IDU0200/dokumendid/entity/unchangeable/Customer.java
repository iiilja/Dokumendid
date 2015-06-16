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

/**
 *
 * @author ilja
 */
@Entity
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustomer", query = "SELECT c FROM Customer c WHERE c.customer = :customer"),
    @NamedQuery(name = "Customer.findBySubjectFk", query = "SELECT c FROM Customer c WHERE c.subjectFk = :subjectFk"),
    @NamedQuery(name = "Customer.findBySubjectTypeFk", query = "SELECT c FROM Customer c WHERE c.subjectTypeFk = :subjectTypeFk")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer")
    private Long customer;
    @Column(name = "subject_fk")
    private Long subjectFk;
    @Column(name = "subject_type_fk")
    private Long subjectTypeFk;

    public Customer() {
    }

    public Customer(Long customer) {
        this.customer = customer;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Long getSubjectFk() {
        return subjectFk;
    }

    public void setSubjectFk(Long subjectFk) {
        this.subjectFk = subjectFk;
    }

    public Long getSubjectTypeFk() {
        return subjectTypeFk;
    }

    public void setSubjectTypeFk(Long subjectTypeFk) {
        this.subjectTypeFk = subjectTypeFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customer != null ? customer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customer == null && other.customer != null) || (this.customer != null && !this.customer.equals(other.customer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.Customer[ customer=" + customer + " ]";
    }
    
}
