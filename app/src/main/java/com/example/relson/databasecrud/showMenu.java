package com.example.relson.databasecrud;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.relson.databasecrud.performLogin.ApiInterface;
import  com.example.relson.databasecrud.performLogin.*;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class showMenu extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_menu);
        Log.d("tag", "onCreate: ");
        final RecyclerView menu = findViewById(R.id.showItem);


        final ArrayList<String> list = new ArrayList() ;
        final ArrayList<String> price = new ArrayList();



        Log.d("menu", "onCreate: retrofit");

        ApiInterface apiInterface = retroInstance.setRetrofit().create(ApiInterface.class);
        Call<List<menu>> list1 = apiInterface.getItem();
        list1.enqueue(new Callback<List<com.example.relson.databasecrud.performLogin.menu>>() {
            @Override
            public void onResponse(Call<List<menu>> call, Response<List<menu>> response) {
                List<menu> m = response.body();

                for(menu m1: m)
                {
                    list.add(m1.getName());
                    Log.d("tag", "onResponse: " + m1.getName());
                    price.add(m1.getPrice());
                }
                menu.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                menu.setAdapter(new ProgrammingAdapter(list, price));

            }

            @Override
            public void onFailure(Call<List<menu>> call, Throwable t) {
                Log.d("tag", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }
}











