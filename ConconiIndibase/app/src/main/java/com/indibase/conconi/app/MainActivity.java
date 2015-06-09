package com.indibase.conconi.app;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.indibase.conconi.R;
import com.indibase.conconi.tabbar.TabBar;

// TODO fix naivigation so the only returning screen is not TestTabFragment
public class MainActivity extends FragmentActivity {

    private TabBar tabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabBar = new TabBar(findViewById(R.id.pager), getActionBar(), getSupportFragmentManager());

        tabBar.drawTabBar();

        LayoutInflater inflater = LayoutInflater.from(this);

        tabBar.setupTabListeners(inflater, R.layout.bottombar_tab1);
        tabBar.setupTabListeners(inflater, R.layout.bottombar_tab2);
        tabBar.setupTabListeners(inflater, R.layout.bottombar_tab3);

        // create the TabHost that will contain the Tabs
        /*TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");

        tab1.setIndicator("Test");
        tab1.setContent(new Intent(this, DeviceScanActivity.class));

        tab2.setIndicator("History");
        tab2.setContent(new Intent(this, HistoryActivity.class));

        tab3.setIndicator("Info");
        tab3.setContent(new Intent(this, AdvicesActivity.class));*/

        /** Add the tabs  to the TabHost to display. */
        /*tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);*/


    }

}
