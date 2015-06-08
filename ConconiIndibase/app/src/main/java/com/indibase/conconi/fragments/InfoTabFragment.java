package com.indibase.conconi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.indibase.conconi.R;

public class InfoTabFragment extends Fragment implements View.OnClickListener {

    private View view;
    Button btn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.info_tab_fragment, container, false);

        //btn = (Button) view.findViewById(R.id.myButton);
        //btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Log.i("Manfredinator: ", "Clicke was registered");
        //Intent intent = new Intent(getActivity(), NewActivity.class);
        //startActivity(intent);
    }

}
