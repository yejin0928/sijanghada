package com.example.sijang.Login;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    //로그인 정보 db와 연동

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://flvm1030.dothome.co.kr/tempLogin2.php";    //로그인 위한 php파일
    private Map<String, String> map;

    public LoginRequest(String userID, String userPassword, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPassword);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}