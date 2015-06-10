package com.indibase.conconi.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter {


    public ListViewAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }
}
