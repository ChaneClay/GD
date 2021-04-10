package com.dgut.dg.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgut.dg.Activity.HomeActivity;
import com.dgut.dg.Adapter.ShopCartAdapter;
import com.dgut.dg.R;
import com.dgut.dg.Utils.DatabaseHelper;
import com.dgut.dg.Utils.UtilTool;
import com.dgut.dg.Utils.UtilsLog;
import com.dgut.dg.entity.GoodsInfo;
import com.dgut.dg.entity.StoreInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import static in.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px;


public class ShoppingCartFragment extends Fragment implements View.OnClickListener, ShopCartAdapter.CheckInterface, ShopCartAdapter.ModifyCountInterface, ShopCartAdapter.GroupEditorListener{


    private String TAG = "ShoppingCartFragment";
    ExpandableListView listView;
    CheckBox allCheckBox;
    TextView totalPrice;
    TextView goPay;
    LinearLayout orderInfo;
    TextView shareGoods;
    TextView collectGoods;
    TextView delGoods;
    LinearLayout shareInfo;

    PtrFrameLayout mPtrFrame;

    Button actionBarEdit;

    private Context mContext;
    private double mTotalPrice = 0.00;
    private int mTotalCount = 0;

    //false就是编辑，true就是完成
    private boolean flag = false;
    private ShopCartAdapter adapter;
    private List<StoreInfo> groups;                 //组元素的列表
    private Map<String, List<GoodsInfo>> childs; //子元素的列表

    private View mMainView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mMainView = inflater.inflate(R.layout.main, container, false);
        allCheckBox = mMainView.findViewById(R.id.all_checkBox);
        goPay = mMainView.findViewById(R.id.go_pay);
        shareGoods = mMainView.findViewById(R.id.share_goods);
        collectGoods = mMainView.findViewById(R.id.collect_goods);
        delGoods = mMainView.findViewById(R.id.del_goods);


        allCheckBox.setOnClickListener(this);
        goPay.setOnClickListener(this);
        shareGoods.setOnClickListener(this);
        collectGoods.setOnClickListener(this);
        delGoods.setOnClickListener(this);

        mContext = getActivity();
        initPtrFrame();
        initData();
        initEvents();

        return mMainView;
    }

    private void initPtrFrame() {

        final PtrClassicDefaultHeader header=new PtrClassicDefaultHeader(mMainView.getContext());
        header.setPadding(dp2px(20), dp2px(20), 0, 0);

        //下拉刷新PtrFrameLayout组件的使用
        mPtrFrame = mMainView.findViewById(R.id.mPtrframe);

        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                },2000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }


    private void initEvents() {

        adapter = new ShopCartAdapter(groups, childs, mContext);
        adapter.setCheckInterface(this);//关键步骤1：设置复选框的接口
        adapter.setModifyCountInterface(this); //关键步骤2:设置增删减的接口
        adapter.setGroupEditorListener(this);//关键步骤3:监听组列表的编辑状态

        listView = mMainView.findViewById(R.id.listView);

        listView.setGroupIndicator(null); //设置属性 GroupIndicator 去掉向下箭头
        listView.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i); //关键步骤4:初始化，将ExpandableListView以展开的方式显示
        }
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int firstVisiablePostion=view.getFirstVisiblePosition();
                int top=-1;
                View firstView=view.getChildAt(firstVisibleItem);
                UtilsLog.i("childCount="+view.getChildCount());//返回的是显示层面上的所包含的子view的个数
                if(firstView!=null){
                    top=firstView.getTop();
                }
                UtilsLog.i("firstVisiableItem="+firstVisibleItem+",fistVisiablePosition="+firstVisiablePostion+",firstView="+firstView+",top="+top);
                if(firstVisibleItem==0&&top==0){
                    mPtrFrame.setEnabled(true);
                }else{
                    mPtrFrame.setEnabled(false);
                }
            }
        });
    }



