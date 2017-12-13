
package com.ivy.bakingapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class DisplayMetricUtils {

    public static int convertPixelsToDp(int px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = (float) px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static int convertDpToPixel(int dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = (float) dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public static int getDeviceWidth(Context activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) activity).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    public static int getDeviceHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getDeviceOrientation(Context context) {
        return context.getResources().getConfiguration().orientation;
    }

}
