/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huawei.utils;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 * 
 * @author Zaric
 * @date 2013-5-29 下午1:25:40
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	private static ApplicationContext applicationContext = null;

	private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null.
	 */
	public static void clearHolder() {
		if (logger.isDebugEnabled()){
			logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}

	/**
	 * 实现ApplicationContextAware接口, 注入Context到静态变量中.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
//		logger.debug("注入ApplicationContext到SpringContextHolder:{}", applicationContext);
//		if (SpringContextHolder.applicationContext != null) {
//			logger.info("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
//		}
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 实现DisposableBean接口, 在Context关闭时清理静态变量.
	 */
	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clearHolder();
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		Validate.validState(applicationContext != null, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
	}
	
	/**
	 * 反射 执行方法
	 * @param beanName Spring实例名
	 * @param methodName 方法名
	 * @param paramVals 参数值
	 * @param paramTypes 参数类型
	 * @return
	 */
	public static ApiResult invokeMethod(String beanName, String methodName, Object[] paramVals, Class<?>[] paramTypes){
		//参数校验
		if(StringUtils.isBlank(beanName) || StringUtils.isBlank(methodName)){
			return new ApiResult(false, "参数为空");
		}
		//获得 bean
		Object bean = getBean(beanName);
		if(bean == null){
			return new ApiResult(false, "属性未注入"+beanName);
		}
		//获得方法
		Method method = null;
		if(paramTypes == null){
			method = ReflectionUtils.findMethod(bean.getClass(), methodName);
		}else{
			method = ReflectionUtils.findMethod(bean.getClass(), methodName, paramTypes);
		}
		if(method == null){
			return new ApiResult(false, beanName+"不存在方法:"+methodName);
		}
		//执行方法
		Object obj = null;
		try {
			if(paramVals == null){
				obj = ReflectionUtils.invokeMethod(method, bean);
			}else{
				obj = ReflectionUtils.invokeMethod(method, bean, paramVals);
			}
		} catch (Exception e) {
			if(e instanceof BizException){
				logger.error("业务出错：", e);
				return new ApiResult(false, e.getMessage());
			}else{
				logger.error("beanName:"+beanName+" invokeMethod:"+methodName, e);
				return new ApiResult(false, "beanName:"+beanName+" 执行方法:"+methodName+"失败,"+e.getMessage());
			}
		}
		return new ApiResult(true, "执行成功", obj);
	}
	
}