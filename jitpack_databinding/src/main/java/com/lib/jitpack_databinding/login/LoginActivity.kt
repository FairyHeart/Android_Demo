package com.lib.jitpack_databinding.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lib.jitpack_databinding.R
import com.lib.jitpack_databinding.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var vm: LoginModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        vm = LoginModel(LoginDto("JitPack"))
        binding.vm = vm
        binding.activity = this

    }

    fun print() {
        Log.i("xxx", vm.getUserName().toString())
    }
}