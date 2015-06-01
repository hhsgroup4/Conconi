package com.indibase.conconi.models;

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
}
