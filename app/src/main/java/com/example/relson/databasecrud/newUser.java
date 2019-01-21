package com.example.relson.databasecrud;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.relson.databasecrud.performLogin.ApiInterface;
import com.example.relson.databasecrud.performLogin.modeling;
import com.example.relson.databasecrud.performLogin.retroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class newUser extends AppCompatActivity {

    TextView txtName, txtUsername, txtPassword, txtConfirmPassword;
    Button signUp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        txtName = findViewById(R.id.txtName);
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
         login();
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

    private void login() {

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String name = txtName.getText().toString();

        ApiInterface apiInterface = retroInstance.setRetrofit().create(ApiInterface.class);
        Call<modeling> call = apiInterface.setInfo(name, username, password);
        call.enqueue(new Callback<modeling>() {
            @Override
            public void onResponse(Call<modeling> call, Response<modeling> response) {
                if (response.body().getResponse().equals("Registered Successfully")) {
                    Toast.makeText(newUser.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                } else if (response.body().getResponse().equals("Already exist")) {
                    Toast.makeText(newUser.this, "Already exist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(newUser.this, "Error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<modeling> call, Throwable t) {

            }
        });
        }
    }

























