package com.example.sijang.Point;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.sijang.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PointActivity extends AppCompatActivity {

    String[]name={"중앙젓집"};

    private EditText et_id, et_pass;
    private Button btn_login,btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempmain);

        //서버로 volley를 이용해서 요청을 함
        Intent intent=getIntent();
        String userID=intent.getStringExtra("userID");
        String userPoint=intent.getStringExtra("userPoint");
        String newPoint=intent.getStringExtra("newPoint");
        String marketName=intent.getStringExtra("marketName");

        for(int i=0;i<name.length;i++){
            if(name[i].equals(marketName)) {
                if (userPoint == null) {
                    userPoint = "0";
                    userPoint = String.valueOf(Integer.parseInt(newPoint) * 0.05);
                } else {
                    userPoint = String.valueOf((Integer.parseInt(newPoint) * 0.05) + Integer.parseInt(userPoint));
                }
            }
    }
        Response.Listener<String> responseListener=new Response.Listener<String>() {//volley
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jasonObject=new JSONObject(response);//Register2 php에 response
                    boolean success=jasonObject.getBoolean("success");//Register2 php에 sucess
                    if (success) {
                        Toast.makeText(getApplicationContext(), "포인트 등록 성공", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        };
        PointRequest PointRequest=new PointRequest(userID,userPoint,responseListener);
        RequestQueue queue= Volley.newRequestQueue(PointActivity.this);
        queue.add(PointRequest);

        System.out.println("check this"+newPoint);
    }

}
