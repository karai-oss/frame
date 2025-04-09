package com.githu.comm.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class Effector {


    public static void IoThread(Runnable runnable) {
        Handler.createAsync(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                runnable.run();
                return true;
            }
        }).sendMessage(Message.obtain());
    }
}
