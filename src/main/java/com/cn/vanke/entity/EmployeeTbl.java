package com.cn.vanke.entity;

import com.cn.vanke.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.*;

import java.util.Date;

import javax.persistence.*;

/**
 *
 * EmployeeTbl.java
 * 
 * @Author : zengh05
 * @DateTime : 2017-08-30 15:18:26
 * 
 * Copyright (C)1984-2017  深圳万科物业发展有限公司  All rights reserved.
 */
@ApiModel("表名称 : employee_tbl")
@Table(name = "employee_tbl")
public class EmployeeTbl {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @JsonProperty(value = "id")
    @ApiModelProperty(value = "主键ID",readOnly = true,dataType = "Integer")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "name")
    @JsonProperty(value = "name")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 时间
     */
    @Column(name = "date")
    @JsonProperty(value = "date")
    @ApiModelProperty(value = "时间")
    @JsonFormat(pattern = DateUtils.TO_SECOND)
    private Date date;

    /**
     * 标志
     */
    @Column(name = "singin")
    @JsonProperty(value = "singin")
    @ApiModelProperty(value = "标志")
    private Byte singin;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取时间
     *
     * @return date - 时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置时间
     *
     * @param date 时间
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取标志
     *
     * @return singin - 标志
     */
    public Byte getSingin() {
        return singin;
    }

    /**
     * 设置标志
     *
     * @param singin 标志
     */
    public void setSingin(Byte singin) {
        this.singin = singin;
    }
}