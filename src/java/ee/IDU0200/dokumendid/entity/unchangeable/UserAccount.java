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
@Table(name = "user_account")
@NamedQueries({
    @NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u"),
    @NamedQuery(name = "UserAccount.findByUserAccount", query = "SELECT u FROM UserAccount u WHERE u.userAccount = :userAccount"),
    @NamedQuery(name = "UserAccount.findBySubjectTypeFk", query = "SELECT u FROM UserAccount u WHERE u.subjectTypeFk = :subjectTypeFk"),
    @NamedQuery(name = "UserAccount.findBySubjectFk", query = "SELECT u FROM UserAccount u WHERE u.subjectFk = :subjectFk"),
    @NamedQuery(name = "UserAccount.findByUsername", query = "SELECT u FROM UserAccount u WHERE u.username = :username"),
    @NamedQuery(name = "UserAccount.findByPassw", query = "SELECT u FROM UserAccount u WHERE u.passw = :passw"),
    @NamedQuery(name = "UserAccount.findByStatus", query = "SELECT u FROM UserAccount u WHERE u.status = :status"),
    @NamedQuery(name = "UserAccount.findByValidFrom", query = "SELECT u FROM UserAccount u WHERE u.validFrom = :validFrom"),
    @NamedQuery(name = "UserAccount.findByValidTo", query = "SELECT u FROM UserAccount u WHERE u.validTo = :validTo"),
    @NamedQuery(name = "UserAccount.findByCreatedBy", query = "SELECT u FROM UserAccount u WHERE u.createdBy = :createdBy"),
    @NamedQuery(name = "UserAccount.findByCreated", query = "SELECT u FROM UserAccount u WHERE u.created = :created"),
    @NamedQuery(name = "UserAccount.findByPasswordNeverExpires", query = "SELECT u FROM UserAccount u WHERE u.passwordNeverExpires = :passwordNeverExpires")})
public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_account")
    private Long userAccount;
    @Column(name = "subject_type_fk")
    private Long subjectTypeFk;
    @Column(name = "subject_fk")
    private Long subjectFk;
    @Column(name = "username")
    private String username;
    @Column(name = "passw")
    private String passw;
    @Column(name = "status")
    private Long status;
    @Column(name = "valid_from")
    @Temporal(TemporalType.DATE)
    private Date validFrom;
    @Column(name = "valid_to")
    @Temporal(TemporalType.DATE)
    private Date validTo;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "password_never_expires")
    private String passwordNeverExpires;

    public UserAccount() {
    }

    public UserAccount(Long userAccount) {
        this.userAccount = userAccount;
    }

    public Long getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Long userAccount) {
        this.userAccount = userAccount;
    }

    public Long getSubjectTypeFk() {
        return subjectTypeFk;
    }

    public void setSubjectTypeFk(Long subjectTypeFk) {
        this.subjectTypeFk = subjectTypeFk;
    }

    public Long getSubjectFk() {
        return subjectFk;
    }

    public void setSubjectFk(Long subjectFk) {
        this.subjectFk = subjectFk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPasswordNeverExpires() {
        return passwordNeverExpires;
    }

    public void setPasswordNeverExpires(String passwordNeverExpires) {
        this.passwordNeverExpires = passwordNeverExpires;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userAccount != null ? userAccount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) object;
        if ((this.userAccount == null && other.userAccount != null) || (this.userAccount != null && !this.userAccount.equals(other.userAccount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.IDU0200.dokumendid.entity.unchangeable.UserAccount[ userAccount=" + userAccount + " ]";
    }
    
}
