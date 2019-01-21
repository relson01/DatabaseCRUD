package com.example.relson.databasecrud;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class newUser extends AppCompatActivity {

    TextView txtUsername, txtPassword, txtConfirmPassword;
    Button signUp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        signUp = findViewById(R.id.btnSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();

            }
        });

    }

    private void performRegistration() {

        if(!checkUsername()){
            return;
        }
         if(!checkPassword())
         {
             return;
         }
    }

    private boolean checkUsername() {
        String username = txtUsername.getText().toString();
        if(username.equals(""))
        {
            txtUsername.setError("cannot be empty");
            return false;

        }
        return true;
    }

    private boolean checkPassword() {
        String password = txtPassword.getText().toString();
        String confirmPassword = txtConfirmPassword.getText().toString();

        if(password.equals("")) {
            txtPassword.setError("Cannot be empty");
            return false;
        }
        else if(confirmPassword.equals("")) {
            txtConfirmPassword.setError("Cannot be empty");
            return  false;
        }
        else if(!password.equals(confirmPassword)) {
            txtConfirmPassword.setError("Password doesnot match");
            return false;
        }
        return true;
    }

}























