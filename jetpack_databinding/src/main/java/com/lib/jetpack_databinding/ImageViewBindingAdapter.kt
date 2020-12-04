package com.lib.jetpack_databinding

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/3.
 */
object ImageViewBindingAdapter {

    @BindingAdapter("image")
    @JvmStatic
    fun setImage(imageView: AppCompatImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrBlank()) {
            Picasso.get().load(imageUrl).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round).into(imageView)
        }
    }

    /**
     * 方法的重载，默认会调用参数类型一致的函数
     */
    @BindingAdapter("image")
    @JvmStatic
    fun setImage(imageView: AppCompatImageView, imageUrl: Int) {
        imageView.setImageResource(imageUrl)
    }

    /**
     * 多重方法的重载
     *  requireAll 用于告诉DataBinding库这些参数是否都要赋值，默认值为true
     */
    @BindingAdapter(value = ["image", "defaultImage"], requireAll = false)
    @JvmStatic
    fun setImage(imageView: AppCompatImageView, imageUrl: String?, defaultImage: Int) {
        if (!imageUrl.isNullOrBlank()) {
            Picasso.get().load(imageUrl).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round).into(imageView)
        } else {
            imageView.setImageResource(defaultImage)
        }
    }

    @BindingAdapter("padding")
    @JvmStatic
    fun setPadding(view: AppCompatImageView, oldPadding: Int, newPadding: Int) {
        Log.i("xxx", "oldPadding=$oldPadding / newPadding=$newPadding")
        if (oldPadding != newPadding) {
            view.setPadding(newPadding, newPadding, newPadding, newPadding)
        }
    }
}
