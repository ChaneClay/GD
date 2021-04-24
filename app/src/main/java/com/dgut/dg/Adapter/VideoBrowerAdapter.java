package com.dgut.dg.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
//import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dgut.dg.R;
import com.dgut.dg.Utils.CircleImageView;
import com.dgut.dg.entity.VideoInfo;

import java.util.LinkedList;

import cn.jzvd.JzvdStd;

public class VideoBrowerAdapter extends BaseAdapter {

    private LinkedList<VideoInfo> aData;
    private Context mContext;
    private boolean isGood = false;


    public VideoBrowerAdapter(LinkedList<VideoInfo> aData, Context mContext){
        this.aData = aData;
        this.mContext = mContext;
    }
    @Override
    public int getCount(){
        return aData.size();
    }
    @Override
    public Object getItem(int position){
        return null;
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder holder;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.video_brower_item,parent,false);
            holder = new ViewHolder();
            holder.videoAvatar = convertView.findViewById(R.id.video_avatar);
            holder.username = convertView.findViewById(R.id.video_username);
            holder.videoDate = convertView.findViewById(R.id.video_date);
            holder.videoDescripation = convertView.findViewById(R.id.video_descripation);
            holder.videoPosition = convertView.findViewById(R.id.video_position);
            holder.video_iv_good = convertView.findViewById(R.id.video_iv_good);
            holder.video_iv_comment = convertView.findViewById(R.id.video_iv_comment);
//            holder.video_iv_share = convertView.findViewById(R.id.video_iv_share);
            holder.video_et_comment = convertView.findViewById(R.id.video_et_comment);
            holder.video_et_comment.setVisibility(View.GONE);


            holder.mContainer = convertView.findViewById(R.id.mContainer); //拿到布局，用于动态添加View

            holder.jzvdStd = convertView.findViewById(R.id.cus_jz_video);
            holder.tvLike = convertView.findViewById(R.id.tv_like);
            holder.tvComment = convertView.findViewById(R.id.tv_comment);


            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.videoAvatar.setImageResource(aData.get(position).getAvatarId());
        holder.username.setText(aData.get(position).getUsername());
        holder.videoDate.setText(aData.get(position).getDate());
        holder.videoDescripation.setText(aData.get(position).getVideoDescripation());
        holder.videoPosition.setText(aData.get(position).getPosition());

        String thumbUrl = null;
        if (position %2 == 0){
            thumbUrl = "https://p26-tt.byteimg.com/img/pgc-image/e2e926254ab14fa287ace53faface364~tplv-obj.image";
            holder.tvLike.setText("456");
            holder.tvComment.setText("36");

        }else {
            thumbUrl = "https://upyun.wefitos.com/post/202101/161102348746788.jpg";
            holder.tvLike.setText("109");
            holder.tvComment.setText("15");
        }
        holder.jzvdStd.setUp(aData.get(position).getVideoPath(), "");
        Glide.with(holder.jzvdStd.getContext()).load(thumbUrl).into(holder.jzvdStd.thumbImageView);


//        String playUrl = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=239018&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss&udid=";
//        String title = "可口可乐励志广告：年龄不是我的弱点";
//        String thumbUrl = "http://img.kaiyanapp.com/6b0ebb43a12dbaa8972ebc45997d0737.png?imageMogr2/quality/60/format/jpg";
//        thumbUrl = "https://p1-tt-ipv6.byteimg.com/origin/pgc-image/bdd232dbff7b475ca2ca6916bf70c890";
        thumbUrl = "https://p26-tt.byteimg.com/img/pgc-image/e2e926254ab14fa287ace53faface364~tplv-obj.image";
        thumbUrl = "https://upyun.wefitos.com/post/202101/161102348746788.jpg";

//        holder.jzvdStd.setUp(aData.get(position).getVideoPath(), "");
//        Glide.with(holder.jzvdStd.getContext()).load(thumbUrl).into(holder.jzvdStd.thumbImageView);


        //点赞
        holder.video_iv_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGood){
                    holder.video_iv_good.setImageResource(R.mipmap.heart_like);
                    Toast.makeText(mContext, "点赞成功！", Toast.LENGTH_SHORT).show();
                    isGood = true;
                }else {
                    holder.video_iv_good.setImageResource(R.mipmap.heart_default);
                    isGood = false;
                }
            }
        });

        //评论图片按钮
        holder.video_iv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //焦点移动到评论区
                holder.video_et_comment.requestFocus();
            }
        });

        //评论框右边发表图标
        holder.video_et_comment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = holder.video_et_comment.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > holder.video_et_comment.getWidth()
                        - holder.video_et_comment.getPaddingRight()
                        - drawable.getIntrinsicWidth()){
                    //发表
                    String commentStr = holder.video_et_comment.getText().toString().trim();
                    if (TextUtils.isEmpty(commentStr)){
                        Toast.makeText(mContext,"评论内容不能为空！",Toast.LENGTH_SHORT).show();
                    }else {
                        addView(holder, commentStr);
                        holder.video_et_comment.setText(""); //发表完评论后编辑框清空
                        Toast.makeText(mContext,"发表成功!",Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        });


        return convertView;
    }
    static class ViewHolder{
        CircleImageView videoAvatar;
        TextView username;
        TextView videoDate;
        TextView videoDescripation;
        TextView videoPosition;
        ImageView video_iv_good;
        ImageView video_iv_comment;
//        ImageView video_iv_share;
        EditText video_et_comment;
        LinearLayout mContainer;


        JzvdStd jzvdStd;
        TextView tvLike;
        TextView tvComment;

    }

    private void addView(ViewHolder holder, String commentStr){
        TextView view = new TextView(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60);
        lp.setMargins(0,15,0,15);
        view.setTextSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setTextColor(mContext.getColor(R.color.record_comment_text));
        }
        view.setText("xiaok:"+commentStr);
        int index = holder.mContainer.getChildCount();
        holder.mContainer.addView(view,index-1,lp);
    }

//    private void addThumpUpView(ViewHolder holder){
//        thumpUpView = new TextView(mContext);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60);
//        lp.setMargins(0,15,0,15);
//        thumpUpView.setTextSize(14);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            thumpUpView.setTextColor(mContext.getColor(R.color.record_comment_text));
//        }
//        thumpUpView.setText("xiaok 觉得很赞");
//        holder.mContainer.addView(thumpUpView,0,lp);
//    }

//    private void removeThumpUpView(ViewHolder holder){
//        holder.mContainer.removeViewAt(3);
//    }


}
