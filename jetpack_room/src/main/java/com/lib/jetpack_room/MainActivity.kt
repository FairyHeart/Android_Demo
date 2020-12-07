package com.lib.jetpack_room

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lib.jetpack_room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        val vm by viewModels<MainViewModel> {
            ViewModelProvider.AndroidViewModelFactory(application)
        }
//        val vm =
//            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
//                MainViewModel::class.java
//            )
        binding.vm = vm

    }
}