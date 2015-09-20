package com.lanyuan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.lanyuan.annotation.TableSeg;

public class ConfigUtils {
	private final Logger logger = Logger.getLogger(ConfigUtils.class);
	/**
	 * 初始化数据库表字段到缓存
	 */
	public void initTableField() {
		// 记录总记录数
		Statement countStmt = null;
		ResultSet rs = null;
		Connection connection = null; // 表示数据库的连接对象
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Properties pro = PropertiesUtils.getjdbcProperties();
			Class.forName(pro.getProperty("jdbc.driverClass")); // 1、使用CLASS
			String url = pro.getProperty("jdbc.url");
			String db = url.substring(url.lastIndexOf("/")+1);
			if(db.indexOf("?")>-1){
				db=db.substring(0, db.indexOf("?"));
			}
			connection = DriverManager.getConnection(url, pro.getProperty("jdbc.username"),
					pro.getProperty("jdbc.password")); // 2、连接数据库
			String packageName = "com.lanyuan.entity";
			// List<String> classNames = getClassName(packageName);
			List<String> classNames = ClassUtil.getClassName(packageName, false);
			String tabs = "";
			if (classNames != null) {
				for (String className : classNames) {
					Class<?> clazz = Class.forName(className);
					boolean flag = clazz.isAnnotationPresent(TableSeg.class); // 某个类是不是存在TableSeg注解
					if (flag) {
						TableSeg table = (TableSeg) clazz.getAnnotation(TableSeg.class);
						tabs+="'"+table.tableName()+"',";
						map.put(table.tableName(), table.id());
					} 
				}
			}
			tabs=Common.trimComma(tabs);
			//尽量减少对数据库/IO流操作,一次查询所有表的的字段
			String sql = "select TABLE_NAME,group_concat(COLUMN_NAME) COLUMN_NAME from information_schema.columns where table_name in ("+tabs+") and table_schema = '"+db+"'  GROUP BY TABLE_NAME" ;
			countStmt = connection.createStatement();
			rs = countStmt.executeQuery(sql);
			while (rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("field", rs.getString("COLUMN_NAME"));
				String ble =rs.getString("TABLE_NAME");//表名
				m.put("column_key", map.get(ble));//获取表的主键
				EhcacheUtils.put(ble, m);//某表对应的主键和字段放到缓存
			}
		} catch (Exception e) {
			logger.error(" 初始化数据失败,没法加载表字段到缓存 -->> "+e.fillInStackTrace());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				countStmt.close();
			} catch (Exception e) {
			}
		}
	}
}
