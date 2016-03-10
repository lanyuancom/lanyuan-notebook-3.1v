/*
 *    Copyright 2009-2012 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.Configuration;

import com.lanyuan.plugin.PagePlugin;
import com.lanyuan.plugin.PageView;
import com.lanyuan.util.Common;
import com.lanyuan.util.EhcacheUtils;

/**
 * @author Clinton Begin
 */
public class Plugin implements InvocationHandler {

	private Object target;
	private Interceptor interceptor;
	private Map<Class<?>, Set<Method>> signatureMap;

	private Plugin(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
		this.target = target;
		this.interceptor = interceptor;
		this.signatureMap = signatureMap;
	}

	public static Object wrap(Object target, Interceptor interceptor) {
		Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
		Class<?> type = target.getClass();
		Class<?>[] interfaces = getAllInterfaces(type, signatureMap);
		if (interfaces.length > 0) {
			return Proxy.newProxyInstance(type.getClassLoader(), interfaces,
					new Plugin(target, interceptor, signatureMap));
		}
		return target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			Set<Method> methods = signatureMap.get(method.getDeclaringClass());
			if (methods != null && methods.contains(method)) {
				return interceptor.intercept(new Invocation(target, method, args));
			}
			return method.invoke(target, args);
		} catch (Exception e) {
			throw ExceptionUtil.unwrapThrowable(e);
		}
	}

	private static Map<Class<?>, Set<Method>> getSignatureMap(Interceptor interceptor) {
		Intercepts interceptsAnnotation = interceptor.getClass().getAnnotation(Intercepts.class);
		if (interceptsAnnotation == null) { // issue #251
			throw new PluginException(
					"No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
		}
		Signature[] sigs = interceptsAnnotation.value();
		Map<Class<?>, Set<Method>> signatureMap = new HashMap<Class<?>, Set<Method>>();
		for (Signature sig : sigs) {
			Set<Method> methods = signatureMap.get(sig.type());
			if (methods == null) {
				methods = new HashSet<Method>();
				signatureMap.put(sig.type(), methods);
			}
			try {
				Method method = sig.type().getMethod(sig.method(), sig.args());
				methods.add(method);
			} catch (NoSuchMethodException e) {
				throw new PluginException(
						"Could not find method on " + sig.type() + " named " + sig.method() + ". Cause: " + e, e);
			}
		}
		return signatureMap;
	}

	private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
		Set<Class<?>> interfaces = new HashSet<Class<?>>();
		while (type != null) {
			for (Class<?> c : type.getInterfaces()) {
				if (signatureMap.containsKey(c)) {
					interfaces.add(c);
				}
			}
			type = type.getSuperclass();
		}
		return interfaces.toArray(new Class<?>[interfaces.size()]);
	}

	@SuppressWarnings("unchecked")
	public static final String joinSql(Connection connection, MappedStatement mappedStatement, BoundSql boundSql,
			Map<String, Object> formMap, List<Object> formMaps) throws Exception {
		Object table = null;
		String sql = "";
		Map<String, Object> mapfield = null;
		String field = null;
		if (null != formMap) {
			table = formMap.get("ly_table");
			mapfield = (Map<String, Object>) EhcacheUtils.get(table);
			field = mapfield.get("field").toString();
			sql = " select " + field + " from " + String.valueOf(table);
		}
		String sqlId = mappedStatement.getId();
		sqlId = sqlId.substring(sqlId.lastIndexOf(".") + 1);
		if (Configuration.FINDBYWHERE.equals(sqlId)) {
			if (null != formMap.get("where") && !StringUtils.isBlank(formMap.get("where").toString())) {
				sql += " " + formMap.get("where").toString();
			}
		} else if (Configuration.FINDBYPAGE.equals(sqlId)) {
			String[] fe = field.split(",");
			String param = "";
			for (String string : fe) {
				if (formMap.containsKey(string)) {
					Object v = formMap.get(string);
					String sf = v.toString();
					if (sf.indexOf("%") > -1) {
						param += " and " + string + " like '" + v + "'";
					} else {
						if (sf.indexOf(",") > -1)// 处理模糊查询
						{

							StringBuffer sbuffer = new StringBuffer();
							String[] se = sf.split(",");
							for (String sst : se) {
								if (0 >= sbuffer.length()) {
									sbuffer.append(" and (");
									sbuffer.append(" " + string + " = '" + sst + "'  ");
								} else {
									sbuffer.append(" or " + string + " = '" + sst + "'  ");
								}

							}
							sbuffer.append(")");
							param += sbuffer.toString();
						} else {
							param += " and " + string + " = '" + v + "'";
						}

					}
				}
			}

			Object where = formMap.get("where");
			if (null != where) {
				String sf = where.toString();
				if (StringUtils.isNotBlank(sf)) {
					if (sf.indexOf("null") == -1) {
						param += sf;
					}
				}

			}
			if (StringUtils.isNotBlank(param)) {
				param = param.substring(param.indexOf("and") + 4);
				sql += " where " + param;
			}

			Object by = formMap.get("orderby");
			if (null != by) {
				sql += " " + by;
			}
			Object paging = formMap.get("paging");
			if (null == paging) {
				throw new Exception("调用findByPage接口,必须传入PageView对象! formMap.set(\"paging\", pageView);");
			} else if (StringUtils.isBlank(paging.toString())) {
				throw new Exception("调用findByPage接口,必须传入PageView对象! formMap.set(\"paging\", pageView);");
			}
			PageView pageView = (PageView) paging;
			setCount(sql, connection, boundSql, pageView);
			sql = PagePlugin.generatePagesSql(sql, pageView);
		} else if (Configuration.DELETEBYNAMES.equals(sqlId)) {
			sql = "delete from " + table.toString() + " where ";
			String param = "";
			for (Entry<String, Object> entry : formMap.entrySet()) {
				if (!"ly_table".equals(entry.getKey()) && null != entry.getValue() && !"_t".equals(entry.getKey()))
					param += " and " + entry.getKey() + " in (" + entry.getValue() + ")";
			}
			if (StringUtils.isNotBlank(param)) {
				param = param.substring(param.indexOf("and") + 4);
				sql += param;
			}
		} else if (Configuration.DELETEBYATTRIBUTE.equals(sqlId)) {
			sql = "delete from " + table.toString() + " where " + formMap.get("key");
			if (null != formMap.get("value")) {
				sql += " in (" + formMap.get("value") + ")";
			}
		} else if (Configuration.FINDBYNAMES.equals(sqlId)) {
			String[] fe = field.split(",");
			String param = "";
			for (String string : fe) {
				if (formMap.containsKey(string)) {
					Object v = formMap.get(string);
					if (v.toString().indexOf("%") > -1)// 处理模糊查询
					{
						param += " and " + string + " like '" + v + "'";
					} else {
						param += " and " + string + " = '" + v + "'";
					}
				}
			}
			if (StringUtils.isNotBlank(param)) {
				param = param.substring(param.indexOf("and") + 4);
				sql += " where " + param;

			}
			Object by = formMap.get("orderby");
			if (null != by) {
				sql += " " + by;
			}
		} else if (Configuration.FINDBYATTRIBUTE.equals(sqlId)) {
			sql = "select * from " + table.toString() + " where " + formMap.get("key");
			if (null != formMap.get("value") && formMap.get("value").toString().indexOf("%") > -1)// 处理模糊查询
			{
				sql += " LIKE '" + formMap.get("value") + "'";
			} else {
				Object v = formMap.get("value");
				sql += " in ('" + v + "')";

			}
		} else if (Configuration.ADDENTITY.equals(sqlId)) {
			String[] fe = field.split(",");
			String fieldString = "";
			String fieldValues = "";
			for (String string : fe) {
				Object v = formMap.get(string);
				if (null != v && !StringUtils.isBlank(v.toString())) {
					fieldString += string + ",";
					fieldValues += "'" + v + "',";
				}
			}
			sql = "insert into " + table.toString() + " (" + ResolverUtil.trimComma(fieldString) + ")  values ("
					+ ResolverUtil.trimComma(fieldValues) + ")";
		} else if (Configuration.EDITENTITY.equals(sqlId)) {
			String[] fe = field.split(",");
			String fieldString = "";
			String where = "";
			for (String string : fe) {
				Object v = formMap.get(string);
				if (null != v && !StringUtils.isBlank(v.toString())) {
					String key = mapfield.get(Configuration.COLUMN_KEY).toString();
					if (!StringUtils.isBlank(key)) {
						if (key.equals(string)) {
							where = "WHERE " + key + " = '" + v + "'";
						} else {
							fieldString += string + "='" + v + "',";
						}
					} else {
						throw new NullPointerException("update操作没有找到主键!");
					}

				}
			}

			sql = "update " + table.toString() + " set " + ResolverUtil.trimComma(fieldString) + " " + where;
		} else if (Configuration.FINDBYFRIST.equals(sqlId)) {
			sql = "select * from " + table.toString() + " where " + formMap.get("key");
			if (null != formMap.get("value") && !"".equals(formMap.get("value").toString())) {
				sql += " = '" + formMap.get("value") + "'";
			} else {
				throw new Exception(sqlId + " 调用公共方法异常!,传入参数错误！");
			}

		} else if (Configuration.BATCHSAVE.equals(sqlId)) {
			if (null != formMaps && formMaps.size() > 0) {
				table = Common.toHashMap(formMaps.get(0)).get(Configuration.LY_TABLE);
				mapfield = (Map<String, Object>) EhcacheUtils.get(table);
				field = mapfield.get(Configuration.FIELD).toString();
			}
			sql = "insert into " + table.toString();
			String fieldString = "";
			String fs = "";
			String fd = "";
			String fieldValues = "";
			String fvs = "";
			for (int i = 0; i < formMaps.size(); i++) {
				Object object = formMaps.get(i);
				@SuppressWarnings("unchecked")
				Map<String, Object> froMmap = (Map<String, Object>) object;
				String[] fe = field.split(",");
				for (String string : fe) {
					Object v = froMmap.get(string);
					if (null != v && !StringUtils.isBlank(v.toString())) {
						fieldString += string + ",";
						fieldValues += "'" + v + "',";
					}
				}
				if (i == 0) {
					fd = fieldString;
				}
				fvs += "(" + ResolverUtil.trimComma(fieldValues) + "),";
				fs += " insert into " + table.toString() + " (" + ResolverUtil.trimComma(fieldString) + ")  values ("
						+ ResolverUtil.trimComma(fieldValues) + ");";
				fieldValues = "";
				fieldString = "";
			}
			String v = ResolverUtil.trimComma(fvs);
			sql = "insert into " + table.toString() + " (" + ResolverUtil.trimComma(fd) + ")  values "
					+ ResolverUtil.trimComma(fvs) + "";
			String[] vs = v.split("\\),");
			boolean b = false;
			for (String string : vs) {
				if (string.split(",").length != fd.split(",").length) {
					b = true;
				}
			}
			if (b) {
				sql = fs.substring(0, fs.length() - 1);
			}

		} else {
			throw new Exception("调用公共方法异常!");
		}
		return sql;
	}

	public static void setCount(String sql, Connection connection, BoundSql boundSql, PageView pageView)
			throws SQLException {
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			String countSql = "";
			try {
				countSql = "select count(1) from " + suffixStr(removeOrderBys(sql));
				countStmt = connection.prepareStatement(countSql);
				rs = countStmt.executeQuery();
			} catch (Exception e) {
				PagePlugin.logger.error(countSql + " 统计Sql出错,自动转换为普通统计Sql语句!");
				countSql = "select count(1) from (" + sql + ") tmp_count";
				countStmt = connection.prepareStatement(countSql);
				rs = countStmt.executeQuery();
			}
			int count = 0;
			if (rs.next()) {
				count = ((Number) rs.getObject(1)).intValue();
			}
			pageView.setRowCount(count);
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

	public static String suffixStr(String toSql) {
		int sun = toSql.indexOf("from");
		String f1 = toSql.substring(sun - 1, sun);
		String f2 = toSql.substring(sun + 4, sun + 5);
		if (f1.trim().isEmpty() && f2.trim().isEmpty()) {
			String s1 = toSql.substring(0, sun);
			int s0 = s1.indexOf("(");
			if (s0 > -1) {
				int se1 = s1.indexOf("select");
				if (s0 < se1) {
					if (se1 > -1) {
						String ss1 = s1.substring(se1 - 1, se1);
						String ss2 = s1.substring(se1 + 6, se1 + 7);
						if (ss1.trim().isEmpty() && ss2.trim().isEmpty()) {
							return suffixStr(toSql.substring(sun + 5));
						}
					}
				}
				int se2 = s1.indexOf("(select");
				if (se2 > -1) {
					String ss2 = s1.substring(se2 + 7, se2 + 8);
					if (ss2.trim().isEmpty()) {
						return suffixStr(toSql.substring(sun + 5));
					}
				}
				if (se1 == -1 && se2 == -1) {
					return toSql.substring(sun + 5);
				} else {
					toSql = toSql.substring(sun + 5);
				}
			} else {
				toSql = toSql.substring(sun + 5);
			}
		}
		return toSql;
	}

	private static String removeOrderBys(String toSql) {
		int sun = toSql.indexOf("order");
		if (sun > -1) {
			String f1 = toSql.substring(sun - 1, sun);
			String f2 = toSql.substring(sun + 5, sun + 5);
			if (f1.trim().isEmpty() && f2.trim().isEmpty()) {
				String zb = toSql.substring(sun);
				int s0 = zb.indexOf(")");
				if (s0 > -1) {
					String s1 = toSql.substring(0, sun);
					String s2 = zb.substring(s0);
					return removeOrderBys(s1 + s2);
				} else {
					toSql = toSql.substring(0, sun);
				}
			}
		}
		return toSql;
	}
}
