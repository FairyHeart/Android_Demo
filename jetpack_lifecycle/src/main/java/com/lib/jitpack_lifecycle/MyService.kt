package com.lib.jitpack_lifecycle

import androidx.lifecycle.LifecycleService

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
class MyService : LifecycleService() {
    init {
        val observer = MyServiceLifecycleObserver()
        lifecycle.addObserver(observer)
    }
}