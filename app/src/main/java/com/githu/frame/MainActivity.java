package com.githu.frame;

import android.util.Log;
import android.view.View;

import com.githu.comm.XBaseActivity;
import com.githu.comm.components.XBottomNavigation.INavigationListener;
import com.githu.comm.components.XBottomNavigation.XBottomNavigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kotlin.Triple;


public class MainActivity extends XBaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        XBottomNavigation xbt = findViewById(R.id.xbt_bt);
        List<Triple<String , Integer ,Integer>> menus = new ArrayList<>();

        Collections.addAll(
                menus ,
                new Triple<String ,Integer ,Integer>( "首页" , R.mipmap.home_selected ,R.mipmap.home_unselected),
                new Triple<String ,Integer ,Integer>( "列表" , R.mipmap.list_selected ,R.mipmap.list_unselected),
                new Triple<String ,Integer ,Integer>( "消息" , R.mipmap.mine ,R.mipmap.mine),
                new Triple<String ,Integer ,Integer>( "我的" , R.mipmap.mine ,R.mipmap.mine)
        );
        xbt.setInnerLayoutView(R.layout.layout_xbt_item, menus);

        xbt.setNavigationListener(new INavigationListener() {
            @Override
            public void selectTab(int position, View view) {
                Log.e("TAG", "selectTab: " + position );
            }
        });
    }

    @Override
    protected void useEvent() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }
}