package com.dgut.dg.Fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgut.dg.Adapter.ShopDetailRecyclerViewAdapter;
import com.dgut.dg.Adapter.VideoDetailRecyclerViewAdapter;
import com.dgut.dg.R;
import com.dgut.dg.Utils.CommonRes;
import com.dgut.dg.Utils.VideoBean;

import java.util.ArrayList;
import java.util.List;


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

        // 设置图片资源
        int[] images = new int[]{R.mipmap.img1,R.mipmap.img2,R.mipmap.img3,R.mipmap.img4,
                R.mipmap.img5,R.mipmap.img6,R.mipmap.img7,R.mipmap.img8,
                R.mipmap.img9,R.mipmap.img10,R.mipmap.img11,R.mipmap.img12,
                R.mipmap.img13,R.mipmap.img14,R.mipmap.img15,R.mipmap.img16,R.mipmap.img17};
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

            Log.i("YYYY","图片的宽度:"+width+"图片的高度:"+height);


        }



        CommonRes.setImages(args);

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