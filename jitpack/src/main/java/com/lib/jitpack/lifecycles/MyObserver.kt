package com.lib.jitpack.lifecycles

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *
 *
 * @author: Guazi.
 * @date  : 2020/9/7.
 */
class MyObserver(var lifecycle: Lifecycle, var callBack: (String) -> Unit) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun testStart() {
        callBack("testStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun testOnResume() {
        callBack("testOnResume")
    }

    fun enable() {
        //查询当前状态
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {

        }
    }
}