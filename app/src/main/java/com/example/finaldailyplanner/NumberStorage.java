package com.example.finaldailyplanner;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class NumberStorage extends AppCompatActivity {
    private int onSwipeLeft = 7;
    private int onSwipeRight = 13;

    public int getOnSwipeLeft() {
        return onSwipeLeft;
    }

    public int getOnSwipeRight() {
        return onSwipeRight;
    }

    public void setOnSwipeLeft(int onSwipeLeft) {
        this.onSwipeLeft = onSwipeLeft;
    }

    public void setOnSwipeRight(int onSwipeRight) {
        this.onSwipeRight = onSwipeRight;
    }
    public void swipeLeft(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        onSwipeLeft++;
        editor.putInt("Done", onSwipeLeft);
        editor.apply();
    }
    public void swipeRight(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        onSwipeRight++;
        editor.putInt("NotDone", onSwipeRight);
        editor.apply();
    }
}
