package com.huawei.bean;

import java.io.Serializable;

/**
 * 功能：ResPool -查询参数对象
 * 作者：yk(yanke@lianj.com)
 * 日期：2018-07-04
 * 版权所有：广东联结网络技术有限公司版权所有(C) 2018
 */
public class ResPoolQueryParam extends QueryParam implements Serializable {

    private static final long serialVersionUID = 8213176791905299748L;

    private ResPoolBo resPool;

    public ResPoolBo getResPool() {
        return resPool;
    }

    public void setResPool(ResPoolBo resPool) {
        super.setParamBean(resPool);
        this.resPool = resPool;
    }
}