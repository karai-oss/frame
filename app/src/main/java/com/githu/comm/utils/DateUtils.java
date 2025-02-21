package com.githu.comm.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import com.githu.frame.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 * @author Mr.xie
 * @Date 2025/2/19
 */
public class DateUtils {
    public static final List<String> dateFormatConfiguration = List.of(
            "YYYY-MM-dd",
            "YYYY-MM-dd HH:mm:ss",
            "YYYY-MM-dd hh:mm:ss",
            "YYYY/MM/dd",
            "YYYY/MM/dd HH:mm:ss",
            "YYYY/MM/dd hh:mm:ss",
            "YYYY年MM月dd日",
            "YYYY年MM月dd日 HH:mm:ss",
            "YYYY年MM月dd日 hh:mm:ss",
            ""
    );

    private static Context _context;

    private static DateUtils instance = null;

    public static DateUtils getInstance(Context context) {

        _context = context;
        if (instance == null) {
            instance = new DateUtils();
        }
        return instance;
    }

    /**
     * 格式化日期
     *
     * @param date       需要格式话的日期 Date对象
     * @param formatCode 格式代码  对应 <code>dateFormatConfiguration</code> 中的索引值
     */
    public String dateFormat(Date date, int formatCode) {
        if (formatCode < 0 || formatCode >= dateFormatConfiguration.size()) {
            return null;
        }
        String formatStr = dateFormatConfiguration.get(formatCode);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }


    /**
     * 格式化日期
     *
     * @param date       需要格式话的日期 Date对象
     * <code>R.string.date_format</code> 格式代码  app 模块下面的 <code>resValue "string" , "date_format" , "0"</code> 设置
     *  对应 <code>dateFormatConfiguration</code> 中的索引值
     */
    public String dateFormat(Date date)  {
        try {
            String code = _context.getResources().getString(R.string.date_format);
            if (!TextUtils.isDigitsOnly(code)) {
                Log.e("TAG", "dateFormat: 请再build.gradle文件中填写正确的日期格式代码 "  );
                return "";
            }

            int formatCode = Integer.parseInt(code);
            if (formatCode < 0 || formatCode >= dateFormatConfiguration.size()) {
                return null;
            }
            String formatStr = dateFormatConfiguration.get(formatCode);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            return sdf.format(date);
        } catch (Resources.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
