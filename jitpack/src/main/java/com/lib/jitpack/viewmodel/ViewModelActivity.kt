package com.lib.jitpack.viewmodel

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
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
        val saveModel =
            ViewModelProvider(this, MySaveViewModel.ViewModelFactory(this, savedInstanceState)).get(
                MySaveViewModel::class.java
            )

        Log.i(Tag, androidModel.getApplication<Application>().toString())

        btn.setOnClickListener {
            saveModel.add()
            txt_tv.text = saveModel.count.toString()
        }

        txt_tv.text = saveModel.count.toString()

    }
}