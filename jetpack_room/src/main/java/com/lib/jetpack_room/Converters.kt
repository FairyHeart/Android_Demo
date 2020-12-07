package com.lib.jetpack_room

import androidx.room.TypeConverter
import java.util.*

/**
 *
 */
open class Converters {

    @TypeConverter
    fun formTimestamp(value: Long?): Date? {
        return if (value == null) {
            null
        } else {
            Date(value)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}