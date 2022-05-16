package com.example.finaldailyplanner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private DataRepository dataRepository;
    private LiveData<List<OneTask>> taskList;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        taskList = dataRepository.getTaskList();
    }
    LiveData<List<OneTask>> getAllTasks(){
        return taskList;
    }
    public void insert(OneTask oneTask){
        dataRepository.insertTask(oneTask);
    }
    public void delete(OneTask oneTask) {
        dataRepository.deleteTask(oneTask);
    }
}
