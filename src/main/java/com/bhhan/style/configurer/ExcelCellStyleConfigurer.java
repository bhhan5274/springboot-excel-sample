package com.bhhan.style.configurer;

import com.bhhan.style.align.ExcelAlign;
import com.bhhan.style.align.NoExcelAlign;
import com.bhhan.style.border.ExcelBorders;
import com.bhhan.style.border.NoExcelBorders;
import com.bhhan.style.color.ExcelColor;
import com.bhhan.style.color.NoExcelColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public class ExcelCellStyleConfigurer {
    private ExcelAlign excelAlign = new NoExcelAlign();
    private ExcelColor excelColor = new NoExcelColor();
    private ExcelBorders excelBorders = new NoExcelBorders();

    public ExcelCellStyleConfigurer() {
    }

    public ExcelCellStyleConfigurer excelAlign(ExcelAlign excelAlign){
        this.excelAlign = excelAlign;
        return this;
    }

    public ExcelCellStyleConfigurer excelColor(ExcelColor excelColor){
        this.excelColor = excelColor;
        return this;
    }

    public ExcelCellStyleConfigurer excelBorders(ExcelBorders excelBorders){
        this.excelBorders = excelBorders;
        return this;
    }

    public void configure(CellStyle cellStyle){
        excelAlign.apply(cellStyle);
        excelColor.applyForeground(cellStyle);
        excelBorders.apply(cellStyle);
    }
}
