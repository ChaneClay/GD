package com.dgut.dg.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dgut.dg.Adapter.MsgContentFragmentAdapter;
import com.dgut.dg.R;
import com.dgut.dg.Utils.PersonalMes;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;


public class MeFragment extends Fragment {

    private TextView mTvEmail;
    private TextView mTvName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_me, container, false);

        mTvEmail = view.findViewById(R.id.tv_email);
        mTvName = view.findViewById(R.id.tv_name);


        mTvEmail.setText(PersonalMes.getEmail());
        mTvName.setText(PersonalMes.getName());

        return view;

    }
}