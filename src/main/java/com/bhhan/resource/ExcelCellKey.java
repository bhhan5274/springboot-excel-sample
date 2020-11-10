package com.bhhan.resource;

import java.util.Objects;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public class ExcelCellKey {
    private final String dataFieldName;
    private final ExcelRenderLocation excelRenderLocation;

    public static ExcelCellKey of(String fieldName, ExcelRenderLocation excelRenderLocation){
        assert excelRenderLocation != null;
        return new ExcelCellKey(fieldName, excelRenderLocation);
    }

    private ExcelCellKey(String dataFieldName, ExcelRenderLocation excelRenderLocation) {
        this.dataFieldName = dataFieldName;
        this.excelRenderLocation = excelRenderLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExcelCellKey that = (ExcelCellKey) o;
        return Objects.equals(dataFieldName, that.dataFieldName) &&
                excelRenderLocation == that.excelRenderLocation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataFieldName, excelRenderLocation);
    }
}
