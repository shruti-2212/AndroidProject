package com.example.themoviesworld.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.themoviesworld.DBConstants;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.dao.ResultDao;


import static com.example.themoviesworld.DBConstants.TO_HRS;

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




