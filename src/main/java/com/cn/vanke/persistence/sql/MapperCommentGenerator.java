package com.cn.vanke.persistence.sql;

import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * 
 * 功能说明：注释以及字段注解处理类
 * 
 * MapperCommentGenerator.java
 */
public class MapperCommentGenerator implements  CommentGenerator {
	private Properties properties;
	
	/**
	 * 存在一个疑问,通过实现CommentGenerator接口的方法addConfigurationProperties，获取不到Properties中配置的属性值
	 * 先默认为true,先自身在代码设置isSupportSwaggerApi的值
	 */
	private boolean isSupportSwaggerApi;
	
	public MapperCommentGenerator() {
		super();
		properties = new Properties();
		isSupportSwaggerApi = true;
	}

	/**
	 * 删除标记
	 * @param javaElement
	 * @param markAsDoNotDelete
	 */
	protected void addJavadocTag(JavaElement javaElement,
			boolean markAsDoNotDelete) {
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge");
		}
		javaElement.addJavaDocLine(sb.toString());
	}

	/**
	 * Example使用
	 * 
	 * @param innerClass
	 * @param introspectedTable
	 */
	public void addClassComment(InnerClass innerClass,
			IntrospectedTable introspectedTable) {
	}

	public void addEnumComment(InnerEnum innerEnum,
			IntrospectedTable introspectedTable) {
	}

	/**
	 * 给字段添加数据库备注
	 * 
	 * @param field
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	public void addFieldComment(Field field,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			field.addJavaDocLine("/**");
			StringBuilder sb = new StringBuilder();
			sb.append(" * ");
			sb.append(introspectedColumn.getRemarks());
			field.addJavaDocLine(sb.toString());
			field.addJavaDocLine(" */");
		}
		// 添加注解
		if (field.isTransient()) {
			// @Column
			field.addAnnotation("@Transient");
		}
		for (IntrospectedColumn column : introspectedTable
				.getPrimaryKeyColumns()) {
			if (introspectedColumn == column) {
				field.addAnnotation("@Id");
				break;
			}
		}
		String column = introspectedColumn.getActualColumnName();
		if (StringUtility.stringContainsSpace(column)
				|| introspectedTable.getTableConfiguration()
						.isAllColumnDelimitingEnabled()) {
			column = introspectedColumn.getContext().getBeginningDelimiter()
					+ column
					+ introspectedColumn.getContext().getEndingDelimiter();
		}
		field.addAnnotation("@Column(name = \"" + column + "\")");
		//生成swaggerApi支持
		if(this.isSupportSwaggerApi){
			field.addAnnotation("@JsonProperty(value = \"" + column + "\")");
			field.addAnnotation("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\")");
		}
		
		//判断是否自增长或者序列号
		if (introspectedColumn.isIdentity()) {
			if (introspectedTable.getTableConfiguration().getGeneratedKey()
					.getRuntimeSqlStatement().equals("JDBC")) {
				field.addAnnotation("@GeneratedValue(generator = \"JDBC\")");
			} else {
				field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
			}
		} else if (introspectedColumn.isSequenceColumn()) {
			field.addAnnotation("@SequenceGenerator(name=\"\",sequenceName=\""
					+ introspectedTable.getTableConfiguration()
							.getGeneratedKey().getRuntimeSqlStatement() + "\")");
		}
	}

	/**
	 * Example使用
	 * 
	 * @param field
	 * @param introspectedTable
	 */
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
	}

	/**
	 * @param method
	 * @param introspectedTable
	 */
	public void addGeneralMethodComment(Method method,
			IntrospectedTable introspectedTable) {
	}

	/**
	 * getter方法注释
	 * 
	 * @param method
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	public void addGetterComment(Method method,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**");
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" * 获取");
			sb.append(introspectedColumn.getRemarks());
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" *");
		}
		sb.setLength(0);
		sb.append(" * @return ");
		sb.append(introspectedColumn.getActualColumnName());
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" - ");
			sb.append(introspectedColumn.getRemarks());
		}
		method.addJavaDocLine(sb.toString());
		method.addJavaDocLine(" */");
	}

	/**
	 * setter方法注释
	 * 
	 * @param method
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	public void addSetterComment(Method method,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		StringBuilder sb = new StringBuilder();
		method.addJavaDocLine("/**");
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" * 设置");
			sb.append(introspectedColumn.getRemarks());
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" *");
		}
		Parameter parm = method.getParameters().get(0);
		sb.setLength(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			sb.append(" ");
			sb.append(introspectedColumn.getRemarks());
		}
		method.addJavaDocLine(sb.toString());
		method.addJavaDocLine(" */");
	}

	/**
	 * Example使用
	 * 
	 * @param innerClass
	 * @param introspectedTable
	 * @param markAsDoNotDelete
	 */
	public void addClassComment(InnerClass innerClass,
			IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
	}
	public void addJavaFileComment(CompilationUnit compilationUnit) {
	}

	/**
	 * xml中的注释
	 * @param xmlElement
	 */
	public void addComment(XmlElement xmlElement) {
		return;
	}

	public void addRootComment(XmlElement rootElement) {
		return;
	}

	/***
	 * 注入配置xml属性
	 */
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);
		//存在的疑问,通过实现的接口无法获取到配置的值，默认设置为true
		//this.isSupportSwaggerApi = isTrue(properties.getProperty("isSupportSwaggerApi"));
	}
}
