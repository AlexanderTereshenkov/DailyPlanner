package com.example.finaldailyplanner;



import android.graphics.Canvas;
import android.graphics.Color;

import android.util.Log;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class ItemsSwipe extends ItemTouchHelper.Callback {
    TaskAdapter taskAdapter;
    private swipeListener SwipeListener;

    public ItemsSwipe(TaskAdapter taskAdapter, swipeListener SwipeListener) {
        this.taskAdapter = taskAdapter;
        this.SwipeListener = SwipeListener;
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
            SwipeListener.swipeLeft();
            taskAdapter.deleteTask(viewHolder.getAbsoluteAdapterPosition());
        }
        else{
            SwipeListener.swipeRight();
            taskAdapter.deleteTask(viewHolder.getAbsoluteAdapterPosition());
            Log.i("Log_task", "Right");
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(Color.parseColor("#90EE90"))
                .addSwipeLeftActionIcon(R.drawable.ic_baseline_check_24)
                .addSwipeRightBackgroundColor(Color.RED)
                .addSwipeRightActionIcon(R.drawable.ic_baseline_close_24)
                .create()
                .decorate();
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

}
