package com.diary.bace.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class SetCreateAndUpdateUtil {

    private static final String dateFormat ="yyyy-MM-dd HH:mm:ss";


    public static void setCreateAndUpdateData(Object obj, boolean isAdd) {
        //本地缓存id
//        final Integer id = SecurityContext.getUser().getId();
        final Integer id = 1 ;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String now = sdf.format(new Date());

        if (isAdd) {
            setFieldValue(obj, "createUid", id + "");
            setFieldValue(obj, "createTime", now);
        }
        setFieldValue(obj, "updateUid", id + "");
        setFieldValue(obj, "updateTime", now);
    }

    /**
     * set属性的值到Bean
     *
     * @param bean
     * @param keyname
     * @param value
     */
    public static void setFieldValue(Object bean, String keyname, String value) {
        try {
            setFieldValue2(bean, keyname, value);
        } catch (ParseException ex) {
            log.error("set属性的值到Bean发生异常：" + ex.getMessage());
        } catch (Exception e) {
            log.error("set属性的值到Bean发生异常：" + e.getMessage());
        }
    }

    /**
     * set属性的值到Bean
     *
     * @param bean
     * @param keyname
     * @param value
     */
    public static void setFieldValue2(Object bean, String keyname, String value) throws Exception {
        Class<?> cls = bean.getClass();
        // 取出bean里的所有方法
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            try {
                String fieldSetName = parSetName(field.getName());
                if (!checkSetMet(methods, fieldSetName)) {
                    continue;
                }
                Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
                if (field.getName().equals(keyname)) {
                    if (StringUtils.isNotBlank(value)) {
                        String fieldType = field.getType().getSimpleName();
                        if ("String".equals(fieldType)) {
                            fieldSetMet.invoke(bean, value);
                        } else if ("Date".equals(fieldType)) {
                            Date temp = parseDateNew(value);
                            fieldSetMet.invoke(bean, temp);
                        }else if ("LocalDateTime".equals(fieldType)) {
                            LocalDateTime localDateTime = parseLocalDateTimeNew(value);
                            fieldSetMet.invoke(bean, localDateTime);
                        } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
                            Integer intval = Integer.parseInt(value);
                            fieldSetMet.invoke(bean, intval);
                        } else if ("Long".equalsIgnoreCase(fieldType)) {
                            Long temp = Long.parseLong(value);
                            fieldSetMet.invoke(bean, temp);
                        } else if ("Double".equalsIgnoreCase(fieldType)) {
                            Double temp = Double.parseDouble(value);
                            fieldSetMet.invoke(bean, temp);
                        } else if ("BigDecimal".equalsIgnoreCase(fieldType)) {
                            BigDecimal temp = new BigDecimal(value);
                            fieldSetMet.invoke(bean, temp);
                        } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                            Boolean temp = Boolean.parseBoolean(value);
                            fieldSetMet.invoke(bean, temp);
                        } else {
                            System.out.println("not supper type" + fieldType);
                        }
                    }
                }
            } catch (ParseException ex) {
                throw ex;
            } catch (Exception e) {
//                NumberFormatException
                log.info(e.getMessage());
                throw e;
            }
        }
    }

    private static LocalDateTime parseLocalDateTimeNew(String value) {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * 判断是否存在某属性的 set方法
     *
     * @param methods
     * @param fieldSetMet
     * @return boolean
     */
    public static boolean checkSetMet(Method[] methods, String fieldSetMet) {
        for (Method met : methods) {
            if (fieldSetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 拼接在某属性的 set方法
     *
     * @param fieldName
     * @return String
     */
    public static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "set" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
    }

    /**
     * 格式化string为Date
     *
     * @return date
     */
    public static Date parseDateNew(Object date) throws ParseException {

        if (null == date) {
            return null;
        }
        String datestr = date.toString();
        if ("".equals(datestr)) {

            return null;
        }
        try {
            String fmtstr = null;
            datestr = datestr.replace("/", "-");

            if (datestr.indexOf(':') > 0) {
                fmtstr = "dateFormat";
            } else {
                fmtstr = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr);
            return sdf.parse(datestr);
        } catch (ParseException ex) {
            throw ex;
        } catch (Exception e) {
            return null;
        }
    }
}
