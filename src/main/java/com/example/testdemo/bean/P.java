package com.example.testdemo.bean;

public class P {

    private String str;
    public static String str1;

    static {
        System.out.println("父类 static");
    }

    public P() {
        System.out.println("父类 构造函数");
    }

    {
        System.out.println("父类 代码快");
    }

    public  class PInnerClassOne {

        public PInnerClassOne(){
            System.out.println("父类 非静态内部类 构造函数");
        }
    }

    public static class PInnerClass {
        static {
            System.out.println("父类 静态内部类 static");
        }

        public PInnerClass(){
            System.out.println("父类 静态内部类 构造函数");
        }
    }

}
