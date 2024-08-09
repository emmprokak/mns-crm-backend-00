package com.unipi.mns.mnscrm00.dto.processes;

public class ProcessOutputDTO {
    private String processType;
    private String field;
    private String value;

    public ProcessOutputDTO(String field, String processType, String value) {
        this.field = field;
        this.processType = processType;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
