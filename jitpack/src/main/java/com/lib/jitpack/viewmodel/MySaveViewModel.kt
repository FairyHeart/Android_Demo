package com.lib.jitpack.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

/**
 *
 *
 * @author: Guazi.
 * @date  : 2020/9/8.
 */
class MySaveViewModel(private val handle: SavedStateHandle) : ViewModel() {

    var count = handle.get<Int>("countKey") ?: 1

    fun add() {
        handle.set("countKey", count++)
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