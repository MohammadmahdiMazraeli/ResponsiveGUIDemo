package com.non_android_programmers.responsiveguidemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.non_android_programmers.responsivegui.ResponsiveHorizontalScrollView;

public class DemoActivity extends CustomActivity {

    private Fragment[] fragments = new Fragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        initializeFragments();

        ViewPager viewPager = (ViewPager)findViewById(R.id.demo_view_pager);
        viewPager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(),fragments));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void initializeFragments(){
        fragments[0] = new FirstDemoFragment();
        fragments[1] = new SecondDemoFragment();
        fragments[2] = new ThirdDemoFragment();
    }
}
