package com.example.shopkart.viewmodels.home

import android.app.Application
import androidx.lifecycle.*
import com.example.shopkart.database.getDatabase
import com.example.shopkart.network.*
import com.example.shopkart.repository.Repository
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : ViewModel() {

    enum class ApiStatus {LOADING, DONE, ERROR}

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val repository = Repository(getDatabase(application))

    val kitTypes = repository.kits

    val items1 = repository.items1

    val items2 = repository.items2

    val items3 = repository.items3

    private var viewModelJob = SupervisorJob()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.DONE
                repository.refreshKitsAndItems()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value =
                    ApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}