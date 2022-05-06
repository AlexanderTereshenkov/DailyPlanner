package com.example.finaldailyplanner;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(OneTask oneTask);


    @Query("SELECT * from oneTask_table")
    LiveData<List<OneTask>> getAll();


    @Delete
    void delete(OneTask oneTask);


}
