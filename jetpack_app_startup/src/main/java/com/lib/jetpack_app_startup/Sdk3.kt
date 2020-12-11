package com.lib.jetpack_app_startup

import android.content.Context

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/12/10.
 */
class Sdk3(val context: Context) {

    companion object {

        private var sdk: Sdk3? = null

        @Synchronized
        fun getInstance(context: Context): Sdk3 {
            return sdk ?: Sdk3(context)
        }
    }
}