package com.example.testdemo.bean;

public class Child extends P {

    private String str;
    public static String str1;

    static {
        System.out.println("子类 static:" + str1);
        str1 = "子类 static 赋值";
        System.out.println("子类 static:" + str1);
    }

    public Child() {
        System.out.println("子类 构造函数");
    }

    {
        System.out.println("子类 代码快");
    }

    public  class ChildInnerClassOne {

        public ChildInnerClassOne(){
            System.out.println("子类 非静态内部类:" + str1);
            System.out.println("子类 非静态内部类 构造函数");
            System.out.println("子类 非静态内部类:" + str1);
        }
    }

    public static class ChildInnerClass {
        static {
            System.out.println("子类 静态内部类 static：" + str1);
            str1 = "child 子类静态内部类赋值";
            System.out.println("子类 静态内部类 static：" + str1);
        }

        public ChildInnerClass(){
            System.out.println("子类 静态内部类 构造函数");
        }
    }

}
