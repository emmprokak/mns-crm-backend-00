package com.unipi.mns.mnscrm00.dto.abstracts;

import com.unipi.mns.mnscrm00.entities.data.Account;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class ContactDTO implements EntityDTO{
    private String id;
    private String accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String mobile;
    private String prefix;
    private boolean isActive;
    private Date birthdate;
    private String department;
    private String role;
    private LocalDateTime created;
    private LocalDateTime modified;

    public ContactDTO(Account account, Date birthdate, String department, String email, String firstName, String id, boolean isActive, String lastName, String mobile, String phone, String prefix, String role, LocalDateTime created, LocalDateTime modified) {
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

        if(account != null){
            this.accountId = account.getId();
        }
    }

    public ContactDTO(){

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
}
