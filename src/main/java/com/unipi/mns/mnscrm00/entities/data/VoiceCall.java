package com.unipi.mns.mnscrm00.entities.data;

import com.unipi.mns.mnscrm00.dto.abstracts.VoiceCallDTO;
import com.unipi.mns.mnscrm00.dto.completes.VoiceCallDTOComplete;
import com.unipi.mns.mnscrm00.dto.minimals.VoiceCallDTOMinimal;
import com.unipi.mns.mnscrm00.dto.simples.VoiceCallDTOSimple;
import com.unipi.mns.mnscrm00.entities.abstracts.ChildEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.DataEntity;
import com.unipi.mns.mnscrm00.entities.abstracts.Sendable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="Voice_Call_ent")
public class VoiceCall implements Sendable<VoiceCallDTO>, DataEntity, ChildEntity {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account relatedAccount;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case relatedCase;

    @Column(name="account_id_txt")
    private String relatedAccountId;

    @Column(name="case_id_txt")
    private String relatedCaseId;

    @Column(name="title")
    private String title;

    @Column(name="customer_phone")
    private String customerPhone;

    @Column(name="agent_name")
    private String agentName;

    @Column(name="call_date")
    private Date callDate;

    @Column(name="duration")
    private int duration;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    public VoiceCall(String agentName, Date callDate, String customerPhone, int duration, String id, Account relatedAccount, Case relatedCase, String title) {
        this.agentName = agentName;
        this.callDate = callDate;
        this.customerPhone = customerPhone;
        this.duration = duration;
        this.id = id;
        this.relatedAccount = relatedAccount;
        this.relatedCase = relatedCase;
        this.title = title;
    }

    public VoiceCall() {}

    @Override
    public VoiceCallDTO toDTOSimple() {
        return new VoiceCallDTOSimple(agentName, callDate, customerPhone, duration, id, relatedAccount, relatedCase, title, created, modified);
    }

    @Override
    public VoiceCallDTO toDTOComplete() {
        return new VoiceCallDTOComplete(agentName, callDate, customerPhone, duration, id, relatedAccount, relatedCase, title, created, modified);
    }

    @Override
    public VoiceCallDTO toDTOMinimal() {
        return new VoiceCallDTOMinimal(agentName, callDate, customerPhone, duration, id, relatedAccount, relatedCase, title, created, modified);
    }

    @Override
    public <P> String getParentId(Class<P> entityType) {
        if(entityType == Account.class){
            return relatedAccountId;
        }

        if(entityType == Case.class){
            return relatedCaseId;
        }

        return null;
    }

    @Override
    public <P> P getParent(Class<P> entityType) {
        if(entityType == Account.class){
            return (P) relatedAccount;
        }

        if(entityType == Case.class){
            return (P) relatedCase;
        }

        return null;
    }

    @Override
    public <P> void setParent(Class<P> entityType, P parent) {
        if(entityType == Account.class){
            this.relatedAccount = (Account) parent;
            if(parent != null){
                this.relatedAccountId = ((Account) parent).getId();
            }else{
                this.relatedAccountId = null;
            }
        }

        if(entityType == Case.class){
            this.relatedCase = (Case) parent;
            if(parent != null){
                this.relatedCaseId = ((Case) parent).getId();
            }else{
                this.relatedCaseId = null;
            }
        }
    }

    public String getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(String relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
    }

    public String getRelatedCaseId() {
        return relatedCaseId;
    }

    public void setRelatedCaseId(String relatedCaseId) {
        this.relatedCaseId = relatedCaseId;
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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getRelatedAccount() {
        return relatedAccount;
    }

    public void setRelatedAccount(Account relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

    public Case getRelatedCase() {
        return relatedCase;
    }

    public void setRelatedCase(Case relatedCase) {
        this.relatedCase = relatedCase;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
