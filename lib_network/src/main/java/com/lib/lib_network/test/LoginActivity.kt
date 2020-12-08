package com.lib.lib_network.test

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.lib.android_lib_network.param.LoginParam
import com.lib.lib_network.RetrofitConfig
import com.lib.lib_network.RetrofitManager
import com.lib.lib_network.dto.ResultDto
import com.lib.lib_network.test.dto.LoginDto
import com.lib.lib_network.toBody
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class LoginActivity : AppCompatActivity() {

    val remoteService = RetrofitManager.instance.create(IRemoteService::class.java)

    fun login() {
        GlobalScope.launch {
            val param = LoginParam("13777820327", "123456", "1001")
            val result: LiveData<ResultDto<LoginDto?>> =
                remoteService.loginByPhoneLiveData(param.toBody())
            if (result.value?.isSuccess() == true) {
                RetrofitConfig.instance.addUrlParams("xtoken", result.value?.data?.token)
                RetrofitManager.instance.reCreateRetrofit()
            }
        }
    }
}