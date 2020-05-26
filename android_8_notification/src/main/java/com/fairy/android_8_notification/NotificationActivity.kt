package com.fairy.android_8_notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        Toast.makeText(this, intent.getStringExtra("data"), Toast.LENGTH_SHORT).show()
    }
}
