package com.example.themoviesworld;

import android.app.Application;

import androidx.room.Room;

import com.example.themoviesworld.Activities.LayoutActivity;

public class MovieApp extends Application {

    public static UserDatabase getsUserDatabase() {
        return sUserDatabase;
    }

    private static UserDatabase sUserDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        // Memory intensive.
        sUserDatabase = Room.databaseBuilder(this, UserDatabase.class, "mi-database.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }
}
