package com.huawei.controller;

import com.huawei.bean.ResourceDatabaseBo;
import com.huawei.bean.ResponseData;
import com.huawei.exception.BizException;
import com.huawei.service.UploadService;
import com.huawei.service.ImportExcelService;
import com.huawei.utils.FileUtil;
import com.huawei.utils.ImportExcel;
import com.huawei.utils.JSONUtils;
import com.huawei.utils.RequestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Resource
    public UploadService uploadService;

    @Resource
    public ImportExcelService importExcelService;

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
     * 接收上传文件
     * @param pic
     * @param req
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String uploadFile(@RequestParam("pic") CommonsMultipartFile pic, HttpServletRequest req, HttpServletResponse response) {
        String uuidFileName = null;
        try {
            String filePath = req.getSession().getServletContext().getRealPath("/upload/");//设置文件保存的本地路径
            String fileName = pic.getOriginalFilename();
            String fileType = fileName.split("[.]")[1];
            String uuid = UUID.randomUUID().toString().replace("-", "");//为了避免文件名重复，在文件名前加UUID
            uuidFileName = uuid + fileName;
            File f = new File(filePath + "/" + uuid + "." + fileType);
            FileUtil.upFile(pic.getInputStream(), uuidFileName, filePath); //将文件保存到服务器
        } catch (IOException e) {
            logger.error("系统异常", e);
            e.printStackTrace();
        }
        return uuidFileName;

    }

    /**
     * 上传跳转
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "uploadTest")
    public String uploadTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String savePath = request.getContextPath();
        savePath = "" + savePath + "WEB-INF/upload";
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
        String extName = "";
        File file;
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                String name = item.getName();
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

    /**
     * 导入资源库
     *
     * @param file         导入文件
     * @param classifyCode 导入目录
     * @param request
     * @param response
     */
    @RequestMapping(value = "/batchImport", method = RequestMethod.POST)
    public void batchImport(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "classifyCode") String classifyCode, HttpServletRequest request, HttpServletResponse response) {
        ResponseData<Object> result = new ResponseData<>(1000, "success");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        crossDomain(response);
        PrintWriter pw = null;
        try {
            if (file == null) {
                throw new BizException("上传失败!");
            }
            result.setData(importExcelService.batchImport(file.getInputStream(), file.getOriginalFilename(), classifyCode));
            pw = response.getWriter();
            if (pw != null) {
                pw.write(JSONUtils.toJson(result));
                pw.flush();
            }
        } catch (Exception e) {
            logger.error("导入资源库异常:" + e.getMessage(), e);
            result.setMessage(e.getMessage());
            result.setCode(1001);
            if (pw != null) {
                pw.write(JSONUtils.toJson(result));
                pw.flush();
            }
        }
    }

    /**
     * 跨域参数
     *
     * @param response
     */
    private void crossDomain(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", "68840");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,x-area,x-client,x-device,x-nettype,x-token,x-version");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed", "1");
        response.setHeader("XDomainRequestAllowed", "1");
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * 导入错误资源库下载
     *
     * @param response
     */
    @RequestMapping(value = "/batchImport/error", method = RequestMethod.GET)
    public void exportBatchImportError(HttpServletResponse response) {
        try {
            response.reset();
            crossDomain(response);
            response.setContentType("aapplication/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资源库导入错误数据.xlsx", "UTF-8"));
            byte[] bytes = importExcelService.exportBatchImportError();
            if (bytes != null) {
                response.setContentLength(bytes.length);
                response.getOutputStream().write(bytes);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("错误资源库下载编码异常:", e);
        } catch (IOException e) {
            logger.error("错误资源库下载编码异常:", e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (Exception e) {
                logger.error("导出错误", e);
            }
        }
    }


}
