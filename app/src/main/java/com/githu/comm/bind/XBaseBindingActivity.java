package com.githu.comm.bind;


import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.githu.comm.request.HttpUtils;
import com.githu.comm.utils.ImageUtils;

/**
 * XBaseActivity  继承于 AppCompatActivity  是一个 活动基类  集成了 ViewBinding的能力
 *
 * @author Mr.xie
 * @Date 2025/2/19
 */
public abstract class XBaseBindingActivity<T extends ViewBinding> extends AppCompatActivity implements IBinding<T> {

    public T binding = null;

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflaterBinding();
        initView();
        useEvent();
        HttpUtils.Builder httpBuilder = new HttpUtils.Builder();
        httpBuilder.setContext(this);
        httpBuilder.setTimeOut(2000);
    }

    public CommentViewModel commViewModel(){
        CommentViewModel.activityMutableLiveData.setValue(this);
        return commentViewModel(getApplication());
    }



}
