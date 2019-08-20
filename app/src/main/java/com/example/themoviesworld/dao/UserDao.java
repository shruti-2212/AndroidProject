package com.example.themoviesworld.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.themoviesworld.Models.User;

@Dao
public interface UserDao {

    @Insert
    public void insert(User user);

    @Delete
    public void delete(User user);

    @Query("Select Count(*) from user")
    int getUserCount();

    @Query("Select * from user where email =:mail and password =:pass")
    User getuser(String mail,String pass);

    @Query("Select * from user where id=:id")
    User getUser(int id);

    @Query("Select * from user where email =:mail")
    User getUserChangepassword(String mail);

    @Update
    void update(User user);

}
