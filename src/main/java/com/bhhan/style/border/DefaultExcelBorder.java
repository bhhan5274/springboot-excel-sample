package com.bhhan.style.border;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public class DefaultExcelBorder implements ExcelBorder{
    private ExcelBorderStyle borderStyle;

    public DefaultExcelBorder(ExcelBorderStyle borderStyle) {
        this.borderStyle = borderStyle;
    }

    @Override
    public void applyTop(CellStyle cellStyle) {
        cellStyle.setBorderTop(borderStyle.getStyle());
    }

    @Override
    public void applyRight(CellStyle cellStyle) {
        cellStyle.setBorderRight(borderStyle.getStyle());
    }

    @Override
    public void applyBottom(CellStyle cellStyle) {
        cellStyle.setBorderBottom(borderStyle.getStyle());
    }

    @Override
    public void applyLeft(CellStyle cellStyle) {
        cellStyle.setBorderLeft(borderStyle.getStyle());
    }
}
