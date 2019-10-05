package com.example.themoviesworld.utils;

import android.util.Log;

import com.example.themoviesworld.AppConstants;

public class LogUtils {

    private static final String TAG = LogUtils.class.getSimpleName();

    public static void v(String iTag, String iMessage) {
        if (AppConstants.ENABLE_LOG)
            Log.v(iTag, iMessage);
    }

    public static void d(String iTag, String iMessage) {
        if (AppConstants.ENABLE_LOG)
            Log.d(iTag, iMessage);
    }

    public static void e(String iTag, String iMessage) {
        if (AppConstants.ENABLE_LOG)
            Log.e(iTag, iMessage);
    }

    public static void i(String iTag, String iMessage) {
        if (AppConstants.ENABLE_LOG)
            Log.i(iTag, iMessage);
    }

}
