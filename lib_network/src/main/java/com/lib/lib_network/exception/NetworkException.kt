package com.lib.lib_network.exception

/**
 * 业务异常
 *
 * @author: Fairy.
 * @date  : 2019-08-06.
 */
class NetworkException @JvmOverloads constructor(
    val code: String? = null,
    override val message: String?
) : RuntimeException() {
    constructor(code: String? = null, t: Throwable) : this(code, t.message)
}