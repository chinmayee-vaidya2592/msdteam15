package com.example.chinmayee.mainactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

;

/**
 * Created by Swapnil on 3/29/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AllOppFragment all = new AllOppFragment();
                return all;
            case 1:
                ThisWeekOppFragment thisWeek = new ThisWeekOppFragment();
                return thisWeek;
            case 2:
                RecomOppFragment recc = new RecomOppFragment();
                return recc;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}