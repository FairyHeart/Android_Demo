package com.lib.jitpack.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lib.jitpack.R
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

    //创建观察者对象
    private val observable = Observer<String> {
        txt_tv.text = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        val model by viewModels<LiveDataViewModel>()

        // 订阅观察
        model.data.observe(this, observable)
//        model.data.observeForever()注册一个没有关联的生命周期所有者对象的观察者
//        model.data.removeObserver()

        var index = 1
        change_btn.setOnClickListener {
            model.data.value = "index = ${index++}"
        }
    }
}