package com.indibase.conconi.models;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vince on 1-6-2015.
 */
public class Test {
    private int id;
    private Date creation;
    private int deflection_point;

    private ArrayList<Measurement> measurements;

    public Test(){

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public int getDeflection_point() {
        return deflection_point;
    }

    public void setDeflection_point(int deflection_point) {
        this.deflection_point = deflection_point;
    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();
        values.put("creation", String.valueOf(this.getCreation()));
        values.put("deflection_point", this.getDeflection_point());
        return values;
    }

    @Override
    public String toString(){
        return "ID: " + getId() + " Time: " + getCreation() + " BPM: "  + getDeflection_point();
    }
}
