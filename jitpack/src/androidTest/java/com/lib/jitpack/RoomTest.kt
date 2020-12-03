package com.lib.jitpack

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.lib.jitpack.room.AppDataBase
import com.lib.jitpack.room.bo.AddressBo
import com.lib.jitpack.room.bo.BookBo
import com.lib.jitpack.room.bo.UserBo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * 数据库测试类
 *
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RoomTest {

    lateinit var dataBase: AppDataBase

    @Before
    fun initDb() {
        dataBase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun insertUser() {

        val userDao = dataBase.userDao()
        val bookDao = dataBase.bookDao()

        val address = AddressBo(city = "杭州", state = "三墩镇", street = "西城年华")

        val user = UserBo(firstName = "liucy", lastName = "cj", age = 19, addressBo = address,birthday = Date())
        userDao.insert(user)

        val book = BookBo(id = 10, title = "java编程思想", userId = 0)
        bookDao.insert(book)

        val userDb = userDao.selectAll()
    }

    @After
    fun closeDb() {
        dataBase.close()
    }
}