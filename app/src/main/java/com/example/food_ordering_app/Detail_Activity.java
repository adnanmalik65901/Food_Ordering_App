package com.example.food_ordering_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.food_ordering_app.databinding.ActivityDetailBinding;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Detail_Activity extends AppCompatActivity {

    ActivityDetailBinding binding;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper=new DBHelper(this);
     if(getIntent().getIntExtra("type",0)==1) {

         final int image = getIntent().getIntExtra("Image", 0);
         final String name = getIntent().getStringExtra("Name");
         final int price = Integer.parseInt(getIntent().getStringExtra("Price"));
         final String description = getIntent().getStringExtra("Description");

         binding.detailimageview.setImageResource(image);
         binding.txtdetailfoodname.setText(name);
         binding.txtdetailprice.setText(String.format("%d", price));
         binding.txtdetaildescription.setText(description);
         binding.btnordernow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 boolean isInserted = dbHelper.insertData(
                         binding.txtyourname.getText().toString(),
                         binding.txtphoneno.getText().toString(),
                         price,
                         image,
                         description,
                         name,
                         Integer.parseInt(binding.txtincrement.getText().toString())
                 );
                 if (isInserted) {
                     Toast.makeText(Detail_Activity.this, "Data Successfully Inserted", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(Detail_Activity.this, "Error", Toast.LENGTH_SHORT).show();
                 }
             }
         });
     }
     else{
      int id =getIntent().getIntExtra("id",0);
         Cursor cursor=dbHelper.getOrderById(id);
         final int image=cursor.getInt(4);
         binding.detailimageview.setImageResource(image);
         binding.txtdetailfoodname.setText(cursor.getString(6));
         binding.txtdetailprice.setText(String.format("%d", cursor.getInt(3)));
         binding.txtdetaildescription.setText(cursor.getString(5));
         binding.txtyourname.setText(cursor.getString(1));
         binding.txtphoneno.setText(cursor.getString(2));
         binding.btnordernow.setText("Update Now");
         binding.btnordernow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
          boolean isUpdated =dbHelper.updateOrder(
                  id,
                  binding.txtyourname.getText().toString(),
                  binding.txtphoneno.getText().toString(),
                  Integer.parseInt(binding.txtdetailprice.getText().toString()),
                  image,
                  binding.txtdetaildescription.getText().toString(),
                  binding.txtdetailfoodname.getText().toString(),
                  1
          );
          if(isUpdated){
              Toast.makeText(Detail_Activity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
          }
          else{
              Toast.makeText(Detail_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
          }
             }
         });

     }
    }
}