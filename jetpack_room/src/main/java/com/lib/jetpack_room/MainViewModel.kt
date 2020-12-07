package com.lib.jetpack_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lib.jetpack_room.bo.Student

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/7.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val studentDao = AppDataBase.instance.studentDao()

    var studentsLiveData: LiveData<MutableList<Student>> =
        AppDataBase.instance.studentDao().getStudentsLiveData()

    val showStudent = MutableLiveData<String>()

    private val student = Student(name = "liu", age = "20")

    fun insert() {
        studentDao.insertStudent(student)
        show()
    }

    fun delete() {
        studentDao.deleteStudent(student)
        show()
    }

    fun update() {
        student.name = "Zhang San"
        student.age = "30"
        studentDao.updateStudent(student)
        show()
    }

    fun queryById(id: Int) {
        val student = studentDao.getStudentById(id)
        showStudent.value = student?.toString()
    }

    private fun show() {
        val students = studentDao.getStudents()
        showStudent.value = students.toString()
    }
}