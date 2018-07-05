package com.huawei.controller;

import com.huawei.bean.ResourceDatabaseBo;
import com.huawei.service.UploadService;
import com.huawei.utils.ImportExcel;
import com.huawei.utils.RequestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
     * 上传跳转
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "uploadTest")
    public String uploadTest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        File file;

        String savePath = request.getContextPath();
        savePath = "" + savePath + "/uploads/";
        File f1 = new File(savePath);
        System.out.println(savePath);
        if (!f1.exists()) {
            f1.mkdirs();
        }
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List<FileItem> fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            throw new RuntimeException("上传异常！");
        }
        Iterator<FileItem> it = fileList.iterator();
        String name = "";
        String extName = "";

        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                name = item.getName();
                long size = item.getSize();
                String type = item.getContentType();
                System.out.println(size + " " + type);
                if (name == null || name.trim().equals("")) {
                    continue;
                }
                // 扩展名格式：
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }
                do {
                    // 生成文件名：
                    name = UUID.randomUUID().toString();
                    file = new File(savePath + name + extName);
                } while (file.exists());
                try {
                    item.write(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

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
