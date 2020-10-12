package com.lib.jitpack.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lib.jitpack.R
import com.lib.jitpack.room.bo.AddressBo
import com.lib.jitpack.room.bo.BookBo
import com.lib.jitpack.room.bo.UserBo
import java.util.*

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        AppDataBase.initDatabase(this)

        val userDao = AppDataBase.instance.userDao()
        val bookDao = AppDataBase.instance.bookDao()

        val address = AddressBo(city = "杭州", state = "三墩镇", street = "西城年华")

        val user = UserBo(
            firstName = "liucj001",
            lastName = "cj",
            age = 20,
            addressBo = address,
            birthday = Date()
        )
        userDao.insert(user)

        val book = BookBo(title = "java编程思想", userId = user.id+10)
        bookDao.insert(book)

    }
}