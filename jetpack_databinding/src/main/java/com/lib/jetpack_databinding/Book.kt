package com.lib.jetpack_databinding

/**
 * Book对象
 *
 * @author: GuaZi.
 * @date  : 2020/12/3.
 */
data class Book(
    var title: String,
    var author: String,
    var rating: Int = 0,
    val picture: String = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606994524150&di=37c3392e8169fd233efdfd2c730e901b&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F27%2F67%2F01300000921826141299672233506.jpg",
    val defaultPicture: Int = R.mipmap.ic_launcher_round
)

