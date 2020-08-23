package com.example.sijang.Login;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //회원가입 정보 php서버에 보내 db에 저장

    //서버 url 설정(php파일 연동)
    final static  private String URL="http://flvm1030.dothome.co.kr/tempRegister2.php";      //회원가입 위한 php파일
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPassword, String userName, String userPoint, Response.Listener<String>listener){
        super(Method.POST,URL,listener,null);//위 url에 post방식으로 값을 전송

        map=new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPassword);
        map.put("userName",userName);
        map.put("userPoint",userPoint);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}