package com.hackathon.medibox.model;

import java.util.Map;

public class Note {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;

    public Note() {
        // TODO Auto-generated constructor stub
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
