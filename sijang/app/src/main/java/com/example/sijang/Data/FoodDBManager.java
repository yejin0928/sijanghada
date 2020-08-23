package com.example.sijang.Data;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FoodDBManager {

    FoodDBHelper foodDBHelper = null;
    Cursor cursor = null;

    public FoodDBManager(Context context) {
        foodDBHelper = new FoodDBHelper(context);
    }

//    DB의 모든 food를 반환
    public ArrayList<Food> getAllFood() {
        ArrayList foodList = new ArrayList();
        SQLiteDatabase db = foodDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FoodDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(FoodDBHelper.COL_ID));
            String sector=cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_SECTOR));
            String market=cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_MARKET));
            String food = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_FOOD));
            String price = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_PRICE));
            foodList.add ( new Food (id,sector, market,food, price) );
        }

        cursor.close();
        foodDBHelper.close();
        return foodList;
    }

    public ArrayList<Food> getMarketFood(String marketNum) {
        ArrayList foodList = new ArrayList();
        SQLiteDatabase db = foodDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FoodDBHelper.TABLE_NAME+ " WHERE market= "+marketNum+";", null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(FoodDBHelper.COL_ID));
            String sector=cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_SECTOR));
            String market=cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_MARKET));
            String food = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_FOOD));
            String price = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_PRICE));
            foodList.add ( new Food (id,sector,market,food,price) );
        }

        cursor.close();
        foodDBHelper.close();
        return foodList;
    }



}
