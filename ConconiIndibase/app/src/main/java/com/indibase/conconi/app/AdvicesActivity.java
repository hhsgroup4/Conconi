package com.indibase.conconi.app;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.indibase.conconi.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdvicesActivity extends ActionBarActivity {
    TextView lbl_deflectionPoint;
    String[][] advice_arrays;
    ArrayList<String> advice_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advices);

        advice_arrays = getAdvicesArray();
        advice_names = new ArrayList<>();
        advice_names = getAdviceNames(advice_arrays);
        fillListView(advice_names);



        lbl_deflectionPoint = (TextView) this.findViewById(R.id.lbl_lastDeflection);
    }
    private void fillListView(ArrayList<String> advice_names){
        ListView advicesListView = (ListView) findViewById(R.id.advicesListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, advice_names);
        advicesListView.setAdapter(adapter);
    }

    private ArrayList<String> getAdviceNames(String[][] advice_arrays){
        for (String[] advice : advice_arrays){
            advice_names.add(advice[0]);
        }
        return advice_names;
    }
    private String[][] getAdvicesArray(){
        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(R.array.advice_arrays);
        int n = ta.length();
        advice_arrays = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                advice_arrays[i] = res.getStringArray(id);
            } else {
                // something wrong with the XML
            }
        }
        ta.recycle();
        return advice_arrays;
    }

    public void changeDeflectionPoint(View view){
        lbl_deflectionPoint.setText("170");
    }
}
