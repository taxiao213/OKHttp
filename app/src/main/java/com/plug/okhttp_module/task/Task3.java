package com.plug.okhttp_module.task;

/**
 * 责任链模式
 * Created by A35 on 2020/3/20
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class Task3 extends BaseTask {
    public Task3(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println("Task3");
    }

    public void test(String st, int a, boolean is) {
        System.out.println(":st == " + st + " a == " + a + " boolean == " + is);
    }
}
