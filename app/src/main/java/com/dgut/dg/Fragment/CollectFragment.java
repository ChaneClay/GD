package com.dgut.dg.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgut.dg.Adapter.CollectAdapter;
import com.dgut.dg.Adapter.SubscribeAdapter;
import com.dgut.dg.Dao.NewsEntityDao;
import com.dgut.dg.R;
import com.dgut.dg.entity.NewsEntity;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static in.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px;


public class CollectFragment extends Fragment {

    private RecyclerView mRvSub;
    CollectAdapter adapter;

    PtrFrameLayout mPtrFrame;
    boolean isUpScroll = false; //是否正在上滑标记

    NewsEntityDao newsEntityDao;
    List<NewsEntity> newsEntities;
    NewsEntity newsEntity;
    List<NewsEntity> mData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

    }


    public void initData(){
        mData = new ArrayList<>();

        // 筛选符合条件地传到Adapter
        newsEntityDao = new NewsEntityDao(getContext());
        newsEntities = newsEntityDao.getNewsEntity();
        for (int i = 0; i < newsEntities.size(); i++) {
            newsEntity = newsEntities.get(i);
            if (newsEntity.getIsSel() == 1){
                mData.add(newsEntity);
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, container, false);


        mPtrFrame = view.findViewById(R.id.mPtrframe);
        initPtrFrame();


        mRvSub= view.findViewById(R.id.rv_sub);


        initEvent();

        return view;
    }

    public void initEvent(){
        adapter = new CollectAdapter(getContext(), mData);
        mRvSub.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvSub.setAdapter(adapter);
        mRvSub.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                mPtrFrame.setEnabled(false);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE ) { //当前未滑动

                    int firstItemPosition = manager.findFirstCompletelyVisibleItemPosition();//第一个显示的位置
                    if (firstItemPosition == 0 && !isUpScroll){
                        mPtrFrame.setEnabled(true);     // 到达顶部
                    }
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isUpScroll = dy > 0;

            }
        });

    }



    private void initPtrFrame() {

        final PtrClassicDefaultHeader header=new PtrClassicDefaultHeader(getContext());
        header.setPadding(dp2px(20), dp2px(20), 0, 0);

        //下拉刷新PtrFrameLayout组件的使用
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();

                        initData();
                        initEvent();
                        adapter.notifyDataSetChanged();

                    }
                },2000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }


}