package com.example.sijang.Data;
import java.io.Serializable;

public class Food implements Serializable{
    long _id;
    String food;
    String price;
    String market;
    String sector;
    public Food(String sector,String market,String food, String price) {
        this.sector=sector;
        this.market=market;
        this.food = food;
        this.price = price;
    }

    public Food(long _id,String sector,String market,String food, String price) {
        this.sector=sector;
        this.market=market;
        this.food = food;
        this.price = price;
        this._id = _id;

    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getFood() {
        return food;
    }

    public String getPrice() {
        return price;
    }

    public void setFood(String food) { this.food = food; }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector=sector;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id);
        sb.append("\t\t\t");
        sb.append(sector);
        sb.append("");
        sb.append("\t\t\t");
        sb.append(food);
        sb.append("");
        sb.append("\t\t\t");
        sb.append(price);
        sb.append("원");
        return sb.toString();
    }

}
