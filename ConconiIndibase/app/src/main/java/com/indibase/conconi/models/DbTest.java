package com.indibase.conconi.models;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.util.Log;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DbTest {
    public static Test getTest(Activity activity, int identifier, boolean include_measurements){
        Uri oneTest = Uri.parse("content://com.indibase.provider.conconi/test/"+identifier);
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(activity,oneTest,null,null,null,null);
        c = cursorLoader.loadInBackground();

        Log.d("dump", DatabaseUtils.dumpCursorToString(c));

        Test test = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (c.moveToNext()) {
            c.moveToFirst();

            int id = Integer.valueOf(c.getString(0));

            Date date = new Date();
            int deflection = Integer.valueOf(c.getString(2));
            try {
                date = dateFormat.parse(c.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            test = new Test(id, date, deflection);
        }
        c.close();

        if(include_measurements) {
            ArrayList<Measurement> measurements = getMeasurements(activity, test.getId());
            test.setMeasurements(measurements);
        }

        return test;
    }

    public static int saveTest(Activity activity, Test t){
        Uri turi = activity.getContentResolver().insert(
                Uri.parse("content://com.indibase.provider.conconi/test"),
                t.getContentValues());

        int insertId = Integer.parseInt(turi.getLastPathSegment());

        for(Measurement m : t.getMeasurements()){
            Uri muri = activity.getContentResolver().insert(
                    Uri.parse("content://com.indibase.provider.conconi/measurement"),
                    m.getContentValues());
        }

        return insertId;
    }

    private static ArrayList<Measurement> getMeasurements(Activity activity, int identifier){
        ArrayList<Measurement> measurements = new ArrayList<>();

        Uri m = Uri.parse("content://com.indibase.provider.conconi/measurement/1");
        Cursor c;
        String[] projection=new String[]{"test_id", "second", "bpm"};
        String selection= "test_id = ?";
        String[] selectionArgs=new String[]{String.valueOf(identifier)};
        CursorLoader cursorLoader = new CursorLoader(activity,m,projection,selection,selectionArgs,null);

        c = cursorLoader.loadInBackground();

        while (c.moveToNext()) {
            Measurement mm = new Measurement(Integer.valueOf(c.getString(0)),Integer.valueOf(c.getString(1)),Integer.valueOf(c.getString(2)));
            Log.w("measurements", mm.toString());
            measurements.add(mm);
        }


        return measurements;
    }

    public static ArrayList<Test> getAllTests(Activity activity, boolean include_measurements){
        ArrayList<Test> tests = new ArrayList<>();

        Uri m = Uri.parse("content://com.indibase.provider.conconi/test");

        CursorLoader cursorLoader = new CursorLoader(activity,m,null,null,null,null);

        Cursor c = cursorLoader.loadInBackground();

        while (c.moveToNext()) {
            int id = Integer.valueOf(c.getString(0));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = dateFormat.parse(c.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int deflection = Integer.valueOf(c.getString(2));
            Test t = new Test(id, date, deflection);

            if(include_measurements) {
                ArrayList<Measurement> measurements = getMeasurements(activity, t.getId());
                t.setMeasurements(measurements);
            }
            tests.add(t);
        }

        c.close();

        return tests;
    }
}
