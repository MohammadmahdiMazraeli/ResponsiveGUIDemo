package com.non_android_programmers.responsiveguidemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.non_android_programmers.responsivegui.ScreenDetails;

public class DemoActivity extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);


        ViewPager viewPager = (ViewPager)findViewById(R.id.demo_view_pager);
        viewPager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager()));
    }
}
