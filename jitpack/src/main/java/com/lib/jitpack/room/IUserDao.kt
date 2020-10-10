package com.lib.jitpack.room

import androidx.room.*

@Dao
interface IUserDao {

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

    @Query("select * from user")
    fun selectAll(): MutableList<User>?

    @Query("select * from user where userName = :userId")
    fun selectById(userId: String): User?
}