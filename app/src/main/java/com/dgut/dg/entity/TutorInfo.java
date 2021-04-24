package com.dgut.dg.entity;

public class TutorInfo {

    private String id;
    private String introduce;
    private String date;    // 后来修改,将关注改为时间
    private int image;      // 后来加上去的
    private int isSub;      // 后来加上去的
    private String user_id;

    public TutorInfo(String id, String introduce, String date, int image, int isSub, String user_id) {
        this.id = id;
        this.introduce = introduce;
        this.date = date;
        this.image = image;
        this.isSub = isSub;
        this.user_id = user_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getIsSub() {
        return isSub;
    }

    public void setIsSub(int isSub) {
        this.isSub = isSub;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
