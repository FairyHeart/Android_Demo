package com.lib.jitpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class, Address::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    companion object {

        lateinit var instance: AppDataBase

//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//
//            }
//        }
//
//        private val MIGRATION_2_3 = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("alter table user add column age integer not null default 1")
//            }
//        }


        fun initDatabase(context: Context) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, "user.db"
            )
//                .addMigrations(MIGRATION_2_3)
                .build()
        }
    }

    abstract fun userDao(): IUserDao

}