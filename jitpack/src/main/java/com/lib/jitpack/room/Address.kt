package com.lib.jitpack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Address(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var address: String? = null,
    var userId: String?
)