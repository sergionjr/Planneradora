package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    public static TextView month,day,year;

    public static User myAccount;

    public void onClick(View view) {

    }

    public static class Task{
        String date;
        String name;
        String description;
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

    public static class User{
        String username;
        String password;
        ArrayList<Task> tasks = new ArrayList<Task>();

        User(){
            this.username = "";
            this.password = "";
        }

        User(String username, String password){
            this.username = username;
            this.password = password;
        }

        public void add_task(Task t){
            tasks.add(t);
        }

        public void remove_task(Task t){
            tasks.remove(t);
        }

        public String get_username(){
            return username;
        }

        public String get_password(){
            return password;
        }

        public Task get_task(int index){
            return tasks.get(index);
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