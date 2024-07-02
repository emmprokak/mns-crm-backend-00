package com.unipi.mns.mnscrm00.entities.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table(name="Lead")
public class Lead {
    @Id
    @UuidGenerator
    private String id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> notes;

    @Column(name="company_name")
    private String companyName;

    @Column(name="contact_person")
    private String contactPerson;

    @Column(name="contact_prefix")
    private String contactPrefix;

    @Column(name="company_address")
    private String companyAddress;

    @Column(name="contact_role")
    private String contactRole;

    @Column(name="contact_email")
    private String contactEmail;

    @Column(name="contact_mobile")
    private String contactMobile;

    @Column(name="contact_phone")
    private String contactPhone;

    @Column(name="company_industry")
    private String companyIndustry;

    @Column(name="status")
    private String status;

    public Lead(String companyAddress, String companyIndustry, String companyName, String contactEmail, String contactMobile, String contactPerson, String contactPhone, String contactPrefix, String contactRole, String id, String status) {
        this.companyAddress = companyAddress;
        this.companyIndustry = companyIndustry;
        this.companyName = companyName;
        this.contactEmail = contactEmail;
        this.contactMobile = contactMobile;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
        this.contactPrefix = contactPrefix;
        this.contactRole = contactRole;
        this.id = id;
        this.status = status;
    }

    public Lead() {}

    public List<Note> getNotes() {
        return notes;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPrefix() {
        return contactPrefix;
    }

    public void setContactPrefix(String contactPrefix) {
        this.contactPrefix = contactPrefix;
    }

    public String getContactRole() {
        return contactRole;
    }

    public void setContactRole(String contactRole) {
        this.contactRole = contactRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
