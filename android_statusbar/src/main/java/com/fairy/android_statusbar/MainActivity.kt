package com.fairy.android_statusbar

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        hideStatusBar()
        bgStatusBar()

        //隐藏ActionBar
        supportActionBar?.hide()
    }

    private fun hideStatusBar() {
        //隐藏状态栏
        val decorView = window.decorView //获取到了当前界面的DecorView
        val option = View.SYSTEM_UI_FLAG_FULLSCREEN //全屏
        decorView.systemUiVisibility = option //设置系统UI元素的可见性
    }

    private fun bgStatusBar() {
        //背景图片延伸到状态栏，只有5.0之后的系统才有
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val option =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE//表示会让应用的主体内容占用系统状态栏的空间
            window.decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT//将状态栏设置成透明色
        }
    }
}
