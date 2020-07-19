package com.yeongjae.damoim.util;

import android.util.Log;

public class LogUtils {

    private static final String LOG_TAG = "DAMOIM";
    private static final String FORMAT = "[%s] : %s";

    public static void logInfo(String message) {
        Log.i(LOG_TAG, String.format(FORMAT, getCallerInfo(), message));
    }

    public static void logDebug(String msg) {
        Log.d(LOG_TAG, String.format(FORMAT, getCallerInfo(), msg));
    }

    private static String getCallerInfo() {
        StackTraceElement[] elements = new Exception().getStackTrace();
        String className = elements[2].getClassName();
        return className.substring(className.lastIndexOf(".") +1,
                className.length()) + "_" + elements[2].getLineNumber();
    }
}
