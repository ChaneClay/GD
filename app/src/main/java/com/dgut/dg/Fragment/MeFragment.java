package com.dgut.dg.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dgut.dg.R;
import com.dgut.dg.entity.PersonalInfo;


public class MeFragment extends Fragment {

    private TextView mTvEmail;
    private TextView mTvName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_me, container, false);

        mTvEmail = view.findViewById(R.id.tv_email);
        mTvName = view.findViewById(R.id.tv_name);

        mTvEmail.setText(PersonalInfo.getEmail());
        mTvName.setText(PersonalInfo.getName());

        return view;

    }
}