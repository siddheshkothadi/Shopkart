package com.example.shopkart.viewmodels.home

import android.app.Application
import androidx.lifecycle.*
import com.example.shopkart.database.*
import com.example.shopkart.repository.HomeRepository
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    //For loading screen => progress bar
    enum class ApiStatus {LOADING, DONE, ERROR}
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    //repository
    private val repository = HomeRepository(getDatabase(application))
    val kitTypes = repository.kits
    val items1 = repository.items1
    val items2 = repository.items2
    val items3 = repository.items3
    val cartItems = repository.cartItemsForRecView
    //val bool = repository.bool

    //coroutines
    private var viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                repository.refreshKitsAndItems()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    //onClicks
    fun addToCart(id: Int){
        coroutineScope.launch {
            repository.add(id)
        }
    }
    fun removeFromCart(id: Int) {
        coroutineScope.launch {
            repository.remove(id)
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