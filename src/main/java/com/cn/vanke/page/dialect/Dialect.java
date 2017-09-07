/**
 * 
 */
package com.cn.vanke.page.dialect;

/**
 * 功能说明：数据库方言接口，目前只处理分页
 * 
 * Dialect.java Copyright (C)1984-2017 深圳万科物业发展有限公司 All rights reserved.
 */
public interface Dialect {

	/**
	 * @param sql       原SQL
	 * @param pageIndex 第几页(0:表示第一页)
	 * @param pageSize  每页记录条数
	 * @return
	 */
	public String buildPageSQL(String sql, int pageIndex, int pageSize);

	public enum Type {
		ORACLE, MYSQL, MARIADB, SQLITE, POSTGRESQL, SQLSERVER, HSQLDB
	}
}
