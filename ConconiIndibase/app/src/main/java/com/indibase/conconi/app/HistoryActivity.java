package com.indibase.conconi.app;

import android.content.CursorLoader;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.indibase.conconi.R;

import java.util.ArrayList;

import static com.indibase.conconi.R.layout.activity_history;

public class HistoryActivity extends ActionBarActivity {

    String[] historyItems;
    ListView listViewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_history);
        historyItems = getTests();

        listViewHistory = (ListView) findViewById(R.id.listview_history);
        fillListView(historyItems);


    }
    private void fillListView(String[] historyItems){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, historyItems);
        listViewHistory.setAdapter(adapter);
    }

    private String[] getTests(){
        Uri tests = Uri.parse("content://com.indibase.provider.conconi/test");
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(this,tests,null,null,null,null);
        c = cursorLoader.loadInBackground();
        historyItems = null;
        //historyItems = new String[c.getCount()][3];
        historyItems = new String[c.getCount()];
        int index = 0;

        while (c.moveToNext()) {
            if(index == 0)
                c.moveToFirst();
          //  historyItems[index][0] = c.getString(0);
            //historyItems[index][1] = c.getString(1);
            historyItems[index] = "ID: " + c.getString(0) + " Time: " + c.getString(1) + " BPM: "  + c.getString(2);
           // historyItems[index][2] = c.getString(2);
            index++;
        }
        return historyItems;
    }
}
