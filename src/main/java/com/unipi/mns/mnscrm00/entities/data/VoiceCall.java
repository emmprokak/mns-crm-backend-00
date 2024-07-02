package com.unipi.mns.mnscrm00.entities.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Entity
@Table(name="Voice_Call")
public class VoiceCall {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account relatedAccount;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case relatedCase;

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
