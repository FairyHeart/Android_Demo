package com.lib.jitpack_livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 *
 */
class LiveDataViewModel : ViewModel() {

    var data: MutableLiveData<String> = MutableLiveData()

    //转换LiveData
    var liveDataMap: LiveData<Int> = Transformations.map(data) {
        it.length
    }
}