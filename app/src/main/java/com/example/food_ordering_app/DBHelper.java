package com.example.food_ordering_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.food_ordering_app.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="FoodAPP.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          sqLiteDatabase.execSQL(
        "create table orders"+"(id integer primary key autoincrement, "
                +"name text,"
                +"phone text,"
                 +"price int,"
                 +"image int,"
                 +"description text,"
                 +"foodname text,"
                +"quantity int)"
);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);
    }
    public Boolean insertData(String name,String phone,int price,int image,String description,String foodname,int quantity){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("price",price);
        contentValues.put("image",image);
        contentValues.put("description",description);
        contentValues.put("foodname",foodname);
        contentValues.put("quantity",quantity);
        long result =database.insert("orders",null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public ArrayList<OrdersModel> getOrder(){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.rawQuery("Select id,foodname,image,price from orders",null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OrdersModel Model = new OrdersModel();
                Model.setOrderno(cursor.getInt(0)+"");
                Model.setSolditemname(cursor.getString(1));
                Model.setOrderpic(cursor.getInt(2));
                Model.setSoldorderprice(cursor.getInt(3)+"");
                orders.add(Model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }
    public Cursor getOrderById(int id){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.rawQuery("Select * from orders where id="+id,null);
        if(cursor != null){
           cursor.moveToFirst();
        }

        return cursor;
    }

    public Boolean updateOrder(int id,String name,String phone,int price,int image,String description,String foodname,int quantity){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("price",price);
        contentValues.put("image",image);
        contentValues.put("description",description);
        contentValues.put("foodname",foodname);
        contentValues.put("quantity",quantity);
        long res =database.update("orders",contentValues,"id="+id,null);
        if(res==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public int deleteOrder(String id){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        return sqLiteDatabase.delete("orders","id="+id,null);
    }
}
