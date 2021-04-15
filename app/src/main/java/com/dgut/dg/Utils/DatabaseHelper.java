package com.dgut.dg.Utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.dgut.dg.Adapter.VideoDetailRecyclerViewAdapter.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "user_db"; //数据库名称
    private static final int DB_VERSION = 1;//数据库版本,大于0

    // 默认数据库
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public DatabaseHelper(Context context, String dbName){
        super(context, dbName, null, DB_VERSION);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // 第一次使用数据库时调用该方法
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        insertGoodsInfo(sqLiteDatabase);        // 插入商品信息
        insertPersonInfo(sqLiteDatabase);       // 插入用户信息
        insertStepInfo(sqLiteDatabase);         // 插入步数信息

    }

    private void insertGoodsInfo(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table goods(id varchar(5), name varchar(20), isSelected boolean, " +
                "imageUrl varchar(50), descGoods varchar(20), price double," +
                "prime_price double, position int, count int," +
                "color varchar(10), size varchar(10), goodsImg int, isSub int)";
        sqLiteDatabase.execSQL(sql);
        Log.i(TAG, "insertGoodsInfo: 成功创建goods表");
    }

    public void insertPersonInfo(SQLiteDatabase sqLiteDatabase){
        String sql = "create table user(email varchar(20), name varchar(20), gender varchar(20), age int, height double, weight double)";
        sqLiteDatabase.execSQL(sql);
        Log.i(TAG, "insertGoodsInfo: 成功创建user表");

    }

    // 存储步数
    public void insertStepInfo(SQLiteDatabase sqLiteDatabase){
        String sql = "create table step ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "curDate TEXT, "
                + "totalSteps TEXT)";

        sqLiteDatabase.execSQL(sql);
        Log.i(TAG, "insertGoodsInfo: 成功创建step表");



    }


}
