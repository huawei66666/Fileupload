package com.huawei.service;

import com.github.crab2died.exceptions.Excel4JException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huawei.bean.ResPoolBo;
import com.huawei.excel.ExcelExUtils;
import com.huawei.excel.ResPoolImportModel;
import com.huawei.exception.BizException;
import com.huawei.utils.AssertUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 功能：上传业务数据处理
 * 作者：laihuawei(laihuawei@lianj.com)
 * 日期：2018年07月2018/7/5日 10:47
 * 版权所有：广东联结网络技术有限公司 版权所有(C)
 */
@Service
public class ImportExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ImportExcelService.class);

    public Map<String, Object> batchImport(InputStream inputStream, String originalFilename, String classifyCode) throws BizException {
        AssertUtil.isNotNull(inputStream, "文件不能为空");
        AssertUtil.isTrue(StringUtils.isNotBlank(classifyCode), "资源库分类编码不能为空");
        Map<String, Object> result = Maps.newHashMap();
        try {
            List<ResPoolImportModel> errorData = Lists.newArrayList();
            List<ResPoolBo> okData = Lists.newArrayList();
            Map<String, Integer> repeatData = Maps.newHashMap();
            List<ResPoolImportModel> dealWithData = ExcelExUtils.getInstance().readExcel2Objects(inputStream, ResPoolImportModel.class);
            AssertUtil.isTrue(CollectionUtils.isNotEmpty(dealWithData), "导入的数据不能为空");
            logger.debug("数据量:" + dealWithData.size());

            //关键数据校验
            /*for (ResPoolImportModel data : dealWithData) {
                String resName = data.getResName();
                StringBuilder errorRecord = new StringBuilder();
                if (StringUtils.isNotBlank(resName)) {
                    resName = resName.trim();
                    if (repeatData.get(resName) != null) {
                        errorRecord.append("资源重复导入;");
                    }
                    if (resPoolService.checkRepeatResName(LoginUtil.getUserId(), resName, null)) {
                        errorRecord.append("资源已经存在于资源库中了。;");
                    }
                    repeatData.put(resName.trim(), 1);
                } else {
                    errorRecord.append("资源名称不能为空;");
                }
                if (StringUtils.isBlank(data.getAdminPhone())) {
                    errorRecord.append("资源的联系人手机号不能为空;");
                } else if (data.getAdminPhone().length() > 11) {
                    errorRecord.append("资源的联系人手机号错误;");
                }
                if (StringUtils.isBlank(data.getAdminName())) {
                    errorRecord.append("资源的联系人不能为空;");
                }

                if (StringUtils.isNotBlank(data.getRegionName())) {
                    String[] areaDeal = PatternUtils.exractAddress(data.getRegionName());
                    if (areaDeal != null && StringUtils.isNotBlank(areaDeal[1])) {
                        data.setRegionName(areaDeal[1]);
                    } else if (data.getRegionName().length() <= 2 && data.getRegionName().indexOf("市") < 0) {
                        data.setRegionName(data.getResName() + "市");
                    }
                    List<PlAreaBO> areaList = areaService.getAreaByName(data.getRegionName());
                    if (CollectionUtils.isNotEmpty(areaList)) {
                        data.setRegionCode(areaList.get(0).getAreaCode());
                    } else {
                        errorRecord.append("地市名称没有找到，请录入地市的标准名称[如:广州市]。;");
                    }
                } else {
                    errorRecord.append("地区不能为空;");
                }

                if (StringUtils.isBlank(errorRecord.toString())) {
                    ResPoolBo okBo = BeanMapper.map(data, ResPoolBo.class);
                    okBo.setUserId(LoginUtil.getUserId()).setBelongClassify(classifyCode).setSourceClient(getClientType())
                            .setSource(ResPoolConstants.PoolDataSource.EXCEL_IMPORT_USER);
                    okData.add(okBo);
                } else {
                    data.setErrorReason(errorRecord.toString());
                    errorData.add(data);
                }
            }

            int succeedCount = 0;
            if (CollectionUtils.isNotEmpty(okData)) {
                try {
                    succeedCount = resPoolService.saveForBath(okData, LoginUtil.getUserId(), classifyCode, getClientType());
                } catch (Exception e) {
                    logger.error("导入数据批量写入失败", e);
                }
            }
            result.put("totalCount", dealWithData.size());
            result.put("succeedCount", succeedCount);
            result.put("errorCount", dealWithData.size() - succeedCount);
            if (succeedCount > 0) {
                result.put("errorData", errorData.size() > 5 ? errorData.subList(0, 5) : errorData);
            } else {
                result.put("errorData", dealWithData.size() > 5 ? dealWithData.subList(0, 5) : dealWithData);
            }
            if (CollectionUtils.isNotEmpty(errorData) && succeedCount > 0) {
                ssoRedisService.hset(IMPORT_ERROR_REDIS_KEY, String.valueOf(LoginUtil.getUserId()), JSONUtils.toJson(errorData));
            } else if (succeedCount == 0) {
                List<ResPoolImportModel> okErrorDealWith = Lists.newArrayList();
                for (ResPoolBo dealWithDatum : okData) {
                    ResPoolImportModel okError = BeanMapper.map(dealWithDatum, ResPoolImportModel.class);
                    okError.setErrorReason("数据录入错误,请重新导入。");
                    okErrorDealWith.add(okError);
                }
                if (CollectionUtils.isNotEmpty(errorData)) {
                    okErrorDealWith.addAll(errorData);
                }
                ssoRedisService.hset(IMPORT_ERROR_REDIS_KEY, String.valueOf(LoginUtil.getUserId()), JSONUtils.toJson(okErrorDealWith));
            }*/
        } catch (Excel4JException e) {
            logger.error("模板初始化异常", e);
            throw new BizException("模板初始化异常");
        } catch (IOException e) {
            logger.error("文件读取异常", e);
            throw new BizException("文件读取异常");
        } catch (InvalidFormatException e) {
            logger.error("解析数据异常", e);
            throw new BizException("解析数据异常");
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                logger.error("关闭文件流异常", e);
            }
        }
        return result;
    }

    public byte[] exportBatchImportError() throws BizException {
        try {
           /* String jsonValue = ssoRedisService.hget(IMPORT_ERROR_REDIS_KEY, String.valueOf(LoginUtil.getUserId()));
            if (StringUtils.isNotBlank(jsonValue)) {
                List<ResPoolImportModel> data = JSONUtils.toArray(jsonValue, ResPoolImportModel.class);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ExcelExUtils.getInstance().exportObjects2Excel(data, ResPoolImportModel.class, outputStream);
                return outputStream.toByteArray();
            }*/
        } catch (Exception e) {
            throw new BizException("没有导入数据");
        }
        return null;
    }

    /**
     * 获取客户端类型
     *
     * @return
     */
    /*private int getClientType() {
        int clientType = ClientTypeEnum.PC.getValue();
        try {
            String client = LoginUtil.getClientInfo(ClientInfoKeyEnum.XCLINET);
            if (StringUtils.isNotBlank(client)) {
                clientType = new BigDecimal(client).intValue();
            }
        } catch (Exception e) {
            logger.error("版本转换错误", e);
        }
        return clientType;
    }*/
    private boolean isValidDate(final String date) {
        boolean convertSuccess = false;
        try {
            if (StringUtils.isNotBlank(date)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                format.setLenient(false);
                format.parse(date);
                convertSuccess = true;
            }
        } catch (Exception e) {
            convertSuccess = false;
            logger.error("日期格式错误", e);
        }
        return convertSuccess;
    }


}
