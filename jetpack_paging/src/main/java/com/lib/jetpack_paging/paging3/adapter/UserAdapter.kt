package com.lib.jetpack_paging.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lib.jetpack_paging.R
import com.lib.jetpack_paging.bo.UserBo
import com.lib.jetpack_paging.databinding.AdapterUserBinding

/**
 * 继承PagedListAdapter
 * 需要在构造方法里传入一个DiffUtil.ItemCallback用来确定差量更新的时候的计算规则
 */
class UserAdapter : PagingDataAdapter<UserBo, UserAdapter.UserViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<UserBo>() {
            override fun areItemsTheSame(oldItem: UserBo, newItem: UserBo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserBo, newItem: UserBo): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.user = user
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<AdapterUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_user, parent, false
        )
        return UserViewHolder(binding)
    }

    class UserViewHolder(val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root)
}