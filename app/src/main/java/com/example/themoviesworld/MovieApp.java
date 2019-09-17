package com.example.themoviesworld;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class MovieApp extends Application {

    private static UserDatabase sUserDatabase;
    private static Context sContext;

    public static UserDatabase getUserDatabase() {
        return sUserDatabase;
    }

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        // Memory intensive.
        sUserDatabase = Room.databaseBuilder(this, UserDatabase.class, "mi-database.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }
}
