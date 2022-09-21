package com.hackathon.medibox.model;

import io.swagger.annotations.ApiModelProperty;

public class TabletDetailsRequest {

    @ApiModelProperty(value = "Tablet name", example = "dolo650", position = 0)
    private String tabletName;
    @ApiModelProperty(value = "No of tablets inside strip", example = "10", position = 1)
    private int quantity;
    @ApiModelProperty(value = "tablet strip weight(in grams)", example = "20", position = 2)
    private float weight;
    @ApiModelProperty(value = "Tablet manufacture date(yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z')", example = "2022-05-20T07:06:52.881Z", position = 4)
    private String dateOfManufacture;
    @ApiModelProperty(value = "Tablet expiry date(yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z')", example = "2023-09-20T07:06:52.881Z", position = 5)
    private String dateOfExpiry;

    public TabletDetailsRequest() {
        super();
    }

    public String getTabletName() {
        return tabletName;
    }

    public void setTabletName(String tabletName) {
        this.tabletName = tabletName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

}
