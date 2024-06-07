package com.diary.bace.util;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static cn.hutool.extra.cglib.CglibUtil.copy;

public class BeanUtil extends BeanUtils {


    public static <T> List<T> copyList(List<?> sourceList, Class<T> clazz) {

        List<T> resultList = new ArrayList<>(sourceList.size());
        if (CollectionUtil.isNotEmpty(sourceList)) {
            for (Object source : sourceList) {
                resultList.add((T)copy(source, clazz));
            }
        }
        return resultList;
    }

    public static <T> void copyNonNullProperties(T source, T target) {
        if (source == null || target == null) {
            return;
        }

        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);  // 使得私有属性也可访问
                Object value = field.get(source);
                if (value != null) {
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    if (targetField != null) {
                        targetField.setAccessible(true);
                        targetField.set(target, value);
                    }
                }
            } catch (IllegalAccessException e) {
                System.err.println("Illegal Access: " + e.getMessage());
            } catch (NoSuchFieldException e) {
                System.err.println("No Such Field: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Exception occurred: " + e.getMessage());
            }
        }
    }
}