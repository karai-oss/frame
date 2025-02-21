package com.githu.comm.bind;


import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.githu.comm.request.HttpUtils;
import com.githu.comm.request.IRequestCallBack;
import com.githu.comm.request.RequestCode;
import com.githu.comm.utils.ImageUtils;

/**
 * XBaseActivity  继承于 AppCompatActivity  是一个 活动基类  集成了 ViewBinding的能力
 *
 * @author Mr.xie
 * @Date 2025/2/19
 */
public abstract class XBaseBindingActivity<T extends ViewBinding> extends AppCompatActivity implements IBinding<T>, IRequestCallBack {

    public T binding = null;

    public HttpUtils request;

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflaterBinding();
        createHttpRequest();
        initView();
        useEvent();

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

    public CommentViewModel commViewModel() {
        CommentViewModel.activityMutableLiveData.setValue(this);
        return commentViewModel(getApplication());
    }


    @Override
    public void requestSuccess(RequestCode successCode) {

    }

    @Override
    public void requestError(RequestCode errorCode) {

    }
}
