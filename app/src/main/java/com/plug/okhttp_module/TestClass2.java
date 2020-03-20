package com.plug.okhttp_module;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程
 * Created by A35 on 2020/3/20
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class TestClass2 {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        /**
         * todo 参数一 核心线程数
         * todo 参数二 最大线程数
         * todo 参数三、四 保持时间，时间单位  参数1 ,只要Runnable > 参数1 起作用(60s之内 就会复用之前的任务，60s之后就会回收任务)
         * 正在执行的任务Runnable10 > corePoolSize --> 参数三/参数四 才会起作用
         * 作用：Runnable1执行完毕后 闲置60s，如果过了闲置60s,会回收掉Runnable1任务,，如果在闲置时间60s 复用此线程Runnable1
         * todo 参数五：workQueue队列 ：会把超出的任务加入到队列中 缓存起来
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,
                Integer.MAX_VALUE,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());


        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("runnable" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPoolExecutor.execute(runnable);
        }

        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
