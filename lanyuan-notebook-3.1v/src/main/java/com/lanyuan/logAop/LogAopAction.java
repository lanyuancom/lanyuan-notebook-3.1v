package com.lanyuan.logAop;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lanyuan.annotation.SystemLog;
import com.lanyuan.entity.LogFormMap;
import com.lanyuan.mapper.LogMapper;
import com.lanyuan.util.Common;
/**
 * 切点类
 * @author LJN
 * @since 2015-05-05 Pm 20:35
 * @version 1.0
 */
@Aspect
@Component
public  class LogAopAction {
    //本地异常日志记录对象
     private  static  final Logger logger = LoggerFactory.getLogger(LogAopAction. class);
     @Inject
 	private LogMapper logMapper;
     //Controller层切点
    @Pointcut("@annotation(com.lanyuan.annotation.SystemLog)")
     public  void controllerAspect() {
    }
    
    /**
     * 操作异常记录
     *@descript
     *@param point
     *@param e
     *@author LJN
     *@date 2015年5月5日
     *@version 1.0
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")  
    public  void doAfterThrowing(JoinPoint point, Throwable e) {  
    	LogFormMap logForm = new LogFormMap();
		 Map<String, Object> map = null;
		String user = null;
		String ip = null;
		try {
			ip = SecurityUtils.getSubject().getSession().getHost();
		} catch (Exception ee) {
			ip = "无法获取登录用户Ip";
		}
		try {
			map=getControllerMethodDescription(point);
			// 登录名
			user = SecurityUtils.getSubject().getPrincipal().toString();
			if (Common.isEmpty(user)) {
				user = "无法获取登录用户信息！";
			}
		} catch (Exception ee) {
			user = "无法获取登录用户信息！";
		}
		
    	logForm.put("accountName",user);
		logForm.put("module",map.get("module"));
		logForm.put("methods","<font color=\"red\">执行方法异常:-->"+map.get("methods")+"</font>");
		logForm.put("description","<font color=\"red\">执行方法异常:-->"+e+"</font>");
		logForm.put("actionTime","0");
		logForm.put("userIP",ip);
		try {
			logMapper.addEntity(logForm);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Around("controllerAspect()")
     public Object doController(ProceedingJoinPoint point) {
    	Object result = null;
		// 执行方法名
		String methodName = point.getSignature().getName();
		String className = point.getTarget().getClass().getSimpleName();
		LogFormMap logForm = new LogFormMap();
		 Map<String, Object> map = null;
		String user = null;
		Long start = 0L;
		Long end = 0L;
		Long time = 0L;
		String ip = null;
		try {
			ip = SecurityUtils.getSubject().getSession().getHost();
		} catch (Exception e) {
			ip = "无法获取登录用户Ip";
		}
		try {
			// 登录名
			user = SecurityUtils.getSubject().getPrincipal().toString();
			if (Common.isEmpty(user)) {
				user = "无法获取登录用户信息！";
			}
		} catch (Exception e) {
			user = "无法获取登录用户信息！";
		}
		// 当前用户
		try {
			map=getControllerMethodDescription(point);
			// 执行方法所消耗的时间
			start = System.currentTimeMillis();
			result = point.proceed();
			end = System.currentTimeMillis();
		    time = end - start;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
         try {
 			logForm.put("accountName",user);
 			logForm.put("module",map.get("module"));
 			logForm.put("methods",map.get("methods"));
 			logForm.put("description",map.get("description"));
 			logForm.put("actionTime",time.toString());
 			logForm.put("userIP",ip);
 			logMapper.addEntity(logForm);
            //*========控制台输出=========*//
            System.out.println("=====通知开始=====");
            System.out.println("请求方法:" + className + "." + methodName + "()");
            System.out.println("方法描述:" + map);
            System.out.println("请求IP:" + ip);
            System.out.println("=====通知结束=====");
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("====通知异常====");
            logger.error("异常信息:{}", e.getMessage());
        }
         return result;
    }
    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
     @SuppressWarnings("rawtypes")
	public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
    	 Map<String, Object> map = new HashMap<String, Object>();
    	 String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
         for (Method method : methods) {
             if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                 if (clazzs.length == arguments.length) {
                	 map.put("module", method.getAnnotation(SystemLog.class).module());
                	 map.put("methods", method.getAnnotation(SystemLog.class).methods());
                	 String de = method.getAnnotation(SystemLog.class).description();
                	 if(Common.isEmpty(de))de="执行成功!";
                	 map.put("description", de);
                     break;
                }
            }
        }
         return map;
    }
}
