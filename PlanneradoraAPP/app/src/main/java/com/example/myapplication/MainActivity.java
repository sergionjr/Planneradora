package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void onClick(View view) {

    }

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
    }


}