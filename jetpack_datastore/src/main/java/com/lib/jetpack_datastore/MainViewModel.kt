package com.lib.jetpack_datastore

import android.app.Application
import androidx.datastore.preferences.core.preferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/25.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    val countLiveData = MutableLiveData<Int>()

    /**
     * 定义Key
     */
    private val countKey = preferencesKey<Int>("count")

    private val dataStore = DataStoreRepository(getApplication())

    init {
        read()
    }

    fun save() {
        viewModelScope.launch {
            val count = (countLiveData.value ?: 0) + 1
            dataStore.saveData(countKey, count)
        }
    }

    fun read() {
        viewModelScope.launch {
            val flow = dataStore.readData(countKey, 0)
            flow.collect {
                countLiveData.value = it
            }
        }
    }
}