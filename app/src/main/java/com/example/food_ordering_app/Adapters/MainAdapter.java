package com.example.food_ordering_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_ordering_app.Detail_Activity;
import com.example.food_ordering_app.Models.MainModel;
import com.example.food_ordering_app.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewholder>{
    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycleview_sample,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
    final MainModel mainModel=list.get(position);
    holder.foodimage.setImageResource(mainModel.getPic());
    holder.foodname.setText(mainModel.getName());
    holder.price.setText(mainModel.getPrice());
    holder.description.setText(mainModel.getDescription());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, Detail_Activity.class);
            intent.putExtra("Image",mainModel.getPic());
            intent.putExtra("Name",mainModel.getName());
            intent.putExtra("Price",mainModel.getPrice());
            intent.putExtra("Description",mainModel.getDescription());
            intent.putExtra("type",1);
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView foodimage;
        TextView foodname,price,description;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            foodimage=itemView.findViewById(R.id.imageView);
            foodname=itemView.findViewById(R.id.txtname);
            price=itemView.findViewById(R.id.txtprice);
            description=itemView.findViewById(R.id.txtdescription);
        }
    }
}
