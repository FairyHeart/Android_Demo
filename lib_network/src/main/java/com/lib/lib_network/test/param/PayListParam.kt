package com.lib.lib_network.test.param

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/9/15.
 */
data class PayListParam(
    val entityId: String,
    val payType: Int = 0,// 1：微信，2：支付宝 不传全部
    val startTime: Long = 0,
    val endTime: Long = 0,
    val pageSize: Int = 10,
    val pageIndex: Int = 1,
    val qrCode: String? = null
)