package com.lib.jitpack_lifecycle

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(MyApplicationLifecycleObserver())
    }
}