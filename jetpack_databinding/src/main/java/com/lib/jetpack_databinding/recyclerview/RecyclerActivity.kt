package com.lib.jetpack_databinding.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.lib.jetpack_databinding.Book
import com.lib.jetpack_databinding.R
import com.lib.jetpack_databinding.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRecyclerBinding>(
            this,
            R.layout.activity_recycler
        )
        binding.activity = this


        val adapter = BookAdapter(getBooks())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    fun getBooks(): MutableList<Book> {
        val books = mutableListOf<Book>()
        for (i in 1..10) {
            books.add(Book("java $i", "author $i"))
        }
        return books
    }
}