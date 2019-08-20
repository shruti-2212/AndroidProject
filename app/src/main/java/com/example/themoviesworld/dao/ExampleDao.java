package com.example.themoviesworld.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.themoviesworld.Models.Example;
import com.example.themoviesworld.Models.User;

@Dao
public interface ExampleDao {
    @Insert
    public void insert(Example example);

    @Delete
    public void delete(Example example);

    /*@Query("Select * from example")
    Example getExample();*/


    @Update
    void update(Example example);
}
