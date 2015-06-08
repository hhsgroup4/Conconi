package com.indibase.conconi.fragments;

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
import com.indibase.conconi.models.DbTest;
import com.indibase.conconi.models.Test;

import java.util.ArrayList;

public class InfoTabFragment extends Fragment implements View.OnClickListener {

    private View view;

    ArrayList<Test> infoItems = new ArrayList<>();
    ListView listViewInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.info_tab_fragment, container, false);
        infoItems = DbTest.getAllTests(getActivity(), false);
        listViewInfo = (ListView) view.findViewById(R.id.listview_info);
        fillListView(infoItems);

        return view;
    }

    @Override
    public void onClick(View view) {
        Log.i("Manfredinator: ", "Clicke was registered");
        //Intent intent = new Intent(getActivity(), NewActivity.class);
        //startActivity(intent);
    }

    private void fillListView(ArrayList<Test> infoItems){

        LeInfoListAdapter mLeInfoListAdapter = new LeInfoListAdapter();
        mLeInfoListAdapter.setTests(infoItems);
        listViewInfo.setAdapter(mLeInfoListAdapter);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, infoItems);
        listViewInfo.setAdapter(adapter);*/
    }

    // Adapter for holding tests found through scanning.
    private class LeInfoListAdapter extends BaseAdapter {
        private ArrayList<Test> mTests;

        public LeInfoListAdapter() {
            super();
            mTests = new ArrayList<>();
        }
        public void setTests(ArrayList<Test> tests){
            mTests = new ArrayList<>(tests);
        }
        public void addDevice(Test test) {
            if(!mTests.contains(test)) {
                mTests.add(test);
            }
        }

        public Test getDevice(int position) {
            return mTests.get(position);
        }

        public void clear() {
            mTests.clear();
        }

        @Override
        public int getCount() {
            return mTests.size();
        }

        @Override
        public Object getItem(int i) {
            return mTests.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = getActivity().getLayoutInflater().inflate(R.layout.listitem_info, null);
                viewHolder = new ViewHolder();
                viewHolder.testAddress = (TextView) view.findViewById(R.id.test_address);
                viewHolder.testName = (TextView) view.findViewById(R.id.test_name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            Test test = mTests.get(i);
            final String testName = String.valueOf(test.getCreation());
            if (testName != null && testName.length() > 0)
                viewHolder.testName.setText(testName);
            else
                viewHolder.testName.setText("No test");
            viewHolder.testAddress.setText(String.valueOf(test.getDeflection_point()));

            return view;
        }
    }
    static class ViewHolder {
        TextView testName;
        TextView testAddress;
    }

}
