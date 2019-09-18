package com.example.themoviesworld.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.themoviesworld.Models.Result;

import java.util.List;

@Dao
public interface ResultDao {


    @Insert
    public void insert(Result result);

    @Query("Delete from result where type=:type")
    public void deleteByType(String type);

    @Query("Select * from result")
    List<Result> getResult();

    @Query("Select * from result where type=:type ")
    List<Result> getResult(String type);

    @Query("Delete from result")
    public void deleteAll();

    @Query("Select MAX(lastTimeStamp) from result where type=:type")
    long getMaxTimeStamp(String type);


    @Update
    void update(Result result);

}
