package com.bhhan.resource;

import org.apache.poi.ss.usermodel.DataFormat;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public interface DataFormatDecider {
    short getDataFormat(DataFormat dataFormat, Class<?> type);
}
