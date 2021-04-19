package com.dgut.dg.Application;

import android.util.Log;

public class MyApplication {

    private static String url;
    private static String emailUser;
    private static String emailPassword;
    private static String emailUserName;

    public static String getCurrEmail() {
        return currEmail;
    }

    public static void setCurrEmail(String currEmail) {
        MyApplication.currEmail = currEmail;
    }

    private static String currEmail;


    static {
        url = "http://v.juhe.cn/toutiao/index?type=&page=&page_size=&key=45a4c8a211a9f35ca031e22a93c1fc77";
        emailUser = "ak51507@163.com";
        emailUserName = "ak51507";
        emailPassword = "JIUUSSCFPKWYKTWG";
        currEmail = "215099895@qq.com";

    }

    public static String getEmailUserName() {
        return emailUserName;
    }

    public static void setEmailUserName(String emailUserName) {
        MyApplication.emailUserName = emailUserName;
    }



    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        MyApplication.url = url;
    }

    public static String getEmailUser() {
        return emailUser;
    }

    public static void setEmailUser(String emailUser) {
        MyApplication.emailUser = emailUser;
    }

    public static String getEmailPassword() {
        return emailPassword;
    }

    public static void setEmailPassword(String emailPassword) {
        MyApplication.emailPassword = emailPassword;
    }











}
