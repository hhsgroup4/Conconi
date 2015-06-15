package com.indibase.conconi.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.indibase.conconi.R;
import com.indibase.conconi.models.InfoItem;

public class InfoDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);

        Intent infoIntent = getIntent();
        //InfoItem infoItem = (InfoItem)
        int infoID= Integer.valueOf(infoIntent.getStringExtra("ITEM_ID"));


        String header = "Header placeholder";
        String text = "Text placeholder";

        TextView lbl_header = (TextView) findViewById(R.id.lbl_detailedinfo_header);
        TextView lbl_text = (TextView) findViewById(R.id.lbl_detailedinfo_text);

        lbl_header.setText(Integer.toString(infoID));
        lbl_text.setText(text);

    }
}
