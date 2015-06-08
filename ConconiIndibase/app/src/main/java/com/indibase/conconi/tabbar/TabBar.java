package com.indibase.conconi.tabbar;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.indibase.conconi.R;
import com.indibase.conconi.adapters.TabsPagerAdapter;


public class TabBar extends FragmentActivity implements ActionBar.TabListener{

    private ViewPager viewPager;
    private TabsPagerAdapter adapter;
    private ActionBar actionBar;
    private static final int TRANSPARENT_BACKGROUND = R.drawable.tab_bg_none;

    public TabBar(View viewPager, ActionBar actionBar, android.support.v4.app.FragmentManager fragManager) {
        this.viewPager = (ViewPager) viewPager;
        this.actionBar = actionBar;
        this.adapter = new TabsPagerAdapter(fragManager);

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
        tabLayout.setBackgroundResource(TRANSPARENT_BACKGROUND);
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
}
