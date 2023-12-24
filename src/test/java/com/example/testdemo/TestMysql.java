package com.example.testdemo;

import com.example.testdemo.impl.ClassesServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestMysql {

    @Resource(name = "classesService")
    ClassesServiceImpl classesService;
    @Test
    public void test1() {
        System.out.println(classesService.getById("1"));
    }

}
