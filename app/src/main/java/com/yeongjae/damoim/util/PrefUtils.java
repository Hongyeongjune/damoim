package com.yeongjae.damoim.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    public static void writeAutoSignInTo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_DAMOIM", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("PREF_AUTO_SIGNIN", true);
        editor.apply();
    }

    public static boolean readAutoSignInFrom(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_DAMOIM", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("PREF_AUTO_SIGNIN", false);
    }
}
