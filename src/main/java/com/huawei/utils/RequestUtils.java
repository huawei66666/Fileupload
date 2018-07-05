package com.huawei.utils;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 请求工具类
 * @author xuhaowen
 * @create 2017-07-上午 11:10
 **/
public class RequestUtils {

    /**
     * 获得 上传文件列表
     * @param request
     * @return
     * @throws Exception
     */
    public static List<MultipartFile> getUploadFiles(HttpServletRequest request) throws Exception {
        return getUploadFiles(request, false);
    }

    /**
     * 获得 上传文件
     * @param request
     * @return
     * @throws Exception
     */
    public static MultipartFile getUploadFile(HttpServletRequest request) throws Exception {
        List<MultipartFile> list = getUploadFiles(request, true);
        return CollectionUtils.isNotEmpty(list)?list.get(0):null;
    }

    /**
     * 获得 上传文件
     * @param request
     * @param single 是否单个
     * @return
     * @throws Exception
     */
    private static List<MultipartFile> getUploadFiles(HttpServletRequest request, boolean single) throws Exception {
        if (!(request instanceof MultipartHttpServletRequest)) {
            throw new Exception("Request is not a MultipartHttpServletRequest");
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> result = new ArrayList<MultipartFile>();
        //获得文件
        for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
            String key = it.next();
            result.add(multipartRequest.getFile(key));
            if(single)
                break;
        }
        if (CollectionUtils.isEmpty(result)) {
            throw new Exception("没有上传文件！");
        }
        return result;
    }

    public static Map<String,Object> getMapRequest(final HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        for(Iterator<String> it = request.getParameterMap().keySet().iterator(); it.hasNext();){
            String key = it.next();
            String[] values = request.getParameterValues(key);
            if (values != null) {
                if (values.length == 1) {
                    params.put(key, values[0]);
                } else {
                    params.put(key, values);
                }
            }
        }
        return Collections.unmodifiableMap(params);
    }

    public static String getStrParameter(HttpServletRequest request, String paramName) {
        return getStrParameter(request, paramName, null);
    }

    public static String getStrParameter(HttpServletRequest request, String paramName, String defaultValue) {
        String ret = request.getParameter(paramName);
        if(StringUtils.isEmpty(ret))
            return defaultValue;
        return ret;
    }

    public static Integer getIntParameter(HttpServletRequest request, String paramName) {
        return getIntParameter(request, paramName, null);
    }

    public static Integer getIntParameter(HttpServletRequest request, String paramName, Integer defVal) {
        String id = request.getParameter(paramName);
        if (StringUtils.isNotBlank(id)) {
            try {
                return new Integer(id);
            } catch (Exception e) {
                return defVal;
            }
        }
        return defVal;
    }

    public static Long getLongParameter(HttpServletRequest request, String paramName){
        String paramValue = request.getParameter(paramName);
        try{
            return Long.valueOf(paramValue);
        }catch (Exception e) {
            return null;
        }
    }

    public static boolean getBoolParameter(HttpServletRequest request,String paramName){
        String paramValue = request.getParameter(paramName);
        try{
            if(StringUtils.isNotEmpty(paramValue)){
                if(paramValue.equalsIgnoreCase("true") || paramValue.equalsIgnoreCase("Y") || paramValue.equalsIgnoreCase("1")){
                    return true;
                }
            }
        }catch (Exception e) {
        }
        return false;
    }

    public static Boolean getBoolParameter(HttpServletRequest request, String paramName, boolean defVal){
        String paramValue = request.getParameter(paramName);
        try{
            return Boolean.valueOf(paramValue);
        }catch (Exception e) {
            return defVal;
        }
    }
}
