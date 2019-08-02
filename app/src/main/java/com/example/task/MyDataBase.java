package com.example.task;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class},version = 1,exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    public abstract MyDao myDao();

}
