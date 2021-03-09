package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener{

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

//        MainActivity.myAccount.add_task(t);
        //MainActivity.tasks.add(t);
        //MainActivity.task_adapter.add(t);
        MainActivity.updater.add(t.name);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_diff1:
                t.difficulty = 1;
                break;
            case R.id.btn_diff2:
                t.difficulty = 2;
            case R.id.btn_diff3:
                t.difficulty = 3;
                break;
            case R.id.btn_add:
                add_task();
                Toast.makeText(this, "Task: " + t.name + " has been added to the list.", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}