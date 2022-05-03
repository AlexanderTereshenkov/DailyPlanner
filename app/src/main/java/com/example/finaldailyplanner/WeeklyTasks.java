package com.example.finaldailyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class WeeklyTasks extends AppCompatActivity implements ItemTouchHelperAdapter {
    RecyclerView recyclerView_monday;
    RecyclerView recyclerView_tuesday;
    RecyclerView recyclerView_wednesday;
    RecyclerView recyclerView_thursday;
    RecyclerView recyclerView_friday;
    RecyclerView recyclerView_saturday;
    RecyclerView recyclerView_sunday;
    private RecyclerView[] list = new RecyclerView[7];
    TaskAdapter taskAdapter;
    TaskViewModel taskViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_tasks);

    }

    @Override
    public void onItemClick(int position) {

    }

    private void openDialog(int pos){
        ConfirmDeleteTaskFrahment confirmDeleteTaskFrahment = new ConfirmDeleteTaskFrahment();
        confirmDeleteTaskFrahment.setPosition(pos);
        confirmDeleteTaskFrahment.getTaskExample(taskViewModel);
        confirmDeleteTaskFrahment.show(getSupportFragmentManager(), "bruh");
    }

    @Override
    public void onLongClick(int position) {
        openDialog(position);
    }

    private void setUpAdapters(){
        recyclerView_monday = findViewById(R.id.monday_view);
        recyclerView_tuesday = findViewById(R.id.tuesday_view);
        recyclerView_wednesday = findViewById(R.id.wednesday_view);
        recyclerView_thursday = findViewById(R.id.thursday_view);
        recyclerView_friday = findViewById(R.id.friday_view);
        recyclerView_saturday = findViewById(R.id.saturday_view);
        recyclerView_sunday = findViewById(R.id.sunday_view);

        list[0] = recyclerView_monday;
        list[1] = recyclerView_tuesday;
        list[2] = recyclerView_wednesday;
        list[3] = recyclerView_thursday;
        list[4] = recyclerView_friday;
        list[5] = recyclerView_friday;
        list[6] = recyclerView_sunday;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        for(int i = 0; i < list.length; i++){
            list[i].setLayoutManager(layoutManager);
        }

        taskAdapter = new TaskAdapter(new TaskAdapter.WordDiff(), this, this);

        for(int i = 0; i < list.length; i++){
            list[i].setAdapter(taskAdapter);
        }

        SpaceBetweenItems spaceBetweenItems = new SpaceBetweenItems(20);
        for(int i = 0; i < list.length; i++){
            list[i].addItemDecoration(spaceBetweenItems);
        }

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, oneTask -> {
            taskAdapter.submitList(oneTask);
        });

    }


}