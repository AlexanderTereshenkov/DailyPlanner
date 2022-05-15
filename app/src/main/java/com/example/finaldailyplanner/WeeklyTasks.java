package com.example.finaldailyplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;


public class WeeklyTasks extends AppCompatActivity{
    PieChart pieChart;
    TextView allTasks;
    TextView doneTasks;
    TextView notDoneTasks;
    //#87ceeb изначальный цвет

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_tasks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pieChart = findViewById(R.id.diagram);
        allTasks = findViewById(R.id.allTask);
        doneTasks = findViewById(R.id.doneTasks);
        notDoneTasks = findViewById(R.id.notDoneTasks);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        setData(sharedPreferences.getInt("SwipeLeft", 0), sharedPreferences.getInt("SwipeRight", 0));
        allTasks.setText(setAllTasks(sharedPreferences));
        doneTasks.setText(setDoneTasks(sharedPreferences));
        notDoneTasks.setText(setNotDoneTasks(sharedPreferences));

    }
    private void setData(int doneTasks, int notDoneTasks){
        pieChart.addPieSlice(
                new PieModel(
                        "Не выполнено",
                        notDoneTasks,
                        Color.parseColor("#d9544d")
                )
        );
        pieChart.addPieSlice(

                new PieModel(
                        "Выполнено",
                        doneTasks,
                        Color.parseColor("#98fb98")
                )
        );
        pieChart.startAnimation();
    }
    private String setAllTasks(SharedPreferences sharedPreferences){
        return "Всего заданий: " + (sharedPreferences.getInt("SwipeLeft", 0) +
                sharedPreferences.getInt("SwipeRight", 0));
    }
    private String setDoneTasks(SharedPreferences sharedPreferences){
        return "Выполнено: " + sharedPreferences.getInt("SwipeLeft", 0);
    }
    private String setNotDoneTasks(SharedPreferences sharedPreferences){
        return "Не выполнено: " + sharedPreferences.getInt("SwipeRight", 0);
    }
}