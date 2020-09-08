package com.bat.thinkinginjava.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 *
 * @author ZhengYu
 * @version 1.0 2020/9/5 11:32
 **/
public class TimeUtil {

    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String nowFormat() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }
}
