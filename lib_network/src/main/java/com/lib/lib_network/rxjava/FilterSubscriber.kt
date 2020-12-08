package com.lib.lib_network.rxjava

import android.content.Context
import android.widget.Toast
import com.lib.lib_network.exception.ReturnNullException
import com.google.gson.JsonSyntaxException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.MalformedURLException
import java.util.concurrent.TimeoutException

/**
 * 异常统一处理
 *
 * @author: Fairy.
 * @date  : 2020/8/13.
 */
abstract class FilterSubscriber<T>(private val context: Context) : Observer<T?> {

    var error: String? = null

    override fun onSubscribe(d: Disposable) {
    }

    override fun onComplete() {
    }

    override fun onError(t: Throwable) {
        if (t is ReturnNullException) {
            onNull()
        } else {
            t.printStackTrace()
            error = if (t is TimeoutException || t is ConnectException) {
                "网络超时"
            } else if (t is MalformedURLException) {
                "Bad URL "
            } else if (t is JsonSyntaxException) {
                "JSon解析出错"
            } else {
                t.message
            }
            if (!error.isNullOrBlank()) {
                showError(error.toString())
            }
            this.dealError(t)
        }
    }

    /**
     * 返回空处理
     */
    abstract fun onNull()

    /**
     * 异常展示
     */
    open fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    /**
     * 异常处理
     */
    open fun dealError(t: Throwable) {

    }
}