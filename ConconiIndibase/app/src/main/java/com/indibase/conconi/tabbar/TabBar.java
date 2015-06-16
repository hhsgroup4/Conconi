package com.indibase.conconi.tabbar;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.indibase.conconi.R;
import com.indibase.conconi.adapters.TabsPagerAdapter;


public class TabBar extends FragmentActivity implements ActionBar.TabListener{

    private CustomViewPager viewPager;
    private TabsPagerAdapter adapter;
    private ActionBar actionBar;

    public TabBar(View viewPager, final ActionBar actionBar, android.support.v4.app.FragmentManager fragManager) {
        this.viewPager = (CustomViewPager) viewPager;
        this.actionBar = actionBar;
        this.adapter = new TabsPagerAdapter(fragManager);

        // disables swiping in tab
        this.viewPager.setPagingEnabled(false);

    }

    public void drawTabBar() {
        viewPager.setAdapter(adapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    public void setupTabListeners(LayoutInflater inflater, int layoutID ){
        actionBar.addTab(actionBar.newTab()
                .setCustomView(inflater.inflate(layoutID, null))
                .setTabListener(this));
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        RelativeLayout tabLayout = (RelativeLayout) tab.getCustomView();
        tabLayout.setBackgroundResource(TABS_BACKGROUND[tab.getPosition()]);
        tab.setCustomView(tabLayout);

        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        RelativeLayout tabLayout = (RelativeLayout) tab.getCustomView();
        tabLayout.setBackgroundResource(R.drawable.tab_bg_none);
        tab.setCustomView(tabLayout);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    private static final int[] TABS_BACKGROUND = {
            R.drawable.tabs_bg_green, R.drawable.tab_bg_blue,
            R.drawable.tab_bg_yellow
    };

    @Nullable
    @Override
    public ActionBar getActionBar() {
        return actionBar;
    }

    public void setActionBar(ActionBar actionBar) {
        this.actionBar = actionBar;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
