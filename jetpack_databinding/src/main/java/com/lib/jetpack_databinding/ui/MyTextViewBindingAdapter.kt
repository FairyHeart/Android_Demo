package com.lib.jetpack_databinding.ui

import androidx.databinding.BindingAdapter

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/4.
 */
object MyTextViewBindingAdapter {
    @BindingAdapter("item_text_tip")
    @JvmStatic
    fun setItemTextTip(view: MyTextView, tip: String) {
        view.setInfoTv(tip)
    }
}