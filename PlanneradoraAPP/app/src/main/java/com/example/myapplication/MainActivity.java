package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    TextView month,day,year;


    public void onClick(View view) {
        test
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

        month = findViewById(R.id.month);
        day = findViewById(R.id.day);
        year = findViewById(R.id.year);

        Date currentTime = Calendar. getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);

        String[] splitDate = formattedDate.split(",");

        Log.d("myLOG", currentTime.toString());
        Log.d("myLOG", formattedDate);
        month.setText(splitDate[1]);
        day.setText(splitDate[0]);
        year.setText(splitDate[2]);
        Log.d("myLOG", splitDate[0].trim());
        Log.d("myLOG", splitDate[1].trim());
        Log.d("myLOG", splitDate[2].trim());


    }


}