package com.cn.vanke.page.dialect;

/**
 * 
 * 功能说明：PostgreSQL 数据库查询分页
 * 
 * PostgreSQLDialect.java
 * Copyright (C)1984-2017  深圳万科物业发展有限公司  All rights reserved.
 */
public class PostgreSQLDialect implements Dialect {

	@Override
	public String buildPageSQL(String sql, int pageIndex, int pageSize) {
		StringBuilder pageSql = new StringBuilder(sql.length() + 30);
		long startIndex = (long)pageIndex*pageSize;
		pageSql.append(sql).append(" limit ").append(pageSize).append(" offset ").append(startIndex);
		return pageSql.toString();
	}

}
