package com.lib.jetpack_app_startup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.startup.AppInitializer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //手动调用初始化组件
        AppInitializer.getInstance(this).initializeComponent(Sdk4Initializer::class.java)
    }
}