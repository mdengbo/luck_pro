package com.example.testdemo.model;

public abstract class DbService implements IMxProduceSerivice{

    public void excu(){
        serviceMx();
        System.out.println("db");
    }

}
