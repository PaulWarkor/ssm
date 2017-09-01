package com.cn.vanke.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.cn.vanke.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel("Bean信息模板")
public class EntityTemplate {
	@ApiModelProperty(value = "用户姓名", required = true)
	private String userName;
	@ApiModelProperty(value = "用户编号", required = true)
	private String userId;
	@ApiModelProperty(value = "用户年龄")
	private Integer age;
	@ApiModelProperty(value = "用户出生日期")
	@JsonFormat(pattern = DateUtils.TO_SECOND)
	private Date birthday;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "EntityTemplate [userName=" + userName + ", userId=" + userId
				+ ", age=" + age + ", birthday=" + DateUtils.formatToString(DateUtils.TO_SECOND_CN, birthday) + "]";
	}

}
