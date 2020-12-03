package com.lib.jitpack_databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import com.lib.jitpack_databinding.databinding.ActivityMainBinding
import com.lib.jitpack_databinding.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val book = Book(title = "JetPack", author = "kotlin", 5)
        binding.setVariable(BR.book, book)
//        binding.book = book //或者直接绑定

        binding.activity = this
    }

    //需要传入View，否则无法直接通过名字调用
    fun onClicked(view: View) {
        Toast.makeText(this, "click me", Toast.LENGTH_SHORT).show()
    }

    fun click() {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
    }

    fun login(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

}