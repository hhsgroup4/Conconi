package com.indibase.conconi.models;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Vince on 4-6-2015.
 */
public class DbTest {
    public static Test getTest(Activity activity, int identifier){
        Uri oneTest = Uri.parse("content://com.indibase.provider.conconi/test/"+identifier);
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(activity,oneTest,null,null,null,null);
        c = cursorLoader.loadInBackground();

        Test test = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (c.moveToNext()) {
            c.moveToFirst();

            int id = Integer.valueOf(c.getString(0));;
            Date date = new Date();
            int deflection = Integer.valueOf(c.getString(2));
            try {
                date = dateFormat.parse(c.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            test = new Test(id, date, deflection);
        }
        return test;
    }
    public static Test getTestWithMeasurements(Activity activity, int identifier){
        Uri oneTest = Uri.parse("content://com.indibase.provider.conconi/test/"+identifier);
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(activity,oneTest,null,null,null,null);
        c = cursorLoader.loadInBackground();

        Test test = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (c.moveToNext()) {
            c.moveToFirst();

            int id = Integer.valueOf(c.getString(0));;
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

        ArrayList<Measurement> measurements = getMeasurements(activity, test.getId());
        test.setMeasurements(measurements);
        return test;
    }

    private static ArrayList<Measurement> getMeasurements(Activity activity, int identifier){
        ArrayList<Measurement> measurements = new ArrayList<>();

        Uri m = Uri.parse("content://com.indibase.provider.conconi/measurement/1");
        Cursor c;
        String[] projection=new String[]{"test_id", "second", "bpm"};
        String selection= "test_id = ?";
        String[] selectionArgs=new String[]{String.valueOf(identifier)};
        CursorLoader cursorLoader = new CursorLoader(activity,m,projection,selection,selectionArgs,null);
       // CursorLoader cursorLoader = new CursorLoader(activity,m,null,null,null,null);

        c = cursorLoader.loadInBackground();

        while (c.moveToNext()) {
            Measurement mm = new Measurement(Integer.valueOf(c.getString(0)),Integer.valueOf(c.getString(1)),Integer.valueOf(c.getString(2)));
            Log.w("measurements", mm.toString());
            measurements.add(mm);
        }


        return measurements;
    }
    public static ArrayList<String> getAllTestString(Activity activity){
        ArrayList<String> tests = getAll(activity, "testString", String.class);
        return tests;
    }
    public static ArrayList<Test> getAllTest(Activity activity){
        ArrayList<Test> tests = getAll(activity, "test", Test.class);
        return tests;
    }
    public static ArrayList<Integer> getAllId(Activity activity){
        ArrayList<Integer> tests = getAll(activity, "id", Integer.class);
        return tests;
    }
    public static ArrayList<Date> getAllDate(Activity activity){
        ArrayList<Date> tests = getAll(activity, "date", Date.class);
        return tests;
    }
    public static ArrayList<Integer> getAllDeflection(Activity activity){
        ArrayList<Integer> tests = getAll(activity, "deflection", Integer.class);
        return tests;
    }

    private static <T> ArrayList<T> getAll(Activity activity, String typeStr, Class<T> type){
        Uri allTests = Uri.parse("content://com.indibase.provider.conconi/test");
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(activity,allTests,null,null,null,null);
        c = cursorLoader.loadInBackground();

        ArrayList<T> tests = new ArrayList<>();;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (c.moveToNext()) {
            c.moveToFirst();

            int id = Integer.valueOf(c.getString(0));;
            Date date = new Date();
            int deflection = Integer.valueOf(c.getString(2));
            try {
                date = dateFormat.parse(c.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Object t = null;
            switch (typeStr){
                case "test":
                    t = new Test(id, date, deflection);
                    break;
                case "testString":
                    t = new String(new Test(id, date, deflection).toString());
                    break;
                case "id":
                    t = new Integer(id);
                    break;
                case "date":
                    t = date;
                    break;
                case "deflection":
                    t = new Integer(deflection);
                    break;
            }
            tests.add((T) t);
        }
        return tests;
    }
}
