package com.jiyuren.myapp.ui.main

import androidx.lifecycle.ViewModel
import com.jiyuren.myapp.ui.main.bean.Student

class MainViewModel : ViewModel() {
    val student: Student
        get() = Student.newInstance()
}