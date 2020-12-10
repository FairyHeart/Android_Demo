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
class Sdk1Initializer : Initializer<Sdk1> {

    /**
     * 组件初始话的所有的操作
     */
    override fun create(context: Context): Sdk1 {
        Log.i("startup", "sdk1 create")
        return Sdk1.getInstance(context)
    }

    /**
     * 当前组件初始化需要依赖的其他组件
     *      可以使用此方法来控制应用程序在启动时运行初始化程序的顺序
     */
    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        Log.i("startup", "sdk1 dependencies")
        return Collections.emptyList()
    }
}