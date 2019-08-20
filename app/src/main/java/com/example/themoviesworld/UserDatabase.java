package com.example.themoviesworld;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.themoviesworld.Models.Result;
import com.example.themoviesworld.dao.ResultDao;
import com.example.themoviesworld.dao.UserDao;
import com.example.themoviesworld.Models.User;

@Database(entities = {User.class, Result.class},version = 5)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();

    public abstract ResultDao getResultDao();


    //public abstract ExampleDao getExampleDao();

}
