package com.indibase.conconi.models;
import java.util.Date;

/**
 * Created by Vince on 1-6-2015.
 */
public class measurement {
    private Date dateTime;
    private int bpm;

    public measurement(Date dateTime, int bpm) {
        this.dateTime = dateTime;
        this.bpm = bpm;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    @Override
    public String toString(){
        return "BPM " + getBpm() + "; DateTime " + getDateTime();
    }
}
