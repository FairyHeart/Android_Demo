package com.lib.jetpack_mvvm2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/18.
 */
interface ILifecycleView {

    /**
     * 绑定界面数据
     */
    fun initObserver()

    /**
     * 初始化ViewModel
     */
    fun initViewModel()

    /**
     * 初始化界面数据
     */
    fun initViewData(savedInstanceState: Bundle?) {}

    /**
     * 刷新数据(界面重新可见的时候调用)
     */
    fun refresh()

    /**
     * 获取布局
     */
    fun layout(inflater: LayoutInflater, parent: ViewGroup?): View

}