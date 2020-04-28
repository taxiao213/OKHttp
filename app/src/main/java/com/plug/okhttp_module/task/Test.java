package com.plug.okhttp_module.task;

import android.os.Build;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
        reflectMethod();

    }

    // 通过反射获取方法参数类型
    private static void reflectMethod() {
        try {
            Class<?> aClass = Class.forName("com.plug.okhttp_module.task.Task3");
            // 通过构造初始化对象
            Constructor<?> constructor = aClass.getConstructor(boolean.class);
            Object instance = constructor.newInstance(true);
            Method test = aClass.getDeclaredMethod("test", String.class, int.class, boolean.class);
            test.setAccessible(true);
            Object invoke = test.invoke(instance, "12", 10, true);
            Type[] genericParameterTypes = test.getGenericParameterTypes();
            Type parameterizedType =  genericParameterTypes[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                System.out.println(": " + parameterizedType.getTypeName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
