package com.hackathon.medibox.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ "id", "tabletDetailsRequest" })
public class TabletInfoRequest {

    @ApiModelProperty(value = "Tablet id", example = "test123", position = 0)
    private String id;

    @JsonProperty("details")
    @ApiModelProperty(position = 1)
    private TabletDetailsRequest tabletDetailsRequest;

    public TabletInfoRequest() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TabletDetailsRequest getTabletDetailsRequest() {
        return tabletDetailsRequest;
    }

    public void setTabletDetailsRequest(TabletDetailsRequest tabletDetailsRequest) {
        this.tabletDetailsRequest = tabletDetailsRequest;
    }

}
