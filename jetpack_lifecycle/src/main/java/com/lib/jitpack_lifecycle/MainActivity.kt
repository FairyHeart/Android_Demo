package com.lib.jitpack_lifecycle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lib.jitpack_lifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.activity = this

        val lifecycleObserver = ActivityLifeCycleObserver()
        //将观察者与被观察者绑定
        lifecycle.addObserver(lifecycleObserver)
    }

    fun startService() {
        startService(Intent(this, MyService::class.java))
    }

    fun stopService() {
        stopService(Intent(this, MyService::class.java))
    }
}