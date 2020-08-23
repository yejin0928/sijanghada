package com.example.sijang.Data;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sijang.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class listclickActivity extends AppCompatActivity {

    Food food;

    EditText etPrice;
    EditText etMartPrice;
    EditText etFoodName;
    FoodDBManager foodDBManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listclick);

        foodDBManager = new FoodDBManager(this);

        food = (Food) getIntent().getSerializableExtra("food");
        etMartPrice = findViewById(R.id.et_martprice);
        etPrice = findViewById(R.id.et_price);
        etFoodName=findViewById(R.id.et_foodName);

        etPrice.setText(food.getPrice());
        etFoodName.setText(food.getFood());

        marketPrice(food);
        //sijangPrice(sijang,food);
    }

    public void marketPrice(Food food){


        int idx;
        int sum = 0;
        int count = 1;
        String text = "nothing";
        List<List<String>> ret = new ArrayList<>();
        BufferedReader br = null;


        try {
            BufferedReader br2 = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.list)));
            //Charset.forName(CharEncoding.UTF_8);
            idx = -1;
            while (true) {
                String readLine = br2.readLine();
                String line = readLine;
                if (readLine == null) {
                    break;
                }
                idx++;
                new ArrayList();
                String[] array = line.split(",");
                List asList = Arrays.asList(array);
                if (idx != 0) {
                    String str = "\"";

                    int sector;
                    int ms;
                    int name;
                    int price;

                    if(array.length==14)
                    {
                        sector=12;
                        ms=9;
                        name=4;
                        price=6;
                    }else if(array.length==15){
                        sector=13;
                        ms=10;
                        name=4;
                        price=7;
                    }else{
                      sector=14;
                        ms=12;
                        name=4;
                        price=7;
                    }

                    //if(food.getSector().contains(array[sector].split(str)[1]))
                    if(array[sector].split(str)[1].equals("중구"))
                    {
                        System.out.println("구 똑같음");
                        System.out.println(array[ms]);
                        if(array[ms].contains("2")||array[ms].equals("대형마트"))
                        {
                            System.out.println("대형마트임");
                            if(array[name].contains(food.getFood())) {
                                System.out.println(food.getFood()+"임");
                                sum += Integer.parseInt(array[price].split(str)[1]);
                                count++;
                                System.out.println(sum+"!!"+count);
                            }
                        }
                    }
                    text=Integer.toString(sum/(count));
                    etMartPrice.setText(text);
                    ret.add(asList);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//
//    public void sijangPrice(String[] sijang,Food food){
//
//        etMart = findViewById(R.id.et_foodName);
//        etSijang = findViewById(R.id.et_price);
//
//        int idx;
//        int sum = 0;
//        int count = 1;
//        String text = "nothing";
//        List<List<String>> ret = new ArrayList<>();
//        BufferedReader br = null;
//
//
//        try {
//            BufferedReader br2 = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.list)));
//            //Charset.forName(CharEncoding.UTF_8);
//            idx = -1;
//            while (true) {
//                String readLine = br2.readLine();
//                String line = readLine;
//                if (readLine == null) {
//                    break;
//                }
//                idx++;
//                new ArrayList();
//                String[] array = line.split(",");
//                List asList = Arrays.asList(array);
//                if (idx != 0) {
//                    String str = "\"";
//                    int sector;
//                    int ms;
//                    int name;
//                    int price;
//
//                    if(array.length==14)
//                    {
//                        sector=12;
//                        ms=9;
//                        name=4;
//                        price=6;
//                    }else if(array.length==15){
//                        sector=13;
//                        ms=10;
//                        name=4;
//                        price=7;
//                    }else{
//                        sector=14;
//                        ms=12;
//                        name=4;
//                        price=7;
//                    }
//
//                        if(array[ms].contains("1")||array[ms].equals("전통시장"))
//                        {
//                            System.out.println("clear 2");
//
//                            if(array[name].contains(sijang[4])) {
//                                sum += Integer.parseInt(array[price].split(str)[1]);
//                                count++;
//                                System.out.println(sum+"!!"+count);
//                            }
//                        }
//                    }
//                    text=Integer.toString(sum/(count));
//                    etSijang.setText(text);
//
//                    ret.add(asList);
//                }
//            } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (br != null) {
//                    br.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
