package com.example.sijang.Point;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PointRequest extends StringRequest {

    //로그인 정보 db와 연동

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://flvm1030.dothome.co.kr/tempPoint.php";    //로그인 위한 php파일
    private Map<String, String> map;

    public PointRequest(String userID, String userPoint, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("userID",userID);
        map.put("userPoint",userPoint);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}