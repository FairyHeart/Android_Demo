package com.lib.jetpack_paging.paging3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.lib.jetpack_paging.R
import com.lib.jetpack_paging.databinding.ActivityUserBinding
import com.lib.jetpack_paging.paging3.adapter.UserAdapter
import com.lib.jetpack_paging.paging3.adapter.UserFooterLoadStateAdapter
import com.lib.jetpack_paging.paging3.adapter.UserHeaderLoadStateAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

class UserActivity : AppCompatActivity() {

    private val vm by viewModels<UserViewModel> {
        ViewModelProvider.AndroidViewModelFactory(application)
    }

    lateinit var adapter: UserAdapter

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityUserBinding>(this, R.layout.activity_user)
        binding.vm = vm
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        adapter = UserAdapter()
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            UserHeaderLoadStateAdapter(adapter),
            UserFooterLoadStateAdapter(adapter)
        )

        binding.refresh.setOnRefreshListener {
            adapter.refresh()
//            binding.refresh.isRefreshing = false
        }
        lifecycleScope.launchWhenCreated {
            @OptIn(ExperimentalCoroutinesApi::class)
            adapter.loadStateFlow.collectLatest {
                if (it.refresh !is LoadState.Loading) {
                    binding.refresh.isRefreshing = false
                }
            }
        }
        //获取数据并渲染UI
        /* lifecycleScope.launch {
             vm.getUsersFlow().collectLatest {
                 //触发页面的渲染，是一个挂起方法，需要放到协程中，如果不放在协程中，可以使用submitData(lifecycle, it) 代替
                 adapter.submitData(it)
             }
         }
 */
        //获取数据并渲染UI
        vm.getUsersLiveData().observe(this) {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
//           adapter.submitData(lifecycle, it) 代替上面的协程方法
        }


    }

}