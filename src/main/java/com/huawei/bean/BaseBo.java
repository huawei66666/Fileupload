package com.huawei.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 功能：BO基础类
 * 作者：Jimbo(Jimbo@lianj.com)
 * 日期：2016-05-29
 * 版权所有：广东联结网络技术有限公司 版权所有(C) 2016
 */
public abstract class BaseBo implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(BaseBo.class);

    private static final long serialVersionUID = 5044230490974505185L;

    /**
     * 序列化 实体类继承这个类即可
     *
     * @return 序列化结果
     */
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        java.lang.reflect.Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (java.lang.reflect.Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            logger.error("toString builder encounter an error", e);
        }
        return builder.toString();
    }
}
