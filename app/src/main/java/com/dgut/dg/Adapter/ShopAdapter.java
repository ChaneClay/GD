package com.dgut.dg.Adapter;

import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dgut.dg.Fragment.MsgContentFragment;
import com.dgut.dg.Fragment.NewsFragment;
import com.dgut.dg.Fragment.ShopFragment;
import com.dgut.dg.Fragment.ShoppingCartFragment;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends FragmentPagerAdapter {

    private List<String> names;


    public ShopAdapter(@NonNull FragmentManager fm) {
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
                fragment = new ShopFragment();
                break;
            case 1:
                fragment = new ShoppingCartFragment();
                break;

            default:
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