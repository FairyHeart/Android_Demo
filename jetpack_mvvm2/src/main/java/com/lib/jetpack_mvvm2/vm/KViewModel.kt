package com.lib.jetpack_mvvm2.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * ViewModel拓展函数
 *
 * @author: GuaZi.
 * @date  : 2020/11/27.
 */
fun ViewModel.launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onError: (e: Throwable) -> Unit = {},
        onComplete: () -> Unit = {},
        block: suspend CoroutineScope.() -> Unit
) {
    GlobalScope.launch(context, start) {
        try {
            block.invoke(this)
        } catch (e: Throwable) {
            onError.invoke(e)
        } finally {
            onComplete.invoke()
        }
    }
}
