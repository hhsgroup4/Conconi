package com.indibase.conconi.app;

import android.app.TabActivity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TabHost;

import com.indibase.conconi.R;


public class MainActivity extends TabActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Uri tests = Uri.parse("content://com.indibase.provider.conconi/test/including_measurements");
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(
                this,
                tests,
                null,
                null,
                null,
                null);
        c = cursorLoader.loadInBackground();

        Log.d("lifecycle", DatabaseUtils.dumpCursorToString(c));*/

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");

        tab1.setIndicator("Test");
        tab1.setContent(new Intent(this, DeviceScanActivity.class));

        tab2.setIndicator("History");
        tab2.setContent(new Intent(this, HistoryActivity.class));

        tab3.setIndicator("Advices");
        tab3.setContent(new Intent(this, AdvicesActivity.class));

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

        tabHost.setCurrentTab(2);
    }


}
