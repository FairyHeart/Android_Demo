package com.lib.jitpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lib.jitpack.room.bo.BookBo
import com.lib.jitpack.room.bo.UserBo
import com.lib.jitpack.room.dao.IBookDao
import com.lib.jitpack.room.dao.IUserDao

/**
 * entities：数据库相关的所有Entity实体类，他们会转化成数据库里面的表
 * version：数据库版本
 * exportSchema：默认true，也是建议传true，这样可以把Schema导出到一个文件夹里面。同时建议把这个文件夹上次到VCS
 */
@Database(entities = [UserBo::class, BookBo::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    companion object {

        lateinit var instance: AppDataBase

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table user add column birthday")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //空实现需要添加
            }
        }


        fun initDatabase(context: Context) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, "user_db.db"
            )
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .addMigrations(MIGRATION_2_3)
                .build()
        }
    }

    abstract fun userDao(): IUserDao

    abstract fun bookDao(): IBookDao
}