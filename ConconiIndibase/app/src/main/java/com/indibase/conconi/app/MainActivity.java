package com.indibase.conconi.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.w("in main", "inmain");
        super.onActivityResult(requestCode, resultCode, data);
    }

}
