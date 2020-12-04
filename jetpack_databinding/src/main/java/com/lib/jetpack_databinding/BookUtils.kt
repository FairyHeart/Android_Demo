package com.lib.jetpack_databinding

/**
 * 工具类
 *
 * @author: GuaZi.
 * @date  : 2020/12/3.
 */
object BookUtils {
    fun getRating(rating: Int): String {
        return when (rating) {
            0 -> "零星"
            1 -> "一星"
            2 -> "二星"
            3 -> "三星"
            4 -> "四星"
            else -> "五星"
        }
    }
}