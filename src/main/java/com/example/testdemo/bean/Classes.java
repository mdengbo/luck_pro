package com.example.testdemo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 学生表(Student)表实体类
 *
 * @author makejava
 * @since 2023-03-22 10:12:42
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class Classes extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Long id;

    //姓名
    private String className;
    //年龄
    private String classNum;

    }

