package com.lib.jetpack_app_startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import java.util.*

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/12/10.
 */
class Sdk4Initializer : Initializer<Sdk1> {

    override fun create(context: Context): Sdk1 {
        Log.i("startup", "sdk4 create")
        return Sdk1.getInstance(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        Log.i("startup", "sdk4 dependencies")
        return Collections.emptyList()
    }
}