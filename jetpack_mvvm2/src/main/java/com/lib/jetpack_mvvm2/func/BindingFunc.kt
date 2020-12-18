package com.lib.jetpack_mvvm2.func

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity

/**
 * 基础功能模块
 *
 * @author: GuaZi.
 * @date  : 2020/12/15.
 */
abstract class BindingFunc {

    lateinit var mContext: Context
    lateinit var mActivity: FragmentActivity

    fun onAttach(context: Context) {
        this.mContext = context
    }

    fun onAttach(activity: FragmentActivity) {
        this.mActivity = activity
    }

    fun onActivityCreated(savedInstanceState: Bundle?) {
        this.initView()
        this.initViewModel()
        this.initObserver()
        this.initViewData(savedInstanceState)
    }

    fun onHiddenChanged(hidden: Boolean, isFromModule: Boolean) {
        if (!hidden) {
            refresh()
        }
    }

    fun onDestroyView() {

    }

    /**
     * 初始化界面
     */
    open fun initView() {}

    /**
     * 初始化界面数据
     */
    open fun initViewData(savedInstanceState: Bundle?) {}

    /**
     * EventBus消息处理
     */
    open fun onMessageEvent(event: Any) {}

    /**
     * 绑定界面数据
     */
    abstract fun initObserver()

    /**
     * 初始化ViewModel
     */
    abstract fun initViewModel()

    /**
     * 更新数据
     */
    abstract fun refresh()

    /**
     * 获取中间布局
     */
    abstract fun layout(inflater: LayoutInflater, parent: ViewGroup?): View
}