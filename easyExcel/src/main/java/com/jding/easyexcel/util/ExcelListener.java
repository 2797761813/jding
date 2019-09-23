package com.jding.easyexcel.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jding.easyexcel.pojo.TableBase;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelListener extends AnalysisEventListener {

    public List<TableBase> getData() {
        return data;
    }

    public void setData(List<TableBase> data) {
        this.data = data;
    }

    private List<TableBase> data = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {

        TableBase tableBase = new TableBase();
        BeanUtils.copyProperties(o,tableBase);
        data.add(tableBase);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        for (int i = 0; i < data.size() ; i++) {
            System.out.println(data.get(i));
        }
    }
}
