package com.example.themoviesworld.utils;

import android.util.Log;

import com.example.themoviesworld.DBConstants;
import com.example.themoviesworld.MovieApp;
import com.example.themoviesworld.dao.ResultDao;
import com.example.themoviesworld.utils.DateTimeUtils;

public class DBUtils implements DBConstants {
    // TODO Make use of it for Database operations
    public static boolean shouldRefresh(String TYPE) {
        ResultDao resultDao = MovieApp.getUserDatabase().getResultDao();

        long lastTime = resultDao.getMaxTimeStamp(TYPE);

        long currentTime = DateTimeUtils.getCurrentTime();


        float hrs = (DateTimeUtils.getTimeDifferenceinSeconds(lastTime, currentTime)) / TO_HRS;
        Log.i("TAG", "shouldRefresh: " + hrs + " " + currentTime + " " + lastTime);
        return hrs >= 4;
    }
}