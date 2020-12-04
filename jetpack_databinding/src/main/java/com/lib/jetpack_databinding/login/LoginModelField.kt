package com.lib.jetpack_databinding.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
class LoginModelField {

    //如果是修改对象里面的值，界面不会自动刷新，ObservableField的对象没有变化
    var loginField = ObservableField<LoginDto>()

    var userNameField = ObservableField<String>()

    var userNameLiveData = MutableLiveData<String>()

    init {
        loginField.set(LoginDto("ObservableField Dto"))
        userNameField.set("ObservableField String")
        userNameLiveData.value = "LiveData"
    }

    fun print() {
        Log.i("xxx", loginField.get()?.userName.toString())
    }

    fun printLiveData() {
        Log.i("xxx", userNameLiveData.value.toString())
    }
}