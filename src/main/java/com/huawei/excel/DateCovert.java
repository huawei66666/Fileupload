package com.huawei.excel;


import com.github.crab2died.converter.ReadConvertible;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>功能：${DESCRIPTION}</p>
 * <p>作者：yk(yanke@lianj.com)</p>
 * <p>日期：2017年11月13日 17:22</p>
 * <p>版权所有：广东联结网络技术有限公司 版权所有(C) 2016-2018</p>
 */
public class DateCovert implements ReadConvertible {
    @Override
    public Object execRead(String s) {
        if(StringUtils.isBlank(s)) {
            s = null;
        }
        return s ;
    }
}
