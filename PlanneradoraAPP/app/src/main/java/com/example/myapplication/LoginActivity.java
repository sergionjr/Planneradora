package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    // Sergio: This introduces the buttons/textboxes as variables in java
    Button btn_signIn, btn_register;
    EditText textbox_userName, textbox_passWord;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        // Sergio: This introduces the Firebase database
        mAuth = FirebaseAuth.getInstance();

        // Sergio: This attaches the java buttons to the XML buttons so they are connected
        btn_register = findViewById(R.id.btn_register);
        btn_signIn = findViewById(R.id.btn_signIn);
        // Sergio: This attaches the java textboxes to the XML textboxes
        textbox_userName = findViewById(R.id.textbox_email);
        textbox_passWord = findViewById(R.id.textbox_passWord);
        // Sergio: This attaches the progress bar to the XML progressbar





        // Sergio: This allows the code in the onClick() function to activate if the register button is pressed
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(); // - Register user function
            }
        });
        // Maps clicking function to the button
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                signInUser();

            }
        });

    }

    // Sergio: Signs in the user with the userName & passWord fields.
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void signInUser() {

        String userName = textbox_userName.getText().toString().trim();
        String passWord = textbox_passWord.getText().toString().trim();

        // If username field is empty, makes user try again
        if(userName.isEmpty()){
            Toast.makeText(this, "Email is required!", Toast.LENGTH_SHORT).show();
            textbox_userName.requestFocus();
            return;
        }

        // If password is empty, makes them try again
        if(passWord.isEmpty()){
            Toast.makeText(this, "Password is required!", Toast.LENGTH_SHORT).show();
            textbox_passWord.requestFocus();
            return;
        }



        // FireDB function to sign in with email and password
        mAuth.signInWithEmailAndPassword(userName, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Failed to sign in. Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Sergio: Function to register the user with an email and password.
    private void registerUser() {
        String userName = textbox_userName.getText().toString();
        String passWord = textbox_passWord.getText().toString();

        if (userName.isEmpty()){
            Toast.makeText(this, "Username is required!", Toast.LENGTH_SHORT).show();
            textbox_userName.requestFocus();
            return;
        }

        if (passWord.isEmpty()){
            Toast.makeText(this, "Password is required!", Toast.LENGTH_SHORT).show();
            textbox_passWord.requestFocus();
            return;
        }

        if (passWord.length() < 6){
            Toast.makeText(this, "Password must be longer than 6 characters!", Toast.LENGTH_SHORT).show();
            textbox_passWord.requestFocus();
        }

        mAuth.createUserWithEmailAndPassword(userName, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    UserModel user = new UserModel(userName, passWord);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "User has been registered!", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Failed to register! Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else{
                    Toast.makeText(LoginActivity.this, "User already exists or invalid email was provided! Try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}


// Sergio: Ignore all of this for now, I'm going to keep it here in case Firebase turns out to be annoying.

//public class LoginActivity extends AppCompatActivity {
//    // Sergio: this introduces new 'java' button variables and java textbox (editable) variables,
//    // this is where those buttons and editable boxes from the registration page will map
//    Button btn_signIn, btn_register;
//    EditText textbox_userName, textbox_passWord;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.registration_page);
////
////        // Connecting the XML buttons to the JAVA buttons
////        btn_register = findViewById(R.id.btn_register);
////        btn_signIn = findViewById(R.id.btn_signIn);
////
////        // Connecting the XML textboxes to the JAVA textboxes
////        textbox_userName = findViewById(R.id.textbox_userName);
////        textbox_passWord = findViewById(R.id.textbox_passWord);
////
////        // Whenever the buttons are pressed ('onClick') the functions within these listeners will be activated
////        btn_register.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                UserModel userModel;
////                try {
////                    Toast.makeText(LoginActivity.this, "Register button has been pressed!", Toast.LENGTH_SHORT).show();
////                    userModel = new UserModel(-1, textbox_userName.getText().toString(), textbox_passWord.getText().toString());
////                }
////                catch (Exception e){
////                    Toast.makeText(LoginActivity.this, "Registration Error!", Toast.LENGTH_SHORT).show();
////                    //userModel = new UserModel(-1, "error", "error");
////                    return;
////                }
////
////                DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
////                boolean success = databaseHelper.registerUser(userModel);
////                if (success){
////                Toast.makeText(LoginActivity.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
//        btn_signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                UserModel userModel;
//                try {
//                    Toast.makeText(LoginActivity.this, "Sign In button has been pressed!", Toast.LENGTH_SHORT).show();
//                    userModel = new UserModel(-1, textbox_userName.getText().toString(), textbox_passWord.getText().toString());
//                }
//                catch (Exception e){
//                    Toast.makeText(LoginActivity.this, "Sign in error!", Toast.LENGTH_SHORT).show();
//                    userModel = new UserModel(-1, "error", "error");
//                    return;
//                }
//
//
//
//                DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
//                try {
//                    boolean accountExists = databaseHelper.signInUser(userModel);
//                    if (accountExists){Toast.makeText(LoginActivity.this, "Successfully found account!", Toast.LENGTH_SHORT).show();}
//                }
//                catch (Exception e){
//                    Toast.makeText(LoginActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
//                }
//
//                //Toast.makeText(LoginActivity.this, retrieved.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}