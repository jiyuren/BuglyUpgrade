package com.jiyuren.myapp.ui.main;

import androidx.lifecycle.ViewModel;

import com.jiyuren.myapp.ui.main.bean.Student;

public class MainViewModel extends ViewModel {
    public Student getStudent(){
        return Student.newInstance();
    }

}