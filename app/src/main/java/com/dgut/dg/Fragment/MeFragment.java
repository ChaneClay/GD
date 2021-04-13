package com.dgut.dg.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgut.dg.Activity.InfoDetailActivity;
import com.dgut.dg.R;
import com.dgut.dg.entity.PersonalInfo;


public class MeFragment extends Fragment {

    private TextView mTvEmail;
    private TextView mTvName;
    private ConstraintLayout mInfoDetail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_me, container, false);

        mTvEmail = view.findViewById(R.id.tv_email);
        mTvName = view.findViewById(R.id.tv_name);
        mInfoDetail = view.findViewById(R.id.infoDetail);

        mTvEmail.setText(PersonalInfo.getEmail());
        mTvName.setText(PersonalInfo.getName());

        mInfoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoDetailActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
}