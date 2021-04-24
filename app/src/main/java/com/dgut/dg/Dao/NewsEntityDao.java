package com.dgut.dg.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dgut.dg.Utils.DatabaseHelper;
import com.dgut.dg.entity.GoodsInfo;
import com.dgut.dg.entity.NewsEntity;

import java.util.ArrayList;
import java.util.List;

public class NewsEntityDao {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    public NewsEntityDao(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public List<NewsEntity> getNewsEntity(){
        db = databaseHelper.getWritableDatabase();
        List<NewsEntity> dataList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from news", null);
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String thumbUrl = cursor.getString(cursor.getColumnIndex("thumbUrl"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            int isSel = cursor.getInt(cursor.getColumnIndex("isSel"));

            dataList.add(new NewsEntity(id, date, thumbUrl, title, url, isSel));
        }
        db.close();
        cursor.close();
        return dataList;
    }

    //更新个人信息
    public NewsEntity updateNewsEntity(ContentValues values, String id) {
        db = databaseHelper.getReadableDatabase();

        db.update("news", values, "id=?", new String[]{id});
        int mId = Integer.parseInt(id);

        NewsEntity newsEntity = getNewsEntity().get(mId);
        return newsEntity;
    }

}
