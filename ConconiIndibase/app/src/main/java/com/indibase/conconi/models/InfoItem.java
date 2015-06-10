package com.indibase.conconi.models;

public class InfoItem {

    private String time;
    private String deflectionPoint;
    private String level;

    public InfoItem(String deflectionPoint, String level, String time) {
        this.deflectionPoint = deflectionPoint;
        this.level = level;
        this.time = time;
    }


    public String getTime() {
        return time;
    }
}
