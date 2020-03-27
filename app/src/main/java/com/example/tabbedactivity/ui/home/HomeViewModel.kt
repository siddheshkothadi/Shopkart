package com.example.tabbedactivity.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabbedactivity.network.Api
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

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getProperties()
    }

    private fun getProperties() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = Api.retrofitService.getPropertiesApi()
            try {
                _status.value = ApiStatus.LOADING
                // Await the completion of our Retrofit request
                var listResult = getPropertiesDeferred.await()
                _status.value = ApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _properties.value = ArrayList()
                _status.value = ApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}