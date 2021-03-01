package com.dgut.dg.Adapter;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dgut.dg.Fragment.MsgContentFragment;
import com.dgut.dg.Fragment.VideoTutorialFragment;

import java.util.ArrayList;
import java.util.List;

public class MsgContentFragmentAdapter extends FragmentPagerAdapter {
    private List<String> names;

    public MsgContentFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.names = new ArrayList<>();
    }


    public void setList(List<String> datas){
        this.names.clear();
        this.names.addAll(datas);

        // 有时候我们需要修改已经生成的列表，添加或者修改数据，notifyDataSetChanged()
        // 可以在修改适配器绑定的数组后，不用重新刷新Activity，通知Activity更新ListView。
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {


        MsgContentFragment fragment = new MsgContentFragment();
        Bundle bundle = new Bundle();
//        内容
        bundle.putString("name", names.get(position));
//        bundle.putString("name", position+"");


        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return names.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String plateName = names.get(position);
        if (plateName == null){
            plateName = "";
        }else if (plateName.length() > 15){
            plateName = plateName.substring(0, 15) + "...";
        }
        return plateName;
    }
}
