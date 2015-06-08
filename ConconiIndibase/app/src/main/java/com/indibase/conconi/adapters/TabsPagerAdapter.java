package com.indibase.conconi.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.indibase.conconi.fragments.HistoryTabFragment;
import com.indibase.conconi.fragments.InfoTabFragment;
import com.indibase.conconi.fragments.TestTabFragment;


public class TabsPagerAdapter extends FragmentPagerAdapter {
    private static final int NO_OF_TABS = 3;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new TestTabFragment();
            case 1:
                return new HistoryTabFragment();
            case 2:
                return new InfoTabFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return NO_OF_TABS;
    }

}