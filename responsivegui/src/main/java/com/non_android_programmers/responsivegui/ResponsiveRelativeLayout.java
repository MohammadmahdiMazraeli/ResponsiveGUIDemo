package com.non_android_programmers.responsivegui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class ResponsiveRelativeLayout extends RelativeLayout implements ResponsiveView {

    private PixelDimensions pixelDimensions;
    private float polarOriginX=0;
    private float polarOriginY=0;
    private float polarRad=0;
    private float polarTheta=0;
    private boolean usePolar = false;

    public ResponsiveRelativeLayout(Context context) {
        super(context);
    }

    public ResponsiveRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPolarCoord(attrs);
    }

    public ResponsiveRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupPolarCoord(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ResponsiveRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupPolarCoord(attrs);
    }

    public PixelDimensions getPixelDimensions() {
        return pixelDimensions;
    }

    public void setupPolarCoord(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ResponsiveRelativeLayout, 0, 0);
        int[] attrsRes= {R.styleable.ResponsiveRelativeLayout_PolarCoordOriginX, R.styleable.ResponsiveRelativeLayout_PolarCoordOriginY, R.styleable.ResponsiveRelativeLayout_PolarCoordRad, R.styleable.ResponsiveRelativeLayout_PolarCoordTheta, R.styleable.ResponsiveRelativeLayout_UsePolar};
        polarOriginX = typedArray.getFloat(attrsRes[0], 0);
        polarOriginY = typedArray.getFloat(attrsRes[1], 0);
        polarRad = typedArray.getFloat(attrsRes[2], 0);
        polarTheta = typedArray.getFloat(attrsRes[3], 0);
        usePolar = typedArray.getBoolean(attrsRes[4], false);
        typedArray.recycle();
    }

    public void calculateDimensions() {
        Context context = getContext();

        ViewGroup.LayoutParams params = getLayoutParams();

        float dpX = 0;
        float dpY = 0;
        float dpEX = 0;
        float dpEY = 0;

        try {
            MarginLayoutParams marginParams = (MarginLayoutParams) params;

            dpX = ScreenDetails.px2Dp(context, marginParams.leftMargin);
            dpY = ScreenDetails.px2Dp(context, marginParams.topMargin);
            dpEX = ScreenDetails.px2Dp(context, marginParams.rightMargin);
            dpEY = ScreenDetails.px2Dp(context, marginParams.topMargin);
        } catch (Exception e) {
        }

        float dpWidth = ScreenDetails.px2Dp(context, params.width);
        float dpHeight = ScreenDetails.px2Dp(context, params.height);

        pixelDimensions = new PixelDimensions(dpX, dpY, dpEX, dpEY, dpWidth, dpHeight, (View) getParent(), polarOriginX, polarOriginY, polarRad, polarTheta, usePolar);
    }

    public void updateDimensions(){
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = pixelDimensions.getWidth();
        layoutParams.height = pixelDimensions.getHeight();

        try {
            MarginLayoutParams marginParams = (MarginLayoutParams) layoutParams;
            marginParams.leftMargin = pixelDimensions.getX();
            marginParams.topMargin = pixelDimensions.getY();
            marginParams.rightMargin = 0;
            marginParams.bottomMargin = 0;
        } catch (Exception e) {
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean editMode = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            editMode=isInEditMode();
        if (!editMode) {
            calculateDimensions();
            updateDimensions();
        }
    }

}
