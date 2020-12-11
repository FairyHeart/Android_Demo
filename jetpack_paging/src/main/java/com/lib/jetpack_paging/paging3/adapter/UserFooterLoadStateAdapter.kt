package com.lib.jetpack_paging.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lib.jetpack_paging.R
import com.lib.jetpack_paging.databinding.AdapterUserFooterBinding

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/9.
 */
class UserFooterLoadStateAdapter(private val adapter: UserAdapter) :
    LoadStateAdapter<UserFooterLoadStateAdapter.BindingViewHolder>() {

    val tip = MutableLiveData<String>()

    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {
        val binding = holder.binding
        binding.adapter = this
        when (loadState) {
            is LoadState.Error -> {
                tip.value = "加载失败"
                binding.tip.setOnClickListener {
                    adapter.retry()
                }
            }
            is LoadState.Loading -> {
                tip.value = "加载中..."
            }
            is LoadState.NotLoading -> {
                tip.value = "没有内容"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BindingViewHolder {
        return BindingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_user_footer,
                parent,
                false
            )
        )
    }

    class BindingViewHolder(val binding: AdapterUserFooterBinding) :
        RecyclerView.ViewHolder(binding.root)

}