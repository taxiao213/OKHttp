package com.plug.okhttp_module.task1;

/**
 * 责任链模式 OkHttp
 * Created by A35 on 2020/3/20
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public interface IBaseTask {

    /**
     * @param isTask    是否有能力执行
     * @param iBaseTask 下一个任务节点
     */
    public void doAction(String isTask, IBaseTask iBaseTask);
}
