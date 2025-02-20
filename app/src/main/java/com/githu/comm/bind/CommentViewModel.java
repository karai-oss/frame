package com.githu.comm.bind;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

/**
 * Android 自定义项目框架通用ViewModel
 *
 * @author Mr.xie
 * @Date 2025/2/20
 */
public class CommentViewModel extends AndroidViewModel {
    public CommentViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Activity> activityMutableLiveData = new MutableLiveData<>();

    public CommentViewModel getInstance() {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CommentViewModel.class);
    }


}
