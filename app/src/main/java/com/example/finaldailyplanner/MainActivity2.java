package com.example.finaldailyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    public static final String EXTRA_REPLY = "task_reply";
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText = findViewById(R.id.task_text);
        button = findViewById(R.id.ok_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(TextUtils.isEmpty(editText.getText())){
                    setResult(RESULT_CANCELED, intent);
                }
                else{
                    String task = editText.getText().toString();
                    intent.putExtra(EXTRA_REPLY, task);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }
}