package com.jding.easyexcel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class TableBase extends BaseRowModel {
    @ExcelProperty(value = "姓名",index = 0)
    private String name;
    @ExcelProperty(value = "年龄",index = 1)
    private int age;
    @ExcelProperty(value = "学校",index = 2)
    private String school;


}
