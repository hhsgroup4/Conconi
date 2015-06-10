package com.indibase.conconi.fragments;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.indibase.conconi.R;
import com.indibase.conconi.adapters.InfoListViewAdapter;
import com.indibase.conconi.models.DbTest;
import com.indibase.conconi.models.InfoItem;
import com.indibase.conconi.models.Test;

import java.util.ArrayList;

public class InfoTabFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.info_tab_fragment, container, false);

        InfoListViewAdapter adapter = new InfoListViewAdapter(getActivity().getBaseContext(), generateData());

        ListView lv = (ListView) view.findViewById(R.id.listview_info);
        lv.setAdapter(adapter);

        return view;
    }

    private ArrayList<InfoItem> generateData(){
        ArrayList<InfoItem> items = new ArrayList<InfoItem>();
        items.add(new InfoItem("Item 1","First Item on the list", "About"));
        items.add(new InfoItem("Item 2","Second Item on the list", "Team"));
        items.add(new InfoItem("Item 3", "Third Item on the list", "Science"));

        return items;
    }


}
