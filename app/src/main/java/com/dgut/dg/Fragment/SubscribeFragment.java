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

import com.dgut.dg.Adapter.SubscribeAdapter;
import com.dgut.dg.R;
import com.dgut.dg.entity.TutorInfo;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static in.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px;


public class SubscribeFragment extends Fragment {

    private RecyclerView mRvSub;
    SubscribeAdapter adapter;
    List<TutorInfo> mData;
    private int NUM=8;

    PtrFrameLayout mPtrFrame;
    boolean isUpScroll = false; //是否正在上滑标记






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subscribe, container, false);

        mPtrFrame = view.findViewById(R.id.mPtrframe);
        initPtrFrame();
        mRvSub= view.findViewById(R.id.rv_sub);
        mData = new ArrayList<>();



        // 论坛数据
        String ids[] = {"1", "1", "1", "1"};
        String introduces[] = {"20年跑步经验\nCBBA高级教练认证\n亚洲体适能高级教练员", "左右普拉提教练员\n中国运动康复师认证\n前赛普康复导员", "美国普拉提认证教练\nDAILY YOGA线上图集减脂营总教练", "EPTC急速瘦身认证\n国家职业健身中级教练认证\nBIGGER认证跑步教练"};
        String date[] = {"2021-07-12 18:30:00", "2021-06-12 10:20:00", "2021-07-06 15:30:00", "2021-07-16 19:50:00"};
        int image[] = {R.mipmap.tutor01, R.mipmap.tutor02, R.mipmap.tutor03, R.mipmap.tutor04, };
        int isSub[] = {0, 1, 1, 0};
        String user_id[] = {"4", "4", "4", "4"};


        for (int i = 0; i < ids.length; i++) {
            mData.add(new TutorInfo(ids[i], introduces[i], date[i], image[i], isSub[i], user_id[i]));
        }

        initEvent();


        return view;
    }

    public void initEvent(){

        adapter = new SubscribeAdapter(getContext(), mData);
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
                        Log.i("TAG", "onScrollStateChanged: 到达顶部");
                        mPtrFrame.setEnabled(true);
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

                        Log.i("TAG", "run: 正在刷新！！！");

//                        initData();
//                        initEvents();

//                        adapter.notifyDataSetChanged();

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