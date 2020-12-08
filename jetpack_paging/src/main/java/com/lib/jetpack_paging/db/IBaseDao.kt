package com.lib.jetpack_paging.db

import androidx.room.*

interface IBaseDao<T> {

    /**
     * onConflict：默认值是OnConflictStrategy.ABORT，表示当插入有冲突的时候的处理策略。
     *     OnConflictStrategy封装了Room解决冲突的相关策略：
           1. OnConflictStrategy.REPLACE：冲突策略是取代旧数据同时继续事务。
           2. OnConflictStrategy.ROLLBACK：冲突策略是回滚事务。
           3. OnConflictStrategy.ABORT：冲突策略是终止事务。
           4. OnConflictStrategy.FAIL：冲突策略是事务失败。
           5. OnConflictStrategy.IGNORE：冲突策略是忽略冲突。
     *@return 当@Insert注解的方法只有一个参数的时候，这个方法也可以返回一个long，有多个参数的时候则可以返回long[]或者r List<Long>。long都是表示插入的rowId。
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(value: T): Long

    /**
     * 通过参数里面的primary key找到要删除的行
     * @return 删除了多少行
     */
    @Delete
    fun delete(value: T): Int

    /**
     * Room会把对应的参数信息更新到数据库里面去(会根据参数里面的primary key做更新操作)。
     * @return 更新了多少行
     */
    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(value: T): Int

}