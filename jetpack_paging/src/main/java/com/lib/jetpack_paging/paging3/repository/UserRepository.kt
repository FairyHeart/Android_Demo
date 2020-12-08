package com.lib.jetpack_paging.paging3.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lib.jetpack_paging.bo.UserBo
import kotlinx.coroutines.flow.Flow

/**
 * 数据仓库层Repository
 */
class UserRepository(private val context: Context) {

    /**
     * PagingData 用来存储每次分页数据获取的结果,每个PagingData代表一页数据
     */
    fun getUsers(): Flow<PagingData<UserBo>> {

        /**
         * Pager是进入分页的主要入口
         *      它需要4个参数：PagingConfig、Key(必填)、RemoteMediator、PagingSource(必填)
         */
        return Pager(
            /**
             * PagingConfig用来配置加载的时候的一些属性
             */
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