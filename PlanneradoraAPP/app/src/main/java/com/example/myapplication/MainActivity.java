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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    private DatabaseReference dbRef;
    private DataSnapshot mySnapShot;

    public static ArrayList<TaskModel> updater = new ArrayList<>();
    private FirebaseAuth mAuth;
    public String myID;


//    public static User myAccount;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_task_btn:
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
            case R.id.update_btn:
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btn_signIn = findViewById(R.id.btn_signIn);
        add_to_adapter();

        month = findViewById(R.id.month);
        day = findViewById(R.id.day);
        year = findViewById(R.id.year);

        mAuth = FirebaseAuth.getInstance();
        myID = mAuth.getCurrentUser().getUid().toString().trim();


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


        add_to_adapter();

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

    //Alaskar - repopulates the listview with the added tasks of the user
    public void add_to_adapter(){
        dbRef = FirebaseDatabase.getInstance().getReference().child("Tasks");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mySnapShot = snapshot;
                for(DataSnapshot ds: snapshot.getChildren()){
                    String task = ds.child("name").getValue().toString().trim();
                    String id =  ds.child("userID").getValue().toString().trim();

                    String newId = id;
                    String newMyID = myID;

                    boolean ok = true;

                    for(int i = 0; i < newId.length(); i++){
                        char c1, c2;

                        c1 = newMyID.charAt(i);
                        c2 = newId.charAt(i);

                        if(!(c1 == c2)){
                            ok = false;
                            break;
                        }
                    }

                    if(!tasks.contains(task) && ok){
                        task_adapter.add(task);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        for(int i = 0; i < updater.size(); i++){
//            if(!tasks.contains(updater.get(i).name)){
//                task_adapter.add(updater.get(i).name);
//            }
//        }
    }

    //Alaskar - listens for a long press on a task item and deletes after a long press
    private void setUpListViewListener() {
        list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();

                String task = tasks.get(position);

                tasks.remove(position);
                task_adapter.notifyDataSetChanged();

                for(DataSnapshot ds: mySnapShot.getChildren()){
                    String name = ds.child("name").getValue().toString().trim();
                    String newName = name;

                    if(newName == task){
                        ds.getRef().removeValue();
                        break;
                    }
                }

                return true;
            }
        });
    }

    public void openAdd_task(){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }


}