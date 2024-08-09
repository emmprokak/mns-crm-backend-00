package com.unipi.mns.mnscrm00.entities.data;

import com.unipi.mns.mnscrm00.dto.abstracts.LeadDTO;
import com.unipi.mns.mnscrm00.dto.completes.LeadDTOComplete;
import com.unipi.mns.mnscrm00.dto.minimals.LeadDTOMinimal;
import com.unipi.mns.mnscrm00.dto.simples.LeadDTOSimple;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.ParentEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Lead_ent")
public class Lead implements Sendable<LeadDTO>, DataEntity, ParentEntity {
    @Id
    @UuidGenerator
    private String id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    public Lead(String companyAddress, String companyIndustry, String companyName, String contactEmail, String contactMobile, String contactPerson, String contactPhone, String contactPrefix, String contactRole, String id, String status, LocalDateTime created, LocalDateTime modified) {
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

    public Lead() {}

    @Override
    public LeadDTO toDTOSimple() {
        return new LeadDTOSimple(companyAddress, companyIndustry, companyName, contactEmail, contactMobile, contactPerson, contactPhone, contactPrefix, contactRole, id, status, created, modified);
    }

    @Override
    public LeadDTO toDTOComplete() {
        return new LeadDTOComplete(companyAddress, companyIndustry, companyName, contactEmail, contactMobile, contactPerson, contactPhone, contactPrefix, contactRole, id, status, tasks, created, modified);
    }

    @Override
    public LeadDTO toDTOMinimal() {
        return new LeadDTOMinimal(companyAddress, companyIndustry, companyName, contactEmail, contactMobile, contactPerson, contactPhone, contactPrefix, contactRole, id, status, created, modified);
    }

    @Override
    public <C> List<C> getChildrenEntities(Class<C> childType) {
        if (childType == Task.class) {
            return (List<C>) tasks;
        }

        return new ArrayList<>();
    }

    @Override
    public <C> void addChild(Class<C> childType, C child) {
        if (childType == Task.class) {
            tasks.add((Task) child);
        }
    }

    @Override
    public <C> void removeChild(Class<C> childType, C child) {
        if (childType == Task.class) {
            tasks.remove((Task) child);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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
