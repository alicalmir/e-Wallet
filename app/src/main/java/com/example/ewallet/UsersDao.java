package com.example.ewallet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UsersDao {
    @Insert
    public void add(Users user);

    @Delete
    public void delete(Users user);

    @Query("SELECT * FROM users WHERE id = :id")
    public Users get(int id);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    public Users getUser(String email, String password);

    @Query("SELECT id FROM users WHERE email = :email AND password = :password")
    public int getId(String email, String password);

    @Query("SELECT * FROM users WHERE email =:email")
    public Users getEmail(String email);


}