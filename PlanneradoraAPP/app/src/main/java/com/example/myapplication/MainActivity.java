package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    public static class Task{
        String date;
        int difficulty;
        boolean finished;

        Task(){
            this.date = "";
            this.difficulty = 1;
            this.finished = false;
        }

        Task(String date, int difficulty, boolean finished){
            this.date = date;
            this.difficulty = difficulty;
            this.finished = finished;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.add_task_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd_task();
            }
        });
    }

    public void openAdd_task(){
        Intent intent = new Intent(this, add_task.class);
        startActivity(intent);
    }

}