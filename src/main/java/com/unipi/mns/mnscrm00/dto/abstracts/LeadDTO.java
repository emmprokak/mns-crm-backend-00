package com.unipi.mns.mnscrm00.dto.abstracts;

import com.unipi.mns.mnscrm00.entities.data.Note;
import com.unipi.mns.mnscrm00.entities.data.Task;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public abstract class LeadDTO implements EntityDTO {

    private String id;
    private String companyName;
    private String contactPerson;
    private String contactPrefix;
    private String companyAddress;
    private String contactRole;
    private String contactEmail;
    private String contactMobile;
    private String contactPhone;
    private String companyIndustry;
    private String status;
    private LocalDateTime created;
    private LocalDateTime modified;

    public LeadDTO(String companyAddress, String companyIndustry, String companyName, String contactEmail, String contactMobile, String contactPerson, String contactPhone, String contactPrefix, String contactRole, String id, String status, LocalDateTime created, LocalDateTime modified) {
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
        this.created = created;
        this.modified = modified;
    }

    public LeadDTO() {

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
