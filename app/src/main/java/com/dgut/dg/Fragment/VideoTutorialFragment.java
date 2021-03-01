package com.dgut.dg.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.dgut.dg.Activity.Person;
import com.dgut.dg.Activity.VideosActivity;
import com.dgut.dg.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class VideoTutorialFragment extends Fragment {


//    private TextView textView;
//    private VideoView videoView;


    private RecyclerView recyclerView;
    private List<Person> personList = new ArrayList<>();



    LinearLayoutManager layoutManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_tutorial, container, false);




        // 在这里添加RecyclerView


        recyclerView = view.findViewById(R.id.recycler);

        // 设置RecyclerView保持固定大小,这样可优化RecyclerView的性能
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // 设置RecyclerView的滚动方向
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 为RecyclerView设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        initData();

        RecyclerView.Adapter adapter = new RecyclerView.Adapter<PersonViewHolder>()
        {
            // 创建列表项组件的方法，该方法创建组件会被自动缓存
            @Override
            public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
            {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item, null);
                return new PersonViewHolder(view, this);
            }
            // 为列表项组件绑定数据的方法，每次组件重新显示出来时都会重新执行该方法
            @Override
            public void onBindViewHolder(PersonViewHolder viewHolder, int i)
            {
                viewHolder.nameTv.setText(personList.get(i).name);
                viewHolder.descTv.setText(personList.get(i).desc);
                viewHolder.headerIv.setImageResource(personList.get(i).header);

                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        switch (i){
                            case 0:

                                Intent intent = new Intent(getContext(), VideosActivity.class);
                                startActivity(intent);

//                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                                Fragment fragment = new VideoTutorialFragment();
////                                fragmentManager.beginTransaction().replace();

//                                Toast.makeText(getActivity(), "位置-: "+ i, Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(getActivity(), "位置--: "+ i, Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(getActivity(), "默认", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                });

            }
            // 该方法的返回值决定包含多少个列表项
            @Override
            public int getItemCount()
            {
                return personList.size();
            }
        };

        recyclerView.setAdapter(adapter);

        return view;
    }


    private void initData()
    {
        String[] names = new String[]{"虎头", "弄玉", "李清照", "李白"};
        String[] descs = new String[]{"可爱的小孩", "一个擅长音乐的女孩",
                "一个擅长文学的女性", "浪漫主义诗人"};
        int[] imageIds = new int[]{R.drawable.qingzhao,
                R.drawable.nongyu, R.drawable.qingzhao, R.drawable.libai};


        // 清除原有的信息
        if (!personList.isEmpty()){
            personList.clear();
        }


        for(int i = 0; i < names.length; i++){
            System.out.println("add: "+names[i]);
            this.personList.add(new Person(names[i], descs[i], imageIds[i]));
        }
    }
    class PersonViewHolder extends RecyclerView.ViewHolder
    {
        View rootView;
        TextView nameTv;
        TextView descTv;
        ImageView headerIv;

        public PersonViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView);
            this.nameTv = itemView.findViewById(R.id.name);
            this.descTv = itemView.findViewById(R.id.desc);
            this.headerIv = itemView.findViewById(R.id.header);
            this.rootView = itemView.findViewById(R.id.item_root);

        }
    }

}