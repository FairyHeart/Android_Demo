package com.lib.jetpack_mvvm2.ktx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * DataBinding拓展函数
 *
 * @author: GuaZi.
 * @date  : 2020/12/18.
 */
fun <T : ViewDataBinding> Fragment.binding(
    @NonNull inflater: LayoutInflater,
    @LayoutRes layoutId: Int,
    @Nullable parent: ViewGroup?,
    attachToParent: Boolean = false
): T {
    return DataBindingUtil.inflate(inflater, layoutId, parent, attachToParent)
}

fun <T : ViewDataBinding> FragmentActivity.binding(
    @LayoutRes layoutId: Int,
    @Nullable bindingComponent: DataBindingComponent
): T {
    return DataBindingUtil.setContentView(this, layoutId, bindingComponent)
}


