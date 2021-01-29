package com.dgut.dg.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgut.dg.Adapter.MsgContentFragmentAdapter;
import com.dgut.dg.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;


public class MeFragment extends Fragment {


    public MeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }
}