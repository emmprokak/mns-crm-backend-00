package com.unipi.mns.mnscrm00.entities.builders;

import com.unipi.mns.mnscrm00.entities.data.Case;

import java.util.Date;

public class CaseBuilder implements EntityBuilder<Case>{
    private Case caseEntry;

    @Override
    public Case build() {
        return caseEntry;
    }

    public CaseBuilder(){
        this.caseEntry = new Case();
    }

    public CaseBuilder(Case acc){
        this.caseEntry = acc;
    }


    public CaseBuilder setTitle(String title) {
        caseEntry.setTitle(title);
        return this;
    }

    public CaseBuilder setReason(String reason) {
        caseEntry.setReason(reason);
        return this;
    }

    public CaseBuilder setSource(String source) {
        caseEntry.setSource(source);
        return this;
    }

    public CaseBuilder setCategory(String category) {
        caseEntry.setCategory(category);
        return this;
    }

    public CaseBuilder setSeverity(String severity) {
        caseEntry.setSeverity(severity);
        return this;
    }

    public CaseBuilder setStatus(String status) {
        caseEntry.setStatus(status);
        return this;
    }

    public CaseBuilder setCreationDate(Date creationDate) {
        caseEntry.setCreationDate(creationDate);
        return this;
    }

    public CaseBuilder setClosedDate(Date closedDate) {
        caseEntry.setClosedDate(closedDate);
        return this;
    }

}
