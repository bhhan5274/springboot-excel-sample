package com.bhhan;

import com.bhhan.style.ExcelCellStyle;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public @interface ExcelColumnStyle {
    Class<? extends ExcelCellStyle> excelCellStyleClass();
    String enumName() default "";
}
