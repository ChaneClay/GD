package com.dgut.dg.entity;



public class PersonalInfo {

    private String email;
    private String name;
    private String gender;
    private String birthday;
    private int height;
    private int weight;
    private String address;



    public PersonalInfo(String email, String name, String gender, String birthday, int height, int weight, String address) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
