package com.hackathon.medibox.model;

public class TableInfoResponse {

    private String id;
    private StripDetails details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StripDetails getDetails() {
        return details;
    }

    public void setDetails(StripDetails details) {
        this.details = details;
    }

}
