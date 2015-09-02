package com.lanyuan.util;

import java.text.SimpleDateFormat; 
import java.util.Date; 

import org.springframework.beans.propertyeditors.CustomDateEditor; 
import org.springframework.web.bind.WebDataBinder; 
import org.springframework.web.bind.support.WebBindingInitializer; 
import org.springframework.web.context.request.WebRequest; 

/**
 * spring3 mvc 的日期传递[前台-后台]bug: 
 * org.springframework.validation.BindException 
 * 的解决方式.包括xml的配置 
 *  new SimpleDateFormat("yyyy-MM-dd");  这里的日期格式必须与提交的日期格式一致
 * @author lanyuan
 * Email：mmm333zzz520@163.com
 * date：2014-11-20
 */
public class SpringMVCDateConverter implements WebBindingInitializer { 

  public void initBinder(WebDataBinder binder, WebRequest request) { 
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
      binder.registerCustomEditor(Date.class, new CustomDateEditor(df,true));   
  } 

} 
