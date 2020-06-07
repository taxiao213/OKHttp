package com.plug.okhttp_module;

import android.app.Activity;
import android.content.IntentFilter;

/**
 * Created by A35 on 2020/6/5
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class AClass {

    private static volatile AClass aClass;
    private String name;

    private AClass() {

    }

    public static AClass getInstanceA() {
        if (aClass == null) {
            synchronized (AClass.class) {
                if (aClass == null) {
                    aClass = new AClass();
                }
            }
        }
        return aClass;
    }

    public final static synchronized void getStringB() {
        System.out.println("bbb");
    }

    public static synchronized void getStringA() {
        System.out.println("aaa");
    }


    void getStringC() {
        System.out.println("aaa");
    }

    protected void getStringD() {
        System.out.println("aaa");
    }

    public interface cc {

    }
}
