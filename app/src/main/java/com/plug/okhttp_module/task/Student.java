package com.plug.okhttp_module.task;


import java.io.Serializable;

/**
 * Created by A35 on 2020/5/25
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/taxiao213
 */
public class Student implements Cloneable , Serializable {
    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -6849794470754667710L;

    String name;
    String sex;
    Student stu;

    public Student(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {
        Student s = null;
        s = (Student) super.clone();
        if (s.stu != null) {
            s.stu = stu.clone();
        }
        return s;

    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", stu=" + stu +
                '}' + " -- " + this.hashCode();
    }
}
