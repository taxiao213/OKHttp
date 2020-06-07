package com.plug.okhttp_module.task;

/**
 * Created by A35 on 2020/5/26
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class ThreadTest {

    public static void main(String[] args) {
        new ThreadTest().init();
    }

    public void init() {
        final Business business = new Business();
        new Thread(
                new Runnable() {
                    public void run() {
                        for (int i = 0; i < 50; i++) {
                            business.SubThread(i);
                        }
                    }
                }
        ).start();

        for (int i = 0; i < 50; i++) {
            business.MainThread(i);
        }
    }

    private class Business {
        boolean bShouldSub = true;//这里相当于定义了控制该谁执行的一个信号灯

        public synchronized void MainThread(int i) {
            if (bShouldSub)
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            for (int j = 0; j < 5; j++) {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            bShouldSub = true;
            this.notify();
        }


        public synchronized void SubThread(int i) {
            if (!bShouldSub)
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            for (int j = 0; j < 10; j++) {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            bShouldSub = false;
            this.notify();
        }
    }
}
