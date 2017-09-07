/**
 * 
 */
package com.cn.vanke.page.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 功能说明： 封装分页查询条件
 * 
 * PageParams.java
 * Copyright (C)1984-2017  深圳万科物业发展有限公司  All rights reserved.
 */
@ApiModel("分页信息模板")
public class Pager<T> implements Serializable{
	
	private static final long serialVersionUID = 4820194350910329815L;
	@ApiModelProperty(value = "页号",dataType = "Integer")
	private int pageIndex = 1;
	@ApiModelProperty(value = "每页记录数",dataType = "Integer")
	private int pageSize = 10;
	@ApiModelProperty(value = "封装的数据列表")
	private List<T> data;
	@ApiModelProperty(value = "排序字段")
	private String sortField;
	@ApiModelProperty(value = "排序顺序")
	private String sortOrder = "asc";
	@ApiModelProperty(value = "查询条件")
	private Map<String,Object> queryParams = new HashMap<String,Object>();
	@ApiModelProperty(value = "总记录数",dataType = "Long")
	private long totalCount;
	@ApiModelProperty(value = "总页数",dataType = "Long")
	private long totalPage;
	@ApiModelProperty(value = "当前页",dataType = "Integer")
	private int currentPage;
	
	public Pager(){
		super();
	}
	
	public Pager(List<T> data,int totalCount,int pageSize){
		super();
		this.totalCount = totalCount;
		this.data = data;
		this.pageSize = pageSize;
	}
	
	public Pager(List<T> data,int totalCount,int currentPage,int pageSize){
		super();
		this.totalCount = totalCount;
		this.data = data;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public Pager(int currentPage, int pageSize){
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}
	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPage() {
		try{
			if(this.pageSize == 0){
				return 0L;
			}else if(this.totalCount % this.pageSize == 0){
				this.totalPage = Long.valueOf(String.valueOf(this.totalCount/this.pageSize));
			}else{
				this.totalPage = Long.valueOf(String.valueOf((this.totalCount - this.totalCount % this.pageSize)/this.pageSize)) + 1L;
			}
			return this.totalPage;
		}catch (Exception ex){
			ex.printStackTrace();
			return 0L;
		}
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
