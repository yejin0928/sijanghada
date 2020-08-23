package com.example.sijang.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDBHelper  extends SQLiteOpenHelper {


    final static String TAG ="data_market";
    final static String DB_NAME="foods.db";
    public final static String TABLE_NAME="food_table";
    public final static String COL_ID="_id";
    public final static String COL_MARKET="market";
    public final static String COL_FOOD="food";
    public final static String COL_PRICE="price";
    public final static String COL_SECTOR="sector";
    public FoodDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db)  {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " + COL_SECTOR +" TEXT, "+COL_MARKET +" TEXT, "+
                COL_FOOD + " TEXT, " + COL_PRICE + " TEXT)";
        db.execSQL(sql);

        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','1', '오징어', '4000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','1', '고등어', '5000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','1', '조기', '3500');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','1', '명태', '3500');");

        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','2', '사과', '2500');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','2', '배', '4000');");


        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','3', '몸빼바지', '5000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','3', '양말', '1500');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','3', '런닝', '5000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','3', '모자', '6000');");

        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','4', '쇠고기600g', '24000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','4', '돼지고기/삼겹살', '16000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','4', '닭고기', '5500');");


        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','5', '배추(중간)', '7000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','5', '무(중간)', '2500');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','5', '양파1.5kg', '3000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','5', '상추100g', '1250');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','5', '오이', '500');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','5', '애호박', '1000');");

        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','6', '쇠고기600g', '24000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','6', '돼지고기/삼겹살', '16000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','6', '닭고기', '5500');");

        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','7', '계란(10개)', '2000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','7', '계란(20개)', '5000');");

        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','8', '계란(10개)', '2000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','8', '계란(20개)', '5000');");

        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','9', '쇠고기600g', '24000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','9', '돼지고기/삼겹살', '16000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','9', '닭고기', '5500');");



        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','10', '쌀떡볶이', '2500');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','10', '모듬튀김', '2500');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','10', '길쭉이감자', '2500');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','10', '팝콕', '2000');");
        db.execSQL("insert into "+ TABLE_NAME+ " VALUES (null,'종로구','10', '떡꼬치', '500');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)  {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

}