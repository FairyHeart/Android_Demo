package com.lib.jetpack_mvvm2.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lib.jetpack_mvvm2.ILifecycleView

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/18.
 */
abstract class BindActivity : AppCompatActivity(), ILifecycleView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}