package com.indibase.conconi.app;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.indibase.conconi.R;
import com.indibase.conconi.models.DbTest;
import com.indibase.conconi.models.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        //getTestExample(1);
        getTestWithAllMeasurementsExample(1);

=======
>>>>>>> origin/master
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
    }

}
