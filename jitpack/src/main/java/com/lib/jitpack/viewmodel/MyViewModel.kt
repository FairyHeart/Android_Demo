package com.lib.jitpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 *
 */
class MyViewModel(var name: String) : ViewModel() {

    //构造方法需要实现Factory，通过自定义factory来获取model对象
    class MyViewModelFactory(private val name: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyViewModel(name) as T
        }

    }
}