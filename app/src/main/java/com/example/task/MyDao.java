package com.example.task;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addRecord(Product product);

    @Query("SELECT * FROM records ")
    List<Product> getAll();

    @Query("SELECT COUNT(*) from records")
    int countUsers();

}
