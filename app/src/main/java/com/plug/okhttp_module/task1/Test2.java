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
//        thread.start();

        int[] sorted = bubbleSort(new int[]{5, 2, 0, 1, 5});
        for (int i : sorted) {
            System.out.print(i + "\r\n");
        }
    }

    /**
     * 冒泡排序
     */
    public static int[] bubbleSort(int[] arr) {
        // 外层循环控制比较轮数
        for (int i = 0; i < arr.length; i++) {
            // 内层循环控制每轮比较次数
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 按照从小到大排列
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * <p>
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * <p>
     * 针对所有的元素重复以上的步骤，除了最后一个。
     *
     * @param arr
     */
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
