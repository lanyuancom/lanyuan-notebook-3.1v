package com.lanyuan.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * 前台发送的是一个对象数组
 * 后台需要以下对象接受
 * 例如:
 * menus[0].id	    1
 * menus[0].level	1
 * menus[1].id	    3
 * menus[1].level	2
 * menus[2].id	    2
 * menus[2].level	3
 * 这个菜单数组对象要接收这个对象,必须在这里加一个list<Resourcess>
 * @author lanyuan
 * Email：mmm333zzz520@163.com
 * date：2014-12-12
 */
public class Params {

	
	private List<String> resId = new ArrayList<String>();
	private List<String> ids = new ArrayList<String>();
	private List<String> id = new ArrayList<String>();
	private List<String> rowId = new ArrayList<String>();


	public List<String> getResId() {
		return resId;
	}

	public void setResId(List<String> resId) {
		this.resId = resId;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public List<String> getRowId() {
		return rowId;
	}

	public void setRowId(List<String> rowId) {
		this.rowId = rowId;
	}

}
