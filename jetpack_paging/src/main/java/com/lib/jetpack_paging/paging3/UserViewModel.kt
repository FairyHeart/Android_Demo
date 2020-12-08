package com.lib.jetpack_paging.paging3

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lib.jetpack_paging.bo.UserBo
import com.lib.jetpack_paging.paging3.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/8.
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    var userLiveData = MutableLiveData<PagingData<UserBo>>()

    private val repository: UserRepository by lazy {
        UserRepository(application)
    }

    fun getUsersFlow(): Flow<PagingData<UserBo>> = repository.getUsers().cachedIn(viewModelScope)


    fun getUsersLiveData(): LiveData<PagingData<UserBo>> =
        repository.getUsers().flowOn(Dispatchers.IO).asLiveData()

}