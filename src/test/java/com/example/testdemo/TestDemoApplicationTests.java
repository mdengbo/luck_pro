package com.example.testdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
class TestDemoApplicationTests {

    volatile Boolean asynStop = false;

    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
        Boolean asynStop = false;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        AtomicReference<Boolean> finalAsynStop1 = new AtomicReference<>(asynStop);
        Map<String, Boolean> map = new HashMap<>();
        Map<String, Integer> mapInt = new HashMap<>();
        map.put("list_test", false);
        for (int j = 0; j < 4; j++) {

            String threadName = "test_thread_name_" + j;
            final Future<Boolean> submit = executorService.submit(() -> {
                Thread.currentThread().setName(threadName);
                int h = 0;
                for (int i = 0; i < 10000000; i++) {

//                    if(i < 10 && Thread.currentThread().getName().equalsIgnoreCase("test_thread_name_" + 1)) {
//                        Thread.sleep(1000);
//                    }
                    if (i == 100000 ){
                        if( Thread.currentThread().getName().equalsIgnoreCase("test_thread_name_" + 1)){
                            System.out.println("异常推出 ：" + Thread.currentThread().getName());
                            map.put("list_test", true);
//                            finalAsynStop1.set(true);
                        }
//                        int k = i/0;
                    }
                    if(i==1000000){
//                        System.out.println("finalAsynStop1: " + finalAsynStop1.get());
                        System.out.println("list_test: " + map.get("list_test"));
                    }
                    mapInt.put("key", mapInt.getOrDefault("key", 0) + 1);
                    h ++;
                }
                System.out.println("正常执行：" + Thread.currentThread().getName() + " === " + h + " map:" + mapInt.get("key"));
                return false;
            });

            if (submit.get()) {
                //多线程异步控制
                asynStop = true;
            }
        }

       // System.out.println(executorService.awaitTermination(2, TimeUnit.MINUTES));
    }

}
