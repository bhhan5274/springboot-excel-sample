package com.bhhan.style.border;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public interface ExcelBorder {
    void applyTop(CellStyle cellStyle);
    void applyRight(CellStyle cellStyle);
    void applyBottom(CellStyle cellStyle);
    void applyLeft(CellStyle cellStyle);
}
