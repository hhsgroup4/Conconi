package com.indibase.conconi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.indibase.conconi.R;
import com.indibase.conconi.app.CyclingActivity;
import com.indibase.conconi.app.DeviceScanActivity;
import com.indibase.conconi.bluetooth.BluetoothLeService;

public class TestTabFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Intent intent;
    private ImageButton btnTest;
    private ImageButton btnBluetooth;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.test_tab_fragment, container, false);

        btnTest = (ImageButton) view.findViewById(R.id.btn_start_test);
        btnBluetooth= (ImageButton) view.findViewById(R.id.btn_bluetooth);

        // Check if app has connection to bluetooth device
        if(false) {
            btnTest.setImageResource(R.mipmap.btn_start_test_green);
            btnBluetooth.setImageResource(R.mipmap.btn_bluetooth_green);
        } else {
            Log.i("Manfredinator: ", "No bluetooth device paired!");
        }

        btnTest.setOnClickListener(this);
        btnBluetooth.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        System.out.println(view.getLabelFor());

        switch (view.getId()) {
            case R.id.btn_start_test:
                intent = new Intent(getActivity(), CyclingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_bluetooth:
                intent = new Intent(getActivity(), DeviceScanActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }


    }
}
