package com.example.demo;

public class StaticClass {

    public static final String str0 = "ii";
    public static String str;
    public String str1;
    public static int i;

    {
        System.out.println("代码快");
    }

    static {
        System.out.println("static 代码快");
        str = "str0";
    }
    public StaticClass() {
        System.out.println("构造器");
    }

    public static String getStr0() {
        return str0;
    }

    public static String getStr() {
        return str;
    }

    public static void setStr(String str) {
        StaticClass.str = str;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public static void getStrFun() {
        System.out.println("static fun:" + str);
        System.out.println("static fun:" + i);
    }
}
