package com.example.sijang.Data;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sijang.R;

import java.util.ArrayList;

public class listActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;
    String marketNum="1";
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<Food> foodList = null;
    FoodDBManager foodDBManager;

    FoodDBHelper foodDBHelper;
    SQLiteDatabase db;
    Cursor cursor=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



        Intent intent=getIntent();
        marketNum=intent.getStringExtra("marketNum");

        listView = findViewById(R.id.listView);
        foodList = new ArrayList();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foodList);
        listView.setAdapter(adapter);
        foodDBManager = new FoodDBManager(this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = foodList.get(position);
                Intent intent = new Intent(listActivity.this, listclickActivity.class);
                intent.putExtra("food", food);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                final int pos = position;
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle(R.string.dialog_title)
//                        .setMessage(R.string.dialog_message)
//                        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (foodDBManager.removeFood(foodList.get(pos).get_id())) {
//                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
//                                    foodList.clear();
//                                    foodList.addAll(foodDBManager.getAllFood());
//                                    adapter.notifyDataSetChanged();
//                                } else {
//                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        })
//                        .setNegativeButton(R.string.dialog_cancel, null)
//                        .setCancelable(false)
//                        .show();
//                return true;
//            }
//        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        foodList.clear();
        foodList.addAll(foodDBManager.getMarketFood(marketNum));
        adapter.notifyDataSetChanged();
    }


//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.button:
//                Intent intent = new Intent(this, AddActivity.class);
//                startActivityForResult(intent, REQ_CODE);
//                break;
//        }
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQ_CODE) {  // AddActivity 호출 후 결과 확인
//            switch(resultCode) {
//                case RESULT_OK:
//                    String food = data.getStringExtra("food");
//                    Toast.makeText(this, food + " 추가 완료", Toast.LENGTH_SHORT).show();
//                    break;
//                case RESULT_CANCELED:
//                    Toast.makeText(this, "음식 추가 취소", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        } else if (requestCode == UPDATE_CODE) {    // UpdateActivity 호출 후 결과 확인
//            switch(resultCode) {
//                case RESULT_OK:
//                    Toast.makeText(this, "음식 수정 완료", Toast.LENGTH_SHORT).show();
//                    break;
//                case RESULT_CANCELED:
//                    Toast.makeText(this, "음식 수정 취소", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
    }

