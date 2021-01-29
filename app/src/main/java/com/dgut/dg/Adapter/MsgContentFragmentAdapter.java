package com.dgut.dg.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dgut.dg.Fragment.MsgContentFragment;

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
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        MsgContentFragment fragment = new MsgContentFragment();
        Bundle bundle = new Bundle();
        System.out.println("---------------"+names.get(position));
        bundle.putString("name", names.get(position));
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
