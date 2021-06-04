package com.example.ewallet;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class}, version = 1, exportSchema = false)
public abstract class UsersDatabase extends RoomDatabase {
    public abstract UsersDao usersDao();
    public static UsersDatabase INSTANCE;

    public static UsersDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, UsersDatabase.class, "UsersDatabase").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
