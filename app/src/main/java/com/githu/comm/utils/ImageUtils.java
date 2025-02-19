package com.githu.comm.utils;


import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.githu.comm.asyc.XHandle;

/**
 * 图片工具类
 *
 * @author Mr.xie
 * @Date 2025/2/19
 */
public class ImageUtils {


    /**
     * 通过Uri设置ImageView
     *
     * @param view ImageView 控件
     * @param uri  图片Uri
     */
    public static void setImage(ImageView view, Uri uri) {
        new XHandle().post(() -> {
            view.setImageURI(uri);
        });
    }

    public static void setImage(ImageView view, @DrawableRes int id) {
        new XHandle().postAtFrontOfQueue(() -> {
            view.setBackgroundResource(id);
        });
    }


    /**
     * 通过位图设置图片
     *
     * @param view     ImageView控件
     * @param mipmapId mipMap资源ID
     */
    public static void setBitmapImage(ImageView view, int mipmapId) {
        new XHandle().post(() -> {
            view.setBackgroundResource(mipmapId);
        });
    }
}
