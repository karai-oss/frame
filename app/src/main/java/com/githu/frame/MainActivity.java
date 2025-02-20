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
import com.githu.comm.utils.DateUtils;
import com.githu.comm.utils.ImageUtils;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    public static CommentViewModel commentViewModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        commentViewModel = new CommentViewModel(getApplication()).getInstance();
        commentViewModel.activityMutableLiveData.setValue(this);

        new Thread(()->{
            ImageUtils.setImage(findViewById(R.id.imageview), R.mipmap.ic_launcher);
        }).start();

    }
}