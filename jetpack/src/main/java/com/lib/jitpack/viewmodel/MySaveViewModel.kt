package com.lib.jitpack.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

/**
 *
 *
 */
class MySaveViewModel(private val handle: SavedStateHandle) : ViewModel() {


    fun add() {
        getCount().value = getCount().value?.plus(1)
    }

    fun getCount(): MutableLiveData<Int> {
        if (!handle.contains("countKey")) {
            handle.set("countKey", 0)
        }
        return handle.getLiveData("countKey")
    }

    class ViewModelFactory(owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null) :
        AbstractSavedStateViewModelFactory(owner, defaultArgs) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return MySaveViewModel(handle) as T
        }
    }

}