package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener{

    private Button add_task_button, diff1, diff2, diff3;
    TextView choose_diff;
    public static EditText task_name, task_date, task_description;
    public TaskModel t = new TaskModel();
    TextView mTv;
    Button mBtn;

    Calendar c;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mTv = (TextView) findViewById(R.id.txtView);
        mBtn = (Button) findViewById(R.id.btnPick);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        mTv.setText(mDay + "/" + (mMonth+1) + "/" +mYear);
                    }
                }, day, month, year);
                dpd.show();

            }
        });

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
        MainActivity.updater.add(t);

        // Alaskar - Trying to get info from current user to add the task to Database, Needs Database Ref and User from FirebaseAuth
        // to get the current user that is logged in.

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user");

        Toast.makeText(this, "Current User: " + ref.child(user.getUid()).getKey().toString(), Toast.LENGTH_LONG).show();
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