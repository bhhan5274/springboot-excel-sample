package com.bhhan.excel.multiSheet;

import com.bhhan.excel.SXSSFExcelFile;
import com.bhhan.resource.DataFormatDecider;
import org.apache.commons.compress.archivers.zip.Zip64Mode;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public class MultiSheetExcelFile<T> extends SXSSFExcelFile<T> {

    //private static final int maxRowCanBeRendered = supplyExcelVersion.getMaxRows() - 1;
    private static final int MAX_ROWS = 10000;
    private static final int maxRowCanBeRendered = MAX_ROWS + 1;

    private static final int ROW_START_INDEX = 0;
    private static final int COLUMN_START_INDEX = 0;
    private int currentRowIndex = ROW_START_INDEX;

    public MultiSheetExcelFile(Class<?> type) {
        super(type);
        wb.setZip64Mode(Zip64Mode.Always);
    }

    public MultiSheetExcelFile(List<T> data, Class<T> type){
        super(data, type);
        wb.setZip64Mode(Zip64Mode.Always);
    }

    public MultiSheetExcelFile(List<T> data, Class<T> type, DataFormatDecider dataFormatDecider){
        super(data, type, dataFormatDecider);
        wb.setZip64Mode(Zip64Mode.Always);
    }

    @Override
    protected void renderExcel(List<T> data) {
        if(data.isEmpty()){
            createNewSheetWithHeader();
            return;
        }

        createNewSheetWithHeader();
        addRows(data);
    }

    @Override
    public void addRows(List<T> data) {
        for (Object renderedData : data) {
            renderBody(renderedData, currentRowIndex++, COLUMN_START_INDEX);
            if(currentRowIndex == maxRowCanBeRendered){
                currentRowIndex = 0;
                createNewSheetWithHeader();
            }
        }
    }

    private void createNewSheetWithHeader() {
        sheet = wb.createSheet();
        renderHeadersWithNewSheet(sheet, ROW_START_INDEX, COLUMN_START_INDEX);
        currentRowIndex++;
    }
}
