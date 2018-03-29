package com.example.peter.project1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.peter.project1.Fragment.SlideshowFragment;

/**
 * Created by daovip on 3/27/2018.
 */

public class AdapterViewpager extends PagerAdapter {
    int number=5;
    public AdapterViewpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new SlideshowFragment();
    }

    @Override
    public int getCount() {
        return number;
    }
}
