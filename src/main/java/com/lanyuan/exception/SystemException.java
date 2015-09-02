package com.lanyuan.exception;

/**
 * 系统业务异常 throw new SystemException("XXXX")
 * @author lanyuan
 * @date 2014-12-10
 * @Email：mmm333zzz520@163.com
 */
public class SystemException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 2332608236621015980L;

	private String code;

	public SystemException() {
		super();
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String code, String message) {
		super(message);
		this.code = code;
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
