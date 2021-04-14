package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cmBtn, rmBtn;
    private EditText vName, vDate, vDiff, vDesc;
    private DataSnapshot mySnapshot;
    private DatabaseReference dbRef;

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.completeBtn:
                for(DataSnapshot ds: mySnapshot.getChildren()){
                    String key = ds.getKey().toString().trim();
                    String newKey = key;

                    if(newKey == MainActivity.taskID){
                        ds.getRef().child("completed").setValue(true);
                        break;
                    }
                }
                break;
            case R.id.viewRemoveBtn:
                //
                for(DataSnapshot ds: mySnapshot.getChildren()){
                    String key = ds.getKey().toString().trim();
                    String newKey = key;

                    Log.d("Debug Equal", String.valueOf(newKey == MainActivity.taskID));

                    if(newKey == MainActivity.taskID){
                        ds.getRef().removeValue();
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        cmBtn = findViewById(R.id.completeBtn);
        rmBtn = findViewById(R.id.viewRemoveBtn);
        vName = findViewById(R.id.viewName);
        vDate = findViewById(R.id.viewDate);
        vDiff = findViewById(R.id.viewDiff);
        vDesc = findViewById(R.id.viewDesc);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Tasks");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mySnapshot = snapshot;
                for(DataSnapshot ds: snapshot.getChildren()){
                    String tID = ds.getKey().toString().trim();
                    String newTID = tID;

                    if( newTID == MainActivity.taskID){
                        vName.setText("Task Name: " + ds.child("name").getValue().toString().trim());
                        vDate.setText("Date: " + ds.child("date").getValue().toString().trim());
                        vDiff.setText("Difficulty: " + ds.child("difficulty").getValue().toString().trim());
                        vDesc.setText("Description: " + ds.child("description").getValue().toString().trim());

                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}