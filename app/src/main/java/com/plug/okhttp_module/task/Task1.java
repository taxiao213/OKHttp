package com.plug.okhttp_module.task;

/**
 * 责任链模式
 * Created by A35 on 2020/3/20
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class Task1 extends BaseTask {
    public Task1(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println("Task1");
    }
}
