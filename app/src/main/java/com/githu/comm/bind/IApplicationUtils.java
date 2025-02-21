package com.githu.comm.bind;




import android.content.Context;

import com.githu.comm.utils.DateUtils;
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
    public default DateUtils dateUtils(Context context) {
        return  DateUtils.getInstance(context);
    }

}
