package com.bhhan.resource.collection;

import com.bhhan.resource.DataFormatDecider;
import com.bhhan.resource.ExcelCellKey;
import com.bhhan.style.ExcelCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public class PreCalculatedCellStyleMap {
    private final DataFormatDecider dataFormatDecider;
    private final Map<ExcelCellKey, CellStyle> cellStyleMap = new HashMap<>();

    public PreCalculatedCellStyleMap(DataFormatDecider dataFormatDecider) {
        this.dataFormatDecider = dataFormatDecider;
    }

    public void put(Class<?> fieldType, ExcelCellKey excelCellKey,
                    ExcelCellStyle excelCellStyle, Workbook wb){
        CellStyle cellStyle = wb.createCellStyle();
        DataFormat dataFormat = wb.createDataFormat();
        cellStyle.setDataFormat(dataFormatDecider.getDataFormat(dataFormat, fieldType));
        excelCellStyle.apply(cellStyle);
        cellStyleMap.put(excelCellKey, cellStyle);
    }

    public CellStyle get(ExcelCellKey excelCellKey){
        return cellStyleMap.get(excelCellKey);
    }

    public boolean isEmpty(){
        return cellStyleMap.isEmpty();
    }
}
