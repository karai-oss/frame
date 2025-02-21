package com.githu.frame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
import com.githu.comm.request.RequestCode;
import com.githu.comm.utils.DateUtils;
import com.githu.comm.utils.ImageUtils;
import com.githu.frame.databinding.ActivityMainBinding;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends XBaseBindingActivity<ActivityMainBinding> {


    @Override
    public ActivityMainBinding inflaterBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initData() {


        Log.e("TAG", "initData: "+dateUtils(this).dateFormat(new Date()));
    }

    @Override
    public void initView() {

    }

    @Override
    public void useEvent() {

    }

    @Override
    public void requestSuccess(RequestCode successCode) {
        super.requestSuccess(successCode);


        Log.e("TAG", "requestSuccess: " + successCode.msg );
    }

    @Override
    public void requestError(RequestCode errorCode) {
        super.requestError(errorCode);
        Log.e("TAG", "requestError: " + errorCode.msg );
    }
}