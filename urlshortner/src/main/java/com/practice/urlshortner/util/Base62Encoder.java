package com.practice.urlshortner.util;

public class Base62Encoder{
    private static final String str = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static String encode(long id){
        StringBuilder str1 = new StringBuilder();
        if((int) id == 0){
            return "a";
        }
        while(id >= 0){
            int rem = (int)id%62;
            str1.append(str.charAt(rem));
            id = id/62;
        }
        return str1.reverse().toString();
    }
}
