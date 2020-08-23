package com.example.sijang;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sijang.Data.listActivity;
import com.example.sijang.NewsFeed.FeedMainActivity;
import com.example.sijang.Point.PointVisionActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class thirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.btn_feed:
                new Thread(new Runnable() {


                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //your code or your request that you want to run on uiThread
                                Intent intent;
                                intent = new Intent(thirdActivity.this, FeedMainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }).start();

                break;
            case R.id.btn_point:
                intent = new Intent(thirdActivity.this,PointVisionActivity.class);
                startActivity(intent);
                break;
          /*  case R.id.btn_list:
                intent = new Intent(thirdActivity.this, listActivity.class);
                startActivity(intent);
                break;*/
            case R.id.m1:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","1");
                startActivity(intent);
                break;
            case R.id.m2:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","2");
                startActivity(intent);
                break;
            case R.id.m3:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","3");
                startActivity(intent);
                break;
            case R.id.m4:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","4");
                startActivity(intent);
                break;
            case R.id.m5:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","5");
                startActivity(intent);
                break;
            case R.id.m6:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","6");
                startActivity(intent);
                break;
            case R.id.m7:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","7");
                startActivity(intent);
                break;
            case R.id.m8:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","8");
                startActivity(intent);
                break;
            case R.id.m9:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","9");
                startActivity(intent);
                break;
            case R.id.m10:
                intent =new Intent(thirdActivity.this,listActivity.class);
                intent.putExtra("marketNum","10");
                startActivity(intent);
                break;


        }
    }

}

