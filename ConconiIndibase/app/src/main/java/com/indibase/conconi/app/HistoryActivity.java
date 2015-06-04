package com.indibase.conconi.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.indibase.conconi.R;
import com.indibase.conconi.models.DbTest;
import com.indibase.conconi.models.Test;

import java.util.ArrayList;

import static com.indibase.conconi.R.layout.activity_history;

public class HistoryActivity extends ActionBarActivity {

    ArrayList<String> historyItems;
    ListView listViewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_history);
        historyItems = getTests();

        listViewHistory = (ListView) findViewById(R.id.listview_history);
        fillListView(historyItems);


    }
    private void fillListView(ArrayList<String> historyItems){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, historyItems);
        listViewHistory.setAdapter(adapter);
    }

    private ArrayList<String> getTests(){
        Test Test1 = DbTest.getTestWithMeasurements(this, 1);

        historyItems = DbTest.getAllTestString(this);
        return historyItems;
    }
}
