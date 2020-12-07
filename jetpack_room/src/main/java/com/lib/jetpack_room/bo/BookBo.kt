package com.lib.jetpack_room.bo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * foreignKeys:属性来设置外键
 *     1. NO_ACTION：当parent中的key有变化的时候child不做任何动作。
       2. RESTRICT：当parent中的key有依赖的时候禁止对parent做动作，做动作就会报错。
       3. SET_NULL：当paren中的key有变化的时候child中依赖的key会设置为NULL。
       4. SET_DEFAULT：当parent中的key有变化的时候child中依赖的key会设置为默认值。
       5. CASCADE：当parent中的key有变化的时候child中依赖的key会跟着变化
 */
@Entity(
    tableName = "book"
//    foreignKeys = [ForeignKey(
//        entity = UserBo::class,//entity：parent实体类(引用外键的表的实体)
//        parentColumns = ["id"],//parentColumns：parent外键列(要引用的外键列)
//        childColumns = ["user_id"],//childColumns：child外键列(要关联的列)
//        onDelete = ForeignKey.SET_NULL,//当parent里面有删除操作的时候，child表可以做的Action动作
//        onUpdate = ForeignKey.SET_NULL,//当parent里面有更新操作的时候，child表可以做的Action动作
//        deferred = false//默认值false，在事务完成之前，是否应该推迟外键约束(当我们启动一个事务插入很多数据的时候，事务还没完成之前。
//        // 当parent引起key变化的时候。可以设置deferred为true。让key立即改变。)
//    )]
)
class BookBo(

//    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String = "",

    @ColumnInfo(name = "user_id")
    @PrimaryKey
    val userId: Long = 0
)