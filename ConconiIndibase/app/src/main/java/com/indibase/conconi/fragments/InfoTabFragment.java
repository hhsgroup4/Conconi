package com.indibase.conconi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.indibase.conconi.R;
import com.indibase.conconi.adapters.InfoListViewAdapter;
import com.indibase.conconi.app.InfoDetailActivity;
import com.indibase.conconi.models.InfoItem;

import java.util.ArrayList;

public class InfoTabFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.info_tab_fragment, container, false);

        InfoListViewAdapter adapter = new InfoListViewAdapter(getActivity().getBaseContext(), generateData());

        ListView lv = (ListView) view.findViewById(R.id.listview_info);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(view.getContext(), InfoDetailActivity.class);
                adapterView.getItemAtPosition(position);
                InfoItem infoItem = (InfoItem) adapterView.getItemAtPosition(position);

                // Use position to identify selected pressed item in next detailed info
                intent.putExtra("ITEM_ID", infoItem.getPositionID());
                startActivity(intent);
            }
        });

        lv.setAdapter(adapter);

        return view;
    }

    private ArrayList<InfoItem> generateData() {
        ArrayList<InfoItem> items = new ArrayList<InfoItem>();
        items.add(new InfoItem("1", getResources().getString(R.string.info_userguide), ""));
        items.add(new InfoItem("2", getResources().getString(R.string.info_team), ""));
        items.add(new InfoItem("3", getResources().getString(R.string.info_about), ""));

        return items;
    }


}
