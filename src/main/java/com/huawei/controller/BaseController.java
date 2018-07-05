package com.huawei.controller;

import com.huawei.utils.JSONUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能：BaseController
 * 作者：laihuawei(laihuawei@lianj.com)
 * 日期：2018年07月2018/7/5日 15:25
 * 版权所有：广东联结网络技术有限公司 版权所有(C)
 */
public class BaseController {

    @RequestMapping(value = {"index", ""})
    public String index() throws Exception {
        return "index";
    }

    protected String returnJson(int statusCode, Object obj, String message) {
        Map<String, Object> result = new HashMap<String, Object>(3);
        result.put("statusCode", statusCode);
        result.put("obj", obj);
        result.put("message", message);
        return JSONUtils.toJson(result);
    }
}
