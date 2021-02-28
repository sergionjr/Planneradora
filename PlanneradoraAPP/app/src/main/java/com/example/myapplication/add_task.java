package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class add_task extends AppCompatActivity {

    private Button add_task_button, diff1, diff2, diff3;
    TextView choose_diff;
    EditText task_name, task_date, task_description;
    public static MainActivity.Task t = new MainActivity.Task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        task_name = findViewById(R.id.edtTxtTaskName);
        task_date = findViewById(R.id.edtTxtDate);
        task_description = findViewById(R.id.edtTxtDescription);

        MainActivity.Task t = new MainActivity.Task();

        t.name = task_name.getText().toString();
        t.date = task_date.getText().toString();
        t.description = task_description.getText().toString();

        diff1 = (Button) findViewById(R.id.btnDiff1);
        diff2 = (Button) findViewById(R.id.btnDiff2);
        diff3 = (Button) findViewById(R.id.btnDiff3);
        add_task_button = (Button) findViewById(R.id.btnAdd);

        diff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.difficulty = 1;
            }
        });

        diff2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.difficulty = 2;
            }
        });

        diff3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.difficulty = 3;
            }
        });

        add_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_task();
            }
        });
    }


    public void add_task(){
        MainActivity.myAccount.add_task(t);
        startActivity(new Intent(add_task.this, MainActivity.class));
    }

    public void onClick(View view) {
    }
}