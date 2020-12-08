package com.lib.jetpack_paging.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lib.jetpack_paging.bo.UserBo
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/8.
 */
class UserRepository(val context: Context) {

    fun getUsers(): Flow<PagingData<UserBo>> {
        return Pager(
            PagingConfig(
                pageSize = 10,// 每页显示的数据的大小
                enablePlaceholders = false,// 开启占位符
                prefetchDistance = 3, // 预刷新的距离，距离最后一个 item 多远时加载数据
                initialLoadSize = 30,//初始化加载数量，默认为 pageSize * 3
                maxSize = 200// 一次应在内存中保存的最大数据
            )
        ) {
            UserPageSource(context)
        }.flow
    }
}