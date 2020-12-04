package com.lib.jitpack_databinding.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lib.jitpack_databinding.Book
import com.lib.jitpack_databinding.R
import com.lib.jitpack_databinding.databinding.AdapterRecyclerBinding

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
class BookAdapter(private val books: MutableList<Book>) :
    RecyclerView.Adapter<BookAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: AdapterRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<AdapterRecyclerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_recycler, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = books[position]
        holder.binding.book = book
    }

    override fun getItemCount(): Int {
        return books.size
    }
}