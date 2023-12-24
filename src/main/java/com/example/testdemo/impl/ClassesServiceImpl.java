package com.example.testdemo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testdemo.bean.Classes;
import com.example.testdemo.dao.ClassesDao;
import org.springframework.stereotype.Service;

/**
 * 班级表(Classes)表服务实现类
 *
 * @author makejava
 * @since 2023-03-29 15:06:35
 */
@Service("classesService")
public class ClassesServiceImpl extends ServiceImpl<ClassesDao, Classes> implements ClassesService {

}

