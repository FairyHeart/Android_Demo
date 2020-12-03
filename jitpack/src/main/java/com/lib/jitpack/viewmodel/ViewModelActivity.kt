package com.lib.jitpack.viewmodel

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.lib.jitpack.R
import com.lib.jitpack.Tag
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

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

        saveModel2.getCount()?.observe(this, Observer<Int> {
            txt_tv.text = it.toString()
        })

        btn.setOnClickListener {
            saveModel2.add()
        }

    }
}