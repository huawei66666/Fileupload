package com.huawei.bean;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * <p>Project:			<p>
 * <p>Module:			<p>
 * <p>Description:		<p>
 *
 * @author wj<swj@ttwz168.net>
 * @date 2015年10月13日 下午5:42:56
 */
public abstract class BeanUtils extends org.springframework.beans.BeanUtils {

	/**
	 * 复制属性值
	 * @param source 源对象
	 * @param target 目标对象
	 * @param ignoreNullProperties 是否忽略源对象属性为空值的情况
	 * @param ignoreCollection 是否忽略源对象属性为集合的情况
	 * @param ignoreProperties 指定不复制的属性
	 * @throws BeansException
	 */
	public static void copyProperties(Object source, Object target, boolean ignoreNullProperties, boolean ignoreCollection, String... ignoreProperties )
			throws BeansException {

		// tips: 如果源数据为空, 直接返回空s
		if( source == null ) {
			return;
		}

		Assert.notNull( source, "Source must not be null" );
		Assert.notNull( target, "Target must not be null" );

		Class<?> actualEditable = target.getClass();

		PropertyDescriptor[] targetPds = getPropertyDescriptors( actualEditable );
		List<String> ignoreList = ( ignoreProperties != null ) ? Arrays.asList( ignoreProperties ) : null;

		for( PropertyDescriptor targetPd : targetPds ) {
			Method writeMethod = targetPd.getWriteMethod();
			if( writeMethod != null && ( ignoreProperties == null || ( !ignoreList.contains( targetPd.getName() ) ) ) ) {
				PropertyDescriptor sourcePd = getPropertyDescriptor( source.getClass(), targetPd.getName() );
				if( sourcePd != null ) {
					Method readMethod = sourcePd.getReadMethod();
					if( readMethod != null &&
							ClassUtils.isAssignable( writeMethod.getParameterTypes()[ 0 ], readMethod.getReturnType() ) ) {
						try {
							if( !Modifier.isPublic( readMethod.getDeclaringClass().getModifiers() ) ) {
								readMethod.setAccessible( true );
							}
							Object value = readMethod.invoke( source );

							// 如果忽略集合，则当检测到源对象属性读取方法返回值类型为集合时跳过
							if( ignoreCollection && Collection.class.isAssignableFrom( readMethod.getReturnType() ) ) {
								continue;
							}

							// 如果忽略null属性，则当检测到源对象属性值为null时跳过
							if( ignoreNullProperties && value == null ) {
								continue;
							}

							if( !Modifier.isPublic( writeMethod.getDeclaringClass().getModifiers() ) ) {
								writeMethod.setAccessible( true );
							}
							writeMethod.invoke( target, value );
						} catch( Throwable ex ) {
							throw new FatalBeanException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex );
						}
					}
				}
			}
		}
	}

	public static void copyBean(Object source, Object target) throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						// 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
						if (value != null) {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	}

	private static String[] getIgnorePropertyNames(Object source, boolean ignoreNull, boolean ignoreEmptyString) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (ignoreNull && srcValue == null)
				emptyNames.add(pd.getName());
			if (ignoreEmptyString && "".equals(srcValue))
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static void copyProperties(Object src, Object target, boolean ignoreNull, boolean ignoreEmptyString) {
		org.springframework.beans.BeanUtils.copyProperties(src, target,
				getIgnorePropertyNames(src, ignoreNull, ignoreEmptyString));
	}

}
