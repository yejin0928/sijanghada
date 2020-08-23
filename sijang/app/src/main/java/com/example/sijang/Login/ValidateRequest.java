package com.example.sijang.Login;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {

    //회원가입하는 id 중복체크

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://flvm1030.dothome.co.kr/tempUserValidate.php";      //아이디 중복체크 위한 php파일
    private Map<String, String> map;

    public ValidateRequest(String userID, Response.Listener<String>listener){
        super(Request.Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("userID",userID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}