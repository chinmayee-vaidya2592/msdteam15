package com.example.chinmayee.mainactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

;

/**
 * Created by Swapnil on 3/29/2016.
 */
public class MyOppPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MyOppPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                InProgressFragment inProgress = new InProgressFragment();
                return inProgress;
            case 1:
                UpcomingOppFragment all = new UpcomingOppFragment();
                return all;
            case 2:
                CompletedFragment completedFragment = new CompletedFragment();
                return completedFragment;
            case 3:
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