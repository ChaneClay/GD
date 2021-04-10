package com.dgut.dg.Fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgut.dg.Adapter.ShopDetailRecyclerViewAdapter;
import com.dgut.dg.R;
import com.dgut.dg.entity.CommonInfo;



public class ShopFragment extends Fragment {

    ShopDetailRecyclerViewAdapter adapter;
    RecyclerView recyclerView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 不要放在adapter初始化
        initData();

    }


    private void initData() {

        int[] images = new int[]{R.mipmap.image01,R.mipmap.image02,R.mipmap.image03,R.mipmap.image04,
                R.mipmap.image05,R.mipmap.image06,R.mipmap.image07,R.mipmap.image08};

        int[][] args = new int[images.length][3];

        for(int i = 0;i < images.length;i++){
            BitmapFactory.Options options = new BitmapFactory.Options();
            BitmapFactory.decodeResource(getResources(),images[i],options);

            //获取图片的宽高
            int width = options.outWidth;
            int height = options.outHeight;

            args[i][0] = images[i];
            args[i][1] = width;
            args[i][2] = height;

        }

        CommonInfo.setImages(args);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        adapter = new ShopDetailRecyclerViewAdapter(getActivity());
        recyclerView = view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        return view;
    }
}