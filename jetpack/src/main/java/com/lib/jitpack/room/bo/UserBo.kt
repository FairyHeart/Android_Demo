package com.lib.jitpack.room.bo

import android.graphics.Bitmap
import androidx.room.*
import com.lib.jitpack.room.Converters
import java.util.*

/**
 * tableName：设置表名字。默认是类的名字
 * primaryKeys:属性来设置主键(复合主键)
 * indices:属性来给表格添加索引,unique数学来设置是否唯一索引
 *
 */
@Entity(
    tableName = "user",
//    primaryKeys = ["first_name", "last_name"]
    indices = [
        Index(value = ["first_name"], unique = true),
        Index(value = ["last_name", "age"])//addressBo对象不能设置索引
    ]
)
data class UserBo(
    //primaryKeys属性来设置单个主键,autoGenerate自增长的字段,复合主键和单个主键不能同时存在，如果需要设置多个主键只能试用复合主键
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    //@ColumnInfo注解来自定义表中列的名字
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,

    val age: Int = 0,

    //@Embedded注解来表示嵌入,prefix：如果实体具有多个相同类型的嵌入字段，则可以通过设置前缀属性来保持每个列的唯一性
    @Embedded(prefix = "")
    val addressBo: AddressBo? = null,

    val birthday: Date? = null
) {
    //@Ignore所以该字段不会映射到User表中
    @Ignore
    val picture: Bitmap? = null
}
