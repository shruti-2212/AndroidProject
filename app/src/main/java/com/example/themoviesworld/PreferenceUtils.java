package com.example.themoviesworld;

import android.content.SharedPreferences;

public class PreferenceUtils {

    public static boolean saveId(int id) {
        SharedPreferences sharedPreferences = MovieApp.getContext().getSharedPreferences(DBConstants.PREF_STRING, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("ID", id);
        return editor.commit();
    }

    public static int getId() {
        SharedPreferences sharedPreferences = MovieApp.getContext().getSharedPreferences(DBConstants.PREF_STRING, 0);
        return sharedPreferences.getInt("ID", 0);
    }
}
