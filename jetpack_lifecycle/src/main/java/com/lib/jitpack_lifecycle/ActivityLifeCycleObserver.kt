package com.lib.jitpack_lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Activity生命周期变化观察类
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
class ActivityLifeCycleObserver : LifecycleObserver {

    /**
     * 当activity执行onCreate方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        print("onCreate")
    }

    /**
     * 当activity执行onStart方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        print("onStart")
    }

    /**
     * 当activity执行onResume方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        print("onResume")
    }

    /**
     * 当activity执行onPause方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        print("onPause")
    }

    /**
     * 当activity执行onStop方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        print("onStop")
    }

    /**
     * 当activity执行onDestroy方法时，该方法会被自动调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        print("onDestroy")
    }


    private fun print(log: String) {
        Log.i("xxx", log)
    }
}