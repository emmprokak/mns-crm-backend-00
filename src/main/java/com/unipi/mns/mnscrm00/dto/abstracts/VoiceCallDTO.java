package com.unipi.mns.mnscrm00.dto.abstracts;

import com.unipi.mns.mnscrm00.entities.data.Account;
import com.unipi.mns.mnscrm00.entities.data.Case;

import java.time.LocalDateTime;
import java.util.Date;

public class VoiceCallDTO implements EntityDTO{
    private String id;
    private String relatedAccountId;
    private String relatedCaseId;
    private String title;
    private String customerPhone;
    private String agentName;
    private Date callDate;
    private int duration;
    private LocalDateTime created;
    private LocalDateTime modified;

    public VoiceCallDTO(String agentName, Date callDate, String customerPhone, int duration, String id, Account account, Case caseRecord, String title, LocalDateTime created, LocalDateTime modified) {
        this.agentName = agentName;
        this.callDate = callDate;
        this.customerPhone = customerPhone;
        this.duration = duration;
        this.id = id;
        this.title = title;
        this.created = created;
        this.modified = modified;

        if(account != null){
            this.relatedAccountId = account.getId();
        }

        if(caseRecord != null){
            this.relatedCaseId = caseRecord.getId();
        }
    }

    public VoiceCallDTO(){}

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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
