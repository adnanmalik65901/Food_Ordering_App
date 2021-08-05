package com.example.food_ordering_app.Models;

public class OrdersModel {
    int orderpic;
    String solditemname,orderno,soldorderprice;

    public OrdersModel(int orderpic, String solditemname, String orderno, String soldorderprice) {
        this.orderpic = orderpic;
        this.solditemname = solditemname;
        this.orderno = orderno;
        this.soldorderprice = soldorderprice;
    }

    public OrdersModel() {

    }

    public int getOrderpic() {
        return orderpic;
    }

    public void setOrderpic(int orderpic) {
        this.orderpic = orderpic;
    }

    public String getSolditemname() {
        return solditemname;
    }

    public void setSolditemname(String solditemname) {
        this.solditemname = solditemname;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getSoldorderprice() {
        return soldorderprice;
    }

    public void setSoldorderprice(String soldorderprice) {
        this.soldorderprice = soldorderprice;
    }
}
