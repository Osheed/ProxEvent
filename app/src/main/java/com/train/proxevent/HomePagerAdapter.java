package com.train.proxevent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Helder on 06.09.2017.
 */

class HomePagerAdapter extends FragmentPagerAdapter{
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                MapFragment mapFragment = new MapFragment();
                return mapFragment;
            case 1:
                CurrentActivitiesFragment currentActivitiesFragment = new CurrentActivitiesFragment();
                return currentActivitiesFragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){

        switch (position){
            case 0:
                return "Map";
            case 1:
                return "Current Activities";

            default:
                return null;
        }

    }
}
