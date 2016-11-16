package com.non_android_programmers.responsivegui;

import android.util.AttributeSet;

/**
 * Created by mohammadmahdi on 10/17/16.
 */
public interface ResponsiveView  {
    PixelDimensions getPixelDimensions();
    void calculateDimensions();
    void updateDimensions();
    void setupPolarCoord(AttributeSet attrs);
}
