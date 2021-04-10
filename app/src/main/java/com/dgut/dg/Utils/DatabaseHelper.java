package com.dgut.dg.Utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.dgut.dg.Adapter.VideoDetailRecyclerViewAdapter.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // 第一次使用数据库时调用该方法
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        insertGoodsInfo(sqLiteDatabase);
        insertPersonInfo(sqLiteDatabase);

    }

    private void insertGoodsInfo(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "insertGoodsInfo: ----222");
        String sql = "create table goods(id varchar(5), name varchar(20), isSelected boolean, " +
                "imageUrl varchar(50), descGoods varchar(20), price double," +
                "prime_price double, position int, count int," +
                "color varchar(10), size varchar(10), goodsImg int)";
        sqLiteDatabase.execSQL(sql);
    }

    public void insertPersonInfo(SQLiteDatabase sqLiteDatabase){
        Log.i(TAG, "insertGoodsInfo: ----333");
        String sql = "create table user(email varchar(20), name varchar(20), gender varchar(20), age int, height double, weight double)";
        sqLiteDatabase.execSQL(sql);
    }

}
