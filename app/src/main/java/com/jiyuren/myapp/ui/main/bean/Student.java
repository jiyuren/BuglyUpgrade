package com.jiyuren.myapp.ui.main.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author jiyuren on 2022-04-15 10:16
 * @name com.jiyuren.myapp.ui.main.bean.Student
 * @email 1126618648@qq.com
 * @descr Student
 * @copyright www.juming.com
 */
@Data
@ToString
public class Student {
    private String name;
    private String sex;
    private int age;

    public static Student newInstance(){
        Student student = new Student();
        student.setAge(20);
        student.setName("lisi");
        student.setSex("male");
        return student;
    }

}
