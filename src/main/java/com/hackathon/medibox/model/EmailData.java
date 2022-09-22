package com.hackathon.medibox.model;

import io.swagger.annotations.ApiModelProperty;

public class EmailData {
    @ApiModelProperty(value = "From email", example = "venkata.karunakar@nagra.com", position = 0)
    private String fromEmailAddress;
    @ApiModelProperty(value = "To email", example = "dl-iot-deviceeagriffin@nagragrp.mail.onmicrosoft.com", position = 1)
    private String toEmailAddress;
    @ApiModelProperty(value = "Subject of email", example = "Item is missing", position = 2)
    private String subject;
    @ApiModelProperty(value = "Body of email", example = "Item is missing", position = 3)
    private String body;

    public EmailData() {
        super();
    }

    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    public void setFromEmailAddress(String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    public String getToEmailAddress() {
        return toEmailAddress;
    }

    public void setToEmailAddress(String toEmailAddress) {
        this.toEmailAddress = toEmailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
