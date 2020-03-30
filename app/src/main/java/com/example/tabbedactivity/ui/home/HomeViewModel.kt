package com.example.tabbedactivity.ui.home

import android.util.Log
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

    /*private val _text1 = MutableLiveData<Property?>()
    val text1: LiveData<Property?>
        get() = _text1*/

    private val _properties = MutableLiveData<List<Property?>>()
    val properties: LiveData<List<Property?>>
        get() = _properties
    /*init {
        _properties.value!![0]?.name=" "
    }*/
    private val _properties2 = MutableLiveData<List<Property2>>()
    val properties2: LiveData<List<Property2>>
        get() = _properties2

    private val _properties3 = MutableLiveData<List<Property3>>()
    val properties3: LiveData<List<Property3>>
        get() = _properties3

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getProperties()
    }

    private fun getProperties() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            val getPropertiesDeferred = Api.retrofitService.getPropertiesApi()
            val getPropertiesDeferred2 = Api2.retrofitService2.getPropertiesApi2()
            val getPropertiesDeferred3 = Api3.retrofitService3.getPropertiesApi3()
            try {
                _status.value = ApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()
                val listResult2 = getPropertiesDeferred2.await()
                val listResult3 = getPropertiesDeferred3.await()
                //_text1.value = listResult[0]
                _status.value = ApiStatus.DONE
                _properties.value = listResult
                _properties2.value = listResult2
                _properties3.value = listResult3
                println(properties.value!![0])
            } catch (e: Exception) {
                _properties.value = ArrayList()
                _properties2.value = ArrayList()
                _properties3.value = ArrayList()
                _status.value = ApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}