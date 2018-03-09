package com.dev.ehnyn.mediaviewer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class PagerAdapter extends FragmentStatePagerAdapter {

    int noOfFrag;
    public PagerAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfFrag  = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PictureFragment tab1 = new PictureFragment();
                return tab1;
            case 1:
                VideoFragment tab2 = new VideoFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return  noOfFrag;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}


