package com.plug.okhttp_module.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式 OkHttp
 * Created by A35 on 2020/3/20
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class TaskManager implements IBaseTask {
    private List<IBaseTask> iBaseTaskList = new ArrayList<>();
    private int index = 0;

    void addTask(IBaseTask iBaseTask) {
        iBaseTaskList.add(iBaseTask);
    }

    @Override
    public void doAction(String isTask, IBaseTask iBaseTask) {
        if (iBaseTaskList.isEmpty()) {
            // 抛出异常..
            return;
        }
        if (index >= iBaseTaskList.size()) {
            return;
        }
        IBaseTask task = iBaseTaskList.get(index);
        index++;
        task.doAction(isTask, iBaseTask);
    }
}
