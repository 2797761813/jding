package com.jding.easyexcel.service;

import com.jding.easyexcel.pojo.TableBase;
import com.jding.easyexcel.util.ExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EasyExcelServiceTest {

    @Autowired
    private EasyExcelService service;

    @Test
    public void readExcel() {
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

    @Test
    public void exportExcel() {
        service.exportExcel();
    }

    @Test
    public void importAndReadExcel() throws FileNotFoundException {
        service.importAndReadExcel();

    }
}
