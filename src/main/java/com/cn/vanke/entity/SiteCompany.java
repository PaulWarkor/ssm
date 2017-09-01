package com.cn.vanke.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;
import javax.persistence.*;

/**
 *
 * SiteCompany.java
 * 
 * @Author : zengh05
 * @DateTime : 2017-08-30 15:20:37
 * 
 * Copyright (C)1984-2017  深圳万科物业发展有限公司  All rights reserved.
 */
@ApiModel("表名称 : site_vn")
@Table(name = "site_vn")
public class SiteCompany {
    @Id
    @Column(name = "id")
    @JsonProperty(value = "id")
    @ApiModelProperty(value = "主键ID")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "site_id")
    @JsonProperty(value = "site_id")
    @ApiModelProperty(value = "项目编码")
    private String siteId;

    @Column(name = "vn_id")
    @JsonProperty(value = "vn_id")
    @ApiModelProperty(value = "供应商编码")
    private String vnId;

    @Column(name = "company")
    @JsonProperty(value = "company")
    @ApiModelProperty(value = "供应商名称")
    private String company;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return site_id
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @param siteId
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * @return vn_id
     */
    public String getVnId() {
        return vnId;
    }

    /**
     * @param vnId
     */
    public void setVnId(String vnId) {
        this.vnId = vnId;
    }

    /**
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }
}