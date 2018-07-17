package com.huawei.excel;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;


/**
 * <p>功能： </p>
 * <p>作者：yk(yanke@lianj.com)</p>
 * <p>日期：2018年07月09日 17:37</p>
 * <p>版权所有：广东联结网络技术有限公司 版权所有(C) 2016-2018</p>
 */
public class ResPoolImportUtils {

    public static void main(String[] args) throws Exception {

        String path = "C:\\Users\\Administrator\\Desktop\\资源库导入模板.xlsx";
         path = "C:\\Users\\Administrator\\Desktop\\NO.xlsx";
         path = "C:\\Users\\Administrator\\Desktop\\NO2.xlsx";

       /*
        Workbook workbook = WorkbookFactory.create(new File(path));
        Sheet sheet = workbook.getSheetAt(0);
        int maxRow= sheet.getLastRowNum();
        for (int i = 0; i < maxRow; i++) {
           Row row = sheet.getRow(i);
          // String value=  row.getCell(0).getStringCellValue();
           // System.out.println(value+"----values");
            for (Cell cell : row) {
                String val = Utils.getCellValue(cell);
                //val=cell.getStringCellValue();
                 val=getCellValue(cell);
                System.out.print(cell.getColumnIndex()+"=["+val+"]--");
            }
            System.out.println("");
        }
*/

       List<ResPoolImportModel> rs= ExcelExUtils.getInstance().readExcel2Objects(path,ResPoolImportModel.class);

        if (CollectionUtils.isNotEmpty(rs)) {
            for (ResPoolImportModel column : rs) {
              //  System.out.println(JSONUtils.toJson(column));
                System.out.println(column.toString());
            }
        }
    }

}
