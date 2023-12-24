package com.example.testdemo.controller;


import com.example.testdemo.impl.ClassesServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Resource(name = "classesService")
    ClassesServiceImpl classesService;

    @RequestMapping("/getById")
    public Object getById(String id) {

        return classesService.getById(id);
    }
}
