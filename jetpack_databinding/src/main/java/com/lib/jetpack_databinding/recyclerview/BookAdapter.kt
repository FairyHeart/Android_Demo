package com.lib.jetpack_databinding.recyclerview

import com.lib.jetpack_databinding.Book
import com.lib.jetpack_databinding.R
import com.lib.jetpack_databinding.databinding.AdapterRecyclerBinding

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
class BookAdapter : BindAdapter<Book, AdapterRecyclerBinding>(R.layout.adapter_recycler) {

    override fun onBindViewHolder(item: Book, binding: AdapterRecyclerBinding, position: Int) {
        binding.book = item
    }

}