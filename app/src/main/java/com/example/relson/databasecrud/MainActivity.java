package com.example.relson.databasecrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.relson.databasecrud.performLogin.ApiInterface;
import com.example.relson.databasecrud.performLogin.modeling;
import com.example.relson.databasecrud.performLogin.retroInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText UserName, Password, Name;
    private TextView tv;

    private ArrayList<String> txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.loginBtn);
        UserName = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById((R.id.password));
        tv = (TextView) findViewById(R.id.createAc_Text);

        txtTitle = new ArrayList();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View  v) {
                attemptLogin();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), newUser.class);
                startActivity(intent);
            }
        });



    }

    private void attemptLogin()
    {
        String username = UserName.getText().toString();
        String password = Password.getText().toString();

        if(username.equals(""))
        {
            UserName.setError("Field blank");
            return;
        }
        if(password.equals(""))
        {
            Password.setError("Field blank");
        }
        performLogin();
    }

    private void performLogin()
    {
        String username = UserName.getText().toString();
        String password = Password.getText().toString();



        ApiInterface apiInterface = retroInstance.setRetrofit().create(ApiInterface.class);

        Call<modeling> call = apiInterface.getInfo(username, password);
        call.enqueue(new Callback<modeling>() {
            @Override
            public void onResponse(Call<modeling> call, Response<modeling> response) {


                if(response.body().getResponse().equals("OK")){
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Log.e("main", "modeling " + response.body().getId());
                    Intent intent = new Intent(getBaseContext(), showMenu.class);
                    startActivity(intent);
                }
                else if(response.body().getResponse().equals("failed")){
                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Error occured" , Toast.LENGTH_SHORT).show();
                    Log.e("main", "else: " + response.message());
                }

                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<modeling> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                Log.e("main", "error " + t.getLocalizedMessage());
            }

        });

    }
}
