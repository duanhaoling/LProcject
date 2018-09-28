package com.ldh.android.rxhttpcore.utils;

public final class UriUtils {

    public static final String TAG = "OkHttp";
    public static final String baseUrl = "http://gank.io/api";
    public static final String API_VER_3 = "/3.0/";


    public static String addCommonParams(String url, String qtime) {
        StringBuffer tempBuffer = new StringBuffer(url);
        if (url.contains("?")) {
            tempBuffer.append("&");
        } else {
            tempBuffer.append(("?"));
        }
        tempBuffer.append(getCommonParams());
        return tempBuffer.toString();
    }

    private static String getCommonParams() {
        return "tools=retrofit&from=android";
    }


}
