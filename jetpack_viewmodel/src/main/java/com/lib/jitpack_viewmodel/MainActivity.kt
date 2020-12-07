package com.lib.jitpack_viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //无参构造方法获取model对象
        //val model = ViewModelProvider(this).get(MyViewModel::class.java)
        //有参构造方法获取model对象需要通过factory
        val model = ViewModelProvider(
            this,
            MyViewModel.MyViewModelFactory("default data")
        ).get(MyViewModel::class.java)

        //获取自带保存Application model
        val androidModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                AndroidViewModel::class.java
            )

        //onSaveInstanceState状态保存model
        val saveModel2 =
            ViewModelProvider(this, MySaveViewModel.ViewModelFactory(this, savedInstanceState)).get(
                MySaveViewModel::class.java
            )

//        val saveModel2 = ViewModelProvider(
//            this,
//            SavedStateViewModelFactory(application, this, savedInstanceState)
//        ).get(MySaveViewModel::class.java)

//        val saveModel2 by viewModels<MySaveViewModel> {
//            SavedStateViewModelFactory(application, this, savedInstanceState)
//        }

        saveModel2.getCount().observe(this, Observer<Int> {
            txt_tv.text = it.toString()
        })

        btn.setOnClickListener {
            saveModel2.add()
        }
    }
}