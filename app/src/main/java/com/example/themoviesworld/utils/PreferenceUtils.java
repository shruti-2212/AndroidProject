package com.example.themoviesworld.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.themoviesworld.DBConstants;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.dao.ResultDao;


import static com.example.themoviesworld.DBConstants.TO_HRS;
import static com.example.themoviesworld.DBConstants.TYPE1;
import static com.example.themoviesworld.DBConstants.TYPE2;
import static com.example.themoviesworld.DBConstants.TYPE3;

public class PreferenceUtils {

    public static boolean saveId(int id) {
        SharedPreferences sharedPreferences = MovieApp.getContext().getSharedPreferences(DBConstants.PREF_STRING, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("ID", id);
        return editor.commit();
    }

    public static int getId()
     {
        SharedPreferences sharedPreferences = MovieApp.getContext().getSharedPreferences(DBConstants.PREF_STRING, 0);
        return sharedPreferences.getInt("ID", 0);
    }
    public static boolean shouldRefresh(String TYPE){
        ResultDao resultDao= MovieApp.getUserDatabase().getResultDao();

        long lastTime = resultDao.getMaxTimeStamp(TYPE);


        //Log.i("TAG", "Time TRM : " + lastTimeTRM + "-" + DateTimeUtils.getCurrentTime());

        long currentTime = DateTimeUtils.getCurrentTime();


        float hrs = (DateTimeUtils.getTimeDifferenceinSeconds(lastTime, currentTime)) / TO_HRS;
        Log.i("TAG", "shouldRefresh: "+hrs+" "+currentTime+" "+lastTime);
        return hrs>=4;
    }
}




        /*Log.i("TAG", "onCreate: " + " " + hrsTRM + " " + hrsPM + " " + hrsUM);
        switch (TYPE){
            case TYPE1:
                return hrs>=4;
                break;

            case TYPE2:
                return hrs>=4;
                break;

            case TYPE3:
                return hrs>=4;
                break;
        }*/

