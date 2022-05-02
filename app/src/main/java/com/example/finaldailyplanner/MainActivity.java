package com.example.finaldailyplanner;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements ItemTouchHelperAdapter{
    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    FloatingActionButton floatingActionButton;
    private TaskViewModel taskViewModel;
    public static final int NEW_WORD_ACTIVITY = 1;
    //shared Preferences
    //https://weeklycoding.com/mpandroidchart/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.item_list);
        floatingActionButton = findViewById(R.id.add_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        taskAdapter = new TaskAdapter(new TaskAdapter.WordDiff(), this, this);
        recyclerView.setAdapter(taskAdapter);
        SpaceBetweenItems spaceBetweenItems = new SpaceBetweenItems(20);
        recyclerView.addItemDecoration(spaceBetweenItems);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, oneTask -> {
            taskAdapter.submitList(oneTask);
        });
        taskAdapter.getTaskViewModel(taskViewModel);

        ItemTouchHelper.Callback callback = new ItemsSwipe(taskAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private void openDialog(int pos){
        ConfirmDeleteTaskFrahment confirmDeleteTaskFrahment = new ConfirmDeleteTaskFrahment();
        confirmDeleteTaskFrahment.setPosition(pos);
        confirmDeleteTaskFrahment.getTaskExample(taskViewModel);
        confirmDeleteTaskFrahment.show(getSupportFragmentManager(), "bruh");
    }

    //создавать фрагмент для добавления
    @Override
    public void onItemClick(int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String dayName = simpleDateFormat.format(date);
        Toast.makeText(this, dayName, Toast.LENGTH_LONG).show();
    }

    //удаление задания полностью
    @Override
    public void onLongClick(int position) {
        openDialog(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_WORD_ACTIVITY && resultCode == RESULT_OK){
            OneTask oneTask = new OneTask(data.getStringExtra(MainActivity2.EXTRA_REPLY));
            taskViewModel.insert(oneTask);
        }
    }
}