package com.plug.okhttp_module.task;

import android.content.UriMatcher;
import android.content.res.ColorStateList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by A35 on 2020/5/23
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public abstract class Atest {

    public Atest() {

    }

    abstract void vode();

    public static void main(String args[]) throws Exception {
        test();
        Integer a = new Integer(999);
        Integer b = new Integer(999);
        System.out.println(a == b);
        Integer c = 888;
        Integer d = 888;
        System.out.println(c == d);

        Student student1 = new Student("啊啊啊", "222");
        Student xiaoming = new Student("小明", "22");
        student1.setStu(xiaoming);
        System.out.println(" == student1 " + student1.toString());
        Student student2 = student1.clone();
        System.out.println(" == student2 " + student2.toString());
        if (1 > 0 & 2 > 0) {
            System.out.println("是都是");
        }
        if (1 > 0 & 2 < 0) {
            System.out.println("是都是ss");
        }

        if (1 > 0 && 2 > 0) {
            System.out.println("是都是et");
        }
        if (1 > 0 && 2 < 0) {
            System.out.println("是都是ss");
        }


        int anInt = Integer.parseInt("155abd", 16);
        System.out.println(anInt);

        String s1 = "a";
        String s2 = s1 + "b";
        String s3 = "a" + "b";
        System.out.println(s2 == "ab");// false
        System.out.println(s3 == "ab");// true

        String s = "a" + "b" + "c" + "d";
        System.out.println(s == "abcd");// true
        System.out.println("testA ==  " + testA());
        System.out.println("testB ==  " + testB());

        testC();
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // 序列化过程
        Student student = new Student("12", "2");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cache.text"));
        outputStream.writeObject(student);
        outputStream.close();

        // 反序列化过程
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("cache.text"));
        Student st = (Student) inputStream.readObject();
        inputStream.close();
    }




    private static void testC() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
        } finally {
            System.out.println("finally");
        }
    }

    static int testB() {
        try {
            return func1();
        } finally {
            return func2();
        }
    }

    static int func1() {
        System.out.println("func1");
        return 1;
    }

    static int func2() {
        System.out.println("func2");
        return 2;
    }

    static int testA() {
        int x = 1;
        try {
            System.out.println("try");
            return x;
        } finally {
            ++x;
            System.out.println("finally");
        }
    }

    public static void test() {
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
//        for (int i = 0; i < 10; i++) {
//            fixedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("newFixedThreadPool threadName -- " + Thread.currentThread().getName());
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            cachedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("newCachedThreadPool threadName -- " + Thread.currentThread().getName());
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("newSingleThreadExecutor threadName -- " + Thread.currentThread().getName());
                }
            });
        }


        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("newScheduledThreadPool threadName -- " + Thread.currentThread().getName());
                }
            });
        }

        ExecutorService workStealingPool = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            workStealingPool = Executors.newWorkStealingPool();
            for (int i = 0; i < 10; i++) {
                workStealingPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("newWorkStealingPool threadName -- " + Thread.currentThread().getName());
                    }
                });
            }
        }


        ExecutorService singleThreadScheduled = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 10; i++) {
            singleThreadScheduled.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("newSingleThreadScheduledExecutor threadName -- " + Thread.currentThread().getName());
                }
            });
        }

        try {
            int w = 1 / 0;
            System.out.println("11111111");
        } catch (Exception e) {
            System.out.println("222222");
        } finally {
            System.out.println("3333");

        }

    }
}
