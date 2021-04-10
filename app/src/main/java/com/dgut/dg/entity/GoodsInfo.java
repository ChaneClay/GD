package com.dgut.dg.entity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dgut.dg.Activity.HomeActivity;
import com.dgut.dg.Utils.DatabaseHelper;

/**
 * Created by Administrator on 2017/3/26.
 * 商品信息
 */

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

    String TAG = "TAG";



    public GoodsInfo(){

    }

    public GoodsInfo(String id, String name, String desc,double price, double prime_price,
                     String color, String size, int goodsImg,int count) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.prime_price = prime_price;
        this.count = count;
        this.color = color;
        this.size = size;
        this.goodsImg = goodsImg;
    }

    public GoodsInfo[] getGoodsInfo(Context context) {

        DatabaseHelper dbHelper = new DatabaseHelper(context, "userdb", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int N=5;
        GoodsInfo goodsInfo[] = new GoodsInfo[N];
//        Log.i(TAG, "getGoodsInfo: GoodsInfo length: " + goodsInfo.length);
        for (int i = 0; i < goodsInfo.length; i++) {
            goodsInfo[i] = new GoodsInfo();
        }


        String query = "select * from goods";
        Cursor cursor = db.rawQuery(query, null);

        int i=0;
       while(cursor.moveToNext()){

            Log.i("TAG", "getGoodsInfo: ###" + cursor.getString(cursor.getColumnIndex("price")));

            goodsInfo[i].id = cursor.getString(cursor.getColumnIndex("id"));
            goodsInfo[i].name = cursor.getString(cursor.getColumnIndex("name"));
            goodsInfo[i].isSelected = cursor.getInt(cursor.getColumnIndex("isSelected")) == 0 ? false:true;
            goodsInfo[i].imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl"));
            goodsInfo[i].desc = cursor.getString(cursor.getColumnIndex("descGoods"));
            goodsInfo[i].price = cursor.getDouble(cursor.getColumnIndex("price"));
            goodsInfo[i].prime_price = cursor.getDouble(cursor.getColumnIndex("prime_price"));
            goodsInfo[i].position = cursor.getInt(cursor.getColumnIndex("position"));
            goodsInfo[i].count = cursor.getInt(cursor.getColumnIndex("count"));
            goodsInfo[i].color = cursor.getString(cursor.getColumnIndex("color"));
            goodsInfo[i].size = cursor.getString(cursor.getColumnIndex("size"));
            goodsInfo[i].goodsImg = cursor.getInt(cursor.getColumnIndex("goodsImg"));
            i++;
        }


//        for (int j = 0; j < goodsInfo.length; j++) {
//            Log.i("TAG", "getGoodsInfo: --- " + goodsInfo[j].getPrice());
//        }


//        while (cursor.moveToNext()){
//            goodsInfo.id = cursor.getString(cursor.getColumnIndex("id"));
//            goodsInfo.name = cursor.getString(cursor.getColumnIndex("name"));
//            goodsInfo.isSelected = cursor.getColumnIndex("isSelected") == 0 ? false:true;
//            goodsInfo.imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl"));
//            goodsInfo.desc = cursor.getString(cursor.getColumnIndex("descGoods"));
//
//            goodsInfo.price = cursor.getDouble(cursor.getColumnIndex("price"));
////            Log.i(TAG, "getGoodsInfo: **** " + goodsInfo.price);
//
//            goodsInfo.prime_price = cursor.getDouble(cursor.getColumnIndex("prime_price"));
//
////            Log.i(TAG, "getGoodsInfo: **** " + goodsInfo.prime_price);
//
//
//            goodsInfo.position = cursor.getInt(cursor.getColumnIndex("position"));
//            goodsInfo.count = cursor.getColumnIndex("count");
//            goodsInfo.color = cursor.getString(cursor.getColumnIndex("color"));
//            goodsInfo.size = cursor.getString(cursor.getColumnIndex("size"));
//            goodsInfo.goodsImg = cursor.getColumnIndex("goodsImg");
//        }

        return goodsInfo;
    }





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


}
