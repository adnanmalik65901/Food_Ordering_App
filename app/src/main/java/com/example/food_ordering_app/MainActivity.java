package com.example.food_ordering_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.food_ordering_app.Adapters.MainAdapter;
import com.example.food_ordering_app.Models.MainModel;
import com.example.food_ordering_app.databinding.ActivityMainBinding;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList <MainModel> list= new ArrayList<>();
        list.add(new MainModel(R.drawable.food1,"Burger","5","Burger"));
        list.add(new MainModel(R.drawable.food2,"Pizza","10","Pizza with Cold Drink"));
        list.add(new MainModel(R.drawable.food3,"Burger","6","Burger with Finger Chips"));
        list.add(new MainModel(R.drawable.food4,"Burger","15","Burger with wings & Chips"));
        list.add(new MainModel(R.drawable.food5,"Burger","5","Burger with two wings"));
        list.add(new MainModel(R.drawable.food6,"Burger","5","Burger with two wings"));
        list.add(new MainModel(R.drawable.food7,"Burger","5","Burger with two wings"));
        list.add(new MainModel(R.drawable.food8,"Burger","5","Burger with two wings"));
        list.add(new MainModel(R.drawable.food9,"Burger","5","Burger with two wings"));
        list.add(new MainModel(R.drawable.food10,"Burger","5","Burger with two wings"));

        MainAdapter adapter=new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                Intent intent=new Intent(MainActivity.this,OrderActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}