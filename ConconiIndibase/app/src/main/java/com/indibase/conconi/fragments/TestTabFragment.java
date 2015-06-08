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

import com.indibase.conconi.R;

/**
 * Created by Manfredinator on 08/06/15.
 */
public class TestTabFragment extends Fragment implements View.OnClickListener {

    private View view;
    Button btn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_tab1, container, false);

        btn = (Button) view.findViewById(R.id.myButton);
        btn.setOnClickListener(this);

        return view;
    }

    public void yeah(View view) {
        Log.i("manfredinator: ", "Clicked motherfucker!");
    }

    @Override
    public void onClick(View view) {
        Log.i("Manfredinator: ", "Clicke was registered");
        //Intent intent = new Intent(getActivity(), NewActivity.class);
        //startActivity(intent);
    }
}
