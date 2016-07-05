package com.project.levi.news.common;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.project.levi.news.App;

/**
 * Created by Levi on 7/4/2016.
 */

public class Utils {

    public static String createSearchQuery(String type, String query, String[] fields) {
        StringBuilder builder = new StringBuilder(Constant.FACEBOOK_GRAPH_SEARCH);
        builder.append("type=");
        builder.append(type);
        builder.append("&");
        builder.append("q=");
        builder.append(query);
        builder.append("&");
        builder.append("fields=");
        for (int i = 0; i < fields.length; i++) {
            if (i == (fields.length - 1)) {
                builder.append(fields[i]);
            } else {
                builder.append(fields[i]);
                builder.append(",");
            }
        }
        return builder.toString();
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp){
        Resources resources = App.getInstance().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px){
        Resources resources = App.getInstance().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
