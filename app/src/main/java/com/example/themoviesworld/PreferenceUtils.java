package com.example.themoviesworld;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    SharedPreferences sharedPreferences;
    Context mContext;


    public PreferenceUtils(Context mContext) {

        this.mContext = mContext;
    }

    public static boolean saveId(int id, Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(DBConstants.PREF_STRING, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("ID", id);
        editor.commit();
        return true;
    }

    public static int getId(Context mcontext) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(DBConstants.PREF_STRING, 0);
        return sharedPreferences.getInt("ID", 0);
    }
}
