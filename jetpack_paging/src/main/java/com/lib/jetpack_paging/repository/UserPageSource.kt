package com.lib.jetpack_paging.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lib.jetpack_paging.bo.UserBo
import com.lib.jetpack_paging.db.AppDatabase
import com.lib.jetpack_paging.db.UserDao

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/8.
 */
class UserPageSource(context: Context) : PagingSource<Int, UserBo>() {

    var userDao: UserDao = AppDatabase.getInstance(context).getUserDao()

    /**
     * 触发异步加载
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserBo> {
        return try {
            val page = params.key ?: 1
            val result = userDao.queryAllUser()
            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    /**
     * 只在初始加载成功且加载页面的列表不为空的情况下被调用
     */
    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, UserBo>): Int? {
        return super.getRefreshKey(state)
    }
}