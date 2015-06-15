package com.indibase.conconi.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.indibase.conconi.R;
import com.indibase.conconi.models.InfoItem;

import java.util.ArrayList;

public class InfoListViewAdapter extends ArrayAdapter<InfoItem> {

    private final Context context;
    private final ArrayList<InfoItem> itemsArrayList;

    public InfoListViewAdapter(Context context, ArrayList<InfoItem> itemsArrayList) {

        super(context, R.layout.list_item_row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.list_item_row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.lbl_listrow_title);
        //TextView valueView = (TextView) rowView.findViewById(R.id.value);

        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getPositionID());
        //valueView.setText(itemsArrayList.get(position).getDescription());

        // 5. retrn rowView
        return rowView;
    }
}
