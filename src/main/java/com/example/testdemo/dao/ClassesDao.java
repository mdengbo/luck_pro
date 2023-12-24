package com.example.testdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.testdemo.bean.Classes;
import org.springframework.stereotype.Repository;

/**
 * 学生表(Student)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-22 10:12:40
 */
@Repository
public interface ClassesDao extends BaseMapper<Classes> {

}

