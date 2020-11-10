package com.bhhan.excel;

import com.bhhan.exception.ExcelInternalException;
import com.bhhan.resource.*;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static com.bhhan.utils.SuperClassReflectionUtils.getField;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public abstract class SXSSFExcelFile<T> implements ExcelFile<T>{
    protected static final SpreadsheetVersion supplyExcelVersion = SpreadsheetVersion.EXCEL2007;
    protected SXSSFWorkbook wb;
    protected Sheet sheet;
    protected ExcelRenderResource resource;

    public SXSSFExcelFile(Class<?> type){
        this(Collections.emptyList(), type, new DefaultDataFormatDecider());
    }

    public SXSSFExcelFile(List<T> data, Class<T> type){
        this(data, type, new DefaultDataFormatDecider());
    }

    public SXSSFExcelFile(List<T> data, Class<?> type, DataFormatDecider dataFormatDecider){
        validateData(data);
        this.wb = new SXSSFWorkbook();
        this.resource = ExcelRenderResourceFactory.prepareRenderResource(type, wb, dataFormatDecider);
        renderExcel(data);
    }

    protected void validateData(List<T> data){}
    protected abstract void renderExcel(List<T> data);

    protected void renderHeadersWithNewSheet(Sheet sheet, int rowIndex, int columnStartIndex){
        Row row = sheet.createRow(rowIndex);
        int columnIndex = columnStartIndex;
        for (String dataFieldName : resource.getDataFieldNames()) {
            Cell cell = row.createCell(columnIndex++);
            cell.setCellStyle(resource.getCellStyle(dataFieldName, ExcelRenderLocation.HEADER));
            cell.setCellValue(resource.getExcelHeaderName(dataFieldName));
        }
    }

    protected void renderBody(Object data, int rowIndex, int columnStartIndex){
        Row row = sheet.createRow(rowIndex);
        int columnIndex = columnStartIndex;
        for (String dataFieldName : resource.getDataFieldNames()) {
            Cell cell = row.createCell(columnIndex++);
            try{
                Field field = getField(data.getClass(), dataFieldName);
                field.setAccessible(true);
                cell.setCellStyle(resource.getCellStyle(dataFieldName, ExcelRenderLocation.BODY));
                Object cellValue = field.get(data);
                renderCellValue(cell, cellValue);
            } catch (Exception e) {
                throw new ExcelInternalException(e.getMessage(), e);
            }
        }
    }

    private void renderCellValue(Cell cell, Object cellValue){
        if(cellValue instanceof Number){
            Number numberValue = (Number)cellValue;
            cell.setCellValue(numberValue.doubleValue());
            return;
        }
        cell.setCellValue(cellValue == null ? "" : cellValue.toString());
    }

    @Override
    public void write(OutputStream stream) throws IOException {
        wb.write(stream);
        wb.close();
        wb.dispose();
        stream.close();
    }
}
