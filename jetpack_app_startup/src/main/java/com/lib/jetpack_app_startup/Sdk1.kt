package com.lib.jetpack_app_startup

import android.content.Context

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/12/10.
 */
class Sdk1(val context: Context) {

    companion object {

        private var sdk: Sdk1? = null

        @Synchronized
        fun getInstance(context: Context): Sdk1 {
            return sdk ?: Sdk1(context)
        }
    }
}