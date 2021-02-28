package com.dgut.dg.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dgut.dg.Fragment.ContactFragment;
import com.dgut.dg.Fragment.FindFragment;
import com.dgut.dg.Fragment.MeFragment;
import com.dgut.dg.Fragment.MsgFragment;

public class MainFragmentAdapter extends FragmentPagerAdapter {
    public MainFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    // 根据不同的参数，返回不同的fragment

    // position哪里传过来的？

    @Override
    public Fragment getItem(int position) {

        System.out.println("**********"+position);

        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new MsgFragment();
                break;
            case 1:
                fragment = new ContactFragment();
                break;
            case 2:
                fragment = new FindFragment();
                break;
            case 3:
                fragment = new MeFragment();
                break;
            default:
                break;

        }

        return fragment;

    }

    @Override
    public int getCount() {
        return 4;
    }
}
