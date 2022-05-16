package com.example.finaldailyplanner;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TaskAdapter extends ListAdapter<OneTask, TaskAdapter.ViewHolder> {
    private static ArrayList<OneTask> tasksList;
    private final LayoutInflater layoutInflater;
    private final ItemTouchHelperAdapter itemTouchHelperAdapter;
    private TaskViewModel taskViewModel;

    public void getTaskViewModel(TaskViewModel taskViewModel){
        this.taskViewModel = taskViewModel;
    }


    public TaskAdapter(@NonNull DiffUtil.ItemCallback<OneTask> diffCallback, ItemTouchHelperAdapter itemTouchHelperAdapter, Context context) {
        super(diffCallback);
        this.itemTouchHelperAdapter = itemTouchHelperAdapter;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.new_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        OneTask oneTask = getItem(position);
        holder.textView.setText(oneTask.getTaskHeading());
    }

    public void deleteTask(int position){
        OneTask oneTask = taskViewModel.getAllTasks().getValue().get(position);
        taskViewModel.delete(oneTask);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.heading);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemTouchHelperAdapter.onItemClick(getAbsoluteAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemTouchHelperAdapter.onLongClick(getAbsoluteAdapterPosition());
                    return true;
                }
            });
        }
    }
    static class WordDiff extends DiffUtil.ItemCallback<OneTask>{

        @Override
        public boolean areItemsTheSame(@NonNull OneTask oldItem, @NonNull OneTask newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull OneTask oldItem, @NonNull OneTask newItem) {
            return oldItem.getTaskHeading().equals(newItem.getTaskHeading());
        }
    }

}