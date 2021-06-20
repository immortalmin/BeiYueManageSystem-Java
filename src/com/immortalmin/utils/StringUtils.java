package com.immortalmin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringUtils {

    public static boolean isIncludeChinese(String s){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(s);
        return m.find();
    }
}
