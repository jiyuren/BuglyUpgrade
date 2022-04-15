package com.jiyuren.myapp;

import org.junit.Test;

import static org.junit.Assert.*;

import com.jiyuren.myapp.ui.main.bean.Student;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_lombok(){
        System.out.println(Student.newInstance());

    }
}