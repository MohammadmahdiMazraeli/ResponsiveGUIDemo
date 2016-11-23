package com.non_android_programmers.responsiveguidemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.non_android_programmers.responsivegui.ResponsiveHorizontalScrollView;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    Fragment[] fragments;

    public ViewPagerFragmentAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
