package com.bhhan.style;

import com.bhhan.style.align.DefaultExcelAlign;
import com.bhhan.style.align.ExcelAlign;
import com.bhhan.style.border.DefaultExcelBorders;
import com.bhhan.style.border.ExcelBorderStyle;
import com.bhhan.style.color.DefaultExcelColor;
import com.bhhan.style.color.ExcelColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */
public enum DefaultExcelCellStyle implements ExcelCellStyle {
    GREY_HEADER(DefaultExcelColor.rgb(217, 217, 217),
            DefaultExcelBorders.newInstance(ExcelBorderStyle.THIN), DefaultExcelAlign.CENTER_CENTER),
    BLUE_HEADER(DefaultExcelColor.rgb(223, 235, 246),
            DefaultExcelBorders.newInstance(ExcelBorderStyle.THIN), DefaultExcelAlign.CENTER_CENTER),
    BODY(DefaultExcelColor.rgb(255, 255, 255),
            DefaultExcelBorders.newInstance(ExcelBorderStyle.THIN), DefaultExcelAlign.RIGHT_CENTER);

    private final ExcelColor excelColor;
    private final DefaultExcelBorders defaultExcelBorders;
    private final ExcelAlign excelAlign;

    DefaultExcelCellStyle(ExcelColor excelColor, DefaultExcelBorders defaultExcelBorders, ExcelAlign excelAlign) {
        this.excelColor = excelColor;
        this.defaultExcelBorders = defaultExcelBorders;
        this.excelAlign = excelAlign;
    }

    @Override
    public void apply(CellStyle cellStyle) {
        excelColor.applyForeground(cellStyle);
        defaultExcelBorders.apply(cellStyle);
        excelAlign.apply(cellStyle);
    }
}
