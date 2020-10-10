package com.lib.jitpack.room

import androidx.room.*

/**
 * 使用 @Entity 的indices 属性，列出要包含在索引或复合索引中的列的名称
 * 外键约束@ForeignKey:使用@ForeignKey 注释定义其与实体的 关系；ForeignKey中 entity 为要关联的父实体类；parentColumns 为关联父实体类的列名；childColumns此实体类中的列名
 */
@Entity(
    tableName = "user",
//    primaryKeys = ["userId", "age"],
    indices = [Index("userName"), Index(value = ["name"], unique = true)], //唯一索引
    foreignKeys = [ForeignKey(
        entity = Address::class,
        parentColumns = ["user_Id"],
        childColumns = ["userId"]
    )]
)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @PrimaryKey val user_Id: String,
    @ColumnInfo(name = "userName") var name: String?,// 定义列名,
    var age: Int?
//    @Embedded
//    val address: Address?
)
