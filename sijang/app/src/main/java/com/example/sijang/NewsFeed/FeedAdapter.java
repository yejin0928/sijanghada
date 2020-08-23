package com.example.sijang.NewsFeed;
import com.example.sijang.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class FeedAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<FeedManager> myDataList;
    private LayoutInflater layoutInflater;

    public FeedAdapter(Context context, int layout, ArrayList<FeedManager> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;

        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return myDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myDataList.get(position).get_id();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        final int position = pos;
        ViewHolder holder;
        if (convertView == null){
            convertView = layoutInflater.inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.textHash = (TextView)convertView.findViewById(R.id.txtHash);
            holder.imgFeed = (ImageView)convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textHash.setText(myDataList.get(position).getHash());

        byte[] arr = myDataList.get(position).getImage();
        Bitmap bm = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        holder.imgFeed.setImageBitmap(bm);

        return convertView;
    }
    static class ViewHolder {
        TextView textHash;
        ImageView imgFeed;
    }
}
