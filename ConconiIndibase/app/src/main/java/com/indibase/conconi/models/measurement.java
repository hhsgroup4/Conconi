package com.indibase.conconi.models;
import android.content.ContentValues;

import java.util.Date;

/**
 * Created by Vince on 1-6-2015.
 */ 
public class Measurement {
    private int testId;
    private int second;
    private int bpm;

    public Measurement(int second, int bpm) {
        this.second = second;
        this.bpm = bpm;
    }

    public Measurement(int testId, int second, int bpm) {
        this.testId = testId;
        this.second = second;
        this.bpm = bpm;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();
        values.put("test_id", this.getTestId());
        values.put("second",this.getSecond());
        values.put("bpm",this.getBpm());
        return values;
    }
    @Override
    public String toString(){
        return "BPM " + getBpm() + "; Second " + getSecond();
    }
}
