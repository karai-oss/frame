package com.githu.comm.bind;


import android.text.format.DateUtils;

import com.githu.comm.utils.ImageUtils;
import com.githu.comm.utils.StringUtils;

/**
 * @author Mr.xie
 * @Date 2025/2/20
 */
public interface IApplicationUtils {

    public default ImageUtils imageUtils() {
        return new ImageUtils();
    }

    public default StringUtils stringUtils() {
        return new StringUtils();
    }
    public default DateUtils dateUtils() {
        return new DateUtils();
    }

}
