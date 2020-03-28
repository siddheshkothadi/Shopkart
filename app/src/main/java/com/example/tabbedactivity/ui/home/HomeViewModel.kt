package com.example.tabbedactivity.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabbedactivity.network.Api
import com.example.tabbedactivity.network.Child
import com.example.tabbedactivity.network.ChildApi
import com.example.tabbedactivity.network.Property
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    enum class ApiStatus {LOADING, DONE, ERROR}

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Property>>()
    val properties: LiveData<List<Property>>
        get() = _properties

    /*private val _childData = MutableLiveData<List<Child>>()
    val childData: LiveData<List<Child>>
        get() = _childData*/

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getProperties()
    }

    private fun getProperties() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            val getPropertiesDeferred = Api.retrofitService.getPropertiesApi()
            val getChildPropertiesDeferred = ChildApi.retrofitChildService.getPropertiesChildApi()
            try {
                _status.value = ApiStatus.LOADING
                // Await the completion of our Retrofit request
                val listResult = getPropertiesDeferred.await()
                //val childResult = getChildPropertiesDeferred.await()
                _status.value = ApiStatus.DONE
                _properties.value = listResult
                //_childData.value = childResult
            } catch (e: Exception) {
                _properties.value = ArrayList()
                //_childData.value = ArrayList()
                _status.value = ApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}