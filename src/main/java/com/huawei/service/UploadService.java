package com.huawei.service;

import com.huawei.bean.ResourceDatabaseBo;
import com.huawei.utils.BizException;
import com.huawei.utils.ImportExcel;
import com.huawei.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：上传业务数据处理
 * 作者：laihuawei(laihuawei@lianj.com)
 * 日期：2018年07月2018/7/5日 10:47
 * 版权所有：广东联结网络技术有限公司 版权所有(C)
 */
@Service
public class UploadService {

    /**
     * 读取 计划 excel
     *
     * @param excel
     * @return 采购计划列表
     */
    public List<ResourceDatabaseBo> upload(ImportExcel excel) throws BizException {
        List<ResourceDatabaseBo> list = new ArrayList<>();
        try {
            for (int i = 0; i <= excel.getLastDataRowNum(); i++) {
                Row row = excel.getRow(i);
                ResourceDatabaseBo bo = new ResourceDatabaseBo();
                for (int j = 0; j < excel.getLastCellNum(); j++) {
                    Object val = excel.getCellValue(row, j);
                    if (i == 0) {
                        if (!checkExcelHead(row, j, excel)) {
                            throw new BizException("Excel格式不正确！");
                        }
                    } else {
                        /*if (checkExcelContent(val)) {
                            if (j == 0) {
                                bo.setGoodName(val.toString());
                            } else if (j == 1) {
                                bo.setSpecificationName(val.toString());
                            } else if (j == 2) {
                                bo.setBrandName(val.toString());
                            } else if (j == 3) {
                                bo.setQuantity(Double.parseDouble(val.toString()));
                            } else if (j == 4) {
                                bo.setUnit(val.toString());
                            } else if (j == 5) {
                                bo.setRemark(val.toString());
                            }
                        }*/
                    }
                }
//                if (StringUtils.isNotBlank(bo.getGoodName())) {
//                    list.add(bo);
//                }
            }
        } catch (Exception e) {
            throw new BizException("数据格式错误！");
        }
        if (CollectionUtils.isEmpty(list))
            throw new BizException("文件内容为空，请重新上传！");
        return list;
    }

    /**
     * 检查 excel头
     *
     * @param row
     * @param column
     * @param excel
     * @return
     */
    private boolean checkExcelHead(Row row, int column, ImportExcel excel) {
        Object val = excel.getCellValue(row, column);
        if (column == 0) {
            return checkExcelHeadContent("物料名称", val);
        } else if (column == 1) {
            return checkExcelHeadContent("规格型号", val);
        } else if (column == 2) {
            return checkExcelHeadContent("品牌", val);
        } else if (column == 3) {
            return checkExcelHeadContent("数量", val);
        } else if (column == 4) {
            return checkExcelHeadContent("单位", val);
        } else if (column == 5) {
            return checkExcelHeadContent("备注", val);
        }
        return true;
    }

    /**
     * 检查头
     *
     * @param defContent
     * @param val
     * @return
     */
    private boolean checkExcelHeadContent(String defContent, Object val) {
        if (val == null || !val.toString().startsWith(defContent))
            return false;
        return true;
    }

    /**
     * 检查内容
     *
     * @param val
     * @return
     */
    private boolean checkExcelContent(Object val) {
        if (val == null || StringUtils.isBlank(val.toString()))
            return false;
        return true;
    }

}
