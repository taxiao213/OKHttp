package com.plug.okhttp_module;

import android.app.Application;
import android.util.Log;

/**
 * 捕获异常
 * Created by A35 on 2020/4/2
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();
        // 设置捕获异常
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("myApplication", " ThreadName = " + t.getName() + " error == " + e.getMessage());
    }
}
