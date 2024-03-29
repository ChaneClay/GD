package com.dgut.dg.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dgut.dg.Adapter.VideoBrowerAdapter;
import com.dgut.dg.R;
import com.dgut.dg.entity.VideoInfo;

import java.util.LinkedList;
import java.util.List;


public class ForumFragment extends Fragment {

    private List<VideoInfo> aDate;
    private int[]videoAvatars;
    private String[]usernames;
    private String[]videoDates;
    private String[]videoDescripation;
    private String[]videoPaths;
    private String[]videoPosition;

    private ListView lv_video;

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_video_brower, container, false);
        lv_video = view.findViewById(R.id.video_listview);


        videoAvatars = new int[]{R.drawable.head_picture,R.mipmap.video_avatar_02,
                R.mipmap.video_avatar_03,R.mipmap.video_avatar_04,R.drawable.head_picture,R.mipmap.video_avatar_02,
                R.mipmap.video_avatar_03,R.mipmap.video_avatar_04};
        usernames = new String[]{"_张姑娘","Alen_华","程曦不坏","本美一七八","陈海诺Leo","Janet","骑行君","Xuanily"};
        videoDates = new String[]{"08月22日08:02","09月17日07:12","08月22日18:15","08月22日17:42","刚刚","09月17日10:12","08月22日18:15","08月22日17:42"};
        videoDescripation = new String[]{"早！\n越来越早起，越来越晚睡","清晨的景色还是很美的，跑完感觉神清气爽\n加油！",
                "很累\n不过感觉很棒！","#测试1.4# 奥利给","#测试1.1# 童年卢本伟","#测试1.2# 川普","#测试1.3# 李云龙","#测试1.4# 奥利给"};
        videoPaths = new String[]{
                "https://www.bilibili.com/video/av80588467",
                "https://www.bilibili.com/video/av77275486?spm_id_from=333.5.b_6b696368696b755f6775696465.18",
                "https://www.bilibili.com/video/av80721495",
                "https://www.bilibili.com/video/av75889185?spm_id_from=333.5.b_6b696368696b755f6d6164.4",
                "https://www.bilibili.com/video/av80588467",
                "https://www.bilibili.com/video/av77275486?spm_id_from=333.5.b_6b696368696b755f6775696465.18",
                "https://www.bilibili.com/video/av80721495",
                "https://www.bilibili.com/video/av75889185?spm_id_from=333.5.b_6b696368696b755f6d6164.4"};

        videoPosition = new String[]{"北京市朝阳区","郑州市中原区","郑州市中原区","郑州市中原区","北京市朝阳区","郑州市中原区","郑州市中原区","郑州市中原区"};

        aDate = new LinkedList<>();
        for (int i=0;i<videoAvatars.length;i++){
            aDate.add(new VideoInfo(videoAvatars[i],usernames[i],videoDates[i],
                    videoDescripation[i],videoPaths[i],videoPosition[i]));
        }

        lv_video.setAdapter(new VideoBrowerAdapter((LinkedList<VideoInfo>)aDate,mContext));

        lv_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }
}