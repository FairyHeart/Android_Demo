package com.lib.jitpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lib.jitpack.lifecycles.LifeCycleActivity
import com.lib.jitpack.lifecycles.MyLifeCycleActivity
import com.lib.jitpack.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*

const val Tag = "JitPack"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        life_btn.setOnClickListener {
            val intent = Intent(this, LifeCycleActivity::class.java)
            startActivity(intent)
        }
        my_life_btn.setOnClickListener {
            val intent = Intent(this, MyLifeCycleActivity::class.java)
            startActivity(intent)
        }
        view_model_btn.setOnClickListener {
            val intent = Intent(this, ViewModelActivity::class.java)
            startActivity(intent)
        }
    }
}
