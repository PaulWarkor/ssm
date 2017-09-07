package com.cn.vanke.page.dialect;

/**
 * 
 * 功能说明：SQLServer实现SQL分页(支持sql2005及以上)
 * 
 * SqlServerDialect.java
 * Copyright (C)1984-2017  深圳万科物业发展有限公司  All rights reserved.
 */
public class SqlServerDialect implements Dialect {

	@Override
	public String buildPageSQL(String sql, int pageIndex, int pageSize) {
		String loweredString = sql.toLowerCase();
		StringBuilder pagingBuilder = new StringBuilder();
		String orderby = getOrderByPart(sql);
		String distinctStr = "";
		String sqlPartString = sql;
		if (loweredString.trim().startsWith("select")) {
			int index = 6;
			if (loweredString.startsWith("select distinct")) {
				distinctStr = "DISTINCT ";
				index = 15;
			}
			sqlPartString = sqlPartString.substring(index);
		}
		pagingBuilder.append(sqlPartString);
		// if no ORDER BY is specified use fake ORDER BY field to avoid errors
		if (orderby == null || orderby.length() == 0) {
			orderby = "ORDER BY CURRENT_TIMESTAMP";
		 }
		StringBuilder pageSql = new StringBuilder(sql.length() + 200);
		long startIndex = (long)pageIndex*pageSize;
		long endIndex = startIndex+pageSize;
		pageSql.append("WITH query AS (SELECT ")
				.append(distinctStr)
				.append("TOP 100 PERCENT ")
				.append(" ROW_NUMBER() OVER (")
				.append(orderby)
				.append(") as __row_number__, ")
				.append(pagingBuilder)
				.append(") SELECT * FROM query WHERE __row_number__ > ")
				.append(startIndex)
				.append(" AND __row_number__ <= ")
				.append(endIndex)
				.append(" ORDER BY __row_number__");
     
		return pageSql.toString();
	}
	
	/**
	 * 获取order by 后面的部分
	 * @param sql
	 * @return
	 */
	private static String getOrderByPart(String sql) {
		String loweredString = sql.toLowerCase();
		int orderByIndex = loweredString.indexOf("order by");
		if (orderByIndex != -1) {
			// if we find a new "order by" then we need to ignore
			// the previous one since it was probably used for a subquery
			return sql.substring(orderByIndex);
		} else {
			return "";
		}
	}

}
