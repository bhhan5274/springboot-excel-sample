package com.bhhan.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public interface ExcelFile<T> {
    void write(OutputStream stream) throws IOException;
    void addRows(List<T> data);
}
