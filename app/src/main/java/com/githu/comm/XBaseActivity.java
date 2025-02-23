package com.githu.comm;


import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.githu.comm.bind.IApplicationUtils;
import com.githu.comm.request.HttpUtils;
import com.githu.comm.request.IRequestCallBack;
import com.githu.comm.request.RequestCode;
import com.githu.frame.R;

/**
 * XBaseActivity  继承于 AppCompatActivity  是一个 活动基类
 *
 * @author Mr.xie
 * @Date 2025/2/19
 */
public abstract class XBaseActivity extends AppCompatActivity implements IRequestCallBack, IApplicationUtils {

    /**
     * HTTP 请求对象
     */
    public HttpUtils request;

    protected abstract void initData();

    public abstract void initView();

    protected abstract void useEvent();

    @LayoutRes
    public abstract int layoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        createHttpRequest();
        initData();
        initView();
        useEvent();

    }

    @Override
    public void requestSuccess(RequestCode successCode) {

    }

    @Override
    public void requestError(RequestCode errorCode) {

    }

    /**
     * 初始化Http请求
     */
    private void createHttpRequest() {
        HttpUtils.Builder httpBuilder = new HttpUtils.Builder();
        httpBuilder.setContext(this);
        httpBuilder.setTimeOut(2000);
        httpBuilder.setRequestCallBack(this);
        request = httpBuilder.build();
    }
}
