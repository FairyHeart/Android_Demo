package com.lib.jetpack_paging.paging3

import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lib.jetpack_paging.R
import com.lib.jetpack_paging.paging3.adapter.UserAdapter
import com.lib.jetpack_paging.databinding.ActivityUserBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private val vm by viewModels<UserViewModel> {
        ViewModelProvider.AndroidViewModelFactory(application)
    }

    lateinit var adapter: UserAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityUserBinding>(this, R.layout.activity_user)
        binding.vm = vm
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        adapter = UserAdapter()
//        val manager = LinearLayoutManager(this)
//        manager.orientation = RecyclerView.VERTICAL
//        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter

        //获取数据并渲染UI
        lifecycleScope.launch {
            vm.getUsersFlow().collectLatest {
                //触发页面的渲染，是一个挂起方法，需要放到协程中，如果不放在协程中，可以使用submitData(lifecycle, it) 代替
                adapter.submitData(it)
            }
        }

        //获取数据并渲染UI
        vm.getUsersLiveData().observe(this) {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
//           adapter.submitData(lifecycle, it) 代替上面的协程方法
        }


    }

}