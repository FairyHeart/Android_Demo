package com.lib.jetpack_mvvm2.ktx

import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.lib.jetpack_mvvm2.ui.SpaceItemDecoration

/**
 * RecyclerView拓展函数
 *
 * @author: GuaZi.
 * @date  : 2020/12/14.
 */
fun RecyclerView.addItemDecoration(@DimenRes right: Int, @DimenRes bottom: Int) {
    this.addItemDecoration(
        SpaceItemDecoration(
            right = resources.getDimensionPixelSize(right),
            bottom = resources.getDimensionPixelSize(bottom)
        )
    )
}