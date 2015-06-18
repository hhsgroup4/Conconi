package com.indibase.conconi.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.indibase.conconi.R;

public class InfoDetailActivity extends Activity {

    private String header;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);

        Intent infoIntent = getIntent();
        //InfoItem infoItem = (InfoItem)
        int infoID = Integer.valueOf(infoIntent.getStringExtra("ITEM_ID"));

        switch (infoID) {
            case 1:
                header = getResources().getString(R.string.guide_header);
                text = getResources().getString(R.string.guide_htmlmarkup_content);
                break;
            case 2:
                header = getResources().getString(R.string.info_team);
                text = getResources().getString(R.string.team_htmlmarkup_content);
                break;
            case 3:
                header = getResources().getString(R.string.about_header);
                text = getResources().getString(R.string.about_htmlmarkup_content);
                break;
            default:
                header = "Header placeholder";
                text = "Text placeholder";
        }


        TextView lbl_header = (TextView) findViewById(R.id.lbl_detailedinfo_header);
        TextView lbl_text = (TextView) findViewById(R.id.lbl_detailedinfo_text);

        lbl_header.setText(header);
        lbl_text.setText(Html.fromHtml(text));

    }
}
