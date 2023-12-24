package com.example.testdemo;

import com.example.testdemo.bean.Child;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemo {

    @Test
    public void test1() {
        System.out.println(new Child.ChildInnerClass());

        System.out.println("1".hashCode());
        System.out.println("2".hashCode());
        System.out.println("3".hashCode());
        System.out.println("a".hashCode());
        System.out.println("A".hashCode());
        System.out.println("中国人民解放觉万岁".hashCode());

    }
}
