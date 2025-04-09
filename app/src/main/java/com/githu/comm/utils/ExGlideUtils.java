package com.githu.comm.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.lang.reflect.Constructor;

/**
 * 自定义扩展Glide 设置类
 * <p>
 * 该类是直接给予Glide RequestBuilder 这个请求去获取的
 * </p>
 *
 * @author Mr.xie
 * @Date 2025/3/28
 */
public class ExGlideUtils<T> {

    private RequestBuilder requestBuilder = null;

    private RequestOptions requestOptions = null;


    private Glide glide;

    private RequestBuilder loadingRequestBuilder(Class<T> clazz, Context context) {
        glide = Glide.get(context);
        RequestManager with = Glide.with(context);

        try {
            Constructor<RequestBuilder> declaredConstructor = RequestBuilder.class.getDeclaredConstructor(Glide.class, RequestManager.class, clazz.getClass(), Context.class);
            declaredConstructor.setAccessible(true);
            requestBuilder = declaredConstructor.newInstance(glide, with, clazz, context);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return requestBuilder;
    }

    public ExGlideUtils initRequestOptions(RequestOptions requestOptions) {
        this.requestOptions = requestOptions;
        requestBuilder.apply(this.requestOptions);
        return this;
    }


    /**
     * 设置到ImageView上
     *
     * @param image ImageView 控件
     * @param url   图片网络地址
     */
    public void setImage(ImageView image, String url) {
        if (requestBuilder == null) {
            return;
        }
        this.requestBuilder.load(url).into(image);
    }


    /**
     * 给任何一个View 去设置网络背景图片
     *
     * @param view 任何View控件
     * @param url  图片网络地址
     */
    public void setViewImage(View view, String url) {
        this.requestBuilder.load(url)
                .into(new CustomTarget<T>() {
                    @Override
                    public void onResourceReady(T resource, Transition<? super T> transition) {

                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        view.setBackground(placeholder);
                    }
                });
    }

    public static ExGlideUtils<Bitmap> asBitmap(Context context) {
        ExGlideUtils<Bitmap> exGlideUtils = new ExGlideUtils<Bitmap>();
        exGlideUtils.loadingRequestBuilder(Bitmap.class, context);
        return exGlideUtils;
    }


    public static ExGlideUtils<Drawable> asDrawable(Context context) {
        ExGlideUtils<Drawable> exGlideUtils = new ExGlideUtils<Drawable>();
        exGlideUtils.loadingRequestBuilder(Drawable.class, context);
        return exGlideUtils;
    }

    /**
     * 加载圆角图片
     * @param context 上下文对象
     */
    public static ExGlideUtils<Drawable> asCircleDrawable(Context context){
        ExGlideUtils<Drawable> exGlideUtils = new ExGlideUtils<Drawable>();
        exGlideUtils.loadingRequestBuilder(Drawable.class, context);
        exGlideUtils.initRequestOptions(RequestOptions.circleCropTransform());
        return exGlideUtils;
    }

    public ExGlideUtils clearCache(boolean isClearCache) {
        if (isClearCache) glide.clearDiskCache();
        return this;
    }
}
