package com.example.testdemo.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体基础类
 * */
@Data
public class BaseEntity implements Serializable {

    private Date createTime;
    //更新时间
    private Date updateTime;
    //状态
    private Integer status;
    //删除标识 0-可用，1-删除
    private Integer delFlag;

}
