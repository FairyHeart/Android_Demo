package com.lib.jetpack_paging

import android.content.Context
import androidx.multidex.MultiDex
import com.lib.lib_network.test.NetworkApplication

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/8.
 */
class PagingApplication : NetworkApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}