package com.dgut.dg.Dao;

import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dgut.dg.Utils.DatabaseHelper;
import com.dgut.dg.entity.PersonalInfo;

import static com.dgut.dg.Adapter.NewsDetailRecyclerViewAdapter.TAG;

public class PersonalInfoDao {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public PersonalInfoDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }



    // 限制查询条件


    public PersonalInfo getPersonalInfo(){
        PersonalInfo personalInfo;

        db = databaseHelper.getWritableDatabase();
        String query = "select * from user";
        Cursor cursor = db.rawQuery(query, null);

        String email = null;
        String name = null;
        String gender = null;
        String birthday = null;
        int height = 0;
        int weight = 0;
        String address = null;

        while (cursor.moveToNext()){

            name = cursor.getString(cursor.getColumnIndex("name"));;
            email = cursor.getString(cursor.getColumnIndex("email"));
            gender = cursor.getString(cursor.getColumnIndex("gender"));;
            birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            height = cursor.getInt(cursor.getColumnIndex("height"));
            weight = cursor.getInt(cursor.getColumnIndex("weight"));
            address = cursor.getString(cursor.getColumnIndex("address"));
        }

        personalInfo = new PersonalInfo(email, name, gender, birthday, height, weight, address);

        return personalInfo;

    }


    public PersonalInfo getPersonalInfo(String curEmail){
        PersonalInfo personalInfo;

        db = databaseHelper.getWritableDatabase();


//        String query = "select * from user where email=" + curEmail;

        Cursor cursor= db.rawQuery("select * from user where email=?",new String[]{curEmail});

//创建一个游标

//        Cursor cursor = db.rawQuery(query, null);

        String email = null;
        String name = null;
        String gender = null;
        String birthday = null;
        int height = 0;
        int weight = 0;
        String address = null;

        while (cursor.moveToNext()){

            name = cursor.getString(cursor.getColumnIndex("name"));;
            email = cursor.getString(cursor.getColumnIndex("email"));
            gender = cursor.getString(cursor.getColumnIndex("gender"));;
            birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            height = cursor.getInt(cursor.getColumnIndex("height"));
            weight = cursor.getInt(cursor.getColumnIndex("weight"));
            address = cursor.getString(cursor.getColumnIndex("address"));
        }

        personalInfo = new PersonalInfo(email, name, gender, birthday, height, weight, address);

        return personalInfo;

    }


    public PersonalInfo updatePersonalInfo(ContentValues values, String email) {
        db = databaseHelper.getReadableDatabase();
        db.update("user", values, "email=?", new String[]{email});

        Log.i(TAG, "updatePersonalInfo: 成功更新个人信息");
        return getPersonalInfo();

    }

    public void insertPersonalInfo(ContentValues contentValues){

        db = databaseHelper.getWritableDatabase();
        db.insert("user", null, contentValues);
    }





}
