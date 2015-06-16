package com.indibase.conconi.app;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import com.indibase.conconi.R;
import com.indibase.conconi.bluetooth.BluetoothLeService;
import com.indibase.conconi.bluetooth.SampleGattAttributes;
import com.indibase.conconi.models.Deflection;
import com.indibase.conconi.models.Measurement;
import com.indibase.conconi.models.Test;

// TODO make 10-0 countdown before the test starts
public class CyclingActivity extends Activity{

    private TextView lbl_test_level,lbl_test_heartbeat,lbl_test_time;

    private String mDeviceAddress;
    private BluetoothLeService mBluetoothLeService;
    private BluetoothGattCharacteristic mNotifyCharacteristic;

    public Queue<Measurement> Measurements, dbMeasurements;
    private Date creation;
    int deflectionPoint;
    int time;
    int level;
    boolean start;
    static int startCount = 5; // count down in seconds
    String strTime;
    private countTimer ct;
    ImageButton btn_finish_test;

    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(CyclingActivity.class.getSimpleName(), "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    // Handles various events fired by the Service.
    // ACTION_GATT_CONNECTED: connected to a GATT server.
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
    // ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
    //                        or notification operations.
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // starts the heartbeat
                startHeartBeatService(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                //displays the heartbeat
                if(start) {
                    processData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
                }

            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycling);

        lbl_test_heartbeat = (TextView) findViewById(R.id.lbl_test_heartbeat);
        lbl_test_time = (TextView) findViewById(R.id.lbl_test_time);
        lbl_test_level = (TextView) findViewById(R.id.lbl_test_level);
        btn_finish_test = (ImageButton) findViewById(R.id.btn_finish_test);

        lbl_test_heartbeat.setText(String.valueOf(startCount));
        lbl_test_time.setText(String.valueOf(startCount));
        lbl_test_level.setText(String.valueOf(startCount));
        btn_finish_test.setEnabled(false);

        creation = null;
        level = 4;
        time = 0;
        deflectionPoint = 0;
        start = false;
        ct = null;

        final Intent intent = getIntent();
        mDeviceAddress = intent.getStringExtra("DEVICE_ADDRESS");

        Measurements = new LinkedList<>();
        Log.w("address", mDeviceAddress);
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);

