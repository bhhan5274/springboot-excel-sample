package com.bhhan.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-11-10
 * Github : http://github.com/bhhan5274
 */
public class SuperClassReflectionUtils {
    private SuperClassReflectionUtils(){}

    public static List<Field> getAllFields(Class<?> clazz){
        List<Field> fields = new ArrayList<>();

        for (Class<?> clazzInClasses : getAllClassesIncludingSuperClasses(clazz, true)) {
            fields.addAll(Arrays.asList(clazzInClasses.getDeclaredFields()));
        }
        return fields;
    }

    public static Annotation getAnnotation(Class<?> clazz, Class<? extends Annotation> targetAnnotation){
        for (Class<?> clazzInClasses : getAllClassesIncludingSuperClasses(clazz, false)) {
            if(clazzInClasses.isAnnotationPresent(targetAnnotation)){
                return clazzInClasses.getAnnotation(targetAnnotation);
            }
        }
        return null;
    }

    public static Field getField(Class<?> clazz, String name){
        try {
            for (Class<?> clazzInClasses : getAllClassesIncludingSuperClasses(clazz, false)) {
                for (Field field : clazzInClasses.getDeclaredFields()) {
                    if (field.getName().equals(name)) {
                        return clazzInClasses.getDeclaredField(name);
                    }
                }
            }
        } catch(NoSuchFieldException e){
            throw new IllegalArgumentException(String.format("%s field is not found!!!", name));
        }

        return null;
    }

    private static List<Class<?>> getAllClassesIncludingSuperClasses(Class<?> clazz, boolean fromSuper){
        List<Class<?>> classes = new ArrayList<>();
        while(clazz != null){
            classes.add(clazz);
            clazz = clazz.getSuperclass();
        }

        if(fromSuper){
            Collections.reverse(classes);
        }

        return classes;
    }
}
