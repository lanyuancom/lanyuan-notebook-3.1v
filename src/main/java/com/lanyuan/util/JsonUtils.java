package com.lanyuan.util;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
  
public class JsonUtils { 
	
	/**
	 *  一个String字符串转换为json格式
	 *@descript
	 *@param s
	 *@return
	 *@author lijianning
	 *@date 2015年6月15日
	 *@version 1.0v
	 */
    public static String stringToJson(String s) { 
        if (s == null) { 
            return nullToJson(); 
        } 
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < s.length(); i++) { 
            char ch = s.charAt(i); 
            switch (ch) { 
            case '"': 
                sb.append("\\\""); 
                break; 
            case '\\': 
                sb.append("\\\\"); 
                break; 
            case '\b': 
                sb.append("\\b"); 
                break; 
            case '\f': 
                sb.append("\\f"); 
                break; 
            case '\n': 
                sb.append("\\n"); 
                break; 
            case '\r': 
                sb.append("\\r"); 
                break; 
            case '\t': 
                sb.append("\\t"); 
                break; 
            case '/': 
                sb.append("\\/"); 
                break; 
            default: 
                if (ch >= '\u0000' && ch <= '\u001F') { 
                    String ss = Integer.toHexString(ch); 
                    sb.append("\\u"); 
                    for (int k = 0; k < 4 - ss.length(); k++) { 
                        sb.append('0'); 
                    } 
                    sb.append(ss.toUpperCase()); 
                } else { 
                    sb.append(ch); 
                } 
            } 
        } 
        return sb.toString(); 
    } 
   
    public static String nullToJson() { 
        return ""; 
    } 
   
    /**
     * 一个obj对象转换为json格式
     *@descript
     *@param obj
     *@return
     *@author lijianning
     *@date 2015年6月15日
     *@version 1.0v
     */
    public static String objectToJson(Object obj) { 
        StringBuilder json = new StringBuilder(); 
        if (obj == null) { 
            json.append("\"\""); 
        } else if (obj instanceof Number) { 
            json.append(numberToJson((Number) obj)); 
        } else if (obj instanceof Boolean) { 
            json.append(booleanToJson((Boolean) obj)); 
        } else if (obj instanceof String) { 
            json.append("\"").append(stringToJson(obj.toString())).append("\""); 
        } else if (obj instanceof Object[]) { 
            json.append(arrayToJson((Object[]) obj)); 
        } else if (obj instanceof List) { 
            json.append(listToJson((List<?>) obj)); 
        } else if (obj instanceof Map) { 
            json.append(mapToJson((Map<?, ?>) obj)); 
        } else if (obj instanceof Set) { 
            json.append(setToJson((Set<?>) obj)); 
        } else { 
            json.append(beanToJson(obj)); 
        } 
        return json.toString(); 
    } 
   
    public static String numberToJson(Number number) { 
        return number.toString(); 
    } 
   
    public static String booleanToJson(Boolean bool) { 
        return bool.toString(); 
    } 
   
   /**
    *  一个bean对象转换为json格式
    *@descript
    *@param bean
    *@return
    *@author lijianning
    *@date 2015年6月15日
    *@version 1.0v
    */
    public static String beanToJson(Object bean) { 
        StringBuilder json = new StringBuilder(); 
        json.append("{"); 
        PropertyDescriptor[] props = null; 
        try { 
            props = Introspector.getBeanInfo(bean.getClass(), Object.class) 
                    .getPropertyDescriptors(); 
        } catch (IntrospectionException e) { 
        } 
        if (props != null) { 
            for (int i = 0; i < props.length; i++) { 
                try { 
                    String name = objectToJson(props[i].getName()); 
                    String value = objectToJson(props[i].getReadMethod() 
                            .invoke(bean)); 
                    json.append(name); 
                    json.append(":"); 
                    json.append(value); 
                    json.append(","); 
                } catch (Exception e) { 
                } 
            } 
            json.setCharAt(json.length() - 1, '}'); 
        } else { 
            json.append("}"); 
        } 
        return json.toString(); 
    } 
   
    /**

     *@descript
     *@param list
     *@return
     *@author lijianning
     *@date 2015年6月15日
     *@version 1.0v
     */
    public static String listToJson(List<?> list) { 
        StringBuilder json = new StringBuilder(); 
        json.append("["); 
        if (list != null && list.size() > 0) { 
            for (Object obj : list) { 
                json.append(objectToJson(obj)); 
                json.append(","); 
            } 
            json.setCharAt(json.length() - 1, ']'); 
        } else { 
            json.append("]"); 
        } 
        return json.toString(); 
    } 
   
    /**
     *  一个数组集合转换为json格式
     *@descript
     *@param array
     *@return
     *@author lijianning
     *@date 2015年6月15日
     *@version 1.0v
     */
    public static String arrayToJson(Object[] array) { 
        StringBuilder json = new StringBuilder(); 
        json.append("["); 
        if (array != null && array.length > 0) { 
            for (Object obj : array) { 
                json.append(objectToJson(obj)); 
                json.append(","); 
            } 
            json.setCharAt(json.length() - 1, ']'); 
        } else { 
            json.append("]"); 
        } 
        return json.toString(); 
    } 
   
   /**
    * 一个Map集合转换为json格式
    *@descript
    *@param map
    *@return
    *@author lijianning
    *@date 2015年6月15日
    *@version 1.0v
    */
    public static String mapToJson(Map<?, ?> map) { 
        StringBuilder json = new StringBuilder(); 
        json.append("{"); 
        if (map != null && map.size() > 0) { 
            for (Object key : map.keySet()) { 
                json.append(objectToJson(key)); 
                json.append(":"); 
                json.append(objectToJson(map.get(key))); 
                json.append(","); 
            } 
            json.setCharAt(json.length() - 1, '}'); 
        } else { 
            json.append("}"); 
        } 
        return json.toString(); 
    } 
   
   /**
    * 一个Set集合转换为json格式 
    *@descript
    *@param set
    *@return
    *@author lijianning
    *@date 2015年6月15日
    *@version 1.0v
    */
    public static String setToJson(Set<?> set) { 
        StringBuilder json = new StringBuilder(); 
        json.append("["); 
        if (set != null && set.size() > 0) { 
            for (Object obj : set) { 
                json.append(objectToJson(obj)); 
                json.append(","); 
            } 
            json.setCharAt(json.length() - 1, ']'); 
        } else { 
            json.append("]"); 
        } 
        return json.toString(); 
    } 
    /**
     * json字符串转换为List
     *@descript
     *@param jsonStr
     *@return
     *@author lijianning
     *@date 2015年6月15日
     *@version 1.0v
     */
    public static List<Map<String, Object>> parseJSONList(String jsonStr){  
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);  
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
        Iterator<JSONObject> it = jsonArr.iterator();  
        while(it.hasNext()){  
            JSONObject JSON = it.next();  
            list.add(parseJSONMap(JSON.toString()));  
        }  
        return list;  
    }  
      
     /**
      * json字符串转换为map
      *@descript
      *@param jsonStr
      *@return
      *@author lijianning
      *@date 2015年6月15日
      *@version 1.0v
      */
    public static Map<String, Object> parseJSONMap(String jsonStr){  
        Map<String, Object> map = new HashMap<String, Object>();  
        try {
			//最外层解析  
			JSONObject json = JSONObject.fromObject(jsonStr);
			for (Object k : json.keySet()) {
				Object v = json.get(k);
				//如果内层还是数组的话，继续解析  
				if (v instanceof JSONArray) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Iterator<JSONObject> it = ((JSONArray) v).iterator();
					while (it.hasNext()) {
						JSONObject JSON = it.next();
						list.add(parseJSONMap(JSON.toString()));
					}
					map.put(k.toString(), list);
				} else {
					map.put(k.toString(), v);
				}
			} 
		} catch (Exception e) {
			map.put("exception", jsonStr);
		}
		return map;  
    }  
      
     /**
      * 根据一个url地址.获取json数据.转换为List
      *@descript
      *@param url
      *@return
      *@author lijianning
      *@date 2015年6月15日
      *@version 1.0v
      */
    public static List<Map<String, Object>> getListByUrl(String url){  
        try {  
            //通过HTTP获取JSON数据  
            InputStream in = new URL(url).openStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
            StringBuilder sb = new StringBuilder();  
            String line;  
            while((line=reader.readLine())!=null){  
                sb.append(line);  
            }  
            return parseJSONList(sb.toString());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
     /**
      * 根据一个url地址.获取json数据.转换为MAP
      *@descript
      *@param url
      *@return
      *@author lijianning
      *@date 2015年6月15日
      *@version 1.0v
      */
    public static Map<String, Object> getMapByUrl(String url){  
        try {  
            //通过HTTP获取JSON数据  
            InputStream in = new URL(url).openStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
            StringBuilder sb = new StringBuilder();  
            String line;  
            while((line=reader.readLine())!=null){  
                sb.append(line);  
            }  
            return parseJSONMap(sb.toString());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
}