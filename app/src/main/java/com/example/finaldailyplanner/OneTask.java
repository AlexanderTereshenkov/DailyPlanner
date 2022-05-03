package com.example.finaldailyplanner;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "oneTask_table")
public class OneTask {
    @PrimaryKey
    @NonNull
    String dayName;
    @NonNull
    @ColumnInfo(name = "task")
    private String taskHeading;

    public OneTask(String taskHeading) {

        this.taskHeading = taskHeading;
    }


    public String getTaskHeading() {

        return taskHeading;
    }

    public void setDayName(@NonNull String dayName) {
        this.dayName = dayName;
    }
}
