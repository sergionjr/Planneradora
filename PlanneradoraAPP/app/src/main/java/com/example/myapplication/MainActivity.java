package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_register, btn_signIn;
    private Button button;
    public static TextView month,day,year;

    public static ArrayList<String> tasks;
    public ArrayAdapter<String> task_adapter;
    public static ListView list_view;

    public static String updater = "";


//    public static User myAccount;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_task_button:
                openAdd_task();
                break;
//            case R.id.btnDiff1:
//                Toast.makeText(this, "Chosen Difficulty 1", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.btnDiff2:
//                Toast.makeText(this, "Chosen Difficulty 2", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.btnDiff3:
//                Toast.makeText(this, "Chosen Difficulty 3", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.btnAdd:
//                EditText task_name = findViewById(R.id.edtTxtTaskName);
//                task_adapter.add(task_name.getText().toString());
//                setContentView(R.layout.activity_main);
//                break;
            case R.id.rmButton:
                add_to_adapter();
                if(task_adapter.isEmpty()){
                    Toast.makeText(this, "You didn't add any new tasks", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "You have at least one new task", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
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

//    public static class User{
//        String username;
//        String password;
//        ArrayList<Task> tasks = new ArrayList<Task>();
//
//        User(){
//            this.username = "";
//            this.password = "";
//        }
//
//        User(String username, String password){
//            this.username = username;
//            this.password = password;
//        }
//
//        public void add_task(Task t){
//            tasks.add(t);
//        }
//
//        public void remove_task(Task t){
//            tasks.remove(t);
//        }
//
//        public String get_username(){
//            return username;
//        }
//
//        public String get_password(){
//            return password;
//        }
//
//        public Task get_task(int index){
//            return tasks.get(index);
//        }

//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        btn_signIn = findViewById(R.id.btn_signIn);

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

        list_view = findViewById(R.id.listView);


        tasks = new ArrayList<>();
        task_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        list_view.setAdapter(task_adapter);
        setUpListViewListener();

//        button = (Button) findViewById(R.id.add_task_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAdd_task();
//            }
//        });
//
//        btn_register = (Button) findViewById(R.id.btn_register);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    public void add_to_adapter(){
        task_adapter.add(updater);
    }

    private void setUpListViewListener() {
        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();

                tasks.remove(position);
                task_adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void openAdd_task(){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

}