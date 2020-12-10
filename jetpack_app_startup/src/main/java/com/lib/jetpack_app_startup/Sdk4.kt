package com.lib.jetpack_app_startup

import android.content.Context

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/12/10.
 */
class Sdk4(val context: Context) {

    companion object {

        private var sdk: Sdk4? = null

        @Synchronized
        fun getInstance(context: Context): Sdk4 {
            return sdk ?: Sdk4(context)
        }
    }
}