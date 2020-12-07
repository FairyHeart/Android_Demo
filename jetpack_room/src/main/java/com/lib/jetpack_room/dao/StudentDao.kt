package com.lib.jetpack_room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lib.jetpack_room.bo.Student

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/7.
 */
@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("select * from student")
    fun getStudents(): MutableList<Student>

    @Query("select * from student")
    fun getStudentsLiveData(): LiveData<MutableList<Student>>

    @Query("select * from student where id=:id")
    fun getStudentById(id: Int): Student?
}