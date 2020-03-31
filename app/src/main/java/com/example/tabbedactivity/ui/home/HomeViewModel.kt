package com.example.tabbedactivity.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabbedactivity.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    enum class ApiStatus {LOADING, DONE, ERROR}

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _kitTypes = MutableLiveData<List<KitType?>>()
    val kitTypes: LiveData<List<KitType?>>
        get() = _kitTypes

    private val _items1 = MutableLiveData<List<ItemType?>>()
    val items1: LiveData<List<ItemType?>>
        get() = _items1

    private val _items2 = MutableLiveData<List<ItemType?>>()
    val items2: LiveData<List<ItemType?>>
        get() = _items2

    private val _items3 = MutableLiveData<List<ItemType?>>()
    val items3: LiveData<List<ItemType?>>
        get() = _items3

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getProperties()
    }

    private fun getProperties() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            val kitTypesDeferred = Api.retrofitService.getKitTypesAsync()
            val items1Deferred = Api.retrofitService.getItems1Async()
            val items2Deferred = Api.retrofitService.getItems2Async()
            val items3Deferred = Api.retrofitService.getItems3Async()
            try {
                _status.value = ApiStatus.LOADING
                val kitList = kitTypesDeferred.await()
                val items1List = items1Deferred.await()
                val items2List = items2Deferred.await()
                val items3List = items3Deferred.await()
                _status.value = ApiStatus.DONE
                _kitTypes.value = kitList
                _items1.value = items1List
                _items2.value = items2List
                _items3.value = items3List
                println(kitList)
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _kitTypes.value = ArrayList()
                _items1.value = ArrayList()
                _items2.value = ArrayList()
                _items3.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}