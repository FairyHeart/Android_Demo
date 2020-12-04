package com.lib.jetpack_databinding.login

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.lib.jetpack_databinding.BR

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/3.
 */
class LoginModel(private val loginDto: LoginDto) : BaseObservable() {

    /**
     * @Bindable 告诉编译器，希望对这个字段进行双向绑定
     */
    @Bindable
    fun getUserName(): String? {
        return loginDto.userName
    }

    /**
     * Setter方法会在用户编辑内容时，被自动调用，需要在该方法内对userName字段进行手动更新
     */
    fun setUserName(userName: String?) {
        if (userName != null && userName != loginDto.userName) {
            loginDto.userName = userName
            notifyPropertyChanged(BR.userName)
        }
    }
}