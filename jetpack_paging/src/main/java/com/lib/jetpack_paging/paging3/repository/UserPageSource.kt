package com.lib.jetpack_paging.paging3.repository

import android.content.Context
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lib.jetpack_paging.bo.UserBo
import com.lib.jetpack_paging.db.AppDatabase
import com.lib.jetpack_paging.db.UserDao
import kotlinx.coroutines.delay

/**
 * 配置数据源数据源，获取数据是通过DataSource实现的
 *
 * PagingSource的两个泛型参数分别是表示当前请求第几页的Int，以及请求的数据类型
 */
class UserPageSource(context: Context) : PagingSource<Int, UserBo>() {

    var userDao: UserDao = AppDatabase.getInstance(context).getUserDao()

    /**
     * 实现这个方法来触发异步加载(例如从数据库或网络)。 这是一个suspend挂起函数，可以很方便的使用协程异步加载
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserBo> {
        return try {
            if (params.key ?: 1 > 1) {
                delay(2000)
            }
            //param.key为空时，默认加载第1页数据
            val page = params.key ?: 1
            val result = userDao.queryUser((page - 1) * 10, 10)
            Log.e("xxx", "prevKey = " + (if (page > 1) page - 1 else null))
            Log.e("xxx", "nextKey = " + (if (result.size >= 10) page + 1 else null))
            //请求成功使用LoadResult.Page返回分页数据，prevKey 和 nextKey 分别代表前一页和后一页的索引。
            LoadResult.Page(
                data = result,//加载的数据
                prevKey = if (page > 1) page - 1 else null,//上一页，如果有上一页设置该参数，否则不设置
                nextKey = if (result.size >= 10) page + 1 else null//加载下一页的key 如果传null就说明到底了
            )
        } catch (e: Exception) {
            LoadResult.Error(e)//请求失败使用LoadResult.Error返回错误状态
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