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

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;


public class WeeklyTasks extends AppCompatActivity{
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_tasks);
        pieChart = findViewById(R.id.diagram);
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        setData(sharedPreferences.getInt("Done", 0), sharedPreferences.getInt("NotDone", 0));
    }
    private void setData(int doneTasks, int notDoneTasks){
        pieChart.addPieSlice(
                new PieModel(
                        "Не выполнено",
                        doneTasks,
                        Color.RED
                )
        );
        pieChart.addPieSlice(

                new PieModel(
                        "Выполнено",
                        notDoneTasks,
                        Color.GREEN
                )
        );
    }
}