package com.rdc.recruit.config;

public class GeetestConfig {

    // 填入自己的captcha_id和private_key
    private static final String geetest_id = "9c15eb49006f95e46846b069b071b6f3";
    private static final String geetest_key = "8afdcf650b08074456db7f9c55740db1";
    private static final boolean newfailback = true;

    public static String getGeetest_id() {
        return geetest_id;
    }

    public static  String getGeetest_key() {
        return geetest_key;
    }

    public static boolean isNewfailback() {
        return newfailback;
    }
}
