package com.indibase.conconi.models;

import android.content.ContentValues;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Test {
    private int id;
    private Date creation;
    private int deflection_point;

    private ArrayList<Measurement> measurements;

    public Test() {

    }

    public Test(int id, Date creation, int deflection_point) {
        this.id = id;
        this.creation = creation;
        this.deflection_point = deflection_point;
    }

    public Test(Date creation, int deflection_point) {
        this.creation = creation;
        this.deflection_point = deflection_point;
    }

    public ArrayList<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(ArrayList<Measurement> measurements) {
        this.measurements = measurements;
    }

    public String getTime() {
        int seconds = measurements.get(measurements.size() - 1).getSecond();
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        Date date = new Date((new Date().getTime() + (seconds * 1000)) - new Date().getTime());

        return df.format(date);
    }

    public int getLevel() {
        int seconds = measurements.get(measurements.size() - 1).getSecond();
        int level = (seconds / 60) + 4;
        if (level <= 4) {
            level = 4;
        }
        if (level >= 20) {
            level = 20;
        }
        return level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreation() {
        return creation;
    }

    public int getDeflection_point() {
        return deflection_point;
    }

    public void setDeflection_point(int deflection_point) {
        this.deflection_point = deflection_point;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //always convert to this format
        values.put("creation", df.format(getCreation()));
        values.put("deflection_point", this.getDeflection_point());
        return values;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " Time: " + getCreation() + " BPM: " + getDeflection_point();
    }
}
