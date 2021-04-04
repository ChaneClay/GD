package com.dgut.dg.Utils;


import java.util.Random;

public class RandomData {
    /**
     * n位随机数
     * @param n
     * @return
     */
    public long getRandomNumber(int n){
        if(n<1){
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return (long)(Math.random()*9*Math.pow(10,n-1)) + (long)Math.pow(10,n-1);
    }

    public String getRandomString(){
        final int N = 8;

        String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<N; i++){
            int number = random.nextInt(base.length()-1);
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

}
