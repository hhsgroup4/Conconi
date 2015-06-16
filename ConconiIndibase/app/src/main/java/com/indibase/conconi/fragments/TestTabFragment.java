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
import android.widget.Toast;

import com.indibase.conconi.R;
import com.indibase.conconi.app.CyclingActivity;
import com.indibase.conconi.app.DeviceScanActivity;
import com.indibase.conconi.bluetooth.BluetoothLeService;

public class TestTabFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Intent intent;
    private ImageButton btnTest;
    private ImageButton btnBluetooth;
    private String bluetoothAddress;
    private boolean play;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.test_tab_fragment, container, false);
        play = false;
        btnTest = (ImageButton) view.findViewById(R.id.btn_start_test);
        btnBluetooth= (ImageButton) view.findViewById(R.id.btn_bluetooth);
        btnBluetooth.setEnabled(true);

        btnTest.setImageResource(R.mipmap.btn_start_test_red);
        btnBluetooth.setImageResource(R.mipmap.btn_bluetooth_red);

        // enable buttons if bluetooth is paired
        btnTest.setEnabled(false);
        btnTest.setOnClickListener(this);
        btnBluetooth.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        System.out.println(view.getLabelFor());

        switch (view.getId()) {
            case R.id.btn_start_test:
                if (play) {
                    intent = new Intent(getActivity(), CyclingActivity.class);
                    intent.putExtra("DEVICE_ADDRESS", bluetoothAddress);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No bluetooth device paired", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_bluetooth:
                Intent i = new Intent(getActivity(), DeviceScanActivity.class);
                startActivityForResult(i, 1);
                break;
            default:
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if(requestCode == 1 && data!=null) {
            bluetoothAddress = data.getStringExtra("bAddress");
            this.play = true;

        }
        if(play) {
            btnTest.setImageResource(R.mipmap.btn_start_test_green);
            btnBluetooth.setImageResource(R.mipmap.btn_bluetooth_green);

            // enable buttons if bluetooth is paired
            btnTest.setEnabled(true);
        } else {
            btnTest.setImageResource(R.mipmap.btn_start_test_red);
            btnBluetooth.setImageResource(R.mipmap.btn_bluetooth_red);

            // enable buttons if bluetooth is paired
            btnTest.setEnabled(false);

            Log.i("Manfredinator: ", "No bluetooth device paired!");

        }
    }
}
