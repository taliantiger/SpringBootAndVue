package org.talian.common;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 前端输入的日期，如beginDate, 会首先被格式化为字符串;
 * 后端接受后字符串数据后，必须把字符串转为匹配的 Date 类型，
 * 否则会导致添加新职员失败，报错“未知错误”。
 */
public class DateConverter implements Converter<String, Date> {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String s) {
        if("".equals(s) || s == null) {
            return null;
        }
        try{
            return simpleDateFormat.parse(s);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
