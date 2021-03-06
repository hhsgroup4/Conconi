package com.indibase.conconi.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.indibase.conconi.R;
import com.indibase.conconi.app.CyclingActivity;
import com.indibase.conconi.app.DeviceScanActivity;

public class TestTabFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Intent intent;
    private ImageButton btnTest;
    private ImageButton btnBluetooth;
    private ImageButton shareBtn;
    private String bluetoothAddress;
    private boolean play;

    ShareDialog shareDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.test_tab_fragment, container, false);
        play = false;
        btnTest = (ImageButton) view.findViewById(R.id.btn_start_test);
        btnBluetooth = (ImageButton) view.findViewById(R.id.btn_bluetooth);
        btnBluetooth.setEnabled(true);

        btnTest.setImageResource(R.mipmap.btn_start_test_red);
        btnBluetooth.setImageResource(R.mipmap.btn_bluetooth_red);

        // enable buttons if bluetooth is paired
        btnTest.setEnabled(false);
        btnTest.setOnClickListener(this);
        btnBluetooth.setOnClickListener(this);

        // @Todo: change button in UI
        FacebookSdk.sdkInitialize(getActivity());
        shareDialog = new ShareDialog(this);
        shareBtn = (ImageButton) view.findViewById(R.id.sharebtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class) && isNetworkAvailable()) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Conconi")
                            .setContentDescription(
                                    "Maak gebruik van de 'Conconi' app om het beste uit je training te halen. " +
                                            "Het maakt namelijk gebruik van je hartslag en berekent zo jouw persoonlijke trainingsschema's!")
                            .setContentUrl(Uri.parse("http://conconi.indibase.com"))
                            .build();

                    shareDialog.show(linkContent);
                } else
                    Toast.makeText(getActivity(), "No connection available for sharing this to Facebook.", Toast.LENGTH_SHORT).show();
            }
        });

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
        if (requestCode == 1 && data != null) {
            bluetoothAddress = data.getStringExtra("bAddress");
            this.play = true;

        }
        if (play) {
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
