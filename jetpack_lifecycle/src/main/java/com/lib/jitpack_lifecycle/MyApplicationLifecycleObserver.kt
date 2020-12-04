package com.lib.jitpack_lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * App应用观察者
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
class MyApplicationLifecycleObserver : LifecycleObserver {
    /**
     * 应用创建的时候调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        print("Application onCreate")
    }

    /**
     * 当应用在前台出现时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        print("Application onStart")
    }

    /**
     * 当应用在前台出现时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        print("Application onResume")
    }

    /**
     * 当应用退出到后台时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        print("Application onPause")
    }

    /**
     * 当应用退出到后台时被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        print("Application onStop")
    }

    /**
     * 永远不会调用，系统不会分发调用destroy事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        print("Application onDestroy")
    }


    private fun print(log: String) {
        Log.i("xxx", log)
    }
}