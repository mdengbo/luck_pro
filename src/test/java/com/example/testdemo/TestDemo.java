package com.example.testdemo;

import com.example.demo.StaticClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemo {

    @Test
    public void test1() {
//       String str  = StaticClass.str0;
//       String str  = StaticClass.str;
         StaticClass.getStrFun();
//        new StaticClass();
    }
}
