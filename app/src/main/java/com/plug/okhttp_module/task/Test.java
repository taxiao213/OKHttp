package com.plug.okhttp_module.task;

/**
 * 责任链模式
 * Created by A35 on 2020/3/20
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class Test {
    public static void main(String[] args) {
        Task1 task1 = new Task1(false);
        Task2 task2 = new Task2(true);
        Task3 task3 = new Task3(false);

        task1.addNextTask(task2);
        task2.addNextTask(task3);
        task1.action();
    }
}
