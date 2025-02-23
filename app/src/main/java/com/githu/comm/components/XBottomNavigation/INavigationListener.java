package com.githu.comm.components.XBottomNavigation;

import android.view.View;

/**
 * 自定义底部导航组件的统一监听器
 *
 * @author <a href="https://github.com/karai-oss">Mr.xie</a>
 * @Date 2025/2/23
 */
public interface INavigationListener {

    public void selectTab(int position , View view);
}
