package com.lib.jetpack_paging.bo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/8.
 */

@Entity(tableName = "user")
data class UserBo(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    var name: String?
)
