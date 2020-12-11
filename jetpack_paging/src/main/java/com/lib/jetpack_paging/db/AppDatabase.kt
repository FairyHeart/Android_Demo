package com.lib.jetpack_paging.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lib.jetpack_paging.bo.UserBo

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/8.
 */
@Database(entities = [UserBo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var database: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "user.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return database as AppDatabase
        }
    }

    abstract fun getUserDao(): UserDao
}