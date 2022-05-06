package com.example.finaldailyplanner;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class ItemsSwipe extends ItemTouchHelper.Callback {
    TaskAdapter taskAdapter;
    private int onSwipeLeft = 0;
    private int onSwipeRight = 0;
    NumberStorage numberStorage = new NumberStorage();

    public void setOnSwipeLeft(int onSwipeLeft) {
        this.onSwipeLeft = onSwipeLeft;
    }

    public void setOnSwipeRight(int onSwipeRight) {
        this.onSwipeRight = onSwipeRight;
    }

    public ItemsSwipe(TaskAdapter taskAdapter) {
        this.taskAdapter = taskAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if(direction == ItemTouchHelper.LEFT){
           numberStorage.swipeLeft();
            taskAdapter.deleteTask(viewHolder.getAbsoluteAdapterPosition());
        }
        else{
            numberStorage.swipeRight();
            taskAdapter.deleteTask(viewHolder.getAbsoluteAdapterPosition());
            Log.i("Log_task", "Right");
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(Color.GREEN)
                .addSwipeLeftActionIcon(R.drawable.ic_baseline_check_24)
                .addSwipeRightBackgroundColor(Color.RED)
                .addSwipeRightActionIcon(R.drawable.ic_baseline_close_24)
                .create()
                .decorate();
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

}
