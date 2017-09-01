/**
 * 
 */
package com.cn.vanke.exception;

/**
 * 
 * 功能说明：通用组件异常
 * 
 * BasicAppException.java
 */
public class BasicAppException extends AppException {

	private static final long serialVersionUID = -3632238422563829997L;

	public BasicAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public BasicAppException(String message) {
		super(message);
	}

	public BasicAppException(Throwable cause) {
		super(cause);
	}
}
