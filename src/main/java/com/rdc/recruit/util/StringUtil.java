package com.rdc.recruit.util;

import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str==null||str.equals(""))
            return true;
        else return false;
    }

    /**
     * 验证手机号
     * @param str
     * @return
     */
    public static boolean isLegalPhone(String str){
        if(Pattern.matches("^1[0-9]{10}$",str))
            return true;
        else
            return false;
    }

    /**
     * 验证学号
     * @param str
     * @return
     */
    public static boolean isLegalStuId(String str){
        if(Pattern.matches("^3[1|2]1[7|8][0-9]{6}$",str))
            return true;
        else return false;
    }
}
