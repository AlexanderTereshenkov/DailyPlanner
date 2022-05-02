package com.example.finaldailyplanner;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DataRepository {
    private TaskDao taskDao;
    private LiveData<List<OneTask>> taskList;
    DataRepository(Application application){
        TaskDataBase taskDataBase = TaskDataBase.getDatabase(application);
        taskDao = taskDataBase.taskDao();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String dayName = simpleDateFormat.format(date);
        taskList = taskDao.getAll();
    }
    LiveData<List<OneTask>> getTaskList(){
        return taskList;
    }
    void insertTask(OneTask oneTask){
        TaskDataBase.databaseWriteExecutor.execute(() -> {
            taskDao.insert(oneTask);
        });
    }
    void deleteTask(OneTask oneTask){
        TaskDataBase.databaseWriteExecutor.execute(() -> {
            taskDao.delete(oneTask);
        });
    }

}
