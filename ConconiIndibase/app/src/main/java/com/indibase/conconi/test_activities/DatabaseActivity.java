package com.indibase.conconi.test_activities;

import android.app.Activity;
import android.os.Bundle;

import com.indibase.conconi.R;
import com.indibase.conconi.models.DbTest;
import com.indibase.conconi.models.Test;

public class DatabaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
    }

    public Test getTest(int id){
        return DbTest.getTest(this, id, false);
    }

    public int insertTest(Test test){
        return DbTest.saveTest(this, test);
    }
}
