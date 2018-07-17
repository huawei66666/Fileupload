package com.huawei.bean;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeanMapper {

	private static DozerBeanMapper dozer = new DozerBeanMapper();  
	  
	  /** 
	  * 构造新的destinationClass实例对象，通过source对象中的字段内容 
	  * 映射到destinationClass实例对象中，并返回新的destinationClass实例对象。 
	  *  
	  * @param source 源数据对象 
	  * @param destinationClass 要构造新的实例对象Class 
	  */
	 public static <T> T map(Object source, Class<T> destinationClass){
		 if(source == null || destinationClass == null) return null;
	 	return dozer.map(source, destinationClass);
	 }
	    
	    
	  
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass){
		List destinationList = new ArrayList();
		if(sourceList == null) return destinationList;
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}
	  
	    
	  /** 
	  * 将对象source的所有属性值拷贝到对象destination中. 
	  *  
	  * @param source 对象source 
	  * @param destinationObject 对象destination
	  */  
	public static void copy(Object source, Object destinationObject) {
		if(source == null || destinationObject == null) return;
		dozer.map(source, destinationObject);
	}
}
