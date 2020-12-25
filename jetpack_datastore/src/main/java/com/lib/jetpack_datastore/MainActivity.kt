package com.lib.jetpack_datastore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lib.jetpack_datastore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val vm by viewModels<MainViewModel> {
        ViewModelProvider.AndroidViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = vm
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

}