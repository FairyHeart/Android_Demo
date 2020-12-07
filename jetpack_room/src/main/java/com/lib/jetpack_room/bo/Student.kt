package com.lib.jetpack_room.bo

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * tableName：设置表名字。默认是类的名字
 * primaryKeys:属性来设置主键(复合主键)
 * indices:属性来给表格添加索引,unique数学来设置是否唯一索引
 *
 */
@Entity(
    tableName = "student",
//    primaryKeys = ["first_name", "last_name"]
)
class Student(
    //primaryKeys属性来设置单个主键,autoGenerate自增长的字段,复合主键和单个主键不能同时存在，如果需要设置多个主键只能试用复合主键
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    val id: Int = 0,

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var name: String? = null,

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.TEXT)
    var age: String? = null,
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    var birthday: String? = null

) {
    //@Ignore所以该字段不会映射到User表中
    @Ignore
    val picture: Bitmap? = null

    override fun toString(): String {
        return "Student(id=$id, name=$name, age=$age) , birthday=$birthday"
    }
}