package com.lib.jetpack_room

import android.app.Application

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/7.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDataBase.initDatabase(applicationContext)
    }
}