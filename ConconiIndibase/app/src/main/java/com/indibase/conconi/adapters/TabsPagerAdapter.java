package com.indibase.conconi.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TabsPagerAdapter extends FragmentPagerAdapter {
    private static final int NO_OF_TABS = 3;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new Tab1Frag();
            case 1:
                return new Tab2Frag();
            case 2:
                return new Tab3Frag();
        }

        return null;
    }

    @Override
    public int getCount() {
        return NO_OF_TABS;
    }

}