package com.example.finaldailyplanner;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements ItemTouchHelperAdapter, swipeListener{
    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    FloatingActionButton floatingActionButton;
    Button button;
    private TaskViewModel taskViewModel;
    public static final int NEW_WORD_ACTIVITY = 1;

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
        button = findViewById(R.id.new_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeeklyTasks.class);
                startActivity(intent);
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
        ItemTouchHelper.Callback callback = new ItemsSwipe(taskAdapter, this);
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

    @Override
    public void swipeLeft() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int swipeLeftCounter = sharedPreferences.getInt("SwipeLeft", 0) + 1;
        editor.putInt("SwipeLeft", swipeLeftCounter);
        editor.apply();
        Toast.makeText(this, "Left", Toast.LENGTH_LONG).show();
    }

    @Override
    public void swipeRight() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int swipeRightCounter = sharedPreferences.getInt("SwipeRight", 0) + 1;
        editor.putInt("SwipeRight", swipeRightCounter);
        editor.apply();
        Toast.makeText(this, "Right", Toast.LENGTH_LONG).show();
    }
}