package com.indibase.conconi.app;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.indibase.conconi.R;
import com.indibase.conconi.bluetooth.BluetoothLeService;
import com.indibase.conconi.bluetooth.SampleGattAttributes;
import com.indibase.conconi.models.measurement;

public class CyclingActivity extends Activity {
    private TextView mDataField;
    private String mDeviceAddress;
    private BluetoothLeService mBluetoothLeService;
    private BluetoothGattCharacteristic mNotifyCharacteristic;

    public ArrayList<measurement> measurements;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycling);

        final Intent intent = getIntent();
        mDeviceAddress = intent.getStringExtra("DEVICE_ADDRESS");
        measurements = new ArrayList<>();
        // Sets up UI references.
        mDataField = (TextView) findViewById(R.id.lbl_heartbeat);

        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

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
                clearUI();
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Displays the heartbeat
                startHeartBeatService(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                processData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            }
        }
    };



    private void clearUI() {
        mDataField.setText(R.string.no_data);
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

        //This stops the receiver
        //unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }

/*
    private void updateConnectionState(final int resourceId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mConnectionState.setText(resourceId);
            }
        });
    }*/

    private void processData(String data){
        if (data != null) {
            displayData(data);
            addToMeasurements(data);
        }
    }
    private void addToMeasurements(String bpm){
        measurement m = new measurement(new Date(),Integer.valueOf(bpm));
        measurements.add(m);
        Log.w(CyclingActivity.class.getSimpleName(), m.toString());
    }
    private void displayData(String data) {
        mDataField.setText(data);
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

}
