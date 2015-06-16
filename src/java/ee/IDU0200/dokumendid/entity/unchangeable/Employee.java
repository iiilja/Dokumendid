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
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmployee", query = "SELECT e FROM Employee e WHERE e.employee = :employee"),
    @NamedQuery(name = "Employee.findByPersonFk", query = "SELECT e FROM Employee e WHERE e.personFk = :personFk"),
    @NamedQuery(name = "Employee.findByEnterpriseFk", query = "SELECT e FROM Employee e WHERE e.enterpriseFk = :enterpriseFk"),
    @NamedQuery(name = "Employee.findByStructUnitFk", query = "SELECT e FROM Employee e WHERE e.structUnitFk = :structUnitFk"),
    @NamedQuery(name = "Employee.findByActive", query = "SELECT e FROM Employee e WHERE e.active = :active")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "employee")
    private Long employee;
    @Column(name = "person_fk")
    private Long personFk;
    @Column(name = "enterprise_fk")
    private Long enterpriseFk;
    @Column(name = "struct_unit_fk")
    private Long structUnitFk;
    @Column(name = "active")
    private String active;

    public Employee() {
    }

    public Employee(Long employee) {
        this.employee = employee;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Long getPersonFk() {
        return personFk;
    }

    public void setPersonFk(Long personFk) {
        this.personFk = personFk;
    }

    public Long getEnterpriseFk() {
        return enterpriseFk;
    }

    public void setEnterpriseFk(Long enterpriseFk) {
        this.enterpriseFk = enterpriseFk;
    }

    public Long getStructUnitFk() {
        return structUnitFk;
    }

    public void setStructUnitFk(Long structUnitFk) {
        this.structUnitFk = structUnitFk;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employee != null ? employee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employee == null && other.employee != null) || (this.employee != null && !this.employee.equals(other.employee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.Employee[ employee=" + employee + " ]";
    }
    
}
