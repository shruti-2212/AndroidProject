package com.example.themoviesworld;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    SharedPreferences sharedPreferences;
    Context mcontext;

    public PreferenceUtils( Context mcontext) {

        this.mcontext = mcontext;
    }
    public static boolean saveId(int id,Context mcontext)
    {
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences("Preferences",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("ID",id);
        editor.commit();
        return true;
    }
    public  static int getId(Context mcontext)
    {
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences("Preferences",0);
        return sharedPreferences.getInt("ID",0);
    }
}
