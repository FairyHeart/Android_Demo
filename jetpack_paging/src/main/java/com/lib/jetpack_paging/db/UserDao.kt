package com.lib.jetpack_paging.db

import androidx.room.Dao
import androidx.room.Query
import com.lib.jetpack_paging.bo.UserBo

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/8.
 */
@Dao
interface UserDao : IBaseDao<UserBo> {

    @Query("select * from user ")
    fun queryAllUser(): MutableList<UserBo>

    @Query("select * from user order by id limit :size offset :index")
    fun queryUser(index: Int, size: Int): MutableList<UserBo>
}