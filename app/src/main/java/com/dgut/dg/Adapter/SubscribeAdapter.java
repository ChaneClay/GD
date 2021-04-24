package com.dgut.dg.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dgut.dg.R;
import com.dgut.dg.entity.TutorInfo;

import org.w3c.dom.Text;

import java.util.List;

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.PersonViewHolder> {

    private Context mContext;
    List<TutorInfo> mData;



    public SubscribeAdapter(Context context, List<TutorInfo> data){
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_subscribe, null);


        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        holder.mIvHead.setImageResource(mData.get(position).getImage());
        holder.mTvContent.setText(mData.get(position).getIntroduce());

        int flag = mData.get(position).getIsSub();
        if (flag == 0){     // 未预约
            holder.mBtnOrder.setBackgroundResource(R.drawable.btn_default_selector);
            holder.mBtnOrder.setText("预约");
            holder.mTvDate.setText("未预约");
        }else {
            holder.mBtnOrder.setBackgroundResource(R.drawable.btn_login_selector);
            holder.mBtnOrder.setText("预约时间");
            holder.mTvDate.setText(mData.get(position).getDate());
        }



//        holder.mBtnOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "你是否要预约？", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        holder.mIvChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "你是否要发送消息", Toast.LENGTH_SHORT).show();
//            }
//        });




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class PersonViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mIvHead;
        TextView mTvDate;
        TextView mTvContent;
        Button mBtnOrder;
        ImageView mIvChat;

        public PersonViewHolder(View itemView) {
            super(itemView);
            mIvHead = itemView.findViewById(R.id.iv_head);
            mTvDate = itemView.findViewById(R.id.tv_date);
            mTvContent = itemView.findViewById(R.id.tv_content);
            mBtnOrder = itemView.findViewById(R.id.btn_order);
            mIvChat = itemView.findViewById(R.id.iv_chat);


        }


    }

}
