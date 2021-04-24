package com.dgut.dg.entity;


public class NewsEntity {

    private String id;
    private String date;
    private String thumbUrl;
    private String title;
    private String url;
    private int isSel;

    public NewsEntity(String id, String date, String thumbUrl, String title, String url, int isSel) {
        this.date = date;
        this.id = id;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.url = url;
        this.isSel = isSel;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public int getIsSel() {
        return isSel;
    }

    public void setIsSel(int isSel) {
        this.isSel = isSel;
    }

}