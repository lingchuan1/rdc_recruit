package com.rdc.recruit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
     * 验证姓名
     * @param str
     * @return
     */
    public static boolean isLegalName(String str){
        if(Pattern.matches("^[\u4E00-\u9FA5]{2,12}$",str))
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

    /**
     * 过滤特殊字符
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public   static   String StringFilter(String   str)   throws PatternSyntaxException {
        // 清除掉所有特殊字符
        String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher(str);
        return   m.replaceAll("").trim();
    }
}
