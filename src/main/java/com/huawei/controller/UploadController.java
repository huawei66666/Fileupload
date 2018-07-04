package com.huawei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能：spring控制器
 * 作者：laihuawei(laihuawei@lianj.com)
 * 日期：2018年07月2018/7/4日 17:37
 * 版权所有：广东联结网络技术有限公司 版权所有(C)
 */
@Controller
@RequestMapping(value = "/")
public class UploadController {

    @RequestMapping(value = {"index", ""})
    public String index() throws Exception {
        return "index";
    }
}
