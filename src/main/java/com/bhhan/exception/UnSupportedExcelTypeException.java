package com.bhhan.exception;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public class UnSupportedExcelTypeException extends ExcelException{

    public UnSupportedExcelTypeException(String message) {
        super(message, null);
    }
}
