package com.dgut.dg.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dgut.dg.Activity.HomeActivity;
import com.dgut.dg.Utils.DatabaseHelper;

import java.util.Map;



public class GoodsInfo {
    private String id;
    private String name;
    private boolean isSelected;
    private String imageUrl;
    private String desc;
    private double price;
    private double prime_price;
    private int position;
    private int count;
    private String color;
    private String size;
    private int goodsImg;
    private int isSub;



    String TAG = "TAG";


    public GoodsInfo(String id, String name, boolean isSelected, String imageUrl, String desc, double price, double prime_price, int position, int count, String color, String size, int goodsImg, int isSub) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.price = price;
        this.prime_price = prime_price;
        this.position = position;
        this.count = count;
        this.color = color;
        this.size = size;
        this.goodsImg = goodsImg;
        this.isSub = isSub;

    }

    public GoodsInfo(){

    }





//
//
//    public GoodsInfo[] getGoodsInfo(Context context) {
//
//        DatabaseHelper dbHelper = new DatabaseHelper(context);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        int N=8;
//        GoodsInfo goodsInfo[] = new GoodsInfo[N];
//        for (int i = 0; i < goodsInfo.length; i++) {
//            goodsInfo[i] = new GoodsInfo();
//        }
//
//
//        String query = "select * from goods";
//        Cursor cursor = db.rawQuery(query, null);
//
//        int i=0;
//       while(cursor.moveToNext()){
//
//            goodsInfo[i].id = cursor.getString(cursor.getColumnIndex("id"));
//            goodsInfo[i].name = cursor.getString(cursor.getColumnIndex("name"));
//            goodsInfo[i].isSelected = cursor.getInt(cursor.getColumnIndex("isSelected")) == 0 ? false:true;
//            goodsInfo[i].imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl"));
//            goodsInfo[i].desc = cursor.getString(cursor.getColumnIndex("descGoods"));
//            goodsInfo[i].price = cursor.getDouble(cursor.getColumnIndex("price"));
//            goodsInfo[i].prime_price = cursor.getDouble(cursor.getColumnIndex("prime_price"));
//            goodsInfo[i].position = cursor.getInt(cursor.getColumnIndex("position"));
//            goodsInfo[i].count = cursor.getInt(cursor.getColumnIndex("count"));
//            goodsInfo[i].color = cursor.getString(cursor.getColumnIndex("color"));
//            goodsInfo[i].size = cursor.getString(cursor.getColumnIndex("size"));
//            goodsInfo[i].goodsImg = cursor.getInt(cursor.getColumnIndex("goodsImg"));
//            goodsInfo[i].isSub = cursor.getInt(cursor.getColumnIndex("isSub"));
//            i++;
//        }
//
//        return goodsInfo;
//    }

//
//    public GoodsInfo setGoodsInfo(Context context, ContentValues values, String id){
//
//        DatabaseHelper dbHelper = new DatabaseHelper(context);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        db.update("goods", values, "id=?", new String[]{id});
//        Log.i(TAG, "setGoodsInfo: 成功修改数据！！！");
//
//
//        GoodsInfo goodsInfos[] = getGoodsInfo(context);
//        GoodsInfo goodsInfo = null;
//        for (int i = 0; i < goodsInfos.length; i++) {
//            goodsInfo = goodsInfos[i];
//            if (goodsInfo.getId().equals(id)){
//                return goodsInfos[i];
//            }
//        }
//        return goodsInfo;
//    }
//




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrime_price() {
        return prime_price;
    }

    public void setPrime_price(double prime_price) {
        this.prime_price = prime_price;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(int goodsImg) {
        this.goodsImg = goodsImg;
    }

    public int getIsSub() {
        return isSub;
    }

    public void setIsSub(int isSub) {
        this.isSub = isSub;
    }


}
