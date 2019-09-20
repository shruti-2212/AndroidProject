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
        long lastTimeTRM = resultDao.getMaxTimeStamp(TYPE1);
        long lastTimePM = resultDao.getMaxTimeStamp(TYPE2);
        long lastTimeUM = resultDao.getMaxTimeStamp(TYPE3);

        Log.i("TAG", "Time TRM : " + lastTimeTRM + "-" + DateTimeUtils.getCurrentTime());

        long currentTime = DateTimeUtils.getCurrentTime();

        float hrsTRM = (DateTimeUtils.getTimeDifferenceinSeconds(lastTimeTRM, currentTime)) / TO_HRS;
        float hrsPM = (DateTimeUtils.getTimeDifferenceinSeconds(lastTimePM, currentTime)) / TO_HRS;
        float hrsUM = (DateTimeUtils.getTimeDifferenceinSeconds(lastTimeUM, currentTime)) / TO_HRS;

        Log.i("TAG", "onCreate: " + " " + hrsTRM + " " + hrsPM + " " + hrsUM);
        switch (TYPE){
            case TYPE1:
                return hrsTRM>=4;

            case TYPE2:
                return hrsPM>=4;

            case TYPE3:
                return hrsUM>=4;
        }

        return false;
    }
}
