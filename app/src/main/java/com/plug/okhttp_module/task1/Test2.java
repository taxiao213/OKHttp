package com.plug.okhttp_module.task1;


/**
 * 责任链模式 OkHttp
 * Created by A35 on 2020/3/20
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class Test2 {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new task1());
        taskManager.addTask(new task2());
        taskManager.addTask(new task3());
        taskManager.doAction("ok", taskManager);
        System.out.println(Integer.MAX_VALUE >> 2);
        System.out.println(Integer.MAX_VALUE);

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    System.out.println("run...");
                }
            }
        };
        // 设置守护线程，
        // true 当Jvm退出时，线程也中止，
        // false Jvm退出时，线程还在运行
        thread.setDaemon(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
    }

}
