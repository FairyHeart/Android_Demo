package com.lib.jetpack_mvvm2.fragment

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.lib.jetpack_mvvm2.func.BindingFunc

/**
 * 顶层fragment
 *
 * @author: Guazi.
 * @date  : 2019/7/19.
 */
abstract class RootFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var mActivity: FragmentActivity
    lateinit var mApplication: Application

    var fragmentActivity: FragmentActivity? = null

    var fragmentTag: String? = null
        private set

    /**
     * 注册的功能模块Map
     */
    private val funcList = mutableListOf<BindingFunc>()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.fragmentTag = this.javaClass.name
        this.initView()
        this.initViewModel()
        this.initObserver()
        this.initViewData(savedInstanceState)

        this.onFuncActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("tag", fragmentTag)
        outState.putBoolean("isHidden", isHidden)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        this.onHiddenChanged(hidden, false)
    }

    open fun onHiddenChanged(hidden: Boolean, isFromModule: Boolean) {
        if (context != null) {
            if (!hidden) {
                this.refresh()
            }
            this.onFuncHiddenChanged(hidden, isFromModule)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        fragmentActivity = this.activity
        onFuncAttach(context)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mActivity = activity as FragmentActivity
        mApplication = activity.application
        val func = registerFunc()
        if (func.isNotEmpty()) {
            funcList.addAll(func)
        }
        onFuncAttach(mActivity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.onFuncDestroyView()
    }

//    ------------------------------fragment start--------------------------------
    /**
     * 初始化功能模块
     */
    open fun registerFunc(): MutableList<BindingFunc> {
        return mutableListOf()
    }


    /**
     * 初始化ViewModel
     */
    abstract fun initViewModel()

    /**
     * 绑定界面数据
     */
    open fun initObserver() {}

    /**
     * 初始化界面
     */
    open fun initView() {}

    /**
     * 初始化界面数据
     */
    open fun initViewData(savedInstanceState: Bundle?) {}

    /**
     * 更新数据
     */
    open fun refresh() {}

//    ------------------------------fun start--------------------------------

    /**
     * Func onAttach
     */
    private fun onFuncAttach(context: Context) {
        if (funcList.isNotEmpty()) {
            funcList.forEach {
                it.onAttach(context)
            }
        }
    }

    /**
     * Func onAttach
     */
    private fun onFuncAttach(activity: FragmentActivity) {
        if (funcList.isNotEmpty()) {
            funcList.forEach {
                it.onAttach(activity)
            }
        }
    }

    /**
     * Func onActivityCreated
     */
    private fun onFuncActivityCreated(savedInstanceState: Bundle?) {
        if (funcList.isNotEmpty()) {
            funcList.forEach {
                it.onActivityCreated(savedInstanceState)
            }
        }
    }

    /**
     * Func onHiddenChanged
     */
    private fun onFuncHiddenChanged(hidden: Boolean, isFromModule: Boolean) {
        if (funcList.isNotEmpty()) {
            funcList.forEach {
                it.onHiddenChanged(hidden, isFromModule)
            }
        }
    }

    /**
     * Func onDestroyView
     */
    private fun onFuncDestroyView() {
        if (funcList.isNotEmpty()) {
            funcList.forEach {
                it.onDestroyView()
            }
        }
    }

    /**
     * Func EventBus消息处理
     */
    private fun onFuncMessageEvent(event: Any) {
        if (funcList.isNotEmpty()) {
            funcList.forEach {
                it.onMessageEvent(event)
            }
        }
    }

//    ------------------------------fun end--------------------------------
}