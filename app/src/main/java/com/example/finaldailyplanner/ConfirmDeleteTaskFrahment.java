package com.example.finaldailyplanner;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ConfirmDeleteTaskFrahment extends DialogFragment {
    private int position;
    private TaskViewModel taskViewModel;

    public void getTaskExample(TaskViewModel taskViewModel){
        this.taskViewModel = taskViewModel;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Удалить задание?")
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        OneTask oneTask = taskViewModel.getAllTasks().getValue().get(position);
                        taskViewModel.delete(oneTask);
                        Toast.makeText(getActivity(), "Удалено", Toast.LENGTH_LONG).show();
                    }
                });
        return builder.create();
    }

}
