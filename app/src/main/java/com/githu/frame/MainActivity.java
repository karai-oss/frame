package com.githu.frame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.githu.comm.asyc.XHandle;
import com.githu.comm.bind.CommentViewModel;
import com.githu.comm.bind.XBaseBindingActivity;
import com.githu.comm.utils.DateUtils;
import com.githu.comm.utils.ImageUtils;
import com.githu.frame.databinding.ActivityMainBinding;

import java.util.Date;


public class MainActivity extends XBaseBindingActivity<ActivityMainBinding> {


    @Override
    public ActivityMainBinding inflaterBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initData() {



    }

    @Override
    public void initView() {

    }

    @Override
    public void useEvent() {

    }
}