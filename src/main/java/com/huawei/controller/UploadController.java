package com.huawei.controller;

import com.huawei.bean.ResourceDatabaseBo;
import com.huawei.service.UploadService;
import com.huawei.utils.ImportExcel;
import com.huawei.utils.RequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sun.deploy.registration.InstallCommands.STATUS_OK;
import static sun.plugin2.message.StartAppletAckMessage.STATUS_ERROR;

/**
 * 功能：spring控制器
 * 作者：laihuawei(laihuawei@lianj.com)
 * 日期：2018年07月2018/7/4日 17:37
 * 版权所有：广东联结网络技术有限公司 版权所有(C)
 */
@Controller
@RequestMapping(value = "/")
public class UploadController extends BaseController {

    @Resource
    public UploadService uploadService;

    /**
     * 上传跳转
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "upload")
    public String upload() throws Exception {
        return "upload";
    }

    /**
     * 导入Excel
     *
     * @param request
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("uploadExcel")
    @ResponseBody
    public String uploadExcel(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        List<ResourceDatabaseBo> list = null;
        try {
            MultipartFile multipartFile = RequestUtils.getUploadFile(request);
            if (multipartFile == null) throw new RuntimeException("请上传Excel文件.");
            ImportExcel excel = new ImportExcel(multipartFile, 0, 0);
            list = uploadService.upload(excel);
        } catch (Exception e) {
            return returnJson(STATUS_ERROR, list, e.getMessage());
        }
        return returnJson(STATUS_OK, list, "上传成功！");
    }


}
