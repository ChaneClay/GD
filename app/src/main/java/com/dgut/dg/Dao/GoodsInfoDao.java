package com.dgut.dg.Dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.dgut.dg.Utils.DatabaseHelper;
import com.dgut.dg.entity.GoodsInfo;


public class GoodsInfoDao {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public GoodsInfoDao(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    // 获取个人信息
    public GoodsInfo[] getGoodsInfo() {
        db = databaseHelper.getWritableDatabase();

        int N=8;
        GoodsInfo goodsInfo[] = new GoodsInfo[N];

        String query = "select * from goods";
        Cursor cursor = db.rawQuery(query, null);

        int i=0;
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            Boolean isSelected = cursor.getInt(cursor.getColumnIndex("isSelected")) == 0 ? false:true;
            String imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl"));
            String desc = cursor.getString(cursor.getColumnIndex("descGoods"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            double prime_price = cursor.getDouble(cursor.getColumnIndex("prime_price"));
            int position = cursor.getInt(cursor.getColumnIndex("position"));
            int count = cursor.getInt(cursor.getColumnIndex("count"));
            String color = cursor.getString(cursor.getColumnIndex("color"));
            String size = cursor.getString(cursor.getColumnIndex("size"));
            int goodsImg = cursor.getInt(cursor.getColumnIndex("goodsImg"));
            int isSub = cursor.getInt(cursor.getColumnIndex("isSub"));

            goodsInfo[i] = new GoodsInfo(id, name, isSelected, imageUrl, desc, price, prime_price, position, count, color, size, goodsImg, isSub);

            i++;
        }

        return goodsInfo;
    }

    //更新个人信息
    public GoodsInfo updateGoodsInfo(ContentValues values, String id) {
        db = databaseHelper.getReadableDatabase();
        db.update("goods", values, "id=?", new String[]{id});
        int mId = Integer.parseInt(id);

        GoodsInfo goodsInfo = getGoodsInfo()[mId];
        return goodsInfo;
    }

}
