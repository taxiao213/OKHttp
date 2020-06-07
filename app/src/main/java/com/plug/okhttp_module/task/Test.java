package com.plug.okhttp_module.task;

import android.os.Build;

import com.plug.okhttp_module.AClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

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

        Integer integer = new Integer(300);
        int a = 300;
        System.out.println(integer == a);
        System.out.println(integer.equals(a));

        Integer x = new Integer(200);
        Integer y = new Integer(200);
        String z = new String("string");
        System.out.println(x == y); // true
        System.out.println(x.equals(y)); // true

        threadRun();

        new Thread() {
            @Override
            public void run() {
                super.run();
                AClass instanceA = AClass.getInstanceA();

                AClass.getStringA();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                AClass.getStringB();
            }
        }.start();

    }


    private static void threadRun() {
        new Runnable() {
            @Override
            public void run() {

            }
        };

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 0; i < 20; i++) {
                    System.out.println(i);
                }
                return null;
            }
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);

        new Thread(futureTask).start();
        try {
            futureTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕");

//       new Thread(){
//           @Override
//           public void run() {
//               for (int i = 0; i < 20; i++) {
//                   System.out.println("Thread "+i);
//               }
//               super.run();
//           }
//       }.start();

        System.out.println("执行完毕" + Math.round(1.2));// 1
        System.out.println("执行完毕" + Math.round(1.5));// 2
        System.out.println("执行完毕" + Math.round(1.7));// 3
        System.out.println("执行完毕" + Math.round(-1.7));// -2
        System.out.println("执行完毕" + Math.round(-1.5));// -1
        System.out.println("执行完毕" + Math.round(-1.2));// -1
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
            Type parameterizedType = genericParameterTypes[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                System.out.println(": " + parameterizedType.getTypeName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
