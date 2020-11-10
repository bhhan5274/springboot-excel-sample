package com.bhhan.style;

import com.bhhan.style.configurer.ExcelCellStyleConfigurer;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Created by hbh5274@gmail.com on 2020-11-11
 * Github : http://github.com/bhhan5274
 */
public abstract class CustomExcelCellStyle implements ExcelCellStyle{
    private ExcelCellStyleConfigurer configurer = new ExcelCellStyleConfigurer();

    public CustomExcelCellStyle(ExcelCellStyleConfigurer configurer) {
        configure(configurer);
    }

    public abstract void configure(ExcelCellStyleConfigurer configurer);

    @Override
    public void apply(CellStyle cellStyle) {
        configurer.configure(cellStyle);
    }
}
