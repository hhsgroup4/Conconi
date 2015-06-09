package com.indibase.conconi.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.indibase.conconi.R;
import com.indibase.conconi.models.DbTest;
import com.indibase.conconi.models.Test;

import java.util.ArrayList;

import static com.indibase.conconi.R.layout.activity_history;

public class HistoryActivity extends Activity {

    ArrayList<Test> historyItems = new ArrayList<>();
    ListView listViewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_history);

        historyItems = DbTest.getAllTests(this,false);

        listViewHistory = (ListView) findViewById(R.id.listview_history);

        fillListView(historyItems);


    }
    private void fillListView(ArrayList<Test> historyItems){

        LeHistoryListAdapter mLeHistoryListAdapter = new LeHistoryListAdapter();
        mLeHistoryListAdapter.setTests(historyItems);
        listViewHistory.setAdapter(mLeHistoryListAdapter);
        
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, historyItems);
        listViewHistory.setAdapter(adapter);*/
    }

    // Adapter for holding tests found through scanning.
    private class LeHistoryListAdapter extends BaseAdapter {
        private ArrayList<Test> mTests;

        public LeHistoryListAdapter() {
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
                view = getLayoutInflater().inflate(R.layout.listitem_test, null);
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
