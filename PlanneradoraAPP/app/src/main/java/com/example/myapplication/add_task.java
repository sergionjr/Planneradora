package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class add_task extends AppCompatActivity implements View.OnClickListener{

    private Button add_task_button, diff1, diff2, diff3;
    TextView choose_diff;
    public static EditText task_name, task_date, task_description;
    public static MainActivity.Task t = new MainActivity.Task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
//
//        diff1 = (Button) findViewById(R.id.btnDiff1);
//        diff2 = (Button) findViewById(R.id.btnDiff2);
//        diff3 = (Button) findViewById(R.id.btnDiff3);
//        add_task_button = (Button) findViewById(R.id.btnAdd);
//
//        diff1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                t.difficulty = 1;
//            }
//        });
//
//        diff2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                t.difficulty = 2;
//            }
//        });
//
//        diff3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                t.difficulty = 3;
//            }
//        });
//
//        add_task_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                add_task();
//            }
//        });
    }


    public void add_task(){
        task_name = findViewById(R.id.edtTxtTaskName);
        task_date = findViewById(R.id.edtTxtDate);
        task_description = findViewById(R.id.edtTxtDescription);

        t.name = task_name.getText().toString();
        t.date = task_date.getText().toString();
        t.description = task_description.getText().toString();

        MainActivity.myAccount.add_task(t);

        startActivity(new Intent(add_task.this, MainActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnDiff1:
                Toast.makeText(this, "Hello Difficulty 1", Toast.LENGTH_SHORT).show();
                t.difficulty = 1;
                break;
            case R.id.btnDiff2:
                t.difficulty = 2;
            case R.id.btnDiff3:
                t.difficulty = 3;
                break;
            case R.id.btnAdd:
                add_task();
                break;
            default:
                break;
        }
    }
}