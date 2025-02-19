package com.githu.comm.bind;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * XBaseActivity  继承于 AppCompatActivity  是一个 活动基类  集成了 ViewBinding的能力
 *
 * @author Mr.xie
 * @Date 2025/2/19
 */
public class XBaseBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
