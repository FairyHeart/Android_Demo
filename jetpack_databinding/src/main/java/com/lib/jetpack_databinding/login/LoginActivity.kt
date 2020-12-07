package com.lib.jetpack_databinding.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lib.jetpack_databinding.R
import com.lib.jetpack_databinding.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var vm: LoginModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        vm = LoginModel(LoginDto("JitPack"))
        binding.vm = vm
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        val vmField = LoginModelField()
        binding.vmField = vmField

    }
}