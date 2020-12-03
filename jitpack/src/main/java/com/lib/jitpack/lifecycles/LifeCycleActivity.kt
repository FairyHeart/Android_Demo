package com.lib.jitpack.lifecycles

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lib.jitpack.R

class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycler)

        val observer = MyObserver(lifecycle) {
            Toast.makeText(this@LifeCycleActivity, "$it", Toast.LENGTH_SHORT).show()
        }
        lifecycle.addObserver(observer)
    }
}