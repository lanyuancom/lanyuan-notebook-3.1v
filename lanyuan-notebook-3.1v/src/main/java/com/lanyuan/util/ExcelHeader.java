package com.lanyuan.util;
/**
 * 用来存储Excel标题的对象，通过该对象可以获取标题和方法的对应关系
 * @author lanyuan
 * Email：mmm333zzz520@163.com
 * date：2014-4-18
 */
public class ExcelHeader implements Comparable<ExcelHeader> {

	private String title; // 标题名称
	private int order; // 标题顺序
	private int width; // 宽度
	private String methodName; // 对应方法名称

	
	
	public ExcelHeader(String title, int order, int width, String methodName) {
		super();
		this.width = width;
		this.title = title;
		this.order = order;
		this.methodName = methodName;
	}

	public int compareTo(ExcelHeader o) {
		return order > o.order ? 1 : (order < o.order? -1 : 0);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
