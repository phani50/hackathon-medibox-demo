package com.hackathon.medibox.model;

public class TableInfoResponse {

    private String id;
    private TableDetails details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TableDetails getDetails() {
        return details;
    }

    public void setDetails(TableDetails details) {
        this.details = details;
    }

}
