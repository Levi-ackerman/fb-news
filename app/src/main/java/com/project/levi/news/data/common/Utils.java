package com.project.levi.news.data.common;

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
}
