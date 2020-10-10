package com.lib.jitpack.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lib.jitpack.R

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        AppDataBase.initDatabase(this)

        val userDao = AppDataBase.instance.userDao()
        val userId = "id_001"
        val address = Address(address = "杭州", userId = userId)
        val user = User(userId = userId, name = "张三", age = 20, address = address)

        Thread {
            val user2 = userDao.selectById(user.id.toString())
            if (user2 != null) {
                userDao.update(user)
            } else {
                userDao.insert(user)
            }
        }.start()
    }
}