package com.dgut.dg.entity;




public class PersonalInfo {

    private static String email;
    private static String name;
    private static String gender;
    private static int age;
    private static double height;
    private static double weight;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        PersonalInfo.email = email;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        PersonalInfo.name = name;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        PersonalInfo.gender = gender;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        PersonalInfo.age = age;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        PersonalInfo.height = height;
    }

    public static double getWeight() {
        return weight;
    }

    public static void setWeight(double weight) {
        PersonalInfo.weight = weight;
    }

}
