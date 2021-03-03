package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    // Sergio: this introduces new 'java' button variables and java textbox (editable) variables,
    // this is where those buttons and editable boxes from the registration page will map
    Button btn_signIn, btn_register;
    EditText textbox_userName, textbox_passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        // Connecting the XML buttons to the JAVA buttons
        btn_register = findViewById(R.id.btn_register);
        btn_signIn = findViewById(R.id.btn_signIn);

        // Connecting the XML textboxes to the JAVA textboxes
        textbox_userName = findViewById(R.id.textbox_userName);
        textbox_passWord = findViewById(R.id.textbox_passWord);

        // Whenever the buttons are pressed ('onClick') the functions within these listeners will be activated
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel;
                try {
                    Toast.makeText(LoginActivity.this, "Register button has been pressed!", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, textbox_userName.getText().toString(), textbox_passWord.getText().toString());
                }
                catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Registration Error!", Toast.LENGTH_SHORT).show();
                    //userModel = new UserModel(-1, "error", "error");
                    return;
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
                boolean success = databaseHelper.registerUser(userModel);
                if (success){
                Toast.makeText(LoginActivity.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel;
                try {
                    Toast.makeText(LoginActivity.this, "Sign In button has been pressed!", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, textbox_userName.getText().toString(), textbox_passWord.getText().toString());
                }
                catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Sign in error!", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, "error", "error");
                    return;
                }



                DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
                try {
                    boolean accountExists = databaseHelper.signInUser(userModel);
                    if (accountExists){Toast.makeText(LoginActivity.this, "Successfully found account!", Toast.LENGTH_SHORT).show();}
                }
                catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(LoginActivity.this, retrieved.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}