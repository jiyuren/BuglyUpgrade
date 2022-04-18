package com.jiyuren.myapp.ui.main.bean

/**
 * @author jiyuren on 2022-04-15 10:16
 * @name com.jiyuren.myapp.ui.main.bean.Student
 * @email 1126618648@qq.com
 * @descr Student
 * @copyright www.juming.com
 */
data class Student(val name: String?,val sex: String?,val age:Int = 0) {


    companion object {
        @JvmStatic
        fun newInstance(): Student {
            return Student("lisi","male",18)
        }
    }
}