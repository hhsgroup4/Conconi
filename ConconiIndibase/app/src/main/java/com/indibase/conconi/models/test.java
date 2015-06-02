package com.indibase.conconi.models;

import android.content.ContentValues;

import java.util.Date;

/**
 * Created by Vince on 1-6-2015.
 */
public class test {
    private int id;
    private Date creation;
    private int deflection_point;

    public test(int id, Date creation, int deflection_point) {
        this.id = id;
        this.creation = creation;
        this.deflection_point = deflection_point;
    }

    public test(Date creation, int deflection_point) {
        this.creation = creation;
        this.deflection_point = deflection_point;
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
}
