package com.indibase.conconi.models;

public class InfoItem {

    private String title;
    private String text;
    private String positionID;

    public InfoItem(String positionID, String title, String text) {
        this.positionID = positionID;
        this.title = title;
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getPositionID() {
        return positionID;
    }
}
