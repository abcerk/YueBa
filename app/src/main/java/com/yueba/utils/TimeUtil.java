package com.yueba.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by abcerk on 2016/6/1.
 */
public class TimeUtil {

    /**
     * 得到当前时间
     */
    public static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(new Date(System.currentTimeMillis()));
        return time;
    }
}
