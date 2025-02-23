package com.githu.comm.components.XLoading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.githu.frame.R;

/**
 * 自定义加载组件
 *
 * @author <a href="https://github.com/karai-oss">Mr.xie</a>
 * @Date 2025/2/23
 */
public class XLoading extends Dialog {


    public XLoading(@NonNull Context context) {
        super(context);
        DefualtLoadingView defualtLoadingView = new DefualtLoadingView(getContext());
        asLoading(defualtLoadingView);
    }


    /**
     * 自定义给定加载布局
     *
     * @param view 加载视图
     */
    public XLoading asLoading(View view) {
        this.addContentView(view, view.getLayoutParams());
        return this;
    }

    public void show() {
        setCanceledOnTouchOutside(false);
        super.show();
    }




}
