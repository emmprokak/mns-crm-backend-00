package com.unipi.mns.mnscrm00.entities.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Contact")
public class Contact {
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

    public Contact(Account account, Date birthdate, String department, String email, String firstName, String id, boolean isActive, String lastName, String mobile, String phone, String prefix, String role) {
        this.account = account;
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
}
