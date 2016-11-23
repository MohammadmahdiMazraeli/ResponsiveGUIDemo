package com.non_android_programmers.responsiveguidemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.non_android_programmers.responsivegui.ResponsiveHorizontalScrollView;
import com.non_android_programmers.responsivegui.ResponsiveRelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstDemoFragment extends Fragment {

    ResponsiveHorizontalScrollView scrollView;
    ResponsiveRelativeLayout relativeLayout;

    public FirstDemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_first_demo, container, false);
        scrollView = (ResponsiveHorizontalScrollView) view.findViewById(R.id.horizontal_scrollview);
        relativeLayout = (ResponsiveRelativeLayout) view.findViewById(R.id.relative_layout);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo((relativeLayout.getWidth() - scrollView.getWidth()) / 2, 0);
            }
        }, 1000);
    }

}
