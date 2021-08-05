package com.example.food_ordering_app.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_ordering_app.DBHelper;
import com.example.food_ordering_app.Detail_Activity;
import com.example.food_ordering_app.Models.MainModel;
import com.example.food_ordering_app.Models.OrdersModel;
import com.example.food_ordering_app.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewholder>{
    ArrayList<OrdersModel> list;
    Context context;

    public OrderAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final OrdersModel orderModel = list.get(position);
        holder.orderimage.setImageResource(orderModel.getOrderpic());
        holder.solditemname.setText(orderModel.getSolditemname());
        holder.orderno.setText(orderModel.getOrderno());
        holder.price.setText(orderModel.getSoldorderprice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_Activity.class);
                intent.putExtra("id", Integer.parseInt(orderModel.getOrderno()));
                intent.putExtra("type", 2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                DBHelper dbHelper=new DBHelper(context);
                if (dbHelper.deleteOrder(orderModel.getOrderno())>0){
                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView orderimage;
        TextView solditemname,orderno,price;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            orderimage=itemView.findViewById(R.id.orderimageview);
            solditemname=itemView.findViewById(R.id.txtordername);
            orderno=itemView.findViewById(R.id.txtorderno);
            price=itemView.findViewById(R.id.txtorderprice);
        }
    }
}
