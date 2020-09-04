package com.liyahui.wechartdemo.utils;

import android.util.Log;

import com.liyahui.wechartdemo.BuildConfig;

public class L {
    private static final String TAG = "liyahui";
    private static boolean isDebug = BuildConfig.DEBUG;

    public static void d(String msg, Object... args) {
        if (!isDebug) {
            return;
        }
        Log.d(TAG, String.format(msg, args));
    }
}
