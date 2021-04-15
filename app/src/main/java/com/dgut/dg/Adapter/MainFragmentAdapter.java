package com.dgut.dg.Adapter;

import androidx.appcompat.app.ActionBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dgut.dg.Fragment.ContactFragment;
import com.dgut.dg.Fragment.FindFragment;
import com.dgut.dg.Fragment.MeFragment;
import com.dgut.dg.Fragment.MsgFragment;
import com.dgut.dg.Fragment.PlanFragment;


public class MainFragmentAdapter extends FragmentPagerAdapter {

    private int mCount;


    public MainFragmentAdapter(@NonNull FragmentManager fm, int count) {
        super(fm);
        this.mCount = count;
    }




    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new MsgFragment();
                break;
            case 1:
                fragment = new ContactFragment();
                break;
            case 2:
                fragment = new PlanFragment();
                break;
            case 3:
                fragment = new FindFragment();
                break;
            case 4:
                fragment = new MeFragment();
                break;
            default:
                break;

        }

        return fragment;

    }

    @Override
    public int getCount() {
        return mCount;
    }
}
