package com.lib.android_hotfix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.lib.android_hotfix.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.activity = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    fun show() {
        val i = 10 / 0
        Toast.makeText(this, "show $i", Toast.LENGTH_SHORT).show()
    }

    fun hotFix() {

    }
}