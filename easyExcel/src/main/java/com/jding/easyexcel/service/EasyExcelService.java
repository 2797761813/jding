package com.jding.easyexcel.service;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.jding.easyexcel.pojo.TableBase;
import com.jding.easyexcel.util.ExcelListener;
import com.jding.easyexcel.util.ExcelUtil;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class EasyExcelService {
    public  void readExcel() {
        String file = "C:/Users/Yto/Desktop/testExcel.xlsx";
        List<TableBase> excelList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            TableBase tableBasey = new TableBase();
            tableBasey.setName("cmj" + i);
            //tableBasey.setAge(22 + i);
            tableBasey.setSchool("清华大学" + i);
            excelList.add(tableBasey);
        }

        ExcelUtil.writeWithTemplate(file,excelList);
    }

    public void exportExcel() {
        ArrayList<ExcelUtil.MultipleSheelPropety> list1 = new ArrayList<>();
        for(int j = 1; j < 4; j++){
            ArrayList<TableBase> list = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                TableBase tableHeaderExcelProperty = new TableBase();
                tableHeaderExcelProperty.setName("cmj" + i);
                //tableHeaderExcelProperty.setAge(22 + i);
                tableHeaderExcelProperty.setSchool("清华大学" + i);
                list.add(tableHeaderExcelProperty);
            }

            Sheet sheet = new Sheet(j, 0);
            sheet.setSheetName("sheet" + j);

            ExcelUtil.MultipleSheelPropety multipleSheelPropety = new ExcelUtil.MultipleSheelPropety();
            multipleSheelPropety.setData(list);
            multipleSheelPropety.setSheet(sheet);

            list1.add(multipleSheelPropety);

        }

        ExcelUtil.writeWithMultipleSheel("C:/Users/Yto/Desktop/testMoreSheet.xlsx",list1);

    }

    public void importAndReadExcel() throws FileNotFoundException {
        InputStream in = new FileInputStream("C:/Users/Yto/Desktop/testMoreSheet.xlsx");
        /**
         * @description
         * @params
         * @return
        */
        /**
         * 实例化 ExcelListener  extends AnalysisEventListener
         * 实现： invoke 解析一行数据执行一次,方法中的object是一行的数据
         *       doAfterAllAnalysed 解析完数据,即执行完invoke后执行
         * 实例化 ExcelReader 其中的参数 in 输入流获取表格的
         * 读取表格信息；调用reader
         * 向数据库插入数据
         */
        ExcelListener listener = new ExcelListener();
        ExcelReader reader = new ExcelReader(in,null,listener);
        reader.read(new Sheet(1,1,TableBase.class));
        /*List<TableBase> data = listener.getData();
        for (int i = 0; i < data.size() ; i++) {
            System.out.println(data.get(i).toString());
        }*/
        System.out.println("...");
    }
}
