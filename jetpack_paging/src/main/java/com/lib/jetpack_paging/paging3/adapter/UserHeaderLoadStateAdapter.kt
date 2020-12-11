package com.lib.jetpack_paging.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lib.jetpack_paging.R
import com.lib.jetpack_paging.databinding.AdapterUserHeaderBinding

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/9.
 */
class UserHeaderLoadStateAdapter(private val adapter: UserAdapter) :
    LoadStateAdapter<UserHeaderLoadStateAdapter.BindingViewHolder>() {

    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {
        val binding = holder.binding
        binding.adapter = this

        when (loadState) {
            is LoadState.Error -> {
                binding.tip.text = "加载失败"
            }
            is LoadState.Loading -> {
                binding.tip.text = "加载中..."
            }
            is LoadState.NotLoading -> {
                binding.tip.text = "没有内容"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BindingViewHolder {
        return BindingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_user_header,
                parent,
                false
            )
        )
    }

    class BindingViewHolder(val binding: AdapterUserHeaderBinding) :
        RecyclerView.ViewHolder(binding.root)

}