package com.unipi.mns.mnscrm00.entities.data;

import com.unipi.mns.mnscrm00.dto.abstracts.ContactDTO;
import com.unipi.mns.mnscrm00.dto.completes.ContactDTOComplete;
import com.unipi.mns.mnscrm00.dto.minimals.ContactDTOMinimal;
import com.unipi.mns.mnscrm00.dto.simples.ContactDTOSimple;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Contact_ent")
public class Contact implements Sendable<ContactDTO>, DataEntity {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Case> cases;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="mobile")
    private String mobile;

    @Column(name = "prefix")
    private String prefix;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="birthdate")
    private Date birthdate;

    @Column(name="department")
    private String department;

    @Column(name="role")
    private String role;

    @Column(name="account_id_txt")
    private String accountId;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    public Contact(Account account, Date birthdate, String department, String email, String firstName, String id, boolean isActive, String lastName, String mobile, String phone, String prefix, String role, LocalDateTime created, LocalDateTime modified) {
        this.birthdate = birthdate;
        this.department = department;
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.isActive = isActive;
        this.lastName = lastName;
        this.mobile = mobile;
        this.phone = phone;
        this.prefix = prefix;
        this.role = role;
        this.created = created;
        this.modified = modified;
        this.account = account;

        if(account != null){
            this.accountId = account.getId();
        }
    }

    public Contact() {}

    public List<Case> getCases() {
        return cases;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public ContactDTO toDTOSimple() {
        return new ContactDTOSimple(account, birthdate, department, email, firstName, id, isActive, lastName, mobile, phone, prefix, role, created, modified);
    }

    @Override
    public ContactDTO toDTOComplete() {
        return new ContactDTOComplete(cases, account, birthdate, department, email, firstName, id, isActive, lastName, mobile, phone, prefix, role, created, modified);
    }

    @Override
    public ContactDTO toDTOMinimal() {
        return new ContactDTOMinimal(account, birthdate, department, email, firstName, id, isActive, lastName, mobile, phone, prefix, role, created, modified);
    }
}
