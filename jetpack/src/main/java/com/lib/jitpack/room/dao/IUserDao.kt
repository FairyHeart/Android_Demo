package com.lib.jitpack.room.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.lib.jitpack.room.bo.BookBo
import com.lib.jitpack.room.bo.UserBo
import com.lib.jitpack.room.vo.UserVo
import io.reactivex.Flowable

@Dao
interface IUserDao : IBaseDao<UserBo> {

    //查询全部
    @Query("select * from user")
    fun selectAll(): MutableList<UserBo>?

    //通过单个参数查询
    @Query("select * from user where id = :id")
    fun selectById(id: String): UserBo?

    //通过多个参数查询
    @Query("select * from user where first_name like :name or last_name like :name")
    fun findUserWithName(name: String): MutableList<UserBo>?

    //查询返回某些特定的列信息
    @Query("select id as userId,first_name as firstName,city from user")
    fun selectUserVos(): MutableList<UserVo>?


    //查询的时候传递一组参数
    @Query("select  id as userId,first_name as firstName,city from user where first_name in (:names)")
    fun selectUserVos(names: MutableList<String>): MutableList<UserVo>?

    //返回LiveData
    @Query("select  id as userId,first_name as firstName,city from user")
    fun selectLiveDataUserVos(): LiveData<MutableList<UserVo>>?

    //返回RxJava
    @Query("select * from user")
    fun loadUser(): Flowable<MutableList<UserBo>?>?

    //查询结果直接返回Cursor
    @Query("select * from user where age > :minAge limit 5")
    fun loadRawUsersOlderThan(minAge: Int): Cursor?

    //多表查询
    @Query("select b.* from book b left join user u on u.id = b.user_id where u.first_name like :userName")
    fun findBookByUserName(userName: String): MutableList<BookBo>?
}