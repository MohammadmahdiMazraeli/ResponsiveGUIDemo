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
        scrollView = (ResponsiveHorizontalScrollView) getView().findViewById(R.id.horizontal_scrollview);
        scrollView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                scrollView.scrollTo((relativeLayout.getWidth() - scrollView.getWidth()) / 2, 0);
            }
        });
    }

}
