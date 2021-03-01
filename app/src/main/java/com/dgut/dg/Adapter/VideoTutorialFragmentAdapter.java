package com.dgut.dg.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dgut.dg.Fragment.MsgContentFragment;
import com.dgut.dg.Fragment.VideoTutorialFragment;

import java.util.ArrayList;
import java.util.List;

public class VideoTutorialFragmentAdapter extends FragmentPagerAdapter {

    private List<String> names;

    public VideoTutorialFragmentAdapter(@NonNull FragmentManager fm) {
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

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new VideoTutorialFragment();
                System.out.println("--"+0);
                break;
            case 1:
                fragment = new MsgContentFragment();
                System.out.println("--"+1);

                break;
            case 2:
                fragment = new MsgContentFragment();
                break;
        }



        Bundle bundle = new Bundle();
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