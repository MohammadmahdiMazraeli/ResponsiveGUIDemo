package com.non_android_programmers.responsiveguidemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new FirstDemoFragment();
        }else if (position == 1){
            return new SecondDemoFragment();
        }else {
            return new ThirdDemoFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
