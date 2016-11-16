package com.non_android_programmers.responsiveguidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.non_android_programmers.responsivegui.ScreenDetails;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ScreenDetails.getScreenDimensions(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }
}
