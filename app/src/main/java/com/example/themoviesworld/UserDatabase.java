package com.example.themoviesworld;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.themoviesworld.Models.Result;
import com.example.themoviesworld.Models.User;
import com.example.themoviesworld.dao.ResultDao;
import com.example.themoviesworld.dao.UserDao;

// FIXME there is no migration code but still version is 5
@Database(entities = {User.class, Result.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();

    public abstract ResultDao getResultDao();

    //public abstract ExampleDao getExampleDao();

}
