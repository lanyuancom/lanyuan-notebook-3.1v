package com.lanyuan.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.lanyuan.annotation.TableSeg;
import com.lanyuan.mapper.base.BaseMapper;

public class ConfigUtils {
	private final Logger logger = Logger.getLogger(ConfigUtils.class);
	/**
	 * 初始化数据库表字段到缓存
	 */
	public void initTableField(BaseMapper baseMapper) {
		// 记录总记录数
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Properties pro = PropertiesUtils.getjdbcProperties();
			Class.forName(pro.getProperty("jdbc.driverClass")); // 1、使用CLASS
			String url = pro.getProperty("jdbc.url");
			String db = url.substring(url.lastIndexOf("/")+1);
			if(db.indexOf("?")>-1){
				db=db.substring(0, db.indexOf("?"));
			}
			String packageName = "com.lanyuan.entity";
			// List<String> classNames = getClassName(packageName);
			List<String> classNames = ClassUtil.getClassName(packageName, true);//true包含子目录
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
			HashMap<String, Object> tm = new HashMap<String, Object>();
			tm.put("table_name", tabs);
			tm.put("database_name","'"+db+"'");
			 List<HashMap<String, Object>> lh = baseMapper.initTableField(tm);
			 for (HashMap<String, Object> hashMap : lh) {
				 Map<String, Object> m = new HashMap<String, Object>();
					m.put("field", hashMap.get("COLUMN_NAME"));
					String ble =hashMap.get("TABLE_NAME").toString();//表名
					m.put("column_key", map.get(ble));//获取表的主键
					EhcacheUtils.put(ble, m);//某表对应的主键和字段放到缓存
			}
		} catch (Exception e) {
			logger.error(" 初始化数据失败,没法加载表字段到缓存 -->> "+e.fillInStackTrace());
			e.printStackTrace();
		} 
	}
}
