package com.lib.jitpack_lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Service观察者
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
class MyServiceLifecycleObserver : LifecycleObserver {
    /**
     * 当Service执行onCreate方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        print("Service onCreate")
    }

    /**
     * 当Service执行onStop方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        print("Service onStop")
    }

    /**
     * 当Service执行onDestroy方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        print("Service onDestroy")
    }

    private fun print(log: String) {
        Log.i("xxx", log)
    }
}