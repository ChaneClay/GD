package com.dgut.dg.Adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dgut.dg.R;

import java.util.List;

/**
 * Created by antimage on 2016/1/9.
 */
public class MyAdapter extends BaseAdapter {

    private List<Bitmap> bitmapList;
    private Context mContext;

    public MyAdapter(Context context, List<Bitmap> bitmapList) {
        mContext = context;
        this.bitmapList = bitmapList;
    }

    @Override
    public int getCount() {
        return bitmapList.size();
    }

    @Override
    public Object getItem(int position) {
        return bitmapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        // 此处要用相对布局，且与 XML 中的布局相同；
        // 如果使用线性布局，则显示不完整
        RelativeLayout layout;
        if (convertView == null) {

            layout = (RelativeLayout) View.inflate(mContext, R.layout.image_item_layout, null);

            viewHolder = new ViewHolder();

            viewHolder.image = (ImageView) layout.findViewById(R.id.top_image);
            layout.setTag(viewHolder);
        } else {
            layout = (RelativeLayout) convertView;
            viewHolder = (ViewHolder) layout.getTag();
            Log.e("MyAdapter", "正在检测数据来了没有 ");
        }

        Bitmap btm = (Bitmap) getItem(position);
        viewHolder.image.setImageBitmap(btm);

        Log.e("MyAdapter", "信息来了哦！");

        return layout;
    }

    private static class ViewHolder {
        ImageView image;
    }

}