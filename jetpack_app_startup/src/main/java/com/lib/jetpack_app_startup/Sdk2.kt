package com.lib.jetpack_app_startup

import android.content.Context

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/12/10.
 */
class Sdk2(val context: Context) {

    companion object {

        private var sdk: Sdk2? = null

        @Synchronized
        fun getInstance(context: Context): Sdk2 {
            return sdk ?: Sdk2(context)
        }
    }
}