package com.lib.android_hotfix

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/11/17.
 */
class MyApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
    }

}