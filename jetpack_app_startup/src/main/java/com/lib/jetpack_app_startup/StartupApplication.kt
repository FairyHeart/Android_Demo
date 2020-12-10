package com.lib.jetpack_app_startup

import android.app.Application
import android.content.Context
import android.util.Log

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/12/10.
 */
class StartupApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("startup", "application onCreate")
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.i("startup", "application attachBaseContext")
    }
}