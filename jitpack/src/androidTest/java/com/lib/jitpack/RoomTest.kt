package com.lib.jitpack

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.lib.jitpack.room.Address
import com.lib.jitpack.room.AppDataBase
import com.lib.jitpack.room.User
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
        val instance = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDataBase::class.java
        )
    }

    @Test
    fun insertUser() {
        val userId = "id_001"
        val address = Address(address = "杭州", userId = userId)
        val user = User(user_Id = userId, name = "张三", age = 20)
        dataBase.userDao().insert(user)

        val localUser = dataBase.userDao().selectById(userId)
        println(localUser.toString())

    }

    @After
    fun closeDb() {
        dataBase.close()
    }
}