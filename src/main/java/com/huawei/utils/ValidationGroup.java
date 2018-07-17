package com.huawei.utils;

/**
 * 
 * <p>Project:ttwg.item	<p>
 * <p>Module:			<p>
 * <p>Description:		<p>
 *
 * @author 善财童子<zengxm@ttwg168.net>
 * @date 2015年9月14日 下午3:04:38
 */
public interface ValidationGroup {

	/**
	 * No Means
	 * 
	 * @see BeanValidators.java
	 * 
	 * 用于验证标明组名, 如需要更多, 自行扩展
	 * 
	 */
	public static interface Save {

	}

	public static interface Update {

	}

	public static interface Delete {

	}

	public static interface All {

	}
}
