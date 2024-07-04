package com.unipi.mns.mnscrm00.entities.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name="Note_ent")
public class Note {
    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case relatedCase;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead relatedLead;

    @ManyToOne
    @JoinColumn(name = "opportunity_id")
    private Opportunity relatedOpportunity;

    @Column(name="content")
    private String content;

    public Note(String content, String id, Case relatedCase, Lead relatedLead, Opportunity relatedOpportunity) {
        this.content = content;
        this.id = id;
        this.relatedCase = relatedCase;
        this.relatedLead = relatedLead;
        this.relatedOpportunity = relatedOpportunity;
    }

    public Note() {}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Case getRelatedCase() {
        return relatedCase;
    }

    public void setRelatedCase(Case relatedCase) {
        this.relatedCase = relatedCase;
    }

    public Lead getRelatedLead() {
        return relatedLead;
    }

    public void setRelatedLead(Lead relatedLead) {
        this.relatedLead = relatedLead;
    }

    public Opportunity getRelatedOpportunity() {
        return relatedOpportunity;
    }

    public void setRelatedOpportunity(Opportunity relatedOpportunity) {
        this.relatedOpportunity = relatedOpportunity;
    }
}
