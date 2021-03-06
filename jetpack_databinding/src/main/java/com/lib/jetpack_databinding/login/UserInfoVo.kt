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
class UserInfoVo : BaseObservable() {

    @get:Bindable
    var user: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.user)
        }
}
