package com.plug.okhttp_module.task1;

import android.text.TextUtils;

/**
 * 责任链模式 OkHttp
 * Created by A35 on 2020/3/20
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class task2 implements IBaseTask {
    @Override
    public void doAction(String isTask, IBaseTask iBaseTask) {
        if (isTask.equals("ok")) {
            System.out.println("被拦截task2");
            return;
        } else {
            // 继续执行下一个链条的任务节点  TaskManager.doAction("ok", TaskManager)
            // TaskManager.doAction
            iBaseTask.doAction(isTask, iBaseTask);
        }
    }
}
