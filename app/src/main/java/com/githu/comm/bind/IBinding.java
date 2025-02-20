package com.githu.comm.bind;


import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;

import androidx.viewbinding.ViewBinding;

import com.githu.comm.utils.ImageUtils;
import com.githu.comm.utils.StringUtils;

/**
 * 抽出实现ViewBinding 基类的 的方法
 *
 * @author Mr.xie
 * @Date 2025/2/20
 */
public interface IBinding<T extends ViewBinding> extends IApplicationUtils {

    T inflaterBinding();

    void initData();

    void initView();

    void useEvent();


    /**
     * 获取通用的ViewModel
     *
     * @param application 全局Application
     */
    public default CommentViewModel commentViewModel(Application application) {
        return new CommentViewModel(application).getInstance();
    }




}