        new startTimer().execute(startCount);
    }

    private class startTimer extends AsyncTask<Integer, String, String> {
        int counter = 5;
        @Override
        protected String doInBackground(Integer... params) {
            for (int i = 0; i < params[0]; i++) {
                try {
                    counter = counter - 1;
                    Thread.sleep(1000);
                    publishProgress(String.valueOf(counter));
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            start = true;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if (Integer.valueOf(values[0]) > 0 ){
                lbl_test_heartbeat.setText(values[0]);
                lbl_test_level.setText(values[0]);
                lbl_test_time.setText(values[0]);
            }
            else{
                String initializing = "Initializing";
                lbl_test_heartbeat.setText(initializing);
                lbl_test_level.setText(initializing);
                lbl_test_time.setText(initializing);
            }
        }
    }

    private class countTimer extends AsyncTask<Integer, String, String> {
        int counter = 0;
        @Override
        protected String doInBackground(Integer... params) {
            while (start && !isCancelled()) {
                try {
                    Log.w("progress", String.valueOf(counter));
                    counter++;
                    Thread.sleep(1000);
                    publishProgress(String.valueOf(counter));
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "Executed";
        }

        @Override
        protected void onProgressUpdate(String... values) {

            updateTimeLabel(counter);
        }
    }

    // Demonstrates how to iterate through the supported GATT Services/Characteristics.
    // In this sample, we populate the data structure that is bound to the ExpandableListView
    // on the UI.
    private void startHeartBeatService(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            if(gattService.getUuid().toString().equals(SampleGattAttributes.HEART_RATE_SERVICE)) {
                List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
                // Loops through available Characteristics.
                for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                    if (gattCharacteristic.getUuid().toString().equals(SampleGattAttributes.HEART_RATE_MEASUREMENT)) {
                        mNotifyCharacteristic = gattCharacteristic;
                    }
                }
            }
        }
        if (mNotifyCharacteristic == null ) return;
        mBluetoothLeService.connect(mDeviceAddress);
        mBluetoothLeService.readCharacteristic(mNotifyCharacteristic);
        mBluetoothLeService.setCharacteristicNotification(mNotifyCharacteristic, true);
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }



    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(CyclingActivity.class.getSimpleName(), "Connect request result=" + result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }

    private void processData(String data){
        if (data != null && Integer.valueOf(data) > 10) {
            if (creation == null){
                creation = new Date();
                if (time == 0){
                    ct = new countTimer();
                    ct.execute();
                }
            }
            btn_finish_test.setEnabled(true);
            addToMeasurements(data);
            displayData(data);
        }
    }
    private void displayData(String data) {
        lbl_test_heartbeat.setText(data);
        updateLevelLabel();
    }
    public int calculateDeflectionPoint(Queue<Measurement> dbMeasurementsCopy){
        ArrayList<Integer> points = new ArrayList<>();
        while (dbMeasurementsCopy.size() != 0)
        {
            points.add(dbMeasurementsCopy.poll().getBpm());
        }
        int deflectionPoint =  Deflection.getDeflectionPoint(points);

        return deflectionPoint;
    }
    public int storeTest(Test t){
        Uri uri = getContentResolver().insert(Uri.parse("content://com.indibase.provider.conconi/test"), new ContentValues(t.getContentValues()));
        return Integer.valueOf(uri.getLastPathSegment());
    }

    public void storeMeasurements(Queue<Measurement> dbMeasurements, int testId){
        while (dbMeasurements.size() != 0){
            Measurement m = dbMeasurements.poll();
            m.setTestId(testId);
            storeMeasurement(m);
        }
    }
    public void storeMeasurement(Measurement m){
        Uri uri = getContentResolver().insert(Uri.parse("content://com.indibase.provider.conconi/measurement"),new ContentValues(m.getContentValues()));
        Log.w("uri", String.valueOf(uri));
    }
    public Queue<Measurement> getDbMeasurements(Queue<Measurement> Measurements){
        int i = 0;
        int counter = 0;
        int bpmTotal = 0;
        int average = 0;
        dbMeasurements = new LinkedList<>();
        int second = 0;

        while (Measurements.size() != 0) {
            second = Measurements.peek().getSecond();
            if (second > i){
                average = bpmTotal/counter;
                Measurement mm = new Measurement(i,average);
                Log.w("Measurement between", mm.toString());
                dbMeasurements.add(mm);
                bpmTotal = 0;
                counter = 0;
                i=i+10;
            }
            counter++;
            bpmTotal = bpmTotal + Measurements.poll().getBpm();
        }
        average = bpmTotal/counter;
        Log.w("Measurement end", new Measurement(second,average).toString());
        dbMeasurements.add(new Measurement(second, average));

        return dbMeasurements;
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
    private void addToMeasurements(String bpm){
        long s =  TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - creation.getTime());
        int second = safeLongToInt(s);
        Measurement m = new Measurement(second,Integer.valueOf(bpm));
        Log.w(CyclingActivity.class.getSimpleName(), m.toString());
        Measurements.add(m);
    }

    public void finishedTest(View view) {
        endActions();
        dbMeasurements = getDbMeasurements(Measurements);
        deflectionPoint = calculateDeflectionPoint(new LinkedList(dbMeasurements));
        Test t = new Test(new Date(), deflectionPoint);
        int testId = storeTest(t);
        storeMeasurements(dbMeasurements, testId);

        Intent intent = new Intent(view.getContext(), FinishedTestActivity.class);
        intent.putExtra("DEFLECTION_POINT", String.valueOf(deflectionPoint));
        intent.putExtra("LEVEL", String.valueOf(level));
        intent.putExtra("TIME", strTime);
        startActivity(intent);
        finish();
    }
    public void endActions(){
        unregisterReceiver(mGattUpdateReceiver);
        if (ct != null)
            ct.cancel(true);
    }

    public void quitTest(View view) {
        endActions();
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateTimeLabel(int counter) {
        time = counter;
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        Date date = new Date(new Date().getTime()+(counter*1000) - new Date().getTime());
        strTime= df.format(date);
        lbl_test_time.setText(strTime);
    }
    private void updateLevelLabel() {
        long s =  TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - creation.getTime());
        int second = safeLongToInt(s);
        int tmpLevel = second/60;
        Log.w("tmplevel", String.valueOf(tmpLevel));
        level = 4 + tmpLevel;
        if (level >= 20){
            level = 20;
        }
        lbl_test_level.setText(String.valueOf(level));
    }
}
