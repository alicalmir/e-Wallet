package com.example.ewallet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChangesDao {
    @Insert
    public void add(Changes changes);

    @Delete
    public void delete(Changes changes);

    @Query("SELECT * FROM changes WHERE id = :id")
    public Changes get(int id);

    @Query("SELECT * FROM changes WHERE usersId = :id")
    public List<Changes> getChanges(int id);

   /* @Query("SELECT longitude, latitude FROM changes WHERE id =:id")
    public List<String> getChangePlace(int id);*/

    @Query("SELECT amount FROM changes WHERE usersId = :id LIMIT 1")
    public double getAmount(int id);
}
