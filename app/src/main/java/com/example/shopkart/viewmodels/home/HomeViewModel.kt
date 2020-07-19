package com.example.shopkart.viewmodels.home

import androidx.lifecycle.*
import com.example.shopkart.repository.HomeRepository
import kotlinx.coroutines.*

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    //For loading screen => progress bar
    enum class ApiStatus {LOADING, DONE, ERROR}
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    //repository
    val kitTypes = repository.kits
    val items1 = repository.items1
    val items2 = repository.items2
    val items3 = repository.items3
    val cartItems = repository.cartItemsForRecView

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
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
    fun addToCart(id: Int) {
        viewModelScope.launch {
            repository.add(id)
        }
    }

    fun removeFromCart(id: Int) {
        viewModelScope.launch {
            repository.remove(id)
        }
    }
}