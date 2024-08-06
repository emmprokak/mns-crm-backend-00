package com.unipi.mns.mnscrm00.entities.config;

import com.unipi.mns.mnscrm00.entities.abstracts.ConfigEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name="Config_cfg")
public class GenericConfigEntity implements ConfigEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name="type")
    private String type;

    @Column(name="display_text")
    private String displayText;

    @Column(name="api_value")
    private String apiValue;

    public GenericConfigEntity(String apiValue, String displayText, String id, String type) {
        this.apiValue = apiValue;
        this.displayText = displayText;
        this.id = id;
        this.type = type;
    }

    public GenericConfigEntity() {}

    public String getApiValue() {
        return apiValue;
    }

    public void setApiValue(String apiValue) {
        this.apiValue = apiValue;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
