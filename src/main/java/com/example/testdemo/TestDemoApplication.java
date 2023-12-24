package com.example.testdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@MapperScan("com.example.testdemo.dao")
public class TestDemoApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(TestDemoApplication.class, args);
        final ConfigurableEnvironment environment = run.getEnvironment();

        System.out.println(environment.getProperty("spring.application.name"));
    }

}
