package com.bhhan.resource;

import com.bhhan.ExcelColumn;
import com.bhhan.ExcelColumnStyle;
import com.bhhan.exception.InvalidExcelCellStyleException;
import com.bhhan.exception.NoExcelColumnAnnotationException;
import com.bhhan.resource.collection.PreCalculatedCellStyleMap;
import com.bhhan.DefaultBodyStyle;
import com.bhhan.DefaultHeaderStyle;
import com.bhhan.style.ExcelCellStyle;
import com.bhhan.style.NoExcelCellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.bhhan.utils.SuperClassReflectionUtils.getAllFields;
import static com.bhhan.utils.SuperClassReflectionUtils.getAnnotation;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public final class ExcelRenderResourceFactory {
    public static ExcelRenderResource prepareRenderResource(Class<?> type, Workbook wb, DataFormatDecider dataFormatDecider){
        PreCalculatedCellStyleMap styleMap = new PreCalculatedCellStyleMap(dataFormatDecider);
        Map<String, String> headerNamesMap = new LinkedHashMap<>();
        List<String> fieldNames = new ArrayList<>();

        ExcelColumnStyle classDefinedHeaderStyle = getHeaderExcelColumnStyle(type);
        ExcelColumnStyle classDefinedBodyStyle = getBodyExcelColumnStyle(type);

        for (Field field : getAllFields(type)) {
            if(field.isAnnotationPresent(ExcelColumn.class)){
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                styleMap.put(String.class, ExcelCellKey.of(field.getName(), ExcelRenderLocation.HEADER),
                        getCellStyle(decideAppliedStyleAnnotation(classDefinedHeaderStyle, annotation.headerStyle())), wb);

                Class<?> fieldType = field.getType();

                styleMap.put(
                        fieldType,
                        ExcelCellKey.of(field.getName(), ExcelRenderLocation.BODY),
                        getCellStyle(decideAppliedStyleAnnotation(classDefinedBodyStyle, annotation.bodyStyle())), wb);

                fieldNames.add(field.getName());
                headerNamesMap.put(field.getName(), annotation.headerName());
            }
        }

        if(styleMap.isEmpty()){
            throw new NoExcelColumnAnnotationException(String.format("Class %s has not @ExcelColumn at all", type));
        }

        return new ExcelRenderResource(styleMap, headerNamesMap, fieldNames);
    }

    private static ExcelColumnStyle decideAppliedStyleAnnotation(ExcelColumnStyle classAnnotation, ExcelColumnStyle fieldAnnotaion){
        if(fieldAnnotaion.excelCellStyleClass().equals(NoExcelCellStyle.class)
        && classAnnotation != null){
            return classAnnotation;
        }
        return fieldAnnotaion;
    }

    private static ExcelCellStyle getCellStyle(ExcelColumnStyle excelColumnStyle){
        Class<? extends ExcelCellStyle> excelCellStyleClass = excelColumnStyle.excelCellStyleClass();
        if(excelCellStyleClass.isEnum()){
            String enumName = excelColumnStyle.enumName();
            return findExcelCellStyle(excelCellStyleClass, enumName);
        }

        try {
            return excelCellStyleClass.newInstance();
        }catch(InstantiationException | IllegalAccessException e){
            throw new InvalidExcelCellStyleException(e.getMessage(), e);
        }
    }

    private static ExcelColumnStyle getHeaderExcelColumnStyle(Class<?> clazz){
        Annotation annotation = getAnnotation(clazz, DefaultHeaderStyle.class);
        if(annotation == null){
            return null;
        }

        return ((DefaultHeaderStyle)annotation).style();
    }

    private static ExcelColumnStyle getBodyExcelColumnStyle(Class<?> clazz){
        Annotation annotation = getAnnotation(clazz, DefaultBodyStyle.class);
        if(annotation == null){
            return null;
        }
        return ((DefaultBodyStyle)annotation).style();
    }

    @SuppressWarnings("unchecked")
    private static ExcelCellStyle findExcelCellStyle(Class<?> excelCellStyles, String enumName){
        try {
            return (ExcelCellStyle) Enum.valueOf((Class<Enum>) excelCellStyles, enumName);
        }catch(NullPointerException e){
            throw new InvalidExcelCellStyleException("enumName must not be null", e);
        }catch(IllegalArgumentException e){
            throw new InvalidExcelCellStyleException(String.format("Enum %s does not name %s", excelCellStyles.getName()), e);
        }
    }
}
