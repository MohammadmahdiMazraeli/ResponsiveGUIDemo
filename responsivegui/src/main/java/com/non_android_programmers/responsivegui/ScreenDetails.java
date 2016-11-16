package com.non_android_programmers.responsivegui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by alireza on 10/15/16.
 */
public class ScreenDetails {
    final public static int DP_WIDTH = 411;
    final public static int DP_HEIGHT = 731;

    public static int pixelWidth;
    public static int pixelHeight;

    public static void getScreenDimensions(Context context){

        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point size = new Point();
            display.getRealSize(size);
            pixelWidth = size.x;
            pixelHeight = size.y;
        }else {
            pixelWidth = display.getWidth();
            pixelHeight = display.getHeight();
        }
    }

    public static float px2Dp(Context context,int px) {
        DisplayMetrics displayMetrics = context.getResources().getSystem().getDisplayMetrics();//context.getResources().getDisplayMetrics();
        return (float) px / displayMetrics.density;
    }
}