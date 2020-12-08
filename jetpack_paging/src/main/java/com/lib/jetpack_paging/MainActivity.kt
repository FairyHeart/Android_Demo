package com.lib.jetpack_paging

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import androidx.databinding.DataBindingUtil
import com.lib.jetpack_paging.bo.UserBo
import com.lib.jetpack_paging.databinding.ActivityMainBinding
import com.lib.jetpack_paging.db.AppDatabase
import com.lib.jetpack_paging.db.UserDao
import com.lib.lib_network.test.LoginActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : LoginActivity() {

    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.activity = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        userDao = AppDatabase.getInstance(this).getUserDao()


//        login()
    }

    fun insertUser() {
        GlobalScope.launch {
            for (i in 1..50) {
                userDao.insert(UserBo(name = "JetPack $i star"))
            }
        }
    }

    fun goToUserActivity() {
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }
}