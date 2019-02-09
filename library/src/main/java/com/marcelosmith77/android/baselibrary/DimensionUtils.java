package com.melgames.manicure.utils;

import android.content.Context;

public class DimensionUtils {

    /**
     * convert Dip to Pixels.
     */
    public static int convertDiptoPix(Context context, int dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dip * scale + 0.5f);

        return pixels;
    }


    /**
     * convert Pixels to Dips.
     */
    public static int convertPixtoDip(Context context, int pixel) {
        float scale = context.getResources().getDisplayMetrics().density;
        int dips = (int) ((pixel - 0.5f) / scale);

        return dips;
    }

}
