package com.lib.jetpack_mvvm2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * 基础Adapter
 *
 * @author: GuaZi.
 * @date  : 2020/12/11.
 */
abstract class BindAdapter<T, VM : ViewDataBinding>(
    val layout: Int
) : RecyclerView.Adapter<BindAdapter.MyViewHolder<VM>>() {

    protected lateinit var mContext: Context

    protected var mData: MutableList<T>? = null

    class MyViewHolder<VM : ViewDataBinding>(val binding: VM) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder<VM> {
        mContext = parent.context
        val binding =
            DataBindingUtil.inflate<VM>(LayoutInflater.from(mContext), layout, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder<VM>, position: Int) {
        val item = mData?.get(position) ?: return
        val binding = holder.binding
        this.onBindViewHolder(item, binding, position)
    }

    abstract fun onBindViewHolder(item: T, binding: VM, position: Int)

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    open fun clear() {
        mData?.clear()
        notifyDataSetChanged()
    }

    open fun setNewData(data: MutableList<T>?) {
        this.mData = data
        this.notifyDataSetChanged()
    }

    open fun addData(data: MutableList<T>?) {
        if (!data.isNullOrEmpty()) {
            if (this.mData == null) {
                this.mData = mutableListOf()
            }
            this.mData?.addAll(data)
        }
    }

    open fun getData(): MutableList<T>? {
        return mData
    }
}