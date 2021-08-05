package com.example.food_ordering_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.food_ordering_app.Adapters.OrderAdapter;
import com.example.food_ordering_app.Models.OrdersModel;
import com.example.food_ordering_app.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding orderBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderBinding=ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(orderBinding.getRoot());

        DBHelper dbHelper=new DBHelper(this);
        ArrayList<OrdersModel> list=dbHelper.getOrder();


        OrderAdapter orderAdapter=new OrderAdapter(list,this);
        orderBinding.orderrecylerview.setAdapter(orderAdapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        orderBinding.orderrecylerview.setLayoutManager(layoutManager);
    }
}