//    private void initData() {
////        mContext = getActivity();
//        // 代表一个店铺
//        groups = new ArrayList<StoreInfo>();
//        childs = new HashMap<String, List<GoodsInfo>>();
//        for (int i = 0; i < 1; i++) {
//            groups.add(new StoreInfo(i + "", "小马的第" + (i + 1) + "号当铺"));
//
//            // 代表一个商品
//            List<GoodsInfo> goods = new ArrayList<>();
//
//            for (int j = 0; j <= i+5; j++) {
//                int[] img = {R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz};
//                //i-j 就是商品的id， 对应着第几个店铺的第几个商品，1-1 就是第一个店铺的第一个商品
//                goods.add(new GoodsInfo(i + "-" + j, "商品", groups.get(i).getName() + "的第" + (j + 1) + "个商品", 255.00 + new Random().nextInt(1500), 1555 + new Random().nextInt(3000), "红色", "L", img[j], new Random().nextInt(100)));
//            }
//            //一个键值对应一个商家，一个商家对应多个货物
//            childs.put(groups.get(i).getId(), goods);
//        }
//
//
//    }



    private void initData() {

        GoodsInfo goodsInfo[] = new GoodsInfo().getGoodsInfo(mContext);



        // adapter = new ShopCartAdapter(groups, childs, mContext);
        // 代表一个店铺
        groups = new ArrayList<StoreInfo>();
        childs = new HashMap<String, List<GoodsInfo>>();

        groups.add(new StoreInfo(0 + "", "小马的当铺"));

        // 代表一个商品
        List<GoodsInfo> goods = new ArrayList<>();


        for (int i = 0; i < goodsInfo.length; i++) {

            goods.add(goodsInfo[i]);
        }


//        for (int j = 0; j <= 5; j++) {
//
//            int[] img = {R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz};
//            goods.add(new GoodsInfo(String.valueOf(j), "商品", groups.get(0).getName() + "的第" + (j + 1) + "个商品", 255.00 + new Random().nextInt(1500), 1555 + new Random().nextInt(3000), "红色", "L", img[j], new Random().nextInt(100)));
//
//        }

        //一个键值对应一个商家，一个商家对应多个货物
        childs.put(groups.get(0).getId(), goods);

    }

    private void doDelete() {
        List<StoreInfo> toBeDeleteGroups = new ArrayList<StoreInfo>(); //待删除的组元素
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            if (group.isSelected()) {
                toBeDeleteGroups.add(group);
            }
            List<GoodsInfo> toBeDeleteChilds = new ArrayList<GoodsInfo>();//待删除的子元素
            List<GoodsInfo> child = childs.get(group.getId());
            for (int j = 0; j < child.size(); j++) {
                if (child.get(j).isSelected()) {
                    toBeDeleteChilds.add(child.get(j));
                }
            }
            child.removeAll(toBeDeleteChilds);
        }
        groups.removeAll(toBeDeleteGroups);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> child = childs.get(group.getId());
        for (int i = 0; i < child.size(); i++) {
            child.get(i).setSelected(isChecked);
        }
        if (isCheckAll()) {
            Log.i(TAG, "checkGroup: 全选----");
            allCheckBox.setChecked(true);//全选
        } else {
            Log.i(TAG, "checkGroup: 反选----");
            allCheckBox.setChecked(false);//反选
        }
        adapter.notifyDataSetChanged();
        calculate();
    }


    private boolean isCheckAll() {
        for (StoreInfo group : groups) {
            if (!group.isSelected()) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true; //判断该组下面的所有子元素是否处于同一状态
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> child = childs.get(group.getId());
        for (int i = 0; i < child.size(); i++) {
            //不选全中
            if (child.get(i).isSelected() != isChecked) {
                allChildSameState = false;
                break;
            }
        }

        if (allChildSameState) {
            group.setChoosed(isChecked);//如果子元素状态相同，那么对应的组元素也设置成这一种的同一状态
        } else {
            group.setChoosed(false);//否则一律视为未选中
        }

        if (isCheckAll()) {
            allCheckBox.setChecked(true);//全选
        } else {
            allCheckBox.setChecked(false);//反选
        }

        adapter.notifyDataSetChanged();
        calculate();
    }



    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo good = (GoodsInfo) adapter.getChild(groupPosition, childPosition);
        int count = good.getCount();
        count++;
        good.setCount(count);
        ((TextView) showCountView).setText(String.valueOf(count));
        adapter.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo good = (GoodsInfo) adapter.getChild(groupPosition, childPosition);
        int count = good.getCount();
        if (count == 1) {
            return;
        }
        count--;
        good.setCount(count);
        ((TextView) showCountView).setText("" + count);
        adapter.notifyDataSetChanged();
        calculate();
    }



    @Override
    public void doUpdate(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo good = (GoodsInfo) adapter.getChild(groupPosition, childPosition);
        int count = good.getCount();
        UtilsLog.i("进行更新数据，数量" + count + "");
        ((TextView) showCountView).setText(String.valueOf(count));
        adapter.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> child = childs.get(group.getId());
        child.remove(childPosition);
        if (child.size() == 0) {
            groups.remove(groupPosition);
        }
        adapter.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void groupEditor(int groupPosition) {

    }

    private void setActionBarEditor() {
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            if (group.isActionBarEditor()) {
                group.setActionBarEditor(false);
            } else {
                group.setActionBarEditor(true);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            group.setChoosed(allCheckBox.isChecked());
            List<GoodsInfo> child = childs.get(group.getId());
            for (int j = 0; j < child.size(); j++) {
                child.get(j).setSelected(allCheckBox.isChecked());//这里出现过错误
            }
        }
        adapter.notifyDataSetChanged();
        calculate();
    }

    private void calculate() {
        mTotalPrice = 0.00;
        mTotalCount = 0;
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            List<GoodsInfo> child = childs.get(group.getId());
            for (int j = 0; j < child.size(); j++) {
                GoodsInfo good = child.get(j);
                if (good.isSelected()) {
                    mTotalCount++;
                    mTotalPrice += good.getPrice() * good.getCount();
                }
            }
        }
        totalPrice = mMainView.findViewById(R.id.total_price);
        totalPrice.setText("￥" + mTotalPrice + "");

        goPay = mMainView.findViewById(R.id.go_pay);
        goPay.setText("去支付(" + mTotalCount + ")");

    }

    private void setVisiable() {
        if (flag) {
            orderInfo = mMainView.findViewById(R.id.order_info);
            shareInfo = mMainView.findViewById(R.id.share_info);

            orderInfo.setVisibility(View.GONE);
            shareInfo.setVisibility(View.VISIBLE);
            actionBarEdit.setText("完成");
        } else {
            orderInfo.setVisibility(View.VISIBLE);
            shareInfo.setVisibility(View.GONE);
            actionBarEdit.setText("编辑");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        childs.clear();
        groups.clear();
        mTotalPrice = 0.00;
        mTotalCount = 0;
    }


    // 与点击事件绑定
    @Override
    public void onClick(View view) {

        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.all_checkBox:
                doCheckAll();
                break;
            case R.id.go_pay:
                if (mTotalCount == 0) {
                    UtilTool.toast(mContext, "请选择要支付的商品");
                    return;
                }
                dialog = new AlertDialog.Builder(mContext).create();
                dialog.setMessage("总计:" + mTotalCount + "种商品，" + mTotalPrice + "元");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "支付", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                dialog.show();
                break;
            case R.id.share_goods:
                if (mTotalCount == 0) {
                    UtilTool.toast(mContext, "请选择要分享的商品");
                    return;
                }
                UtilTool.toast(mContext, "分享成功");
                break;
            case R.id.collect_goods:
                if (mTotalCount == 0) {
                    UtilTool.toast(mContext, "请选择要收藏的商品");
                    return;
                }
                UtilTool.toast(mContext, "收藏成功");
                break;
            case R.id.del_goods:
                if (mTotalCount == 0) {
                    UtilTool.toast(mContext, "请选择要删除的商品");
                    return;
                }
                dialog = new AlertDialog.Builder(mContext).create();
                dialog.setMessage("确认要删除该商品吗?");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doDelete();
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                dialog.show();
                break;

        }
    }

}