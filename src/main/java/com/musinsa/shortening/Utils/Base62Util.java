package com.musinsa.shortening.Utils;

import org.springframework.stereotype.Component;

@Component
public class Base62Util {

    static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public static String encode(int value) {
        final StringBuilder sb = new StringBuilder();
        
        while(value > 0) {
            sb.append(BASE62[value % 62]);
            value /= 62;
        }
        
        return sb.toString();
    }

    public static int decode(String value) {
        int result=0;
        int power=1;
        for (int i = 0; i < value.length(); i++) {
            int digit = new String(BASE62).indexOf(value.charAt(i));
            result += digit * power;
            power *= 62;
        }
        return result;
    }
}