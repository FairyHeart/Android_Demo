package com.lib.jitpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lib.jitpack.lifecycles.LifeCycleActivity
import com.lib.jitpack.lifecycles.MyLifeCycleActivity
import com.lib.jitpack.livedata.LiveDataActivity
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
        live_data_btn.setOnClickListener {
            val intent = Intent(this, LiveDataActivity::class.java)
            startActivity(intent)
        }
    }
}
