package com.non_android_programmers.responsiveguiapi9;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.non_android_programmers.responsivegui.PixelDimensions;
import com.non_android_programmers.responsivegui.ResponsiveView;
import com.non_android_programmers.responsivegui.ScreenDetails;

/**
 * Created by mohammadmahdi on 10/17/16.
 */
public class ResponsiveCardView extends CardView implements ResponsiveView {

    private PixelDimensions pixelDimensions;
    private float polarOriginX=0;
    private float polarOriginY=0;
    private float polarRad=0;
    private float polarTheta=0;
    private boolean usePolar = false;


    public ResponsiveCardView(Context context) {
        super(context);
    }

    public ResponsiveCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPolarCoord(attrs);
    }

    public ResponsiveCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupPolarCoord(attrs);
    }

    public PixelDimensions getPixelDimensions() {
        return pixelDimensions;
    }


    public void setupPolarCoord(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ResponsiveCardView, 0, 0);
        int[] attrsRes= {R.styleable.ResponsiveCardView_PolarCoordOriginX, R.styleable.ResponsiveCardView_PolarCoordOriginY, R.styleable.ResponsiveCardView_PolarCoordRad, R.styleable.ResponsiveCardView_PolarCoordTheta, R.styleable.ResponsiveCardView_UsePolar};
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
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) params;

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
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) layoutParams;
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
        if (!isInEditMode()) {
            calculateDimensions();
            updateDimensions();
        }
    }
}
