/**
 * 
 */
package com.cn.vanke.page.dialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.vanke.util.StringUtils;

/**
 * 功能说明：数据库方言工厂类
 * 
 * DialectFactory.java
 * Copyright (C)1984-2017  深圳万科物业发展有限公司  All rights reserved.
 */
public class DialectFactory {
	private static final Logger logger = LoggerFactory.getLogger(DialectFactory.class);

	public static Dialect driveDialect(String databaseType) {
		Dialect dialect = null;
		if (StringUtils.isEmpty(databaseType)) {
			logger.error("the dialect is not defined。");
			return dialect;
		}
		Dialect.Type dbType = Dialect.Type.valueOf(databaseType);
		if (dbType != null) {
			switch (dbType) {
			case ORACLE:
				dialect = new OracleDialect();
				break;
			case MYSQL:
				dialect = new MySqlDialect();
			case MARIADB:
			case SQLITE:
				dialect = new MySqlDialect();
				break;
			case HSQLDB:
				dialect = new HsqlDBDialect();
				break;
			case POSTGRESQL:
				dialect = new PostgreSQLDialect();
				break;
			case SQLSERVER:
				dialect = new SqlServerDialect();
				break;
			}
		}
		return dialect;
	}

}
