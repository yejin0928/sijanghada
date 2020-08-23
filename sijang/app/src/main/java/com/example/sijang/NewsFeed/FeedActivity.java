package com.example.sijang.NewsFeed;
import com.example.sijang.R;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {

    private ArrayList<FeedManager> myDataList;
    private FeedAdapter myAdapter;
    private ListView listView;
    private int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_main);

        myDataList = new ArrayList<FeedManager>();
        cnt++;

        //데이터 연결
        ImageView BigImage = (ImageView)findViewById(R.id.imageView);
        TextView textView = (TextView)findViewById(R.id.txtHash);

        Bundle extras = getIntent().getExtras();
        String hash = extras.getString("hash");
        byte[] arr = getIntent().getByteArrayExtra("image");

        myDataList.add(new FeedManager(cnt, hash, arr));

        myAdapter = new FeedAdapter(this, R.layout.feed_adapter_layout, myDataList);


        //추가됨




        //여기까지


        listView = (ListView) findViewById(R.id.feedView);

        listView.setAdapter(myAdapter);
    }
}