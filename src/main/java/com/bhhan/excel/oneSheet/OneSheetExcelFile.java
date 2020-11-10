package com.bhhan.excel.oneSheet;

import com.bhhan.excel.SXSSFExcelFile;
import com.bhhan.resource.DataFormatDecider;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public class OneSheetExcelFile<T> extends SXSSFExcelFile<T> {

    private static final int ROW_START_INDEX = 0;
    private static final int COLUMN_START_INDEX = 0;
    private int currentRowIndex = ROW_START_INDEX;

    public OneSheetExcelFile(Class<?> type) {
        super(type);
    }

    public OneSheetExcelFile(List<T> data, Class<T> type){
        super(data, type);
    }

    public OneSheetExcelFile(List<T> data, Class<T> type,
                             DataFormatDecider dataFormatDecider){
        super(data, type, dataFormatDecider);
    }

    @Override
    protected void renderExcel(List<T> data) {
        sheet = wb.createSheet();
        renderHeadersWithNewSheet(sheet, currentRowIndex++, COLUMN_START_INDEX);

        if(data.isEmpty()){
            return;
        }

        for (Object renderedData : data) {
            renderBody(renderedData, currentRowIndex++, COLUMN_START_INDEX);
        }
    }

    @Override
    protected void validateData(List<T> data) {
        int maxRows = supplyExcelVersion.getMaxRows();
        if(data.size() > maxRows){
            throw new IllegalArgumentException(String.format("This concrete Excelfile does not support over %s rows", maxRows));
        }
    }

    @Override
    public void addRows(List<T> data) {
        renderBody(data, currentRowIndex++, COLUMN_START_INDEX);
    }
}
