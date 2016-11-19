package com.non_android_programmers.responsivegui;

import android.view.View;

/**
 * Created by alireza on 10/15/16.
 */
public class PixelDimensions {
    private int x;
    private int y;
    private int width;
    private int height;

    private float dpX;
    private float dpY;
    private float dpEX;
    private float dpEY;
    private float dpW;
    private float dpH;

    private float xRatio;
    private float yRatio;

    private float wRatio;
    private float hRatio;

    private float polarOriginX=0;
    private float polarOriginY=0;
    private float polarRad=0;
    private float polarTheta=0;

    private boolean usePolar = false;

    public PixelDimensions() {
    }

    public PixelDimensions(float dpX, float dpY, float dpEX, float dpEY, float dpWidth, float dpHeight, View parent) {
        this.dpX = dpX;
        this.dpY = dpY;
        this.dpEX = dpEX;
        this.dpEY = dpEY;
        this.dpW = dpWidth;
        this.dpH = dpHeight;
        setFromDp(parent);
    }
    public PixelDimensions(float dpX, float dpY, float dpEX, float dpEY, float dpWidth, float dpHeight, View parent, float polarOriginX, float polarOriginY, float polarRad, float polarTheta, boolean usePolar) {
        this.dpX = dpX;
        this.dpY = dpY;
        this.dpEX = dpEX;
        this.dpEY = dpEY;
        this.dpW = dpWidth;
        this.dpH = dpHeight;
        this.polarOriginX = polarOriginX;
        this.polarOriginY = polarOriginY;
        this.polarRad = polarRad;
        this.polarTheta = polarTheta;
        this.usePolar = usePolar;
        setFromDp(parent);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setFromDp(View parent) {
        float screenDPW, screenDPH, screenPXW, screenPXH, parentWRatio, parentHRatio;

        try {
            ResponsiveView responsiveView = (ResponsiveView) parent;
            screenDPW = responsiveView.getPixelDimensions().dpW ;
            screenDPH = responsiveView.getPixelDimensions().dpH;
            screenPXW = responsiveView.getPixelDimensions().width;
            screenPXH = responsiveView.getPixelDimensions().height;

            parentWRatio = responsiveView.getPixelDimensions().wRatio;
            parentHRatio = responsiveView.getPixelDimensions().hRatio;


        } catch (Exception e) {
            screenDPW = ScreenDetails.DP_WIDTH;
            screenDPH = ScreenDetails.DP_HEIGHT;
            screenPXW = ScreenDetails.pixelWidth;
            screenPXH = ScreenDetails.pixelHeight;

            parentWRatio = screenPXW / screenDPW;
            parentHRatio = screenPXH / screenDPH;
        }

        if (dpW < 0) {
            dpW = screenDPW - dpX - dpEX;
            wRatio = parentWRatio;
        }
        else
            wRatio = Math.min (parentWRatio, parentHRatio);

        if (dpH < 0) {
            dpH = screenDPH - dpY - dpEY;
            hRatio = parentHRatio;
        }
        else
            hRatio = Math.min (parentWRatio, parentHRatio);

        if (usePolar) {
            this.dpX = polarOriginX  + (float) Math.cos(polarTheta * Math.PI / 180.0f) * polarRad / parentWRatio * wRatio - dpW / 2;
            this.dpY = polarOriginY - (float) Math.sin(polarTheta * Math.PI / 180.0f) * polarRad / parentHRatio * hRatio - dpH / 2;
        }

        this.width = Math.round(dpW * wRatio);
        this.height = Math.round(dpH * hRatio);
        this.x = Math.round((dpX + dpW / 2 ) * parentWRatio);
        this.y = Math.round((dpY + dpH / 2 ) * parentHRatio);
        this.x -= this.width/2;
        this.y -= this.height/2;
    }

    public void setFromDpOld(View parent) {

        ResponsiveView responsiveView = null;
        float screenDPW = 0, screenDPH = 0, screenPXW = 0, screenPXH = 0;

        float minRatio = Math.min((float) ScreenDetails.pixelWidth / (float) ScreenDetails.DP_WIDTH, (float) ScreenDetails.pixelHeight/(float) ScreenDetails.DP_HEIGHT);

        try {
            responsiveView = (ResponsiveView) parent;
            screenDPW = responsiveView.getPixelDimensions().dpW;
            screenDPH = responsiveView.getPixelDimensions().dpH;
            screenPXW = responsiveView.getPixelDimensions().width;
            screenPXH = responsiveView.getPixelDimensions().height;
            xRatio = responsiveView.getPixelDimensions().xRatio;
            yRatio = responsiveView.getPixelDimensions().yRatio;
            minRatio = Math.min(screenPXW/screenDPW, screenPXH/screenDPH);
        } catch (Exception e) {
            screenDPW = ScreenDetails.DP_WIDTH;
            screenDPH = ScreenDetails.DP_HEIGHT;
            screenPXW = ScreenDetails.pixelWidth;
            screenPXH = ScreenDetails.pixelHeight;
            xRatio = screenPXW / screenDPW;
            yRatio = screenPXH / screenDPH;
        }



        if (dpW < 0) {
            dpW = screenDPW - dpX;
            this.width = Math.round(dpW * xRatio);
        } else
            this.width = Math.round( dpW * minRatio);

        if (dpH < 0) {
            dpH = screenDPH - dpY;
            this.height = Math.round( dpH * yRatio);
        } else
            this.height = Math.round( dpH * minRatio);


        dpH = (dpH < 0) ? (screenDPH - dpY) : dpH;

        this.x = Math.round((dpX + dpW / 2 ) * xRatio);
        this.y = Math.round((dpY + dpH / 2 ) * yRatio);
        this.x -= this.width/2;
        this.y -= this.height/2;
    }


}